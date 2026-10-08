package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class WeekStreakEngineTest {

    private val dummyExercise = Exercise(
        id = 1,
        name = "Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    private fun createWorkout(id: Long, completedAt: Long): Workout {
        val set = WorkoutSet(
            id = id,
            workoutExerciseId = id,
            setNumber = 1,
            weightKg = 80.0,
            reps = 8,
            isCompleted = true
        )
        val we = WorkoutExercise(
            id = id,
            workoutId = id,
            exercise = dummyExercise,
            sets = listOf(set)
        )
        return Workout(
            id = id,
            name = "Workout $id",
            startedAt = completedAt - 3600000L,
            completedAt = completedAt,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(we)
        )
    }

    @Test
    fun `empty workouts yields zero streak`() {
        val result = WeekStreakEngine.calculateStreak(emptyList())
        assertEquals(0, result.currentStreakWeeks)
        assertEquals(0, result.bestStreakWeeks)
        assertFalse(result.isStreakActiveThisWeek)
        assertEquals(null, result.lastTrainedWeekTimestamp)
    }

    @Test
    fun `workout completed this week gives 1 week current streak`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val w = createWorkout(1, now)
        val result = WeekStreakEngine.calculateStreak(listOf(w), now)

        assertEquals(1, result.currentStreakWeeks)
        assertEquals(1, result.bestStreakWeeks)
        assertTrue(result.isStreakActiveThisWeek)
        assertEquals(now, result.lastTrainedWeekTimestamp)
    }

    @Test
    fun `workouts in 3 consecutive weeks up to current week gives 3 week streak`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val calW1 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -2) }
        val calW2 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -1) }

        val w1 = createWorkout(1, calW1.timeInMillis)
        val w2 = createWorkout(2, calW2.timeInMillis)
        val w3 = createWorkout(3, now)

        val result = WeekStreakEngine.calculateStreak(listOf(w1, w2, w3), now)

        assertEquals(3, result.currentStreakWeeks)
        assertEquals(3, result.bestStreakWeeks)
        assertTrue(result.isStreakActiveThisWeek)
    }

    @Test
    fun `not trained yet this week preserves previous week streak as pending`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val calW1 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -2) }
        val calW2 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -1) }

        val w1 = createWorkout(1, calW1.timeInMillis)
        val w2 = createWorkout(2, calW2.timeInMillis)

        val result = WeekStreakEngine.calculateStreak(listOf(w1, w2), now)

        // Previous 2 consecutive weeks are preserved because current week has not ended
        assertEquals(2, result.currentStreakWeeks)
        assertEquals(2, result.bestStreakWeeks)
        assertFalse(result.isStreakActiveThisWeek)
    }

    @Test
    fun `gap of two weeks breaks current streak but keeps best streak`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val calW1 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -5) }
        val calW2 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -4) }
        val calW3 = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -3) }

        val w1 = createWorkout(1, calW1.timeInMillis)
        val w2 = createWorkout(2, calW2.timeInMillis)
        val w3 = createWorkout(3, calW3.timeInMillis)

        // Gap at week -2, week -1 and current week -> current streak is broken (0)
        val result = WeekStreakEngine.calculateStreak(listOf(w1, w2, w3), now)

        assertEquals(0, result.currentStreakWeeks)
        assertEquals(3, result.bestStreakWeeks)
        assertFalse(result.isStreakActiveThisWeek)
    }
}
