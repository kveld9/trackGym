package com.kveld9.trackgym.domain.util

import kotlin.math.roundToInt

internal val WORD_DELIMITER_REGEX = Regex("[^\\p{L}\\p{N}]+")
private val SEPARATOR_REGEX = Regex("[\\s\\-_/]+")

/**
 * Pure Kotlin fuzzy string comparison utility inspired by RapidFuzz / FuzzyWuzzy.
 * Computes Levenshtein distance, normalized similarity ratios, partial ratios,
 * and token-based sorting ratios.
 */
object FuzzySearch {

    /**
     * Computes classic Levenshtein distance between two char sequences using an allocation-efficient
     * rolling array approach.
     */
    fun levenshteinDistance(s1: CharSequence, s2: CharSequence): Int {
        if (s1 == s2) return 0
        if (s1.isEmpty()) return s2.length
        if (s2.isEmpty()) return s1.length

        val len1 = s1.length
        val len2 = s2.length

        var prevRow = IntArray(len2 + 1) { it }
        var currRow = IntArray(len2 + 1)

        for (i in 0 until len1) {
            currRow[0] = i + 1
            val c1 = s1[i]
            for (j in 0 until len2) {
                val cost = if (c1 == s2[j]) 0 else 1
                currRow[j + 1] = minOf(
                    currRow[j] + 1,        // insertion
                    prevRow[j + 1] + 1,    // deletion
                    prevRow[j] + cost      // substitution
                )
            }
            val temp = prevRow
            prevRow = currRow
            currRow = temp
        }
        return prevRow[len2]
    }

    /**
     * Normalized similarity ratio between 0 and 100 based on Levenshtein distance.
     * 100 indicates identical non-empty strings, 0 indicates empty or completely dissimilar strings.
     */
    fun ratio(s1: String, s2: String): Int {
        val trimmed1 = s1.trim()
        val trimmed2 = s2.trim()
        if (trimmed1.isEmpty() || trimmed2.isEmpty()) return 0
        if (trimmed1.equals(trimmed2, ignoreCase = true)) return 100

        val dist = levenshteinDistance(trimmed1.lowercase(), trimmed2.lowercase())
        val maxLen = maxOf(trimmed1.length, trimmed2.length)
        val score = (((maxLen - dist).toDouble() / maxLen) * 100.0).roundToInt()
        return score.coerceIn(0, 100)
    }

    /**
     * Partial ratio matching: finds the best matching substring or token block of the longer string
     * compared to the shorter string.
     * If the shorter string is an exact substring of the longer string, returns 100.
     */
    fun partialRatio(s1: String, s2: String): Int {
        val norm1 = s1.trim().lowercase()
        val norm2 = s2.trim().lowercase()
        if (norm1.isEmpty() || norm2.isEmpty()) return 0
        if (norm1 == norm2) return 100

        val shorter = if (norm1.length <= norm2.length) norm1 else norm2
        val longer = if (norm1.length <= norm2.length) norm2 else norm1

        if (longer.contains(shorter)) return 100

        val windowRatio = maxWindowRatio(shorter, longer)
        val tokenRatio = maxTokenRatio(shorter, longer)
        return maxOf(windowRatio, tokenRatio)
    }

    private fun maxWindowRatio(shorter: String, longer: String): Int {
        val shortLen = shorter.length
        val maxStart = longer.length - shortLen
        if (maxStart < 0) return 0

        var maxRatio = 0
        for (i in 0..maxStart) {
            val sub = longer.substring(i, i + shortLen)
            val r = ratio(shorter, sub)
            if (r > maxRatio) {
                maxRatio = r
                if (maxRatio == 100) return 100
            }
        }
        return maxRatio
    }

    private fun maxTokenRatio(shorter: String, longer: String): Int {
        val tokens = longer.split(SEPARATOR_REGEX).filter { it.isNotEmpty() }
        var maxTokenRatio = 0
        for (token in tokens) {
            val r = ratio(shorter, token)
            if (r > maxTokenRatio) {
                maxTokenRatio = r
                if (maxTokenRatio == 100) return 100
            }
        }
        return maxTokenRatio
    }

    /**
     * Token sort ratio: tokenizes words, sorts them alphabetically, and computes similarity ratio.
     * Invariant to word order permutations (e.g. "bench press" vs "press bench").
     */
    fun tokenSortRatio(s1: String, s2: String): Int {
        if (s1.isBlank() || s2.isBlank()) return 0
        val sorted1 = tokenizeAndSort(s1)
        val sorted2 = tokenizeAndSort(s2)
        return ratio(sorted1, sorted2)
    }

    private fun tokenizeAndSort(s: String): String {
        val tokens = s.trim().lowercase()
            .split(WORD_DELIMITER_REGEX)
            .filter { it.isNotEmpty() }
        if (tokens.isEmpty()) {
            return s.trim().lowercase()
        }
        return tokens.sorted().joinToString(" ")
    }

    /**
     * Token set ratio: compares intersection of word sets against differences.
     */
    fun tokenSetRatio(s1: String, s2: String): Int {
        if (s1.isBlank() || s2.isBlank()) return 0
        val tokens1 = s1.trim().lowercase().split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }.toSet()
        val tokens2 = s2.trim().lowercase().split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }.toSet()
        if (tokens1.isEmpty() || tokens2.isEmpty()) {
            return ratio(s1, s2)
        }

        val intersection = tokens1.intersect(tokens2).sorted().joinToString(" ")
        val diff1 = tokens1.subtract(tokens2).sorted().joinToString(" ")
        val diff2 = tokens2.subtract(tokens1).sorted().joinToString(" ")

        val sSorted = intersection
        val s1Sorted = if (diff1.isEmpty()) intersection else "$intersection $diff1".trim()
        val s2Sorted = if (diff2.isEmpty()) intersection else "$intersection $diff2".trim()

        return maxOf(
            ratio(sSorted, s1Sorted),
            ratio(sSorted, s2Sorted),
            ratio(s1Sorted, s2Sorted)
        )
    }
}
