package com.kveld9.trackgym.domain.model

/**
 * Historical performance summary of an exercise in a completed workout session.
 */
data class ExerciseHistoryEntry(
    val workoutId: Long,
    val workoutName: String,
    val dateMillis: Long,
    val maxWeightKg: Double,
    val totalVolumeKg: Double,
    val completedSetsCount: Int,
    val best1RmKg: Double,
    val completedSets: List<WorkoutSet>,
    val bestSetVolumeKg: Double = 0.0,
    val bestTimeSeconds: Int = 0,
    val totalTimeSeconds: Int = 0
)
