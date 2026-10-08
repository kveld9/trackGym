package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BiologicalSex
import com.kveld9.trackgym.domain.model.CoreLift
import com.kveld9.trackgym.domain.model.StrengthBenchmarkResult
import com.kveld9.trackgym.domain.model.StrengthLevel
import kotlin.math.roundToInt

/**
 * Normative strength standards engine based on population data (ExRx / Kilgore strength standards).
 * Evaluates core lift 1RMs relative to bodyweight, biological sex, and optional age attenuation.
 */
object StrengthBenchmarkEngine {

    /**
     * Bodyweight multiplier standards per lift and sex:
     * Levels: [BEGINNER, NOVICE, INTERMEDIATE, ADVANCED, ELITE]
     */
    private val STANDARDS_MALE = mapOf(
        CoreLift.BENCH_PRESS to doubleArrayOf(0.50, 0.75, 1.00, 1.50, 1.90),
        CoreLift.SQUAT to doubleArrayOf(0.75, 1.10, 1.40, 1.90, 2.40),
        CoreLift.DEADLIFT to doubleArrayOf(0.90, 1.30, 1.65, 2.20, 2.80),
        CoreLift.OVERHEAD_PRESS to doubleArrayOf(0.35, 0.50, 0.65, 0.90, 1.15)
    )

    private val STANDARDS_FEMALE = mapOf(
        CoreLift.BENCH_PRESS to doubleArrayOf(0.30, 0.50, 0.70, 0.95, 1.25),
        CoreLift.SQUAT to doubleArrayOf(0.50, 0.80, 1.00, 1.40, 1.75),
        CoreLift.DEADLIFT to doubleArrayOf(0.60, 0.95, 1.20, 1.60, 2.05),
        CoreLift.OVERHEAD_PRESS to doubleArrayOf(0.20, 0.35, 0.45, 0.60, 0.80)
    )

    /**
     * Detects if an exercise corresponds to one of the CoreLifts by name matching.
     */
    fun matchCoreLift(exerciseName: String): CoreLift? {
        val lower = exerciseName.lowercase().trim()
        // Priority checking:
        // Distinguish overhead press vs bench press
        if (lower.contains("overhead") || lower.contains("militar") || lower.contains("military")) {
            return CoreLift.OVERHEAD_PRESS
        }
        if (lower.contains("bench") || lower.contains("banca")) {
            return CoreLift.BENCH_PRESS
        }
        if (lower.contains("deadlift") || lower.contains("peso muerto")) {
            return CoreLift.DEADLIFT
        }
        if (lower.contains("squat") || lower.contains("sentadilla")) {
            // Avoid Bulgarian split squat or bodyweight variations if needed, but primary barbell squat matches
            return CoreLift.SQUAT
        }
        return null
    }

    /**
     * Calculates age coefficient according to masters weightlifting decay factors.
     * Peak strength is standard between 20-39 years old (factor 1.0).
     */
    fun getAgeFactor(age: Int): Double {
        if (age <= 0) return 1.0
        return when {
            age < 16 -> 0.85
            age < 18 -> 0.92
            age <= 39 -> 1.00
            age <= 49 -> 1.0 - (age - 39) * 0.008 // ~0.92 at 49
            age <= 59 -> 0.92 - (age - 49) * 0.010 // ~0.82 at 59
            age <= 69 -> 0.82 - (age - 59) * 0.012 // ~0.70 at 69
            else -> (0.70 - (age - 69) * 0.015).coerceAtLeast(0.50)
        }
    }

    /**
     * Returns standards array for the specified lift, sex, and age.
     */
    fun getThresholds(lift: CoreLift, sex: BiologicalSex, age: Int = 30): DoubleArray {
        val base = (if (sex == BiologicalSex.MALE) STANDARDS_MALE[lift] else STANDARDS_FEMALE[lift])
            ?: doubleArrayOf(0.5, 0.75, 1.0, 1.5, 2.0)
        val ageFactor = getAgeFactor(age)
        return DoubleArray(base.size) { i -> base[i] * ageFactor }
    }

    /**
     * Evaluates strength level and percentile based on lift, 1RM (kg), bodyweight (kg), sex, and age.
     */
    fun evaluate(
        lift: CoreLift,
        exerciseName: String,
        user1RMKg: Double,
        bodyWeightKg: Double,
        sex: BiologicalSex = BiologicalSex.MALE,
        age: Int = 30
    ): StrengthBenchmarkResult {
        val validBw = if (bodyWeightKg > 10.0) bodyWeightKg else 75.0
        val valid1RM = user1RMKg.coerceAtLeast(0.0)
        val ratio = valid1RM / validBw

        val thresholds = getThresholds(lift, sex, age)
        // thresholds: [0: beginner, 1: novice, 2: intermediate, 3: advanced, 4: elite]

        val level: StrengthLevel
        val percentile: Double
        val nextTargetKg: Double?

        when {
            ratio < thresholds[0] -> {
                level = StrengthLevel.BEGINNER
                percentile = ((ratio / thresholds[0]) * 20.0).coerceIn(1.0, 19.9)
                nextTargetKg = thresholds[0] * validBw
            }
            ratio < thresholds[1] -> {
                level = StrengthLevel.BEGINNER
                val span = thresholds[1] - thresholds[0]
                val fraction = if (span > 0) (ratio - thresholds[0]) / span else 0.0
                percentile = (20.0 + fraction * 10.0).coerceIn(20.0, 29.9)
                nextTargetKg = thresholds[1] * validBw
            }
            ratio < thresholds[2] -> {
                level = StrengthLevel.NOVICE
                val span = thresholds[2] - thresholds[1]
                val fraction = if (span > 0) (ratio - thresholds[1]) / span else 0.0
                percentile = (30.0 + fraction * 25.0).coerceIn(30.0, 54.9)
                nextTargetKg = thresholds[2] * validBw
            }
            ratio < thresholds[3] -> {
                level = StrengthLevel.INTERMEDIATE
                val span = thresholds[3] - thresholds[2]
                val fraction = if (span > 0) (ratio - thresholds[2]) / span else 0.0
                percentile = (55.0 + fraction * 30.0).coerceIn(55.0, 84.9)
                nextTargetKg = thresholds[3] * validBw
            }
            ratio < thresholds[4] -> {
                level = StrengthLevel.ADVANCED
                val span = thresholds[4] - thresholds[3]
                val fraction = if (span > 0) (ratio - thresholds[3]) / span else 0.0
                percentile = (85.0 + fraction * 10.0).coerceIn(85.0, 94.9)
                nextTargetKg = thresholds[4] * validBw
            }
            else -> {
                level = StrengthLevel.ELITE
                val excess = (ratio - thresholds[4]) / (thresholds[4] * 0.25)
                percentile = (95.0 + excess * 4.9).coerceIn(95.0, 99.9)
                nextTargetKg = null
            }
        }

        val roundedRatio = (ratio * 100.0).roundToInt() / 100.0
        val roundedPercentile = (percentile * 10.0).roundToInt() / 10.0
        val roundedTarget = nextTargetKg?.let { (it * 10.0).roundToInt() / 10.0 }

        return StrengthBenchmarkResult(
            lift = lift,
            exerciseName = exerciseName,
            user1RMKg = valid1RM,
            bodyWeightKg = validBw,
            ratio = roundedRatio,
            level = level,
            percentile = roundedPercentile,
            nextLevelTargetKg = roundedTarget,
            hasPersonalRecord = valid1RM > 0.0
        )
    }
}
