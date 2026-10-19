package com.kveld9.trackgym.domain.model

data class RoutineExercise(
    val id: Long = 0,
    val exercise: Exercise,
    val orderIndex: Int = 0,
    val sets: List<RoutineSetPlan> = listOf(
        RoutineSetPlan(),
        RoutineSetPlan(),
        RoutineSetPlan()
    )
) {
    constructor(
        id: Long = 0,
        exercise: Exercise,
        orderIndex: Int = 0,
        targetSets: Int,
        defaultWeightKg: Double = 0.0,
        defaultReps: Int = 10
    ) : this(
        id = id,
        exercise = exercise,
        orderIndex = orderIndex,
        sets = List(targetSets.coerceAtLeast(1)) {
            RoutineSetPlan(
                weightKg = if (defaultWeightKg >= 0.0) defaultWeightKg else 0.0,
                reps = if (defaultReps >= 1) defaultReps else 1
            )
        }
    )

    val targetSets: Int
        get() = sets.size.coerceAtLeast(1)

    val defaultWeightKg: Double
        get() = sets.firstOrNull()?.weightKg ?: 0.0

    val defaultReps: Int
        get() = sets.firstOrNull()?.reps ?: 10
}
