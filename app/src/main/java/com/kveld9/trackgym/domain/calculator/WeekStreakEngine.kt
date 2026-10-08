package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import java.util.Calendar

/**
 * Result of week streak evaluation containing current active consecutive weeks,
 * longest recorded streak, and streak break timestamps if applicable.
 */
data class WeekStreakResult(
    val currentStreakWeeks: Int,
    val bestStreakWeeks: Int,
    val isStreakActiveThisWeek: Boolean,
    val lastTrainedWeekTimestamp: Long?
)

/**
 * Dedicated engine that evaluates consecutive weeks trained by checking intervals
 * between sessions across calendar week boundaries and identifying the exact cutoff point
 * where a streak breaks or continues.
 */
object WeekStreakEngine {

    fun calculateStreak(
        workouts: List<Workout>,
        referenceTimestamp: Long = System.currentTimeMillis()
    ): WeekStreakResult {
        val completedWorkouts = workouts.filter { it.isCompleted && (it.completedAt ?: it.startedAt) > 0L }
        if (completedWorkouts.isEmpty()) {
            return WeekStreakResult(
                currentStreakWeeks = 0,
                bestStreakWeeks = 0,
                isStreakActiveThisWeek = false,
                lastTrainedWeekTimestamp = null
            )
        }

        val cal = Calendar.getInstance()
        // Map Year-Week to latest completion timestamp in that week
        val weekMap = mutableMapOf<Pair<Int, Int>, Long>()
        for (w in completedWorkouts) {
            val ts = w.completedAt ?: w.startedAt
            cal.timeInMillis = ts
            val key = cal.get(Calendar.YEAR) to cal.get(Calendar.WEEK_OF_YEAR)
            val existing = weekMap[key]
            if (existing == null || ts > existing) {
                weekMap[key] = ts
            }
        }

        val activeWeeks = weekMap.keys
        val refCal = Calendar.getInstance().apply { timeInMillis = referenceTimestamp }
        val currentWeekKey = refCal.get(Calendar.YEAR) to refCal.get(Calendar.WEEK_OF_YEAR)

        val isTrainedCurrentWeek = activeWeeks.contains(currentWeekKey)

        // Find starting point for current streak:
        // If trained this week, start counting from this week.
        // If not trained yet this week, user has until end of week, so check if previous week was trained.
        val evalCal = Calendar.getInstance().apply { timeInMillis = referenceTimestamp }
        var currentStreak = 0

        if (isTrainedCurrentWeek) {
            while (true) {
                val key = evalCal.get(Calendar.YEAR) to evalCal.get(Calendar.WEEK_OF_YEAR)
                if (activeWeeks.contains(key)) {
                    currentStreak++
                    evalCal.add(Calendar.WEEK_OF_YEAR, -1)
                } else {
                    break
                }
            }
        } else {
            evalCal.add(Calendar.WEEK_OF_YEAR, -1)
            val prevWeekKey = evalCal.get(Calendar.YEAR) to evalCal.get(Calendar.WEEK_OF_YEAR)
            if (activeWeeks.contains(prevWeekKey)) {
                while (true) {
                    val key = evalCal.get(Calendar.YEAR) to evalCal.get(Calendar.WEEK_OF_YEAR)
                    if (activeWeeks.contains(key)) {
                        currentStreak++
                        evalCal.add(Calendar.WEEK_OF_YEAR, -1)
                    } else {
                        break
                    }
                }
            } else {
                currentStreak = 0
            }
        }

        // Calculate all-time best streak across the entire timeline
        val sortedWeekKeys = activeWeeks.toList().sortedWith(compareBy({ it.first }, { it.second }))
        var bestStreak = 0
        var tempStreak = 0
        var prevCal: Calendar? = null

        val walkCal = Calendar.getInstance()
        for (wKey in sortedWeekKeys) {
            if (prevCal == null) {
                tempStreak = 1
            } else {
                walkCal.timeInMillis = prevCal.timeInMillis
                walkCal.add(Calendar.WEEK_OF_YEAR, 1)
                val expectedKey = walkCal.get(Calendar.YEAR) to walkCal.get(Calendar.WEEK_OF_YEAR)
                if (expectedKey == wKey) {
                    tempStreak++
                } else {
                    tempStreak = 1
                }
            }
            if (tempStreak > bestStreak) {
                bestStreak = tempStreak
            }

            // Set prevCal to the current week
            prevCal = Calendar.getInstance().apply {
                set(Calendar.YEAR, wKey.first)
                set(Calendar.WEEK_OF_YEAR, wKey.second)
            }
        }

        val lastTimestamp = completedWorkouts.maxOfOrNull { it.completedAt ?: it.startedAt }

        return WeekStreakResult(
            currentStreakWeeks = currentStreak,
            bestStreakWeeks = maxOf(bestStreak, currentStreak),
            isStreakActiveThisWeek = isTrainedCurrentWeek,
            lastTrainedWeekTimestamp = lastTimestamp
        )
    }
}
