package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Technical execution complexity and neuromuscular demand:
 * - BEGINNER (Guided path machines, simple cables, or fundamental movements with low technical barrier)
 * - INTERMEDIATE (Free weight barbell/dumbbell compounds or bodyweight demanding technical bracing)
 * - EXPERT (High technical demand, advanced coordination, Olympic variations, or high CNS loads)
 */
enum class DifficultyLevel(@get:StringRes val nameRes: Int, val displayName: String) {
    BEGINNER(R.string.level_beginner, "Beginner"),
    INTERMEDIATE(R.string.level_intermediate, "Intermediate"),
    EXPERT(R.string.level_expert, "Expert");

    companion object {
        fun fromString(value: String): DifficultyLevel {
            val clean = value.trim()
            return entries.find {
                it.name.equals(clean, ignoreCase = true) ||
                it.displayName.equals(clean, ignoreCase = true) ||
                (it == EXPERT && clean.equals("ADVANCED", ignoreCase = true))
            } ?: BEGINNER
        }
    }
}
