package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

enum class SetType(
    val shortLabel: String,
    @get:StringRes val nameRes: Int,
    val displayName: String
) {
    NORMAL("N", R.string.set_type_normal, "Normal"),
    WARMUP("W", R.string.set_type_warmup, "Warmup"),
    DROP("D", R.string.set_type_drop, "Drop Set"),
    FAILURE("F", R.string.set_type_failure, "Failure"),
    MYO_REPS("M", R.string.set_type_myo_reps, "Myo-Reps")
}
