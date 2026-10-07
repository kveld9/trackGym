package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class RpeTargetWeightCalculatorTest {

    @Test
    fun getPercentageOf1RM_singleRepAtRpe10_returns100Percent() {
        val pct = RpeTargetWeightCalculator.getPercentageOf1RM(reps = 1, rpe = 10.0)
        assertEquals(1.0, pct, 0.001)
    }

    @Test
    fun getPercentageOf1RM_5repsAtRpe8_returnsExpectedRtsPercentage() {
        // 5 reps @ RPE 8 = 81.1% in standard Mike Tuchscherer matrix
        val pct = RpeTargetWeightCalculator.getPercentageOf1RM(reps = 5, rpe = 8.0)
        assertEquals(0.811, pct, 0.001)
    }

    @Test
    fun getPercentageOf1RM_invalidInputs_returnsZero() {
        assertEquals(0.0, RpeTargetWeightCalculator.getPercentageOf1RM(reps = 0, rpe = 8.0), 0.001)
        assertEquals(0.0, RpeTargetWeightCalculator.getPercentageOf1RM(reps = -5, rpe = 8.0), 0.001)
    }

    @Test
    fun calculateTargetWeight_100kg1RM_5repsAtRpe8_calculatesCorrectWeights() {
        // 1RM: 100 kg, 5 reps @ RPE 8 -> 81.1% -> theoretical: 81.1 kg, rounded to 2.5 kg: 80.0 kg
        val result = RpeTargetWeightCalculator.calculateTargetWeight(
            estimated1RmKg = 100.0,
            targetReps = 5,
            targetRpe = 8.0,
            roundingStepKg = 2.5
        )

        assertEquals(100.0, result.estimated1RmKg, 0.001)
        assertEquals(5, result.targetReps)
        assertEquals(8.0, result.targetRpe, 0.001)
        assertEquals(0.811, result.percentage, 0.001)
        assertEquals(81.1, result.theoreticalWeightKg, 0.05)
        assertEquals(80.0, result.suggestedWeightKg, 0.05)
    }

    @Test
    fun calculateTargetWeight_140kg1RM_3repsAtRpe9_calculatesCorrectWeights() {
        // 3 reps @ RPE 9 = 89.2%
        // 140 * 0.892 = 124.88 kg -> theoretical: 124.9 kg, rounded to 2.5kg: 125.0 kg
        val result = RpeTargetWeightCalculator.calculateTargetWeight(
            estimated1RmKg = 140.0,
            targetReps = 3,
            targetRpe = 9.0,
            roundingStepKg = 2.5
        )

        assertEquals(0.892, result.percentage, 0.001)
        assertEquals(124.9, result.theoreticalWeightKg, 0.1)
        assertEquals(125.0, result.suggestedWeightKg, 0.1)
    }

    @Test
    fun calculateTargetWeight_customRoundingStep_roundsCorrectly() {
        // Step 0.5 kg
        val result = RpeTargetWeightCalculator.calculateTargetWeight(
            estimated1RmKg = 100.0,
            targetReps = 5,
            targetRpe = 8.0,
            roundingStepKg = 0.5
        )
        assertEquals(81.0, result.suggestedWeightKg, 0.05)
    }
}
