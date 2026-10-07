package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class MuscleHeatmapEngineTest {

    private fun exercise(name: String, group: MuscleGroup) = Exercise(
        id = 1L,
        name = name,
        category = ExerciseCategory.BARBELL,
        muscleGroup = group
    )

    private fun set(completed: Boolean = true) = WorkoutSet(
        workoutExerciseId = 1L,
        setNumber = 1,
        weightKg = 60.0,
        reps = 10,
        isCompleted = completed
    )

    @Test
    fun getTargetMuscles_matchesKeywordsCorrectly() {
        // "bicep curl" -> BICEPS, FOREARMS
        val bicepEx = exercise("Incline Bicep Curl", MuscleGroup.ARMS)
        val bicepMuscles = MuscleHeatmapEngine.getTargetMuscles(bicepEx)
        assertTrue(bicepMuscles.contains(BodyMuscle.BICEPS))
        assertTrue(bicepMuscles.contains(BodyMuscle.FOREARMS))

        // "bench press" -> CHEST
        val benchEx = exercise("Barbell Flat Bench Press", MuscleGroup.CHEST)
        val benchMuscles = MuscleHeatmapEngine.getTargetMuscles(benchEx)
        assertEquals(listOf(BodyMuscle.CHEST), benchMuscles)

        // "deadlift" -> HAMSTRINGS, GLUTES, LOWER_BACK
        val deadliftEx = exercise("Conventional Deadlift", MuscleGroup.BACK)
        val deadliftMuscles = MuscleHeatmapEngine.getTargetMuscles(deadliftEx)
        assertEquals(listOf(BodyMuscle.HAMSTRINGS, BodyMuscle.GLUTES, BodyMuscle.LOWER_BACK), deadliftMuscles)
    }

    @Test
    fun getTargetMuscles_fallbackToMuscleGroup_whenNoKeywordMatches() {
        val unknownEx = exercise("Mysterious Movement", MuscleGroup.CORE)
        val muscles = MuscleHeatmapEngine.getTargetMuscles(unknownEx)
        assertEquals(listOf(BodyMuscle.ABDOMINALS), muscles)
    }

    @Test
    fun calculate_aggregatesSetsAndVolumePercentages() {
        val bench = exercise("Bench Press", MuscleGroup.CHEST)
        val squat = exercise("Barbell Squat", MuscleGroup.LEGS)

        val workout = Workout(
            name = "Push / Legs",
            exercises = listOf(
                WorkoutExercise(exercise = bench, sets = listOf(set(), set(), set())), // 3 sets CHEST
                WorkoutExercise(exercise = squat, sets = listOf(set())) // 1 set LEGS
            )
        )

        val state = MuscleHeatmapEngine.calculate(workout)
        assertEquals(4, state.totalSets)
        assertEquals(3, state.muscleSets[BodyMuscle.CHEST])
        assertEquals(1, state.muscleSets[BodyMuscle.QUADRICEPS])
        assertEquals(1, state.muscleSets[BodyMuscle.GLUTES])

        // Verify group volumes (Chest: 3/4 = 0.75, Legs: 1/4 = 0.25)
        val chestVolume = state.muscleGroupVolumes.first { it.muscleGroup == MuscleGroup.CHEST }
        val legsVolume = state.muscleGroupVolumes.first { it.muscleGroup == MuscleGroup.LEGS }
        assertEquals(3, chestVolume.setsCount)
        assertEquals(0.75f, chestVolume.percentage, 0.001f)
        assertEquals(1, legsVolume.setsCount)
        assertEquals(0.25f, legsVolume.percentage, 0.001f)
    }

    @Test
    fun filterCurrentWeekWorkouts_filtersWorkoutsBeforeMonday() {
        val cal = Calendar.getInstance().apply {
            firstDayOfWeek = Calendar.MONDAY
            set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
            set(Calendar.HOUR_OF_DAY, 12)
        }
        val wednesdayTimestamp = cal.timeInMillis

        // Workout on Wednesday (this week)
        val currentWorkout = Workout(
            name = "Wednesday Workout",
            startedAt = wednesdayTimestamp
        )

        // Workout 10 days ago (last week)
        val lastWeekWorkout = Workout(
            name = "Old Workout",
            startedAt = wednesdayTimestamp - (10L * 24 * 3600 * 1000)
        )

        val filtered = MuscleHeatmapEngine.filterCurrentWeekWorkouts(
            listOf(currentWorkout, lastWeekWorkout),
            referenceTimestamp = wednesdayTimestamp
        )

        assertEquals(1, filtered.size)
        assertEquals("Wednesday Workout", filtered[0].name)
    }
}
