package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutSet
import kotlin.math.roundToInt

/**
 * Result data class representing the work-to-rest time breakdown for a workout session.
 */
data class WorkToRestRatioResult(
    val workSeconds: Long,
    val restSeconds: Long,
    val totalSeconds: Long,
    val workPercentage: Double,
    val restPercentage: Double,
    val ratioString: String
)

/**
 * Pure domain engine that analyzes work-to-rest time distribution for a workout session.
 *
 * Work time is computed from:
 * - Actual duration of time-based sets (DURATION / CARDIO)
 * - Estimated duration of rep-based lifting sets (defaulting to 3 seconds per rep under tension,
 *   e.g. 8 reps * 3s = 24s)
 *
 * Rest time is bounded between 0 and (totalDuration - workSeconds) or explicit tracked rest.
 */
object WorkToRestRatioEngine {

    const val DEFAULT_SECONDS_PER_REP = 3

    /**
     * Estimates work duration in seconds for a single completed set.
     */
    fun estimateSetWorkSeconds(set: WorkoutSet, secondsPerRep: Int = DEFAULT_SECONDS_PER_REP): Long {
        if (!set.isCompleted) return 0L

        return when {
            set.setType == SetType.DURATION || set.setType == SetType.CARDIO -> {
                (set.durationSeconds ?: set.reps).coerceAtLeast(0).toLong()
            }
            set.reps > 0 -> {
                (set.reps.toLong() * secondsPerRep.coerceAtLeast(1))
            }
            else -> 0L
        }
    }

    /**
     * Calculates the total estimated work time (seconds) across all exercises and sets in a workout.
     */
    fun calculateTotalWorkSeconds(workout: Workout, secondsPerRep: Int = DEFAULT_SECONDS_PER_REP): Long {
        return workout.exercises.sumOf { exercise ->
            exercise.sets.sumOf { set ->
                estimateSetWorkSeconds(set, secondsPerRep)
            }
        }
    }

    /**
     * Computes the complete [WorkToRestRatioResult] for a workout.
     *
     * @param workout The workout session to analyze
     * @param totalSessionDurationSeconds Total elapsed duration of the workout session
     * @param recordedRestSeconds Optional explicit rest seconds recorded by rest timers
     * @param secondsPerRep Average time under tension per rep for rep-based sets
     */
    fun calculateRatio(
        workout: Workout,
        totalSessionDurationSeconds: Long = workout.durationSeconds,
        recordedRestSeconds: Long? = null,
        secondsPerRep: Int = DEFAULT_SECONDS_PER_REP
    ): WorkToRestRatioResult {
        val estimatedWork = calculateTotalWorkSeconds(workout, secondsPerRep)
        val sessionTotal = totalSessionDurationSeconds.coerceAtLeast(0L)

        // Work time cannot exceed the total session duration if session duration is greater than 0
        val finalWork = if (sessionTotal > 0L) {
            estimatedWork.coerceAtMost(sessionTotal)
        } else {
            estimatedWork
        }

        // Determine rest duration
        val finalRest = when {
            recordedRestSeconds != null && recordedRestSeconds >= 0L -> {
                if (sessionTotal > 0L) {
                    recordedRestSeconds.coerceAtMost((sessionTotal - finalWork).coerceAtLeast(0L))
                } else {
                    recordedRestSeconds
                }
            }
            sessionTotal > finalWork -> {
                sessionTotal - finalWork
            }
            else -> 0L
        }

        val totalCombined = (finalWork + finalRest).coerceAtLeast(0L)
        val workPct = if (totalCombined > 0L) {
            (finalWork.toDouble() / totalCombined.toDouble()) * 100.0
        } else {
            0.0
        }
        val restPct = if (totalCombined > 0L) {
            (finalRest.toDouble() / totalCombined.toDouble()) * 100.0
        } else {
            0.0
        }

        val roundedWorkPct = (workPct * 10.0).roundToInt() / 10.0
        val roundedRestPct = (restPct * 10.0).roundToInt() / 10.0

        val ratioString = formatRatio(finalWork, finalRest)

        return WorkToRestRatioResult(
            workSeconds = finalWork,
            restSeconds = finalRest,
            totalSeconds = totalCombined,
            workPercentage = roundedWorkPct,
            restPercentage = roundedRestPct,
            ratioString = ratioString
        )
    }

    /**
     * Formats work and rest seconds into a clean simplified ratio string, e.g. "1:2.5" or "1:3".
     */
    fun formatRatio(workSeconds: Long, restSeconds: Long): String {
        if (workSeconds <= 0L && restSeconds <= 0L) return "0:0"
        if (workSeconds <= 0L) return "0:1"
        if (restSeconds <= 0L) return "1:0"

        val ratio = restSeconds.toDouble() / workSeconds.toDouble()
        val rounded = (ratio * 10.0).roundToInt() / 10.0
        return if (rounded == rounded.toLong().toDouble()) {
            "1:${rounded.toLong()}"
        } else {
            "1:$rounded"
        }
    }
}
