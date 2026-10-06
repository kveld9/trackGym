package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet

object PersonalRecordDetector {

    /**
     * Checks a completed set against prior historical sets for the exercise
     * and returns any newly established or broken personal records.
     */
    fun evaluateSet(
        exerciseId: Long,
        workoutId: Long,
        currentSet: WorkoutSet,
        historicalSets: List<WorkoutSet>,
        timestamp: Long = System.currentTimeMillis(),
        formula: OneRepMaxFormula = OneRepMaxFormula.EPLEY
    ): List<PersonalRecord> {
        if (!currentSet.isCompleted || currentSet.weightKg <= 0.0 || currentSet.reps <= 0 || currentSet.setType == SetType.WARMUP) {
            return emptyList()
        }

        val completedHistory = historicalSets.filter { it.isCompleted && it.id != currentSet.id && it.setType != SetType.WARMUP }
        val newRecords = mutableListOf<PersonalRecord>()

        checkMaxWeight(exerciseId, workoutId, currentSet, completedHistory, timestamp)?.let {
            newRecords.add(it)
        }

        checkMaxRepsAtWeight(exerciseId, workoutId, currentSet, completedHistory, timestamp)?.let {
            newRecords.add(it)
        }

        checkEstimated1RM(exerciseId, workoutId, currentSet, completedHistory, timestamp, formula)?.let {
            newRecords.add(it)
        }

        return newRecords
    }

    private fun checkMaxWeight(
        exerciseId: Long,
        workoutId: Long,
        currentSet: WorkoutSet,
        history: List<WorkoutSet>,
        timestamp: Long
    ): PersonalRecord? {
        val prevMax = history.maxOfOrNull { it.weightKg }
        val isRecord = prevMax == null || currentSet.weightKg > prevMax
        if (!isRecord) return null

        val desc = if (prevMax != null) {
            "Beat previous max weight (${prevMax} kg) with ${currentSet.weightKg} kg"
        } else {
            "First max weight record: ${currentSet.weightKg} kg"
        }

        return PersonalRecord(
            exerciseId = exerciseId,
            recordType = RecordType.MAX_WEIGHT,
            recordValue = currentSet.weightKg,
            weightKg = currentSet.weightKg,
            reps = currentSet.reps,
            achievedAt = timestamp,
            workoutId = workoutId,
            description = desc
        )
    }

    private fun checkMaxRepsAtWeight(
        exerciseId: Long,
        workoutId: Long,
        currentSet: WorkoutSet,
        history: List<WorkoutSet>,
        timestamp: Long
    ): PersonalRecord? {
        val sameWeightSets = history.filter { it.weightKg == currentSet.weightKg }
        val prevMaxReps = sameWeightSets.maxOfOrNull { it.reps }

        val isRecord = prevMaxReps != null && currentSet.reps > prevMaxReps
        if (!isRecord) return null

        return PersonalRecord(
            exerciseId = exerciseId,
            recordType = RecordType.MAX_REPS_AT_WEIGHT,
            recordValue = currentSet.reps.toDouble(),
            weightKg = currentSet.weightKg,
            reps = currentSet.reps,
            achievedAt = timestamp,
            workoutId = workoutId,
            description = "At ${currentSet.weightKg} kg performed ${currentSet.reps} reps (previous: $prevMaxReps reps)"
        )
    }

    private fun checkEstimated1RM(
        exerciseId: Long,
        workoutId: Long,
        currentSet: WorkoutSet,
        history: List<WorkoutSet>,
        timestamp: Long,
        formula: OneRepMaxFormula
    ): PersonalRecord? {
        val current1RM = OneRepMaxCalculator.calculate1RM(currentSet.weightKg, currentSet.reps, formula)
        val prevMax1RM = history.map { OneRepMaxCalculator.calculate1RM(it.weightKg, it.reps, formula) }.maxOfOrNull { it }

        val isRecord = prevMax1RM == null || current1RM > prevMax1RM
        if (!isRecord) return null

        val desc = if (prevMax1RM != null) {
            "New estimated 1RM: ${current1RM} kg (previous: ${prevMax1RM} kg)"
        } else {
            "Initial estimated 1RM: ${current1RM} kg"
        }

        return PersonalRecord(
            exerciseId = exerciseId,
            recordType = RecordType.ESTIMATED_1RM,
            recordValue = current1RM,
            weightKg = currentSet.weightKg,
            reps = currentSet.reps,
            achievedAt = timestamp,
            workoutId = workoutId,
            description = desc
        )
    }
}
