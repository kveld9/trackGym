package com.kveld9.trackgym.domain.model

data class Exercise(
    val id: Long = 0,
    val name: String,
    val muscleGroup: MuscleGroup,
    val category: ExerciseCategory = ExerciseCategory.BARBELL,
    val notes: String = "",
    val restDurationSeconds: Int? = null,
    val warmupRampProtocol: String? = null,
    val autoProgressionRule: String? = null,
    val primaryMuscle: BodyMuscle? = null,
    val secondaryMuscles: List<MuscleInvolvement> = emptyList(),
    val mechanics: MechanicsType = MechanicsType.COMPOUND,
    val force: ForceType = ForceType.PUSH,
    val level: DifficultyLevel = DifficultyLevel.BEGINNER,
    val customCategories: List<String> = emptyList(),
    val isCustom: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
