package com.kveld9.trackgym.domain.calculator

import kotlin.math.roundToInt

data class RpeTargetWeightResult(
    val estimated1RmKg: Double,
    val targetReps: Int,
    val targetRpe: Double,
    val percentage: Double,
    val theoreticalWeightKg: Double,
    val suggestedWeightKg: Double
)

/**
 * Mathematical engine for calculating target training loads from prescribed reps and RPE
 * based on Mike Tuchscherer's Reactive Training Systems (RTS) / OpenPowerlifting %1RM matrix.
 */
object RpeTargetWeightCalculator {

    // Mike Tuchscherer / RTS %1RM matrix for reps 1-12 and RPE 10.0 to 6.0
    private val RTS_PERCENTAGE_TABLE: Map<Pair<Int, Int>, Double> = mapOf(
        // Rep 1
        Pair(1, 100) to 1.000,
        Pair(1, 95) to 0.978,
        Pair(1, 90) to 0.955,
        Pair(1, 85) to 0.939,
        Pair(1, 80) to 0.922,
        Pair(1, 75) to 0.907,
        Pair(1, 70) to 0.892,
        Pair(1, 65) to 0.878,
        Pair(1, 60) to 0.863,

        // Rep 2
        Pair(2, 100) to 0.955,
        Pair(2, 95) to 0.939,
        Pair(2, 90) to 0.922,
        Pair(2, 85) to 0.907,
        Pair(2, 80) to 0.892,
        Pair(2, 75) to 0.878,
        Pair(2, 70) to 0.863,
        Pair(2, 65) to 0.849,
        Pair(2, 60) to 0.834,

        // Rep 3
        Pair(3, 100) to 0.922,
        Pair(3, 95) to 0.907,
        Pair(3, 90) to 0.892,
        Pair(3, 85) to 0.878,
        Pair(3, 80) to 0.863,
        Pair(3, 75) to 0.849,
        Pair(3, 70) to 0.834,
        Pair(3, 65) to 0.819,
        Pair(3, 60) to 0.804,

        // Rep 4
        Pair(4, 100) to 0.892,
        Pair(4, 95) to 0.878,
        Pair(4, 90) to 0.863,
        Pair(4, 85) to 0.849,
        Pair(4, 80) to 0.834,
        Pair(4, 75) to 0.819,
        Pair(4, 70) to 0.804,
        Pair(4, 65) to 0.788,
        Pair(4, 60) to 0.772,

        // Rep 5
        Pair(5, 100) to 0.863,
        Pair(5, 95) to 0.849,
        Pair(5, 90) to 0.834,
        Pair(5, 85) to 0.819,
        Pair(5, 80) to 0.811,
        Pair(5, 75) to 0.788,
        Pair(5, 70) to 0.772,
        Pair(5, 65) to 0.757,
        Pair(5, 60) to 0.741,

        // Rep 6
        Pair(6, 100) to 0.837,
        Pair(6, 95) to 0.824,
        Pair(6, 90) to 0.811,
        Pair(6, 85) to 0.799,
        Pair(6, 80) to 0.786,
        Pair(6, 75) to 0.772,
        Pair(6, 70) to 0.757,
        Pair(6, 65) to 0.741,
        Pair(6, 60) to 0.723,

        // Rep 7
        Pair(7, 100) to 0.811,
        Pair(7, 95) to 0.799,
        Pair(7, 90) to 0.786,
        Pair(7, 85) to 0.774,
        Pair(7, 80) to 0.762,
        Pair(7, 75) to 0.751,
        Pair(7, 70) to 0.739,
        Pair(7, 65) to 0.723,
        Pair(7, 60) to 0.707,

        // Rep 8
        Pair(8, 100) to 0.786,
        Pair(8, 95) to 0.774,
        Pair(8, 90) to 0.762,
        Pair(8, 85) to 0.751,
        Pair(8, 80) to 0.739,
        Pair(8, 75) to 0.723,
        Pair(8, 70) to 0.707,
        Pair(8, 65) to 0.694,
        Pair(8, 60) to 0.680,

        // Rep 9
        Pair(9, 100) to 0.762,
        Pair(9, 95) to 0.751,
        Pair(9, 90) to 0.739,
        Pair(9, 85) to 0.723,
        Pair(9, 80) to 0.707,
        Pair(9, 75) to 0.694,
        Pair(9, 70) to 0.680,
        Pair(9, 65) to 0.667,
        Pair(9, 60) to 0.653,

        // Rep 10
        Pair(10, 100) to 0.739,
        Pair(10, 95) to 0.723,
        Pair(10, 90) to 0.707,
        Pair(10, 85) to 0.694,
        Pair(10, 80) to 0.680,
        Pair(10, 75) to 0.667,
        Pair(10, 70) to 0.653,
        Pair(10, 65) to 0.640,
        Pair(10, 60) to 0.626,

        // Rep 11
        Pair(11, 100) to 0.707,
        Pair(11, 95) to 0.694,
        Pair(11, 90) to 0.680,
        Pair(11, 85) to 0.667,
        Pair(11, 80) to 0.653,
        Pair(11, 75) to 0.640,
        Pair(11, 70) to 0.626,
        Pair(11, 65) to 0.613,
        Pair(11, 60) to 0.599,

        // Rep 12
        Pair(12, 100) to 0.680,
        Pair(12, 95) to 0.667,
        Pair(12, 90) to 0.653,
        Pair(12, 85) to 0.640,
        Pair(12, 80) to 0.626,
        Pair(12, 75) to 0.613,
        Pair(12, 70) to 0.599,
        Pair(12, 65) to 0.586,
        Pair(12, 60) to 0.573
    )

