package com.kveld9.trackgym.domain.model

/**
 * Thrown when attempting to create an exercise with a name that already exists in the catalog.
 *
 * @property conflictingName The display name of the existing conflicting exercise.
 */
class DuplicateExerciseNameException(
    val conflictingName: String,
    message: String = "An exercise named \"$conflictingName\" already exists."
) : IllegalArgumentException(message)
