package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.MuscleGroupVolume
import com.kveld9.trackgym.domain.model.MuscleHeatmapState
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import java.util.Calendar

object MuscleHeatmapEngine {

    private val keywordMapping = listOf(
        listOf("bicep", "hammer curl", "preacher curl") to listOf(BodyMuscle.BICEPS, BodyMuscle.FOREARMS),
        listOf("tricep", "pushdown", "skull crusher", "dip") to listOf(BodyMuscle.TRICEPS),
        listOf("bench press", "chest press", "chest fly", "crossover", "push-up", "pec deck") to listOf(BodyMuscle.CHEST),
        listOf("lat pulldown", "pull-up", "chin-up") to listOf(BodyMuscle.LATS, BodyMuscle.UPPER_BACK),
        listOf("row") to listOf(BodyMuscle.LATS, BodyMuscle.UPPER_BACK, BodyMuscle.TRAPS),
        listOf("shrug") to listOf(BodyMuscle.TRAPS),
        listOf("deadlift") to listOf(BodyMuscle.HAMSTRINGS, BodyMuscle.GLUTES, BodyMuscle.LOWER_BACK),
        listOf("squat", "leg press", "lunge", "leg extension") to listOf(BodyMuscle.QUADRICEPS, BodyMuscle.GLUTES),
        listOf("leg curl", "romanian", "nordic", "stiff leg") to listOf(BodyMuscle.HAMSTRINGS),
        listOf("calf", "calves") to listOf(BodyMuscle.CALVES),
        listOf("lateral raise", "front raise", "face pull") to listOf(BodyMuscle.SHOULDERS),
        listOf("shoulder press", "overhead press", "military press") to listOf(BodyMuscle.SHOULDERS, BodyMuscle.TRAPS),
        listOf("crunch", "plank", "leg raise", "sit-up", "ab wheel", "russian twist") to listOf(BodyMuscle.ABDOMINALS)
    )

    fun getTargetMuscles(exercise: Exercise): List<BodyMuscle> {
        val nameLower = exercise.name.lowercase()
        val matched = findByKeywords(nameLower)
        if (matched != null) return matched
        return fallbackForGroup(exercise.muscleGroup)
    }

    private fun findByKeywords(name: String): List<BodyMuscle>? {
        for ((keywords, muscles) in keywordMapping) {
            if (keywords.any { name.contains(it) }) {
                return muscles
            }
        }
        return null
    }

    private fun fallbackForGroup(group: MuscleGroup): List<BodyMuscle> = when (group) {
        MuscleGroup.CHEST -> listOf(BodyMuscle.CHEST)
        MuscleGroup.BACK -> listOf(BodyMuscle.LATS, BodyMuscle.UPPER_BACK, BodyMuscle.TRAPS, BodyMuscle.LOWER_BACK)
        MuscleGroup.LEGS -> listOf(BodyMuscle.QUADRICEPS, BodyMuscle.HAMSTRINGS, BodyMuscle.GLUTES, BodyMuscle.CALVES)
        MuscleGroup.SHOULDERS -> listOf(BodyMuscle.SHOULDERS, BodyMuscle.TRAPS)
        MuscleGroup.ARMS -> listOf(BodyMuscle.BICEPS, BodyMuscle.TRICEPS, BodyMuscle.FOREARMS)
        MuscleGroup.CORE -> listOf(BodyMuscle.ABDOMINALS)
        MuscleGroup.FULL_BODY -> listOf(
            BodyMuscle.CHEST, BodyMuscle.LATS, BodyMuscle.UPPER_BACK,
            BodyMuscle.SHOULDERS, BodyMuscle.QUADRICEPS, BodyMuscle.HAMSTRINGS,
            BodyMuscle.BICEPS, BodyMuscle.TRICEPS, BodyMuscle.ABDOMINALS
        )
        MuscleGroup.OTHER -> emptyList()
    }

    fun calculate(workout: Workout): MuscleHeatmapState {
        return calculate(listOf(workout))
    }

    fun calculate(workouts: List<Workout>): MuscleHeatmapState {
        val muscleSetCounts = mutableMapOf<BodyMuscle, Int>()
        val groupSetCounts = mutableMapOf<MuscleGroup, Int>()
        var totalSets = 0

        for (workout in workouts) {
            for (exercise in workout.exercises) {
                val setsCount = resolveCompletedSetsCount(exercise)
                if (setsCount <= 0) continue

                totalSets += setsCount
                val targetMuscles = getTargetMuscles(exercise.exercise)
                for (muscle in targetMuscles) {
                    muscleSetCounts[muscle] = (muscleSetCounts[muscle] ?: 0) + setsCount
                }

                val primaryGroup = exercise.exercise.muscleGroup
                groupSetCounts[primaryGroup] = (groupSetCounts[primaryGroup] ?: 0) + setsCount
            }
        }

        val volumes = groupSetCounts.map { (group, count) ->
            val percentage = if (totalSets > 0) count.toFloat() / totalSets.toFloat() else 0f
            MuscleGroupVolume(muscleGroup = group, setsCount = count, percentage = percentage)
        }.sortedByDescending { it.setsCount }

        return MuscleHeatmapState(
            muscleSets = muscleSetCounts,
            muscleGroupVolumes = volumes,
            totalSets = totalSets
        )
    }

    private fun resolveCompletedSetsCount(exercise: WorkoutExercise): Int {
        val completed = exercise.sets.count { it.isCompleted }
        if (completed > 0) return completed
        return exercise.sets.size
    }

    fun calculateWeekly(
        workouts: List<Workout>,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): MuscleHeatmapState {
        val thisWeekWorkouts = filterCurrentWeekWorkouts(workouts, referenceTimestamp)
        return calculate(thisWeekWorkouts)
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
        return workouts.filter { workout ->
            val time = workout.completedAt ?: workout.startedAt
            time >= startOfWeek
        }
    }
}
