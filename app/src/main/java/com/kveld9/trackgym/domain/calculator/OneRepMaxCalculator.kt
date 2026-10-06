package com.kveld9.trackgym.domain.calculator

import kotlin.math.roundToInt

object OneRepMaxCalculator {

    /**
     * Estimates 1 Rep Max using the standard Epley formula:
     * 1RM = Weight * (1 + Reps / 30) for reps > 1
     * 1RM = Weight for reps == 1
     */
    fun calculate1RM(weightKg: Double, reps: Int): Double {
        if (weightKg <= 0.0 || reps <= 0) return 0.0
        if (reps == 1) return weightKg

        val epley = weightKg * (1.0 + reps / 30.0)
        return (epley * 10.0).roundToInt() / 10.0
    }
}
