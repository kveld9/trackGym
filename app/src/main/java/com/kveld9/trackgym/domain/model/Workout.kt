package com.kveld9.trackgym.domain.model

data class Workout(
    val id: Long = 0,
    val name: String,
    val startedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val durationSeconds: Long = 0,
    val isCompleted: Boolean = false,
    val notes: String = "",
    val exercises: List<WorkoutExercise> = emptyList(),
    val routineId: Long? = null
) {
    fun calculateTotalVolume(
        doubleDumbbells: Boolean = true,
        excludeWarmup: Boolean = false,
        userBodyWeightKg: Double = 0.0
    ): Double = exercises.sumOf { it.calculateTotalVolume(doubleDumbbells, excludeWarmup, userBodyWeightKg) }

    val totalVolume: Double
        get() = calculateTotalVolume(true, false)

    fun completedSetsCount(excludeWarmup: Boolean = false): Int =
        exercises.sumOf { it.completedSetsCount(excludeWarmup) }

    val totalCompletedSets: Int
        get() = completedSetsCount(false)
}
