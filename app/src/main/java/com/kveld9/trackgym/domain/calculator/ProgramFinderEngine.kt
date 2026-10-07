package com.kveld9.trackgym.domain.calculator

enum class ExperienceLevel {
    BEGINNER,     // < 3 months
    INTERMEDIATE, // 3 - 12 months
    ADVANCED      // > 1 year
}

enum class TrainingGoal {
    STRENGTH,
    HYPERTROPHY,
    HYBRID
}

enum class SessionDuration {
    EXPRESS,  // 30 - 45 min
    STANDARD, // 45 - 60 min
    LONG      // 60 - 90 min
}

data class RecommendedExerciseTemplate(
    val exerciseName: String,
    val targetSets: Int = 3,
    val defaultReps: Int = 10,
    val defaultWeightKg: Double = 0.0
)

data class RecommendedRoutineTemplate(
    val name: String,
    val exercises: List<RecommendedExerciseTemplate>
)

data class ProgramRecommendation(
    val id: String,
    val name: String,
    val description: String,
    val targetFrequencyDays: Int,
    val sessionDuration: SessionDuration,
    val experienceLevel: ExperienceLevel,
    val primaryGoal: TrainingGoal,
    val routines: List<RecommendedRoutineTemplate>
)

/**
 * Recommends and generates tailored training programs based on
 * available weekly frequency, workout duration, experience level, and training goal.
 */
object ProgramFinderEngine {

    val CATALOG: List<ProgramRecommendation> = listOf(
        // 1. Full Body 3x Classic (Novice Linear Progression)
        ProgramRecommendation(
            id = "full_body_3x",
            name = "Full Body 3x (Linear Foundation)",
            description = "High-frequency whole body training built around fundamental barbell and bodyweight compounds. Ideal for building solid strength and muscular base.",
            targetFrequencyDays = 3,
            sessionDuration = SessionDuration.STANDARD,
            experienceLevel = ExperienceLevel.BEGINNER,
            primaryGoal = TrainingGoal.STRENGTH,
            routines = listOf(
                RecommendedRoutineTemplate(
                    name = "Workout A (Squat & Bench)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Back Squat", 3, 5),
                        RecommendedExerciseTemplate("Barbell Bench Press", 3, 5),
                        RecommendedExerciseTemplate("Barbell Bent-Over Row", 3, 8),
                        RecommendedExerciseTemplate("Cable Triceps Pushdown", 2, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Workout B (Deadlift & Press)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Conventional Deadlift", 3, 5),
                        RecommendedExerciseTemplate("Overhead Barbell Press", 3, 5),
                        RecommendedExerciseTemplate("Lat Pulldown", 3, 8),
                        RecommendedExerciseTemplate("EZ-Bar Bicep Curl", 2, 10)
                    )
                )
            )
        ),

