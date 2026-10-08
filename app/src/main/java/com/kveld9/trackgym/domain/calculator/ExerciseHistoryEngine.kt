package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseHistoryEntry
import com.kveld9.trackgym.domain.model.SetType
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

                // Best Set Volume: max(setVolume) for a single completed set in this session
                val isDumbbell = matchingExercises.any { it.exercise.category == ExerciseCategory.DUMBBELL }
                val dumbbellMultiplier = if (doubleDumbbells && isDumbbell) 2.0 else 1.0
                val isBodyweight = matchingExercises.any { it.exercise.category == ExerciseCategory.BODYWEIGHT }
                val bestSetVolume = allCompletedSets
                    .filter { it.reps > 0 && it.setType != SetType.CARDIO }
                    .maxOfOrNull { it.calculateVolume(isBodyweight, userBodyWeightKg) * dumbbellMultiplier } ?: 0.0

                // Time metrics for isometric / timed / cardio sets
                val bestTime = allCompletedSets
                    .filter { it.setType == SetType.DURATION || it.setType == SetType.CARDIO }
                    .maxOfOrNull { it.durationSeconds ?: it.reps } ?: 0
                val totalTime = allCompletedSets
                    .filter { it.setType == SetType.DURATION || it.setType == SetType.CARDIO }
                    .sumOf { it.durationSeconds ?: it.reps }

                ExerciseHistoryEntry(
                    workoutId = workout.id,
                    workoutName = workout.name,
                    dateMillis = workout.completedAt ?: workout.startedAt,
                    maxWeightKg = maxWeight,
                    totalVolumeKg = totalVolume,
                    completedSetsCount = completedCount,
                    best1RmKg = best1Rm,
                    completedSets = allCompletedSets,
                    bestSetVolumeKg = bestSetVolume,
                    bestTimeSeconds = bestTime,
                    totalTimeSeconds = totalTime
                )
            }
            .sortedByDescending { it.dateMillis }
    }
}
