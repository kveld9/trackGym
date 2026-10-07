package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ProgressiveOverloadEngineTest {

    @Test
    fun computeRecommendation_emptyOrOnlyWarmups_returnsNull() {
        val emptyResult = ProgressiveOverloadEngine.computeRecommendation(emptyList())
        assertNull(emptyResult)

        val warmupsOnly = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.WARMUP, weightKg = 40.0, reps = 10, isCompleted = true)
        )
        val warmupResult = ProgressiveOverloadEngine.computeRecommendation(warmupsOnly)
        assertNull(warmupResult)
    }

    @Test
    fun computeRecommendation_uncompletedZeroReps_returnsNull() {
        val incomplete = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.NORMAL, weightKg = 80.0, reps = 0, isCompleted = false)
        )
        val result = ProgressiveOverloadEngine.computeRecommendation(incomplete)
        assertNull(result)
    }

    @Test
    fun computeRecommendation_allIdenticalSets_formatsSetsCountSummary() {
        val sets = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.NORMAL, weightKg = 100.0, reps = 5, isCompleted = true),
            WorkoutSet(workoutExerciseId = 1, setNumber = 2, setType = SetType.NORMAL, weightKg = 100.0, reps = 5, isCompleted = true),
            WorkoutSet(workoutExerciseId = 1, setNumber = 3, setType = SetType.NORMAL, weightKg = 100.0, reps = 5, isCompleted = true)
        )

        val recommendation = ProgressiveOverloadEngine.computeRecommendation(sets, WeightUnit.KG)
        assertNotNull(recommendation)
        assertTrue(recommendation!!.previousSummary.startsWith("3×5 @ 100"))
        assertTrue(recommendation.suggestedTarget.contains("+2.5 kg"))
        assertEquals("102.5 kg × 5 reps (+2.5 kg)", recommendation.suggestedTarget)
    }

    @Test
    fun computeRecommendation_varyingSetsHighReps_suggestsRepRangeAndWeight() {
        val sets = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.NORMAL, weightKg = 50.0, reps = 12, isCompleted = true),
            WorkoutSet(workoutExerciseId = 1, setNumber = 2, setType = SetType.NORMAL, weightKg = 60.0, reps = 10, isCompleted = true)
        )

        val recommendation = ProgressiveOverloadEngine.computeRecommendation(sets, WeightUnit.KG)
        assertNotNull(recommendation)
        assertTrue(recommendation!!.previousSummary.contains("60"))
        // topReps = 10 >= 8 -> repRange is "${topReps - 2}–$topReps" = "8–10"
        assertTrue(recommendation.suggestedTarget.contains("8–10 reps"))
        assertTrue(recommendation.suggestedTarget.contains("62.5 kg"))
    }

    @Test
    fun computeRecommendation_bodyweightZeroKg_suggestsRepsIncrement() {
        val sets = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.NORMAL, weightKg = 0.0, reps = 10, isCompleted = true)
        )

        val recommendation = ProgressiveOverloadEngine.computeRecommendation(sets, WeightUnit.KG)
        assertNotNull(recommendation)
        assertEquals("11–13 reps", recommendation!!.suggestedTarget)
    }

    @Test
    fun computeRecommendation_lbUnits_incrementsBy5Lb() {
        val sets = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, setType = SetType.NORMAL, weightKg = 100.0, reps = 5, isCompleted = true)
        )

        val recommendation = ProgressiveOverloadEngine.computeRecommendation(sets, WeightUnit.LB)
        assertNotNull(recommendation)
        assertTrue(recommendation!!.suggestedTarget.contains("+5 lb"))
    }
}
