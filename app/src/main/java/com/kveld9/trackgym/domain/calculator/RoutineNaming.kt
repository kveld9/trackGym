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
