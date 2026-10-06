package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout

data class MuscleFatigueWarning(
    val muscleGroup: MuscleGroup,
    val effectiveSetsCount: Int,
    val threshold: Int = DEFAULT_MAX_EFFECTIVE_SETS
) {
    companion object {
        const val DEFAULT_MAX_EFFECTIVE_SETS = 12
    }
}

/**
 * Detects excessive session volume (> 12 effective sets per muscle group)
 * which yields diminishing hypertrophy returns and elevated central nervous system fatigue (junk volume).
 */
object OvertrainingDetector {

    const val DEFAULT_MAX_EFFECTIVE_SETS = 12

    fun detectExcessiveVolume(
        workout: Workout,
        threshold: Int = DEFAULT_MAX_EFFECTIVE_SETS
    ): List<MuscleFatigueWarning> {
        val countByMuscle = mutableMapOf<MuscleGroup, Int>()

        for (workoutExercise in workout.exercises) {
            val muscle = workoutExercise.exercise.muscleGroup
            if (muscle == MuscleGroup.OTHER) continue

            val effectiveSets = workoutExercise.sets.count { set ->
                set.setType != SetType.WARMUP && (set.isCompleted || set.weightKg > 0.0 || set.reps > 0)
            }
            if (effectiveSets > 0) {
                countByMuscle[muscle] = (countByMuscle[muscle] ?: 0) + effectiveSets
            }
        }

        return countByMuscle
            .filter { it.value > threshold }
            .map { (muscle, count) ->
                MuscleFatigueWarning(
                    muscleGroup = muscle,
                    effectiveSetsCount = count,
                    threshold = threshold
                )
            }
    }
}
