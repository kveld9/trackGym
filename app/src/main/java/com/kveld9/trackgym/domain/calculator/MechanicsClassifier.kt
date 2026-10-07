package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup

/**
 * Biomechanical classifier to determine whether an exercise is Compound (multi-joint)
 * or Isolation (single-joint).
 */
object MechanicsClassifier {

    private val isolationKeywords = listOf(
        "curl", "extension", "raise", "fly", "crossover",
        "calf", "calves", "crunch", "shrug", "face pull",
        "kickback", "pullover", "wrist", "abductor", "adductor"
    )

    fun classify(
        name: String,
        category: ExerciseCategory = ExerciseCategory.BARBELL,
        muscleGroup: MuscleGroup = MuscleGroup.CHEST
    ): MechanicsType {
        val lower = name.lowercase().trim()

        if (isolationKeywords.any { lower.contains(it) }) {
            return MechanicsType.ISOLATION
        }

        // Arm exercises default to isolation unless dips or close-grip bench
        if (muscleGroup == MuscleGroup.ARMS && !lower.contains("dip") && !lower.contains("close-grip")) {
            return MechanicsType.ISOLATION
        }

        return MechanicsType.COMPOUND
    }
}
