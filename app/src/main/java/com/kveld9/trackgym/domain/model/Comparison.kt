package com.kveld9.trackgym.domain.model

data class SetComparison(
    val setNumber: Int,
    val previousSet: WorkoutSet?,
    val currentSet: WorkoutSet,
    val weightDeltaKg: Double,
    val repsDelta: Int,
    val volumeDeltaKg: Double,
    val isImprovement: Boolean,
    val recordsUnlocked: List<PersonalRecord> = emptyList()
)

data class ExerciseComparison(
    val exercise: Exercise,
    val previousWorkoutDate: Long?,
    val previousSummary: String,
    val currentSummary: String,
    val setComparisons: List<SetComparison>,
    val totalVolumeDeltaKg: Double,
    val recordsUnlocked: List<PersonalRecord> = emptyList()
) {
    val hasRecords: Boolean
        get() = recordsUnlocked.isNotEmpty()

    val isOverallImproved: Boolean
        get() = totalVolumeDeltaKg > 0 || recordsUnlocked.isNotEmpty() || setComparisons.any { it.isImprovement }
}

data class WorkoutComparison(
    val currentWorkout: Workout,
    val previousWorkout: Workout?,
    val exerciseComparisons: List<ExerciseComparison>,
    val totalRecordsUnlocked: List<PersonalRecord>,
    val totalVolumeDeltaKg: Double
)
