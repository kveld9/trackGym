package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseGuide
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup

/**
 * Authoritative domain registry providing comprehensive technique, setup,
 * biomechanical execution, and safety cues for exercises.
 */
object ExerciseGuideRegistry {

    fun getGuideForExercise(exercise: Exercise): ExerciseGuide {
        val nameLower = exercise.name.lowercase().trim()

        return when {
            // Bench Press & variations
            nameLower.contains("bench press") || nameLower.contains("chest press") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_bench_press_setup_1,
                    R.string.guide_bench_press_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_bench_press_exec_1,
                    R.string.guide_bench_press_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_bench_press_mistake_1,
                    R.string.guide_bench_press_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_valsalva,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Squat & Leg Press
            nameLower.contains("squat") || nameLower.contains("leg press") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_squat_setup_1,
                    R.string.guide_squat_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_squat_exec_1,
                    R.string.guide_squat_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_squat_mistake_1,
                    R.string.guide_squat_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_valsalva,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Deadlift
            nameLower.contains("deadlift") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_deadlift_setup_1,
                    R.string.guide_deadlift_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_deadlift_exec_1,
                    R.string.guide_deadlift_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_deadlift_mistake_1,
                    R.string.guide_deadlift_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_valsalva,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Overhead Press / Shoulder Press
            ((nameLower.contains("overhead") && !nameLower.contains("tricep") && !nameLower.contains("extension")) ||
                nameLower.contains("shoulder press") ||
                nameLower.contains("military press")) &&
                (exercise.muscleGroup == MuscleGroup.SHOULDERS || nameLower.contains("press")) -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_ohp_setup_1,
                    R.string.guide_ohp_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_ohp_exec_1,
                    R.string.guide_ohp_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_ohp_mistake_1,
                    R.string.guide_ohp_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_valsalva,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Vertical Pulls: Pull-up / Lat Pulldown
            nameLower.contains("pull-up") || nameLower.contains("chin-up") || nameLower.contains("lat pulldown") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_pull_setup_1,
                    R.string.guide_pull_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_pull_exec_1,
                    R.string.guide_pull_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_pull_mistake_1,
                    R.string.guide_pull_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Horizontal Rows
            Regex("\\b(row|rows|remo|remador)\\b").containsMatchIn(nameLower) -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_row_setup_1,
                    R.string.guide_row_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_row_exec_1,
                    R.string.guide_row_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_row_mistake_1,
                    R.string.guide_row_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_controlled
            )

            // Biceps / Curls
            nameLower.contains("curl") && (exercise.muscleGroup == MuscleGroup.ARMS || nameLower.contains("bicep")) && !nameLower.contains("leg") && !nameLower.contains("hamstring") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_bicep_setup_1,
                    R.string.guide_bicep_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_bicep_exec_1,
                    R.string.guide_bicep_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_bicep_mistake_1,
                    R.string.guide_bicep_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_constant
            )

            // Triceps (Extension / Pushdown / Skull Crusher / Dip)
            nameLower.contains("tricep") || nameLower.contains("pushdown") || nameLower.contains("skull crusher") || (nameLower.contains("dip") && !nameLower.contains("hip")) -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_tricep_setup_1,
                    R.string.guide_tricep_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_tricep_exec_1,
                    R.string.guide_tricep_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_tricep_mistake_1,
                    R.string.guide_tricep_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_constant
            )

            // Leg Isolation (Leg Curl / Leg Extension / Hamstring / Calf)
            nameLower.contains("leg curl") || nameLower.contains("leg extension") || nameLower.contains("hamstring") || nameLower.contains("calf") || (exercise.muscleGroup == MuscleGroup.LEGS && exercise.mechanics == MechanicsType.ISOLATION) -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_leg_isolation_setup_1,
                    R.string.guide_leg_isolation_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_leg_isolation_exec_1,
                    R.string.guide_leg_isolation_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_leg_isolation_mistake_1,
                    R.string.guide_leg_isolation_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_constant
            )

            // Lateral / Front / Rear Raises
            nameLower.contains("raise") && (exercise.muscleGroup == MuscleGroup.SHOULDERS || nameLower.contains("lateral") || nameLower.contains("face pull")) -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_lateral_setup_1,
                    R.string.guide_lateral_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_lateral_exec_1,
                    R.string.guide_lateral_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_lateral_mistake_1,
                    R.string.guide_lateral_mistake_2
                ),
                breathingCueRes = R.string.guide_breathing_standard,
                tempoCueRes = R.string.guide_tempo_constant
            )

            // Core / Abs
            exercise.muscleGroup == MuscleGroup.CORE || nameLower.contains("plank") || nameLower.contains("crunch") -> ExerciseGuide(
                exerciseName = exercise.name,
                setupSteps = listOf(
                    R.string.guide_core_setup_1,
                    R.string.guide_core_setup_2
                ),
                executionSteps = listOf(
                    R.string.guide_core_exec_1,
                    R.string.guide_core_exec_2
                ),
                commonMistakes = listOf(
                    R.string.guide_core_mistake_1,
                    R.string.guide_core_mistake_2
                ),
                breathingCueRes = if (nameLower.contains("plank")) R.string.guide_breathing_continuous else R.string.guide_breathing_standard,
                tempoCueRes = if (nameLower.contains("plank")) R.string.guide_tempo_isometric else R.string.guide_tempo_constant
            )

            // Fallback by Muscle Group & Mechanics
            else -> getFallbackGuide(exercise)
        }
    }

    private fun getFallbackGuide(exercise: Exercise): ExerciseGuide {
        val (setup, exec, mistake) = when (exercise.muscleGroup) {
            MuscleGroup.CHEST, MuscleGroup.SHOULDERS -> if (exercise.mechanics == MechanicsType.COMPOUND) {
                Triple(R.string.guide_generic_push_setup, R.string.guide_generic_push_exec, R.string.guide_generic_push_mistake)
            } else {
                Triple(R.string.guide_generic_isolation_setup, R.string.guide_generic_isolation_exec, R.string.guide_generic_isolation_mistake)
            }
            MuscleGroup.BACK -> if (exercise.mechanics == MechanicsType.COMPOUND) {
                Triple(R.string.guide_generic_pull_setup, R.string.guide_generic_pull_exec, R.string.guide_generic_pull_mistake)
            } else {
                Triple(R.string.guide_generic_isolation_setup, R.string.guide_generic_isolation_exec, R.string.guide_generic_isolation_mistake)
            }
            MuscleGroup.LEGS -> if (exercise.mechanics == MechanicsType.COMPOUND) {
                Triple(R.string.guide_generic_legs_setup, R.string.guide_generic_legs_exec, R.string.guide_generic_legs_mistake)
            } else {
                Triple(R.string.guide_generic_isolation_setup, R.string.guide_generic_isolation_exec, R.string.guide_generic_isolation_mistake)
            }
            MuscleGroup.ARMS -> Triple(R.string.guide_generic_isolation_setup, R.string.guide_generic_isolation_exec, R.string.guide_generic_isolation_mistake)
            MuscleGroup.CORE -> Triple(R.string.guide_core_setup_1, R.string.guide_core_exec_1, R.string.guide_core_mistake_1)
            else -> Triple(R.string.guide_generic_isolation_setup, R.string.guide_generic_isolation_exec, R.string.guide_generic_isolation_mistake)
        }

        return ExerciseGuide(
            exerciseName = exercise.name,
            setupSteps = listOf(setup),
            executionSteps = listOf(exec),
            commonMistakes = listOf(mistake),
            breathingCueRes = R.string.guide_breathing_standard,
            tempoCueRes = R.string.guide_tempo_controlled
        )
    }
}
