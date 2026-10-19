package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineExercise
import com.kveld9.trackgym.domain.model.RoutineSetPlan
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.util.RoutineShareCodec
import com.kveld9.trackgym.data.repository.parseSetPlansJson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RoutinePerSetTest {

    private val testExercise = Exercise(
        id = 1L,
        name = "Barbell Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    @Test
    fun `legacy to plans migration default creates identical plans from legacy fields`() {
        val legacySets = 3
        val legacyWeight = 80.0
        val legacyReps = 10

        val plans = sanitizeRoutineSetPlans(
            plans = null,
            fallbackSets = legacySets,
            fallbackWeight = legacyWeight,
            fallbackReps = legacyReps
        )

        assertEquals(3, plans.size)
        plans.forEach { plan ->
            assertEquals(80.0, plan.weightKg, 0.001)
            assertEquals(10, plan.reps)
            assertEquals(SetType.NORMAL, plan.setType)
        }

        val routineExercise = RoutineExercise(
            id = 10L,
            exercise = testExercise,
            orderIndex = 0,
            targetSets = legacySets,
            defaultWeightKg = legacyWeight,
            defaultReps = legacyReps
        )

        assertEquals(3, routineExercise.sets.size)
        assertEquals(3, routineExercise.targetSets)
        assertEquals(80.0, routineExercise.defaultWeightKg, 0.001)
        assertEquals(10, routineExercise.defaultReps)
    }

    @Test
    fun `per-set expansion preserves weights reps and types across sets`() {
        val diversePlans = listOf(
            RoutineSetPlan(weightKg = 100.0, reps = 4, setType = SetType.NORMAL),
            RoutineSetPlan(weightKg = 85.0, reps = 8, setType = SetType.NORMAL),
            RoutineSetPlan(weightKg = 75.0, reps = 10, setType = SetType.DROP)
        )

        val exercise = RoutineExercise(
            id = 1L,
            exercise = testExercise,
            orderIndex = 0,
            sets = diversePlans
        )

        assertEquals(3, exercise.sets.size)
        assertEquals(100.0, exercise.sets[0].weightKg, 0.001)
        assertEquals(4, exercise.sets[0].reps)
        assertEquals(SetType.NORMAL, exercise.sets[0].setType)

        assertEquals(85.0, exercise.sets[1].weightKg, 0.001)
        assertEquals(8, exercise.sets[1].reps)
        assertEquals(SetType.NORMAL, exercise.sets[1].setType)

        assertEquals(75.0, exercise.sets[2].weightKg, 0.001)
        assertEquals(10, exercise.sets[2].reps)
        assertEquals(SetType.DROP, exercise.sets[2].setType)

        // Computed properties reflect primary set
        assertEquals(3, exercise.targetSets)
        assertEquals(100.0, exercise.defaultWeightKg, 0.001)
        assertEquals(4, exercise.defaultReps)
    }

    @Test
    fun `volume sum diverse correctly calculates total volume`() {
        val diversePlans = listOf(
            RoutineSetPlan(weightKg = 100.0, reps = 4), // 400 kg
            RoutineSetPlan(weightKg = 85.0, reps = 8),  // 680 kg
            RoutineSetPlan(weightKg = 75.0, reps = 10)  // 750 kg
        )

        val totalVolume = diversePlans.sumOf { it.weightKg * it.reps }
        val expectedVolume = 400.0 + 680.0 + 750.0

        assertEquals(expectedVolume, totalVolume, 0.001)
        assertEquals(1830.0, totalVolume, 0.001)
    }

    @Test
    fun `codec v1 decode still works with legacy JSON format`() {
        val v1Json = """
            {
                "version": 1,
                "name": "Chest Day",
                "notes": "Classic chest focus",
                "exercises": [
                    {
                        "name": "Barbell Bench Press",
                        "muscleGroup": "CHEST",
                        "category": "BARBELL",
                        "targetSets": 4,
                        "defaultWeightKg": 80.0,
                        "defaultReps": 10
                    }
                ]
            }
        """.trimIndent()

        val base64 = java.util.Base64.getEncoder().encodeToString(v1Json.toByteArray(Charsets.UTF_8))
        val shareText = "TrackGym Routine: Chest Day\n\ntgroutine:$base64"

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        assertEquals(1, decoded?.version)
        assertEquals("Chest Day", decoded?.name)
        assertEquals(1, decoded?.exercises?.size)

        val ex = decoded!!.exercises[0]
        assertEquals("Barbell Bench Press", ex.name)
        assertEquals(4, ex.targetSets)
        assertEquals(80.0, ex.defaultWeightKg, 0.001)
        assertEquals(10, ex.defaultReps)
        assertTrue(ex.sets.isEmpty())

        // Maps cleanly via sanitizeRoutineSetPlans fallback
        val plans = sanitizeRoutineSetPlans(
            plans = ex.sets.ifEmpty { null },
            fallbackSets = ex.targetSets,
            fallbackWeight = ex.defaultWeightKg,
            fallbackReps = ex.defaultReps
        )
        assertEquals(4, plans.size)
        assertEquals(80.0, plans[0].weightKg, 0.001)
        assertEquals(10, plans[0].reps)
    }

    @Test
    fun `codec v2 encodes and decodes diverse per-set routine`() {
        val routine = Routine(
            id = 1L,
            name = "Push Hypertrophy",
            notes = "Top set + back-off",
            exercises = listOf(
                RoutineExercise(
                    id = 1L,
                    exercise = testExercise,
                    orderIndex = 0,
                    sets = listOf(
                        RoutineSetPlan(weightKg = 100.0, reps = 4),
                        RoutineSetPlan(weightKg = 85.0, reps = 8),
                        RoutineSetPlan(weightKg = 70.0, reps = 12)
                    )
                )
            )
        )

        val shareText = RoutineShareCodec.encodeToShareText(routine)
        assertTrue(shareText.contains("Push Hypertrophy"))
        assertTrue(shareText.contains("100kg x4, 85kg x8, 70kg x12"))

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        assertEquals(2, decoded?.version)
        assertEquals("Push Hypertrophy", decoded?.name)
        assertEquals(1, decoded?.exercises?.size)

        val ex = decoded!!.exercises[0]
        assertEquals(3, ex.sets.size)
        assertEquals(100.0, ex.sets[0].weightKg, 0.001)
        assertEquals(4, ex.sets[0].reps)
        assertEquals(85.0, ex.sets[1].weightKg, 0.001)
        assertEquals(8, ex.sets[1].reps)
        assertEquals(70.0, ex.sets[2].weightKg, 0.001)
        assertEquals(12, ex.sets[2].reps)
    }

    @Test
    fun `sanitize clamps negative weights zero reps and invalid set counts`() {
        // Individual plan clamping
        val invalidPlan = RoutineSetPlan(weightKg = -25.0, reps = 0)
        val clampedPlan = sanitizeRoutineSetPlan(invalidPlan)
        assertEquals(0.0, clampedPlan.weightKg, 0.001)
        assertEquals(1, clampedPlan.reps)

        // Negative reps clamp to 1
        val negativeRepsPlan = RoutineSetPlan(weightKg = 50.0, reps = -5)
        val clampedNegativeReps = sanitizeRoutineSetPlan(negativeRepsPlan)
        assertEquals(50.0, clampedNegativeReps.weightKg, 0.001)
        assertEquals(1, clampedNegativeReps.reps)

        // Empty list falls back to valid positive count and clamps
        val clampedList = sanitizeRoutineSetPlans(
            plans = emptyList(),
            fallbackSets = -1,
            fallbackWeight = -10.0,
            fallbackReps = -3
        )
        // Default fallback sets is 3 when fallbackSets <= 0
        assertEquals(3, clampedList.size)
        clampedList.forEach { plan ->
            assertEquals(0.0, plan.weightKg, 0.001)
            assertEquals(10, plan.reps)
        }

        // List with invalid elements gets all elements clamped
        val dirtyList = listOf(
            RoutineSetPlan(weightKg = -10.0, reps = -2),
            RoutineSetPlan(weightKg = 40.0, reps = 0)
        )
        val cleanedList = sanitizeRoutineSetPlans(dirtyList)
        assertEquals(2, cleanedList.size)
        assertEquals(0.0, cleanedList[0].weightKg, 0.001)
        assertEquals(1, cleanedList[0].reps)
        assertEquals(40.0, cleanedList[1].weightKg, 0.001)
        assertEquals(1, cleanedList[1].reps)
    }

    @Test
    fun `unknown setType element coerced to NORMAL with siblings preserved`() {
        val json = """
            [
                {"weightKg": 100.0, "reps": 5, "setType": "NORMAL"},
                {"weightKg": 80.0, "reps": 10, "setType": "SUPERSET"},
                {"weightKg": 60.0, "reps": 12, "setType": "DROP"}
            ]
        """.trimIndent()

        val plans = parseSetPlansJson(
            jsonStr = json,
            fallbackSets = 3,
            fallbackWeight = 50.0,
            fallbackReps = 10
        )

        assertEquals(3, plans.size)
        assertEquals(100.0, plans[0].weightKg, 0.001)
        assertEquals(5, plans[0].reps)
        assertEquals(SetType.NORMAL, plans[0].setType)

        assertEquals(80.0, plans[1].weightKg, 0.001)
        assertEquals(10, plans[1].reps)
        assertEquals(SetType.NORMAL, plans[1].setType)

        assertEquals(60.0, plans[2].weightKg, 0.001)
        assertEquals(12, plans[2].reps)
        assertEquals(SetType.DROP, plans[2].setType)
    }

    @Test
    fun `fully-unknown list falls back to legacy`() {
        val json = """
            [
                "not an object",
                {"weightKg": "not-a-number"}
            ]
        """.trimIndent()

        val plans = parseSetPlansJson(
            jsonStr = json,
            fallbackSets = 4,
            fallbackWeight = 75.0,
            fallbackReps = 8
        )

        assertEquals(4, plans.size)
        plans.forEach { plan ->
            assertEquals(75.0, plan.weightKg, 0.001)
            assertEquals(8, plan.reps)
            assertEquals(SetType.NORMAL, plan.setType)
        }
    }

    @Test
    fun `malformed element skipped`() {
        val json = """
            [
                {"weightKg": 100.0, "reps": 5, "setType": "NORMAL"},
                "malformed-entry",
                {"weightKg": "invalid", "reps": 10},
                {"weightKg": 70.0, "reps": 12, "setType": "DROP"}
            ]
        """.trimIndent()

        val plans = parseSetPlansJson(
            jsonStr = json,
            fallbackSets = 3,
            fallbackWeight = 50.0,
            fallbackReps = 10
        )

        assertEquals(2, plans.size)
        assertEquals(100.0, plans[0].weightKg, 0.001)
        assertEquals(5, plans[0].reps)
        assertEquals(SetType.NORMAL, plans[0].setType)

        assertEquals(70.0, plans[1].weightKg, 0.001)
        assertEquals(12, plans[1].reps)
        assertEquals(SetType.DROP, plans[1].setType)
    }

    @Test
    fun `empty JSON array falls back to legacy`() {
        val json = "[]"

        val plans = parseSetPlansJson(
            jsonStr = json,
            fallbackSets = 3,
            fallbackWeight = 60.0,
            fallbackReps = 12
        )

        assertEquals(3, plans.size)
        plans.forEach { plan ->
            assertEquals(60.0, plan.weightKg, 0.001)
            assertEquals(12, plan.reps)
            assertEquals(SetType.NORMAL, plan.setType)
        }
    }
}
