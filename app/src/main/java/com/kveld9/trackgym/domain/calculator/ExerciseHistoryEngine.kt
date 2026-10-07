package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseHistoryEntry
import com.kveld9.trackgym.domain.model.Workout

/**
 * Pure domain engine that extracts and aggregates all historical sessions for a given exercise.
 */
object ExerciseHistoryEngine {

    fun extractHistory(
        exerciseId: Long,
        completedWorkouts: List<Workout>,
        doubleDumbbells: Boolean = true,
        userBodyWeightKg: Double = 0.0
    ): List<ExerciseHistoryEntry> {
        return completedWorkouts
            .mapNotNull { workout ->
                val matchingExercises = workout.exercises.filter { it.exercise.id == exerciseId }
                if (matchingExercises.isEmpty()) return@mapNotNull null

                val allCompletedSets = matchingExercises.flatMap { it.sets.filter { set -> set.isCompleted } }
                if (allCompletedSets.isEmpty()) return@mapNotNull null

                val maxWeight = matchingExercises.maxOfOrNull { it.maxWeight } ?: 0.0
                val totalVolume = matchingExercises.sumOf {
                    it.calculateTotalVolume(
                        doubleDumbbells = doubleDumbbells,
                        userBodyWeightKg = userBodyWeightKg
                    )
                }
                val completedCount = allCompletedSets.size
                val best1Rm = allCompletedSets
                    .filter { it.weightKg > 0.0 && it.reps > 0 }
                    .maxOfOrNull { OneRepMaxCalculator.calculate1RM(it.weightKg, it.reps) } ?: 0.0

                ExerciseHistoryEntry(
                    workoutId = workout.id,
                    workoutName = workout.name,
                    dateMillis = workout.completedAt ?: workout.startedAt,
                    maxWeightKg = maxWeight,
                    totalVolumeKg = totalVolume,
                    completedSetsCount = completedCount,
                    best1RmKg = best1Rm,
                    completedSets = allCompletedSets
                )
            }
            .sortedByDescending { it.dateMillis }
    }
}
