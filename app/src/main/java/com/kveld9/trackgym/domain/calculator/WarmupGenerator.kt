package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet
import kotlin.math.roundToInt

data class WarmupSetConfig(
    val percentage: Double,
    val reps: Int,
    val isBarOnly: Boolean = false
)

/**
 * Calculates progressive warm-up sets based on a target working weight.
 */
object WarmupGenerator {

    /**
     * Standard warm-up progression protocol:
     * - Warmup 1: Barbell / light starting load (~40% or minimum 20kg), 10 reps
     * - Warmup 2: ~60% of work weight, 5 reps
     * - Warmup 3: ~75% of work weight, 3 reps
     * - Warmup 4: ~85% of work weight, 1 rep
     */
    val DEFAULT_WARMUP_PROTOCOL = listOf(
        WarmupSetConfig(percentage = 0.40, reps = 10),
        WarmupSetConfig(percentage = 0.60, reps = 5),
        WarmupSetConfig(percentage = 0.75, reps = 3),
        WarmupSetConfig(percentage = 0.85, reps = 1)
    )

    val PRESET_STANDARD = listOf(
        WarmupSetConfig(percentage = 0.40, reps = 10, isBarOnly = true),
        WarmupSetConfig(percentage = 0.60, reps = 5),
        WarmupSetConfig(percentage = 0.75, reps = 3),
        WarmupSetConfig(percentage = 0.85, reps = 1)
    )

    val PRESET_PYRAMID = listOf(
        WarmupSetConfig(percentage = 0.50, reps = 5),
        WarmupSetConfig(percentage = 0.70, reps = 3),
        WarmupSetConfig(percentage = 0.85, reps = 1)
    )

    val PRESET_VOLUME = listOf(
        WarmupSetConfig(percentage = 0.40, reps = 10, isBarOnly = true),
        WarmupSetConfig(percentage = 0.50, reps = 8),
        WarmupSetConfig(percentage = 0.65, reps = 5),
        WarmupSetConfig(percentage = 0.75, reps = 3),
        WarmupSetConfig(percentage = 0.85, reps = 1)
    )

    val PRESET_EXPRESS = listOf(
        WarmupSetConfig(percentage = 0.50, reps = 5),
        WarmupSetConfig(percentage = 0.75, reps = 2)
    )

    /**
     * Encodes a list of [WarmupSetConfig] into a compact deterministic string representation.
     * Format example: "BAR:10,50:5,70:3,85:1"
     */
    fun encodeProtocol(protocol: List<WarmupSetConfig>): String {
        return protocol.joinToString(",") { step ->
            if (step.isBarOnly) {
                "BAR:${step.reps}"
            } else {
                val pct = (step.percentage * 100).roundToInt()
                "$pct:${step.reps}"
            }
        }
    }

    /**
     * Decodes a compact string into a list of [WarmupSetConfig].
     * Returns null if string is empty, null, or invalid.
     */
    fun decodeProtocol(encoded: String?): List<WarmupSetConfig>? {
        if (encoded.isNullOrBlank()) return null
        return try {
            val steps = encoded.split(",").mapNotNull { token ->
                val trimmed = token.trim()
                val parts = trimmed.split(":")
                if (parts.size != 2) return@mapNotNull null
                val reps = parts[1].trim().toIntOrNull() ?: return@mapNotNull null
                val firstPart = parts[0].trim()
                if (firstPart.equals("BAR", ignoreCase = true) || firstPart == "0") {
                    WarmupSetConfig(percentage = 0.0, reps = reps, isBarOnly = true)
                } else {
                    val cleanPct = firstPart.replace("%", "").toDoubleOrNull() ?: return@mapNotNull null
                    val pct = if (cleanPct > 1.0) cleanPct / 100.0 else cleanPct
                    WarmupSetConfig(percentage = pct, reps = reps, isBarOnly = false)
                }
            }
            if (steps.isEmpty()) null else steps
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Generates a list of [WorkoutSet] warmup sets for a target working weight.
     * Weights are rounded to the nearest [roundToKg] step (default 2.5 kg).
     */
    fun generateWarmupSets(
        targetWeightKg: Double,
        workoutExerciseId: Long = 0,
        minWeightKg: Double = 20.0,
        roundToKg: Double = 2.5,
        protocol: List<WarmupSetConfig> = DEFAULT_WARMUP_PROTOCOL
    ): List<WorkoutSet> {
        val safeMin = if (minWeightKg.isFinite() && minWeightKg >= 0.0) minWeightKg else 20.0
        val safeRound = if (roundToKg.isFinite() && roundToKg > 0.0) roundToKg else 2.5
        if (!targetWeightKg.isFinite() || targetWeightKg <= safeMin) {
            return emptyList()
        }

        return protocol.mapNotNull { step ->
            val rawWeight = if (step.isBarOnly) {
                safeMin
            } else {
                (targetWeightKg * step.percentage).coerceAtLeast(safeMin)
            }
            val roundedWeight = kotlin.math.round(rawWeight / safeRound) * safeRound
            if (!roundedWeight.isFinite() || roundedWeight >= targetWeightKg) null else Pair(roundedWeight, step.reps)
        }
            .distinctBy { it.first }
            .mapIndexed { index, (weight, reps) ->
                WorkoutSet(
                    workoutExerciseId = workoutExerciseId,
                    setNumber = index + 1,
                    setType = SetType.WARMUP,
                    weightKg = weight,
                    reps = reps,
                    isCompleted = false
                )
            }
    }
}
