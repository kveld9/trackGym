package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes

/**
 * Domain model representing comprehensive visual and textual technical guidance
 * for an exercise, including step-by-step setup, execution cues, biomechanical safety,
 * breathing, and tempo recommendations.
 */
data class ExerciseGuide(
    val exerciseName: String,
    val setupSteps: List<Int>, // @get:StringRes
    val executionSteps: List<Int>, // @get:StringRes
    val commonMistakes: List<Int>, // @get:StringRes
    @get:StringRes val breathingCueRes: Int,
    @get:StringRes val tempoCueRes: Int
)
