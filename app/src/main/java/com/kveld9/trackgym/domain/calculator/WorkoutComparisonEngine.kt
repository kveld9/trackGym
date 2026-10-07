package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet

object WorkoutComparisonEngine {

    /**
     * Formats a list of sets into a clean summary notation,
     * e.g. "2x8 @ 15 kg" or "1x10 @ 15 kg, 1x8 @ 18 kg".
     */
    fun formatSetsSummary(sets: List<WorkoutSet>, weightUnit: WeightUnit = WeightUnit.KG): String {
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
                grouped.add(count to "${currentReps} @ ${weightUnit.format(currentWeight)}")
                currentWeight = set.weightKg
                currentReps = set.reps
                count = 1
            }
        }
        grouped.add(count to "${currentReps} @ ${weightUnit.format(currentWeight)}")

        return grouped.joinToString(", ") { "${it.first}x${it.second}" }
    }

    /**
     * Compares an exercise performed in the current session against its prior performance.
     */
    fun compareExercise(
        exercise: Exercise,
        currentWorkoutExercise: WorkoutExercise,
        previousWorkoutExercise: WorkoutExercise?,
        previousWorkoutDate: Long?,
        recordsUnlocked: List<PersonalRecord> = emptyList(),
        weightUnit: WeightUnit = WeightUnit.KG,
        userBodyWeightKg: Double = 0.0
    ): ExerciseComparison {
        val isBodyweight = exercise.category == ExerciseCategory.BODYWEIGHT
        val currentSets = currentWorkoutExercise.sets.filter { it.isCompleted }
        val previousSets = previousWorkoutExercise?.sets?.filter { it.isCompleted } ?: emptyList()

        val setComparisons = mutableListOf<SetComparison>()
        for (i in currentSets.indices) {
            val curr = currentSets[i]
            val prev = previousSets.getOrNull(i)

            val weightDelta = if (prev != null) curr.weightKg - prev.weightKg else curr.weightKg
            val repsDelta = if (prev != null) curr.reps - prev.reps else curr.reps
            val currVol = curr.calculateVolume(isBodyweight, userBodyWeightKg)
            val prevVol = prev?.calculateVolume(isBodyweight, userBodyWeightKg) ?: 0.0
            val volumeDelta = if (prev != null) currVol - prevVol else currVol

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

        val prevVolume = previousSets.sumOf { it.calculateVolume(isBodyweight, userBodyWeightKg) }
        val currVolume = currentSets.sumOf { it.calculateVolume(isBodyweight, userBodyWeightKg) }
        val totalVolumeDelta = currVolume - prevVolume

        return ExerciseComparison(
            exercise = exercise,
            previousWorkoutDate = previousWorkoutDate,
            previousSummary = if (previousSets.isNotEmpty()) formatSetsSummary(previousSets, weightUnit) else "First time",
            currentSummary = formatSetsSummary(currentSets, weightUnit),
            setComparisons = setComparisons,
            totalVolumeDeltaKg = totalVolumeDelta,
            recordsUnlocked = recordsUnlocked
        )
    }
}
