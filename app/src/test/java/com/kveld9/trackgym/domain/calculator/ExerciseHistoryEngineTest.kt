package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseHistoryEngineTest {

    private val benchPress = Exercise(
        id = 101L,
        name = "Barbell Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    private val dumbbellCurl = Exercise(
        id = 102L,
        name = "Dumbbell Bicep Curl",
        muscleGroup = MuscleGroup.ARMS,
        category = ExerciseCategory.DUMBBELL,
        mechanics = MechanicsType.ISOLATION
    )

    @Test
    fun extractHistory_noWorkouts_returnsEmptyList() {
        val history = ExerciseHistoryEngine.extractHistory(
            exerciseId = 101L,
            completedWorkouts = emptyList()
        )
        assertTrue(history.isEmpty())
    }

    @Test
    fun extractHistory_workoutsWithoutTargetExercise_returnsEmptyList() {
        val workout = Workout(
            id = 1L,
            name = "Arm Day",
            isCompleted = true,
            completedAt = 1000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 1L,
                    exercise = dumbbellCurl,
                    sets = listOf(
                        WorkoutSet(id = 1L, setNumber = 1, weightKg = 15.0, reps = 10, isCompleted = true)
                    )
                )
            )
        )

        val history = ExerciseHistoryEngine.extractHistory(
            exerciseId = 101L,
            completedWorkouts = listOf(workout)
        )
        assertTrue(history.isEmpty())
    }

    @Test
    fun extractHistory_uncompletedSetsOnly_returnsEmptyList() {
        val workout = Workout(
            id = 1L,
            name = "Push Day",
            isCompleted = true,
            completedAt = 1000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 1L,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 1L, setNumber = 1, weightKg = 100.0, reps = 5, isCompleted = false)
                    )
                )
            )
        )

        val history = ExerciseHistoryEngine.extractHistory(
            exerciseId = 101L,
            completedWorkouts = listOf(workout)
        )
        assertTrue(history.isEmpty())
    }

    @Test
    fun extractHistory_multipleWorkouts_correctlyCalculatesMaxWeightAndVolumeAndSortsDescending() {
        val workoutOlder = Workout(
            id = 1L,
            name = "Push Day Week 1",
            isCompleted = true,
            completedAt = 1000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 1L,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 1L, setNumber = 1, weightKg = 80.0, reps = 10, isCompleted = true),
                        WorkoutSet(id = 2L, setNumber = 2, weightKg = 90.0, reps = 8, isCompleted = true)
                    )
                )
            )
        )

        val workoutNewer = Workout(
            id = 2L,
            name = "Push Day Week 2",
            isCompleted = true,
            completedAt = 2000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 2L,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 3L, setNumber = 1, weightKg = 90.0, reps = 10, isCompleted = true),
                        WorkoutSet(id = 4L, setNumber = 2, weightKg = 100.0, reps = 6, isCompleted = true)
                    )
                )
            )
        )

        val history = ExerciseHistoryEngine.extractHistory(
            exerciseId = 101L,
            completedWorkouts = listOf(workoutOlder, workoutNewer)
        )

        assertEquals(2, history.size)
        // Descending order by date
        assertEquals(2L, history[0].workoutId)
        assertEquals(100.0, history[0].maxWeightKg, 0.001)
        // Volume: 90 * 10 + 100 * 6 = 900 + 600 = 1500
        assertEquals(1500.0, history[0].totalVolumeKg, 0.001)
        assertEquals(2, history[0].completedSetsCount)

        assertEquals(1L, history[1].workoutId)
        assertEquals(90.0, history[1].maxWeightKg, 0.001)
        // Volume: 80 * 10 + 90 * 8 = 800 + 720 = 1520
        assertEquals(1520.0, history[1].totalVolumeKg, 0.001)
    }

    @Test
    fun extractHistory_dumbbellVolumeDoubling_appliesMultiplierCorrectly() {
        val workout = Workout(
            id = 3L,
            name = "Arms Day",
            isCompleted = true,
            completedAt = 3000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 3L,
                    exercise = dumbbellCurl,
                    sets = listOf(
                        WorkoutSet(id = 5L, setNumber = 1, weightKg = 16.0, reps = 10, isCompleted = true)
                    )
                )
            )
        )

        val historyWithDoubling = ExerciseHistoryEngine.extractHistory(
            exerciseId = 102L,
            completedWorkouts = listOf(workout),
            doubleDumbbells = true
        )
        // 16 kg * 10 reps * 2 hands = 320 kg
        assertEquals(320.0, historyWithDoubling[0].totalVolumeKg, 0.001)

        val historyWithoutDoubling = ExerciseHistoryEngine.extractHistory(
            exerciseId = 102L,
            completedWorkouts = listOf(workout),
            doubleDumbbells = false
        )
        // 16 kg * 10 reps = 160 kg
        assertEquals(160.0, historyWithoutDoubling[0].totalVolumeKg, 0.001)
    }

    @Test
    fun extractHistory_best1RmCalculation_isComputedCorrectly() {
        val workout = Workout(
            id = 4L,
            name = "Heavy Bench",
            isCompleted = true,
            completedAt = 4000L,
            exercises = listOf(
                WorkoutExercise(
                    id = 4L,
                    exercise = benchPress,
                    sets = listOf(
                        WorkoutSet(id = 6L, setNumber = 1, weightKg = 100.0, reps = 1, isCompleted = true),
                        WorkoutSet(id = 7L, setNumber = 2, weightKg = 90.0, reps = 10, isCompleted = true)
                    )
                )
            )
        )

        val history = ExerciseHistoryEngine.extractHistory(
            exerciseId = 101L,
            completedWorkouts = listOf(workout)
        )

        // 100 kg x 1 = 100 1RM
        // 90 kg x 10 = 90 * (1 + 10/30) = 120 1RM
        // Best 1RM should be 120.0
        val expected1Rm = OneRepMaxCalculator.calculate1RM(90.0, 10)
        assertEquals(expected1Rm, history[0].best1RmKg, 0.01)
    }
}
