package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OvertrainingDetectorTest {

    private fun createExercise(name: String, muscle: MuscleGroup) = Exercise(
        id = 1L,
        name = name,
        category = ExerciseCategory.BARBELL,
        muscleGroup = muscle
    )

    private fun createSet(setType: SetType, reps: Int = 10, weightKg: Double = 50.0, isCompleted: Boolean = true) = WorkoutSet(
        workoutExerciseId = 1L,
        setNumber = 1,
        setType = setType,
        weightKg = weightKg,
        reps = reps,
        isCompleted = isCompleted
    )

    @Test
    fun detectExcessiveVolume_emptyOrLowVolume_returnsNoWarnings() {
        val exercise = createExercise("Bench Press", MuscleGroup.CHEST)
        val sets = (1..5).map { createSet(SetType.NORMAL) }
        val workout = Workout(
            name = "Test Workout",
            exercises = listOf(WorkoutExercise(exercise = exercise, sets = sets))
        )

        val warnings = OvertrainingDetector.detectExcessiveVolume(workout)
        assertTrue(warnings.isEmpty())
    }

    @Test
    fun detectExcessiveVolume_warmupsDoNotCountTowardsFatigue() {
        val exercise = createExercise("Squat", MuscleGroup.LEGS)
        val warmups = (1..10).map { createSet(SetType.WARMUP) }
        val normals = (1..5).map { createSet(SetType.NORMAL) }
        val workout = Workout(
            name = "Leg Day",
            exercises = listOf(WorkoutExercise(exercise = exercise, sets = warmups + normals))
        )

        val warnings = OvertrainingDetector.detectExcessiveVolume(workout, threshold = 12)
        assertTrue(warnings.isEmpty())
    }

    @Test
    fun detectExcessiveVolume_exceedsThreshold_emitsWarning() {
        val exercise1 = createExercise("Bench Press", MuscleGroup.CHEST)
        val exercise2 = createExercise("Incline Dumbbell Press", MuscleGroup.CHEST)

        val sets1 = (1..8).map { createSet(SetType.NORMAL) }
        val sets2 = (1..6).map { createSet(SetType.NORMAL) }

        // Total 14 sets for CHEST (> threshold 12)
        val workout = Workout(
            name = "Chest Blast",
            exercises = listOf(
                WorkoutExercise(exercise = exercise1, sets = sets1),
                WorkoutExercise(exercise = exercise2, sets = sets2)
            )
        )

        val warnings = OvertrainingDetector.detectExcessiveVolume(workout, threshold = 12)
        assertEquals(1, warnings.size)
        assertEquals(MuscleGroup.CHEST, warnings[0].muscleGroup)
        assertEquals(14, warnings[0].effectiveSetsCount)
        assertEquals(12, warnings[0].threshold)
    }

    @Test
    fun detectExcessiveVolume_ignoresOtherMuscleGroup() {
        val exercise = createExercise("Custom Mobility", MuscleGroup.OTHER)
        val sets = (1..20).map { createSet(SetType.NORMAL) }
        val workout = Workout(
            name = "Mobility Session",
            exercises = listOf(WorkoutExercise(exercise = exercise, sets = sets))
        )

        val warnings = OvertrainingDetector.detectExcessiveVolume(workout, threshold = 12)
        assertTrue(warnings.isEmpty())
    }
}
