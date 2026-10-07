package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.MuscleInvolvement
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise

/**
 * Domain engine responsible for anatomical classification of exercises,
 * defining primary movers and secondary/stabilizer muscles with relative
 * percentage involvements backed by biomechanical principles.
 */
object MuscleAnatomyRegistry {

    private val standardExerciseCatalog: Map<String, List<MuscleInvolvement>> = mapOf(
        // Chest
        "barbell bench press" to listOf(
            MuscleInvolvement(BodyMuscle.CHEST, 70, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRICEPS, 20, isPrimary = false),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 10, isPrimary = false)
        ),
        "incline dumbbell press" to listOf(
            MuscleInvolvement(BodyMuscle.CHEST, 60, isPrimary = true),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.TRICEPS, 15, isPrimary = false)
        ),
        "parallel bar dips" to listOf(
            MuscleInvolvement(BodyMuscle.CHEST, 55, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRICEPS, 35, isPrimary = false),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 10, isPrimary = false)
        ),
        "cable crossover" to listOf(
            MuscleInvolvement(BodyMuscle.CHEST, 85, isPrimary = true),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 15, isPrimary = false)
        ),
        "chest press machine" to listOf(
            MuscleInvolvement(BodyMuscle.CHEST, 75, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRICEPS, 15, isPrimary = false),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 10, isPrimary = false)
        ),

        // Back
        "pull-ups" to listOf(
            MuscleInvolvement(BodyMuscle.LATS, 60, isPrimary = true),
            MuscleInvolvement(BodyMuscle.BICEPS, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 15, isPrimary = false)
        ),
        "lat pulldown" to listOf(
            MuscleInvolvement(BodyMuscle.LATS, 65, isPrimary = true),
            MuscleInvolvement(BodyMuscle.BICEPS, 20, isPrimary = false),
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 15, isPrimary = false)
        ),
        "barbell bent-over row" to listOf(
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 50, isPrimary = true),
            MuscleInvolvement(BodyMuscle.LATS, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.BICEPS, 15, isPrimary = false),
            MuscleInvolvement(BodyMuscle.LOWER_BACK, 10, isPrimary = false)
        ),
        "one-arm dumbbell row" to listOf(
            MuscleInvolvement(BodyMuscle.LATS, 55, isPrimary = true),
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.BICEPS, 20, isPrimary = false)
        ),
        "seated cable row" to listOf(
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 50, isPrimary = true),
            MuscleInvolvement(BodyMuscle.LATS, 30, isPrimary = false),
            MuscleInvolvement(BodyMuscle.BICEPS, 20, isPrimary = false)
        ),
        "conventional deadlift" to listOf(
            MuscleInvolvement(BodyMuscle.HAMSTRINGS, 35, isPrimary = true),
            MuscleInvolvement(BodyMuscle.GLUTES, 30, isPrimary = false),
            MuscleInvolvement(BodyMuscle.LOWER_BACK, 20, isPrimary = false),
            MuscleInvolvement(BodyMuscle.TRAPS, 15, isPrimary = false)
        ),

        // Legs
        "barbell back squat" to listOf(
            MuscleInvolvement(BodyMuscle.QUADRICEPS, 60, isPrimary = true),
            MuscleInvolvement(BodyMuscle.GLUTES, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.HAMSTRINGS, 15, isPrimary = false)
        ),
        "leg press 45°" to listOf(
            MuscleInvolvement(BodyMuscle.QUADRICEPS, 70, isPrimary = true),
            MuscleInvolvement(BodyMuscle.GLUTES, 20, isPrimary = false),
            MuscleInvolvement(BodyMuscle.CALVES, 10, isPrimary = false)
        ),
        "romanian deadlift" to listOf(
            MuscleInvolvement(BodyMuscle.HAMSTRINGS, 60, isPrimary = true),
            MuscleInvolvement(BodyMuscle.GLUTES, 30, isPrimary = false),
            MuscleInvolvement(BodyMuscle.LOWER_BACK, 10, isPrimary = false)
        ),
        "leg extension" to listOf(
            MuscleInvolvement(BodyMuscle.QUADRICEPS, 100, isPrimary = true)
        ),
        "lying leg curl" to listOf(
            MuscleInvolvement(BodyMuscle.HAMSTRINGS, 100, isPrimary = true)
        ),
        "standing calf raise" to listOf(
            MuscleInvolvement(BodyMuscle.CALVES, 100, isPrimary = true)
        ),

        // Shoulders
        "overhead barbell press" to listOf(
            MuscleInvolvement(BodyMuscle.SHOULDERS, 65, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRICEPS, 25, isPrimary = false),
            MuscleInvolvement(BodyMuscle.TRAPS, 10, isPrimary = false)
        ),
        "seated dumbbell shoulder press" to listOf(
            MuscleInvolvement(BodyMuscle.SHOULDERS, 70, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRICEPS, 20, isPrimary = false),
            MuscleInvolvement(BodyMuscle.TRAPS, 10, isPrimary = false)
        ),
        "dumbbell lateral raise" to listOf(
            MuscleInvolvement(BodyMuscle.SHOULDERS, 90, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRAPS, 10, isPrimary = false)
        ),
        "cable lateral raise" to listOf(
            MuscleInvolvement(BodyMuscle.SHOULDERS, 95, isPrimary = true),
            MuscleInvolvement(BodyMuscle.TRAPS, 5, isPrimary = false)
        ),
        "face pull" to listOf(
            MuscleInvolvement(BodyMuscle.SHOULDERS, 50, isPrimary = true),
            MuscleInvolvement(BodyMuscle.UPPER_BACK, 30, isPrimary = false),
            MuscleInvolvement(BodyMuscle.TRAPS, 20, isPrimary = false)
        ),

        // Arms
        "ez-bar bicep curl" to listOf(
            MuscleInvolvement(BodyMuscle.BICEPS, 85, isPrimary = true),
            MuscleInvolvement(BodyMuscle.FOREARMS, 15, isPrimary = false)
        ),
        "dumbbell hammer curl" to listOf(
            MuscleInvolvement(BodyMuscle.FOREARMS, 55, isPrimary = true),
            MuscleInvolvement(BodyMuscle.BICEPS, 45, isPrimary = false)
        ),
        "cable triceps pushdown" to listOf(
            MuscleInvolvement(BodyMuscle.TRICEPS, 100, isPrimary = true)
        ),
        "ez-bar skull crusher" to listOf(
            MuscleInvolvement(BodyMuscle.TRICEPS, 100, isPrimary = true)
        ),

        // Core
        "plank" to listOf(
            MuscleInvolvement(BodyMuscle.ABDOMINALS, 70, isPrimary = true),
            MuscleInvolvement(BodyMuscle.LOWER_BACK, 30, isPrimary = false)
        ),
        "hanging leg raise" to listOf(
            MuscleInvolvement(BodyMuscle.ABDOMINALS, 85, isPrimary = true),
            MuscleInvolvement(BodyMuscle.QUADRICEPS, 15, isPrimary = false)
        ),
        "cable crunch" to listOf(
            MuscleInvolvement(BodyMuscle.ABDOMINALS, 100, isPrimary = true)
        )
    )

    /**
     * Resolves the complete list of muscle involvements (primary and secondary)
     * for a given exercise.
     */
    fun getInvolvementsForExercise(exercise: Exercise): List<MuscleInvolvement> {
        // 1. Explicit configuration stored in exercise entity
        if (exercise.primaryMuscle != null || exercise.secondaryMuscles.isNotEmpty()) {
            return buildExplicitInvolvements(exercise.primaryMuscle, exercise.secondaryMuscles, exercise.muscleGroup)
        }

        // 2. Exact match in catalog
        val nameNormalized = exercise.name.trim().lowercase()
        val exactMatch = standardExerciseCatalog[nameNormalized]
        if (exactMatch != null) return exactMatch

        // 3. Keyword matching in name
        val keywordMatch = matchByKeyword(nameNormalized)
        if (keywordMatch != null) return keywordMatch

        // 4. Default fallback by MuscleGroup
        return fallbackForGroup(exercise.muscleGroup)
    }

    /**
     * Retrieves the primary mover muscle involvement for the exercise.
     */
    fun getPrimaryMuscle(exercise: Exercise): MuscleInvolvement {
        val involvements = getInvolvementsForExercise(exercise)
        return involvements.firstOrNull { it.isPrimary }
            ?: involvements.firstOrNull()
            ?: MuscleInvolvement(fallbackPrimaryForGroup(exercise.muscleGroup), 100, isPrimary = true)
    }

    /**
     * Retrieves secondary/stabilizer muscle involvements sorted by percentage descending.
     */
    fun getSecondaryMuscles(exercise: Exercise): List<MuscleInvolvement> {
        val involvements = getInvolvementsForExercise(exercise)
        return involvements.filter { !it.isPrimary }.sortedByDescending { it.percentage }
    }

    /**
     * Calculates the fractional set volume distributed among body muscles
     * based on their anatomical involvement percentages.
     */
    fun calculateSessionMuscleLoad(workout: Workout): Map<BodyMuscle, Double> {
        val loadMap = mutableMapOf<BodyMuscle, Double>()
        for (exercise in workout.exercises) {
            val completedSets = exercise.sets.count { it.isCompleted }
            if (completedSets <= 0) continue

            val involvements = getInvolvementsForExercise(exercise.exercise)
            for (inv in involvements) {
                val fractionalSets = completedSets * (inv.percentage / 100.0)
                loadMap[inv.muscle] = (loadMap[inv.muscle] ?: 0.0) + fractionalSets
            }
        }
        return loadMap
    }

    private fun buildExplicitInvolvements(
        primary: BodyMuscle?,
        secondary: List<MuscleInvolvement>,
        fallbackGroup: MuscleGroup
    ): List<MuscleInvolvement> {
        val effectivePrimaryMuscle = primary ?: fallbackPrimaryForGroup(fallbackGroup)
        val secondarySum = secondary.filter { !it.isPrimary }.sumOf { it.percentage }
        val primaryPct = (100 - secondarySum).coerceIn(10, 100)

        val result = mutableListOf<MuscleInvolvement>()
        result.add(MuscleInvolvement(effectivePrimaryMuscle, primaryPct, isPrimary = true))
        result.addAll(secondary.filter { !it.isPrimary && it.muscle != effectivePrimaryMuscle })
        return result
    }

    private fun matchByKeyword(name: String): List<MuscleInvolvement>? {
        return when {
            name.contains("bench") || name.contains("chest") || name.contains("push-up") || name.contains("pec") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.CHEST, 70, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.TRICEPS, 20, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.SHOULDERS, 10, isPrimary = false)
                )
            }
            name.contains("pull-up") || name.contains("pulldown") || name.contains("chin-up") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.LATS, 65, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.BICEPS, 20, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.UPPER_BACK, 15, isPrimary = false)
                )
            }
            name.contains("row") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.UPPER_BACK, 55, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.LATS, 25, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.BICEPS, 20, isPrimary = false)
                )
            }
            name.contains("deadlift") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.HAMSTRINGS, 40, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.GLUTES, 35, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.LOWER_BACK, 25, isPrimary = false)
                )
            }
            name.contains("squat") || name.contains("press 45") || name.contains("lunge") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.QUADRICEPS, 60, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.GLUTES, 25, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.HAMSTRINGS, 15, isPrimary = false)
                )
            }
            name.contains("bicep") || name.contains("curl") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.BICEPS, 80, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.FOREARMS, 20, isPrimary = false)
                )
            }
            name.contains("tricep") || name.contains("pushdown") || name.contains("skull crusher") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.TRICEPS, 100, isPrimary = true)
                )
            }
            name.contains("shoulder") || name.contains("overhead") || name.contains("military") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.SHOULDERS, 70, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.TRICEPS, 20, isPrimary = false),
                    MuscleInvolvement(BodyMuscle.TRAPS, 10, isPrimary = false)
                )
            }
            name.contains("lateral raise") || name.contains("front raise") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.SHOULDERS, 90, isPrimary = true),
                    MuscleInvolvement(BodyMuscle.TRAPS, 10, isPrimary = false)
                )
            }
            name.contains("abs") || name.contains("crunch") || name.contains("plank") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.ABDOMINALS, 100, isPrimary = true)
                )
            }
            name.contains("calf") || name.contains("calves") -> {
                listOf(
                    MuscleInvolvement(BodyMuscle.CALVES, 100, isPrimary = true)
                )
            }
            else -> null
        }
    }

    private fun fallbackForGroup(group: MuscleGroup): List<MuscleInvolvement> {
        return when (group) {
            MuscleGroup.CHEST -> listOf(
                MuscleInvolvement(BodyMuscle.CHEST, 80, isPrimary = true),
                MuscleInvolvement(BodyMuscle.TRICEPS, 20, isPrimary = false)
            )
            MuscleGroup.BACK -> listOf(
                MuscleInvolvement(BodyMuscle.UPPER_BACK, 55, isPrimary = true),
                MuscleInvolvement(BodyMuscle.LATS, 30, isPrimary = false),
                MuscleInvolvement(BodyMuscle.BICEPS, 15, isPrimary = false)
            )
            MuscleGroup.LEGS -> listOf(
                MuscleInvolvement(BodyMuscle.QUADRICEPS, 60, isPrimary = true),
                MuscleInvolvement(BodyMuscle.HAMSTRINGS, 25, isPrimary = false),
                MuscleInvolvement(BodyMuscle.GLUTES, 15, isPrimary = false)
            )
            MuscleGroup.SHOULDERS -> listOf(
                MuscleInvolvement(BodyMuscle.SHOULDERS, 80, isPrimary = true),
                MuscleInvolvement(BodyMuscle.TRAPS, 20, isPrimary = false)
            )
            MuscleGroup.ARMS -> listOf(
                MuscleInvolvement(BodyMuscle.BICEPS, 50, isPrimary = true),
                MuscleInvolvement(BodyMuscle.TRICEPS, 50, isPrimary = false)
            )
            MuscleGroup.CORE -> listOf(
                MuscleInvolvement(BodyMuscle.ABDOMINALS, 100, isPrimary = true)
            )
            MuscleGroup.FULL_BODY -> listOf(
                MuscleInvolvement(BodyMuscle.QUADRICEPS, 30, isPrimary = true),
                MuscleInvolvement(BodyMuscle.UPPER_BACK, 25, isPrimary = false),
                MuscleInvolvement(BodyMuscle.CHEST, 25, isPrimary = false),
                MuscleInvolvement(BodyMuscle.SHOULDERS, 20, isPrimary = false)
            )
            MuscleGroup.OTHER -> listOf(
                MuscleInvolvement(BodyMuscle.NECK, 100, isPrimary = true)
            )
        }
    }

    private fun fallbackPrimaryForGroup(group: MuscleGroup): BodyMuscle = when (group) {
        MuscleGroup.CHEST -> BodyMuscle.CHEST
        MuscleGroup.BACK -> BodyMuscle.UPPER_BACK
        MuscleGroup.LEGS -> BodyMuscle.QUADRICEPS
        MuscleGroup.SHOULDERS -> BodyMuscle.SHOULDERS
        MuscleGroup.ARMS -> BodyMuscle.BICEPS
        MuscleGroup.CORE -> BodyMuscle.ABDOMINALS
        MuscleGroup.FULL_BODY -> BodyMuscle.QUADRICEPS
        MuscleGroup.OTHER -> BodyMuscle.NECK
    }

    /**
     * Serializes a list of secondary muscle involvements to a lightweight compact string.
     * Example: "TRICEPS:20,SHOULDERS:10"
     */
    fun serializeSecondaryMuscles(list: List<MuscleInvolvement>): String {
        return list
            .filter { !it.isPrimary && it.percentage > 0 }
            .joinToString(",") { "${it.muscle.name}:${it.percentage}" }
    }

    /**
     * Deserializes a compact string back into a list of secondary muscle involvements.
     */
    fun deserializeSecondaryMuscles(raw: String?): List<MuscleInvolvement> {
        if (raw.isNullOrBlank()) return emptyList()
        return raw.split(',').mapNotNull { entry ->
            val parts = entry.split(':')
            if (parts.size == 2) {
                val muscle = runCatching { BodyMuscle.valueOf(parts[0].trim()) }.getOrNull()
                val pct = parts[1].trim().toIntOrNull()
                if (muscle != null && pct != null && pct > 0) {
                    MuscleInvolvement(muscle = muscle, percentage = pct, isPrimary = false)
                } else null
            } else null
        }
    }
}
