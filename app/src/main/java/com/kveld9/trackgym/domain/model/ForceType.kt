package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Directional force vector classification:
 * - PUSH (Concentric movement driving load away from the body / pushing against resistance)
 * - PULL (Concentric movement drawing load toward the body / pulling resistance)
 * - STATIC (Isometric tension with no continuous joint displacement)
 * - OTHER (Non-directional or hybrid movement)
 */
enum class ForceType(@get:StringRes val nameRes: Int, val displayName: String) {
    PUSH(R.string.force_push, "Push"),
    PULL(R.string.force_pull, "Pull"),
    STATIC(R.string.force_static, "Static"),
    OTHER(R.string.force_other, "Other");

    companion object {
        fun fromString(value: String): ForceType {
            val clean = value.trim()
            return entries.find {
                it.name.equals(clean, ignoreCase = true) ||
                it.displayName.equals(clean, ignoreCase = true)
            } ?: PUSH
        }
    }
}
