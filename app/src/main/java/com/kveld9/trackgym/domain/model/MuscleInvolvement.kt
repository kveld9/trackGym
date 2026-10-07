package com.kveld9.trackgym.domain.model

/**
 * Represents the degree of anatomical activation/involvement of a specific muscle
 * during the execution of an exercise.
 *
 * @param muscle The targeted anatomical body muscle.
 * @param percentage The relative contribution percentage (1-100%).
 * @param isPrimary True if this is the primary mover, false if it acts as a secondary/stabilizer.
 */
data class MuscleInvolvement(
    val muscle: BodyMuscle,
    val percentage: Int,
    val isPrimary: Boolean = false
) {
    init {
        require(percentage in 1..100) { "Percentage must be between 1 and 100, got: $percentage" }
    }
}
