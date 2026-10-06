package com.kveld9.trackgym.data.backup

import com.kveld9.trackgym.domain.calculator.OneRepMaxCalculator
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Standard CSV exporter formatting completed workout sessions and sets
 * into a universally compatible tabular structure for Excel, Google Sheets, Strong, and Hevy.
 */
object CsvWorkoutExporter {

    private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun exportToCsv(workouts: List<WorkoutBackupDto>, outputStream: OutputStream) {
        val writer = OutputStreamWriter(outputStream, Charsets.UTF_8)
        writer.use { out ->
            out.write("Date,Workout,Exercise,Set,Weight,Unit,Reps,RPE,1RM,Notes\n")

            for (workout in workouts) {
                val dateStr = escapeCsv(DATE_FORMAT.format(Date(workout.startedAt)))
                val workoutName = escapeCsv(workout.title)
                val workoutNotes = escapeCsv(workout.notes)

                for (we in workout.exercises) {
                    val exerciseName = escapeCsv(we.exerciseName)
                    for (set in we.sets) {
                        if (!set.isCompleted && set.weightKg == 0.0 && set.reps == 0) continue
                        val oneRm = OneRepMaxCalculator.calculate1RM(set.weightKg, set.reps)
                        val rpeStr = set.rpe?.toString().orEmpty()
                        val notes = if (we.notes.isNotBlank()) escapeCsv(we.notes) else workoutNotes

                        val line = buildString {
                            append(dateStr).append(',')
                            append(workoutName).append(',')
                            append(exerciseName).append(',')
                            append(set.setNumber).append(',')
                            append(set.weightKg).append(',')
                            append("kg,")
                            append(set.reps).append(',')
                            append(rpeStr).append(',')
                            append(oneRm).append(',')
                            append(notes)
                            append('\n')
                        }
                        out.write(line)
                    }
                }
            }
            out.flush()
        }
    }

    private fun escapeCsv(value: String): String {
        if (!value.contains(',') && !value.contains('"') && !value.contains('\n') && !value.contains('\r')) {
            return value
        }
        return "\"" + value.replace("\"", "\"\"") + "\""
    }
}
