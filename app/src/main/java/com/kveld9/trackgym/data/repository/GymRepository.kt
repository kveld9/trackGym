package com.kveld9.trackgym.data.repository

import androidx.room.withTransaction
import com.kveld9.trackgym.data.backup.DuplicatePolicy
import com.kveld9.trackgym.data.backup.ExerciseBackupDto
import com.kveld9.trackgym.data.backup.GymBackupDto
import com.kveld9.trackgym.data.backup.PersonalRecordBackupDto
import com.kveld9.trackgym.data.backup.WorkoutBackupDto
import com.kveld9.trackgym.data.backup.WorkoutExerciseBackupDto
import com.kveld9.trackgym.data.backup.WorkoutSetBackupDto
import com.kveld9.trackgym.data.local.GymDatabase
import com.kveld9.trackgym.data.local.entity.ExerciseEntity
import com.kveld9.trackgym.data.local.entity.PersonalRecordEntity
import com.kveld9.trackgym.data.local.entity.WorkoutEntity
import com.kveld9.trackgym.data.local.entity.WorkoutExerciseEntity
import com.kveld9.trackgym.data.local.entity.WorkoutSetEntity
import com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier
import com.kveld9.trackgym.domain.calculator.PersonalRecordDetector
import com.kveld9.trackgym.domain.calculator.WorkoutComparisonEngine
import com.kveld9.trackgym.domain.calculator.resolveNewRoutineName
import com.kveld9.trackgym.domain.calculator.sanitizeRoutineDefaultReps
import com.kveld9.trackgym.domain.calculator.sanitizeRoutineDefaultWeight
import com.kveld9.trackgym.domain.calculator.sanitizeRoutineSetPlan
import com.kveld9.trackgym.domain.calculator.sanitizeRoutineSetPlans
import com.kveld9.trackgym.domain.calculator.sanitizeRoutineTargetSets
import com.kveld9.trackgym.domain.model.RoutineSetPlan
import com.kveld9.trackgym.domain.util.RoutineShareCodec
import com.kveld9.trackgym.domain.model.DefaultExercises
import com.kveld9.trackgym.domain.model.DuplicateExerciseNameException
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseInUseException
import com.kveld9.trackgym.domain.model.ExerciseModality
import com.kveld9.trackgym.domain.model.canonicalizeExerciseName
import com.kveld9.trackgym.domain.model.classifyExerciseModality
import com.kveld9.trackgym.domain.model.classifyLegacyCardioCategory
import com.kveld9.trackgym.domain.model.hasExerciseReferences
import com.kveld9.trackgym.domain.model.normalizeExerciseName
import com.kveld9.trackgym.domain.model.validateExerciseRename
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.data.local.entity.RoutineEntity
import com.kveld9.trackgym.data.local.entity.RoutineExerciseEntity
import com.kveld9.trackgym.data.local.entity.RoutineFolderEntity
import com.kveld9.trackgym.domain.model.PeriodizedCycle
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineExercise
import com.kveld9.trackgym.domain.model.RoutineFolder
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import com.kveld9.trackgym.domain.util.RoutineShareDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class GymRepository(private val database: GymDatabase) {

    private val exerciseDao = database.exerciseDao()
    private val workoutDao = database.workoutDao()
    private val prDao = database.personalRecordDao()
    private val routineDao = database.routineDao()
    private val customCategoryDao = database.customCategoryDao()
    private val routinePlanMutationMutex = Mutex()

    suspend fun ensureDefaultExercisesSeeded() = withContext(Dispatchers.IO) {
        if (exerciseDao.countExercises() == 0) {
            val entities = DefaultExercises.list.map { ExerciseEntity.fromDomain(it) }
            exerciseDao.insertAll(entities)
        } else {
            val existingNames = exerciseDao.getAllExercisesSync()
                .map { canonicalizeExerciseName(it.name) }
                .toSet()
            val missing = DefaultExercises.list
                .filter { canonicalizeExerciseName(it.name) !in existingNames }
                .map { ExerciseEntity.fromDomain(it) }
            if (missing.isNotEmpty()) {
                exerciseDao.insertAll(missing)
            }
        }
    }

    // CUSTOM EXERCISE CATEGORIES & TAGS
    fun getAllCustomCategories(): Flow<List<com.kveld9.trackgym.domain.model.CustomExerciseCategory>> {
        return customCategoryDao.getAllCategories().map { list -> list.map { it.toDomain() } }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllCustomCategoriesSync(): List<com.kveld9.trackgym.domain.model.CustomExerciseCategory> = withContext(Dispatchers.IO) {
        customCategoryDao.getAllCategoriesSync().map { it.toDomain() }
    }

    suspend fun createCustomCategory(name: String): Long = withContext(Dispatchers.IO) {
        val trimmed = name.replace(",", " ").trim()
        if (trimmed.isBlank()) return@withContext -1L
        customCategoryDao.insertCategory(com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity(name = trimmed))
    }

    suspend fun renameCustomCategory(id: Long, oldName: String, newName: String) = withContext(Dispatchers.IO) {
        val trimmedOld = oldName.replace(",", " ").trim()
        val trimmedNew = newName.replace(",", " ").trim()
        if (trimmedOld.isBlank() || trimmedNew.isBlank() || trimmedOld.equals(trimmedNew, ignoreCase = true)) return@withContext

        database.withTransaction {
            val existingTarget = customCategoryDao.getCategoryByName(trimmedNew)
            if (existingTarget != null && existingTarget.id != id) {
                // If a category with the target name already exists, merge them by deleting the old category record
                customCategoryDao.deleteCategoryById(id)
            } else {
                customCategoryDao.updateCategory(com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity(id = id, name = trimmedNew))
            }

            val affected = exerciseDao.getExercisesWithCategory(trimmedOld)
            for (entity in affected) {
                val updated = com.kveld9.trackgym.domain.util.CustomCategoryCodec.rename(entity.customCategories, trimmedOld, trimmedNew)
                if (updated != entity.customCategories) {
                    exerciseDao.updateExerciseCustomCategories(entity.id, updated)
                }
            }
        }
    }

    suspend fun deleteCustomCategory(id: Long, name: String) = withContext(Dispatchers.IO) {
        val trimmed = name.replace(",", " ").trim()
        database.withTransaction {
            customCategoryDao.deleteCategoryById(id)
            if (trimmed.isNotBlank()) {
                val affected = exerciseDao.getExercisesWithCategory(trimmed)
                for (entity in affected) {
                    val updated = com.kveld9.trackgym.domain.util.CustomCategoryCodec.remove(entity.customCategories, trimmed)
                    if (updated != entity.customCategories) {
                        exerciseDao.updateExerciseCustomCategories(entity.id, updated)
                    }
                }
            }
        }
    }

    suspend fun updateExerciseCustomCategories(exerciseId: Long, categories: List<String>) = withContext(Dispatchers.IO) {
        val serialized = com.kveld9.trackgym.domain.util.CustomCategoryCodec.serialize(categories)
        database.withTransaction {
            exerciseDao.updateExerciseCustomCategories(exerciseId, serialized)
            for (cat in categories) {
                val trimmed = cat.replace(",", " ").trim()
                if (trimmed.isNotBlank()) {
                    customCategoryDao.insertCategory(com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity(name = trimmed))
                }
            }
        }
    }

    // EXERCISES
    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises().map { list -> list.map { it.toDomain() } }
    }

    suspend fun getExerciseById(id: Long): Exercise? = withContext(Dispatchers.IO) {
        exerciseDao.getExerciseById(id)?.toDomain()
    }

    fun searchExercises(query: String): Flow<List<Exercise>> {
        return exerciseDao.searchExercises(query).map { list -> list.map { it.toDomain() } }
    }

    fun getExercisesByMuscleGroup(group: MuscleGroup): Flow<List<Exercise>> {
        return exerciseDao.getExercisesByMuscleGroup(group.name).map { list -> list.map { it.toDomain() } }
    }

    suspend fun createCustomExercise(
        name: String,
        muscleGroup: MuscleGroup,
        category: ExerciseCategory,
        notes: String = "",
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle? = null,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement> = emptyList(),
        mechanics: com.kveld9.trackgym.domain.model.MechanicsType = com.kveld9.trackgym.domain.model.MechanicsType.COMPOUND,
        force: com.kveld9.trackgym.domain.model.ForceType = com.kveld9.trackgym.domain.model.ForceType.PUSH,
        level: com.kveld9.trackgym.domain.model.DifficultyLevel = com.kveld9.trackgym.domain.model.DifficultyLevel.BEGINNER,
        customCategories: List<String> = emptyList()
    ): Long = withContext(Dispatchers.IO) {
        database.withTransaction {
            val trimmedName = normalizeExerciseName(name)
            val canonicalTarget = canonicalizeExerciseName(trimmedName)
            val existing = exerciseDao.getAllExercisesSync().firstOrNull {
                canonicalizeExerciseName(it.name) == canonicalTarget
            }
            if (existing != null) {
                throw DuplicateExerciseNameException(conflictingName = existing.name)
            }
            val modality = classifyExerciseModality(trimmedName)
            val entity = ExerciseEntity(
                name = trimmedName,
                muscleGroup = muscleGroup.name,
                category = category.name,
                modality = modality.name,
                notes = notes.trim(),
                primaryMuscle = primaryMuscle?.name,
                secondaryMuscles = com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry
                    .serializeSecondaryMuscles(secondaryMuscles)
                    .ifBlank { null },
                mechanics = mechanics.name,
                force = force.name,
                level = level.name,
                customCategories = com.kveld9.trackgym.domain.util.CustomCategoryCodec.serialize(customCategories),
                isCustom = true
            )
            val id = exerciseDao.insertExercise(entity)
            for (cat in customCategories) {
                val trimmed = cat.trim()
                if (trimmed.isNotBlank()) {
                    customCategoryDao.insertCategory(
                        com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity(name = trimmed)
                    )
                }
            }
            id
        }
    }

    suspend fun renameExercise(
        exerciseId: Long,
        newName: String,
        muscleGroup: MuscleGroup? = null,
        category: ExerciseCategory? = null,
        notes: String? = null,
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle? = null,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement>? = null,
        mechanics: MechanicsType? = null,
        force: com.kveld9.trackgym.domain.model.ForceType? = null,
        level: com.kveld9.trackgym.domain.model.DifficultyLevel? = null,
        customCategories: List<String>? = null
    ): Exercise = withContext(Dispatchers.IO) {
        database.withTransaction {
            val trimmedName = normalizeExerciseName(newName)
            val canonicalTarget = canonicalizeExerciseName(trimmedName)
            val existing = exerciseDao.getAllExercisesSync().firstOrNull {
                canonicalizeExerciseName(it.name) == canonicalTarget
            }
            validateExerciseRename(
                targetExerciseId = exerciseId,
                candidateName = trimmedName,
                existingMatchId = existing?.id,
                existingMatchName = existing?.name
            )

            val current = exerciseDao.getExerciseById(exerciseId)
                ?: throw IllegalArgumentException("Exercise with id $exerciseId not found")

            val updated = mergeExerciseEdits(
                current = current,
                muscleGroup = muscleGroup,
                category = category,
                notes = notes,
                primaryMuscle = primaryMuscle,
                secondaryMuscles = secondaryMuscles,
                mechanics = mechanics,
                force = force,
                level = level,
                customCategories = customCategories,
                trimmedName = trimmedName
            )
            exerciseDao.updateExercise(updated)
            if (customCategories != null) {
                for (cat in customCategories) {
                    val trimmed = cat.trim()
                    if (trimmed.isNotBlank()) {
                        customCategoryDao.insertCategory(
                            com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity(name = trimmed)
                        )
                    }
                }
            }
            updated.toDomain()
        }
    }

    private fun mergeExerciseEdits(
        current: ExerciseEntity,
        muscleGroup: MuscleGroup?,
        category: ExerciseCategory?,
        notes: String?,
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle?,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement>?,
        mechanics: MechanicsType?,
        force: com.kveld9.trackgym.domain.model.ForceType?,
        level: com.kveld9.trackgym.domain.model.DifficultyLevel?,
        customCategories: List<String>?,
        trimmedName: String
    ): ExerciseEntity {
        val serializedSecondaries = secondaryMuscles?.let {
            com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry.serializeSecondaryMuscles(it)
                .ifBlank { null }
        } ?: current.secondaryMuscles

        val serializedCustomCategories = customCategories?.let {
            com.kveld9.trackgym.domain.util.CustomCategoryCodec.serialize(it)
        } ?: current.customCategories

        val modality = classifyExerciseModality(trimmedName)

        return current.copy(
            name = trimmedName,
            muscleGroup = muscleGroup?.name ?: current.muscleGroup,
            category = category?.name ?: current.category,
            modality = modality.name,
            notes = notes?.trim() ?: current.notes,
            primaryMuscle = primaryMuscle?.name ?: current.primaryMuscle,
            secondaryMuscles = serializedSecondaries,
            mechanics = mechanics?.name ?: current.mechanics,
            force = force?.name ?: current.force,
            level = level?.name ?: current.level,
            customCategories = serializedCustomCategories
        )
    }

    suspend fun deleteExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        exerciseDao.deleteExercise(ExerciseEntity.fromDomain(exercise))
    }

    suspend fun deleteExerciseById(exerciseId: Long) = withContext(Dispatchers.IO) {
        val entity = exerciseDao.getExerciseById(exerciseId) ?: return@withContext
        database.withTransaction {
            val routineCount = routineDao.countRoutineReferences(exerciseId)
            val recordCount = prDao.countRecordReferences(exerciseId)
            if (hasExerciseReferences(routineCount, recordCount)) {
                val reason = if (routineCount > 0) {
                    ExerciseInUseException.Reason.ROUTINE
                } else {
                    ExerciseInUseException.Reason.RECORD
                }
                throw ExerciseInUseException(exerciseId = exerciseId, reason = reason)
            }
            exerciseDao.deleteExercise(entity)
        }
    }

    suspend fun updateExerciseNotes(exerciseId: Long, notes: String) = withContext(Dispatchers.IO) {
        exerciseDao.updateExerciseNotes(exerciseId, notes.trim())
    }

    suspend fun updateExerciseRestDuration(exerciseId: Long, restSeconds: Int?) = withContext(Dispatchers.IO) {
        exerciseDao.updateExerciseRestDuration(exerciseId, restSeconds)
    }

    suspend fun updateExerciseWarmupProtocol(exerciseId: Long, protocol: String?) = withContext(Dispatchers.IO) {
        exerciseDao.updateExerciseWarmupProtocol(exerciseId, protocol)
    }

    suspend fun updateExerciseAutoProgressionRule(exerciseId: Long, rule: String?) = withContext(Dispatchers.IO) {
        exerciseDao.updateExerciseAutoProgressionRule(exerciseId, rule)
    }

    suspend fun updateExerciseAnatomy(
        exerciseId: Long,
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle?,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement>
    ) = withContext(Dispatchers.IO) {
        exerciseDao.updateExerciseAnatomy(
            id = exerciseId,
            primaryMuscle = primaryMuscle?.name,
            secondaryMuscles = com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry.serializeSecondaryMuscles(secondaryMuscles).ifBlank { null }
        )
    }

    suspend fun getRecentSessionsSetsForExercise(
        exerciseId: Long,
        currentWorkoutId: Long,
        limit: Int = 5
    ): List<List<WorkoutSet>> = withContext(Dispatchers.IO) {
        val recentEntities = workoutDao.getRecentCompletedWorkoutExercises(exerciseId, currentWorkoutId, limit)
        recentEntities.map { we ->
            workoutDao.getWorkoutSets(we.id).map { it.toDomain() }
        }
    }

    suspend fun updateWorkoutNotes(workoutId: Long, notes: String) = withContext(Dispatchers.IO) {
        workoutDao.updateWorkoutNotes(workoutId, notes.trim())
    }

    // WORKOUTS
    suspend fun getActiveWorkout(): Workout? = withContext(Dispatchers.IO) {
        val activeEntity = workoutDao.getActiveWorkout() ?: return@withContext null
        buildFullWorkout(activeEntity)
    }

    suspend fun startNewWorkout(title: String = "Entrenamiento"): Workout = withContext(Dispatchers.IO) {
        val existing = workoutDao.getActiveWorkout()
        if (existing != null) {
            return@withContext buildFullWorkout(existing)
        }
        val newEntity = WorkoutEntity(
            title = title,
            startedAt = System.currentTimeMillis(),
            isCompleted = false
        )
        val id = workoutDao.insertWorkout(newEntity)
        Workout(id = id, name = title, startedAt = newEntity.startedAt)
    }

    suspend fun addExerciseToWorkout(workoutId: Long, exerciseId: Long): WorkoutExercise = withContext(Dispatchers.IO) {
        val exercise = exerciseDao.getExerciseById(exerciseId)?.toDomain()
            ?: throw IllegalArgumentException("Exercise $exerciseId not found")

        val existingExercises = workoutDao.getWorkoutExercises(workoutId)
        val orderIndex = existingExercises.size

        val weEntity = WorkoutExerciseEntity(
            workoutId = workoutId,
            exerciseId = exerciseId,
            orderIndex = orderIndex
        )
        val weId = workoutDao.insertWorkoutExercise(weEntity)

        // Seed with 1 initial empty/target set (matching previous session if available)
        val workout = workoutDao.getWorkoutById(workoutId)
        val prevWe = getContextualPreviousWorkoutExercise(exerciseId, workoutId, workout?.routineId)
        val prevCompletedSets = if (prevWe != null) {
            workoutDao.getWorkoutSets(prevWe.id).filter { it.isCompleted }
        } else emptyList()
        val initialWeight = prevCompletedSets.firstOrNull()?.weightKg ?: 0.0
        val initialReps = prevCompletedSets.firstOrNull()?.reps ?: 0

        val initialSetType = if (exercise.modality == ExerciseModality.CARDIO) {
            SetType.CARDIO
        } else {
            SetType.NORMAL
        }
        val initialSet = WorkoutSetEntity(
            workoutExerciseId = weId,
            setNumber = 1,
            setType = initialSetType.name,
            weightKg = initialWeight,
            reps = initialReps,
            isCompleted = false
        )
        val setId = workoutDao.insertWorkoutSet(initialSet)

        WorkoutExercise(
            id = weId,
            workoutId = workoutId,
            exercise = exercise,
            sets = listOf(initialSet.toDomain().copy(id = setId)),
            orderIndex = orderIndex
        )
    }

    suspend fun removeExerciseFromWorkout(workoutExerciseId: Long) = withContext(Dispatchers.IO) {
        workoutDao.deleteWorkoutExercise(workoutExerciseId)
    }

    suspend fun addSetToExercise(
        workoutExerciseId: Long,
        weightKg: Double,
        reps: Int,
        setType: SetType = SetType.NORMAL
    ): WorkoutSet = withContext(Dispatchers.IO) {
        val existingSets = workoutDao.getWorkoutSets(workoutExerciseId)
        val nextNumber = existingSets.size + 1
        val entity = WorkoutSetEntity(
            workoutExerciseId = workoutExerciseId,
            setNumber = nextNumber,
            setType = setType.name,
            weightKg = weightKg,
            reps = reps,
            isCompleted = false
        )
        val id = workoutDao.insertWorkoutSet(entity)
        entity.toDomain().copy(id = id)
    }

    suspend fun updateSet(set: WorkoutSet) = withContext(Dispatchers.IO) {
        workoutDao.updateWorkoutSet(WorkoutSetEntity.fromDomain(set, set.workoutExerciseId))
    }

    suspend fun deleteSet(setId: Long) = withContext(Dispatchers.IO) {
        workoutDao.deleteWorkoutSet(setId)
    }

    suspend fun restoreSet(set: WorkoutSet) = withContext(Dispatchers.IO) {
        workoutDao.insertWorkoutSet(WorkoutSetEntity.fromDomain(set, set.workoutExerciseId))
    }

    suspend fun moveWorkoutExercise(workoutId: Long, fromIndex: Int, toIndex: Int) = withContext(Dispatchers.IO) {
        val exercises = workoutDao.getWorkoutExercises(workoutId).toMutableList()
        if (fromIndex !in exercises.indices || toIndex !in exercises.indices || fromIndex == toIndex) return@withContext
        val item = exercises.removeAt(fromIndex)
        exercises.add(toIndex, item)
        exercises.forEachIndexed { index, we ->
            workoutDao.updateExerciseOrder(we.id, index)
        }
    }

    suspend fun swapExerciseInWorkout(workoutExerciseId: Long, newExerciseId: Long, resetSets: Boolean = false) = withContext(Dispatchers.IO) {
        workoutDao.swapExercise(workoutExerciseId, newExerciseId)
        if (resetSets) {
            val sets = workoutDao.getWorkoutSets(workoutExerciseId)
            sets.forEach { set ->
                workoutDao.deleteWorkoutSet(set.id)
            }
            val newExercise = exerciseDao.getExerciseById(newExerciseId)
            val initialSetType = if (newExercise?.let {
                ExerciseModality.fromString(it.modality) == ExerciseModality.CARDIO
            } == true) {
                SetType.CARDIO
            } else {
                SetType.NORMAL
            }
            val initial = WorkoutSetEntity(
                workoutExerciseId = workoutExerciseId,
                setNumber = 1,
                setType = initialSetType.name,
                weightKg = 0.0,
                reps = 0,
                isCompleted = false
            )
            workoutDao.insertWorkoutSet(initial)
        }
    }

    suspend fun updateExerciseSupersetGroup(workoutExerciseId: Long, supersetGroupId: String?) = withContext(Dispatchers.IO) {
        workoutDao.updateExerciseSupersetGroup(workoutExerciseId, supersetGroupId)
    }

    suspend fun insertWarmupSets(
        workoutExerciseId: Long,
        warmupSets: List<WorkoutSet>
    ) = withContext(Dispatchers.IO) {
        if (warmupSets.isEmpty()) return@withContext
        database.withTransaction {
            val existingSets = workoutDao.getWorkoutSets(workoutExerciseId).map { it.toDomain() }
            val completedWarmups = existingSets.filter { it.setType == SetType.WARMUP && it.isCompleted }
            val completedWarmupWeights = completedWarmups.map { it.weightKg }
            val newWarmups = warmupSets.filter { ws ->
                completedWarmupWeights.none { completedWeight -> kotlin.math.abs(completedWeight - ws.weightKg) < 0.01 }
            }
            val allWarmups = (completedWarmups + newWarmups).sortedBy { it.weightKg }
            val nonWarmupExisting = existingSets.filter { it.setType != SetType.WARMUP }

            workoutDao.deleteWorkoutSetsForExercise(workoutExerciseId)

            var currentSetNumber = 1
            allWarmups.forEach { ws ->
                val entity = WorkoutSetEntity(
                    workoutExerciseId = workoutExerciseId,
                    setNumber = currentSetNumber++,
                    setType = SetType.WARMUP.name,
                    weightKg = ws.weightKg,
                    reps = ws.reps,
                    isCompleted = ws.isCompleted,
                    completedAt = ws.completedAt,
                    rpe = ws.rpe
                )
                workoutDao.insertWorkoutSet(entity)
            }

            nonWarmupExisting.forEach { set ->
                val entity = WorkoutSetEntity(
                    workoutExerciseId = workoutExerciseId,
                    setNumber = currentSetNumber++,
                    setType = set.setType.name,
                    weightKg = set.weightKg,
                    reps = set.reps,
                    isCompleted = set.isCompleted,
                    completedAt = set.completedAt,
                    rpe = set.rpe
                )
                workoutDao.insertWorkoutSet(entity)
            }
        }
    }

    suspend fun completeSet(
        set: WorkoutSet,
        workoutId: Long,
        exerciseId: Long,
        formula: com.kveld9.trackgym.domain.calculator.OneRepMaxFormula = com.kveld9.trackgym.domain.calculator.OneRepMaxFormula.EPLEY
    ): List<PersonalRecord> = withContext(Dispatchers.IO) {
        val completedSet = set.copy(
            isCompleted = true,
            completedAt = System.currentTimeMillis()
        )
        workoutDao.updateWorkoutSet(WorkoutSetEntity.fromDomain(completedSet, completedSet.workoutExerciseId))

        // Check for personal records
        val historicalSets = workoutDao.getAllCompletedSetsForExercise(exerciseId).map { it.toDomain() }
        val unlockedPrs = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = completedSet,
            historicalSets = historicalSets,
            formula = formula
        )

        if (unlockedPrs.isNotEmpty()) {
            val entities = unlockedPrs.map { PersonalRecordEntity.fromDomain(it) }
            prDao.insertAll(entities)
        }

        unlockedPrs
    }

    suspend fun finishWorkout(
        workoutId: Long,
        durationSeconds: Long,
        notes: String = "",
        weightUnit: com.kveld9.trackgym.domain.model.WeightUnit = com.kveld9.trackgym.domain.model.WeightUnit.KG,
        userBodyWeightKg: Double = 0.0,
        detachRoutine: Boolean = false,
        completedAtTimestamp: Long? = null
    ): WorkoutComparison? = withContext(Dispatchers.IO) {
        val entity = workoutDao.getWorkoutById(workoutId)
            ?: return@withContext null

        val finalCompletedAt = completedAtTimestamp ?: System.currentTimeMillis()
        val completedEntity = entity.copy(
            isCompleted = true,
            completedAt = finalCompletedAt,
            durationSeconds = durationSeconds,
            notes = notes,
            routineId = if (detachRoutine) null else entity.routineId
        )
        workoutDao.updateWorkout(completedEntity)

        compareWorkoutWithPrevious(workoutId, weightUnit, userBodyWeightKg)
    }

    fun getCompletedWorkouts(): Flow<List<Workout>> {
        return workoutDao.getCompletedWorkouts().map { list ->
            list.map { buildFullWorkout(it) }
        }
    }

    fun getRecentCompletedExerciseIds(limit: Int = 20): Flow<List<Long>> {
        return workoutDao.getRecentCompletedExerciseIds(limit)
    }

    suspend fun getFullWorkout(workoutId: Long): Workout? = withContext(Dispatchers.IO) {
        val entity = workoutDao.getWorkoutById(workoutId) ?: return@withContext null
        buildFullWorkout(entity)
    }

    suspend fun discardActiveWorkout(workoutId: Long) = withContext(Dispatchers.IO) {
        database.withTransaction {
            prDao.deleteRecordsForWorkout(workoutId)
            workoutDao.deleteWorkoutById(workoutId)
        }
    }

    suspend fun getContextualPreviousWorkoutExercise(
        exerciseId: Long,
        currentWorkoutId: Long,
        routineId: Long?
    ): WorkoutExerciseEntity? {
        return if (routineId != null) {
            workoutDao.getLastCompletedWorkoutExerciseForRoutine(exerciseId, currentWorkoutId, routineId)
                ?: workoutDao.getLastCompletedWorkoutExercise(exerciseId, currentWorkoutId)
        } else {
            workoutDao.getLastCompletedWorkoutExercise(exerciseId, currentWorkoutId)
        }
    }

    suspend fun getPreviousSetsForExercise(
        exerciseId: Long,
        currentWorkoutId: Long,
        routineId: Long? = null
    ): List<WorkoutSet> = withContext(Dispatchers.IO) {
        val prevWe = getContextualPreviousWorkoutExercise(exerciseId, currentWorkoutId, routineId)
            ?: return@withContext emptyList()
        workoutDao.getWorkoutSets(prevWe.id).map { it.toDomain() }.filter { it.isCompleted }
    }

    suspend fun compareWorkoutWithPrevious(
        workoutId: Long,
        weightUnit: com.kveld9.trackgym.domain.model.WeightUnit = com.kveld9.trackgym.domain.model.WeightUnit.KG,
        userBodyWeightKg: Double = 0.0
    ): WorkoutComparison? = withContext(Dispatchers.IO) {
        val currentWorkout = getFullWorkout(workoutId) ?: return@withContext null
        val exerciseComparisons = mutableListOf<ExerciseComparison>()

        for (workoutExercise in currentWorkout.exercises) {
            val exerciseId = workoutExercise.exercise.id
            val prevWe = getContextualPreviousWorkoutExercise(exerciseId, workoutId, currentWorkout.routineId)
            val prevWorkout = if (prevWe != null) workoutDao.getWorkoutById(prevWe.workoutId) else null
            val prevSets = if (prevWe != null) {
                workoutDao.getWorkoutSets(prevWe.id).map { it.toDomain() }.filter { it.isCompleted }
            } else emptyList()

            val prevWorkoutExercise = prevWe?.let {
                WorkoutExercise(
                    id = it.id,
                    workoutId = it.workoutId,
                    exercise = workoutExercise.exercise,
                    sets = prevSets,
                    orderIndex = it.orderIndex,
                    notes = it.notes,
                    supersetGroupId = it.supersetGroupId
                )
            }

            val recordsForExercise = prDao.getRecordsForWorkout(workoutId).filter { it.exerciseId == exerciseId }.map { it.toDomain() }

            val comp = WorkoutComparisonEngine.compareExercise(
                exercise = workoutExercise.exercise,
                currentWorkoutExercise = workoutExercise,
                previousWorkoutExercise = prevWorkoutExercise,
                previousWorkoutDate = prevWorkout?.completedAt,
                recordsUnlocked = recordsForExercise,
                weightUnit = weightUnit,
                userBodyWeightKg = userBodyWeightKg
            )
            exerciseComparisons.add(comp)
        }

        val totalPrs = prDao.getRecordsForWorkout(workoutId).map { it.toDomain() }
        val totalVolumeDelta = exerciseComparisons.sumOf { it.totalVolumeDeltaKg }

        WorkoutComparison(
            currentWorkout = currentWorkout,
            exerciseComparisons = exerciseComparisons,
            totalRecordsUnlocked = totalPrs,
            totalVolumeDeltaKg = totalVolumeDelta
        )
    }

    // RECORDS
    fun getRecordsForExercise(exerciseId: Long): Flow<List<PersonalRecord>> {
        return prDao.getRecordsForExercise(exerciseId).map { list -> list.map { it.toDomain() } }
    }

    fun getAllRecords(): Flow<List<PersonalRecord>> {
        return prDao.getAllRecords().map { list -> list.map { it.toDomain() } }
    }

    private suspend fun buildFullWorkout(entity: WorkoutEntity): Workout {
        val weEntities = workoutDao.getWorkoutExercises(entity.id)
        val workoutExercises = weEntities.map { we ->
            val exercise = exerciseDao.getExerciseById(we.exerciseId)?.toDomain()
                ?: Exercise(id = we.exerciseId, name = "Ejercicio", muscleGroup = MuscleGroup.OTHER)
            val sets = workoutDao.getWorkoutSets(we.id).map { it.toDomain() }
            WorkoutExercise(
                id = we.id,
                workoutId = entity.id,
                exercise = exercise,
                sets = sets,
                orderIndex = we.orderIndex,
                notes = we.notes,
                supersetGroupId = we.supersetGroupId
            )
        }
        return Workout(
            id = entity.id,
            name = entity.title,
            startedAt = entity.startedAt,
            completedAt = entity.completedAt,
            durationSeconds = entity.durationSeconds,
            isCompleted = entity.isCompleted,
            notes = entity.notes,
            exercises = workoutExercises,
            routineId = entity.routineId
        )
    }

    // BACKUP & RESTORE
    suspend fun getBackupData(): GymBackupDto = withContext(Dispatchers.IO) {
        val exercises = exerciseDao.getAllExercisesSync().map {
            ExerciseBackupDto(
                id = it.id,
                name = it.name,
                muscleGroup = it.muscleGroup,
                category = it.category,
                notes = it.notes,
                isCustom = it.isCustom,
                createdAt = it.createdAt,
                mechanics = it.mechanics,
                modality = it.modality
            )
        }

        val workouts = workoutDao.getAllWorkoutsSync().map { workout ->
            val weEntities = workoutDao.getWorkoutExercises(workout.id)
            val workoutExercisesDto = weEntities.map { we ->
                val exName = exerciseDao.getExerciseById(we.exerciseId)?.name ?: "Ejercicio"
                val setsDto = workoutDao.getWorkoutSets(we.id).map { s ->
                    WorkoutSetBackupDto(
                        setNumber = s.setNumber,
                        setType = s.setType,
                        weightKg = s.weightKg,
                        reps = s.reps,
                        durationSeconds = s.durationSeconds,
                        rpe = s.rpe,
                        isCompleted = s.isCompleted,
                        completedAt = s.completedAt
                    )
                }
                WorkoutExerciseBackupDto(
                    exerciseName = exName,
                    orderIndex = we.orderIndex,
                    notes = we.notes,
                    supersetGroupId = we.supersetGroupId,
                    sets = setsDto
                )
            }
            WorkoutBackupDto(
                id = workout.id,
                title = workout.title,
                startedAt = workout.startedAt,
                completedAt = workout.completedAt,
                durationSeconds = workout.durationSeconds,
                isCompleted = workout.isCompleted,
                notes = workout.notes,
                exercises = workoutExercisesDto
            )
        }

        val prEntities = prDao.getAllRecordsSync()
        val prsDto = prEntities.map { pr ->
            val exName = exerciseDao.getExerciseById(pr.exerciseId)?.name ?: "Ejercicio"
            PersonalRecordBackupDto(
                exerciseName = exName,
                recordType = pr.recordType,
                recordValue = pr.recordValue,
                weightKg = pr.weightKg,
                reps = pr.reps,
                achievedAt = pr.achievedAt,
                description = pr.description
            )
        }

        GymBackupDto(
            exportedAt = "",
            exercises = exercises,
            workouts = workouts,
            personalRecords = prsDto
        )
    }

    suspend fun hasExistingData(): Boolean = withContext(Dispatchers.IO) {
        workoutDao.getAllWorkoutsSync().isNotEmpty()
    }

    suspend fun importBackup(backup: GymBackupDto, policy: DuplicatePolicy): Pair<Int, Int> = withContext(Dispatchers.IO) {
        database.withTransaction {
            var insertedCount = 0
            var skippedCount = 0

            if (policy == DuplicatePolicy.OVERWRITE_ALL) {
                prDao.deleteAllRecords()
                workoutDao.deleteAllWorkoutSets()
                workoutDao.deleteAllWorkoutExercises()
                workoutDao.deleteAllWorkouts()
            }

            // 1. Process Exercises
            val exerciseNameToIdMap = mutableMapOf<String, Long>()
            val existingExercises = exerciseDao.getAllExercisesSync()
            existingExercises.forEach {
                exerciseNameToIdMap[canonicalizeExerciseName(it.name)] = it.id
            }

            for (exDto in backup.exercises) {
                val key = canonicalizeExerciseName(exDto.name)
                val group = MuscleGroup.fromString(exDto.muscleGroup)
                val cat = if (exDto.category.equals("CARDIO", ignoreCase = true)) {
                    classifyLegacyCardioCategory(exDto.name)
                } else {
                    ExerciseCategory.fromString(exDto.category)
                }
                val mechanics = if (exDto.mechanics.isNotBlank()) {
                    exDto.mechanics
                } else {
                    BiomechanicalClassifier.classifyMechanics(
                        name = exDto.name,
                        category = cat,
                        muscleGroup = group
                    ).name
                }
                val force = BiomechanicalClassifier.classifyForce(exDto.name, cat, group).name
                val level = BiomechanicalClassifier.classifyDifficulty(exDto.name, cat, group, MechanicsType.fromString(mechanics)).name

                if (!exerciseNameToIdMap.containsKey(key)) {
                    val newEntity = ExerciseEntity(
                        name = exDto.name.trim(),
                        muscleGroup = exDto.muscleGroup,
                        category = cat.name,
                        modality = (ExerciseModality.parseOrNull(exDto.modality)
                            ?: classifyExerciseModality(exDto.name)).name,
                        notes = exDto.notes,
                        isCustom = exDto.isCustom,
                        createdAt = if (exDto.createdAt > 0) exDto.createdAt else System.currentTimeMillis(),
                        mechanics = mechanics,
                        force = force,
                        level = level
                    )
                    val newId = exerciseDao.insertExercise(newEntity)
                    exerciseNameToIdMap[key] = newId
                    insertedCount++
                } else if (policy == DuplicatePolicy.OVERWRITE_ALL) {
                    val existingId = exerciseNameToIdMap[key]!!
                    val existing = exerciseDao.getExerciseById(existingId)
                    val updated = ExerciseEntity(
                        id = existingId,
                        name = exDto.name.trim(),
                        muscleGroup = exDto.muscleGroup,
                        category = cat.name,
                        modality = (ExerciseModality.parseOrNull(exDto.modality)
                            ?: classifyExerciseModality(exDto.name)).name,
                        notes = exDto.notes,
                        restDurationSeconds = existing?.restDurationSeconds,
                        warmupRampProtocol = existing?.warmupRampProtocol,
                        autoProgressionRule = existing?.autoProgressionRule,
                        primaryMuscle = existing?.primaryMuscle,
                        secondaryMuscles = existing?.secondaryMuscles,
                        mechanics = mechanics,
                        force = force,
                        level = level,
                        customCategories = existing?.customCategories ?: "",
                        isCustom = exDto.isCustom,
                        createdAt = if (exDto.createdAt > 0) exDto.createdAt else System.currentTimeMillis()
                    )
                    exerciseDao.insertExercise(updated)
                }
            }

            // 2. Process Workouts
            val existingWorkouts = if (policy == DuplicatePolicy.OVERWRITE_ALL) emptyList() else workoutDao.getAllWorkoutsSync()
            val existingStartedAts = existingWorkouts.map { it.startedAt }.toSet()

            for (wDto in backup.workouts) {
                val isExisting = existingStartedAts.contains(wDto.startedAt)
                if (isExisting && policy == DuplicatePolicy.SKIP_EXISTING) {
                    skippedCount++
                    continue
                }

                val workoutEntity = WorkoutEntity(
                    title = wDto.title,
                    startedAt = wDto.startedAt,
                    completedAt = wDto.completedAt,
                    durationSeconds = wDto.durationSeconds,
                    isCompleted = wDto.isCompleted,
                    notes = wDto.notes
                )
                val newWorkoutId = workoutDao.insertWorkout(workoutEntity)
                insertedCount++

                for (weDto in wDto.exercises) {
                    val exKey = canonicalizeExerciseName(weDto.exerciseName)
                    var exId = exerciseNameToIdMap[exKey]
                    if (exId == null) {
                        val mech = BiomechanicalClassifier.classifyMechanics(weDto.exerciseName)
                        val force = BiomechanicalClassifier.classifyForce(weDto.exerciseName, ExerciseCategory.BARBELL, MuscleGroup.OTHER).name
                        val level = BiomechanicalClassifier.classifyDifficulty(weDto.exerciseName, ExerciseCategory.BARBELL, MuscleGroup.OTHER, mech).name
                        val newEx = ExerciseEntity(
                            name = weDto.exerciseName.trim(),
                            muscleGroup = MuscleGroup.OTHER.name,
                            category = ExerciseCategory.BARBELL.name,
                            modality = classifyExerciseModality(weDto.exerciseName).name,
                            isCustom = true,
                            mechanics = mech.name,
                            force = force,
                            level = level
                        )
                        exId = exerciseDao.insertExercise(newEx)
                        exerciseNameToIdMap[exKey] = exId
                    }

                    val weEntity = WorkoutExerciseEntity(
                        workoutId = newWorkoutId,
                        exerciseId = exId,
                        orderIndex = weDto.orderIndex,
                        notes = weDto.notes,
                        supersetGroupId = weDto.supersetGroupId
                    )
                    val newWeId = workoutDao.insertWorkoutExercise(weEntity)

                    for (sDto in weDto.sets) {
                        val setEntity = WorkoutSetEntity(
                            workoutExerciseId = newWeId,
                            setNumber = sDto.setNumber,
                            setType = sDto.setType,
                            weightKg = sDto.weightKg,
                            reps = sDto.reps,
                            durationSeconds = sDto.durationSeconds,
                            rpe = sDto.rpe,
                            isCompleted = sDto.isCompleted,
                            completedAt = sDto.completedAt
                        )
                        workoutDao.insertWorkoutSet(setEntity)
                    }
                }
            }

            // 3. Process Personal Records
            for (prDto in backup.personalRecords) {
                val exKey = canonicalizeExerciseName(prDto.exerciseName)
                val exId = exerciseNameToIdMap[exKey] ?: continue
                val prEntity = PersonalRecordEntity(
                    exerciseId = exId,
                    recordType = prDto.recordType,
                    recordValue = prDto.recordValue,
                    weightKg = prDto.weightKg,
                    reps = prDto.reps,
                    achievedAt = prDto.achievedAt,
                    workoutId = 0,
                    description = prDto.description
                )
                prDao.insertRecord(prEntity)
            }

            insertedCount to skippedCount
        }
    }

    // ROUTINES & FOLDERS
    fun getAllFolders(): Flow<List<RoutineFolder>> {
        return routineDao.getAllFolders().map { list -> list.map { it.toDomain() } }
    }

    suspend fun createFolder(name: String): Long = withContext(Dispatchers.IO) {
        routineDao.insertFolder(RoutineFolderEntity(name = name.trim()))
    }

    suspend fun createEmptyRoutine(name: String, folderId: Long? = null): Long = withContext(Dispatchers.IO) {
        val resolvedName = resolveNewRoutineName(name) ?: return@withContext -1L
        val routineEntity = RoutineEntity(
            folderId = folderId,
            name = resolvedName,
            notes = ""
        )
        routineDao.insertRoutine(routineEntity)
    }

    suspend fun deleteFolder(id: Long) = withContext(Dispatchers.IO) {
        routineDao.deleteFolder(id)
    }

    fun getAllRoutines(): Flow<List<Routine>> {
        return combine(
            routineDao.getAllRoutines(),
            routineDao.getAllFolders(),
            exerciseDao.getAllExercises(),
            routineDao.getAllRoutineExercisesFlow()
        ) { routines, folders, exercises, routineExercises ->
            val folderMap = folders.associateBy { it.id }
            val exerciseMap = exercises.associateBy { it.id }
            val exercisesByRoutine = routineExercises.groupBy { it.routineId }

            routines.map { entity ->
                val exercisesForRoutine = exercisesByRoutine[entity.id].orEmpty()
                val mappedExercises = exercisesForRoutine.mapNotNull { re ->
                    val ex = exerciseMap[re.exerciseId]?.toDomain() ?: return@mapNotNull null
                    val plans = parseSetPlansJson(
                        re.setPlansJson,
                        re.targetSets,
                        re.defaultWeightKg,
                        re.defaultReps
                    )
                    RoutineExercise(
                        id = re.id,
                        exercise = ex,
                        orderIndex = re.orderIndex,
                        sets = plans
                    )
                }
                Routine(
                    id = entity.id,
                    folderId = entity.folderId,
                    folderName = folderMap[entity.folderId]?.name,
                    name = entity.name,
                    notes = entity.notes,
                    exercises = mappedExercises,
                    orderIndex = entity.orderIndex,
                    isArchived = entity.isArchived,
                    isPeriodized = false,
                    periodizedCycle = null,
                    createdAt = entity.createdAt
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        internal fun parseSetPlansJson(
            jsonStr: String?,
            fallbackSets: Int,
            fallbackWeight: Double,
            fallbackReps: Int
        ): List<RoutineSetPlan> {
            if (!jsonStr.isNullOrBlank()) {
                val fastParsed = runCatching {
                    RoutineShareCodec.json.decodeFromString<List<RoutineSetPlan>>(jsonStr)
                }.getOrNull()
                val survivors = if (!fastParsed.isNullOrEmpty()) {
                    fastParsed
                } else {
                    val element = runCatching {
                        RoutineShareCodec.json.parseToJsonElement(jsonStr)
                    }.getOrNull()
                    RoutineShareCodec.recoverSetPlans(element)
                }
                if (survivors.isNotEmpty()) {
                    return sanitizeRoutineSetPlans(survivors)
                }
            }
            return sanitizeRoutineSetPlans(
                null,
                fallbackSets = fallbackSets,
                fallbackWeight = fallbackWeight,
                fallbackReps = fallbackReps
            )
        }
    }

    private fun serializeSetPlans(plans: List<RoutineSetPlan>): String {
        return RoutineShareCodec.json.encodeToString(plans)
    }

    suspend fun addExerciseToRoutine(
        routineId: Long,
        exerciseId: Long,
        targetSets: Int = 3,
        defaultWeightKg: Double = 0.0,
        defaultReps: Int = 10,
        initialPlans: List<RoutineSetPlan>? = null
    ): Long = withContext(Dispatchers.IO) {
        val existing = routineDao.getExercisesForRoutine(routineId)
        val plans = if (!initialPlans.isNullOrEmpty()) {
            sanitizeRoutineSetPlans(initialPlans)
        } else {
            sanitizeRoutineSetPlans(null, targetSets, defaultWeightKg, defaultReps)
        }
        val entity = RoutineExerciseEntity(
            routineId = routineId,
            exerciseId = exerciseId,
            orderIndex = existing.size,
            targetSets = plans.size,
            defaultWeightKg = plans.firstOrNull()?.weightKg ?: 0.0,
            defaultReps = plans.firstOrNull()?.reps ?: 10,
            setPlansJson = serializeSetPlans(plans)
        )
        routineDao.insertRoutineExercise(entity)
    }

    suspend fun removeExerciseFromRoutine(routineExerciseId: Long) = withContext(Dispatchers.IO) {
        routineDao.deleteRoutineExercise(routineExerciseId)
    }

    suspend fun updateRoutineExercise(
        routineExerciseId: Long,
        targetSets: Int,
        defaultWeightKg: Double,
        defaultReps: Int
    ) = withContext(Dispatchers.IO) {
        // withTransaction guarantees crash-atomicity; mutex serializes concurrent writers.
        routinePlanMutationMutex.withLock {
            val safeSets = sanitizeRoutineTargetSets(targetSets)
            val safeWeight = sanitizeRoutineDefaultWeight(defaultWeightKg)
            val safeReps = sanitizeRoutineDefaultReps(defaultReps)
            val plans = List(safeSets) {
                RoutineSetPlan(weightKg = safeWeight, reps = safeReps)
            }
            updateRoutineExercisePlans(routineExerciseId, plans)
        }
    }

    suspend fun updateRoutineExercisePlans(
        routineExerciseId: Long,
        plans: List<RoutineSetPlan>
    ) = withContext(Dispatchers.IO) {
        val safePlans = sanitizeRoutineSetPlans(plans)
        routineDao.updateRoutineExercisePlans(
            id = routineExerciseId,
            targetSets = safePlans.size,
            defaultWeightKg = safePlans.firstOrNull()?.weightKg ?: 0.0,
            defaultReps = safePlans.firstOrNull()?.reps ?: 10,
            setPlansJson = serializeSetPlans(safePlans)
        )
    }

    suspend fun updateRoutineSetPlan(
        routineExerciseId: Long,
        setIndex: Int,
        updatedPlan: RoutineSetPlan
    ) = withContext(Dispatchers.IO) {
        // withTransaction guarantees crash-atomicity; mutex serializes concurrent writers.
        routinePlanMutationMutex.withLock {
            database.withTransaction {
                val exercise = routineDao.getRoutineExerciseById(routineExerciseId) ?: return@withTransaction
                val currentPlans = parseSetPlansJson(
                    exercise.setPlansJson,
                    exercise.targetSets,
                    exercise.defaultWeightKg,
                    exercise.defaultReps
                ).toMutableList()
                if (setIndex in currentPlans.indices) {
                    currentPlans[setIndex] = sanitizeRoutineSetPlan(updatedPlan)
                    updateRoutineExercisePlans(routineExerciseId, currentPlans)
                }
            }
        }
    }

    suspend fun addRoutineSet(
        routineExerciseId: Long
    ) = withContext(Dispatchers.IO) {
        // withTransaction guarantees crash-atomicity; mutex serializes concurrent writers.
        routinePlanMutationMutex.withLock {
            database.withTransaction {
                val exercise = routineDao.getRoutineExerciseById(routineExerciseId) ?: return@withTransaction
                val currentPlans = parseSetPlansJson(
                    exercise.setPlansJson,
                    exercise.targetSets,
                    exercise.defaultWeightKg,
                    exercise.defaultReps
                ).toMutableList()
                val lastPlan = currentPlans.lastOrNull() ?: RoutineSetPlan()
                currentPlans.add(lastPlan.copy())
                updateRoutineExercisePlans(routineExerciseId, currentPlans)
            }
        }
    }

    suspend fun removeRoutineSet(
        routineExerciseId: Long,
        setIndex: Int
    ) = withContext(Dispatchers.IO) {
        // withTransaction guarantees crash-atomicity; mutex serializes concurrent writers.
        routinePlanMutationMutex.withLock {
            database.withTransaction {
                val exercise = routineDao.getRoutineExerciseById(routineExerciseId) ?: return@withTransaction
                val currentPlans = parseSetPlansJson(
                    exercise.setPlansJson,
                    exercise.targetSets,
                    exercise.defaultWeightKg,
                    exercise.defaultReps
                ).toMutableList()
                if (currentPlans.size > 1 && setIndex in currentPlans.indices) {
                    currentPlans.removeAt(setIndex)
                    updateRoutineExercisePlans(routineExerciseId, currentPlans)
                }
            }
        }
    }

    suspend fun duplicateRoutineSet(
        routineExerciseId: Long,
        setIndex: Int
    ) = withContext(Dispatchers.IO) {
        // withTransaction guarantees crash-atomicity; mutex serializes concurrent writers.
        routinePlanMutationMutex.withLock {
            database.withTransaction {
                val exercise = routineDao.getRoutineExerciseById(routineExerciseId) ?: return@withTransaction
                val currentPlans = parseSetPlansJson(
                    exercise.setPlansJson,
                    exercise.targetSets,
                    exercise.defaultWeightKg,
                    exercise.defaultReps
                ).toMutableList()
                if (setIndex in currentPlans.indices) {
                    val toCopy = currentPlans[setIndex]
                    currentPlans.add(setIndex + 1, toCopy.copy())
                    updateRoutineExercisePlans(routineExerciseId, currentPlans)
                }
            }
        }
    }

    suspend fun moveRoutineExercise(routineId: Long, fromIndex: Int, toIndex: Int) = withContext(Dispatchers.IO) {
        val exercises = routineDao.getExercisesForRoutine(routineId).toMutableList()
        if (fromIndex !in exercises.indices || toIndex !in exercises.indices || fromIndex == toIndex) return@withContext
        val item = exercises.removeAt(fromIndex)
        exercises.add(toIndex, item)
        exercises.forEachIndexed { index, re ->
            routineDao.updateRoutineExerciseOrder(re.id, index)
        }
    }

    suspend fun updateRoutinesOrder(orderedRoutineIds: List<Long>) = withContext(Dispatchers.IO) {
        orderedRoutineIds.forEachIndexed { index, id ->
            routineDao.updateRoutineOrder(id, index)
        }
    }

    suspend fun updateFoldersOrder(orderedFolderIds: List<Long>) = withContext(Dispatchers.IO) {
        orderedFolderIds.forEachIndexed { index, id ->
            routineDao.updateFolderOrder(id, index)
        }
    }

    suspend fun saveWorkoutAsRoutine(
        workoutId: Long,
        routineName: String,
        folderId: Long? = null
    ): Long = withContext(Dispatchers.IO) {
        val workout = getFullWorkout(workoutId) ?: return@withContext -1L
        val routineEntity = RoutineEntity(
            folderId = folderId,
            name = routineName.ifBlank { workout.name },
            notes = workout.notes
        )
        val routineId = routineDao.insertRoutine(routineEntity)

        val routineExercises = workout.exercises.mapIndexed { index, we ->
            val sourceSets = we.sets.filter { it.setType != SetType.WARMUP && it.isCompleted }
                .ifEmpty { we.sets.filter { it.setType != SetType.WARMUP } }
                .ifEmpty { we.sets }
            val plans = if (sourceSets.isNotEmpty()) {
                sourceSets.map { s ->
                    RoutineSetPlan(
                        weightKg = sanitizeRoutineDefaultWeight(s.weightKg),
                        reps = sanitizeRoutineDefaultReps(s.reps),
                        setType = s.setType
                    )
                }
            } else {
                List(3) { RoutineSetPlan() }
            }
            RoutineExerciseEntity(
                routineId = routineId,
                exerciseId = we.exercise.id,
                orderIndex = index,
                targetSets = plans.size,
                defaultWeightKg = plans.firstOrNull()?.weightKg ?: 0.0,
                defaultReps = plans.firstOrNull()?.reps ?: 10,
                setPlansJson = serializeSetPlans(plans)
            )
        }
        routineDao.insertRoutineExercises(routineExercises)
        routineId
    }

    suspend fun startWorkoutFromRoutine(routineId: Long): Long = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext -1L
        val active = getActiveWorkout()
        if (active != null) return@withContext active.id

        val workoutEntity = WorkoutEntity(
            title = routine.name,
            startedAt = System.currentTimeMillis(),
            notes = routine.notes,
            isCompleted = false,
            routineId = routineId
        )
        val workoutId = workoutDao.insertWorkout(workoutEntity)
        val routineExercises = routineDao.getExercisesForRoutine(routineId)
        val exerciseModalities = exerciseDao.getAllExercisesSync().associate { it.id to it.modality }

        for (re in routineExercises) {
            val weEntity = WorkoutExerciseEntity(
                workoutId = workoutId,
                exerciseId = re.exerciseId,
                orderIndex = re.orderIndex
            )
            val weId = workoutDao.insertWorkoutExercise(weEntity)
            val modality = ExerciseModality.fromString(exerciseModalities[re.exerciseId])
            val defaultSetType = if (modality == ExerciseModality.CARDIO) {
                SetType.CARDIO
            } else {
                SetType.NORMAL
            }

            val plans = parseSetPlansJson(
                re.setPlansJson,
                re.targetSets,
                re.defaultWeightKg,
                re.defaultReps
            )

            plans.forEachIndexed { index, plan ->
                val effectiveType = if (plan.setType != SetType.NORMAL) {
                    plan.setType
                } else {
                    defaultSetType
                }
                val setEntity = WorkoutSetEntity(
                    workoutExerciseId = weId,
                    setNumber = index + 1,
                    weightKg = plan.weightKg,
                    reps = plan.reps,
                    isCompleted = false,
                    setType = effectiveType.name
                )
                workoutDao.insertWorkoutSet(setEntity)
            }
        }
        workoutId
    }

    suspend fun duplicateRoutine(routineId: Long, copySuffix: String = "(Copy)"): Long = withContext(Dispatchers.IO) {
        val original = routineDao.getRoutineById(routineId) ?: return@withContext -1L
        val originalExercises = routineDao.getExercisesForRoutine(routineId)

        val newRoutine = RoutineEntity(
            folderId = original.folderId,
            name = "${original.name} $copySuffix".trim(),
            notes = original.notes,
            orderIndex = original.orderIndex + 1,
            isArchived = original.isArchived,
            isPeriodized = original.isPeriodized,
            periodizedCycleData = original.periodizedCycleData,
            createdAt = System.currentTimeMillis()
        )
        val newRoutineId = routineDao.insertRoutine(newRoutine)

        val duplicatedExercises = originalExercises.map { re ->
            RoutineExerciseEntity(
                routineId = newRoutineId,
                exerciseId = re.exerciseId,
                orderIndex = re.orderIndex,
                targetSets = re.targetSets,
                defaultWeightKg = re.defaultWeightKg,
                defaultReps = re.defaultReps,
                setPlansJson = re.setPlansJson
            )
        }
        routineDao.insertRoutineExercises(duplicatedExercises)
        newRoutineId
    }



    suspend fun setRoutineArchived(routineId: Long, isArchived: Boolean) = withContext(Dispatchers.IO) {
        routineDao.updateRoutineArchived(routineId, isArchived)
    }


    suspend fun importRoutineFromShareDto(dto: RoutineShareDto): Long = withContext(Dispatchers.IO) {
        database.withTransaction {
            val routineEntity = RoutineEntity(
                name = dto.name.ifBlank { "Imported Routine" },
                notes = dto.notes
            )
            val routineId = routineDao.insertRoutine(routineEntity)
            val existingExercises = exerciseDao.getAllExercisesSync()
            val existingByName = existingExercises.associate {
                canonicalizeExerciseName(it.name) to it.id
            }.toMutableMap()

            val routineExercises = dto.exercises.mapIndexed { index, exDto ->
                val exerciseId = resolveShareExerciseId(
                    existingByName = existingByName,
                    exerciseName = exDto.name,
                    muscleGroup = exDto.muscleGroup,
                    category = exDto.category
                )
                val plans = if (exDto.sets.isNotEmpty()) {
                    sanitizeRoutineSetPlans(exDto.sets)
                } else {
                    sanitizeRoutineSetPlans(
                        null,
                        fallbackSets = exDto.targetSets,
                        fallbackWeight = exDto.defaultWeightKg,
                        fallbackReps = exDto.defaultReps
                    )
                }
                RoutineExerciseEntity(
                    routineId = routineId,
                    exerciseId = exerciseId,
                    orderIndex = index,
                    targetSets = plans.size,
                    defaultWeightKg = plans.firstOrNull()?.weightKg ?: 0.0,
                    defaultReps = plans.firstOrNull()?.reps ?: 10,
                    setPlansJson = serializeSetPlans(plans)
                )
            }
            routineDao.insertRoutineExercises(routineExercises)
            routineId
        }
    }

    private suspend fun resolveShareExerciseId(
        existingByName: MutableMap<String, Long>,
        exerciseName: String,
        muscleGroup: String,
        category: String
    ): Long {
        val exerciseKey = canonicalizeExerciseName(exerciseName)
        existingByName[exerciseKey]?.let { return it }

        val group = try {
            MuscleGroup.valueOf(muscleGroup.uppercase())
        } catch (_: Exception) {
            MuscleGroup.OTHER
        }
        val cat = if (category.equals("CARDIO", ignoreCase = true)) {
            classifyLegacyCardioCategory(exerciseName)
        } else {
            ExerciseCategory.fromString(category)
        }
        val classifiedMechanics = BiomechanicalClassifier.classifyMechanics(
            name = exerciseName,
            category = cat,
            muscleGroup = group
        )
        val newEntity = ExerciseEntity(
            name = exerciseName.trim(),
            muscleGroup = group.name,
            category = cat.name,
            modality = classifyExerciseModality(exerciseName).name,
            isCustom = true,
            mechanics = classifiedMechanics.name,
            force = BiomechanicalClassifier.classifyForce(
                name = exerciseName,
                category = cat,
                muscleGroup = group
            ).name,
            level = BiomechanicalClassifier.classifyDifficulty(
                name = exerciseName,
                category = cat,
                muscleGroup = group,
                mechanics = classifiedMechanics
            ).name
        )
        val newId = exerciseDao.insertExercise(newEntity)
        existingByName[exerciseKey] = newId
        return newId
    }

    suspend fun syncRoutineWithWorkoutValues(routineId: Long, workout: Workout) = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext
        val existingExercises = routineDao.getExercisesForRoutine(routineId)
        val exerciseMap = existingExercises.associateBy { it.exerciseId }

        val updatedRoutineExercises = mutableListOf<RoutineExerciseEntity>()
        workout.exercises.forEachIndexed { index, we ->
            val existingRe = exerciseMap[we.exercise.id]
            val completedSets = we.sets.filter { it.isCompleted && it.setType != SetType.WARMUP }
            val sourceSets = completedSets.ifEmpty {
                we.sets.filter { it.setType != SetType.WARMUP }
            }.ifEmpty { we.sets }

            val plans = if (sourceSets.isNotEmpty()) {
                sourceSets.map { s ->
                    RoutineSetPlan(
                        weightKg = sanitizeRoutineDefaultWeight(s.weightKg),
                        reps = sanitizeRoutineDefaultReps(s.reps),
                        setType = s.setType
                    )
                }
            } else {
                parseSetPlansJson(
                    existingRe?.setPlansJson,
                    existingRe?.targetSets ?: 3,
                    existingRe?.defaultWeightKg ?: 0.0,
                    existingRe?.defaultReps ?: 10
                )
            }

            updatedRoutineExercises.add(
                RoutineExerciseEntity(
                    routineId = routineId,
                    exerciseId = we.exercise.id,
                    orderIndex = index,
                    targetSets = plans.size,
                    defaultWeightKg = plans.firstOrNull()?.weightKg ?: 0.0,
                    defaultReps = plans.firstOrNull()?.reps ?: 10,
                    setPlansJson = serializeSetPlans(plans)
                )
            )
        }

        database.withTransaction {
            routineDao.deleteExercisesForRoutine(routineId)
            routineDao.insertRoutineExercises(updatedRoutineExercises)
        }
    }

    suspend fun deleteRoutine(routineId: Long) = withContext(Dispatchers.IO) {
        routineDao.deleteRoutine(routineId)
    }
}

internal fun parseSetPlansJson(
    jsonStr: String?,
    fallbackSets: Int,
    fallbackWeight: Double,
    fallbackReps: Int
): List<RoutineSetPlan> = GymRepository.parseSetPlansJson(
    jsonStr = jsonStr,
    fallbackSets = fallbackSets,
    fallbackWeight = fallbackWeight,
    fallbackReps = fallbackReps
)

