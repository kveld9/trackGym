package com.kveld9.trackgym.data.backup

import org.junit.Assert.assertTrue
import org.junit.Test
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
}
