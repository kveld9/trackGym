package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class DayActivity(
    val dateKey: String,
    val timestamp: Long,
    val workoutCount: Int,
    val totalVolumeKg: Double,
    val intensityLevel: Int
)

data class TrainingConsistencyStats(
    val currentStreakWeeks: Int,
    val totalWorkoutsLast30Days: Int,
    val totalVolumeLast30Days: Double,
    val recentDays: List<DayActivity>,
    val bestStreakWeeks: Int = currentStreakWeeks,
    val isStreakActiveThisWeek: Boolean = false
)

object TrainingConsistencyEngine {

    private const val MS_PER_DAY = 24 * 60 * 60 * 1000L
    const val DAYS_TO_SHOW = 70

    fun calculateConsistency(
        workouts: List<Workout>,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): TrainingConsistencyStats {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val validWorkouts = workouts.filter { it.completedAt != null }

        val workoutsByDay = groupWorkoutsByDay(validWorkouts, dateFormat)
        val last30Workouts = filterLast30Days(validWorkouts, referenceTimestamp)

        val totalWorkoutsLast30Days = last30Workouts.size
        val totalVolumeLast30Days = last30Workouts.sumOf { it.totalVolume }

        val recentDays = generateRecentDays(referenceTimestamp, workoutsByDay, dateFormat)
        val streakResult = WeekStreakEngine.calculateStreak(validWorkouts, referenceTimestamp)

        return TrainingConsistencyStats(
            currentStreakWeeks = streakResult.currentStreakWeeks,
            bestStreakWeeks = streakResult.bestStreakWeeks,
            isStreakActiveThisWeek = streakResult.isStreakActiveThisWeek,
            totalWorkoutsLast30Days = totalWorkoutsLast30Days,
            totalVolumeLast30Days = totalVolumeLast30Days,
            recentDays = recentDays
        )
    }

    private fun groupWorkoutsByDay(
        workouts: List<Workout>,
        dateFormat: SimpleDateFormat
    ): Map<String, List<Workout>> {
        val result = mutableMapOf<String, MutableList<Workout>>()
        for (workout in workouts) {
            val dateKey = dateFormat.format(Date(workout.completedAt ?: workout.startedAt))
            result.getOrPut(dateKey) { mutableListOf() }.add(workout)
        }
        return result
    }

    private fun filterLast30Days(
        workouts: List<Workout>,
        referenceTimestamp: Long
    ): List<Workout> {
        val thirtyDaysAgo = referenceTimestamp - 30L * MS_PER_DAY
        return workouts.filter { (it.completedAt ?: it.startedAt) >= thirtyDaysAgo }
    }

    private fun generateRecentDays(
        referenceTimestamp: Long,
        workoutsByDay: Map<String, List<Workout>>,
        dateFormat: SimpleDateFormat
    ): List<DayActivity> {
        val startCal = Calendar.getInstance().apply {
            timeInMillis = referenceTimestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.DAY_OF_YEAR, -(DAYS_TO_SHOW - 1))
        }

        val daysList = ArrayList<DayActivity>(DAYS_TO_SHOW)
        for (i in 0 until DAYS_TO_SHOW) {
            val dateKey = dateFormat.format(startCal.time)
            val dayWorkouts = workoutsByDay[dateKey].orEmpty()
            val count = dayWorkouts.size
            val volume = dayWorkouts.sumOf { it.totalVolume }

            daysList.add(
                DayActivity(
                    dateKey = dateKey,
                    timestamp = startCal.timeInMillis,
                    workoutCount = count,
                    totalVolumeKg = volume,
                    intensityLevel = calculateIntensityLevel(count, volume)
                )
            )
            startCal.add(Calendar.DAY_OF_YEAR, 1)
        }
        return daysList
    }

    fun calculateIntensityLevel(count: Int, volume: Double): Int {
        if (count == 0) return 0
        if (count == 1 && volume < 5000) return 1
        if (count == 1) return 2
        return 3
    }
}
