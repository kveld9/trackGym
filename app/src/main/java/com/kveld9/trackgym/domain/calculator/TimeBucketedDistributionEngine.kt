package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DistributionMetricType
import com.kveld9.trackgym.domain.model.DistributionTimeBucket
import com.kveld9.trackgym.domain.model.ExerciseDistributionItem
import com.kveld9.trackgym.domain.model.MuscleDistributionItem
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.TimeBucketedDistribution
import com.kveld9.trackgym.domain.model.Workout
import java.util.Calendar

/**
 * Engine that computes muscle group and exercise distribution across overlapping
 * time buckets (Last Week, Last Month, Last Year, All-Time) by metric (Sets, Volume, Reps).
 */
object TimeBucketedDistributionEngine {

    fun calculateDistribution(
        workouts: List<Workout>,
        bucket: DistributionTimeBucket,
        metricType: DistributionMetricType = DistributionMetricType.SETS,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): TimeBucketedDistribution {
        val filtered = filterWorkoutsForBucket(workouts, bucket, referenceTimestamp)

        val muscleValues = mutableMapOf<MuscleGroup, Double>()
        val exerciseValues = mutableMapOf<Pair<Long, String>, Pair<MuscleGroup, Double>>()
        var grandTotal = 0.0

        for (w in filtered) {
            for (we in w.exercises) {
                val exercise = we.exercise
                val completedSets = we.sets.filter { it.isCompleted }
                val setsToEvaluate = if (completedSets.isNotEmpty()) completedSets else we.sets

                val valForExercise = when (metricType) {
                    DistributionMetricType.SETS -> setsToEvaluate.size.toDouble()
                    DistributionMetricType.VOLUME -> setsToEvaluate.sumOf { (it.weightKg ?: 0.0) * it.reps }
                    DistributionMetricType.REPS -> setsToEvaluate.sumOf { it.reps }.toDouble()
                }

                if (valForExercise <= 0.0) continue

                grandTotal += valForExercise

                val currentMuscleVal = muscleValues[exercise.muscleGroup] ?: 0.0
                muscleValues[exercise.muscleGroup] = currentMuscleVal + valForExercise

                val exKey = exercise.id to exercise.name
                val existing = exerciseValues[exKey]
                val currentExVal = existing?.second ?: 0.0
                exerciseValues[exKey] = exercise.muscleGroup to (currentExVal + valForExercise)
            }
        }

        val muscleDistributions = muscleValues.map { (group, value) ->
            val percentage = if (grandTotal > 0.0) (value / grandTotal).toFloat() else 0f
            MuscleDistributionItem(muscleGroup = group, value = value, percentage = percentage)
        }.sortedByDescending { it.value }

        val topExercises = exerciseValues.map { (key, groupAndVal) ->
            val (group, value) = groupAndVal
            val percentage = if (grandTotal > 0.0) (value / grandTotal).toFloat() else 0f
            ExerciseDistributionItem(
                exerciseId = key.first,
                exerciseName = key.second,
                muscleGroup = group,
                value = value,
                percentage = percentage
            )
        }.sortedByDescending { it.value }

        return TimeBucketedDistribution(
            bucket = bucket,
            metricType = metricType,
            totalValue = grandTotal,
            totalWorkouts = filtered.size,
            muscleDistributions = muscleDistributions,
            topExercises = topExercises
        )
    }

    fun filterWorkoutsForBucket(
        workouts: List<Workout>,
        bucket: DistributionTimeBucket,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): List<Workout> {
        val completedOnly = workouts.filter { it.isCompleted }
        if (bucket == DistributionTimeBucket.ALL_TIME) {
            return completedOnly
        }

        val cutoffCal = Calendar.getInstance().apply {
            timeInMillis = referenceTimestamp
            when (bucket) {
                DistributionTimeBucket.LAST_WEEK -> add(Calendar.DAY_OF_YEAR, -7)
                DistributionTimeBucket.LAST_MONTH -> add(Calendar.DAY_OF_YEAR, -30)
                DistributionTimeBucket.LAST_YEAR -> add(Calendar.DAY_OF_YEAR, -365)
                DistributionTimeBucket.ALL_TIME -> {}
            }
        }
        val cutoff = cutoffCal.timeInMillis

        return completedOnly.filter { w ->
            val ts = w.completedAt ?: w.startedAt
            ts >= cutoff
        }
    }
}
