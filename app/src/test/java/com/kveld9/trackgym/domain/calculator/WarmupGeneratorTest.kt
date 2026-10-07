package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WarmupGeneratorTest {

    @Test
    fun generateWarmupSets_targetWeightLessThanOrEqualToMin_returnsEmptyList() {
        val sets = WarmupGenerator.generateWarmupSets(targetWeightKg = 20.0, minWeightKg = 20.0)
        assertTrue(sets.isEmpty())

        val setsLighter = WarmupGenerator.generateWarmupSets(targetWeightKg = 15.0, minWeightKg = 20.0)
        assertTrue(setsLighter.isEmpty())
    }

    @Test
    fun generateWarmupSets_standard100kg_generatesProgressiveWarmupSets() {
        // Target: 100kg, min: 20kg
        // Step 1: 40% = 40kg, 10 reps
        // Step 2: 60% = 60kg, 5 reps
        // Step 3: 75% = 75kg, 3 reps
        // Step 4: 85% = 85kg, 1 rep
        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = 100.0,
            workoutExerciseId = 42L,
            minWeightKg = 20.0
        )

        assertEquals(4, sets.size)

        // Set 1
        assertEquals(1, sets[0].setNumber)
        assertEquals(SetType.WARMUP, sets[0].setType)
        assertEquals(40.0, sets[0].weightKg, 0.001)
        assertEquals(10, sets[0].reps)
        assertEquals(42L, sets[0].workoutExerciseId)

        // Set 2
        assertEquals(2, sets[1].setNumber)
        assertEquals(SetType.WARMUP, sets[1].setType)
        assertEquals(60.0, sets[1].weightKg, 0.001)
        assertEquals(5, sets[1].reps)

        // Set 3
        assertEquals(3, sets[2].setNumber)
        assertEquals(SetType.WARMUP, sets[2].setType)
        assertEquals(75.0, sets[2].weightKg, 0.001)
        assertEquals(3, sets[2].reps)

        // Set 4
        assertEquals(4, sets[3].setNumber)
        assertEquals(SetType.WARMUP, sets[3].setType)
        assertEquals(85.0, sets[3].weightKg, 0.001)
        assertEquals(1, sets[3].reps)
    }

    @Test
    fun generateWarmupSets_roundsToNearest2Point5Kg() {
        // Target: 70kg
        // 40% = 28kg -> rounded to 2.5 step: 27.5kg (28/2.5 = 11.2 -> 11 * 2.5 = 27.5)
        // 60% = 42kg -> rounded: 42.5kg (42/2.5 = 16.8 -> 17 * 2.5 = 42.5)
        // 75% = 52.5kg -> rounded: 52.5kg
        // 85% = 59.5kg -> rounded: 60.0kg
        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = 70.0,
            minWeightKg = 20.0
        )

        assertEquals(4, sets.size)
        assertEquals(27.5, sets[0].weightKg, 0.001)
        assertEquals(42.5, sets[1].weightKg, 0.001)
        assertEquals(52.5, sets[2].weightKg, 0.001)
        assertEquals(60.0, sets[3].weightKg, 0.001)
    }

    @Test
    fun generateWarmupSets_deduplicatesSameWeightSets() {
        // Target: 30kg, min: 20kg
        // 40% = 12kg -> coerceAtLeast(20) = 20kg
        // 60% = 18kg -> coerceAtLeast(20) = 20kg
        // distinctBy weight should eliminate duplicate 20kg sets
        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = 30.0,
            minWeightKg = 20.0
        )

        // Only one 20kg set should remain, plus 75% (22.5) and 85% (25.5 -> 25)
        val weights = sets.map { it.weightKg }
        assertEquals(weights.distinct().size, weights.size)
    }
}
