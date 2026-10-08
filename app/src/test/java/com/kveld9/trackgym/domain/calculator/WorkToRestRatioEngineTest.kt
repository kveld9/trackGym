package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Test

class WorkToRestRatioEngineTest {

    private val sampleExercise = Exercise(
        id = 1L,
        name = "Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    private val cardioExercise = Exercise(
        id = 2L,
        name = "Running",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.CARDIO
    )

    @Test
    fun estimateSetWorkSeconds_uncompletedSet_returnsZero() {
        val set = WorkoutSet(
            id = 1L,
            setNumber = 1,
            reps = 10,
            isCompleted = false
        )
        val workSec = WorkToRestRatioEngine.estimateSetWorkSeconds(set)
        assertEquals(0L, workSec)
    }

    @Test
    fun estimateSetWorkSeconds_completedRepBasedSet_calculatesThreeSecondsPerRep() {
        val set = WorkoutSet(
            id = 1L,
            setNumber = 1,
            reps = 10,
            isCompleted = true
        )
        val workSec = WorkToRestRatioEngine.estimateSetWorkSeconds(set, secondsPerRep = 3)
        assertEquals(30L, workSec)
    }

    @Test
    fun estimateSetWorkSeconds_completedDurationSet_usesDurationSeconds() {
        val set = WorkoutSet(
            id = 1L,
            setNumber = 1,
            setType = SetType.DURATION,
            durationSeconds = 45,
            reps = 45,
            isCompleted = true
        )
        val workSec = WorkToRestRatioEngine.estimateSetWorkSeconds(set)
        assertEquals(45L, workSec)
    }

    @Test
    fun estimateSetWorkSeconds_completedCardioSet_usesDurationSeconds() {
        val set = WorkoutSet(
            id = 1L,
            setNumber = 1,
            setType = SetType.CARDIO,
            durationSeconds = 600,
            reps = 600,
            isCompleted = true
        )
        val workSec = WorkToRestRatioEngine.estimateSetWorkSeconds(set)
        assertEquals(600L, workSec)
    }

    @Test
    fun calculateRatio_emptyWorkout_returnsZero() {
        val workout = Workout(id = 1L, name = "Empty", durationSeconds = 0)
        val ratio = WorkToRestRatioEngine.calculateRatio(workout)

        assertEquals(0L, ratio.workSeconds)
        assertEquals(0L, ratio.restSeconds)
        assertEquals(0L, ratio.totalSeconds)
        assertEquals("0:0", ratio.ratioString)
    }

    @Test
    fun calculateRatio_typicalWorkout_computesWorkAndRestPercentages() {
        // 4 sets of 10 reps @ 3s/rep = 120s of lifting work
        val sets = (1..4).map {
            WorkoutSet(id = it.toLong(), setNumber = it, reps = 10, isCompleted = true)
        }
        val workoutExercise = WorkoutExercise(
            id = 1L,
            workoutId = 1L,
            exercise = sampleExercise,
            sets = sets
        )
        // Total session duration: 600s (10 minutes) -> 120s work, 480s rest
        val workout = Workout(
            id = 1L,
            name = "Chest Day",
            durationSeconds = 600L,
            exercises = listOf(workoutExercise)
        )

        val result = WorkToRestRatioEngine.calculateRatio(workout)

        assertEquals(120L, result.workSeconds)
        assertEquals(480L, result.restSeconds)
        assertEquals(600L, result.totalSeconds)
        assertEquals(20.0, result.workPercentage, 0.01)
        assertEquals(80.0, result.restPercentage, 0.01)
        // 480 / 120 = 4 -> 1:4
        assertEquals("1:4", result.ratioString)
    }

    @Test
    fun calculateRatio_workExceedsSessionTotal_isCappedToTotal() {
        // 10 sets of 10 reps = 300s work, but session recorded as 200s
        val sets = (1..10).map {
            WorkoutSet(id = it.toLong(), setNumber = it, reps = 10, isCompleted = true)
        }
        val workoutExercise = WorkoutExercise(
            id = 1L,
            workoutId = 1L,
            exercise = sampleExercise,
            sets = sets
        )
        val workout = Workout(
            id = 1L,
            name = "Intense",
            durationSeconds = 200L,
            exercises = listOf(workoutExercise)
        )

        val result = WorkToRestRatioEngine.calculateRatio(workout)

        assertEquals(200L, result.workSeconds)
        assertEquals(0L, result.restSeconds)
        assertEquals(200L, result.totalSeconds)
        assertEquals(100.0, result.workPercentage, 0.01)
        assertEquals(0.0, result.restPercentage, 0.01)
        assertEquals("1:0", result.ratioString)
    }

    @Test
    fun formatRatio_variousInputs() {
        assertEquals("0:0", WorkToRestRatioEngine.formatRatio(0, 0))
        assertEquals("0:1", WorkToRestRatioEngine.formatRatio(0, 100))
        assertEquals("1:0", WorkToRestRatioEngine.formatRatio(100, 0))
        assertEquals("1:1", WorkToRestRatioEngine.formatRatio(100, 100))
        assertEquals("1:2", WorkToRestRatioEngine.formatRatio(100, 200))
        assertEquals("1:2.5", WorkToRestRatioEngine.formatRatio(100, 250))
    }
}
