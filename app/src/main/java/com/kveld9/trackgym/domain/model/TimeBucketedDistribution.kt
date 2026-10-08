package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Predefined time window buckets for analyzing stimulus distribution.
 */
enum class DistributionTimeBucket(@get:StringRes val labelRes: Int) {
    LAST_WEEK(R.string.time_bucket_last_week),
    LAST_MONTH(R.string.time_bucket_last_month),
    LAST_YEAR(R.string.time_bucket_last_year),
    ALL_TIME(R.string.time_bucket_all_time)
}

/**
 * Metric type used to evaluate stimulus across time buckets.
 */
enum class DistributionMetricType(@get:StringRes val labelRes: Int) {
    SETS(R.string.metric_type_sets),
    VOLUME(R.string.metric_type_volume),
    REPS(R.string.metric_type_reps)
}

/**
 * Item in the muscle group distribution.
 */
data class MuscleDistributionItem(
    val muscleGroup: MuscleGroup,
    val value: Double,
    val percentage: Float
)

/**
 * Item in the exercise ranking distribution.
 */
data class ExerciseDistributionItem(
    val exerciseId: Long,
    val exerciseName: String,
    val muscleGroup: MuscleGroup,
    val value: Double,
    val percentage: Float
)

/**
 * Complete distribution analysis result for a given time window.
 */
data class TimeBucketedDistribution(
    val bucket: DistributionTimeBucket,
    val metricType: DistributionMetricType,
    val totalValue: Double,
    val totalWorkouts: Int,
    val muscleDistributions: List<MuscleDistributionItem>,
    val topExercises: List<ExerciseDistributionItem>
)
