package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.util.RoutineShareCodec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Base64

class RoutineShareCodecTest {

    @Test
    fun `unknown setType coerced to NORMAL with siblings preserved through decodeFromText`() {
        val jsonPayload = """
            {
                "version": 2,
                "name": "Shoulder Day",
                "notes": "Overhead strength",
                "exercises": [
                    {
                        "name": "Overhead Press",
                        "muscleGroup": "SHOULDERS",
                        "category": "BARBELL",
                        "targetSets": 3,
                        "defaultWeightKg": 50.0,
                        "defaultReps": 8,
                        "sets": [
                            {"weightKg": 50.0, "reps": 8, "setType": "NORMAL"},
                            {"weightKg": 45.0, "reps": 10, "setType": "SUPERSET"},
                            {"weightKg": 40.0, "reps": 12, "setType": "DROP"}
                        ]
                    }
                ]
            }
        """.trimIndent()
        val base64 = Base64.getEncoder().encodeToString(jsonPayload.toByteArray(Charsets.UTF_8))
        val shareText = "TrackGym Routine: Shoulder Day\n\ntgroutine:$base64"

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        assertEquals(2, decoded?.version)
        assertEquals("Shoulder Day", decoded?.name)
        assertEquals(1, decoded?.exercises?.size)

        val ex = decoded!!.exercises[0]
        assertEquals("Overhead Press", ex.name)
        assertEquals(3, ex.sets.size)

        assertEquals(50.0, ex.sets[0].weightKg, 0.001)
        assertEquals(8, ex.sets[0].reps)
        assertEquals(SetType.NORMAL, ex.sets[0].setType)

        assertEquals(45.0, ex.sets[1].weightKg, 0.001)
        assertEquals(10, ex.sets[1].reps)
        assertEquals(SetType.NORMAL, ex.sets[1].setType)

        assertEquals(40.0, ex.sets[2].weightKg, 0.001)
        assertEquals(12, ex.sets[2].reps)
        assertEquals(SetType.DROP, ex.sets[2].setType)
    }

    @Test
    fun `malformed element skipped`() {
        val jsonPayload = """
            {
                "version": 2,
                "name": "Leg Day",
                "notes": "Squat focus",
                "exercises": [
                    {
                        "name": "Barbell Squat",
                        "muscleGroup": "LEGS",
                        "category": "BARBELL",
                        "targetSets": 3,
                        "defaultWeightKg": 100.0,
                        "defaultReps": 5,
                        "sets": [
                            {"weightKg": 100.0, "reps": 5, "setType": "NORMAL"},
                            "malformed-entry",
                            {"weightKg": "invalid", "reps": 10},
                            {"weightKg": 70.0, "reps": 12, "setType": "DROP"}
                        ]
                    }
                ]
            }
        """.trimIndent()
        val base64 = Base64.getEncoder().encodeToString(jsonPayload.toByteArray(Charsets.UTF_8))
        val shareText = "TrackGym Routine: Leg Day\n\ntgroutine:$base64"

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        val ex = decoded!!.exercises[0]
        assertEquals(2, ex.sets.size)

        assertEquals(100.0, ex.sets[0].weightKg, 0.001)
        assertEquals(5, ex.sets[0].reps)
        assertEquals(SetType.NORMAL, ex.sets[0].setType)

        assertEquals(70.0, ex.sets[1].weightKg, 0.001)
        assertEquals(12, ex.sets[1].reps)
        assertEquals(SetType.DROP, ex.sets[1].setType)
    }

    @Test
    fun `fully-unknown sets fall back to legacy fields`() {
        val jsonPayload = """
            {
                "version": 2,
                "name": "Pull Day",
                "notes": "Back focus",
                "exercises": [
                    {
                        "name": "Pull Up",
                        "muscleGroup": "BACK",
                        "category": "BODYWEIGHT",
                        "targetSets": 4,
                        "defaultWeightKg": 75.0,
                        "defaultReps": 8,
                        "sets": [
                            "not an object",
                            {"weightKg": "not-a-number"}
                        ]
                    }
                ]
            }
        """.trimIndent()
        val base64 = Base64.getEncoder().encodeToString(jsonPayload.toByteArray(Charsets.UTF_8))
        val shareText = "TrackGym Routine: Pull Day\n\ntgroutine:$base64"

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        val ex = decoded!!.exercises[0]
        assertEquals(4, ex.sets.size)
        ex.sets.forEach { plan ->
            assertEquals(75.0, plan.weightKg, 0.001)
            assertEquals(8, plan.reps)
            assertEquals(SetType.NORMAL, plan.setType)
        }
    }

    @Test
    fun `garbage still null`() {
        val garbageCases = listOf(
            "",
            "not a share string",
            "tgroutine:!!!invalid-base64!!!",
            "tgroutine:" + Base64.getEncoder().encodeToString("not json at all".toByteArray(Charsets.UTF_8)),
            "tgroutine:" + Base64.getEncoder().encodeToString("""{"random": "value"}""".toByteArray(Charsets.UTF_8)),
            "tgroutine:" + Base64.getEncoder().encodeToString("""[1, 2, 3]""".toByteArray(Charsets.UTF_8)),
            "tgroutine:" + Base64.getEncoder().encodeToString("""{"name": "Bad Ex", "exercises": ["not-an-object"]}""".toByteArray(Charsets.UTF_8))
        )

        garbageCases.forEach { shareText ->
            val result = RoutineShareCodec.decodeFromText(shareText)
            assertNull("Expected null for input: $shareText", result)
        }
    }

    @Test
    fun `v1 payload still decodes`() {
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
        val base64 = Base64.getEncoder().encodeToString(v1Json.toByteArray(Charsets.UTF_8))
        val shareText = "TrackGym Routine: Chest Day\n\ntgroutine:$base64"

        val decoded = RoutineShareCodec.decodeFromText(shareText)
        assertNotNull(decoded)
        assertEquals(1, decoded?.version)
        assertEquals("Chest Day", decoded?.name)
        assertEquals("Classic chest focus", decoded?.notes)
        assertEquals(1, decoded?.exercises?.size)

        val ex = decoded!!.exercises[0]
        assertEquals("Barbell Bench Press", ex.name)
        assertEquals(4, ex.targetSets)
        assertEquals(80.0, ex.defaultWeightKg, 0.001)
        assertEquals(10, ex.defaultReps)
        assertTrue(ex.sets.isEmpty())
    }
}
