package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup

/**
 * Biomechanical classifier to determine directional force vector (Push/Pull/Static)
 * and technical difficulty level (Beginner/Intermediate/Expert).
 */
object BiomechanicalClassifier {

    private val staticKeywords = listOf(
        "plank", "isometric", "wall sit", "l-sit", "bridge", "dead hang"
    )

    private val dynamicExceptions = listOf(
        "raise", "clean", "snatch", "press", "curl", "sit-up", "sit up", "squat", "row"
    )

    private val pullKeywords = listOf(
        "pull", "row", "chin-up", "chin up", "curl", "deadlift", "rdl", "shrug",
        "face pull", "rear delt", "pulldown", "pullover", "crunch", "leg raise", "knee raise",
        "clean", "snatch", "sit-up", "sit up"
    )

    private val latsRegex = Regex("""\b(lat|lats)\b""")

    private val expertKeywords = listOf(
        "snatch", "clean and jerk", "clean", "jerk", "muscle-up", "muscle up",
        "pistol squat", "dragon flag", "overhead squat"
    )

    private val intermediateKeywords = listOf(
        "squat", "deadlift", "bench press", "overhead press", "dip", "pull-up", "chin-up",
        "barbell", "dumbbell press", "romanian deadlift", "rdl", "good morning"
    )

    /**
     * Determines the directional force vector of an exercise.
     */
    fun classifyForce(
        name: String,
        category: ExerciseCategory = ExerciseCategory.BARBELL,
        muscleGroup: MuscleGroup = MuscleGroup.CHEST
    ): ForceType {
        val lower = name.lowercase().trim()
        if (isStaticExercise(lower)) {
            return ForceType.STATIC
        }
        if (pullKeywords.any { lower.contains(it) } || latsRegex.containsMatchIn(lower) || muscleGroup == MuscleGroup.BACK) {
            return ForceType.PULL
        }
        return ForceType.PUSH
    }

    private fun isStaticExercise(lower: String): Boolean {
        if (dynamicExceptions.any { lower.contains(it) }) {
            return false
        }
        if (staticKeywords.any { lower.contains(it) }) {
            return true
        }
        return lower.contains("hold")
    }

    /**
     * Determines technical complexity level of an exercise.
     */
    fun classifyDifficulty(
        name: String,
        category: ExerciseCategory = ExerciseCategory.BARBELL,
        muscleGroup: MuscleGroup = MuscleGroup.CHEST,
        mechanics: MechanicsType = MechanicsType.COMPOUND
    ): DifficultyLevel {
        val lower = name.lowercase().trim()
        if (expertKeywords.any { lower.contains(it) }) {
            return DifficultyLevel.EXPERT
        }
        if (isIntermediateExercise(lower, category, mechanics)) {
            return DifficultyLevel.INTERMEDIATE
        }
        return DifficultyLevel.BEGINNER
    }

    private fun isIntermediateExercise(
        lowerName: String,
        category: ExerciseCategory,
        mechanics: MechanicsType
    ): Boolean {
        if (intermediateKeywords.any { lowerName.contains(it) }) {
            return true
        }
        return mechanics == MechanicsType.COMPOUND &&
                (category == ExerciseCategory.BARBELL || category == ExerciseCategory.DUMBBELL)
    }
}
