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

/**
 * Determines whether renaming an exercise with [targetExerciseId] to [candidateName]
 * conflicts with an existing exercise row [existingMatchId] (if any).
 *
 * Case-insensitively identical names on the same row ([existingMatchId] == [targetExerciseId])
 * are allowed. Conflicting names on a different exercise row ([existingMatchId] != [targetExerciseId])
 * are rejected. Blank candidate names do not conflict with existing rows.
 */
fun isExerciseRenameConflicting(
    targetExerciseId: Long,
    candidateName: String,
    existingMatchId: Long?
): Boolean {
    val normalized = normalizeExerciseName(candidateName)
    if (normalized.isBlank() || existingMatchId == null) {
        return false
    }
    return existingMatchId != targetExerciseId
}

/**
 * Validates whether renaming an exercise with [targetExerciseId] to [candidateName]
 * is valid against an optional conflicting row [existingMatchId].
 *
 * - Trims and normalizes whitespace via [normalizeExerciseName].
 * - Blank candidate names are rejected with [IllegalArgumentException].
 * - Renaming to its own current name (any case, [existingMatchId] == [targetExerciseId]) is allowed.
 * - Renaming to a name taken by a different row ([existingMatchId] != [targetExerciseId]) throws [DuplicateExerciseNameException].
 *
 * @return The normalized candidate name.
 */
fun validateExerciseRename(
    targetExerciseId: Long,
    candidateName: String,
    existingMatchId: Long?,
    existingMatchName: String? = null
): String {
    val normalized = normalizeExerciseName(candidateName)
    require(normalized.isNotBlank()) { "Exercise name cannot be blank" }
    if (isExerciseRenameConflicting(targetExerciseId, normalized, existingMatchId)) {
        throw DuplicateExerciseNameException(conflictingName = existingMatchName ?: normalized)
    }
    return normalized
}
