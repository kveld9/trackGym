package com.kveld9.trackgym.data.backup

import com.kveld9.trackgym.domain.calculator.MechanicsClassifier
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Standard CSV workout record parsed from supported CSV backups or generic CSV spreadsheets.
 */
data class CsvWorkoutRow(
    val date: String,
    val workoutName: String,
    val exerciseName: String,
    val setOrder: Int,
    val weight: Double,
    val weightUnit: String,
    val reps: Int,
    val rpe: Double? = null,
    val notes: String = ""
)

object CsvWorkoutImporter {

    private val DATE_FORMATS = listOf(
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyy-MM-dd",
        "MM/dd/yyyy HH:mm:ss",
        "MM/dd/yyyy HH:mm",
        "MM/dd/yyyy",
        "dd/MM/yyyy HH:mm:ss",
        "dd/MM/yyyy HH:mm",
        "dd/MM/yyyy"
    )

    /**
     * Parses a CSV stream into structured [GymBackupDto] for repository insertion.
     */
    fun parseCsvToBackupDto(inputStream: InputStream): GymBackupDto {
        val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
        val lines = reader.readLines().filter { it.isNotBlank() }
        if (lines.isEmpty()) {
            return GymBackupDto(exportedAt = "", app = "CSV")
        }

        val headerLine = lines.first()
        val headers = parseCsvLine(headerLine).map { it.trim().lowercase(Locale.ROOT) }

        // Detect column indices
        val dateIdx = headers.indexOfFirst { it.contains("date") }
        val workoutIdx = headers.indexOfFirst { it.contains("workout") || it.contains("routine") }
        val exerciseIdx = headers.indexOfFirst { it.contains("exercise") }
        val setOrderIdx = headers.indexOfFirst { it.contains("set") }
        val weightIdx = headers.indexOfFirst { it.contains("weight") || it.contains("kg") || it.contains("lbs") }
        val repsIdx = headers.indexOfFirst { it.contains("rep") }
        val rpeIdx = headers.indexOfFirst { it.contains("rpe") }
        val notesIdx = headers.indexOfFirst { it.contains("note") || it.contains("comment") }

        if (dateIdx == -1 || exerciseIdx == -1) {
            throw IllegalArgumentException("CSV missing required columns: 'Date' and 'Exercise'")
        }

        // Group rows by Date and Workout Name
        val rows = mutableListOf<CsvWorkoutRow>()
        for (i in 1 until lines.size) {
            val cols = parseCsvLine(lines[i])
            if (cols.size <= dateIdx || cols.size <= exerciseIdx) continue

            val dateStr = cols.getOrNull(dateIdx)?.trim().orEmpty()
            if (dateStr.isBlank()) continue

            val workoutName = if (workoutIdx != -1) cols.getOrNull(workoutIdx)?.trim().orEmpty() else "Workout"
            val exerciseName = cols.getOrNull(exerciseIdx)?.trim().orEmpty()
            if (exerciseName.isBlank()) continue

            val setOrder = if (setOrderIdx != -1) cols.getOrNull(setOrderIdx)?.toIntOrNull() ?: 1 else 1
            val rawWeight = if (weightIdx != -1) cols.getOrNull(weightIdx)?.trim()?.replace(',', '.')?.toDoubleOrNull() ?: 0.0 else 0.0
            val reps = if (repsIdx != -1) cols.getOrNull(repsIdx)?.trim()?.toIntOrNull() ?: 0 else 0
            val rpe = if (rpeIdx != -1) cols.getOrNull(rpeIdx)?.trim()?.replace(',', '.')?.toDoubleOrNull() else null
            val notes = if (notesIdx != -1) cols.getOrNull(notesIdx)?.trim().orEmpty() else ""

            rows.add(
                CsvWorkoutRow(
                    date = dateStr,
                    workoutName = if (workoutName.isNotBlank()) workoutName else "Workout",
                    exerciseName = exerciseName,
                    setOrder = setOrder,
                    weight = rawWeight,
                    weightUnit = "kg",
                    reps = reps,
                    rpe = rpe,
                    notes = notes
                )
            )
        }

        // Group rows by workout session (date string + workout name)
        val groupedWorkouts = rows.groupBy { "${it.date}_${it.workoutName}" }

        val workoutDtos = mutableListOf<WorkoutBackupDto>()
        val exerciseSet = mutableSetOf<String>()

        groupedWorkouts.forEach { (_, sessionRows) ->
            val first = sessionRows.first()
            val timestamp = parseTimestamp(first.date)
            
            // Group exercises within the workout
            val exercisesInWorkout = sessionRows.groupBy { it.exerciseName }
            val exerciseDtos = mutableListOf<WorkoutExerciseBackupDto>()

            exercisesInWorkout.entries.forEachIndexed { exIndex, (exName, setRows) ->
                exerciseSet.add(exName)
                val setDtos = setRows.mapIndexed { sIndex, sRow ->
                    WorkoutSetBackupDto(
                        setNumber = sIndex + 1,
                        setType = SetType.NORMAL.name,
                        weightKg = sRow.weight,
                        reps = sRow.reps,
                        rpe = sRow.rpe,
                        isCompleted = true,
                        completedAt = timestamp + (sIndex * 60_000L)
                    )
                }

                exerciseDtos.add(
                    WorkoutExerciseBackupDto(
                        exerciseName = exName,
                        orderIndex = exIndex,
                        notes = setRows.firstOrNull { it.notes.isNotBlank() }?.notes.orEmpty(),
                        sets = setDtos
                    )
                )
            }

            workoutDtos.add(
                WorkoutBackupDto(
                    title = first.workoutName,
                    startedAt = timestamp,
                    completedAt = timestamp + (exerciseDtos.size * 300_000L),
                    durationSeconds = (exerciseDtos.size * 300L).coerceAtLeast(60L),
                    isCompleted = true,
                    notes = "",
                    exercises = exerciseDtos
                )
            )
        }

        val exerciseDtos = exerciseSet.map { name ->
            ExerciseBackupDto(
                name = name,
                muscleGroup = MuscleGroup.OTHER.name,
                category = ExerciseCategory.BARBELL.name,
                isCustom = true,
                mechanics = MechanicsClassifier.classify(name).name
            )
        }

        return GymBackupDto(
            version = 1,
            exportedAt = System.currentTimeMillis().toString(),
            app = "CSV_Import",
            exercises = exerciseDtos,
            workouts = workoutDtos,
            personalRecords = emptyList()
        )
    }

    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        val sb = StringBuilder()
        var inQuotes = false
        var i = 0
        while (i < line.length) {
            val c = line[i]
            when {
                c == '"' -> {
                    if (inQuotes && i + 1 < line.length && line[i + 1] == '"') {
                        sb.append('"')
                        i++
                    } else {
                        inQuotes = !inQuotes
                    }
                }
                c == ',' && !inQuotes -> {
                    result.add(sb.toString().trim())
                    sb.clear()
                }
                c == ';' && !inQuotes -> {
                    // Support semicolon CSV delimiter
                    result.add(sb.toString().trim())
                    sb.clear()
                }
                else -> sb.append(c)
            }
            i++
        }
        result.add(sb.toString().trim())
        return result
    }

    private fun parseTimestamp(dateStr: String): Long {
        for (pattern in DATE_FORMATS) {
            try {
                val sdf = SimpleDateFormat(pattern, Locale.US).apply {
                    isLenient = false
                }
                val parsed = sdf.parse(dateStr)
                if (parsed != null) {
                    return parsed.time
                }
            } catch (_: Exception) {
            }
        }
        return System.currentTimeMillis()
    }
}
