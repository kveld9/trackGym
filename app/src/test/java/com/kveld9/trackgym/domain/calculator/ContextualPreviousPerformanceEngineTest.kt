package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ContextualPreviousPerformanceEngineTest {

    private val benchPress = Exercise(
        id = 1,
        name = "Barbell Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    private val squat = Exercise(
        id = 2,
        name = "Barbell Back Squat",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    // Older Push Day (routineId = 101, t = 1000): Heavy Bench 4x6 @ 80kg
    private val pushDayWorkout = Workout(
        id = 1,
        name = "Push Day",
        startedAt = 1000,
        completedAt = 1000,
        isCompleted = true,
        routineId = 101L,
        exercises = listOf(
            WorkoutExercise(
                id = 10,
                workoutId = 1,
                exercise = benchPress,
                sets = listOf(
                    WorkoutSet(id = 101, workoutExerciseId = 10, setNumber = 1, weightKg = 80.0, reps = 6, isCompleted = true),
                    WorkoutSet(id = 102, workoutExerciseId = 10, setNumber = 2, weightKg = 80.0, reps = 6, isCompleted = true)
                )
            )
        )
    )

    // More recent Fullbody Day (routineId = 202, t = 2000): Light Bench 3x12 @ 50kg
    private val fullbodyWorkout = Workout(
        id = 2,
        name = "Fullbody Day",
        startedAt = 2000,
        completedAt = 2000,
        isCompleted = true,
        routineId = 202L,
        exercises = listOf(
            WorkoutExercise(
                id = 20,
                workoutId = 2,
                exercise = benchPress,
                sets = listOf(
                    WorkoutSet(id = 201, workoutExerciseId = 20, setNumber = 1, weightKg = 50.0, reps = 12, isCompleted = true),
                    WorkoutSet(id = 202, workoutExerciseId = 20, setNumber = 2, weightKg = 50.0, reps = 12, isCompleted = true)
                )
            )
        )
    )

    // Even more recent Leg Day (routineId = 303, t = 3000): Only Squat
    private val legWorkout = Workout(
        id = 3,
        name = "Leg Day",
        startedAt = 3000,
        completedAt = 3000,
        isCompleted = true,
        routineId = 303L,
        exercises = listOf(
            WorkoutExercise(
                id = 30,
                workoutId = 3,
                exercise = squat,
                sets = listOf(
                    WorkoutSet(id = 301, workoutExerciseId = 30, setNumber = 1, weightKg = 100.0, reps = 5, isCompleted = true)
                )
            )
        )
    )

    private val allCompletedWorkouts = listOf(pushDayWorkout, fullbodyWorkout, legWorkout)

    @Test
    fun testPrioritizesSameRoutineWhenAvailableEvenIfOlder() {
        // Today user is doing Push Day (routineId = 101).
        // Even though Fullbody (t=2000) is more recent than Push (t=1000),
        // we should get the heavy 80kg bench sets from Push Day!
        val previousWe = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = benchPress.id,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = 101L,
            currentWorkoutId = 999L
        )

        assertNotNull(previousWe)
        assertEquals(1L, previousWe!!.workoutId)
        assertEquals(80.0, previousWe.sets.first().weightKg, 0.01)

        val previousSets = ContextualPreviousPerformanceEngine.findPreviousSets(
            exerciseId = benchPress.id,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = 101L,
            currentWorkoutId = 999L
        )
        assertEquals(2, previousSets.size)
        assertEquals(80.0, previousSets.first().weightKg, 0.01)
    }

    @Test
    fun testFallsBackToGlobalMostRecentWhenExerciseNotInPreferredRoutine() {
        // Today user is doing Leg Day (routineId = 303) and decides to add Bench Press.
        // Leg Day has never contained Bench Press, so it should fall back to the most recent Bench session (Fullbody, t=2000 @ 50kg).
        val previousWe = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = benchPress.id,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = 303L,
            currentWorkoutId = 999L
        )

        assertNotNull(previousWe)
        assertEquals(2L, previousWe!!.workoutId) // Fullbody
        assertEquals(50.0, previousWe.sets.first().weightKg, 0.01)
    }

    @Test
    fun testGlobalLookupWhenNoRoutineSpecified() {
        // Free workout (routineId = null): returns the most recent Bench session globally (Fullbody @ 50kg)
        val previousWe = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = benchPress.id,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = null,
            currentWorkoutId = 999L
        )

        assertNotNull(previousWe)
        assertEquals(2L, previousWe!!.workoutId)
        assertEquals(50.0, previousWe.sets.first().weightKg, 0.01)
    }

    @Test
    fun testExcludesCurrentWorkoutId() {
        // If current workout is id = 2 (Fullbody), it should ignore itself and return Push Day (id = 1)
        val previousWe = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = benchPress.id,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = null,
            currentWorkoutId = 2L
        )

        assertNotNull(previousWe)
        assertEquals(1L, previousWe!!.workoutId) // Push Day
        assertEquals(80.0, previousWe.sets.first().weightKg, 0.01)
    }

    @Test
    fun testReturnsNullWhenExerciseNeverPerformed() {
        val deadliftId = 9999L
        val previousWe = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = deadliftId,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = 101L
        )
        assertNull(previousWe)

        val sets = ContextualPreviousPerformanceEngine.findPreviousSets(
            exerciseId = deadliftId,
            completedWorkouts = allCompletedWorkouts,
            preferredRoutineId = 101L
        )
        assertTrue(sets.isEmpty())
    }

    @Test
    fun testIsFromSameRoutine() {
        assertTrue(ContextualPreviousPerformanceEngine.isFromSameRoutine(101L, 101L))
        assertFalse(ContextualPreviousPerformanceEngine.isFromSameRoutine(101L, 202L))
        assertFalse(ContextualPreviousPerformanceEngine.isFromSameRoutine(null, 101L))
        assertFalse(ContextualPreviousPerformanceEngine.isFromSameRoutine(101L, null))
        assertFalse(ContextualPreviousPerformanceEngine.isFromSameRoutine(null, null))
    }

    @Test
    fun testUncompletedPlaceholderSetsAreExcluded() {
        val workoutWithUncompleted = Workout(
            id = 50,
            name = "Skipped Workout",
            startedAt = 4000,
            completedAt = 4000,
            isCompleted = true,
            routineId = 101L,
            exercises = listOf(
                WorkoutExercise(
                    id = 500,
                    workoutId = 50,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 5001, workoutExerciseId = 500, setNumber = 1, weightKg = 99.0, reps = 10, isCompleted = false)
                    )
                )
            )
        )

        val previousSets = ContextualPreviousPerformanceEngine.findPreviousSets(
            exerciseId = benchPress.id,
            completedWorkouts = listOf(workoutWithUncompleted),
            preferredRoutineId = 101L
        )

        // Uncompleted set must NOT be returned
        assertTrue(previousSets.isEmpty())
    }

    @Test
    fun testCompletedAtNullFallbackToStartedAt() {
        val workoutWithoutCompletedAt = Workout(
            id = 60,
            name = "Restored Legacy Workout",
            startedAt = 5000,
            completedAt = null,
            isCompleted = true,
            routineId = 101L,
            exercises = listOf(
                WorkoutExercise(
                    id = 600,
                    workoutId = 60,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 6001, workoutExerciseId = 600, setNumber = 1, weightKg = 85.0, reps = 5, isCompleted = true)
                    )
                )
            )
        )

        val resolved = ContextualPreviousPerformanceEngine.findPreviousWorkoutExercise(
            exerciseId = benchPress.id,
            completedWorkouts = listOf(pushDayWorkout, workoutWithoutCompletedAt),
            preferredRoutineId = 101L
        )

        assertNotNull(resolved)
        assertEquals(60L, resolved!!.workoutId)
        assertEquals(85.0, resolved.sets.first().weightKg, 0.01)
    }
}
