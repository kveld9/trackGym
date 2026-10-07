package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

enum class ExerciseCategory(@get:StringRes val nameRes: Int, val displayName: String) {
    BARBELL(R.string.category_barbell, "Barbell"),
    DUMBBELL(R.string.category_dumbbell, "Dumbbell"),
    MACHINE(R.string.category_machine, "Machine"),
    CABLE(R.string.category_cable, "Cable"),
    BODYWEIGHT(R.string.category_bodyweight, "Bodyweight"),
    SMITH_MACHINE(R.string.category_smith_machine, "Smith Machine"),
    KETTLEBELL(R.string.category_kettlebell, "Kettlebell"),
    BAND(R.string.category_band, "Band"),
    CARDIO(R.string.category_cardio, "Cardio"),
    OTHER(R.string.category_other, "Other");

    companion object {
        fun fromString(value: String): ExerciseCategory {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: OTHER
        }
    }
}
