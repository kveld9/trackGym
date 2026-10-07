package com.kveld9.trackgym.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kveld9.trackgym.data.repository.GymRepository
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseTranslationRegistry
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.data.ThemePreferences
import com.kveld9.trackgym.domain.calculator.MuscleHeatmapEngine
import com.kveld9.trackgym.domain.calculator.TrainingConsistencyEngine
import com.kveld9.trackgym.domain.calculator.TrainingConsistencyStats
import com.kveld9.trackgym.domain.model.MuscleHeatmapState
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineFolder
import com.kveld9.trackgym.domain.model.WeightUnit
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GymViewModel(
    private val repository: GymRepository,
    private val themePreferences: ThemePreferences? = null
) : ViewModel() {

    val weightUnit: StateFlow<WeightUnit> = (themePreferences?.themeSettings?.map {
        WeightUnit.fromString(it.weightUnit)
    } ?: flowOf(WeightUnit.KG))
        .stateIn(viewModelScope, SharingStarted.Eagerly, WeightUnit.KG)

    val exerciseLanguage: StateFlow<String> = (themePreferences?.themeSettings?.map {
        it.exerciseLanguage
    } ?: flowOf(ThemePreferences.EXERCISE_LANG_SYSTEM))
        .stateIn(viewModelScope, SharingStarted.Eagerly, ThemePreferences.EXERCISE_LANG_SYSTEM)

    val keepExerciseNamesInEnglish: StateFlow<Boolean> = exerciseLanguage.map {
        it == ThemePreferences.EXERCISE_LANG_ENGLISH
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val ormFormula: StateFlow<com.kveld9.trackgym.domain.calculator.OneRepMaxFormula> = (themePreferences?.themeSettings?.map {
        runCatching { com.kveld9.trackgym.domain.calculator.OneRepMaxFormula.valueOf(it.ormFormula) }
            .getOrDefault(com.kveld9.trackgym.domain.calculator.OneRepMaxFormula.EPLEY)
    } ?: flowOf(com.kveld9.trackgym.domain.calculator.OneRepMaxFormula.EPLEY))
        .stateIn(viewModelScope, SharingStarted.Eagerly, com.kveld9.trackgym.domain.calculator.OneRepMaxFormula.EPLEY)

    val timerSound: StateFlow<String> = (themePreferences?.themeSettings?.map {
        it.timerSound
    } ?: flowOf("DIGITAL_BEEP"))
        .stateIn(viewModelScope, SharingStarted.Eagerly, "DIGITAL_BEEP")

    val timerSoundCountdown: StateFlow<Boolean> = (themePreferences?.themeSettings?.map {
        it.timerSoundCountdown
    } ?: flowOf(true))
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    val soundFeedbackOnComplete: StateFlow<Boolean> = (themePreferences?.themeSettings?.map {
        it.soundFeedbackOnComplete
    } ?: flowOf(true))
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    val userBodyWeight: StateFlow<Double> = (themePreferences?.themeSettings?.map {
        it.userBodyWeightKg
    } ?: flowOf(75.0))
        .stateIn(viewModelScope, SharingStarted.Eagerly, 75.0)

    val routineUpdateMode: StateFlow<com.kveld9.trackgym.domain.model.RoutineUpdateMode> = (themePreferences?.themeSettings?.map {
        com.kveld9.trackgym.domain.model.RoutineUpdateMode.fromString(it.routineUpdateMode)
    } ?: flowOf(com.kveld9.trackgym.domain.model.RoutineUpdateMode.ASK))
        .stateIn(viewModelScope, SharingStarted.Eagerly, com.kveld9.trackgym.domain.model.RoutineUpdateMode.ASK)

    val distanceUnit: StateFlow<com.kveld9.trackgym.domain.model.DistanceUnit> = (themePreferences?.themeSettings?.map {
        com.kveld9.trackgym.domain.model.DistanceUnit.fromString(it.distanceUnit)
    } ?: flowOf(com.kveld9.trackgym.domain.model.DistanceUnit.KM))
        .stateIn(viewModelScope, SharingStarted.Eagerly, com.kveld9.trackgym.domain.model.DistanceUnit.KM)

    val showInlinePlates: StateFlow<Boolean> = (themePreferences?.themeSettings?.map {
        it.showInlinePlates
    } ?: flowOf(true))
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    private val _activeWorkout = MutableStateFlow<Workout?>(null)
    val activeWorkout: StateFlow<Workout?> = _activeWorkout.asStateFlow()

    private val _previousSetsMap = MutableStateFlow<Map<Long, List<WorkoutSet>>>(emptyMap())
    val previousSetsMap: StateFlow<Map<Long, List<WorkoutSet>>> = _previousSetsMap.asStateFlow()

    private val _timerSeconds = MutableStateFlow(0L)
    val timerSeconds: StateFlow<Long> = _timerSeconds.asStateFlow()
    private var timerJob: Job? = null

    // Rest Timer
    private val _restTimerRemainingSeconds = MutableStateFlow<Int?>(null)
    val restTimerRemainingSeconds: StateFlow<Int?> = _restTimerRemainingSeconds.asStateFlow()

    private val _restTimerTotalSeconds = MutableStateFlow(90)
    val restTimerTotalSeconds: StateFlow<Int> = _restTimerTotalSeconds.asStateFlow()

    private val _restTimerIsRunning = MutableStateFlow(false)
    val restTimerIsRunning: StateFlow<Boolean> = _restTimerIsRunning.asStateFlow()

    private val _restTimerFinishedEvent = MutableSharedFlow<Unit>()
    val restTimerFinishedEvent: SharedFlow<Unit> = _restTimerFinishedEvent.asSharedFlow()

    private val _restTimerWarningEvent = MutableSharedFlow<Int>()
    val restTimerWarningEvent: SharedFlow<Int> = _restTimerWarningEvent.asSharedFlow()

    private val _supersetFocusEvent = MutableSharedFlow<Long>(extraBufferCapacity = 1)
    val supersetFocusEvent: SharedFlow<Long> = _supersetFocusEvent.asSharedFlow()

    private var restTimerJob: Job? = null

    val completedWorkouts: StateFlow<List<Workout>> = repository.getCompletedWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val consistencyStats: StateFlow<TrainingConsistencyStats> = completedWorkouts.map { workouts ->
        TrainingConsistencyEngine.calculateConsistency(workouts)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        TrainingConsistencyStats(0, 0, 0.0, emptyList())
    )

    val weeklyHeatmapState: StateFlow<MuscleHeatmapState> = completedWorkouts.map { workouts ->
        MuscleHeatmapEngine.calculateWeekly(workouts)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MuscleHeatmapState()
    )

    val routines: StateFlow<List<Routine>> = repository.getAllRoutines()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val folders: StateFlow<List<RoutineFolder>> = repository.getAllFolders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _rawExercises = repository.getAllExercises()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedMuscleFilter = MutableStateFlow<MuscleGroup?>(null)
    val selectedMuscleFilter: StateFlow<MuscleGroup?> = _selectedMuscleFilter.asStateFlow()

    enum class ExerciseOriginFilter {
        ALL,
        PRELOADED,
        CUSTOM
    }

    private val _selectedOriginFilter = MutableStateFlow(ExerciseOriginFilter.ALL)
    val selectedOriginFilter: StateFlow<ExerciseOriginFilter> = _selectedOriginFilter.asStateFlow()

    private val _selectedEquipmentFilter = MutableStateFlow<ExerciseCategory?>(null)
    val selectedEquipmentFilter: StateFlow<ExerciseCategory?> = _selectedEquipmentFilter.asStateFlow()

    private val _selectedMechanicsFilter = MutableStateFlow<MechanicsType?>(null)
    val selectedMechanicsFilter: StateFlow<MechanicsType?> = _selectedMechanicsFilter.asStateFlow()

    val filteredExercises: StateFlow<List<Exercise>> = combine(
        listOf(
            _rawExercises,
            _searchQuery,
            _selectedMuscleFilter,
            _selectedOriginFilter,
            _selectedEquipmentFilter,
            _selectedMechanicsFilter,
            keepExerciseNamesInEnglish
        )
    ) { array ->
        @Suppress("UNCHECKED_CAST")
        val exercises = array[0] as List<Exercise>
        val query = array[1] as String
        val muscleFilter = array[2] as MuscleGroup?
        val originFilter = array[3] as ExerciseOriginFilter
        val equipmentFilter = array[4] as ExerciseCategory?
        val mechanicsFilter = array[5] as MechanicsType?
        val keepEnglish = array[6] as Boolean

        exercises.filter { ex ->
            val matchesQuery = query.isBlank() ||
                ex.name.contains(query, ignoreCase = true) ||
                (!keepEnglish && ExerciseTranslationRegistry.matchesQuery(ex.name, query))
            val matchesMuscle = muscleFilter == null || ex.muscleGroup == muscleFilter
            val matchesOrigin = when (originFilter) {
                ExerciseOriginFilter.ALL -> true
                ExerciseOriginFilter.PRELOADED -> !ex.isCustom
                ExerciseOriginFilter.CUSTOM -> ex.isCustom
            }
            val matchesEquipment = equipmentFilter == null || ex.category == equipmentFilter
            val matchesMechanics = mechanicsFilter == null || ex.mechanics == mechanicsFilter

            matchesQuery && matchesMuscle && matchesOrigin && matchesEquipment && matchesMechanics
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allRecords: StateFlow<List<PersonalRecord>> = repository.getAllRecords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _lastFinishedComparison = MutableStateFlow<WorkoutComparison?>(null)
    val lastFinishedComparison: StateFlow<WorkoutComparison?> = _lastFinishedComparison.asStateFlow()

    private val _selectedDetailComparison = MutableStateFlow<WorkoutComparison?>(null)
    val selectedDetailComparison: StateFlow<WorkoutComparison?> = _selectedDetailComparison.asStateFlow()

    private val _recentlyUnlockedPr = MutableStateFlow<PersonalRecord?>(null)
    val recentlyUnlockedPr: StateFlow<PersonalRecord?> = _recentlyUnlockedPr.asStateFlow()

    init {
        viewModelScope.launch {
            repository.ensureDefaultExercisesSeeded()
            loadActiveWorkout()
        }
    }

    private suspend fun setActiveWorkout(workout: Workout?) {
        _activeWorkout.value = workout
        refreshPreviousSets(workout)
    }

    private suspend fun refreshPreviousSets(workout: Workout?) {
        if (workout == null) {
            _previousSetsMap.value = emptyMap()
            return
        }
        val currentMap = _previousSetsMap.value.toMutableMap()
        val workoutExerciseIds = workout.exercises.map { it.exercise.id }.toSet()
        currentMap.keys.retainAll(workoutExerciseIds)
        for (we in workout.exercises) {
            if (!currentMap.containsKey(we.exercise.id)) {
                val prev = repository.getPreviousSetsForExercise(we.exercise.id, workout.id)
                currentMap[we.exercise.id] = prev
            }
        }
        _previousSetsMap.value = currentMap
    }

    private suspend fun loadActiveWorkout() {
        val active = repository.getActiveWorkout()
        setActiveWorkout(active)
        if (active != null) {
            val elapsedFromStart = (System.currentTimeMillis() - active.startedAt) / 1000
            val restoredDuration = elapsedFromStart.coerceAtLeast(active.durationSeconds).coerceAtLeast(0)
            _timerSeconds.value = restoredDuration
            startTimer()
        } else {
            stopTimer()
            stopRestTimer()
            _timerSeconds.value = 0
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var tickCount = 0
            while (true) {
                delay(1000)
                _timerSeconds.value += 1
                tickCount++
                if (tickCount >= 10) {
                    tickCount = 0
                    _activeWorkout.value?.id?.let { wid ->
                        repository.updateWorkoutDuration(wid, _timerSeconds.value)
                    }
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun startWorkout(title: String = "Entrenamiento") {
        viewModelScope.launch {
            val workout = repository.startNewWorkout(title)
            setActiveWorkout(workout)
            _timerSeconds.value = 0
            startTimer()
        }
    }

    fun addExerciseToActiveWorkout(exerciseId: Long) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.addExerciseToWorkout(current.id, exerciseId)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun removeExerciseFromActiveWorkout(workoutExerciseId: Long) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.removeExerciseFromWorkout(workoutExerciseId)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun moveExercise(fromIndex: Int, toIndex: Int) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.moveWorkoutExercise(current.id, fromIndex, toIndex)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun swapExercise(workoutExerciseId: Long, newExerciseId: Long, resetSets: Boolean) {
        viewModelScope.launch {
            repository.swapExerciseInWorkout(workoutExerciseId, newExerciseId, resetSets)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun addSet(workoutExerciseId: Long, weightKg: Double, reps: Int) {
        viewModelScope.launch {
            repository.addSetToExercise(workoutExerciseId, weightKg, reps)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun duplicateLastSet(workoutExerciseId: Long) {
        val current = _activeWorkout.value ?: return
        val we = current.exercises.firstOrNull { it.id == workoutExerciseId } ?: return
        val lastSet = we.sets.lastOrNull()
        val weightKg = lastSet?.weightKg ?: 0.0
        val reps = lastSet?.reps ?: 0
        val setType = lastSet?.setType ?: SetType.NORMAL
        viewModelScope.launch {
            repository.addSetToExercise(workoutExerciseId, weightKg, reps, setType)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun addWarmupSets(
        workoutExerciseId: Long,
        targetWeightKg: Double,
        customProtocol: List<com.kveld9.trackgym.domain.calculator.WarmupSetConfig>? = null
    ) {
        viewModelScope.launch {
            val we = _activeWorkout.value?.exercises?.find { it.id == workoutExerciseId }
            val protocol = customProtocol
                ?: com.kveld9.trackgym.domain.calculator.WarmupGenerator.decodeProtocol(we?.exercise?.warmupRampProtocol)
                ?: com.kveld9.trackgym.domain.calculator.WarmupGenerator.DEFAULT_WARMUP_PROTOCOL

            val warmups = com.kveld9.trackgym.domain.calculator.WarmupGenerator.generateWarmupSets(
                targetWeightKg = targetWeightKg,
                workoutExerciseId = workoutExerciseId,
                protocol = protocol
            )
            if (warmups.isNotEmpty()) {
                repository.insertWarmupSets(workoutExerciseId, warmups)
                setActiveWorkout(repository.getActiveWorkout())
            }
        }
    }

    fun updateSet(set: WorkoutSet) {
        viewModelScope.launch {
            repository.updateSet(set)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun updateExerciseNotes(exerciseId: Long, notes: String) {
        viewModelScope.launch {
            repository.updateExerciseNotes(exerciseId, notes)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun updateWorkoutNotes(notes: String) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.updateWorkoutNotes(current.id, notes)
            _activeWorkout.value = _activeWorkout.value?.copy(notes = notes)
        }
    }

    fun toggleCompleteSet(
        set: WorkoutSet,
        workoutId: Long,
        exerciseId: Long,
        currentWeight: Double? = null,
        currentReps: Int? = null
    ) {
        viewModelScope.launch {
            val targetSet = if (currentWeight != null || currentReps != null) {
                set.copy(
                    weightKg = currentWeight ?: set.weightKg,
                    reps = currentReps ?: set.reps
                )
            } else set

            if (targetSet.isCompleted) {
                // Uncomplete
                repository.updateSet(targetSet.copy(isCompleted = false, completedAt = null))
            } else {
                val newPrs = repository.completeSet(targetSet, workoutId, exerciseId, ormFormula.value)
                if (newPrs.isNotEmpty()) {
                    _recentlyUnlockedPr.value = newPrs.first()
                }
                val currentExercises = _activeWorkout.value?.exercises.orEmpty()
                val exerciseCustomRest = currentExercises
                    .firstOrNull { it.exercise.id == exerciseId }
                    ?.exercise?.restDurationSeconds
                val completionStep = resolveSupersetNextStep(
                    exercises = currentExercises,
                    exerciseId = exerciseId,
                    customRest = exerciseCustomRest
                )
                if (completionStep.nextExerciseId != null) {
                    _supersetFocusEvent.tryEmit(completionStep.nextExerciseId)
                }
                triggerAutoRestTimer(completionStep.restSeconds)
            }
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    data class SupersetCompletionResult(
        val restSeconds: Int?,
        val nextExerciseId: Long?
    )

    private fun resolveSupersetNextStep(
        exercises: List<WorkoutExercise>,
        exerciseId: Long,
        customRest: Int?
    ): SupersetCompletionResult {
        val currentWe = exercises.firstOrNull { it.exercise.id == exerciseId }
        val groupId = currentWe?.supersetGroupId ?: return SupersetCompletionResult(customRest, null)
        val groupExercises = exercises.filter { it.supersetGroupId == groupId }
        if (groupExercises.size <= 1) return SupersetCompletionResult(customRest, null)

        val currentIndex = groupExercises.indexOfFirst { it.exercise.id == exerciseId }
        return if (currentIndex in 0 until groupExercises.size - 1) {
            SupersetCompletionResult(15, groupExercises[currentIndex + 1].exercise.id)
        } else {
            SupersetCompletionResult(customRest ?: 120, groupExercises.first().exercise.id)
        }
    }

    fun setExerciseSupersetGroup(workoutExerciseId: Long, supersetGroupId: String?) {
        viewModelScope.launch {
            repository.updateExerciseSupersetGroup(workoutExerciseId, supersetGroupId)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    private fun triggerAutoRestTimer(customRestSeconds: Int? = null) {
        viewModelScope.launch {
            val settings = themePreferences?.themeSettings?.firstOrNull()
            val isAuto = settings?.autoRestTimer ?: true
            val duration = customRestSeconds ?: settings?.defaultRestSeconds ?: 90
            if (isAuto) {
                startRestTimer(duration)
            }
        }
    }

    fun updateExerciseRestDuration(exerciseId: Long, restSeconds: Int?) {
        viewModelScope.launch {
            repository.updateExerciseRestDuration(exerciseId, restSeconds)
            val current = _activeWorkout.value ?: return@launch
            val updatedExercises = current.exercises.map { we ->
                if (we.exercise.id == exerciseId) {
                    we.copy(exercise = we.exercise.copy(restDurationSeconds = restSeconds))
                } else we
            }
            _activeWorkout.value = current.copy(exercises = updatedExercises)
        }
    }

    fun updateExerciseWarmupProtocol(exerciseId: Long, protocol: String?) {
        viewModelScope.launch {
            repository.updateExerciseWarmupProtocol(exerciseId, protocol)
            val current = _activeWorkout.value ?: return@launch
            val updatedExercises = current.exercises.map { we ->
                if (we.exercise.id == exerciseId) {
                    we.copy(exercise = we.exercise.copy(warmupRampProtocol = protocol))
                } else we
            }
            _activeWorkout.value = current.copy(exercises = updatedExercises)
        }
    }

    fun updateExerciseAutoProgressionRule(exerciseId: Long, rule: String?) {
        viewModelScope.launch {
            repository.updateExerciseAutoProgressionRule(exerciseId, rule)
            val current = _activeWorkout.value ?: return@launch
            val updatedExercises = current.exercises.map { we ->
                if (we.exercise.id == exerciseId) {
                    we.copy(exercise = we.exercise.copy(autoProgressionRule = rule))
                } else we
            }
            _activeWorkout.value = current.copy(exercises = updatedExercises)
        }
    }

    suspend fun evaluateAutoProgression(
        exerciseId: Long,
        currentWorkoutId: Long,
        workingWeightKg: Double
    ): com.kveld9.trackgym.domain.calculator.AutoProgressionResult? {
        val exercise = _activeWorkout.value?.exercises?.find { it.exercise.id == exerciseId }?.exercise
            ?: repository.getExerciseById(exerciseId) ?: return null
        val rule = com.kveld9.trackgym.domain.calculator.AutoProgressionEngine.decode(exercise.autoProgressionRule)
            ?: return null
        if (!rule.enabled) return null

        val recentSessions = repository.getRecentSessionsSetsForExercise(exerciseId, currentWorkoutId, 5)
        return com.kveld9.trackgym.domain.calculator.AutoProgressionEngine.evaluate(
            rule = rule,
            recentSessionsSets = recentSessions,
            currentWeightKg = workingWeightKg
        )
    }

    fun startRestTimer(seconds: Int? = null) {
        val duration = seconds ?: _restTimerTotalSeconds.value
        _restTimerTotalSeconds.value = duration
        _restTimerRemainingSeconds.value = duration
        _restTimerIsRunning.value = true
        runRestTimerCountdown()
    }

    private fun runRestTimerCountdown() {
        restTimerJob?.cancel()
        restTimerJob = viewModelScope.launch {
            while (_restTimerIsRunning.value) {
                val remaining = _restTimerRemainingSeconds.value ?: break
                if (remaining <= 0) {
                    _restTimerRemainingSeconds.value = null
                    _restTimerIsRunning.value = false
                    _restTimerFinishedEvent.emit(Unit)
                    break
                }
                delay(1000)
                val current = _restTimerRemainingSeconds.value ?: break
                val newRemaining = (current - 1).coerceAtLeast(0)
                _restTimerRemainingSeconds.value = newRemaining
                if (newRemaining in 1..3 && timerSoundCountdown.value) {
                    _restTimerWarningEvent.emit(newRemaining)
                }
            }
        }
    }

    fun pauseRestTimer() {
        _restTimerIsRunning.value = false
        restTimerJob?.cancel()
    }

    fun resumeRestTimer() {
        if ((_restTimerRemainingSeconds.value ?: 0) > 0) {
            _restTimerIsRunning.value = true
            runRestTimerCountdown()
        }
    }

    fun addRestSeconds(delta: Int) {
        val current = _restTimerRemainingSeconds.value ?: return
        val newTime = (current + delta).coerceAtLeast(0)
        _restTimerRemainingSeconds.value = newTime
        if (newTime > _restTimerTotalSeconds.value) {
            _restTimerTotalSeconds.value = newTime
        }
    }

    fun stopRestTimer() {
        restTimerJob?.cancel()
        _restTimerIsRunning.value = false
        _restTimerRemainingSeconds.value = null
    }

    fun completeFirstPendingSet() {
        val active = _activeWorkout.value ?: return
        for (we in active.exercises) {
            val pending = we.sets.firstOrNull { !it.isCompleted }
            if (pending != null) {
                toggleCompleteSet(
                    set = pending,
                    workoutId = active.id,
                    exerciseId = we.exercise.id
                )
                break
            }
        }
    }

    fun startWorkoutFromRoutine(routineId: Long) {
        viewModelScope.launch {
            val workoutId = repository.startWorkoutFromRoutine(routineId)
            if (workoutId > 0) {
                setActiveWorkout(repository.getActiveWorkout())
                startTimer()
            }
        }
    }

    fun saveActiveWorkoutAsRoutine(name: String, folderId: Long? = null, onSaved: (() -> Unit)? = null) {
        val active = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.saveWorkoutAsRoutine(active.id, name, folderId)
            onSaved?.invoke()
        }
    }

    fun saveCompletedWorkoutAsRoutine(workoutId: Long, name: String, folderId: Long? = null, onSaved: (() -> Unit)? = null) {
        viewModelScope.launch {
            repository.saveWorkoutAsRoutine(workoutId, name, folderId)
            onSaved?.invoke()
        }
    }

    fun createFolder(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.createFolder(name)
        }
    }

    fun deleteFolder(folderId: Long) {
        viewModelScope.launch {
            repository.deleteFolder(folderId)
        }
    }

    fun deleteRoutine(routineId: Long) {
        viewModelScope.launch {
            repository.deleteRoutine(routineId)
        }
    }

    fun duplicateRoutine(routineId: Long, copySuffix: String = "(Copy)") {
        viewModelScope.launch {
            repository.duplicateRoutine(routineId, copySuffix)
        }
    }

    fun generateDeloadRoutine(
        routineId: Long,
        loadReductionPct: Double = com.kveld9.trackgym.domain.calculator.DeloadGenerator.DEFAULT_LOAD_REDUCTION_PCT,
        volumeReductionPct: Double = com.kveld9.trackgym.domain.calculator.DeloadGenerator.DEFAULT_VOLUME_REDUCTION_PCT,
        copySuffix: String = "(Deload)",
        onCreated: ((Long) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val newId = repository.generateDeloadRoutine(routineId, loadReductionPct, volumeReductionPct, copySuffix)
            onCreated?.invoke(newId)
        }
    }

    fun toggleRoutineArchived(routineId: Long, isArchived: Boolean) {
        viewModelScope.launch {
            repository.setRoutineArchived(routineId, isArchived)
        }
    }

    fun updateRoutinePeriodization(routineId: Long, isPeriodized: Boolean, cycle: com.kveld9.trackgym.domain.model.PeriodizedCycle?) {
        viewModelScope.launch {
            repository.updateRoutinePeriodization(routineId, isPeriodized, cycle)
        }
    }

    fun advanceRoutineCycleWeek(routineId: Long) {
        viewModelScope.launch {
            repository.advanceRoutineCycleWeek(routineId)
        }
    }

    fun previousRoutineCycleWeek(routineId: Long) {
        viewModelScope.launch {
            repository.previousRoutineCycleWeek(routineId)
        }
    }

    fun setRoutineCycleWeek(routineId: Long, weekNumber: Int) {
        viewModelScope.launch {
            repository.setRoutineCycleWeek(routineId, weekNumber)
        }
    }

    fun instantiateProgram(program: com.kveld9.trackgym.domain.calculator.ProgramRecommendation, onCompleted: (() -> Unit)? = null) {
        viewModelScope.launch {
            repository.instantiateProgram(program)
            onCompleted?.invoke()
        }
    }

    fun importRoutineFromText(rawText: String, onResult: (Boolean) -> Unit) {
        val dto = com.kveld9.trackgym.domain.util.RoutineShareCodec.decodeFromText(rawText)
        if (dto == null) {
            onResult(false)
            return
        }
        viewModelScope.launch {
            repository.importRoutineFromShareDto(dto)
            onResult(true)
        }
    }

    fun moveRoutineUp(routineId: Long, currentList: List<Routine>) {
        val index = currentList.indexOfFirst { it.id == routineId }
        if (index > 0) {
            val mutable = currentList.toMutableList()
            java.util.Collections.swap(mutable, index, index - 1)
            viewModelScope.launch {
                repository.updateRoutinesOrder(mutable.map { it.id })
            }
        }
    }

    fun moveRoutineDown(routineId: Long, currentList: List<Routine>) {
        val index = currentList.indexOfFirst { it.id == routineId }
        if (index >= 0 && index < currentList.size - 1) {
            val mutable = currentList.toMutableList()
            java.util.Collections.swap(mutable, index, index + 1)
            viewModelScope.launch {
                repository.updateRoutinesOrder(mutable.map { it.id })
            }
        }
    }

    fun moveFolderUp(folderId: Long, currentList: List<RoutineFolder>) {
        val index = currentList.indexOfFirst { it.id == folderId }
        if (index > 0) {
            val mutable = currentList.toMutableList()
            java.util.Collections.swap(mutable, index, index - 1)
            viewModelScope.launch {
                repository.updateFoldersOrder(mutable.map { it.id })
            }
        }
    }

    fun moveFolderDown(folderId: Long, currentList: List<RoutineFolder>) {
        val index = currentList.indexOfFirst { it.id == folderId }
        if (index >= 0 && index < currentList.size - 1) {
            val mutable = currentList.toMutableList()
            java.util.Collections.swap(mutable, index, index + 1)
            viewModelScope.launch {
                repository.updateFoldersOrder(mutable.map { it.id })
            }
        }
    }

    fun clearRecentPrAlert() {
        _recentlyUnlockedPr.value = null
    }

    private var recentlyDeletedSet: WorkoutSet? = null

    fun deleteSet(setId: Long) {
        val current = _activeWorkout.value
        val set = current?.exercises?.flatMap { it.sets }?.firstOrNull { it.id == setId }
        if (set != null) {
            deleteSet(set)
        } else {
            viewModelScope.launch {
                repository.deleteSet(setId)
                setActiveWorkout(repository.getActiveWorkout())
            }
        }
    }

    fun deleteSet(set: WorkoutSet) {
        recentlyDeletedSet = set
        viewModelScope.launch {
            repository.deleteSet(set.id)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun restoreRecentlyDeletedSet() {
        val set = recentlyDeletedSet ?: return
        recentlyDeletedSet = null
        viewModelScope.launch {
            repository.restoreSet(set)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun refreshActiveWorkout() {
        viewModelScope.launch {
            loadActiveWorkout()
        }
    }

    fun finishWorkout(
        notes: String = "",
        syncRoutine: Boolean = false,
        detachRoutine: Boolean = false,
        completedAtTimestamp: Long? = null,
        onFinished: () -> Unit
    ) {
        val current = _activeWorkout.value ?: return
        val activeUnit = weightUnit.value
        viewModelScope.launch {
            stopTimer()
            stopRestTimer()
            try {
                if (syncRoutine && !detachRoutine && current.routineId != null) {
                    repository.syncRoutineWithWorkoutValues(current.routineId, current)
                }
                val comparison = repository.finishWorkout(
                    workoutId = current.id,
                    durationSeconds = _timerSeconds.value,
                    notes = notes,
                    weightUnit = activeUnit,
                    userBodyWeightKg = userBodyWeight.value,
                    detachRoutine = detachRoutine,
                    completedAtTimestamp = completedAtTimestamp
                )
                if (comparison != null) {
                    _lastFinishedComparison.value = comparison
                }
            } catch (e: Exception) {
                // Workout was removed or not found (e.g. wiped by an overwrite import)
            } finally {
                setActiveWorkout(null)
                _timerSeconds.value = 0
                onFinished()
            }
        }
    }

    fun cancelActiveWorkout() {
        val current = _activeWorkout.value
        stopTimer()
        stopRestTimer()
        viewModelScope.launch {
            if (current != null) {
                repository.discardActiveWorkout(current.id)
            }
            setActiveWorkout(null)
            _timerSeconds.value = 0
        }
    }

    fun setMuscleFilter(filter: MuscleGroup?) {
        _selectedMuscleFilter.value = filter
    }

    fun setOriginFilter(filter: ExerciseOriginFilter) {
        _selectedOriginFilter.value = filter
    }

    fun setEquipmentFilter(filter: ExerciseCategory?) {
        _selectedEquipmentFilter.value = filter
    }

    fun setMechanicsFilter(filter: MechanicsType?) {
        _selectedMechanicsFilter.value = filter
    }

    fun clearAllExerciseFilters() {
        _selectedMuscleFilter.value = null
        _selectedOriginFilter.value = ExerciseOriginFilter.ALL
        _selectedEquipmentFilter.value = null
        _selectedMechanicsFilter.value = null
        _searchQuery.value = ""
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun createCustomExercise(
        name: String,
        muscleGroup: MuscleGroup,
        category: ExerciseCategory,
        notes: String = "",
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle? = null,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement> = emptyList(),
        mechanics: MechanicsType = MechanicsType.COMPOUND
    ) {
        viewModelScope.launch {
            repository.createCustomExercise(name, muscleGroup, category, notes, primaryMuscle, secondaryMuscles, mechanics)
        }
    }

    fun updateExerciseAnatomy(
        exerciseId: Long,
        primaryMuscle: com.kveld9.trackgym.domain.model.BodyMuscle?,
        secondaryMuscles: List<com.kveld9.trackgym.domain.model.MuscleInvolvement>
    ) {
        viewModelScope.launch {
            repository.updateExerciseAnatomy(exerciseId, primaryMuscle, secondaryMuscles)
        }
    }

    fun viewWorkoutDetail(workoutId: Long) {
        val activeUnit = weightUnit.value
        viewModelScope.launch {
            val comparison = repository.compareWorkoutWithPrevious(workoutId, activeUnit)
            _selectedDetailComparison.value = comparison
        }
    }

    fun clearSelectedDetailComparison() {
        _selectedDetailComparison.value = null
    }

    fun clearLastFinishedComparison() {
        _lastFinishedComparison.value = null
    }

    class Factory(
        private val repository: GymRepository,
        private val themePreferences: ThemePreferences? = null
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GymViewModel(repository, themePreferences) as T
        }
    }
}
