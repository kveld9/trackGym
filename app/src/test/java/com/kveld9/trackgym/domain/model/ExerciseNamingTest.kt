package com.kveld9.trackgym.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseNamingTest {

    @Test
    fun normalizeExerciseName_withSurroundingWhitespace_trimsCorrectly() {
        assertEquals("Plank", normalizeExerciseName("  Plank  "))
        assertEquals("Squat", normalizeExerciseName("\tSquat\n"))
        assertEquals("Bench Press", normalizeExerciseName("   Bench Press   "))
    }

    @Test
    fun normalizeExerciseName_withInternalWhitespaceRuns_collapsesToSingleSpace() {
        assertEquals("Bench Press", normalizeExerciseName("Bench   Press"))
        assertEquals("Incline Dumbbell Press", normalizeExerciseName("Incline \t  Dumbbell \n  Press"))
    }

    @Test
    fun normalizeExerciseName_withCleanName_preservesNameIntact() {
        assertEquals("Deadlift", normalizeExerciseName("Deadlift"))
        assertEquals("Overhead Press", normalizeExerciseName("Overhead Press"))
    }

    @Test
    fun areExerciseNamesConflicting_withIdenticalNames_returnsTrue() {
        assertTrue(areExerciseNamesConflicting("Plank", "Plank"))
        assertTrue(areExerciseNamesConflicting("Bench Press", "Bench Press"))
    }

    @Test
    fun areExerciseNamesConflicting_withCaseDifferences_returnsTrue() {
        assertTrue(areExerciseNamesConflicting("plank", "Plank"))
        assertTrue(areExerciseNamesConflicting("BENCH PRESS", "bench press"))
        assertTrue(areExerciseNamesConflicting("sQuAt", "Squat"))
    }

    @Test
    fun areExerciseNamesConflicting_withWhitespaceDifferences_returnsTrue() {
        assertTrue(areExerciseNamesConflicting("  Plank  ", "plank"))
        assertTrue(areExerciseNamesConflicting("Incline   Bench Press", "Incline Bench Press"))
        assertTrue(areExerciseNamesConflicting("\tDeadlift\n", "deadlift"))
    }

    @Test
    fun areExerciseNamesConflicting_withDifferentNames_returnsFalse() {
        assertFalse(areExerciseNamesConflicting("Plank", "Side Plank"))
        assertFalse(areExerciseNamesConflicting("Bench Press", "Incline Bench Press"))
        assertFalse(areExerciseNamesConflicting("Squat", "Front Squat"))
    }

    @Test
    fun areExerciseNamesConflicting_withBlankNames_returnsFalse() {
        assertFalse(areExerciseNamesConflicting("", ""))
        assertFalse(areExerciseNamesConflicting("   ", ""))
        assertFalse(areExerciseNamesConflicting("", "Plank"))
        assertFalse(areExerciseNamesConflicting("   ", "Plank"))
    }

    @Test
    fun duplicateExerciseNameException_preservesConflictingNameAndMessage() {
        val exception = DuplicateExerciseNameException(conflictingName = "Plank")
        assertEquals("Plank", exception.conflictingName)
        assertTrue(exception.message?.contains("Plank") == true)
    }
}
