package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseTranslationRegistry
import com.kveld9.trackgym.domain.util.FuzzySearch
import com.kveld9.trackgym.domain.util.WORD_DELIMITER_REGEX
import java.text.Normalizer

private val DIACRITICS_REGEX = Regex("\\p{M}+")

/**
 * Fuzzy search engine for exercises with typographical error tolerance, acronym resolution,
 * diacritic normalization, partial substring matching, and normalized score ranking.
 */
object FuzzyExerciseSearchEngine {

    const val DEFAULT_MIN_SCORE: Int = 55

    fun filterAndRank(
        query: String,
        exercises: List<Exercise>,
        keepEnglish: Boolean = false,
        minScore: Int = DEFAULT_MIN_SCORE
    ): List<Exercise> {
        val cleanQuery = query.trim()
        if (cleanQuery.isBlank()) return exercises

        return exercises
            .map { exercise -> exercise to calculateScore(cleanQuery, exercise, keepEnglish) }
            .filter { (_, score) -> score >= minScore }
            .sortedByDescending { (_, score) -> score }
            .map { (exercise, _) -> exercise }
    }

    fun calculateScore(
        query: String,
        exercise: Exercise,
        keepEnglish: Boolean = false
    ): Int {
        val cleanQuery = query.trim()
        if (cleanQuery.isBlank()) return 100

        val candidates = buildCandidates(exercise, keepEnglish)
        return calculateScore(cleanQuery, candidates)
    }

    fun calculateScore(query: String, candidates: List<String>): Int {
        val cleanQuery = query.trim()
        if (cleanQuery.isBlank()) return 100
        if (candidates.isEmpty()) return 0

        var bestScore = 0
        for (candidate in candidates) {
            val score = scoreCandidate(cleanQuery, candidate)
            if (score > bestScore) {
                bestScore = score
                if (bestScore == 100) return 100
            }
        }
        return bestScore
    }

    fun stripDiacritics(text: String): String {
        if (text.isEmpty()) return text
        val normalized = Normalizer.normalize(text, Normalizer.Form.NFD)
        return DIACRITICS_REGEX.replace(normalized, "")
    }

    private fun buildCandidates(exercise: Exercise, keepEnglish: Boolean): List<String> {
        val list = mutableListOf(exercise.name)
        if (!keepEnglish) {
            ExerciseTranslationRegistry.getSpanishName(exercise.name)?.let { list.add(it) }
        }
        return list
    }

    private fun scoreCandidate(query: String, candidate: String): Int {
        val qFolded = stripDiacritics(query.lowercase().trim())
        val cFolded = stripDiacritics(candidate.lowercase().trim())
        if (qFolded.isEmpty() || cFolded.isEmpty()) return 0

        val exactOrPrefix = evaluateExactOrPrefix(qFolded, cFolded)
        if (exactOrPrefix != null) return exactOrPrefix

        val acronymScore = evaluateAcronym(qFolded, cFolded)
        if (acronymScore > 0) return acronymScore

        return evaluateFuzzy(qFolded, cFolded)
    }

    private fun evaluateExactOrPrefix(q: String, c: String): Int? {
        if (c == q) return 100
        if (c.startsWith(q)) return 98
        if (containsWordStartingWith(c, q)) return 95
        if (c.contains(q)) return 90
        return null
    }

    private fun containsWordStartingWith(text: String, prefix: String): Boolean {
        val words = text.split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }
        return words.any { it.startsWith(prefix) }
    }

    private fun evaluateAcronym(q: String, c: String): Int {
        if (q.length < 2) return 0
        val initials = c.split(WORD_DELIMITER_REGEX)
            .filter { it.isNotEmpty() }
            .map { it.first() }
            .joinToString("")
        return if (initials == q || initials.startsWith(q)) 92 else 0
    }

    private fun evaluateFuzzy(q: String, c: String): Int {
        val pr = FuzzySearch.partialRatio(q, c)
        val ts = FuzzySearch.tokenSortRatio(q, c)
        val tset = FuzzySearch.tokenSetRatio(q, c)
        return maxOf(pr, ts, tset)
    }
}
