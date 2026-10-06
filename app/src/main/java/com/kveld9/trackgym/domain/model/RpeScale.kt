package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

data class RpeOption(
    val rpe: Double?,
    val rirLabel: String,
    @get:StringRes val descRes: Int
)

object RpeScale {
    val options: List<RpeOption> = listOf(
        RpeOption(10.0, "0", R.string.rpe_desc_10),
        RpeOption(9.5, "0-1", R.string.rpe_desc_9_5),
        RpeOption(9.0, "1", R.string.rpe_desc_9),
        RpeOption(8.5, "1-2", R.string.rpe_desc_8_5),
        RpeOption(8.0, "2", R.string.rpe_desc_8),
        RpeOption(7.5, "2-3", R.string.rpe_desc_7_5),
        RpeOption(7.0, "3", R.string.rpe_desc_7),
        RpeOption(6.5, "3-4", R.string.rpe_desc_6_5),
        RpeOption(6.0, "4+", R.string.rpe_desc_6)
    )

    fun formatRpe(rpe: Double?): String? {
        if (rpe == null) return null
        return if (rpe % 1.0 == 0.0) {
            String.format(java.util.Locale.US, "%.0f", rpe)
        } else {
            String.format(java.util.Locale.US, "%.1f", rpe)
        }
    }
}