    /**
     * Resolves the % of 1RM for a target repetition count and RPE.
     */
    fun getPercentageOf1RM(reps: Int, rpe: Double): Double {
        if (reps <= 0) return 0.0
        val clampedRpe = rpe.coerceIn(5.0, 10.0)
        val rpeKey = (kotlin.math.round(clampedRpe * 10.0)).toInt()

        val tableValue = RTS_PERCENTAGE_TABLE[Pair(reps, rpeKey)]
        if (tableValue != null) {
            return tableValue
        }

        // Mathematical model extrapolation: effective reps = reps + (10 - RPE)
        val rir = (10.0 - clampedRpe).coerceAtLeast(0.0)
        val effectiveReps = reps + rir
        val raw = 1.0 / (1.0 + (effectiveReps - 1.0) / 30.0)
        return (raw * 1000.0).roundToInt() / 1000.0
    }

    /**
     * Calculates theoretical and suggested load in kg given an estimated 1RM, target reps, and RPE.
     */
    fun calculateTargetWeight(
        estimated1RmKg: Double,
        targetReps: Int,
        targetRpe: Double,
        roundingStepKg: Double = 2.5
    ): RpeTargetWeightResult {
        if (estimated1RmKg <= 0.0 || targetReps <= 0) {
            return RpeTargetWeightResult(
                estimated1RmKg = estimated1RmKg,
                targetReps = targetReps,
                targetRpe = targetRpe,
                percentage = 0.0,
                theoreticalWeightKg = 0.0,
                suggestedWeightKg = 0.0
            )
        }

        val percentage = getPercentageOf1RM(targetReps, targetRpe)
        val exact = estimated1RmKg * percentage
        val theoretical = (exact * 10.0).roundToInt() / 10.0

        val suggested = if (roundingStepKg > 0.0) {
            (theoretical / roundingStepKg).roundToInt() * roundingStepKg
        } else {
            theoretical
        }

        return RpeTargetWeightResult(
            estimated1RmKg = estimated1RmKg,
            targetReps = targetReps,
            targetRpe = targetRpe,
            percentage = percentage,
            theoreticalWeightKg = theoretical,
            suggestedWeightKg = (suggested * 10.0).roundToInt() / 10.0
        )
    }
}
