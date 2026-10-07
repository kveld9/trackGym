package com.kveld9.trackgym.data.backup

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class CsvWorkoutExportImportTest {

    @Test
    fun exportToCsv_writesExpectedCsvHeaderAndRows() {
        val sets = listOf(
            WorkoutSetBackupDto(
                setNumber = 1,
                setType = "NORMAL",
                weightKg = 100.0,
                reps = 5,
                rpe = 8.5,
                isCompleted = true
            )
        )

        val exercises = listOf(
            WorkoutExerciseBackupDto(
                exerciseName = "Barbell Squat",
                orderIndex = 0,
                notes = "Felt solid",
                sets = sets
            )
        )

        val workouts = listOf(
            WorkoutBackupDto(
                id = 1L,
                title = "Leg Day Heavy",
                startedAt = 1700000000000L,
                completedAt = 1700003600000L,
                durationSeconds = 3600,
                isCompleted = true,
                notes = "Good depth",
                exercises = exercises
            )
        )

        val out = ByteArrayOutputStream()
        CsvWorkoutExporter.exportToCsv(workouts, out)
        val csvContent = out.toString(Charsets.UTF_8.name())

        assertTrue(csvContent.startsWith("Date,Workout,Exercise,Set,Weight,Unit,Reps,RPE,1RM,Notes\n"))
        assertTrue(csvContent.contains("Leg Day Heavy"))
        assertTrue(csvContent.contains("Barbell Squat"))
        assertTrue(csvContent.contains("100.0,kg,5,8.5"))
    }

    @Test
    fun parseCsvToBackupDto_parsesExportedCsvCorrectly() {
        val csvData = """
            Date,Workout,Exercise,Set,Weight,Unit,Reps,RPE,1RM,Notes
            2026-10-06 14:00:00,Leg Day Heavy,Barbell Squat,1,100.0,kg,5,8.5,116.7,Felt solid
            2026-10-06 14:00:00,Leg Day Heavy,Barbell Squat,2,100.0,kg,5,9.0,116.7,Felt solid
        """.trimIndent()

        val inputStream = ByteArrayInputStream(csvData.toByteArray(Charsets.UTF_8))
        val backupDto = CsvWorkoutImporter.parseCsvToBackupDto(inputStream)

        assertEquals("CSV_Import", backupDto.app)
        assertEquals(1, backupDto.exercises.size)
        assertEquals("Barbell Squat", backupDto.exercises[0].name)

        assertEquals(1, backupDto.workouts.size)
        val workout = backupDto.workouts[0]
        assertEquals("Leg Day Heavy", workout.title)
        assertEquals(1, workout.exercises.size)

        val squat = workout.exercises[0]
        assertEquals("Barbell Squat", squat.exerciseName)
        assertEquals(2, squat.sets.size)
        assertEquals(100.0, squat.sets[0].weightKg, 0.001)
        assertEquals(5, squat.sets[0].reps)
        assertEquals(8.5, squat.sets[0].rpe ?: 0.0, 0.001)
        assertEquals(9.0, squat.sets[1].rpe ?: 0.0, 0.001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun parseCsvToBackupDto_missingRequiredHeaders_throwsException() {
        val invalidCsv = "Col1,Col2,Col3\n1,2,3"
        val inputStream = ByteArrayInputStream(invalidCsv.toByteArray(Charsets.UTF_8))
        CsvWorkoutImporter.parseCsvToBackupDto(inputStream)
    }
}
