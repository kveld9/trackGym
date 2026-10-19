package com.kveld9.trackgym.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RoutineSetPlan(
    val weightKg: Double = 0.0,
    val reps: Int = 10,
    val setType: SetType = SetType.NORMAL
)
