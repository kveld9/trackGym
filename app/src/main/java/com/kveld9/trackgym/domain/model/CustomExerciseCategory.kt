package com.kveld9.trackgym.domain.model

/**
 * User-defined custom category or tag for grouping exercises.
 * (e.g. "Calisthenics", "Powerlifting", "Shoulder Accessories", "Mobility").
 */
data class CustomExerciseCategory(
    val id: Long = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)
