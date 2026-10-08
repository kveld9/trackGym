package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.WeightUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlateCalculatorTest {

    @Test
    fun calculatePlates_exactMatch_calculatesCorrectBreakdown() {
        // Target: 100kg, Bar: 20kg, Collars: 0kg -> 80kg total plates -> 40kg per side
        // Default KG plates: [25, 20, 15, 10, 5, 2.5, 1.25]
        // 40kg = 1x25kg + 1x15kg
        val result = PlateCalculator.calculatePlates(
            targetWeight = 100.0,
            barWeight = 20.0,
            collarsWeight = 0.0
        )

        assertEquals(100.0, result.targetWeight, 0.001)
        assertEquals(20.0, result.barWeight, 0.001)
        assertEquals(40.0, result.weightPerSide, 0.001)
        assertEquals(0.0, result.remainderPerSide, 0.001)
        assertEquals(100.0, result.totalAchievableWeight, 0.001)
        assertEquals(2, result.platesPerSide.size)
        assertEquals(25.0, result.platesPerSide[0].weight, 0.001)
        assertEquals(1, result.platesPerSide[0].count)
        assertEquals(15.0, result.platesPerSide[1].weight, 0.001)
        assertEquals(1, result.platesPerSide[1].count)
    }

    @Test
    fun calculatePlates_withCollars_accountsForCollarWeight() {
        // Target: 101kg, Bar: 20kg, Collars: 1.0kg -> 80kg plates -> 40kg per side
        val result = PlateCalculator.calculatePlates(
            targetWeight = 101.0,
            barWeight = 20.0,
            collarsWeight = 1.0
        )

        assertEquals(40.0, result.weightPerSide, 0.001)
        assertEquals(0.0, result.remainderPerSide, 0.001)
        assertEquals(101.0, result.totalAchievableWeight, 0.001)
    }

    @Test
    fun calculatePlates_targetLighterThanBar_returnsEmptyPlates() {
        val result = PlateCalculator.calculatePlates(
            targetWeight = 15.0,
            barWeight = 20.0
        )

        assertTrue(result.platesPerSide.isEmpty())
        assertEquals(0.0, result.weightPerSide, 0.001)
        assertEquals(0.0, result.remainderPerSide, 0.001)
        assertEquals(20.0, result.totalAchievableWeight, 0.001)
    }

    @Test
    fun calculatePlates_withRemainder_calculatesAchievableAndRemainder() {
        // Target: 23kg, Bar: 20kg -> 3kg needed -> 1.5kg per side
        // Available plates: 1.25 -> 1x 1.25kg, remainder = 0.25kg per side
        val result = PlateCalculator.calculatePlates(
            targetWeight = 23.0,
            barWeight = 20.0,
            collarsWeight = 0.0
        )

        assertEquals(1.5, result.weightPerSide, 0.001)
        assertEquals(1, result.platesPerSide.size)
        assertEquals(1.25, result.platesPerSide[0].weight, 0.001)
        assertEquals(1, result.platesPerSide[0].count)
        assertEquals(0.25, result.remainderPerSide, 0.001)
        assertEquals(22.5, result.totalAchievableWeight, 0.001)
    }

    @Test
    fun calculatePlates_inLbs_calculatesCorrectBreakdown() {
        // Target: 225 lbs, Bar: 45 lbs -> 180 lbs plates -> 90 lbs per side
        // Default LB plates: [45, 35, 25, 10, 5, 2.5]
        // 90 lbs = 2x45 lbs
        val result = PlateCalculator.calculatePlates(
            targetWeight = 225.0,
            barWeight = PlateCalculator.defaultBarWeight(WeightUnit.LB),
            availablePlates = PlateCalculator.defaultPlates(WeightUnit.LB)
        )

        assertEquals(90.0, result.weightPerSide, 0.001)
        assertEquals(1, result.platesPerSide.size)
        assertEquals(45.0, result.platesPerSide[0].weight, 0.001)
        assertEquals(2, result.platesPerSide[0].count)
        assertEquals(0.0, result.remainderPerSide, 0.001)
        assertEquals(225.0, result.totalAchievableWeight, 0.001)
    }

    @Test
    fun barbellProfiles_returnExpectedWeights() {
        assertEquals(20.0, BarbellProfile.OLYMPIC.weight(WeightUnit.KG), 0.001)
        assertEquals(45.0, BarbellProfile.OLYMPIC.weight(WeightUnit.LB), 0.001)
        assertEquals(15.0, BarbellProfile.WOMEN.weight(WeightUnit.KG), 0.001)
        assertEquals(10.0, BarbellProfile.EZ_CURL.weight(WeightUnit.KG), 0.001)
        assertEquals(25.0, BarbellProfile.TRAP_HEX.weight(WeightUnit.KG), 0.001)
        assertEquals(0.0, BarbellProfile.SMITH.weight(WeightUnit.KG), 0.001)
    }

    @Test
    fun formatCompactPlatesPerSide_barOnly_returnsBar() {
        val result = PlateCalculator.formatCompactPlatesPerSide(
            targetWeight = 20.0,
            barWeight = 20.0,
            unit = WeightUnit.KG
        )
        assertEquals("[Bar] kg", result)
    }

    @Test
    fun formatCompactPlatesPerSide_belowBarWeight_returnsNull() {
        val result = PlateCalculator.formatCompactPlatesPerSide(
            targetWeight = 15.0,
            barWeight = 20.0,
            unit = WeightUnit.KG
        )
        org.junit.Assert.assertNull(result)
    }

    @Test
    fun formatCompactPlatesPerSide_kgBreakdown_matchesExpectedFormat() {
        // 85kg with 20kg bar and available plates [20, 10, 5, 2.5, 1.25] -> per side: 32.5kg = 20 + 10 + 2.5
        val result = PlateCalculator.formatCompactPlatesPerSide(
            targetWeight = 85.0,
            barWeight = 20.0,
            availablePlates = listOf(20.0, 10.0, 5.0, 2.5, 1.25),
            unit = WeightUnit.KG
        )
        assertEquals("[20/10/2.5] kg", result)
    }

    @Test
    fun formatCompactPlatesPerSide_lbBreakdown_matchesExpectedFormat() {
        // 205 lbs with 45 lbs bar and [45, 25, 10, 5, 2.5] -> per side: 80 lbs = 45 + 25 + 10
        val result = PlateCalculator.formatCompactPlatesPerSide(
            targetWeight = 205.0,
            barWeight = 45.0,
            availablePlates = listOf(45.0, 25.0, 10.0, 5.0, 2.5),
            unit = WeightUnit.LB
        )
        assertEquals("[45/25/10] lb", result)
    }

    @Test
    fun formatCompactPlatesPerSide_multiplePlates_formatsSlashSeparated() {
        // 100kg with 20kg bar and [20, 10] -> 40kg per side = 2x20
        val result = PlateCalculator.formatCompactPlatesPerSide(
            targetWeight = 100.0,
            barWeight = 20.0,
            availablePlates = listOf(20.0, 10.0),
            unit = WeightUnit.KG
        )
        assertEquals("[20/20] kg", result)
    }

    @Test
    fun calculatePlates_nanAndInfiniteInputs_handledGracefully() {
        val result = PlateCalculator.calculatePlates(Double.NaN)
        assertTrue(result.platesPerSide.isEmpty())
        assertEquals(0.0, result.weightPerSide, 0.001)

        val formatResult = PlateCalculator.formatCompactPlatesPerSide(Double.NaN)
        assertEquals(null, formatResult)
    }
}
