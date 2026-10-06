package com.kveld9.trackgym.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kveld9.trackgym.data.repository.GymRepository
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.domain.model.WorkoutSet
import com.kveld9.trackgym.data.ThemePreferences
import com.kveld9.trackgym.domain.calculator.TrainingConsistencyEngine
import com.kveld9.trackgym.domain.calculator.TrainingConsistencyStats
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

    val routines: StateFlow<List<Routine>> = repository.getAllRoutines()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val folders: StateFlow<List<RoutineFolder>> = repository.getAllFolders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _rawExercises = repository.getAllExercises()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedMuscleFilter = MutableStateFlow<MuscleGroup?>(null)
    val selectedMuscleFilter: StateFlow<MuscleGroup?> = _selectedMuscleFilter.asStateFlow()

    val filteredExercises: StateFlow<List<Exercise>> = combine(
        _rawExercises,
        _searchQuery,
        _selectedMuscleFilter
    ) { exercises, query, filter ->
        exercises.filter { ex ->
            val matchesQuery = query.isBlank() || ex.name.contains(query, ignoreCase = true)
            val matchesFilter = filter == null || ex.muscleGroup == filter
            matchesQuery && matchesFilter
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

    fun addSet(workoutExerciseId: Long, weightKg: Double, reps: Int) {
        viewModelScope.launch {
            repository.addSetToExercise(workoutExerciseId, weightKg, reps)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun addWarmupSets(workoutExerciseId: Long, targetWeightKg: Double) {
        viewModelScope.launch {
            val warmups = com.kveld9.trackgym.domain.calculator.WarmupGenerator.generateWarmupSets(
                targetWeightKg = targetWeightKg,
                workoutExerciseId = workoutExerciseId
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
                val newPrs = repository.completeSet(targetSet, workoutId, exerciseId)
                if (newPrs.isNotEmpty()) {
                    _recentlyUnlockedPr.value = newPrs.first()
                }
                triggerAutoRestTimer()
            }
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    private fun triggerAutoRestTimer() {
        viewModelScope.launch {
            val settings = themePreferences?.themeSettings?.firstOrNull()
            val isAuto = settings?.autoRestTimer ?: true
            val duration = settings?.defaultRestSeconds ?: 90
            if (isAuto) {
                startRestTimer(duration)
            }
        }
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
                _restTimerRemainingSeconds.value = (current - 1).coerceAtLeast(0)
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

    fun clearRecentPrAlert() {
        _recentlyUnlockedPr.value = null
    }

    fun deleteSet(setId: Long) {
        viewModelScope.launch {
            repository.deleteSet(setId)
            setActiveWorkout(repository.getActiveWorkout())
        }
    }

    fun refreshActiveWorkout() {
        viewModelScope.launch {
            loadActiveWorkout()
        }
    }

    fun finishWorkout(notes: String = "", onFinished: () -> Unit) {
        val current = _activeWorkout.value ?: return
        val activeUnit = weightUnit.value
        viewModelScope.launch {
            stopTimer()
            stopRestTimer()
            try {
                val comparison = repository.finishWorkout(
                    workoutId = current.id,
                    durationSeconds = _timerSeconds.value,
                    notes = notes,
                    weightUnit = activeUnit
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

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun createCustomExercise(
        name: String,
        muscleGroup: MuscleGroup,
        category: ExerciseCategory,
        notes: String = ""
    ) {
        viewModelScope.launch {
            repository.createCustomExercise(name, muscleGroup, category, notes)
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
