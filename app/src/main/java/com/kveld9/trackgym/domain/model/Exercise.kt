package com.kveld9.trackgym.domain.model

data class Exercise(
    val id: Long = 0,
    val name: String,
    val muscleGroup: MuscleGroup,
    val category: ExerciseCategory = ExerciseCategory.BARBELL,
    val notes: String = "",
    val restDurationSeconds: Int? = null,
    val isCustom: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
