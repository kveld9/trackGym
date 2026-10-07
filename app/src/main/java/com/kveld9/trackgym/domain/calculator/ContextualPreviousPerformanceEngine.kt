package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet

/**
 * Pure domain engine that resolves context-aware previous performance for an exercise,
 * prioritizing previous sessions within the same routine template over global history.
 */
object ContextualPreviousPerformanceEngine {

    /**
     * Finds the most relevant previous workout exercise for [exerciseId], prioritizing
     * the last completed session belonging to [preferredRoutineId].
     * If no prior session exists within that routine, falls back to the globally most recent session.
     */
    fun findPreviousWorkoutExercise(
        exerciseId: Long,
        completedWorkouts: List<Workout>,
        preferredRoutineId: Long? = null,
        currentWorkoutId: Long? = null
    ): WorkoutExercise? {
        val eligibleWorkouts = completedWorkouts
            .filter { it.isCompleted && (currentWorkoutId == null || it.id != currentWorkoutId) }
            .sortedByDescending { it.completedAt ?: it.startedAt }

        if (preferredRoutineId != null) {
            val sameRoutineMatch = findInRoutine(exerciseId, eligibleWorkouts, preferredRoutineId)
            if (sameRoutineMatch != null) return sameRoutineMatch
        }

        return findInAnyWorkout(exerciseId, eligibleWorkouts)
    }

    private fun findInRoutine(
        exerciseId: Long,
        workouts: List<Workout>,
        routineId: Long
    ): WorkoutExercise? {
        val workout = workouts.firstOrNull { w ->
            w.routineId == routineId && w.exercises.any { it.exercise.id == exerciseId }
        } ?: return null

        return workout.exercises.firstOrNull { it.exercise.id == exerciseId }
    }

    private fun findInAnyWorkout(
        exerciseId: Long,
        workouts: List<Workout>
    ): WorkoutExercise? {
        val workout = workouts.firstOrNull { w ->
            w.exercises.any { it.exercise.id == exerciseId }
        } ?: return null

        return workout.exercises.firstOrNull { it.exercise.id == exerciseId }
    }

    /**
     * Resolves the previous completed sets for an exercise, prioritizing the same routine.
     */
    fun findPreviousSets(
        exerciseId: Long,
        completedWorkouts: List<Workout>,
        preferredRoutineId: Long? = null,
        currentWorkoutId: Long? = null
    ): List<WorkoutSet> {
        val previousWe = findPreviousWorkoutExercise(
            exerciseId = exerciseId,
            completedWorkouts = completedWorkouts,
            preferredRoutineId = preferredRoutineId,
            currentWorkoutId = currentWorkoutId
        ) ?: return emptyList()

        return previousWe.sets.filter { it.isCompleted }
    }

    /**
     * Returns true if the resolved previous workout belonged to the same routine template.
     */
    fun isFromSameRoutine(previousWorkoutRoutineId: Long?, currentRoutineId: Long?): Boolean {
        if (currentRoutineId == null || previousWorkoutRoutineId == null) return false
        return currentRoutineId == previousWorkoutRoutineId
    }
}
