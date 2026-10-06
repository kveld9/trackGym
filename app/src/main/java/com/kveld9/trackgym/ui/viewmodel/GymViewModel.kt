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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GymViewModel(private val repository: GymRepository) : ViewModel() {

    private val _activeWorkout = MutableStateFlow<Workout?>(null)
    val activeWorkout: StateFlow<Workout?> = _activeWorkout.asStateFlow()

    private val _timerSeconds = MutableStateFlow(0L)
    val timerSeconds: StateFlow<Long> = _timerSeconds.asStateFlow()
    private var timerJob: Job? = null

    val completedWorkouts: StateFlow<List<Workout>> = repository.getCompletedWorkouts()
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

    private suspend fun loadActiveWorkout() {
        val active = repository.getActiveWorkout()
        _activeWorkout.value = active
        if (active != null) {
            val elapsed = (System.currentTimeMillis() - active.startedAt) / 1000
            _timerSeconds.value = elapsed.coerceAtLeast(0)
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timerSeconds.value += 1
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
            _activeWorkout.value = workout
            _timerSeconds.value = 0
            startTimer()
        }
    }

    fun addExerciseToActiveWorkout(exerciseId: Long) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            repository.addExerciseToWorkout(current.id, exerciseId)
            _activeWorkout.value = repository.getActiveWorkout()
        }
    }

    fun removeExerciseFromActiveWorkout(workoutExerciseId: Long) {
        viewModelScope.launch {
            repository.removeExerciseFromWorkout(workoutExerciseId)
            _activeWorkout.value = repository.getActiveWorkout()
        }
    }

    fun addSet(workoutExerciseId: Long, weightKg: Double, reps: Int) {
        viewModelScope.launch {
            repository.addSetToExercise(workoutExerciseId, weightKg, reps)
            _activeWorkout.value = repository.getActiveWorkout()
        }
    }

    fun updateSet(set: WorkoutSet) {
        viewModelScope.launch {
            repository.updateSet(set)
            _activeWorkout.value = repository.getActiveWorkout()
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
            }
            _activeWorkout.value = repository.getActiveWorkout()
        }
    }

    fun clearRecentPrAlert() {
        _recentlyUnlockedPr.value = null
    }

    fun deleteSet(setId: Long) {
        viewModelScope.launch {
            repository.deleteSet(setId)
            _activeWorkout.value = repository.getActiveWorkout()
        }
    }

    fun finishWorkout(notes: String = "", onFinished: () -> Unit) {
        val current = _activeWorkout.value ?: return
        viewModelScope.launch {
            stopTimer()
            val comparison = repository.finishWorkout(current.id, _timerSeconds.value, notes)
            _lastFinishedComparison.value = comparison
            _activeWorkout.value = null
            _timerSeconds.value = 0
            onFinished()
        }
    }

    fun cancelActiveWorkout() {
        stopTimer()
        _activeWorkout.value = null
        _timerSeconds.value = 0
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
        viewModelScope.launch {
            val comparison = repository.compareWorkoutWithPrevious(workoutId)
            _selectedDetailComparison.value = comparison
        }
    }

    fun clearSelectedDetailComparison() {
        _selectedDetailComparison.value = null
    }

    class Factory(private val repository: GymRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GymViewModel(repository) as T
        }
    }
}
