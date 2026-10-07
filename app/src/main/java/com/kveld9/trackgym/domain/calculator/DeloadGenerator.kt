package com.kveld9.trackgym.domain.calculator

import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Calculates volume and load deload reductions to dissipate CNS and joint fatigue.
 */
object DeloadGenerator {

    const val DEFAULT_LOAD_REDUCTION_PCT = 15.0
    const val DEFAULT_VOLUME_REDUCTION_PCT = 50.0

    /**
     * Calculates prescribed working sets for a deload cycle.
     * Typically cuts effective sets in half (e.g. 50% volume).
     */
    fun calculateDeloadSets(baseSets: Int, volumeReductionPct: Double = DEFAULT_VOLUME_REDUCTION_PCT): Int {
        if (baseSets <= 0) return 1
        val multiplier = (1.0 - (volumeReductionPct / 100.0)).coerceIn(0.2, 0.9)
        val calculated = (baseSets * multiplier).roundToInt()
        return max(1, calculated)
    }

    /**
     * Calculates prescribed deload weight, typically reduced by 15-20% and rounded
     * to the nearest barbell/dumbbell increment (e.g. 0.5 kg).
     */
    fun calculateDeloadWeight(
        baseWeightKg: Double,
        loadReductionPct: Double = DEFAULT_LOAD_REDUCTION_PCT,
        stepKg: Double = 0.5
    ): Double {
        if (baseWeightKg <= 0.0) return 0.0
        val multiplier = (1.0 - (loadReductionPct / 100.0)).coerceIn(0.4, 0.95)
        val raw = baseWeightKg * multiplier
        val step = if (stepKg <= 0.0) 0.5 else stepKg
        val rounded = (kotlin.math.round(raw / step) * step)
        return max(0.0, rounded)
    }
}
