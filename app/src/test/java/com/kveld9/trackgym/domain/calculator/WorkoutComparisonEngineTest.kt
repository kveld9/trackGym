package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
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

    @Test
    fun `detectExcessiveVolume flags muscle groups exceeding threshold while ignoring warmups`() {
        val warmupSet = WorkoutSet(id = 1, setNumber = 1, weightKg = 40.0, reps = 15, isCompleted = true, setType = com.kveld9.trackgym.domain.model.SetType.WARMUP)
        val normalSets = (2..14).map {
            WorkoutSet(id = it.toLong(), setNumber = it, weightKg = 80.0, reps = 10, isCompleted = true)
        }
        val we = WorkoutExercise(
            id = 1,
            workoutId = 1,
            exercise = sampleExercise,
            sets = listOf(warmupSet) + normalSets
        )
        val workout = com.kveld9.trackgym.domain.model.Workout(
            id = 1,
            name = "Chest Heavy Day",
            exercises = listOf(we)
        )

        val warnings = OvertrainingDetector.detectExcessiveVolume(workout, threshold = 12)
        assertEquals(1, warnings.size)
        assertEquals(MuscleGroup.CHEST, warnings[0].muscleGroup)
        assertEquals(13, warnings[0].effectiveSetsCount)
    }

    @Test
    fun `computeRecommendation suggests micro-load progression based on previous session`() {
        val previousSets = listOf(
            WorkoutSet(id = 1, setNumber = 1, weightKg = 80.0, reps = 10, isCompleted = true),
            WorkoutSet(id = 2, setNumber = 2, weightKg = 80.0, reps = 10, isCompleted = true),
            WorkoutSet(id = 3, setNumber = 3, weightKg = 80.0, reps = 10, isCompleted = true)
        )
        val rec = ProgressiveOverloadEngine.computeRecommendation(previousSets, com.kveld9.trackgym.domain.model.WeightUnit.KG)
        org.junit.Assert.assertNotNull(rec)
        rec?.let {
            assertTrue(it.previousSummary.contains("3×10"))
            assertTrue(it.suggestedTarget.contains("82.5 kg"))
            assertTrue(it.suggestedTarget.contains("+2.5 kg"))
        }
    }

    @Test
    fun `totalVolume doubles volume for dumbbell exercises when enabled`() {
        val dumbbellExercise = Exercise(
            id = 2,
            name = "Mancuernas Press Inclinado",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        )
        val we = WorkoutExercise(
            id = 1,
            workoutId = 1,
            exercise = dumbbellExercise,
            sets = listOf(
                WorkoutSet(id = 1, setNumber = 1, weightKg = 20.0, reps = 10, isCompleted = true)
            )
        )
        // 20 kg x 10 reps = 200 kg raw. With 2x dumbbell multiplier = 400 kg.
        assertEquals(400.0, we.totalVolume, 0.01)
        assertEquals(200.0, we.calculateTotalVolume(doubleDumbbells = false), 0.01)
    }

    @Test
    fun `calculateTotalVolume excludes warmup sets when excludeWarmup is true`() {
        val benchPress = Exercise(
            id = 1,
            name = "Barbell Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        )
        val we = WorkoutExercise(
            id = 1,
            workoutId = 1,
            exercise = benchPress,
            sets = listOf(
                WorkoutSet(id = 1, setNumber = 1, setType = SetType.WARMUP, weightKg = 40.0, reps = 10, isCompleted = true),
                WorkoutSet(id = 2, setNumber = 2, setType = SetType.NORMAL, weightKg = 80.0, reps = 8, isCompleted = true),
                WorkoutSet(id = 3, setNumber = 3, setType = SetType.NORMAL, weightKg = 80.0, reps = 8, isCompleted = true)
            )
        )

        // Warmup: 40 * 10 = 400 kg. Normal sets: 2 * (80 * 8) = 1280 kg. Total with warmup: 1680 kg.
        assertEquals(1680.0, we.calculateTotalVolume(doubleDumbbells = false, excludeWarmup = false), 0.01)
        assertEquals(1280.0, we.calculateTotalVolume(doubleDumbbells = false, excludeWarmup = true), 0.01)
        assertEquals(3, we.completedSetsCount(excludeWarmup = false))
        assertEquals(2, we.completedSetsCount(excludeWarmup = true))
    }
}
