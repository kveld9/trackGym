package com.kveld9.trackgym.domain.model

data class WorkoutSet(
    val id: Long = 0,
    val workoutExerciseId: Long = 0,
    val setNumber: Int,
    val setType: SetType = SetType.NORMAL,
    val weightKg: Double = 0.0,
    val reps: Int = 0,
    val rpe: Double? = null,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
) {
    val volume: Double
        get() = if (isCompleted) weightKg * reps else 0.0
}
