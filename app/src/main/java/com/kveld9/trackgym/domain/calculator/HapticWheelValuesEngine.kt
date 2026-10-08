package com.kveld9.trackgym.domain.calculator

import java.util.Locale
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

/**
 * Engine for decomposing, formatting, and indexing numerical values for haptic wheel scroll pickers.
 */
object HapticWheelValuesEngine {

    val DEFAULT_FRACTIONS = listOf(0.0, 0.25, 0.5, 0.75)
    val HALF_FRACTIONS = listOf(0.0, 0.5)

    const val MAX_WEIGHT_WHOLE = 400
    const val MAX_REPS = 100

    data class SplitWeight(
        val whole: Int,
        val fraction: Double
    ) {
        val total: Double get() = round((whole + fraction) * 100.0) / 100.0
    }

    /**
     * Splits a decimal weight into whole integer part and closest available fraction.
     */
    fun splitWeight(
        weight: Double,
        availableFractions: List<Double> = DEFAULT_FRACTIONS
    ): SplitWeight {
        if (!weight.isFinite() || weight <= 0.0) return SplitWeight(0, 0.0)
        val safeWeight = weight.coerceAtMost(MAX_WEIGHT_WHOLE.toDouble())
        val wholePart = floor(safeWeight).toInt()
        val remainder = round((safeWeight - wholePart) * 1000.0) / 1000.0

        val closestFraction = availableFractions.minByOrNull { abs(it - remainder) } ?: 0.0
        return SplitWeight(whole = wholePart, fraction = closestFraction)
    }

    /**
     * Combines whole and fractional parts into a precise double value.
     */
    fun combineWeight(whole: Int, fraction: Double): Double {
        val safeWhole = whole.coerceAtLeast(0)
        val safeFraction = if (fraction.isFinite() && fraction >= 0.0) fraction else 0.0
        val combined = safeWhole + safeFraction
        return round(combined * 100.0) / 100.0
    }

    /**
     * Formats a decimal fraction for wheel presentation (e.g. .00, .25, .50, .75).
     */
    fun formatFractionDisplay(fraction: Double): String {
        return when {
            fraction <= 0.001 -> ".00"
            abs(fraction - 0.25) < 0.01 -> ".25"
            abs(fraction - 0.5) < 0.01 -> ".50"
            abs(fraction - 0.75) < 0.01 -> ".75"
            else -> String.format(Locale.US, ".%02d", (fraction * 100).toInt())
        }
    }

    /**
     * Finds the index of an item in the list or returns 0 as fallback.
     */
    fun <T> findClosestIndex(items: List<T>, target: T): Int {
        val idx = items.indexOf(target)
        return if (idx >= 0) idx else 0
    }
}
