package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DistributionMetricType
import com.kveld9.trackgym.domain.model.DistributionTimeBucket
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class TimeBucketedDistributionEngineTest {

    private val benchPress = Exercise(
        id = 1,
        name = "Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    private val squat = Exercise(
        id = 2,
        name = "Squat",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.BARBELL
    )

    private fun createWorkout(
        id: Long,
        timestamp: Long,
        exercise: Exercise,
        setsCount: Int,
        weight: Double,
        reps: Int
    ): Workout {
        val sets = (1..setsCount).map { i ->
            WorkoutSet(
                id = (id * 100 + i),
                workoutExerciseId = id,
                setNumber = i,
                weightKg = weight,
                reps = reps,
                isCompleted = true
            )
        }
        val we = WorkoutExercise(
            id = id,
            workoutId = id,
            exercise = exercise,
            sets = sets
        )
        return Workout(
            id = id,
            name = "Workout $id",
            startedAt = timestamp - 3600000L,
            completedAt = timestamp,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = listOf(we)
        )
    }

    @Test
    fun `empty workouts produces zero distribution`() {
        val res = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = emptyList(),
            bucket = DistributionTimeBucket.ALL_TIME,
            metricType = DistributionMetricType.SETS
        )
        assertEquals(0.0, res.totalValue, 0.001)
        assertEquals(0, res.totalWorkouts)
        assertTrue(res.muscleDistributions.isEmpty())
        assertTrue(res.topExercises.isEmpty())
    }

    @Test
    fun `time bucketing filters workouts outside window correctly`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        // 3 days ago (in last week, month, year, all-time)
        val t3d = (nowCal.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -3) }.timeInMillis
        // 15 days ago (outside last week, in last month, year, all-time)
        val t15d = (nowCal.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -15) }.timeInMillis
        // 50 days ago (outside last week & month, in last year, all-time)
        val t50d = (nowCal.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -50) }.timeInMillis

        val wRecent = createWorkout(1, t3d, benchPress, setsCount = 3, weight = 100.0, reps = 10)
        val wMonth = createWorkout(2, t15d, squat, setsCount = 4, weight = 120.0, reps = 8)
        val wYear = createWorkout(3, t50d, benchPress, setsCount = 5, weight = 90.0, reps = 10)

        val workouts = listOf(wRecent, wMonth, wYear)

        // Last week -> only wRecent
        val lastWeekRes = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = DistributionTimeBucket.LAST_WEEK,
            metricType = DistributionMetricType.SETS,
            referenceTimestamp = now
        )
        assertEquals(1, lastWeekRes.totalWorkouts)
        assertEquals(3.0, lastWeekRes.totalValue, 0.001)
        assertEquals(1, lastWeekRes.muscleDistributions.size)
        assertEquals(MuscleGroup.CHEST, lastWeekRes.muscleDistributions.first().muscleGroup)

        // Last month -> wRecent (3 sets) + wMonth (4 sets) = 7 sets
        val lastMonthRes = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = DistributionTimeBucket.LAST_MONTH,
            metricType = DistributionMetricType.SETS,
            referenceTimestamp = now
        )
        assertEquals(2, lastMonthRes.totalWorkouts)
        assertEquals(7.0, lastMonthRes.totalValue, 0.001)
        assertEquals(2, lastMonthRes.muscleDistributions.size)

        // All time -> all 3 workouts (3 + 4 + 5 = 12 sets)
        val allTimeRes = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = DistributionTimeBucket.ALL_TIME,
            metricType = DistributionMetricType.SETS,
            referenceTimestamp = now
        )
        assertEquals(3, allTimeRes.totalWorkouts)
        assertEquals(12.0, allTimeRes.totalValue, 0.001)
    }

    @Test
    fun `calculates volume and reps distribution accurately`() {
        val now = System.currentTimeMillis()
        // 3 sets of 100kg x 10 reps = 3000 volume, 30 reps
        val wBench = createWorkout(1, now, benchPress, setsCount = 3, weight = 100.0, reps = 10)
        // 2 sets of 150kg x 5 reps = 1500 volume, 10 reps
        val wSquat = createWorkout(2, now, squat, setsCount = 2, weight = 150.0, reps = 5)

        val workouts = listOf(wBench, wSquat)

        // Volume metric
        val volRes = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = DistributionTimeBucket.ALL_TIME,
            metricType = DistributionMetricType.VOLUME,
            referenceTimestamp = now
        )
        assertEquals(4500.0, volRes.totalValue, 0.001)
        val chestVol = volRes.muscleDistributions.first { it.muscleGroup == MuscleGroup.CHEST }
        val legsVol = volRes.muscleDistributions.first { it.muscleGroup == MuscleGroup.LEGS }
        assertEquals(3000.0, chestVol.value, 0.001)
        assertEquals(1500.0, legsVol.value, 0.001)
        assertEquals(3000.0 / 4500.0, chestVol.percentage.toDouble(), 0.01)

        // Reps metric
        val repsRes = TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = DistributionTimeBucket.ALL_TIME,
            metricType = DistributionMetricType.REPS,
            referenceTimestamp = now
        )
        assertEquals(40.0, repsRes.totalValue, 0.001)
        val chestReps = repsRes.muscleDistributions.first { it.muscleGroup == MuscleGroup.CHEST }
        val legsReps = repsRes.muscleDistributions.first { it.muscleGroup == MuscleGroup.LEGS }
        assertEquals(30.0, chestReps.value, 0.001)
        assertEquals(10.0, legsReps.value, 0.001)
    }
}
