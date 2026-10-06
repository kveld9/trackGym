package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WorkoutComparisonEngineTest {

    private val sampleExercise = Exercise(
        id = 1,
        name = "Press de Banca Plano con Barra",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    @Test
    fun `formatSetsSummary groups repeated sets correctly`() {
        val sets = listOf(
            WorkoutSet(id = 1, setNumber = 1, weightKg = 15.0, reps = 8, isCompleted = true),
            WorkoutSet(id = 2, setNumber = 2, weightKg = 15.0, reps = 8, isCompleted = true)
        )
        val summary = WorkoutComparisonEngine.formatSetsSummary(sets)
        assertEquals("2x8 @ 15 kg", summary)
    }

    @Test
    fun `compareExercise computes deltas between prior and current session accurately`() {
        val previousWe = WorkoutExercise(
            id = 1,
            workoutId = 1,
            exercise = sampleExercise,
            sets = listOf(
                WorkoutSet(id = 1, setNumber = 1, weightKg = 15.0, reps = 8, isCompleted = true),
                WorkoutSet(id = 2, setNumber = 2, weightKg = 15.0, reps = 8, isCompleted = true)
            )
        )

        val currentWe = WorkoutExercise(
            id = 2,
            workoutId = 2,
            exercise = sampleExercise,
            sets = listOf(
                WorkoutSet(id = 3, setNumber = 1, weightKg = 15.0, reps = 10, isCompleted = true),
                WorkoutSet(id = 4, setNumber = 2, weightKg = 18.0, reps = 8, isCompleted = true)
            )
        )

        val comparison = WorkoutComparisonEngine.compareExercise(
            exercise = sampleExercise,
            currentWorkoutExercise = currentWe,
            previousWorkoutExercise = previousWe,
            previousWorkoutDate = System.currentTimeMillis() - 7 * 24 * 3600 * 1000L
        )

        assertEquals("2x8 @ 15 kg", comparison.previousSummary)
        assertEquals("1x10 @ 15 kg, 1x8 @ 18 kg", comparison.currentSummary)
        assertEquals(2, comparison.setComparisons.size)

        // Set 1: +2 reps
        val set1Comp = comparison.setComparisons[0]
        assertEquals(0.0, set1Comp.weightDeltaKg, 0.01)
        assertEquals(2, set1Comp.repsDelta)
        assertTrue(set1Comp.isImprovement)

        // Set 2: +3kg
        val set2Comp = comparison.setComparisons[1]
        assertEquals(3.0, set2Comp.weightDeltaKg, 0.01)
        assertEquals(0, set2Comp.repsDelta)
        assertTrue(set2Comp.isImprovement)

        assertTrue(comparison.isOverallImproved)
    }

    @Test
    fun `generateMarkdownPrompt contains markdown structure and exercise details`() {
        val currentWe = WorkoutExercise(
            id = 2,
            workoutId = 2,
            exercise = sampleExercise,
            sets = listOf(
                WorkoutSet(id = 3, setNumber = 1, weightKg = 80.0, reps = 10, isCompleted = true)
            )
        )
        val workout = com.kveld9.trackgym.domain.model.Workout(
            id = 2,
            name = "Push Day A",
            startedAt = 1700000000000L,
            completedAt = 1700003600000L,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(currentWe)
        )
        val comparison = WorkoutComparisonEngine.compareExercise(
            exercise = sampleExercise,
            currentWorkoutExercise = currentWe,
            previousWorkoutExercise = null,
            previousWorkoutDate = null
        )
        val workoutComparison = com.kveld9.trackgym.domain.model.WorkoutComparison(
            currentWorkout = workout,
            previousWorkout = null,
            exerciseComparisons = listOf(comparison),
            totalRecordsUnlocked = emptyList(),
            totalVolumeDeltaKg = 800.0
        )

        val prompt = LlmWorkoutPromptGenerator.generateMarkdownPrompt(workoutComparison)

        assertTrue(prompt.contains("# Workout Session Analysis Request"))
        assertTrue(prompt.contains("Push Day A"))
        assertTrue(prompt.contains("Press de Banca Plano con Barra"))
        assertTrue(prompt.contains("80.0 kg × 10 reps"))
        assertTrue(prompt.contains("Coaching Questions"))
    }
}
