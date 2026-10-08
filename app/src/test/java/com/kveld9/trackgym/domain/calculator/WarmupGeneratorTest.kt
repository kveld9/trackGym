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

    @Test
    fun encodeAndDecodeProtocol_roundTrip_preservesStructure() {
        val original = listOf(
            WarmupSetConfig(percentage = 0.0, reps = 10, isBarOnly = true),
            WarmupSetConfig(percentage = 0.50, reps = 5, isBarOnly = false),
            WarmupSetConfig(percentage = 0.70, reps = 3, isBarOnly = false),
            WarmupSetConfig(percentage = 0.85, reps = 1, isBarOnly = false)
        )

        val encoded = WarmupGenerator.encodeProtocol(original)
        assertEquals("BAR:10,50:5,70:3,85:1", encoded)

        val decoded = WarmupGenerator.decodeProtocol(encoded)
        org.junit.Assert.assertNotNull(decoded)
        assertEquals(4, decoded!!.size)

        assertEquals(true, decoded[0].isBarOnly)
        assertEquals(10, decoded[0].reps)

        assertEquals(0.50, decoded[1].percentage, 0.001)
        assertEquals(5, decoded[1].reps)
        assertEquals(false, decoded[1].isBarOnly)

        assertEquals(0.70, decoded[2].percentage, 0.001)
        assertEquals(3, decoded[2].reps)

        assertEquals(0.85, decoded[3].percentage, 0.001)
        assertEquals(1, decoded[3].reps)
    }

    @Test
    fun decodeProtocol_handlesSpacesPercentSignsAndInvalidInputs() {
        val decodedWithSpaces = WarmupGenerator.decodeProtocol(" BAR : 12 , 55% : 6 , 75% : 2 ")
        org.junit.Assert.assertNotNull(decodedWithSpaces)
        assertEquals(3, decodedWithSpaces!!.size)
        assertTrue(decodedWithSpaces[0].isBarOnly)
        assertEquals(12, decodedWithSpaces[0].reps)
        assertEquals(0.55, decodedWithSpaces[1].percentage, 0.001)
        assertEquals(6, decodedWithSpaces[1].reps)
        assertEquals(0.75, decodedWithSpaces[2].percentage, 0.001)
        assertEquals(2, decodedWithSpaces[2].reps)

        org.junit.Assert.assertNull(WarmupGenerator.decodeProtocol(null))
        org.junit.Assert.assertNull(WarmupGenerator.decodeProtocol(""))
        org.junit.Assert.assertNull(WarmupGenerator.decodeProtocol("   "))
        org.junit.Assert.assertNull(WarmupGenerator.decodeProtocol("invalid:token,corrupt"))
    }

    @Test
    fun generateWarmupSets_withCustomProtocolAndBarOnly_generatesAccurateLoads() {
        val customProtocol = listOf(
            WarmupSetConfig(percentage = 0.0, reps = 10, isBarOnly = true),
            WarmupSetConfig(percentage = 0.50, reps = 5),
            WarmupSetConfig(percentage = 0.70, reps = 3),
            WarmupSetConfig(percentage = 0.85, reps = 1)
        )

        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = 120.0,
            workoutExerciseId = 10L,
            minWeightKg = 20.0,
            protocol = customProtocol
        )

        assertEquals(4, sets.size)
        // Step 1: Bar only = 20kg x 10
        assertEquals(20.0, sets[0].weightKg, 0.001)
        assertEquals(10, sets[0].reps)

        // Step 2: 50% of 120 = 60kg x 5
        assertEquals(60.0, sets[1].weightKg, 0.001)
        assertEquals(5, sets[1].reps)

        // Step 3: 70% of 120 = 84kg -> rounded to 85kg x 3
        assertEquals(85.0, sets[2].weightKg, 0.001)
        assertEquals(3, sets[2].reps)

        // Step 4: 85% of 120 = 102kg -> rounded to 102.5kg x 1
        assertEquals(102.5, sets[3].weightKg, 0.001)
        assertEquals(1, sets[3].reps)
    }

    @Test
    fun generateWarmupSets_withPresetExpress_generatesTwoSets() {
        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = 100.0,
            minWeightKg = 20.0,
            protocol = WarmupGenerator.PRESET_EXPRESS
        )

        assertEquals(2, sets.size)
        // 50% = 50kg x 5
        assertEquals(50.0, sets[0].weightKg, 0.001)
        assertEquals(5, sets[0].reps)

        // 75% = 75kg x 2
        assertEquals(75.0, sets[1].weightKg, 0.001)
        assertEquals(2, sets[1].reps)
    }

    @Test
    fun generateWarmupSets_nanTargetWeight_returnsEmptyList() {
        val sets = WarmupGenerator.generateWarmupSets(
            targetWeightKg = Double.NaN,
            minWeightKg = 20.0
        )
        assertEquals(0, sets.size)
    }
}
