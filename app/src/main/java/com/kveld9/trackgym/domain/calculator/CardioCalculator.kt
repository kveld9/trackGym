package com.kveld9.trackgym.domain.calculator

import java.util.Locale
import kotlin.math.roundToInt

/**
 * Domain calculator for cardiovascular metrics:
 * - Pace (min/km and min/mile)
 * - Metabolic Energy Expenditure (Calories in kcal based on METs, duration and body weight)
 */
object CardioCalculator {

    /**
     * Default MET (Metabolic Equivalent of Task) for general moderate-to-vigorous cardio activities:
     * - Running / Jogging: ~7.0 - 10.0
     * - Cycling: ~6.0 - 8.0
     * - Rowing machine: ~7.0
     * - Jump rope: ~10.0
     * General aerobic default: 7.0 METs
     */
    const val DEFAULT_CARDIO_MET = 7.0
    const val DEFAULT_FALLBACK_BODYWEIGHT_KG = 70.0

    /**
     * Calculates pace in seconds per unit (km or mi) given total duration in seconds and distance in km.
     * Returns null if distance is non-positive or duration is non-positive.
     */
    fun calculatePaceSecondsPerUnit(durationSeconds: Int, distanceKm: Double, distanceUnit: com.kveld9.trackgym.domain.model.DistanceUnit = com.kveld9.trackgym.domain.model.DistanceUnit.KM): Int? {
        if (durationSeconds <= 0 || distanceKm <= 0.0) return null
        val distanceInUnits = distanceUnit.fromKm(distanceKm)
        if (distanceInUnits <= 0.0) return null
        return (durationSeconds / distanceInUnits).roundToInt()
    }

    /**
     * Formats pace as "M:SS /km" or "M:SS /mi" (e.g., "5:30 /km").
     */
    fun formatPace(
        distanceKm: Double,
        durationSeconds: Int,
        distanceUnit: com.kveld9.trackgym.domain.model.DistanceUnit = com.kveld9.trackgym.domain.model.DistanceUnit.KM
    ): String {
        val paceSec = calculatePaceSecondsPerUnit(durationSeconds, distanceKm, distanceUnit)
        if (paceSec == null || paceSec <= 0) return "--:-- /${distanceUnit.symbol}"
        val minutes = paceSec / 60
        val seconds = paceSec % 60
        return String.format(Locale.US, "%d:%02d /%s", minutes, seconds, distanceUnit.symbol)
    }

    /**
     * Formats pace in seconds per km as "M:SS /km" (e.g., "5:30 /km").
     */
    fun formatPace(paceSecondsPerKm: Int?): String {
        if (paceSecondsPerKm == null || paceSecondsPerKm <= 0) return "--:-- /km"
        val minutes = paceSecondsPerKm / 60
        val seconds = paceSecondsPerKm % 60
        return String.format(Locale.US, "%d:%02d /km", minutes, seconds)
    }

    /**
     * Calculates estimated calories burned using standard ACSM metabolic equation:
     * Calories (kcal) = MET × 3.5 × weightKg / 200 × durationMinutes
     *
     * @param durationSeconds Total exercise duration in seconds
     * @param bodyWeightKg User bodyweight in kg (falls back to 70.0 kg if <= 0)
     * @param met Metabolic Equivalent of Task (defaults to 7.0)
     */
    fun calculateCaloriesBurned(
        durationSeconds: Int,
        bodyWeightKg: Double = DEFAULT_FALLBACK_BODYWEIGHT_KG,
        met: Double = DEFAULT_CARDIO_MET
    ): Int {
        if (durationSeconds <= 0) return 0
        val effectiveWeight = if (bodyWeightKg > 0.0) bodyWeightKg else DEFAULT_FALLBACK_BODYWEIGHT_KG
        val durationMinutes = durationSeconds / 60.0
        val calories = met * 3.5 * effectiveWeight / 200.0 * durationMinutes
        return calories.roundToInt().coerceAtLeast(0)
    }
}
