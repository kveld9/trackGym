package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import java.util.Locale

/**
 * Supported weight units: Kilograms (KG) and Pounds (LB).
 * 1 kg ≈ 2.20462262185 lbs.
 */
enum class WeightUnit(@get:StringRes val labelRes: Int, val symbol: String) {
    KG(R.string.unit_kg, "kg"),
    LB(R.string.unit_lb, "lb");

    fun fromKg(kg: Double): Double = when (this) {
        KG -> kg
        LB -> kg * KG_TO_LB
    }

    fun toKg(value: Double): Double = when (this) {
        KG -> value
        LB -> value / KG_TO_LB
    }

    fun format(kg: Double, decimals: Int = 1): String {
        val converted = fromKg(kg)
        return if (converted % 1.0 == 0.0) {
            "${converted.toInt()} $symbol"
        } else {
            String.format(Locale.US, "%.${decimals}f %s", converted, symbol)
        }
    }

    fun formatValue(kg: Double, decimals: Int = 1): String {
        val converted = fromKg(kg)
        return if (converted % 1.0 == 0.0) {
            "${converted.toInt()}"
        } else {
            String.format(Locale.US, "%.${decimals}f", converted)
        }
    }

    companion object {
        const val KG_TO_LB = 2.20462262185

        fun fromString(value: String): WeightUnit {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.symbol.equals(value, ignoreCase = true)
            } ?: KG
        }
    }
}

/**
 * Supported distance units: Kilometers (KM) and Miles (MI).
 * 1 km ≈ 0.621371192 mi.
 */
enum class DistanceUnit(@get:StringRes val labelRes: Int, val symbol: String) {
    KM(R.string.unit_km, "km"),
    MI(R.string.unit_mi, "mi");

    fun fromKm(km: Double): Double = when (this) {
        KM -> km
        MI -> km * KM_TO_MI
    }

    fun toKm(value: Double): Double = when (this) {
        KM -> value
        MI -> value / KM_TO_MI
    }

    fun format(km: Double, decimals: Int = 2): String {
        val converted = fromKm(km)
        return String.format(Locale.US, "%.${decimals}f %s", converted, symbol)
    }

    companion object {
        const val KM_TO_MI = 0.621371192

        fun fromString(value: String): DistanceUnit {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.symbol.equals(value, ignoreCase = true)
            } ?: KM
        }
    }
}
