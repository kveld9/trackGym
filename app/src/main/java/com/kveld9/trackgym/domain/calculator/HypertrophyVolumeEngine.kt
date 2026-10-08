package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.MuscleHypertrophyVolume
import com.kveld9.trackgym.domain.model.Workout
import java.util.Calendar

/**
 * Calculates direct weekly sets per muscle group and maps them against
 * scientifically-backed hypertrophy volume landmarks (10-20 weekly sets).
 */
object HypertrophyVolumeEngine {

    const val DEFAULT_MIN_SETS = 10
    const val DEFAULT_MAX_SETS = 20

    fun calculateWeeklyVolume(
        workouts: List<Workout>,
        referenceTimestamp: Long = System.currentTimeMillis(),
        minThreshold: Int = DEFAULT_MIN_SETS,
        maxThreshold: Int = DEFAULT_MAX_SETS
    ): List<MuscleHypertrophyVolume> {
        val weeklyWorkouts = filterCurrentWeekWorkouts(workouts, referenceTimestamp)
        val setsPerMuscle = mutableMapOf<MuscleGroup, Int>()

        for (w in weeklyWorkouts) {
            for (we in w.exercises) {
                val completedSets = we.sets.count { it.isCompleted }
                val setsToAdd = if (completedSets > 0) completedSets else we.sets.size
                if (setsToAdd <= 0) continue

                val group = we.exercise.muscleGroup
                if (group != MuscleGroup.OTHER) {
                    setsPerMuscle[group] = (setsPerMuscle[group] ?: 0) + setsToAdd
                }
            }
        }

        val relevantGroups = listOf(
            MuscleGroup.CHEST,
            MuscleGroup.BACK,
            MuscleGroup.LEGS,
            MuscleGroup.SHOULDERS,
            MuscleGroup.ARMS,
            MuscleGroup.CORE
        )

        return relevantGroups.map { group ->
            val count = setsPerMuscle[group] ?: 0
            MuscleHypertrophyVolume(
                muscleGroup = group,
                weeklySets = count,
                minThreshold = minThreshold,
                maxThreshold = maxThreshold
            )
        }.sortedByDescending { it.weeklySets }
    }

    fun filterCurrentWeekWorkouts(
        workouts: List<Workout>,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): List<Workout> {
        val cal = Calendar.getInstance().apply {
            timeInMillis = referenceTimestamp
            firstDayOfWeek = Calendar.MONDAY
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfWeek = cal.timeInMillis

        return workouts.filter { w ->
            w.isCompleted && ((w.completedAt ?: w.startedAt) >= startOfWeek)
        }
    }
}
