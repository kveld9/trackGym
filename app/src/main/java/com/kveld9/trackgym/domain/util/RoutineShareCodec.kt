package com.kveld9.trackgym.domain.util

import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineSetPlan
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
    val defaultReps: Int = 10,
    val sets: List<RoutineSetPlan> = emptyList()
)

@Serializable
data class RoutineShareDto(
    val version: Int = 2,
    val name: String,
    val notes: String = "",
    val exercises: List<RoutineShareExerciseDto> = emptyList()
)

object RoutineShareCodec {
    private const val PREFIX = "tgroutine:"
    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private fun base64Encode(bytes: ByteArray): String {
        return try {
            android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
        } catch (_: Throwable) {
            java.util.Base64.getEncoder().encodeToString(bytes)
        }
    }

    private fun base64Decode(token: String): ByteArray {
        return try {
            android.util.Base64.decode(token, android.util.Base64.DEFAULT)
        } catch (_: Throwable) {
            java.util.Base64.getDecoder().decode(token)
        }
    }

    private fun formatWeight(weightKg: Double): String {
        return if (weightKg % 1.0 == 0.0) {
            weightKg.toInt().toString()
        } else {
            weightKg.toString()
        }
    }

    fun encodeToShareText(routine: Routine): String {
        val dto = RoutineShareDto(
            version = 2,
            name = routine.name,
            notes = routine.notes,
            exercises = routine.exercises.map { re ->
                RoutineShareExerciseDto(
                    name = re.exercise.name,
                    muscleGroup = re.exercise.muscleGroup.name,
                    category = re.exercise.category.name,
                    targetSets = re.targetSets,
                    defaultWeightKg = re.defaultWeightKg,
                    defaultReps = re.defaultReps,
                    sets = re.sets
                )
            }
        )
        val jsonString = json.encodeToString(RoutineShareDto.serializer(), dto)
        val base64 = base64Encode(jsonString.toByteArray(StandardCharsets.UTF_8))

        val summaryBuilder = StringBuilder()
        summaryBuilder.append("TrackGym Routine: ").append(routine.name).append("\n")
        if (routine.notes.isNotBlank()) {
            summaryBuilder.append(routine.notes).append("\n")
        }
        summaryBuilder.append("\nExercises:\n")
        routine.exercises.forEach { re ->
            summaryBuilder.append("- ").append(re.exercise.name).append(": ")
            val plans = re.sets
            val allSame = plans.isNotEmpty() && plans.all {
                it.weightKg == plans[0].weightKg && it.reps == plans[0].reps
            }
            if (plans.isEmpty() || allSame) {
                summaryBuilder.append(re.targetSets).append(" sets")
                if (re.defaultWeightKg > 0.0 || re.defaultReps > 0) {
                    val w = formatWeight(re.defaultWeightKg)
                    summaryBuilder.append(" ($w kg x ${re.defaultReps} reps)")
                }
            } else {
                val setsDesc = plans.joinToString(", ") { plan ->
                    "${formatWeight(plan.weightKg)}kg x${plan.reps}"
                }
                summaryBuilder.append(plans.size).append(" sets (").append(setsDesc).append(")")
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
            val decodedBytes = base64Decode(token)
            val jsonString = String(decodedBytes, StandardCharsets.UTF_8)
            json.decodeFromString(RoutineShareDto.serializer(), jsonString)
        } catch (_: Exception) {
            null
        }
    }
}
