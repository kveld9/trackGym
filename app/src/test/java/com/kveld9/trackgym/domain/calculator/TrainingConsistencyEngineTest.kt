package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class TrainingConsistencyEngineTest {

    @Test
    fun `empty workouts yields zero streak and empty activity`() {
        val stats = TrainingConsistencyEngine.calculateConsistency(emptyList())
        assertEquals(0, stats.currentStreakWeeks)
        assertEquals(0, stats.totalWorkoutsLast30Days)
        assertEquals(0.0, stats.totalVolumeLast30Days, 0.01)
        assertEquals(TrainingConsistencyEngine.DAYS_TO_SHOW, stats.recentDays.size)
        assertTrue(stats.recentDays.all { it.workoutCount == 0 && it.intensityLevel == 0 })
    }

    @Test
    fun `workouts across consecutive weeks calculates correct streak`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val dummyExercise = com.kveld9.trackgym.domain.model.Exercise(
            id = 1,
            name = "Bench Press",
            muscleGroup = com.kveld9.trackgym.domain.model.MuscleGroup.CHEST,
            category = com.kveld9.trackgym.domain.model.ExerciseCategory.BARBELL
        )
        val dummySet1 = com.kveld9.trackgym.domain.model.WorkoutSet(
            id = 1,
            workoutExerciseId = 1,
            setNumber = 1,
            weightKg = 100.0,
            reps = 10,
            isCompleted = true
        )
        val dummyWe1 = com.kveld9.trackgym.domain.model.WorkoutExercise(
            id = 1,
            workoutId = 1,
            exercise = dummyExercise,
            sets = listOf(dummySet1)
        )

        // 1 workout today
        val wToday = Workout(
            id = 1,
            name = "Session 1",
            startedAt = now - 3600000,
            completedAt = now,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(dummyWe1)
        )

        // 1 workout 1 week ago
        val oneWeekAgoCal = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -1) }
        val dummyWe2 = com.kveld9.trackgym.domain.model.WorkoutExercise(
            id = 2,
            workoutId = 2,
            exercise = dummyExercise,
            sets = listOf(dummySet1.copy(id = 2, weightKg = 80.0, reps = 10))
        )
        val wPrevWeek = Workout(
            id = 2,
            name = "Session 2",
            startedAt = oneWeekAgoCal.timeInMillis - 3600000,
            completedAt = oneWeekAgoCal.timeInMillis,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(dummyWe2)
        )

        val stats = TrainingConsistencyEngine.calculateConsistency(listOf(wToday, wPrevWeek), now)
        assertEquals(2, stats.currentStreakWeeks)
        assertEquals(2, stats.totalWorkoutsLast30Days)
        assertEquals(1800.0, stats.totalVolumeLast30Days, 0.01)
    }

    @Test
    fun `intensity level maps accurately`() {
        assertEquals(0, TrainingConsistencyEngine.calculateIntensityLevel(0, 0.0))
        assertEquals(1, TrainingConsistencyEngine.calculateIntensityLevel(1, 2000.0))
        assertEquals(2, TrainingConsistencyEngine.calculateIntensityLevel(1, 8000.0))
        assertEquals(3, TrainingConsistencyEngine.calculateIntensityLevel(2, 4000.0))
    }
}
