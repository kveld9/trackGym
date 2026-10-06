package com.kveld9.trackgym.domain.model

data class WorkoutExercise(
    val id: Long = 0,
    val workoutId: Long = 0,
    val exercise: Exercise,
    val sets: List<WorkoutSet> = emptyList(),
    val orderIndex: Int = 0,
    val notes: String = ""
) {
    val completedSetsCount: Int
        get() = sets.count { it.isCompleted }

    val totalVolume: Double
        get() = sets.filter { it.isCompleted }.sumOf { it.volume }

    val maxWeight: Double
        get() = sets.filter { it.isCompleted }.maxOfOrNull { it.weightKg } ?: 0.0
}
