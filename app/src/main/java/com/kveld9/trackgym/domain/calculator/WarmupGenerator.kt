package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet

data class WarmupSetConfig(
    val percentage: Double,
    val reps: Int
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

    /**
     * Generates a list of [WorkoutSet] warmup sets for a target working weight.
     * Weights are rounded to the nearest 2.5 kg step.
     */
    fun generateWarmupSets(
        targetWeightKg: Double,
        workoutExerciseId: Long = 0,
        minWeightKg: Double = 20.0,
        protocol: List<WarmupSetConfig> = DEFAULT_WARMUP_PROTOCOL
    ): List<WorkoutSet> {
        if (targetWeightKg <= minWeightKg) {
            return emptyList()
        }

        return protocol.mapNotNull { step ->
            val rawWeight = (targetWeightKg * step.percentage).coerceAtLeast(minWeightKg)
            val roundedWeight = kotlin.math.round(rawWeight / 2.5) * 2.5
            if (roundedWeight >= targetWeightKg) null else Pair(roundedWeight, step.reps)
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
