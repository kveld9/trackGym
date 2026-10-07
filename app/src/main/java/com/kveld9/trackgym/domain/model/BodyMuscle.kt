package com.kveld9.trackgym.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Anatomical muscles represented in the body heatmap visualization.
 * Maps to front and back overlay drawables aligned to the base body silhouette.
 */
enum class BodyMuscle(
    val muscleGroup: MuscleGroup,
    @get:StringRes val nameRes: Int,
    val displayName: String,
    @get:DrawableRes val frontRes: Int? = null,
    @get:DrawableRes val backRes: Int? = null
) {
    CHEST(MuscleGroup.CHEST, R.string.body_muscle_chest, "Chest", frontRes = R.drawable.muscle_chest_front),
    LATS(MuscleGroup.BACK, R.string.body_muscle_lats, "Lats", frontRes = R.drawable.muscle_lats_front, backRes = R.drawable.muscle_lats_back),
    UPPER_BACK(MuscleGroup.BACK, R.string.body_muscle_upper_back, "Upper Back", frontRes = R.drawable.muscle_upperback_front, backRes = R.drawable.muscle_upperback_back),
    LOWER_BACK(MuscleGroup.BACK, R.string.body_muscle_lower_back, "Lower Back", backRes = R.drawable.muscle_lowerback_back),
    TRAPS(MuscleGroup.BACK, R.string.body_muscle_traps, "Traps", frontRes = R.drawable.muscle_traps_front, backRes = R.drawable.muscle_traps_back),
    SHOULDERS(MuscleGroup.SHOULDERS, R.string.body_muscle_shoulders, "Shoulders", frontRes = R.drawable.muscle_shoulders_front, backRes = R.drawable.muscle_shoulders_back),
    BICEPS(MuscleGroup.ARMS, R.string.body_muscle_biceps, "Biceps", frontRes = R.drawable.muscle_biceps_front, backRes = R.drawable.muscle_biceps_back),
    TRICEPS(MuscleGroup.ARMS, R.string.body_muscle_triceps, "Triceps", frontRes = R.drawable.muscle_triceps_front, backRes = R.drawable.muscle_triceps_back),
    FOREARMS(MuscleGroup.ARMS, R.string.body_muscle_forearms, "Forearms", frontRes = R.drawable.muscle_forearms_front, backRes = R.drawable.muscle_forearms_back),
    ABDOMINALS(MuscleGroup.CORE, R.string.body_muscle_abdominals, "Abdominals", frontRes = R.drawable.muscle_abdominals_front, backRes = R.drawable.muscle_abdominals_back),
    QUADRICEPS(MuscleGroup.LEGS, R.string.body_muscle_quadriceps, "Quadriceps", frontRes = R.drawable.muscle_quadriceps_front, backRes = R.drawable.muscle_quadriceps_back),
    HAMSTRINGS(MuscleGroup.LEGS, R.string.body_muscle_hamstrings, "Hamstrings", backRes = R.drawable.muscle_hamstrings_back),
    GLUTES(MuscleGroup.LEGS, R.string.body_muscle_glutes, "Glutes", backRes = R.drawable.muscle_glutes_back),
    CALVES(MuscleGroup.LEGS, R.string.body_muscle_calves, "Calves", frontRes = R.drawable.muscle_calves_front, backRes = R.drawable.muscle_calves_back),
    ADDUCTORS(MuscleGroup.LEGS, R.string.body_muscle_adductors, "Adductors", frontRes = R.drawable.muscle_adductors_front, backRes = R.drawable.muscle_adductors_back),
    ABDUCTORS(MuscleGroup.LEGS, R.string.body_muscle_abductors, "Abductors", frontRes = R.drawable.muscle_abductors_front),
    NECK(MuscleGroup.OTHER, R.string.body_muscle_neck, "Neck", frontRes = R.drawable.muscle_neck_front, backRes = R.drawable.muscle_neck_back)
}
