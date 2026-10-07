package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class AutoProgressionEngineTest {

    private fun createSet(weightKg: Double, reps: Int, isCompleted: Boolean = true, setType: SetType = SetType.NORMAL): WorkoutSet {
        return WorkoutSet(
            workoutExerciseId = 1L,
            setNumber = 1,
            setType = setType,
            weightKg = weightKg,
            reps = reps,
            isCompleted = isCompleted
        )
    }

    @Test
    fun encodeAndDecode_roundTrip_preservesValues() {
        val rule = ProgressionRule(
            enabled = true,
            targetReps = 8,
            incrementKg = 5.0,
            failureThreshold = 3,
            deloadPercentage = 15.0
        )
        val encoded = AutoProgressionEngine.encode(rule)
        val decoded = AutoProgressionEngine.decode(encoded)

        assertNotNull(decoded)
        assertEquals(true, decoded!!.enabled)
        assertEquals(8, decoded.targetReps)
        assertEquals(5.0, decoded.incrementKg, 0.001)
        assertEquals(3, decoded.failureThreshold)
        assertEquals(15.0, decoded.deloadPercentage, 0.001)
    }

    @Test
    fun decode_handlesCorruptedOrEmptyStringsSafely() {
        assertNull(AutoProgressionEngine.decode(null))
        assertNull(AutoProgressionEngine.decode(""))
        assertNull(AutoProgressionEngine.decode("   "))

        val partial = AutoProgressionEngine.decode("EN=0;REPS=12")
        assertNotNull(partial)
        assertEquals(false, partial!!.enabled)
        assertEquals(12, partial.targetReps)
    }

    @Test
    fun evaluate_whenAllSetsMeetTargetReps_recommendsProgression() {
        val rule = ProgressionRule(targetReps = 10, incrementKg = 2.5)
        // Session: 3 sets of 10 reps @ 80kg
        val lastSession = listOf(
            createSet(80.0, 10),
            createSet(80.0, 10),
            createSet(80.0, 11)
        )
        val result = AutoProgressionEngine.evaluate(
            rule = rule,
            recentSessionsSets = listOf(lastSession),
            currentWeightKg = 80.0
        )

        assertEquals(ProgressionDecisionType.PROGRESSION, result.type)
        assertEquals(80.0, result.baseWeightKg, 0.001)
        assertEquals(82.5, result.recommendedWeightKg, 0.001)
        assertEquals(2.5, result.weightDeltaKg, 0.001)
        assertEquals(0, result.consecutiveFailures)
    }

    @Test
    fun evaluate_whenOneSetMissesTargetReps_recommendsMaintain() {
        val rule = ProgressionRule(targetReps = 10, incrementKg = 2.5, failureThreshold = 2)
        // Session: sets 10, 10, 8 (missed target reps on 3rd set)
        val lastSession = listOf(
            createSet(80.0, 10),
            createSet(80.0, 10),
            createSet(80.0, 8)
        )
        val result = AutoProgressionEngine.evaluate(
            rule = rule,
            recentSessionsSets = listOf(lastSession),
            currentWeightKg = 80.0
        )

        assertEquals(ProgressionDecisionType.MAINTAIN, result.type)
        assertEquals(80.0, result.recommendedWeightKg, 0.001)
        assertEquals(0.0, result.weightDeltaKg, 0.001)
        assertEquals(1, result.consecutiveFailures)
    }

    @Test
    fun evaluate_whenConsecutiveFailuresReachThreshold_recommendsDeload() {
        val rule = ProgressionRule(targetReps = 5, failureThreshold = 2, deloadPercentage = 10.0)
        // Two consecutive failed sessions @ 100kg
        val session1 = listOf(createSet(100.0, 5), createSet(100.0, 4), createSet(100.0, 3))
        val session2 = listOf(createSet(100.0, 5), createSet(100.0, 4), createSet(100.0, 4))

        val result = AutoProgressionEngine.evaluate(
            rule = rule,
            recentSessionsSets = listOf(session1, session2),
            currentWeightKg = 100.0,
            roundingStepKg = 2.5
        )

        assertEquals(ProgressionDecisionType.DELOAD, result.type)
        // 100kg - 10% = 90kg
        assertEquals(90.0, result.recommendedWeightKg, 0.001)
        assertEquals(-10.0, result.weightDeltaKg, 0.001)
        assertEquals(2, result.consecutiveFailures)
    }

    @Test
    fun evaluate_warmupSetsAreIgnoredDuringEvaluation() {
        val rule = ProgressionRule(targetReps = 10, incrementKg = 2.5)
        // Warmup of 5 reps at 40kg, followed by 3 working sets of 10 reps at 80kg
        val session = listOf(
            createSet(40.0, 5, setType = SetType.WARMUP),
            createSet(80.0, 10, setType = SetType.NORMAL),
            createSet(80.0, 10, setType = SetType.NORMAL),
            createSet(80.0, 10, setType = SetType.NORMAL)
        )
        val result = AutoProgressionEngine.evaluate(
            rule = rule,
            recentSessionsSets = listOf(session),
            currentWeightKg = 80.0
        )

        assertEquals(ProgressionDecisionType.PROGRESSION, result.type)
        assertEquals(82.5, result.recommendedWeightKg, 0.001)
    }

    @Test
    fun evaluate_whenRuleIsDisabled_returnsMaintain() {
        val disabledRule = ProgressionRule(enabled = false)
        val session = listOf(createSet(100.0, 10))

        val result = AutoProgressionEngine.evaluate(
            rule = disabledRule,
            recentSessionsSets = listOf(session),
            currentWeightKg = 100.0
        )

        assertEquals(ProgressionDecisionType.MAINTAIN, result.type)
        assertEquals(100.0, result.recommendedWeightKg, 0.001)
    }
}
