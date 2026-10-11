package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet

/**
 * Unified authority for identifying and counting effective working sets across volume,
 * overtraining fatigue detection, and muscle heatmap calculators.
 *
 * Excludes warm-up sets and considers sets that are completed or contain logged work.
 */
object EffectiveSetCounter {

    /**
     * Determines whether an individual set qualifies as an effective working set.
     * Excludes warmup sets and requires completion or logged work (weight > 0 or reps > 0).
     */
    fun isEffectiveSet(set: WorkoutSet): Boolean {
        if (set.setType == SetType.WARMUP) return false
        return set.isCompleted || set.weightKg > 0.0 || set.reps > 0
    }

    /**
     * Counts effective sets for a given workout exercise.
     */
    fun countEffectiveSets(exercise: WorkoutExercise): Int {
        return countEffectiveSets(exercise.sets)
    }

    /**
     * Counts effective sets in a list of workout sets.
     * Prioritizes sets that are completed or contain logged work.
     * If no sets are completed or logged, falls back to non-warmup sets.
     */
    fun countEffectiveSets(sets: List<WorkoutSet>): Int {
        val effectiveCount = sets.count { isEffectiveSet(it) }
        if (effectiveCount > 0) return effectiveCount

        // Fallback for uncompleted planned workouts or templates where sets have not yet been logged
        return sets.count { it.setType != SetType.WARMUP }
    }
}
