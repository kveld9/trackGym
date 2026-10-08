package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Volume status relative to evidence-based hypertrophy thresholds (e.g. Schoenfeld, Israetel).
 * Low / Maintenance: < 10 weekly sets
 * Optimal Hypertrophy (MEV - MAV): 10 to 20 weekly sets
 * High / Overreaching (MRV): > 20 weekly sets
 */
enum class HypertrophyVolumeStatus(@get:StringRes val labelRes: Int) {
    BELOW_OPTIMAL(R.string.hypertrophy_status_below),
    OPTIMAL(R.string.hypertrophy_status_optimal),
    EXCESSIVE(R.string.hypertrophy_status_excessive)
}

/**
 * Muscle group weekly set volume evaluated against hypertrophy thresholds.
 */
data class MuscleHypertrophyVolume(
    val muscleGroup: MuscleGroup,
    val weeklySets: Int,
    val minThreshold: Int = 10,
    val maxThreshold: Int = 20,
    val status: HypertrophyVolumeStatus = when {
        weeklySets < minThreshold -> HypertrophyVolumeStatus.BELOW_OPTIMAL
        weeklySets in minThreshold..maxThreshold -> HypertrophyVolumeStatus.OPTIMAL
        else -> HypertrophyVolumeStatus.EXCESSIVE
    }
) {
    val progressToMin: Float
        get() = if (minThreshold > 0) (weeklySets.toFloat() / minThreshold.toFloat()).coerceIn(0f, 1f) else 1f

    val progressWithinRange: Float
        get() = when {
            weeklySets <= 0 -> 0f
            weeklySets >= maxThreshold -> 1f
            else -> (weeklySets.toFloat() / maxThreshold.toFloat()).coerceIn(0f, 1f)
        }
}
