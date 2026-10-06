package com.kveld9.trackgym.domain.model

import androidx.annotation.DrawableRes
import com.kveld9.trackgym.R

/**
 * Anatomical muscles represented in the body heatmap visualization.
 * Maps to front and back overlay drawables aligned to the base body silhouette.
 */
enum class BodyMuscle(
    val muscleGroup: MuscleGroup,
    @get:DrawableRes val frontRes: Int? = null,
    @get:DrawableRes val backRes: Int? = null
) {
    CHEST(MuscleGroup.CHEST, frontRes = R.drawable.muscle_chest_front),
    LATS(MuscleGroup.BACK, frontRes = R.drawable.muscle_lats_front, backRes = R.drawable.muscle_lats_back),
    UPPER_BACK(MuscleGroup.BACK, frontRes = R.drawable.muscle_upperback_front, backRes = R.drawable.muscle_upperback_back),
    LOWER_BACK(MuscleGroup.BACK, backRes = R.drawable.muscle_lowerback_back),
    TRAPS(MuscleGroup.BACK, frontRes = R.drawable.muscle_traps_front, backRes = R.drawable.muscle_traps_back),
    SHOULDERS(MuscleGroup.SHOULDERS, frontRes = R.drawable.muscle_shoulders_front, backRes = R.drawable.muscle_shoulders_back),
    BICEPS(MuscleGroup.ARMS, frontRes = R.drawable.muscle_biceps_front, backRes = R.drawable.muscle_biceps_back),
    TRICEPS(MuscleGroup.ARMS, frontRes = R.drawable.muscle_triceps_front, backRes = R.drawable.muscle_triceps_back),
    FOREARMS(MuscleGroup.ARMS, frontRes = R.drawable.muscle_forearms_front, backRes = R.drawable.muscle_forearms_back),
    ABDOMINALS(MuscleGroup.CORE, frontRes = R.drawable.muscle_abdominals_front, backRes = R.drawable.muscle_abdominals_back),
    QUADRICEPS(MuscleGroup.LEGS, frontRes = R.drawable.muscle_quadriceps_front, backRes = R.drawable.muscle_quadriceps_back),
    HAMSTRINGS(MuscleGroup.LEGS, backRes = R.drawable.muscle_hamstrings_back),
    GLUTES(MuscleGroup.LEGS, backRes = R.drawable.muscle_glutes_back),
    CALVES(MuscleGroup.LEGS, frontRes = R.drawable.muscle_calves_front, backRes = R.drawable.muscle_calves_back),
    ADDUCTORS(MuscleGroup.LEGS, frontRes = R.drawable.muscle_adductors_front, backRes = R.drawable.muscle_adductors_back),
    ABDUCTORS(MuscleGroup.LEGS, frontRes = R.drawable.muscle_abductors_front),
    NECK(MuscleGroup.OTHER, frontRes = R.drawable.muscle_neck_front, backRes = R.drawable.muscle_neck_back)
}
