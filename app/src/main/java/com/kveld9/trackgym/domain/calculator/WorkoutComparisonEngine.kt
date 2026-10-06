package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet

object WorkoutComparisonEngine {

    /**
     * Formats a list of sets into a clean Hevy-style summary,
     * e.g. "2x8 @ 15.0 kg" or "1x10 @ 15.0 kg, 1x8 @ 18.0 kg".
     */
    fun formatSetsSummary(sets: List<WorkoutSet>): String {
        val completed = sets.filter { it.isCompleted && it.reps > 0 }
        if (completed.isEmpty()) return "No completed sets"

        val grouped = mutableListOf<Pair<Int, String>>()
        var currentWeight = completed.first().weightKg
        var currentReps = completed.first().reps
        var count = 0

        for (set in completed) {
            if (set.weightKg == currentWeight && set.reps == currentReps) {
                count++
            } else {
                grouped.add(count to "${currentReps} @ ${formatWeight(currentWeight)} kg")
                currentWeight = set.weightKg
                currentReps = set.reps
                count = 1
            }
        }
        grouped.add(count to "${currentReps} @ ${formatWeight(currentWeight)} kg")

        return grouped.joinToString(", ") { "${it.first}x${it.second}" }
    }

    private fun formatWeight(weight: Double): String {
        return if (weight % 1.0 == 0.0) {
            weight.toInt().toString()
        } else {
            String.format(java.util.Locale.US, "%.1f", weight)
        }
    }

    /**
     * Compares an exercise performed in the current session against its prior performance.
     */
    fun compareExercise(
        exercise: Exercise,
        currentWorkoutExercise: WorkoutExercise,
        previousWorkoutExercise: WorkoutExercise?,
        previousWorkoutDate: Long?,
        recordsUnlocked: List<PersonalRecord> = emptyList()
    ): ExerciseComparison {
        val currentSets = currentWorkoutExercise.sets.filter { it.isCompleted }
        val previousSets = previousWorkoutExercise?.sets?.filter { it.isCompleted } ?: emptyList()

        val setComparisons = mutableListOf<SetComparison>()
        for (i in currentSets.indices) {
            val curr = currentSets[i]
            val prev = previousSets.getOrNull(i)

            val weightDelta = if (prev != null) curr.weightKg - prev.weightKg else curr.weightKg
            val repsDelta = if (prev != null) curr.reps - prev.reps else curr.reps
            val volumeDelta = if (prev != null) curr.volume - prev.volume else curr.volume

            val isImprovement = prev == null || weightDelta > 0 || (weightDelta == 0.0 && repsDelta > 0)
            val matchingRecords = recordsUnlocked.filter { it.weightKg == curr.weightKg && it.reps == curr.reps }

            setComparisons.add(
                SetComparison(
                    setNumber = i + 1,
                    previousSet = prev,
                    currentSet = curr,
                    weightDeltaKg = weightDelta,
                    repsDelta = repsDelta,
                    volumeDeltaKg = volumeDelta,
                    isImprovement = isImprovement,
                    recordsUnlocked = matchingRecords
                )
            )
        }

        val prevVolume = previousSets.sumOf { it.volume }
        val currVolume = currentSets.sumOf { it.volume }
        val totalVolumeDelta = currVolume - prevVolume

        return ExerciseComparison(
            exercise = exercise,
            previousWorkoutDate = previousWorkoutDate,
            previousSummary = if (previousSets.isNotEmpty()) formatSetsSummary(previousSets) else "First time",
            currentSummary = formatSetsSummary(currentSets),
            setComparisons = setComparisons,
            totalVolumeDeltaKg = totalVolumeDelta,
            recordsUnlocked = recordsUnlocked
        )
    }
}
