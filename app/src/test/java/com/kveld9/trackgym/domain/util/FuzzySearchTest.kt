package com.kveld9.trackgym.domain.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FuzzySearchTest {

    @Test
    fun testLevenshteinDistance() {
        assertEquals(0, FuzzySearch.levenshteinDistance("", ""))
        assertEquals(0, FuzzySearch.levenshteinDistance("bench", "bench"))
        assertEquals(1, FuzzySearch.levenshteinDistance("bench", "benc"))
        assertEquals(2, FuzzySearch.levenshteinDistance("squat", "skwat"))
        assertEquals(5, FuzzySearch.levenshteinDistance("bench", ""))
        assertEquals(4, FuzzySearch.levenshteinDistance("", "curl"))
    }

    @Test
    fun testRatioExactAndTypos() {
        assertEquals(100, FuzzySearch.ratio("Squat", "Squat"))
        assertEquals(100, FuzzySearch.ratio("bench press", "BENCH PRESS"))
        assertEquals(0, FuzzySearch.ratio("", "bench"))
        assertEquals(0, FuzzySearch.ratio("", ""))

        val typoScore = FuzzySearch.ratio("skwat", "squat")
        assertTrue("Typo score should be reasonable ($typoScore)", typoScore >= 60)

        val unrelatedScore = FuzzySearch.ratio("squat", "deadlift")
        assertTrue("Unrelated score should be low ($unrelatedScore)", unrelatedScore < 40)
    }

    @Test
    fun testPartialRatio() {
        assertEquals(0, FuzzySearch.partialRatio("", ""))
        assertEquals(100, FuzzySearch.partialRatio("bench", "Barbell Bench Press"))
        assertEquals(100, FuzzySearch.partialRatio("benc", "Barbell Bench Press"))

        val typoInLonger = FuzzySearch.partialRatio("skwat", "Barbell Back Squat")
        assertTrue("Partial ratio for typo should match ($typoInLonger)", typoInLonger >= 60)

        val unrelated = FuzzySearch.partialRatio("deadlift", "Bicep Curl")
        assertTrue("Unrelated partial ratio should be low ($unrelated)", unrelated < 40)
    }

    @Test
    fun testTokenSortRatio() {
        assertEquals(100, FuzzySearch.tokenSortRatio("bench press", "press bench"))
        assertEquals(100, FuzzySearch.tokenSortRatio("barbell bench press", "press bench barbell"))

        val partialToken = FuzzySearch.tokenSortRatio("incline dumbbell press", "dumbbell incline press")
        assertEquals(100, partialToken)

        // Non-ASCII different words should NOT return 100
        val nonAsciiDifferent = FuzzySearch.tokenSortRatio("жим", "приседания")
        assertTrue("Non-ASCII different strings should not match 100 ($nonAsciiDifferent)", nonAsciiDifferent < 40)

        // Symbols only should not match 100
        val symbolsOnly = FuzzySearch.tokenSortRatio("!@#", "$$$")
        assertTrue("Pure symbol strings should not match 100 ($symbolsOnly)", symbolsOnly < 40)
    }

    @Test
    fun testTokenSetRatio() {
        val score = FuzzySearch.tokenSetRatio("bench press", "bench press barbell")
        assertTrue("Token set ratio should be very high ($score)", score >= 90)

        assertEquals(0, FuzzySearch.tokenSetRatio("", ""))
    }
}
