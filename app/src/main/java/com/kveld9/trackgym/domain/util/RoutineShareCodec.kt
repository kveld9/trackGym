package com.kveld9.trackgym.domain.util

import android.util.Base64
import com.kveld9.trackgym.domain.model.Routine
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

@Serializable
data class RoutineShareExerciseDto(
    val name: String,
    val muscleGroup: String = "OTHER",
    val category: String = "OTHER",
    val targetSets: Int = 3,
    val defaultWeightKg: Double = 0.0,
    val defaultReps: Int = 10
)

@Serializable
data class RoutineShareDto(
    val version: Int = 1,
    val name: String,
    val notes: String = "",
    val exercises: List<RoutineShareExerciseDto> = emptyList()
)

object RoutineShareCodec {
    private const val PREFIX = "tgroutine:"
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun encodeToShareText(routine: Routine): String {
        val dto = RoutineShareDto(
            version = 1,
            name = routine.name,
            notes = routine.notes,
            exercises = routine.exercises.map { re ->
                RoutineShareExerciseDto(
                    name = re.exercise.name,
                    muscleGroup = re.exercise.muscleGroup.name,
                    category = re.exercise.category.name,
                    targetSets = re.targetSets,
                    defaultWeightKg = re.defaultWeightKg,
                    defaultReps = re.defaultReps
                )
            }
        )
        val jsonString = json.encodeToString(RoutineShareDto.serializer(), dto)
        val base64 = Base64.encodeToString(jsonString.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP)

        val summaryBuilder = StringBuilder()
        summaryBuilder.append("TrackGym Routine: ").append(routine.name).append("\n")
        if (routine.notes.isNotBlank()) {
            summaryBuilder.append(routine.notes).append("\n")
        }
        summaryBuilder.append("\nExercises:\n")
        routine.exercises.forEach { re ->
            summaryBuilder.append("- ").append(re.exercise.name).append(": ")
                .append(re.targetSets).append(" sets")
            if (re.defaultWeightKg > 0.0 || re.defaultReps > 0) {
                summaryBuilder.append(" (").append(re.defaultWeightKg).append(" kg x ").append(re.defaultReps).append(" reps)")
            }
            summaryBuilder.append("\n")
        }
        summaryBuilder.append("\n").append(PREFIX).append(base64)
        return summaryBuilder.toString()
    }

    fun decodeFromText(rawText: String): RoutineShareDto? {
        val tokenIndex = rawText.indexOf(PREFIX)
        val token = if (tokenIndex != -1) {
            rawText.substring(tokenIndex + PREFIX.length).trim().takeWhile { !it.isWhitespace() }
        } else {
            rawText.trim()
        }
        return try {
            val decodedBytes = Base64.decode(token, Base64.DEFAULT)
            val jsonString = String(decodedBytes, StandardCharsets.UTF_8)
            json.decodeFromString(RoutineShareDto.serializer(), jsonString)
        } catch (_: Exception) {
            null
        }
    }
}
