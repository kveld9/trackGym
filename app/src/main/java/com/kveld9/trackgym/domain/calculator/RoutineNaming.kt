package com.kveld9.trackgym.domain.calculator

/**
 * Resolves and sanitizes a routine name candidate.
 * Returns the trimmed string, or null if empty or composed only of whitespace.
 */
fun resolveNewRoutineName(raw: String): String? {
    val trimmed = raw.trim()
    if (trimmed.isEmpty()) {
        return null
    }
    return trimmed
}

/**
 * Sanitizes routine exercise target sets, guaranteeing a positive value.
 */
fun sanitizeRoutineTargetSets(sets: Int, fallback: Int = 3): Int =
    if (sets > 0) sets else fallback

/**
 * Sanitizes routine exercise default reps, guaranteeing a positive value.
 */
fun sanitizeRoutineDefaultReps(reps: Int, fallback: Int = 10): Int =
    if (reps > 0) reps else fallback

/**
 * Sanitizes routine exercise default weight in kg, guaranteeing a non-negative value.
 */
fun sanitizeRoutineDefaultWeight(weightKg: Double): Double =
    if (weightKg >= 0.0) weightKg else 0.0

