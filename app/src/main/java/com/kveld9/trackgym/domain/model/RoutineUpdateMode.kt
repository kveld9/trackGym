package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

enum class RoutineUpdateMode(
    @get:StringRes val titleRes: Int,
    @get:StringRes val descRes: Int
) {
    ALWAYS(
        titleRes = R.string.routine_update_mode_always_title,
        descRes = R.string.routine_update_mode_always_desc
    ),
    ASK(
        titleRes = R.string.routine_update_mode_ask_title,
        descRes = R.string.routine_update_mode_ask_desc
    ),
    NEVER(
        titleRes = R.string.routine_update_mode_never_title,
        descRes = R.string.routine_update_mode_never_desc
    );

    companion object {
        fun fromString(value: String?): RoutineUpdateMode = when (value?.uppercase()) {
            "ALWAYS" -> ALWAYS
            "NEVER" -> NEVER
            else -> ASK
        }
    }
}
