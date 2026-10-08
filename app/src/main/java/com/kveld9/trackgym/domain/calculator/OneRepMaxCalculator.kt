package com.kveld9.trackgym.domain.calculator

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.roundToInt

enum class OneRepMaxFormula(
    @get:StringRes val displayNameRes: Int,
    val formulaExpression: String
) {
    EPLEY(R.string.orm_formula_epley, "w × (1 + r / 30)"),
    BRZYCKI(R.string.orm_formula_brzycki, "w × 36 / (37 - r)"),
    WATHAN(R.string.orm_formula_wathan, "100w / (48.8 + 53.8e^-0.075r)"),
    LANDER(R.string.orm_formula_lander, "100w / (101.3 - 2.67r)"),
    LOMBARDI(R.string.orm_formula_lombardi, "w × r^0.10"),
    MAYHEW(R.string.orm_formula_mayhew, "100w / (52.2 + 41.9e^-0.055r)"),
    OCONNER(R.string.orm_formula_oconner, "w × (1 + 0.025r)")
}

object OneRepMaxCalculator {

    /**
     * Estimates 1 Rep Max using the selected mathematical model.
     * Default model is Epley.
     */
    fun calculate1RM(
        weightKg: Double,
        reps: Int,
        formula: OneRepMaxFormula = OneRepMaxFormula.EPLEY
    ): Double {
        if (!weightKg.isFinite() || weightKg <= 0.0 || reps <= 0) return 0.0
        if (reps == 1) return weightKg

        val raw = computeRaw1RM(weightKg, reps, formula)
        if (!raw.isFinite() || raw <= 0.0) return 0.0
        if (raw * 10.0 > Int.MAX_VALUE) return raw
        return (raw * 10.0).roundToInt() / 10.0
    }

    private fun computeRaw1RM(weightKg: Double, reps: Int, formula: OneRepMaxFormula): Double = when (formula) {
        OneRepMaxFormula.EPLEY -> weightKg * (1.0 + reps / 30.0)
        OneRepMaxFormula.BRZYCKI -> computeBrzycki(weightKg, reps)
        OneRepMaxFormula.WATHAN -> (100.0 * weightKg) / (48.8 + 53.8 * exp(-0.075 * reps))
        OneRepMaxFormula.LANDER -> computeLander(weightKg, reps)
        OneRepMaxFormula.LOMBARDI -> weightKg * reps.toDouble().pow(0.10)
        OneRepMaxFormula.MAYHEW -> (100.0 * weightKg) / (52.2 + 41.9 * exp(-0.055 * reps))
        OneRepMaxFormula.OCONNER -> weightKg * (1.0 + 0.025 * reps)
    }

    private fun computeBrzycki(weightKg: Double, reps: Int): Double =
        if (reps < 37) weightKg * (36.0 / (37.0 - reps)) else weightKg * (1.0 + reps / 30.0)

    private fun computeLander(weightKg: Double, reps: Int): Double {
        val denom = 101.3 - 2.67123 * reps
        return if (denom > 0.0) (100.0 * weightKg) / denom else weightKg * (1.0 + reps / 30.0)
    }
}
