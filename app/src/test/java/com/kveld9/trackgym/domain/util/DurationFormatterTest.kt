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
