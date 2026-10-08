package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import java.util.Calendar
import java.util.Locale

data class CalendarDayInfo(
    val dayOfMonth: Int,
    val dateKey: String,
    val timestamp: Long,
    val workouts: List<Workout>
) {
    val hasWorkouts: Boolean get() = workouts.isNotEmpty()
}

data class CalendarMonthData(
    val year: Int,
    val month: Int,
    val firstDayOfWeekOffset: Int,
    val totalDays: Int,
    val days: List<CalendarDayInfo>
)

object WorkoutCalendarMonthEngine {

    fun computeMonthData(
        year: Int,
        month: Int,
        completedWorkouts: List<Workout>
    ): CalendarMonthData {
        val cal = Calendar.getInstance().apply {
            clear()
            firstDayOfWeek = Calendar.MONDAY
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        val totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Convert Calendar.DAY_OF_WEEK (Sunday=1, Monday=2, ...) to Monday=0 ... Sunday=6
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        val firstDayOfWeekOffset = (dayOfWeek - Calendar.MONDAY + 7) % 7

        val workoutCal = Calendar.getInstance()
        val workoutsByDay = mutableMapOf<Int, MutableList<Workout>>()

        for (workout in completedWorkouts) {
            val ts = workout.completedAt ?: workout.startedAt
            workoutCal.timeInMillis = ts
            val wYear = workoutCal.get(Calendar.YEAR)
            val wMonth = workoutCal.get(Calendar.MONTH)
            if (wYear == year && wMonth == month) {
                val day = workoutCal.get(Calendar.DAY_OF_MONTH)
                workoutsByDay.getOrPut(day) { mutableListOf() }.add(workout)
            }
        }

        val daysList = (1..totalDays).map { day ->
            cal.set(Calendar.DAY_OF_MONTH, day)
            val dateKey = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, day)
            CalendarDayInfo(
                dayOfMonth = day,
                dateKey = dateKey,
                timestamp = cal.timeInMillis,
                workouts = workoutsByDay[day] ?: emptyList()
            )
        }

        return CalendarMonthData(
            year = year,
            month = month,
            firstDayOfWeekOffset = firstDayOfWeekOffset,
            totalDays = totalDays,
            days = daysList
        )
    }
}
