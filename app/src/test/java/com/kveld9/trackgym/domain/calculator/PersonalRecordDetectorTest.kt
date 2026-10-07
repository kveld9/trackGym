package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PersonalRecordDetectorTest {

    @Test
    fun `user did 2x8 at 15kg last Thursday and does 2x10 at 15kg today triggers Rep PR and 1RM PR`() {
        val exerciseId = 1L
        val workoutId = 2L

        // Last Thursday sets
        val historySets = listOf(
            WorkoutSet(id = 1, workoutExerciseId = 1, setNumber = 1, weightKg = 15.0, reps = 8, isCompleted = true),
            WorkoutSet(id = 2, workoutExerciseId = 1, setNumber = 2, weightKg = 15.0, reps = 8, isCompleted = true)
        )

        // Today set: 10 reps @ 15.0 kg
        val currentSet = WorkoutSet(id = 3, workoutExerciseId = 2, setNumber = 1, weightKg = 15.0, reps = 10, isCompleted = true)

        val unlockedRecords = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = currentSet,
            historicalSets = historySets
        )

        val repRecord = unlockedRecords.find { it.recordType == RecordType.MAX_REPS_AT_WEIGHT }
        assertNotNull("Should unlock MAX_REPS_AT_WEIGHT record", repRecord)
        assertEquals(10.0, repRecord!!.recordValue, 0.01)

        val oneRmRecord = unlockedRecords.find { it.recordType == RecordType.ESTIMATED_1RM }
        assertNotNull("Should unlock ESTIMATED_1RM record", oneRmRecord)
        // 15 * (1 + 10/30) = 20.0 kg > 19.0 kg
        assertEquals(20.0, oneRmRecord!!.recordValue, 0.1)
    }

    @Test
    fun `user did 2x8 at 15kg last Thursday and does 2x8 at 18kg today triggers Weight PR and 1RM PR`() {
        val exerciseId = 1L
        val workoutId = 2L

        // Last Thursday sets
        val historySets = listOf(
            WorkoutSet(id = 1, workoutExerciseId = 1, setNumber = 1, weightKg = 15.0, reps = 8, isCompleted = true),
            WorkoutSet(id = 2, workoutExerciseId = 1, setNumber = 2, weightKg = 15.0, reps = 8, isCompleted = true)
        )

        // Today set: 8 reps @ 18.0 kg
        val currentSet = WorkoutSet(id = 3, workoutExerciseId = 2, setNumber = 1, weightKg = 18.0, reps = 8, isCompleted = true)

        val unlockedRecords = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = currentSet,
            historicalSets = historySets
        )

        val weightRecord = unlockedRecords.find { it.recordType == RecordType.MAX_WEIGHT }
        assertNotNull("Should unlock MAX_WEIGHT record", weightRecord)
        assertEquals(18.0, weightRecord!!.recordValue, 0.01)

        val oneRmRecord = unlockedRecords.find { it.recordType == RecordType.ESTIMATED_1RM }
        assertNotNull("Should unlock ESTIMATED_1RM record", oneRmRecord)
        // 18 * (1 + 8/30) = 22.8 kg > 19.0 kg
        assertTrue(oneRmRecord!!.recordValue > 19.0)
    }

    @Test
    fun `lower weight or reps does not trigger false PRs`() {
        val exerciseId = 1L
        val workoutId = 2L

        val historySets = listOf(
            WorkoutSet(id = 1, workoutExerciseId = 1, setNumber = 1, weightKg = 20.0, reps = 10, isCompleted = true)
        )

        val currentSet = WorkoutSet(id = 2, workoutExerciseId = 2, setNumber = 1, weightKg = 15.0, reps = 8, isCompleted = true)

        val unlocked = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = currentSet,
            historicalSets = historySets
        )

        assertTrue("No PR should be unlocked for sub-maximal set", unlocked.isEmpty())
    }

    @Test
    fun `warmup sets are excluded from personal records calculations`() {
        val exerciseId = 1L
        val workoutId = 2L

        val currentWarmupSet = WorkoutSet(
            id = 1,
            workoutExerciseId = 1,
            setNumber = 1,
            weightKg = 100.0,
            reps = 10,
            isCompleted = true,
            setType = com.kveld9.trackgym.domain.model.SetType.WARMUP
        )

        val unlocked = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = currentWarmupSet,
            historicalSets = emptyList()
        )

        assertTrue("Warmup set should never unlock a personal record", unlocked.isEmpty())
    }

    @Test
    fun `amrap set unlocks max amrap reps record for that weight`() {
        val exerciseId = 1L
        val workoutId = 2L

        val previousAmrap = WorkoutSet(
            id = 1,
            workoutExerciseId = 1,
            setNumber = 3,
            weightKg = 80.0,
            reps = 8,
            setType = SetType.AMRAP,
            isCompleted = true
        )

        val newAmrap = WorkoutSet(
            id = 2,
            workoutExerciseId = 2,
            setNumber = 3,
            weightKg = 80.0,
            reps = 10,
            setType = SetType.AMRAP,
            isCompleted = true
        )

        val unlocked = PersonalRecordDetector.evaluateSet(
            exerciseId = exerciseId,
            workoutId = workoutId,
            currentSet = newAmrap,
            historicalSets = listOf(previousAmrap)
        )

        val amrapRecord = unlocked.find { it.recordType == RecordType.MAX_AMRAP_REPS_AT_WEIGHT }
        assertNotNull("Should unlock AMRAP record", amrapRecord)
        assertEquals(10.0, amrapRecord!!.recordValue, 0.01)
        assertTrue(amrapRecord.description.contains("AMRAP Record at 80.0 kg: 10 reps"))
    }
}
