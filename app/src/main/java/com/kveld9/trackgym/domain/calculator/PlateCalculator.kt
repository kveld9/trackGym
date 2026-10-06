package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.WeightUnit

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

    /**
     * Calculates plate configuration for one side of the barbell.
     *
     * @param targetWeight The total target weight (including bar) in the active unit.
     * @param barWeight The barbell weight in the active unit.
     * @param availablePlates Available plate weights in descending order.
     */
    fun calculatePlates(
        targetWeight: Double,
        barWeight: Double = DEFAULT_BAR_KG,
        availablePlates: List<Double> = DEFAULT_KG_PLATES
    ): PlateCalculationResult {
        if (targetWeight <= barWeight) {
            return PlateCalculationResult(
                targetWeight = targetWeight,
                barWeight = barWeight,
                weightPerSide = 0.0,
                platesPerSide = emptyList(),
                remainderPerSide = 0.0,
                totalAchievableWeight = barWeight
            )
        }

        val sortedPlates = availablePlates.filter { it > 0.0 }.sortedDescending()
        val targetWeightPerSide = (targetWeight - barWeight) / 2.0
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
        val totalAchievable = barWeight + (loadedPerSide * 2.0)

        return PlateCalculationResult(
            targetWeight = targetWeight,
            barWeight = barWeight,
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

    fun defaultPlates(unit: WeightUnit): List<Double> = when (unit) {
        WeightUnit.KG -> DEFAULT_KG_PLATES
        WeightUnit.LB -> DEFAULT_LB_PLATES
    }
}
