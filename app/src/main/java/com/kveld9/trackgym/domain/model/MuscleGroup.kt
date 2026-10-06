package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

enum class MuscleGroup(@get:StringRes val nameRes: Int, val displayName: String) {
    CHEST(R.string.muscle_chest, "Chest"),
    BACK(R.string.muscle_back, "Back"),
    LEGS(R.string.muscle_legs, "Legs"),
    SHOULDERS(R.string.muscle_shoulders, "Shoulders"),
    ARMS(R.string.muscle_arms, "Arms"),
    CORE(R.string.muscle_core, "Core"),
    FULL_BODY(R.string.muscle_full_body, "Full Body"),
    OTHER(R.string.muscle_other, "Other");

    companion object {
        fun fromString(value: String): MuscleGroup {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: OTHER
        }
    }
}
