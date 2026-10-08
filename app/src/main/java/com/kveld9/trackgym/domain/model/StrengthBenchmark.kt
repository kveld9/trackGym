package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Biological sex classification used for normative strength benchmark baselines.
 */
enum class BiologicalSex(@get:StringRes val displayNameRes: Int) {
    MALE(R.string.strength_sex_male),
    FEMALE(R.string.strength_sex_female)
}

/**
 * Standard strength tiers based on bodyweight multiplier standards (e.g. ExRx / Dr. Lon Kilgore standards).
 */
enum class StrengthLevel(
    @get:StringRes val displayNameRes: Int,
    val minPercentile: Double,
    val maxPercentile: Double
) {
    BEGINNER(R.string.strength_level_beginner, 0.0, 20.0),
    NOVICE(R.string.strength_level_novice, 20.0, 50.0),
    INTERMEDIATE(R.string.strength_level_intermediate, 50.0, 80.0),
    ADVANCED(R.string.strength_level_advanced, 80.0, 95.0),
    ELITE(R.string.strength_level_elite, 95.0, 100.0)
}

/**
 * Primary core compound lifts evaluated for strength benchmarks.
 */
enum class CoreLift(
    @get:StringRes val displayNameRes: Int,
    val keywords: List<String>
) {
    BENCH_PRESS(
        R.string.core_lift_bench_press,
        listOf("bench press", "press banca", "press de banca", "flat barbell bench press")
    ),
    SQUAT(
        R.string.core_lift_squat,
        listOf("squat", "sentadilla", "back squat", "barbell back squat")
    ),
    DEADLIFT(
        R.string.core_lift_deadlift,
        listOf("deadlift", "peso muerto", "conventional deadlift", "sumo deadlift")
    ),
    OVERHEAD_PRESS(
        R.string.core_lift_overhead_press,
        listOf("overhead press", "press militar", "overhead barbell press", "shoulder press barbell", "military press")
    )
}

/**
 * Evaluated strength benchmark result for a specific core lift.
 */
data class StrengthBenchmarkResult(
    val lift: CoreLift,
    val exerciseName: String,
    val user1RMKg: Double,
    val bodyWeightKg: Double,
    val ratio: Double,
    val level: StrengthLevel,
    val percentile: Double,
    val nextLevelTargetKg: Double?,
    val hasPersonalRecord: Boolean
)
