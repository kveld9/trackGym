package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Workout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class WorkoutCalendarMonthEngineTest {

    @Test
    fun testFebruaryDaysCalculationForLeapAndNonLeapYears() {
        val leapData = WorkoutCalendarMonthEngine.computeMonthData(2024, Calendar.FEBRUARY, emptyList())
        assertEquals(29, leapData.totalDays)
        assertEquals(29, leapData.days.size)

        val regularData = WorkoutCalendarMonthEngine.computeMonthData(2025, Calendar.FEBRUARY, emptyList())
        assertEquals(28, regularData.totalDays)
        assertEquals(28, regularData.days.size)
    }

    @Test
    fun testFirstDayOfWeekOffsetForOctober2026() {
        // October 1, 2026 is a Thursday.
        // Monday = 0, Tuesday = 1, Wednesday = 2, Thursday = 3.
        val monthData = WorkoutCalendarMonthEngine.computeMonthData(2026, Calendar.OCTOBER, emptyList())
        assertEquals(3, monthData.firstDayOfWeekOffset)
        assertEquals(31, monthData.totalDays)
    }

    @Test
    fun testWorkoutsMappedToExactDaysAndFilteredByMonth() {
        val cal = Calendar.getInstance()

        // Workout 1: Oct 10, 2026
        cal.set(2026, Calendar.OCTOBER, 10, 10, 0, 0)
        val w1 = Workout(id = 1, name = "Leg Day", startedAt = cal.timeInMillis, isCompleted = true)

        // Workout 2: Oct 10, 2026 (same day second session)
        cal.set(2026, Calendar.OCTOBER, 10, 18, 0, 0)
        val w2 = Workout(id = 2, name = "Cardio", startedAt = cal.timeInMillis, isCompleted = true)

        // Workout 3: Oct 15, 2026
        cal.set(2026, Calendar.OCTOBER, 15, 9, 30, 0)
        val w3 = Workout(id = 3, name = "Push Day", startedAt = cal.timeInMillis, isCompleted = true)

        // Workout 4: September 28, 2026 (different month)
        cal.set(2026, Calendar.SEPTEMBER, 28, 12, 0, 0)
        val w4 = Workout(id = 4, name = "Old Workout", startedAt = cal.timeInMillis, isCompleted = true)

        val monthData = WorkoutCalendarMonthEngine.computeMonthData(
            year = 2026,
            month = Calendar.OCTOBER,
            completedWorkouts = listOf(w1, w2, w3, w4)
        )

        val day10 = monthData.days.first { it.dayOfMonth == 10 }
        assertTrue(day10.hasWorkouts)
        assertEquals(2, day10.workouts.size)
        assertEquals("2026-10-10", day10.dateKey)

        val day15 = monthData.days.first { it.dayOfMonth == 15 }
        assertTrue(day15.hasWorkouts)
        assertEquals(1, day15.workouts.size)

        val day1 = monthData.days.first { it.dayOfMonth == 1 }
        assertFalse(day1.hasWorkouts)
        assertEquals(0, day1.workouts.size)
    }
}
