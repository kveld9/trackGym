package com.kveld9.trackgym.domain.util

import org.junit.Assert.assertEquals
import org.junit.Test

class DurationFormatterTest {

    @Test
    fun formatSecondsToMmSs_formatsStandardTimesCorrectly() {
        assertEquals("00:00", DurationFormatter.formatSecondsToMmSs(0))
        assertEquals("00:45", DurationFormatter.formatSecondsToMmSs(45))
        assertEquals("01:00", DurationFormatter.formatSecondsToMmSs(60))
        assertEquals("02:30", DurationFormatter.formatSecondsToMmSs(150))
        assertEquals("15:05", DurationFormatter.formatSecondsToMmSs(905))
    }

    @Test
    fun formatSecondsToMmSs_negativeSeconds_clampsToZero() {
        assertEquals("00:00", DurationFormatter.formatSecondsToMmSs(-10))
    }

    @Test
    fun formatSecondsToWorkoutTime_formatsTimesAcrossThresholdsCorrectly() {
        // Negative values and zero
        assertEquals("00:00", DurationFormatter.formatSecondsToWorkoutTime(-15))
        assertEquals("00:00", DurationFormatter.formatSecondsToWorkoutTime(0))

        // < 3600 (under 1 hour: mm:ss)
        assertEquals("00:45", DurationFormatter.formatSecondsToWorkoutTime(45))
        assertEquals("02:30", DurationFormatter.formatSecondsToWorkoutTime(150))
        assertEquals("59:59", DurationFormatter.formatSecondsToWorkoutTime(3599))

        // >= 3600 (1 hour and above: hh:mm:ss)
        assertEquals("01:00:00", DurationFormatter.formatSecondsToWorkoutTime(3600))
        assertEquals("01:01:05", DurationFormatter.formatSecondsToWorkoutTime(3665))
        assertEquals("02:02:02", DurationFormatter.formatSecondsToWorkoutTime(7322))

        // >= 86400 (24 hours and above, e.g. 25 hours -> 25:00:00)
        assertEquals("24:00:00", DurationFormatter.formatSecondsToWorkoutTime(86400))
        assertEquals("25:00:00", DurationFormatter.formatSecondsToWorkoutTime(90000))
        assertEquals("25:01:30", DurationFormatter.formatSecondsToWorkoutTime(90090))
    }

    @Test
    fun parseInputToSeconds_digitsOnlyTwoOrFewer_parsesAsSeconds() {
        assertEquals(0, DurationFormatter.parseInputToSeconds(""))
        assertEquals(9, DurationFormatter.parseInputToSeconds("9"))
        assertEquals(45, DurationFormatter.parseInputToSeconds("45"))
    }

    @Test
    fun parseInputToSeconds_threeOrMoreDigits_parsesAsMinutesAndSeconds() {
        // "130" -> min = 1, sec = 30 -> 1*60 + 30 = 90
        assertEquals(90, DurationFormatter.parseInputToSeconds("130"))
        // "1200" -> min = 12, sec = 00 -> 12*60 + 0 = 720
        assertEquals(720, DurationFormatter.parseInputToSeconds("1200"))
        // "1:30" with punctuation cleaned -> "130" -> 90
        assertEquals(90, DurationFormatter.parseInputToSeconds("1:30"))
    }
}
