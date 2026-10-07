package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.DefaultExercises
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.MuscleInvolvement
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MuscleAnatomyRegistryTest {

    @Test
    fun standardExercises_haveExpectedPrimaryAndSecondaryInvolvements() {
        // Bench Press
        val bench = Exercise(name = "Barbell Bench Press", muscleGroup = MuscleGroup.CHEST)
        val benchPrimary = MuscleAnatomyRegistry.getPrimaryMuscle(bench)
        val benchSecondaries = MuscleAnatomyRegistry.getSecondaryMuscles(bench)
        assertEquals(BodyMuscle.CHEST, benchPrimary.muscle)
        assertEquals(70, benchPrimary.percentage)
        assertTrue(benchPrimary.isPrimary)
        assertTrue(benchSecondaries.any { it.muscle == BodyMuscle.TRICEPS && it.percentage == 20 })
        assertTrue(benchSecondaries.any { it.muscle == BodyMuscle.SHOULDERS && it.percentage == 10 })

        // Barbell Back Squat
        val squat = Exercise(name = "Barbell Back Squat", muscleGroup = MuscleGroup.LEGS)
        val squatPrimary = MuscleAnatomyRegistry.getPrimaryMuscle(squat)
        val squatSecondaries = MuscleAnatomyRegistry.getSecondaryMuscles(squat)
        assertEquals(BodyMuscle.QUADRICEPS, squatPrimary.muscle)
        assertEquals(60, squatPrimary.percentage)
        assertTrue(squatSecondaries.any { it.muscle == BodyMuscle.GLUTES && it.percentage == 25 })
        assertTrue(squatSecondaries.any { it.muscle == BodyMuscle.HAMSTRINGS && it.percentage == 15 })

        // Conventional Deadlift
        val deadlift = Exercise(name = "Conventional Deadlift", muscleGroup = MuscleGroup.BACK)
        val deadliftPrimary = MuscleAnatomyRegistry.getPrimaryMuscle(deadlift)
        val deadliftSecondaries = MuscleAnatomyRegistry.getSecondaryMuscles(deadlift)
        assertEquals(BodyMuscle.HAMSTRINGS, deadliftPrimary.muscle)
        assertEquals(35, deadliftPrimary.percentage)
        assertTrue(deadliftSecondaries.any { it.muscle == BodyMuscle.GLUTES && it.percentage == 30 })
        assertTrue(deadliftSecondaries.any { it.muscle == BodyMuscle.LOWER_BACK && it.percentage == 20 })
        assertTrue(deadliftSecondaries.any { it.muscle == BodyMuscle.TRAPS && it.percentage == 15 })
    }

    @Test
    fun allStandardExercises_sumTo100Percent() {
        for (exercise in DefaultExercises.list) {
            val involvements = MuscleAnatomyRegistry.getInvolvementsForExercise(exercise)
            val totalPercentage = involvements.sumOf { it.percentage }
            assertEquals(
                "Exercise ${exercise.name} involvements must sum to 100%, but got $totalPercentage",
                100,
                totalPercentage
            )
            assertTrue(
                "Exercise ${exercise.name} must have exactly one primary mover",
                involvements.count { it.isPrimary } == 1
            )
        }
    }

    @Test
    fun customExercise_withExplicitPrimaryAndSecondaries_usesExplicitValues() {
        val custom = Exercise(
            name = "Special Compound Lift",
            muscleGroup = MuscleGroup.CHEST,
            primaryMuscle = BodyMuscle.CHEST,
            secondaryMuscles = listOf(
                MuscleInvolvement(BodyMuscle.TRICEPS, 25),
                MuscleInvolvement(BodyMuscle.SHOULDERS, 15)
            )
        )

        val involvements = MuscleAnatomyRegistry.getInvolvementsForExercise(custom)
        val primary = MuscleAnatomyRegistry.getPrimaryMuscle(custom)
        val secondaries = MuscleAnatomyRegistry.getSecondaryMuscles(custom)

        assertEquals(BodyMuscle.CHEST, primary.muscle)
        assertEquals(60, primary.percentage) // 100 - (25 + 15) = 60
        assertEquals(2, secondaries.size)
        assertEquals(100, involvements.sumOf { it.percentage })
    }

    @Test
    fun keywordMatching_correctlyInfersMuscles() {
        val unknownBench = Exercise(
            name = "Incline Neutral Grip Dumbbell Bench",
            muscleGroup = MuscleGroup.CHEST
        )
        val primary = MuscleAnatomyRegistry.getPrimaryMuscle(unknownBench)
        val secondaries = MuscleAnatomyRegistry.getSecondaryMuscles(unknownBench)

        assertEquals(BodyMuscle.CHEST, primary.muscle)
        assertEquals(70, primary.percentage)
        assertTrue(secondaries.any { it.muscle == BodyMuscle.TRICEPS })
    }

    @Test
    fun fallback_returnsSensibleInvolvements_whenUnknown() {
        val obscureExercise = Exercise(
            name = "Mysterious Romanian Machine Movement",
            muscleGroup = MuscleGroup.SHOULDERS
        )
        val primary = MuscleAnatomyRegistry.getPrimaryMuscle(obscureExercise)
        val secondaries = MuscleAnatomyRegistry.getSecondaryMuscles(obscureExercise)

        assertEquals(BodyMuscle.SHOULDERS, primary.muscle)
        assertTrue(secondaries.any { it.muscle == BodyMuscle.TRAPS })
    }

    @Test
    fun calculateSessionMuscleLoad_calculatesFractionalSetsCorrectly() {
        val bench = Exercise(name = "Barbell Bench Press", muscleGroup = MuscleGroup.CHEST)
        val sets = listOf(
            WorkoutSet(workoutExerciseId = 1, setNumber = 1, weightKg = 100.0, reps = 8, isCompleted = true),
            WorkoutSet(workoutExerciseId = 1, setNumber = 2, weightKg = 100.0, reps = 8, isCompleted = true)
        )
        val workout = Workout(
            name = "Chest Day",
            exercises = listOf(WorkoutExercise(exercise = bench, sets = sets))
        )

        val load = MuscleAnatomyRegistry.calculateSessionMuscleLoad(workout)

        // Bench: 2 completed sets
        // Chest (70%): 2 * 0.70 = 1.4 sets
        // Triceps (20%): 2 * 0.20 = 0.4 sets
        // Shoulders (10%): 2 * 0.10 = 0.2 sets
        assertEquals(1.4, load[BodyMuscle.CHEST] ?: 0.0, 0.001)
        assertEquals(0.4, load[BodyMuscle.TRICEPS] ?: 0.0, 0.001)
        assertEquals(0.2, load[BodyMuscle.SHOULDERS] ?: 0.0, 0.001)
    }

    @Test
    fun serializationAndDeserialization_roundTripsCorrectly() {
        val secondaries = listOf(
            MuscleInvolvement(BodyMuscle.TRICEPS, 25),
            MuscleInvolvement(BodyMuscle.SHOULDERS, 15)
        )
        val serialized = MuscleAnatomyRegistry.serializeSecondaryMuscles(secondaries)
        assertEquals("TRICEPS:25,SHOULDERS:15", serialized)

        val deserialized = MuscleAnatomyRegistry.deserializeSecondaryMuscles(serialized)
        assertEquals(2, deserialized.size)
        assertEquals(BodyMuscle.TRICEPS, deserialized[0].muscle)
        assertEquals(25, deserialized[0].percentage)
        assertEquals(BodyMuscle.SHOULDERS, deserialized[1].muscle)
        assertEquals(15, deserialized[1].percentage)

        // Blank and empty edge cases
        assertTrue(MuscleAnatomyRegistry.deserializeSecondaryMuscles(null).isEmpty())
        assertTrue(MuscleAnatomyRegistry.deserializeSecondaryMuscles("").isEmpty())
        assertTrue(MuscleAnatomyRegistry.deserializeSecondaryMuscles("   ").isEmpty())
    }
}
