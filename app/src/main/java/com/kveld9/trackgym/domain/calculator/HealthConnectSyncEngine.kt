package com.kveld9.trackgym.domain.calculator

import kotlin.math.max

object HealthConnectSyncEngine {

    // Standard metabolic equivalent of task for resistance training (calisthenics/weight training)
    // Compendium of Physical Activities: Resistance training, multiple exercises: ~5.0 - 6.0 METs
    const val RESISTANCE_TRAINING_MET = 5.5

    /**
     * Calculates estimated energy burned in kilocalories using the ACSM equation:
     * Calories (kcal) = METs * weightKg * (durationMinutes / 60.0)
     */
    fun estimateCaloriesBurned(
        durationMinutes: Long,
        userBodyWeightKg: Double
    ): Double {
        if (durationMinutes <= 0 || userBodyWeightKg <= 0.0) return 0.0
        val durationHours = durationMinutes / 60.0
        val calories = RESISTANCE_TRAINING_MET * userBodyWeightKg * durationHours
        return max(0.0, calories)
    }

    /**
     * Ensures duration in milliseconds is bounded and consistent.
     */
    fun calculateDurationMinutes(startedAt: Long, completedAt: Long): Long {
        if (completedAt <= startedAt) return 0L
        val diffMs = completedAt - startedAt
        return diffMs / (1000L * 60L)
    }
}
