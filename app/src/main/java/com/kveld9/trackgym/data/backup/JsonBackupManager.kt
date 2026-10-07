package com.kveld9.trackgym.data.backup

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class DuplicatePolicy {
    OVERWRITE_ALL,
    SKIP_EXISTING,
    DUPLICATE_ALL
}

@Serializable
data class ExerciseBackupDto(
    val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val category: String,
    val notes: String = "",
    val isCustom: Boolean = false,
    val createdAt: Long = 0
)

@Serializable
data class WorkoutSetBackupDto(
    val setNumber: Int,
    val setType: String,
    val weightKg: Double,
    val reps: Int,
    val rpe: Double? = null,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
)

@Serializable
data class WorkoutExerciseBackupDto(
    val exerciseName: String,
    val orderIndex: Int = 0,
    val notes: String = "",
    val supersetGroupId: String? = null,
    val sets: List<WorkoutSetBackupDto> = emptyList()
)

@Serializable
data class WorkoutBackupDto(
    val id: Long = 0,
    val title: String,
    val startedAt: Long,
    val completedAt: Long? = null,
    val durationSeconds: Long = 0,
    val isCompleted: Boolean = false,
    val notes: String = "",
    val exercises: List<WorkoutExerciseBackupDto> = emptyList()
)

@Serializable
data class PersonalRecordBackupDto(
    val exerciseName: String,
    val recordType: String,
    val recordValue: Double,
    val weightKg: Double,
    val reps: Int,
    val achievedAt: Long,
    val description: String = ""
)

@Serializable
data class GymBackupDto(
    val version: Int = 1,
    val exportedAt: String,
    val app: String = "TrackGym",
    val exercises: List<ExerciseBackupDto> = emptyList(),
    val workouts: List<WorkoutBackupDto> = emptyList(),
    val personalRecords: List<PersonalRecordBackupDto> = emptyList()
)

class JsonBackupManager {

    companion object {
        const val MAX_JSON_SIZE_CHARS = 10 * 1024 * 1024 // 10 MB
        const val MAX_WORKOUTS = 5000
    }

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun readBoundedStream(inputStream: InputStream, maxChars: Int = MAX_JSON_SIZE_CHARS): String {
        val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
        val buffer = CharArray(8192)
        val builder = StringBuilder()
        var totalChars = 0
        var charsRead: Int
        while (reader.read(buffer).also { charsRead = it } != -1) {
            totalChars += charsRead
            if (totalChars > maxChars) {
                throw IllegalArgumentException("El archivo de respaldo excede el tamaño máximo permitido (10 MB).")
            }
            builder.append(buffer, 0, charsRead)
        }
        return builder.toString()
    }

    fun exportToJson(
        exercises: List<ExerciseBackupDto>,
        workouts: List<WorkoutBackupDto>,
        personalRecords: List<PersonalRecordBackupDto>
    ): String {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val backupDto = GymBackupDto(
            version = 1,
            exportedAt = isoFormat.format(Date()),
            app = "TrackGym",
            exercises = exercises,
            workouts = workouts,
            personalRecords = personalRecords
        )
        return json.encodeToString(GymBackupDto.serializer(), backupDto)
    }

    fun parseAndValidateJson(jsonString: String): Result<GymBackupDto> {
        return runCatching {
            if (jsonString.length > MAX_JSON_SIZE_CHARS) {
                throw IllegalArgumentException("El archivo de respaldo excede el tamaño máximo permitido (10 MB).")
            }

            val dto = json.decodeFromString(GymBackupDto.serializer(), jsonString)
            if (dto.version > 1) {
                throw IllegalArgumentException("Versión de esquema no soportada: ${dto.version}")
            }
            if (dto.workouts.size > MAX_WORKOUTS) {
                throw IllegalArgumentException("El archivo contiene demasiados entrenamientos (${dto.workouts.size} > $MAX_WORKOUTS).")
            }
            dto
        }
    }
}
