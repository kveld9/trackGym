package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutSet
import kotlin.math.roundToInt

data class ProgressiveOverloadRecommendation(
    val previousSummary: String,
    val suggestedTarget: String
)

/**
 * Recommends progressive overload adjustments based on successful sets
 * in the lifter's previous session for this exercise.
 */
object ProgressiveOverloadEngine {

    fun computeRecommendation(
        previousSets: List<WorkoutSet>,
        weightUnit: WeightUnit = WeightUnit.KG
    ): ProgressiveOverloadRecommendation? {
        val completedEffective = previousSets.filter {
            it.setType != SetType.WARMUP && (it.isCompleted || it.reps > 0)
        }
        if (completedEffective.isEmpty()) return null

        val topSet = completedEffective.maxByOrNull { it.weightKg } ?: return null
        val topWeightKg = topSet.weightKg
        val topReps = topSet.reps

        val prevSummary = buildPreviousSummary(completedEffective, topWeightKg, topReps, weightUnit)
        val suggestedTarget = buildSuggestedTarget(topWeightKg, topReps, weightUnit)

        return ProgressiveOverloadRecommendation(
            previousSummary = prevSummary,
            suggestedTarget = suggestedTarget
        )
    }

    private fun buildPreviousSummary(
        sets: List<WorkoutSet>,
        topWeightKg: Double,
        topReps: Int,
        weightUnit: WeightUnit
    ): String {
        val allIdentical = sets.all { it.weightKg == topWeightKg && it.reps == topReps }
        val weightFormatted = weightUnit.format(topWeightKg)
        return if (allIdentical) {
            "${sets.size}×$topReps @ $weightFormatted"
        } else {
            "$weightFormatted × $topReps reps"
        }
    }

    private fun buildSuggestedTarget(
        topWeightKg: Double,
        topReps: Int,
        weightUnit: WeightUnit
    ): String {
        if (topWeightKg <= 0.0) {
            val nextReps = topReps + 1
            return "$nextReps–${nextReps + 2} reps"
        }

        val stepDisplay = if (weightUnit == WeightUnit.LB) "5 lb" else "2.5 kg"
        val nextKg = if (weightUnit == WeightUnit.LB) {
            topWeightKg + (5.0 / 2.20462262185)
        } else {
            topWeightKg + 2.5
        }
        val nextFormatted = weightUnit.format(nextKg)
        val repRange = if (topReps >= 8) "${topReps - 2}–$topReps" else "$topReps"

        return "$nextFormatted × $repRange reps (+$stepDisplay)"
    }
}
