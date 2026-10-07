package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Biomechanical movement classification:
 * - COMPOUND (Multi-joint movements involving multiple prime movers and joints)
 * - ISOLATION (Single-joint movements targeting an isolated muscle group)
 */
enum class MechanicsType(@get:StringRes val nameRes: Int, val displayName: String) {
    COMPOUND(R.string.mechanics_compound, "Compound"),
    ISOLATION(R.string.mechanics_isolation, "Isolation");

    companion object {
        fun fromString(value: String): MechanicsType {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: COMPOUND
        }
    }
}
