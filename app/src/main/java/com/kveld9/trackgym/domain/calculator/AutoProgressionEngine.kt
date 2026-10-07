package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet
import kotlin.math.round

data class ProgressionRule(
    val enabled: Boolean = true,
    val targetReps: Int = 10,
    val incrementKg: Double = 2.5,
    val failureThreshold: Int = 2,
    val deloadPercentage: Double = 10.0
)

enum class ProgressionDecisionType {
    PROGRESSION,
    DELOAD,
    MAINTAIN
}

data class AutoProgressionResult(
    val type: ProgressionDecisionType,
    val baseWeightKg: Double,
    val recommendedWeightKg: Double,
    val weightDeltaKg: Double,
    val consecutiveFailures: Int,
    val failureThreshold: Int,
    val explanationRes: Int
)

/**
 * Rule-based auto-progression and auto-deload engine.
 * Evaluates performance across recent workouts against lifter-defined rules.
 */
object AutoProgressionEngine {

    const val DEFAULT_TARGET_REPS = 10
    const val DEFAULT_INCREMENT_KG = 2.5
    const val DEFAULT_FAILURE_THRESHOLD = 2
    const val DEFAULT_DELOAD_PERCENTAGE = 10.0

    val DEFAULT_RULE = ProgressionRule(
        enabled = true,
        targetReps = DEFAULT_TARGET_REPS,
        incrementKg = DEFAULT_INCREMENT_KG,
        failureThreshold = DEFAULT_FAILURE_THRESHOLD,
        deloadPercentage = DEFAULT_DELOAD_PERCENTAGE
    )

    /**
     * Serializes a [ProgressionRule] into a compact deterministic string.
     * Format: "EN=1;REPS=10;INC=2.5;FAIL=2;DELOAD=10.0"
     */
    fun encode(rule: ProgressionRule): String {
        val en = if (rule.enabled) "1" else "0"
        return "EN=$en;REPS=${rule.targetReps};INC=${rule.incrementKg};FAIL=${rule.failureThreshold};DELOAD=${rule.deloadPercentage}"
    }

    /**
     * Parses a serialized string into a [ProgressionRule].
     * Returns null if string is empty or invalid.
     */
    fun decode(encoded: String?): ProgressionRule? {
        if (encoded.isNullOrBlank()) return null
        return try {
            val map = encoded.split(";").associate {
                val pair = it.split("=")
                pair[0].trim().uppercase() to pair.getOrElse(1) { "" }.trim()
            }
            val enabled = map["EN"] == "1"
            val reps = map["REPS"]?.toIntOrNull() ?: DEFAULT_TARGET_REPS
            val inc = map["INC"]?.toDoubleOrNull() ?: DEFAULT_INCREMENT_KG
            val fail = map["FAIL"]?.toIntOrNull() ?: DEFAULT_FAILURE_THRESHOLD
            val deload = map["DELOAD"]?.toDoubleOrNull() ?: DEFAULT_DELOAD_PERCENTAGE

            ProgressionRule(
                enabled = enabled,
                targetReps = reps,
                incrementKg = inc,
                failureThreshold = fail,
                deloadPercentage = deload
            )
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Evaluates recent workout sessions (ordered newest to oldest) against the progression rule.
     */
    fun evaluate(
        rule: ProgressionRule,
        recentSessionsSets: List<List<WorkoutSet>>,
        currentWeightKg: Double,
        roundingStepKg: Double = 2.5
    ): AutoProgressionResult {
        if (!rule.enabled || recentSessionsSets.isEmpty() || currentWeightKg <= 0.0) {
            return AutoProgressionResult(
                type = ProgressionDecisionType.MAINTAIN,
                baseWeightKg = currentWeightKg,
                recommendedWeightKg = currentWeightKg,
                weightDeltaKg = 0.0,
                consecutiveFailures = 0,
                failureThreshold = rule.failureThreshold,
                explanationRes = R.string.progression_maintain_no_history
            )
        }

        // Evaluate session success/failure for each historical session
        val sessionResults = recentSessionsSets.mapNotNull { sets ->
            val effective = sets.filter { it.setType != SetType.WARMUP && (it.isCompleted || it.reps > 0) }
            if (effective.isEmpty()) null
            else effective.all { it.reps >= rule.targetReps }
        }

        if (sessionResults.isEmpty()) {
            return AutoProgressionResult(
                type = ProgressionDecisionType.MAINTAIN,
                baseWeightKg = currentWeightKg,
                recommendedWeightKg = currentWeightKg,
                weightDeltaKg = 0.0,
                consecutiveFailures = 0,
                failureThreshold = rule.failureThreshold,
                explanationRes = R.string.progression_maintain_no_history
            )
        }

        val lastSessionSucceeded = sessionResults.first()
        if (lastSessionSucceeded) {
            val newWeight = round((currentWeightKg + rule.incrementKg) / roundingStepKg) * roundingStepKg
            return AutoProgressionResult(
                type = ProgressionDecisionType.PROGRESSION,
                baseWeightKg = currentWeightKg,
                recommendedWeightKg = newWeight,
                weightDeltaKg = newWeight - currentWeightKg,
                consecutiveFailures = 0,
                failureThreshold = rule.failureThreshold,
                explanationRes = R.string.progression_success_increment
            )
        }

        // Count consecutive failures from the latest session backward
        var consecutiveFailures = 0
        for (success in sessionResults) {
            if (!success) consecutiveFailures++
            else break
        }

        return if (consecutiveFailures >= rule.failureThreshold) {
            val rawDeload = currentWeightKg * (1.0 - (rule.deloadPercentage / 100.0))
            val deloadWeight = (round(rawDeload / roundingStepKg) * roundingStepKg).coerceAtLeast(roundingStepKg)
            AutoProgressionResult(
                type = ProgressionDecisionType.DELOAD,
                baseWeightKg = currentWeightKg,
                recommendedWeightKg = deloadWeight,
                weightDeltaKg = deloadWeight - currentWeightKg,
                consecutiveFailures = consecutiveFailures,
                failureThreshold = rule.failureThreshold,
                explanationRes = R.string.progression_deload_triggered
            )
        } else {
            AutoProgressionResult(
                type = ProgressionDecisionType.MAINTAIN,
                baseWeightKg = currentWeightKg,
                recommendedWeightKg = currentWeightKg,
                weightDeltaKg = 0.0,
                consecutiveFailures = consecutiveFailures,
                failureThreshold = rule.failureThreshold,
                explanationRes = R.string.progression_retry_attempt
            )
        }
    }
}
