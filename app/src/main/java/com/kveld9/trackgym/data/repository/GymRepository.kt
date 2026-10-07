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
import com.kveld9.trackgym.domain.calculator.PersonalRecordDetector
import com.kveld9.trackgym.domain.calculator.WorkoutComparisonEngine
import com.kveld9.trackgym.domain.model.DefaultExercises
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.data.local.entity.RoutineEntity
import com.kveld9.trackgym.data.local.entity.RoutineExerciseEntity
import com.kveld9.trackgym.data.local.entity.RoutineFolderEntity
import com.kveld9.trackgym.domain.calculator.PeriodizedRoutineEngine
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
import kotlinx.coroutines.withContext

class GymRepository(private val database: GymDatabase) {

    private val exerciseDao = database.exerciseDao()
    private val workoutDao = database.workoutDao()
    private val prDao = database.personalRecordDao()
    private val routineDao = database.routineDao()

    suspend fun ensureDefaultExercisesSeeded() = withContext(Dispatchers.IO) {
        if (exerciseDao.countExercises() == 0) {
            val entities = DefaultExercises.list.map { ExerciseEntity.fromDomain(it) }
            exerciseDao.insertAll(entities)
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
        notes: String = ""
    ): Long = withContext(Dispatchers.IO) {
        val entity = ExerciseEntity(
            name = name.trim(),
            muscleGroup = muscleGroup.name,
            category = category.name,
            notes = notes.trim(),
            isCustom = true
        )
        exerciseDao.insertExercise(entity)
    }

    suspend fun deleteExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        exerciseDao.deleteExercise(ExerciseEntity.fromDomain(exercise))
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
        val prevWe = workoutDao.getLastCompletedWorkoutExercise(exerciseId, workoutId)
        val initialWeight = if (prevWe != null) {
            val prevSets = workoutDao.getWorkoutSets(prevWe.id)
            prevSets.firstOrNull()?.weightKg ?: 0.0
        } else 0.0
        val initialReps = if (prevWe != null) {
            val prevSets = workoutDao.getWorkoutSets(prevWe.id)
            prevSets.firstOrNull()?.reps ?: 0
        } else 0

        val initialSet = WorkoutSetEntity(
            workoutExerciseId = weId,
            setNumber = 1,
            setType = SetType.NORMAL.name,
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
            val initial = WorkoutSetEntity(
                workoutExerciseId = workoutExerciseId,
                setNumber = 1,
                setType = SetType.NORMAL.name,
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

        if (completedEntity.routineId != null && !detachRoutine) {
            val routine = routineDao.getRoutineById(completedEntity.routineId)
            if (routine != null && routine.isPeriodized) {
                val cycle = PeriodizedRoutineEngine.decode(routine.periodizedCycleData)
                if (cycle != null && cycle.autoAdvanceOnCompletion) {
                    val advanced = PeriodizedRoutineEngine.advanceCycle(cycle, autoRepeat = true)
                    routineDao.updateRoutineCycleData(routine.id, PeriodizedRoutineEngine.encode(advanced))
                }
            }
        }

        compareWorkoutWithPrevious(workoutId, weightUnit, userBodyWeightKg)
    }

    fun getCompletedWorkouts(): Flow<List<Workout>> {
        return workoutDao.getCompletedWorkouts().map { list ->
            list.map { buildFullWorkout(it) }
        }
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

    suspend fun updateWorkoutDuration(workoutId: Long, durationSeconds: Long) = withContext(Dispatchers.IO) {
        val entity = workoutDao.getWorkoutById(workoutId) ?: return@withContext
        workoutDao.updateWorkout(entity.copy(durationSeconds = durationSeconds))
    }

    suspend fun getPreviousSetsForExercise(exerciseId: Long, currentWorkoutId: Long): List<WorkoutSet> = withContext(Dispatchers.IO) {
        val prevWe = workoutDao.getLastCompletedWorkoutExercise(exerciseId, currentWorkoutId) ?: return@withContext emptyList()
        workoutDao.getWorkoutSets(prevWe.id).map { it.toDomain() }
    }

    suspend fun compareWorkoutWithPrevious(
        workoutId: Long,
        weightUnit: com.kveld9.trackgym.domain.model.WeightUnit = com.kveld9.trackgym.domain.model.WeightUnit.KG,
        userBodyWeightKg: Double = 0.0
    ): WorkoutComparison? = withContext(Dispatchers.IO) {
        val currentWorkout = getFullWorkout(workoutId) ?: return@withContext null
        val exerciseComparisons = mutableListOf<ExerciseComparison>()
        val totalPrs = mutableListOf<PersonalRecord>()

        for (workoutExercise in currentWorkout.exercises) {
            val exerciseId = workoutExercise.exercise.id
            val prevWe = workoutDao.getLastCompletedWorkoutExercise(exerciseId, workoutId)
            val prevWorkout = if (prevWe != null) workoutDao.getWorkoutById(prevWe.workoutId) else null
            val prevSets = if (prevWe != null) workoutDao.getWorkoutSets(prevWe.id).map { it.toDomain() } else emptyList()

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
            totalPrs.addAll(recordsForExercise)

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

        val totalVolumeDelta = exerciseComparisons.sumOf { it.totalVolumeDeltaKg }

        WorkoutComparison(
            currentWorkout = currentWorkout,
            previousWorkout = null,
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
                createdAt = it.createdAt
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
                exerciseNameToIdMap[it.name.lowercase().trim()] = it.id
            }

            for (exDto in backup.exercises) {
                val key = exDto.name.lowercase().trim()
                if (!exerciseNameToIdMap.containsKey(key)) {
                    val newEntity = ExerciseEntity(
                        name = exDto.name.trim(),
                        muscleGroup = exDto.muscleGroup,
                        category = exDto.category,
                        notes = exDto.notes,
                        isCustom = exDto.isCustom,
                        createdAt = if (exDto.createdAt > 0) exDto.createdAt else System.currentTimeMillis()
                    )
                    val newId = exerciseDao.insertExercise(newEntity)
                    exerciseNameToIdMap[key] = newId
                    insertedCount++
                } else if (policy == DuplicatePolicy.OVERWRITE_ALL) {
                    val existingId = exerciseNameToIdMap[key]!!
                    val updated = ExerciseEntity(
                        id = existingId,
                        name = exDto.name.trim(),
                        muscleGroup = exDto.muscleGroup,
                        category = exDto.category,
                        notes = exDto.notes,
                        isCustom = exDto.isCustom,
                        createdAt = exDto.createdAt
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
                    val exKey = weDto.exerciseName.lowercase().trim()
                    var exId = exerciseNameToIdMap[exKey]
                    if (exId == null) {
                        val newEx = ExerciseEntity(
                            name = weDto.exerciseName.trim(),
                            muscleGroup = MuscleGroup.OTHER.name,
                            category = ExerciseCategory.BARBELL.name,
                            isCustom = true
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
                val exKey = prDto.exerciseName.lowercase().trim()
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

    suspend fun deleteFolder(id: Long) = withContext(Dispatchers.IO) {
        routineDao.deleteFolder(id)
    }

    fun getAllRoutines(): Flow<List<Routine>> {
        return combine(
            routineDao.getAllRoutines(),
            routineDao.getAllFolders(),
            exerciseDao.getAllExercises()
        ) { routines, folders, exercises ->
            val folderMap = folders.associateBy { it.id }
            val exerciseMap = exercises.associateBy { it.id }

            routines.map { entity ->
                val exercisesForRoutine = routineDao.getExercisesForRoutine(entity.id)
                val mappedExercises = exercisesForRoutine.mapNotNull { re ->
                    val ex = exerciseMap[re.exerciseId]?.toDomain() ?: return@mapNotNull null
                    RoutineExercise(
                        id = re.id,
                        exercise = ex,
                        orderIndex = re.orderIndex,
                        targetSets = re.targetSets,
                        defaultWeightKg = re.defaultWeightKg,
                        defaultReps = re.defaultReps
                    )
                }
                val cycle = if (entity.isPeriodized) {
                    PeriodizedRoutineEngine.decode(entity.periodizedCycleData)
                        ?: PeriodizedRoutineEngine.createDefaultCycle(PeriodizedRoutineEngine.DEFAULT_CYCLE_WEEKS)
                } else null
                Routine(
                    id = entity.id,
                    folderId = entity.folderId,
                    folderName = folderMap[entity.folderId]?.name,
                    name = entity.name,
                    notes = entity.notes,
                    exercises = mappedExercises,
                    orderIndex = entity.orderIndex,
                    isArchived = entity.isArchived,
                    isPeriodized = entity.isPeriodized,
                    periodizedCycle = cycle,
                    createdAt = entity.createdAt
                )
            }
        }.flowOn(Dispatchers.IO)
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
            val workingSets = we.sets.filter { it.setType != SetType.WARMUP }
            val primarySet = workingSets.firstOrNull { it.isCompleted }
                ?: workingSets.firstOrNull()
                ?: we.sets.firstOrNull()
            val targetCount = if (workingSets.isNotEmpty()) workingSets.size else we.sets.size.coerceAtLeast(1)
            RoutineExerciseEntity(
                routineId = routineId,
                exerciseId = we.exercise.id,
                orderIndex = index,
                targetSets = targetCount.coerceAtLeast(1),
                defaultWeightKg = primarySet?.weightKg ?: 0.0,
                defaultReps = primarySet?.reps ?: 10
            )
        }
        routineDao.insertRoutineExercises(routineExercises)
        routineId
    }

    suspend fun startWorkoutFromRoutine(routineId: Long): Long = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext -1L
        val active = getActiveWorkout()
        if (active != null) return@withContext active.id

        val cycle = if (routine.isPeriodized) PeriodizedRoutineEngine.decode(routine.periodizedCycleData) else null
        val currentWeekConfig = cycle?.getCurrentWeekConfig()

        val workoutTitle = if (currentWeekConfig != null) {
            "${routine.name} - W${currentWeekConfig.weekNumber} (${currentWeekConfig.phase.name})"
        } else {
            routine.name
        }

        val workoutEntity = WorkoutEntity(
            title = workoutTitle,
            startedAt = System.currentTimeMillis(),
            notes = routine.notes,
            isCompleted = false,
            routineId = routineId
        )
        val workoutId = workoutDao.insertWorkout(workoutEntity)
        val routineExercises = routineDao.getExercisesForRoutine(routineId)

        for (re in routineExercises) {
            val weEntity = WorkoutExerciseEntity(
                workoutId = workoutId,
                exerciseId = re.exerciseId,
                orderIndex = re.orderIndex
            )
            val weId = workoutDao.insertWorkoutExercise(weEntity)
            val effectiveTargetSets = if (currentWeekConfig != null) {
                PeriodizedRoutineEngine.calculatePrescribedSets(re.targetSets, currentWeekConfig)
            } else {
                re.targetSets
            }
            val effectiveWeight = if (currentWeekConfig != null) {
                PeriodizedRoutineEngine.calculatePrescribedWeight(re.defaultWeightKg, currentWeekConfig)
            } else {
                re.defaultWeightKg
            }

            for (setIndex in 1..effectiveTargetSets) {
                val setEntity = WorkoutSetEntity(
                    workoutExerciseId = weId,
                    setNumber = setIndex,
                    weightKg = effectiveWeight,
                    reps = re.defaultReps,
                    isCompleted = false,
                    setType = SetType.NORMAL.name
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
                defaultReps = re.defaultReps
            )
        }
        routineDao.insertRoutineExercises(duplicatedExercises)
        newRoutineId
    }

    suspend fun updateRoutinePeriodization(routineId: Long, isPeriodized: Boolean, cycle: PeriodizedCycle?) = withContext(Dispatchers.IO) {
        val encoded = cycle?.let { PeriodizedRoutineEngine.encode(it) }
        routineDao.updateRoutinePeriodization(routineId, isPeriodized, encoded)
    }

    suspend fun advanceRoutineCycleWeek(routineId: Long) = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext
        val cycle = PeriodizedRoutineEngine.decode(routine.periodizedCycleData)
            ?: PeriodizedRoutineEngine.createDefaultCycle()
        val next = PeriodizedRoutineEngine.advanceCycle(cycle)
        routineDao.updateRoutineCycleData(routineId, PeriodizedRoutineEngine.encode(next))
    }

    suspend fun previousRoutineCycleWeek(routineId: Long) = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext
        val cycle = PeriodizedRoutineEngine.decode(routine.periodizedCycleData)
            ?: PeriodizedRoutineEngine.createDefaultCycle()
        val prev = PeriodizedRoutineEngine.previousCycle(cycle)
        routineDao.updateRoutineCycleData(routineId, PeriodizedRoutineEngine.encode(prev))
    }

    suspend fun setRoutineCycleWeek(routineId: Long, weekNumber: Int) = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext
        val cycle = PeriodizedRoutineEngine.decode(routine.periodizedCycleData)
            ?: PeriodizedRoutineEngine.createDefaultCycle()
        val updated = PeriodizedRoutineEngine.setCycleWeek(cycle, weekNumber)
        routineDao.updateRoutineCycleData(routineId, PeriodizedRoutineEngine.encode(updated))
    }

    suspend fun setRoutineArchived(routineId: Long, isArchived: Boolean) = withContext(Dispatchers.IO) {
        routineDao.updateRoutineArchived(routineId, isArchived)
    }

    suspend fun importRoutineFromShareDto(dto: RoutineShareDto): Long = withContext(Dispatchers.IO) {
        val routineEntity = RoutineEntity(
            name = dto.name.ifBlank { "Imported Routine" },
            notes = dto.notes
        )
        val routineId = routineDao.insertRoutine(routineEntity)
        val existingExercises = exerciseDao.getAllExercisesSync()
        val existingByName = existingExercises.associateBy { it.name.lowercase().trim() }

        val routineExercises = dto.exercises.mapIndexed { index, exDto ->
            var exerciseId = existingByName[exDto.name.lowercase().trim()]?.id
            if (exerciseId == null) {
                val group = try {
                    MuscleGroup.valueOf(exDto.muscleGroup.uppercase())
                } catch (_: Exception) {
                    MuscleGroup.OTHER
                }
                val cat = try {
                    ExerciseCategory.valueOf(exDto.category.uppercase())
                } catch (_: Exception) {
                    ExerciseCategory.OTHER
                }
                val newEntity = ExerciseEntity(
                    name = exDto.name.trim(),
                    muscleGroup = group.name,
                    category = cat.name,
                    isCustom = true
                )
                exerciseId = exerciseDao.insertExercise(newEntity)
            }
            RoutineExerciseEntity(
                routineId = routineId,
                exerciseId = exerciseId,
                orderIndex = index,
                targetSets = exDto.targetSets.coerceAtLeast(1),
                defaultWeightKg = exDto.defaultWeightKg,
                defaultReps = exDto.defaultReps
            )
        }
        routineDao.insertRoutineExercises(routineExercises)
        routineId
    }

    suspend fun syncRoutineWithWorkoutValues(routineId: Long, workout: Workout) = withContext(Dispatchers.IO) {
        val routine = routineDao.getRoutineById(routineId) ?: return@withContext
        val existingExercises = routineDao.getExercisesForRoutine(routineId)
        val exerciseMap = existingExercises.associateBy { it.exerciseId }

        val updatedRoutineExercises = mutableListOf<RoutineExerciseEntity>()
        workout.exercises.forEachIndexed { index, we ->
            val existingRe = exerciseMap[we.exercise.id]
            val completedSets = we.sets.filter { it.isCompleted && it.setType != SetType.WARMUP }
            val effectiveSet = completedSets.lastOrNull() ?: we.sets.lastOrNull()
            val newDefaultWeight = effectiveSet?.weightKg ?: existingRe?.defaultWeightKg ?: 0.0
            val newDefaultReps = effectiveSet?.reps ?: existingRe?.defaultReps ?: 10
            val targetCount = if (completedSets.isNotEmpty()) completedSets.size else (existingRe?.targetSets ?: we.sets.size.coerceAtLeast(1))

            updatedRoutineExercises.add(
                RoutineExerciseEntity(
                    routineId = routineId,
                    exerciseId = we.exercise.id,
                    orderIndex = index,
                    targetSets = targetCount.coerceAtLeast(1),
                    defaultWeightKg = newDefaultWeight,
                    defaultReps = newDefaultReps
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
