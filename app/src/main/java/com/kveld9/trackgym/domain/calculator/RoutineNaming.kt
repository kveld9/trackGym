package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.RoutineSetPlan

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

/**
 * Sanitizes an individual routine set plan, clamping weight to non-negative and reps to at least 1.
 */
fun sanitizeRoutineSetPlan(plan: RoutineSetPlan): RoutineSetPlan =
    plan.copy(
        weightKg = if (plan.weightKg >= 0.0) plan.weightKg else 0.0,
        reps = if (plan.reps >= 1) plan.reps else 1
    )

/**
 * Sanitizes a list of routine set plans, guaranteeing at least one plan with clamped values.
 */
fun sanitizeRoutineSetPlans(
    plans: List<RoutineSetPlan>?,
    fallbackSets: Int = 3,
    fallbackWeight: Double = 0.0,
    fallbackReps: Int = 10
): List<RoutineSetPlan> {
    if (plans.isNullOrEmpty()) {
        val count = sanitizeRoutineTargetSets(fallbackSets)
        val weight = sanitizeRoutineDefaultWeight(fallbackWeight)
        val reps = sanitizeRoutineDefaultReps(fallbackReps)
        return List(count) {
            RoutineSetPlan(weightKg = weight, reps = reps)
        }
    }
    return plans.map { sanitizeRoutineSetPlan(it) }
}
