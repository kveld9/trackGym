package com.kveld9.trackgym.domain.model

data class WorkoutExercise(
    val id: Long = 0,
    val workoutId: Long = 0,
    val exercise: Exercise,
    val sets: List<WorkoutSet> = emptyList(),
    val orderIndex: Int = 0,
    val notes: String = "",
    val supersetGroupId: String? = null
) {
    fun completedSetsCount(excludeWarmup: Boolean = false): Int =
        sets.count { it.isCompleted && (!excludeWarmup || it.setType != SetType.WARMUP) }

    val completedSetsCount: Int
        get() = completedSetsCount(false)

    fun calculateTotalVolume(
        doubleDumbbells: Boolean = true,
        excludeWarmup: Boolean = false,
        userBodyWeightKg: Double = 0.0
    ): Double {
        val multiplier = if (doubleDumbbells && exercise.category == ExerciseCategory.DUMBBELL) 2.0 else 1.0
        val isBodyweight = exercise.category == ExerciseCategory.BODYWEIGHT
        return sets.filter { it.isCompleted && (!excludeWarmup || it.setType != SetType.WARMUP) }
            .sumOf { it.calculateVolume(isBodyweight, userBodyWeightKg) } * multiplier
    }

    val totalVolume: Double
        get() = calculateTotalVolume(true, false)

    val maxWeight: Double
        get() = sets.filter { it.isCompleted }.maxOfOrNull { it.weightKg } ?: 0.0
}
