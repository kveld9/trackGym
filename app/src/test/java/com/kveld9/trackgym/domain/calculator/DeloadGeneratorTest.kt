package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class DeloadGeneratorTest {

    @Test
    fun calculateDeloadSets_cutsVolumeInHalfByDefault() {
        assertEquals(2, DeloadGenerator.calculateDeloadSets(4, 50.0))
        assertEquals(2, DeloadGenerator.calculateDeloadSets(3, 50.0))
        assertEquals(1, DeloadGenerator.calculateDeloadSets(2, 50.0))
        assertEquals(1, DeloadGenerator.calculateDeloadSets(1, 50.0))
        assertEquals(1, DeloadGenerator.calculateDeloadSets(0, 50.0))
    }

    @Test
    fun calculateDeloadSets_customReductions() {
        // 60% reduction -> 4 * 0.4 = 1.6 -> 2 sets
        assertEquals(2, DeloadGenerator.calculateDeloadSets(4, 60.0))
        // 40% reduction -> 5 * 0.6 = 3 sets
        assertEquals(3, DeloadGenerator.calculateDeloadSets(5, 40.0))
    }

    @Test
    fun calculateDeloadWeight_reducesWeightBy15PercentByDefault() {
        // 100 kg * 0.85 = 85.0 kg
        assertEquals(85.0, DeloadGenerator.calculateDeloadWeight(100.0, 15.0), 0.001)

        // 80 kg * 0.85 = 68.0 kg
        assertEquals(68.0, DeloadGenerator.calculateDeloadWeight(80.0, 15.0), 0.001)

        // 60 kg * 0.80 (20% reduction) = 48.0 kg
        assertEquals(48.0, DeloadGenerator.calculateDeloadWeight(60.0, 20.0), 0.001)
    }

    @Test
    fun calculateDeloadWeight_roundsToStepProperly() {
        // 82.5 kg * 0.85 = 70.125 kg -> rounds to 70.0 kg (step 0.5)
        assertEquals(70.0, DeloadGenerator.calculateDeloadWeight(82.5, 15.0, stepKg = 0.5), 0.001)

        // 77.5 kg * 0.85 = 65.875 kg -> rounds to 66.0 kg (step 0.5)
        assertEquals(66.0, DeloadGenerator.calculateDeloadWeight(77.5, 15.0, stepKg = 0.5), 0.001)
    }

    @Test
    fun calculateDeloadWeight_handlesZeroAndEdgeCases() {
        assertEquals(0.0, DeloadGenerator.calculateDeloadWeight(0.0), 0.001)
        assertEquals(0.0, DeloadGenerator.calculateDeloadWeight(-10.0), 0.001)
        assertEquals(0.0, DeloadGenerator.calculateDeloadWeight(Double.NaN), 0.001)
        assertEquals(2, DeloadGenerator.calculateDeloadSets(3, Double.NaN))
    }
}
