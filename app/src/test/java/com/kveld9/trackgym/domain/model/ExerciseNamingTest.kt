package com.kveld9.trackgym.domain.model

import java.util.Locale
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

    @Test
    fun validateExerciseRename_withSameRowDifferentCase_isAllowed() {
        val result = validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "bench press",
            existingMatchId = 10L,
            existingMatchName = "Bench Press"
        )
        assertEquals("bench press", result)
    }

    @Test
    fun validateExerciseRename_withSameRowExactCase_isAllowed() {
        val result = validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "Bench Press",
            existingMatchId = 10L,
            existingMatchName = "Bench Press"
        )
        assertEquals("Bench Press", result)
    }

    @Test
    fun validateExerciseRename_withNoExistingMatch_isAllowed() {
        val result = validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "  Incline Dumbbell Fly  ",
            existingMatchId = null
        )
        assertEquals("Incline Dumbbell Fly", result)
    }

    @Test(expected = DuplicateExerciseNameException::class)
    fun validateExerciseRename_withDifferentRowConflict_throwsDuplicateException() {
        validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "Squat",
            existingMatchId = 20L,
            existingMatchName = "Squat"
        )
    }

    @Test
    fun validateExerciseRename_withDifferentRowConflict_preservesConflictingNameAndMessage() {
        try {
            validateExerciseRename(
                targetExerciseId = 10L,
                candidateName = "deadlift",
                existingMatchId = 99L,
                existingMatchName = "Deadlift"
            )
            org.junit.Assert.fail("Expected DuplicateExerciseNameException")
        } catch (e: DuplicateExerciseNameException) {
            assertEquals("Deadlift", e.conflictingName)
            assertTrue(e.message?.contains("Deadlift") == true)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun validateExerciseRename_withBlankName_throwsIllegalArgumentException() {
        validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "   ",
            existingMatchId = null
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun validateExerciseRename_withEmptyName_throwsIllegalArgumentException() {
        validateExerciseRename(
            targetExerciseId = 10L,
            candidateName = "",
            existingMatchId = null
        )
    }

    @Test
    fun isExerciseRenameConflicting_sameRowDifferentCase_returnsFalse() {
        assertFalse(isExerciseRenameConflicting(targetExerciseId = 5L, candidateName = "plank", existingMatchId = 5L))
    }

    @Test
    fun isExerciseRenameConflicting_differentRowMatch_returnsTrue() {
        assertTrue(isExerciseRenameConflicting(targetExerciseId = 5L, candidateName = "Plank", existingMatchId = 12L))
    }

    @Test
    fun isExerciseRenameConflicting_noMatchOrBlank_returnsFalse() {
        assertFalse(
            isExerciseRenameConflicting(
                targetExerciseId = 5L,
                candidateName = "Unique Exercise",
                existingMatchId = null
            )
        )
        assertFalse(isExerciseRenameConflicting(targetExerciseId = 5L, candidateName = "   ", existingMatchId = 12L))
    }

    @Test
    fun hasExerciseReferences_withZeroCounts_returnsFalse() {
        assertFalse(hasExerciseReferences(routineCount = 0, recordCount = 0))
    }

    @Test
    fun hasExerciseReferences_withRoutineCountGreaterThanZero_returnsTrue() {
        assertTrue(hasExerciseReferences(routineCount = 1, recordCount = 0))
    }

    @Test
    fun hasExerciseReferences_withRecordCountGreaterThanZero_returnsTrue() {
        assertTrue(hasExerciseReferences(routineCount = 0, recordCount = 2))
    }

    @Test
    fun canonicalizeExerciseName_withMultipleWhitespaceAndMixedCase_normalizesAndLowercases() {
        assertEquals("bench press", canonicalizeExerciseName("   Bench   Press  "))
    }

    @Test
    fun canonicalizeExerciseName_withCanonicalName_lowercasesOnly() {
        assertEquals("squat", canonicalizeExerciseName("Squat"))
    }

    @Test
    fun canonicalizeExerciseName_matchesLegacyMultiSpaceRowWithCanonicalSeed() {
        val legacyRowName = "Incline   Dumbbell   Press"
        val canonicalSeedName = "Incline Dumbbell Press"
        assertEquals(canonicalizeExerciseName(canonicalSeedName), canonicalizeExerciseName(legacyRowName))
    }

    @Test
    fun canonicalizeExerciseName_findsConflictingStoredDisplayName() {
        val existingNames = listOf("Barbell Back Squat", "Incline Dumbbell Press", "Bench Press")
        val candidateName = "  bench   PRESS "
        val canonicalTarget = canonicalizeExerciseName(candidateName)
        val matchedStoredName = existingNames.firstOrNull {
            canonicalizeExerciseName(it) == canonicalTarget
        }
        assertEquals("Bench Press", matchedStoredName)
    }

    @Test
    fun canonicalizeExerciseName_withTurkishLocale_lowercasesToRootAscii() {
        val originalLocale = Locale.getDefault()
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"))
            assertEquals("bike", canonicalizeExerciseName("BIKE"))
            assertEquals("bike", canonicalizeExerciseName("BiKe"))
        } finally {
            Locale.setDefault(originalLocale)
        }
    }
}

