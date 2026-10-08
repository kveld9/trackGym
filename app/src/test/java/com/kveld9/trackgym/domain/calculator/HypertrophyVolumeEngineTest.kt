package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.HypertrophyVolumeStatus
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class HypertrophyVolumeEngineTest {

    private val bench = Exercise(
        id = 1,
        name = "Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    private val pullup = Exercise(
        id = 2,
        name = "Pull-up",
        muscleGroup = MuscleGroup.BACK,
        category = ExerciseCategory.BODYWEIGHT
    )

    private fun createWorkout(id: Long, timestamp: Long, exercises: List<Pair<Exercise, Int>>): Workout {
        val weList = exercises.mapIndexed { idx, (ex, setCount) ->
            val sets = (1..setCount).map { sIdx ->
                WorkoutSet(
                    id = (id * 100 + idx * 10 + sIdx),
                    workoutExerciseId = id,
                    setNumber = sIdx,
                    weightKg = 50.0,
                    reps = 10,
                    isCompleted = true
                )
            }
            WorkoutExercise(
                id = (id * 10 + idx),
                workoutId = id,
                exercise = ex,
                sets = sets
            )
        }
        return Workout(
            id = id,
            name = "Workout $id",
            startedAt = timestamp - 3600000L,
            completedAt = timestamp,
            durationSeconds = 3600,
            isCompleted = true,
            exercises = weList
        )
    }

    @Test
    fun `empty workouts yields all muscle groups below optimal`() {
        val volumes = HypertrophyVolumeEngine.calculateWeeklyVolume(emptyList())
        assertEquals(6, volumes.size)
        assertTrue(volumes.all { it.weeklySets == 0 && it.status == HypertrophyVolumeStatus.BELOW_OPTIMAL })
    }

    @Test
    fun `sets are accurately aggregated per muscle group for current week`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        // 6 sets Chest, 4 sets Back
        val w1 = createWorkout(1, now, listOf(bench to 6, pullup to 4))
        // 8 sets Chest (total 14 -> Optimal 10..20)
        val w2 = createWorkout(2, now, listOf(bench to 8))

        val volumes = HypertrophyVolumeEngine.calculateWeeklyVolume(listOf(w1, w2), referenceTimestamp = now)

        val chestVol = volumes.first { it.muscleGroup == MuscleGroup.CHEST }
        assertEquals(14, chestVol.weeklySets)
        assertEquals(HypertrophyVolumeStatus.OPTIMAL, chestVol.status)

        val backVol = volumes.first { it.muscleGroup == MuscleGroup.BACK }
        assertEquals(4, backVol.weeklySets)
        assertEquals(HypertrophyVolumeStatus.BELOW_OPTIMAL, backVol.status)
    }

    @Test
    fun `more than 20 sets marks status as excessive overreaching`() {
        val now = System.currentTimeMillis()
        val w1 = createWorkout(1, now, listOf(bench to 22))

        val volumes = HypertrophyVolumeEngine.calculateWeeklyVolume(listOf(w1), referenceTimestamp = now)
        val chestVol = volumes.first { it.muscleGroup == MuscleGroup.CHEST }
        assertEquals(22, chestVol.weeklySets)
        assertEquals(HypertrophyVolumeStatus.EXCESSIVE, chestVol.status)
    }

    @Test
    fun `workouts from previous weeks are ignored`() {
        val nowCal = Calendar.getInstance()
        val now = nowCal.timeInMillis

        val twoWeeksAgoCal = (nowCal.clone() as Calendar).apply { add(Calendar.WEEK_OF_YEAR, -2) }
        val wOld = createWorkout(1, twoWeeksAgoCal.timeInMillis, listOf(bench to 15))

        val volumes = HypertrophyVolumeEngine.calculateWeeklyVolume(listOf(wOld), referenceTimestamp = now)
        val chestVol = volumes.first { it.muscleGroup == MuscleGroup.CHEST }
        assertEquals(0, chestVol.weeklySets)
        assertEquals(HypertrophyVolumeStatus.BELOW_OPTIMAL, chestVol.status)
    }
}
