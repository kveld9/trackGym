package com.kveld9.trackgym.domain.model

private val MULTIPLE_WHITESPACE_REGEX = Regex("\\s+")

/**
 * Normalizes an exercise name for display and storage by trimming surrounding whitespace
 * and collapsing multiple consecutive whitespace characters into a single space.
 */
fun normalizeExerciseName(name: String): String {
    return name.trim().replace(MULTIPLE_WHITESPACE_REGEX, " ")
}

/**
 * Determines whether two exercise names conflict, comparing case-insensitively
 * after whitespace normalization.
 */
fun areExerciseNamesConflicting(name1: String, name2: String): Boolean {
    val norm1 = normalizeExerciseName(name1)
    val norm2 = normalizeExerciseName(name2)
    return norm1.isNotBlank() && norm1.equals(norm2, ignoreCase = true)
}
