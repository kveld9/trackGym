package com.kveld9.trackgym.domain.model

data class WorkoutSet(
    val id: Long = 0,
    val workoutExerciseId: Long = 0,
    val setNumber: Int,
    val setType: SetType = SetType.NORMAL,
    val weightKg: Double = 0.0,
    val reps: Int = 0,
    val durationSeconds: Int? = null,
    val distanceKm: Double? = null,
    val caloriesBurned: Int? = null,
    val rpe: Double? = null,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
) {
    val volume: Double
        get() = calculateVolume()

    fun calculateVolume(isBodyweight: Boolean = false, userBodyWeightKg: Double = 0.0): Double {
        if (!isCompleted) return 0.0
        val effectiveWeight = when {
            setType == SetType.BODYWEIGHT_LOAD -> userBodyWeightKg + weightKg
            setType == SetType.BODYWEIGHT_ASSISTED -> (userBodyWeightKg - weightKg).coerceAtLeast(0.0)
            isBodyweight && userBodyWeightKg > 0.0 -> userBodyWeightKg + weightKg
            else -> weightKg
        }
        return effectiveWeight * reps
    }

    val effectiveDurationSeconds: Int
        get() = durationSeconds ?: reps
}
