package com.kveld9.trackgym.domain.model

/**
 * Thrown when attempting to delete an exercise that is still referenced
 * by active routines or personal records.
 *
 * @property exerciseId The ID of the exercise that could not be deleted.
 * @property reason The entity type referencing the exercise.
 */
class ExerciseInUseException(
    val exerciseId: Long,
    val reason: Reason,
    message: String = "Exercise with ID $exerciseId is in use by $reason."
) : IllegalStateException(message) {

    enum class Reason {
        ROUTINE,
        RECORD
    }
}
