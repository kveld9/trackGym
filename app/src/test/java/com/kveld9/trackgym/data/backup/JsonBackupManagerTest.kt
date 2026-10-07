package com.kveld9.trackgym.data.backup

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream

class JsonBackupManagerTest {

    private val backupManager = JsonBackupManager()

    @Test
    fun exportToJson_and_parseAndValidateJson_roundTripSucceeds() {
        val exercises = listOf(
            ExerciseBackupDto(
                id = 1L,
                name = "Bench Press",
                muscleGroup = "CHEST",
                category = "BARBELL",
                notes = "Primary push movement",
                isCustom = false
            )
        )

        val sets = listOf(
            WorkoutSetBackupDto(
                setNumber = 1,
                setType = "NORMAL",
                weightKg = 80.0,
                reps = 8,
                rpe = 8.0,
                isCompleted = true
            )
        )

        val workoutExercises = listOf(
            WorkoutExerciseBackupDto(
                exerciseName = "Bench Press",
                orderIndex = 0,
                sets = sets
            )
        )

        val workouts = listOf(
            WorkoutBackupDto(
                id = 10L,
                title = "Push Day",
                startedAt = 1700000000000L,
                completedAt = 1700003600000L,
                durationSeconds = 3600,
                isCompleted = true,
                exercises = workoutExercises
            )
        )

        val records = listOf(
            PersonalRecordBackupDto(
                exerciseName = "Bench Press",
                recordType = "MAX_WEIGHT",
                recordValue = 80.0,
                weightKg = 80.0,
                reps = 8,
                achievedAt = 1700003600000L,
                description = "80 kg x 8 reps"
            )
        )

        val exportedJson = backupManager.exportToJson(exercises, workouts, records)
        assertTrue(exportedJson.contains("TrackGym"))
        assertTrue(exportedJson.contains("Bench Press"))

        val validationResult = backupManager.parseAndValidateJson(exportedJson)
        assertTrue(validationResult.isSuccess)

        val dto = validationResult.getOrThrow()
        assertEquals(1, dto.version)
        assertEquals("TrackGym", dto.app)
        assertEquals(1, dto.exercises.size)
        assertEquals("Bench Press", dto.exercises[0].name)
        assertEquals(1, dto.workouts.size)
        assertEquals("Push Day", dto.workouts[0].title)
        assertEquals(1, dto.workouts[0].exercises[0].sets.size)
        assertEquals(80.0, dto.workouts[0].exercises[0].sets[0].weightKg, 0.001)
        assertEquals(1, dto.personalRecords.size)
    }

    @Test
    fun parseAndValidateJson_futureVersion_failsValidation() {
        val futureJson = """
            {
                "version": 999,
                "exportedAt": "2026-10-06T00:00:00Z",
                "app": "TrackGym",
                "exercises": [],
                "workouts": [],
                "personalRecords": []
            }
        """.trimIndent()

        val result = backupManager.parseAndValidateJson(futureJson)
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("Versión de esquema no soportada") == true)
    }

    @Test
    fun readBoundedStream_readsSmallStreamSuccessfully() {
        val sampleText = "{\"app\":\"TrackGym\"}"
        val stream = ByteArrayInputStream(sampleText.toByteArray(Charsets.UTF_8))
        val result = backupManager.readBoundedStream(stream, maxChars = 1000)
        assertEquals(sampleText, result)
    }

    @Test
    fun readBoundedStream_exceedingSize_throwsIllegalArgumentException() {
        val sampleText = "a".repeat(200)
        val stream = ByteArrayInputStream(sampleText.toByteArray(Charsets.UTF_8))
        try {
            backupManager.readBoundedStream(stream, maxChars = 50)
            org.junit.Assert.fail("Expected IllegalArgumentException for exceeding size")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("tamaño máximo") == true)
        }
    }
}
