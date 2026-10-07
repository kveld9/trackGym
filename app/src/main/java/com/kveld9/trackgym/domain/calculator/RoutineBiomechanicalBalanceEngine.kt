package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import kotlin.math.round

/**
 * Summary metrics of biomechanical force vectors and technical complexity for a routine or set of exercises.
 */
data class RoutineBiomechanicalBalance(
    val totalExercises: Int,
    val pushCount: Int,
    val pullCount: Int,
    val staticCount: Int,
    val otherCount: Int,
    val beginnerCount: Int,
    val intermediateCount: Int,
    val expertCount: Int,
    val compoundCount: Int,
    val isolationCount: Int,
    val pushPullRatio: Double?,
    val balanceStatusRes: Int,
    val isBalanced: Boolean
)

object RoutineBiomechanicalBalanceEngine {

    fun calculate(exercises: List<Exercise>): RoutineBiomechanicalBalance {
        if (exercises.isEmpty()) {
            return RoutineBiomechanicalBalance(
                totalExercises = 0,
                pushCount = 0,
                pullCount = 0,
                staticCount = 0,
                otherCount = 0,
                beginnerCount = 0,
                intermediateCount = 0,
                expertCount = 0,
                compoundCount = 0,
                isolationCount = 0,
                pushPullRatio = null,
                balanceStatusRes = R.string.routine_balance_status_neutral,
                isBalanced = true
            )
        }

        var push = 0
        var pull = 0
        var static = 0
        var other = 0
        var beginner = 0
        var intermediate = 0
        var expert = 0
        var compound = 0
        var isolation = 0

        for (ex in exercises) {
            when (ex.force) {
                ForceType.PUSH -> push++
                ForceType.PULL -> pull++
                ForceType.STATIC -> static++
                ForceType.OTHER -> other++
            }
            when (ex.level) {
                DifficultyLevel.BEGINNER -> beginner++
                DifficultyLevel.INTERMEDIATE -> intermediate++
                DifficultyLevel.EXPERT -> expert++
            }
            when (ex.mechanics) {
                MechanicsType.COMPOUND -> compound++
                MechanicsType.ISOLATION -> isolation++
            }
        }

        val ratio = calculatePushPullRatio(push, pull)
        val (statusRes, isBalanced) = evaluateBalanceStatus(push, pull, static, exercises.size)

        return RoutineBiomechanicalBalance(
            totalExercises = exercises.size,
            pushCount = push,
            pullCount = pull,
            staticCount = static,
            otherCount = other,
            beginnerCount = beginner,
            intermediateCount = intermediate,
            expertCount = expert,
            compoundCount = compound,
            isolationCount = isolation,
            pushPullRatio = ratio,
            balanceStatusRes = statusRes,
            isBalanced = isBalanced
        )
    }

    private fun calculatePushPullRatio(push: Int, pull: Int): Double? {
        if (pull == 0) return null
        return round((push.toDouble() / pull.toDouble()) * 100.0) / 100.0
    }

    private fun evaluateBalanceStatus(
        push: Int,
        pull: Int,
        static: Int,
        total: Int
    ): Pair<Int, Boolean> {
        if (static > 0 && (static.toDouble() / total.toDouble()) >= 0.5) {
            return Pair(R.string.routine_balance_status_static_dominant, false)
        }
        if (push == 0 && pull == 0) {
            return Pair(R.string.routine_balance_status_neutral, true)
        }
        if (pull == 0 && push > 0) {
            return Pair(R.string.routine_balance_status_push_dominant, false)
        }
        if (push == 0 && pull > 0) {
            return Pair(R.string.routine_balance_status_pull_dominant, false)
        }

        val ratio = push.toDouble() / pull.toDouble()
        val isBalanced = ratio in 0.6..1.6
        val res = when {
            isBalanced -> R.string.routine_balance_status_balanced
            ratio > 1.6 -> R.string.routine_balance_status_push_dominant
            else -> R.string.routine_balance_status_pull_dominant
        }
        return Pair(res, isBalanced)
    }
}
