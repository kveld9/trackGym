package com.kveld9.trackgym.domain.calculator

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.WeightUnit

/**
 * Predefined barbell profiles for quick selection.
 */
enum class BarbellProfile(
    @get:StringRes val nameRes: Int,
    val weightKg: Double,
    val weightLb: Double
) {
    OLYMPIC(R.string.bar_olympic, 20.0, 45.0),
    WOMEN(R.string.bar_women, 15.0, 35.0),
    EZ_CURL(R.string.bar_ez, 10.0, 25.0),
    TRAP_HEX(R.string.bar_trap_hex, 25.0, 55.0),
    SMITH(R.string.bar_smith, 0.0, 0.0);

    fun weight(unit: WeightUnit): Double = when (unit) {
        WeightUnit.KG -> weightKg
        WeightUnit.LB -> weightLb
    }
}

/**
 * Plate breakdown for one side of a barbell.
 */
data class PlateCount(
    val weight: Double,
    val count: Int
)

data class PlateCalculationResult(
    val targetWeight: Double,
    val barWeight: Double,
    val collarsWeight: Double = 0.0,
    val weightPerSide: Double,
    val platesPerSide: List<PlateCount>,
    val remainderPerSide: Double,
    val totalAchievableWeight: Double
)

/**
 * Calculates the required weight plates per side for a given target barbell weight.
 */
object PlateCalculator {

    val DEFAULT_KG_PLATES = listOf(25.0, 20.0, 15.0, 10.0, 5.0, 2.5, 1.25)
    val DEFAULT_LB_PLATES = listOf(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)

    const val DEFAULT_BAR_KG = 20.0
    const val DEFAULT_BAR_LB = 45.0

    const val DEFAULT_COLLARS_KG = 0.5
    const val DEFAULT_COLLARS_LB = 1.0

    /**
     * Calculates plate configuration for one side of the barbell.
     */
    fun calculatePlates(
        targetWeight: Double,
        barWeight: Double = DEFAULT_BAR_KG,
        collarsWeight: Double = 0.0,
        availablePlates: List<Double> = DEFAULT_KG_PLATES
    ): PlateCalculationResult {
        val totalBase = barWeight + collarsWeight
        if (targetWeight <= totalBase) {
            return PlateCalculationResult(
                targetWeight = targetWeight,
                barWeight = barWeight,
                collarsWeight = collarsWeight,
                weightPerSide = 0.0,
                platesPerSide = emptyList(),
                remainderPerSide = 0.0,
                totalAchievableWeight = totalBase
            )
        }

        val sortedPlates = availablePlates.filter { it > 0.0 }.sortedDescending()
        val targetWeightPerSide = (targetWeight - totalBase) / 2.0
        var remaining = targetWeightPerSide

        val platesResult = mutableListOf<PlateCount>()

        for (plate in sortedPlates) {
            val count = (remaining / plate).toInt()
            if (count > 0) {
                platesResult.add(PlateCount(weight = plate, count = count))
                remaining -= count * plate
                // Round remaining to avoid floating point precision artifacts
                remaining = kotlin.math.round(remaining * 1000.0) / 1000.0
            }
        }

        val loadedPerSide = platesResult.sumOf { it.weight * it.count }
        val totalAchievable = totalBase + (loadedPerSide * 2.0)

        return PlateCalculationResult(
            targetWeight = targetWeight,
            barWeight = barWeight,
            collarsWeight = collarsWeight,
            weightPerSide = targetWeightPerSide,
            platesPerSide = platesResult,
            remainderPerSide = remaining,
            totalAchievableWeight = totalAchievable
        )
    }

    fun defaultBarWeight(unit: WeightUnit): Double = when (unit) {
        WeightUnit.KG -> DEFAULT_BAR_KG
        WeightUnit.LB -> DEFAULT_BAR_LB
    }

    fun defaultCollarsWeight(unit: WeightUnit): Double = when (unit) {
        WeightUnit.KG -> DEFAULT_COLLARS_KG
        WeightUnit.LB -> DEFAULT_COLLARS_LB
    }

    fun defaultPlates(unit: WeightUnit): List<Double> = when (unit) {
        WeightUnit.KG -> DEFAULT_KG_PLATES
        WeightUnit.LB -> DEFAULT_LB_PLATES
    }

    /**
     * Formats a compact plate breakdown representation for quick display in a set row.
     * E.g. "[20/10/2.5] kg", "[45/25/10] lb", "[20/20] kg", "[Bar] kg".
     * Returns null if targetWeight is zero or below the base barbell weight.
     */
    fun formatCompactPlatesPerSide(
        targetWeight: Double,
        barWeight: Double = DEFAULT_BAR_KG,
        collarsWeight: Double = 0.0,
        availablePlates: List<Double> = DEFAULT_KG_PLATES,
        unit: WeightUnit = WeightUnit.KG
    ): String? {
        if (targetWeight <= 0.0) return null
        val totalBase = barWeight + collarsWeight
        if (targetWeight < totalBase) return null

        val result = calculatePlates(
            targetWeight = targetWeight,
            barWeight = barWeight,
            collarsWeight = collarsWeight,
            availablePlates = availablePlates
        )

        if (result.platesPerSide.isEmpty()) {
            return if (kotlin.math.abs(targetWeight - totalBase) < 0.001) "[Bar] ${unit.symbol}" else null
        }

        val plateTokens = result.platesPerSide.flatMap { plate ->
            val wStr = if (plate.weight % 1.0 == 0.0) {
                plate.weight.toInt().toString()
            } else {
                plate.weight.toString()
            }
            if (plate.count > 2) {
                listOf("$wStr×${plate.count}")
            } else {
                List(plate.count) { wStr }
            }
        }
        val platesJoined = plateTokens.joinToString("/")

        return if (result.remainderPerSide > 0.001) {
            val remStr = if (result.remainderPerSide % 1.0 == 0.0) {
                result.remainderPerSide.toInt().toString()
            } else {
                result.remainderPerSide.toString()
            }
            "[$platesJoined +$remStr] ${unit.symbol}"
        } else {
            "[$platesJoined] ${unit.symbol}"
        }
    }
}
