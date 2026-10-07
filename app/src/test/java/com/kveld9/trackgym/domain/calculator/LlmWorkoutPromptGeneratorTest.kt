package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertTrue
import org.junit.Test

class LlmWorkoutPromptGeneratorTest {

    @Test
    fun generateMarkdownPrompt_createsComprehensiveStructuredPrompt() {
        val exercise = Exercise(
            id = 1L,
            name = "Overhead Press",
            category = ExerciseCategory.BARBELL,
            muscleGroup = MuscleGroup.SHOULDERS
        )

        val currentSet = WorkoutSet(
            id = 10L,
            workoutExerciseId = 1L,
            setNumber = 1,
            setType = SetType.NORMAL,
            weightKg = 60.0,
            reps = 8,
            rpe = 8.5,
            isCompleted = true
        )

        val previousSet = WorkoutSet(
            id = 5L,
            workoutExerciseId = 1L,
            setNumber = 1,
            setType = SetType.NORMAL,
            weightKg = 57.5,
            reps = 8,
            isCompleted = true
        )

        val currentWorkout = Workout(
            id = 100L,
            name = "Push Day A",
            startedAt = 1700000000000L,
            completedAt = 1700003600000L,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(
                WorkoutExercise(exercise = exercise, sets = listOf(currentSet))
            )
        )

        val record = PersonalRecord(
            exerciseId = 1L,
            recordType = RecordType.MAX_WEIGHT,
            recordValue = 60.0,
            weightKg = 60.0,
            reps = 8,
            achievedAt = 1700003600000L,
            workoutId = 100L,
            description = "New max weight: 60.0 kg × 8 reps"
        )

        val setComp = SetComparison(
            setNumber = 1,
            currentSet = currentSet,
            previousSet = previousSet,
            weightDeltaKg = 2.5,
            repsDelta = 0,
            volumeDeltaKg = 20.0,
            isImprovement = true
        )

        val exComp = ExerciseComparison(
            exercise = exercise,
            previousWorkoutDate = 1699000000000L,
            previousSummary = "57.5 kg × 8 reps",
            currentSummary = "60.0 kg × 8 reps",
            setComparisons = listOf(setComp),
            totalVolumeDeltaKg = 20.0,
            recordsUnlocked = listOf(record)
        )

        val comparison = WorkoutComparison(
            currentWorkout = currentWorkout,
            previousWorkout = null,
            totalVolumeDeltaKg = 20.0,
            exerciseComparisons = listOf(exComp),
            totalRecordsUnlocked = listOf(record)
        )

        val prompt = LlmWorkoutPromptGenerator.generateMarkdownPrompt(comparison, WeightUnit.KG)

        // Assert core sections and data
        assertTrue(prompt.contains("# Workout Session Analysis Request"))
        assertTrue(prompt.contains("**Workout Name**: Push Day A"))
        assertTrue(prompt.contains("**Duration**: 60 min"))
        assertTrue(prompt.contains("**Personal Records Broken**: 1"))
        assertTrue(prompt.contains("New max weight: 60.0 kg × 8 reps"))
        assertTrue(prompt.contains("### Overhead Press (SHOULDERS)"))
        assertTrue(prompt.contains("Set 1: 60.0 kg × 8 reps @ RPE 8.5"))
        assertTrue(prompt.contains("+2.5 kg"))
        assertTrue(prompt.contains("## 3. Coaching Questions"))
    }
}