        // 2. Full Body Express 3x (Time-Constrained Hypertrophy)
        ProgramRecommendation(
            id = "full_body_express_3x",
            name = "Full Body Express 3x",
            description = "Ultra time-efficient full body routines designed to maximize muscular stimulus in under 45 minutes using compound multi-joint movements.",
            targetFrequencyDays = 3,
            sessionDuration = SessionDuration.EXPRESS,
            experienceLevel = ExperienceLevel.BEGINNER,
            primaryGoal = TrainingGoal.HYPERTROPHY,
            routines = listOf(
                RecommendedRoutineTemplate(
                    name = "Day 1 (Squat & Push)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Back Squat", 3, 8),
                        RecommendedExerciseTemplate("Barbell Bench Press", 3, 8),
                        RecommendedExerciseTemplate("Pull-ups", 3, 8)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 2 (Hinge & Pull)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Romanian Deadlift", 3, 8),
                        RecommendedExerciseTemplate("Overhead Barbell Press", 3, 8),
                        RecommendedExerciseTemplate("Seated Cable Row", 3, 8)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 3 (Legs & Incline)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Leg Press 45°", 3, 10),
                        RecommendedExerciseTemplate("Incline Dumbbell Press", 3, 10),
                        RecommendedExerciseTemplate("Lat Pulldown", 3, 10)
                    )
                )
            )
        ),

        // 3. Upper / Lower 4x (Torso / Pierna Classic)
        ProgramRecommendation(
            id = "upper_lower_4x",
            name = "Upper / Lower 4x (Torso & Legs)",
            description = "The gold standard 4-day split alternating upper body and lower body days with optimal balance between mechanical tension and metabolic stress.",
            targetFrequencyDays = 4,
            sessionDuration = SessionDuration.STANDARD,
            experienceLevel = ExperienceLevel.INTERMEDIATE,
            primaryGoal = TrainingGoal.HYBRID,
            routines = listOf(
                RecommendedRoutineTemplate(
                    name = "Upper Body A (Power)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Bench Press", 4, 6),
                        RecommendedExerciseTemplate("Barbell Bent-Over Row", 4, 6),
                        RecommendedExerciseTemplate("Overhead Barbell Press", 3, 8),
                        RecommendedExerciseTemplate("Pull-ups", 3, 8),
                        RecommendedExerciseTemplate("Cable Triceps Pushdown", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Lower Body A (Power)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Back Squat", 4, 6),
                        RecommendedExerciseTemplate("Romanian Deadlift", 3, 8),
                        RecommendedExerciseTemplate("Leg Extension", 3, 10),
                        RecommendedExerciseTemplate("Lying Leg Curl", 3, 10),
                        RecommendedExerciseTemplate("Standing Calf Raise", 3, 12)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Upper Body B (Hypertrophy)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Incline Dumbbell Press", 3, 10),
                        RecommendedExerciseTemplate("Lat Pulldown", 3, 10),
                        RecommendedExerciseTemplate("Seated Dumbbell Shoulder Press", 3, 10),
                        RecommendedExerciseTemplate("Dumbbell Lateral Raise", 3, 12),
                        RecommendedExerciseTemplate("EZ-Bar Bicep Curl", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Lower Body B (Hypertrophy)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Leg Press 45°", 3, 10),
                        RecommendedExerciseTemplate("Conventional Deadlift", 3, 5),
                        RecommendedExerciseTemplate("Lying Leg Curl", 3, 12),
                        RecommendedExerciseTemplate("Standing Calf Raise", 4, 12),
                        RecommendedExerciseTemplate("Hanging Leg Raise", 3, 12)
                    )
                )
            )
        ),

        // 4. Powerbuilding 5x (Upper/Lower + PPL Hybrid)
        ProgramRecommendation(
            id = "powerbuilding_5x",
            name = "Powerbuilding 5x Hybrid",
            description = "Combines 2 heavy power days with 3 targeted push/pull/legs hypertrophy sessions for complete strength progression and muscle growth.",
            targetFrequencyDays = 5,
            sessionDuration = SessionDuration.LONG,
            experienceLevel = ExperienceLevel.ADVANCED,
            primaryGoal = TrainingGoal.HYBRID,
            routines = listOf(
                RecommendedRoutineTemplate(
                    name = "Day 1: Upper Power",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Bench Press", 4, 5),
                        RecommendedExerciseTemplate("Barbell Bent-Over Row", 4, 5),
                        RecommendedExerciseTemplate("Overhead Barbell Press", 3, 6),
                        RecommendedExerciseTemplate("Pull-ups", 3, 6)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 2: Lower Power",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Back Squat", 4, 5),
                        RecommendedExerciseTemplate("Conventional Deadlift", 3, 5),
                        RecommendedExerciseTemplate("Leg Press 45°", 3, 8),
                        RecommendedExerciseTemplate("Standing Calf Raise", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 3: Push Hypertrophy",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Incline Dumbbell Press", 3, 10),
                        RecommendedExerciseTemplate("Cable Crossover", 3, 12),
                        RecommendedExerciseTemplate("Dumbbell Lateral Raise", 4, 12),
                        RecommendedExerciseTemplate("Cable Triceps Pushdown", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 4: Pull Hypertrophy",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Lat Pulldown", 4, 10),
                        RecommendedExerciseTemplate("Seated Cable Row", 3, 10),
                        RecommendedExerciseTemplate("Face Pull", 3, 12),
                        RecommendedExerciseTemplate("Dumbbell Hammer Curl", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Day 5: Legs Hypertrophy",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Leg Press 45°", 4, 10),
                        RecommendedExerciseTemplate("Romanian Deadlift", 3, 10),
                        RecommendedExerciseTemplate("Leg Extension", 3, 12),
                        RecommendedExerciseTemplate("Lying Leg Curl", 3, 12)
                    )
                )
            )
        ),

        // 5. Push / Pull / Legs 6x (Classic PPL)
        ProgramRecommendation(
            id = "ppl_6x",
            name = "Push / Pull / Legs 6x (PPL)",
            description = "Dedicated 6-day split with complete muscle recovery between rotations. High volume, distinct mechanical focus, and maximal hypertrophy stimulus.",
            targetFrequencyDays = 6,
            sessionDuration = SessionDuration.STANDARD,
            experienceLevel = ExperienceLevel.INTERMEDIATE,
            primaryGoal = TrainingGoal.HYPERTROPHY,
            routines = listOf(
                RecommendedRoutineTemplate(
                    name = "Push Day (Chest, Shoulders, Triceps)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Bench Press", 4, 8),
                        RecommendedExerciseTemplate("Incline Dumbbell Press", 3, 10),
                        RecommendedExerciseTemplate("Overhead Barbell Press", 3, 8),
                        RecommendedExerciseTemplate("Dumbbell Lateral Raise", 4, 12),
                        RecommendedExerciseTemplate("Cable Triceps Pushdown", 3, 12)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Pull Day (Back, Rear Delts, Biceps)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Conventional Deadlift", 3, 5),
                        RecommendedExerciseTemplate("Barbell Bent-Over Row", 4, 8),
                        RecommendedExerciseTemplate("Lat Pulldown", 3, 10),
                        RecommendedExerciseTemplate("Face Pull", 3, 12),
                        RecommendedExerciseTemplate("EZ-Bar Bicep Curl", 3, 10)
                    )
                ),
                RecommendedRoutineTemplate(
                    name = "Legs Day (Quads, Hamstrings, Calves)",
                    exercises = listOf(
                        RecommendedExerciseTemplate("Barbell Back Squat", 4, 8),
                        RecommendedExerciseTemplate("Romanian Deadlift", 3, 10),
                        RecommendedExerciseTemplate("Leg Press 45°", 3, 10),
                        RecommendedExerciseTemplate("Lying Leg Curl", 3, 12),
                        RecommendedExerciseTemplate("Standing Calf Raise", 4, 12)
                    )
                )
            )
        )
    )

    /**
     * Scores and ranks programs based on user inputs.
     * Returns list sorted descending by match score.
     */
    fun findRecommendations(
        daysPerWeek: Int,
        duration: SessionDuration,
        experience: ExperienceLevel,
        goal: TrainingGoal
    ): List<ProgramRecommendation> {
        return CATALOG.sortedByDescending { program ->
            calculateMatchScore(program, daysPerWeek, duration, experience, goal)
        }
    }

    private fun calculateMatchScore(
        program: ProgramRecommendation,
        days: Int,
        duration: SessionDuration,
        experience: ExperienceLevel,
        goal: TrainingGoal
    ): Int {
        var score = 0

        // Frequency match (Primary weight: 40)
        val dayDiff = kotlin.math.abs(program.targetFrequencyDays - days)
        if (dayDiff == 0) {
            score += 40
        } else {
            score += kotlin.math.max(0, 40 - (dayDiff * 15))
        }

        // Goal match (Weight: 25)
        if (program.primaryGoal == goal) {
            score += 25
        } else if (program.primaryGoal == TrainingGoal.HYBRID || goal == TrainingGoal.HYBRID) {
            score += 18
        }

        // Duration match (Weight: 20)
        if (program.sessionDuration == duration) {
            score += 20
        } else {
            score += 8
        }

        // Experience match (Weight: 15)
        if (program.experienceLevel == experience) {
            score += 15
        } else {
            val expDiff = kotlin.math.abs(program.experienceLevel.ordinal - experience.ordinal)
            if (expDiff == 1) score += 8
        }

        return score
    }
}
