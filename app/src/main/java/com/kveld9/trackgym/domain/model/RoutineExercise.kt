package com.kveld9.trackgym.domain.model

data class RoutineExercise(
    val id: Long = 0,
    val exercise: Exercise,
    val orderIndex: Int = 0,
    val targetSets: Int = 3,
    val defaultWeightKg: Double = 0.0,
    val defaultReps: Int = 10
)
