package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseTranslationRegistry
import com.kveld9.trackgym.domain.util.WORD_DELIMITER_REGEX
import java.text.Normalizer
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs
import kotlin.math.min

private val DIACRITICS_REGEX = Regex("\\p{M}+")

private class CachedExerciseTokens(
    val enFolded: String,
    val esFolded: String?,
    val enWords: List<String>,
    val esWords: List<String>
)

private fun containsWordStartingWith(text: String, prefix: String): Boolean {
    var idx = text.indexOf(prefix)
    while (idx != -1) {
        if (idx == 0 || !text[idx - 1].isLetterOrDigit()) {
            return true
        }
        idx = text.indexOf(prefix, idx + 1)
    }
    return false
}

private fun fastLevenshtein(s1: String, s2: String): Int {
    if (s1 == s2) return 0
    val m = s1.length
    val n = s2.length
    return when {
        m == 0 -> n
        n == 0 -> m
        else -> computeLevenshtein(s1, s2, m, n)
    }
}

private fun computeLevenshtein(s1: String, s2: String, m: Int, n: Int): Int {
    var prev = IntArray(n + 1) { it }
    var curr = IntArray(n + 1)

    for (i in 1..m) {
        curr[0] = i
        val c1 = s1[i - 1]
        for (j in 1..n) {
            val cost = if (c1 == s2[j - 1]) 0 else 1
            curr[j] = min(
                min(curr[j - 1] + 1, prev[j] + 1),
                prev[j - 1] + cost
            )
        }
        val temp = prev
        prev = curr
        curr = temp
    }
    return prev[n]
}

private const val SCORE_EXACT = 100
private const val SCORE_PREFIX = 98
private const val SCORE_WORD_PREFIX = 95
private const val SCORE_SUBSTRING = 90
private const val SCORE_ALL_TOKENS = 92
private const val SCORE_ACRONYM = 92
private const val SCORE_TYPO_DIST1 = 82
private const val SCORE_TYPO_DIST2 = 75
private const val MIN_TYPO_QUERY_LENGTH = 3
private const val MIN_TYPO_DIST2_LENGTH = 5
private const val MAX_TYPO_LENGTH_DELTA = 2
private const val MAX_ACRONYM_LENGTH = 8

private fun evaluateDirectMatch(cFolded: String, qFolded: String): Int {
    return when {
        cFolded == qFolded -> SCORE_EXACT
        cFolded.startsWith(qFolded) -> SCORE_PREFIX
        containsWordStartingWith(cFolded, qFolded) -> SCORE_WORD_PREFIX
        cFolded.contains(qFolded) -> SCORE_SUBSTRING
        else -> 0
    }
}

private fun evaluateTokensOrAcronym(cFolded: String, qFolded: String, qTokens: List<String>): Int {
    if (qTokens.size > 1 && qTokens.all { cFolded.contains(it) }) return SCORE_ALL_TOKENS
    return if (evaluateAcronym(qFolded, cFolded) > 0) SCORE_ACRONYM else 0
}

private fun evaluateWordsTypoMatch(words: List<String>, qFolded: String): Int {
    var matchScore = 0
    for (word in words) {
        if (abs(word.length - qFolded.length) <= MAX_TYPO_LENGTH_DELTA) {
            val dist = fastLevenshtein(qFolded, word)
            if (dist == 1) {
                matchScore = maxOf(matchScore, SCORE_TYPO_DIST1)
            } else if (dist == 2 && qFolded.length >= MIN_TYPO_DIST2_LENGTH) {
                matchScore = maxOf(matchScore, SCORE_TYPO_DIST2)
            }
        }
    }
    return matchScore
}

private fun evaluateAcronym(q: String, c: String): Int {
    if (q.length < 2 || q.length > MAX_ACRONYM_LENGTH) return 0
    var qIdx = 0
    var isWordStart = true
    for (i in c.indices) {
        val ch = c[i]
        if (ch.isLetterOrDigit()) {
            if (isWordStart) {
                if (qIdx < q.length && ch == q[qIdx]) {
                    qIdx++
                }
                isWordStart = false
            }
        } else {
            isWordStart = true
        }
    }
    return if (qIdx == q.length) SCORE_ACRONYM else 0
}

private fun scoreTokens(
    tokens: CachedExerciseTokens,
    qFolded: String,
    qTokens: List<String>,
    keepEnglish: Boolean
): Int {
    var bestScore = evaluateDirectMatch(tokens.enFolded, qFolded)
    if (!keepEnglish && tokens.esFolded != null) {
        bestScore = maxOf(bestScore, evaluateDirectMatch(tokens.esFolded, qFolded))
    }
    if (bestScore == 0) {
        bestScore = evaluateTokensOrAcronym(tokens.enFolded, qFolded, qTokens)
        if (!keepEnglish && tokens.esFolded != null) {
            bestScore = maxOf(bestScore, evaluateTokensOrAcronym(tokens.esFolded, qFolded, qTokens))
        }
    }
    if (bestScore == 0 && qFolded.length >= MIN_TYPO_QUERY_LENGTH && qTokens.size == 1) {
        bestScore = evaluateWordsTypoMatch(tokens.enWords, qFolded)
        if (!keepEnglish) {
            bestScore = maxOf(bestScore, evaluateWordsTypoMatch(tokens.esWords, qFolded))
        }
    }
    return bestScore
}

/**
 * High-performance search engine for exercises with typographical error tolerance,
 * acronym resolution, diacritic normalization, and fast ranking without heavy allocations.
 */
object FuzzyExerciseSearchEngine {

    private const val ASCII_MAX_CODE = 128
    const val HIGH_CONFIDENCE_THRESHOLD = 90
    const val MAX_FUZZY_LENGTH_DELTA = 15
    const val MAX_SEARCH_RESULTS = 80

    const val DEFAULT_MIN_SCORE: Int = 55

    private val tokenCache = ConcurrentHashMap<String, CachedExerciseTokens>()

    private fun getOrCreateTokens(exercise: Exercise): CachedExerciseTokens {
        return tokenCache.computeIfAbsent(exercise.name) { name ->
            val enFolded = stripDiacritics(name.lowercase().trim())
            val esRaw = ExerciseTranslationRegistry.getSpanishName(name)
            val esFolded = esRaw?.let { stripDiacritics(it.lowercase().trim()) }
            val enWords = enFolded.split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }
            val esWords = esFolded?.split(WORD_DELIMITER_REGEX)?.filter { it.isNotEmpty() } ?: emptyList()
            CachedExerciseTokens(enFolded, esFolded, enWords, esWords)
        }
    }

    fun filterAndRank(
        query: String,
        exercises: List<Exercise>,
        keepEnglish: Boolean = false,
        minScore: Int = DEFAULT_MIN_SCORE
    ): List<Exercise> {
        val cleanQuery = query.trim()
        val qFolded = stripDiacritics(cleanQuery.lowercase())
        if (cleanQuery.isBlank() || qFolded.isEmpty()) return exercises

        val qTokens = qFolded.split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }

        val scored = ArrayList<Pair<Exercise, Int>>()
        for (exercise in exercises) {
            val tokens = getOrCreateTokens(exercise)
            val bestScore = scoreTokens(tokens, qFolded, qTokens, keepEnglish)

            if (bestScore >= minScore) {
                scored.add(exercise to bestScore)
            }
        }
        scored.sortByDescending { it.second }
        val capped = if (scored.size > MAX_SEARCH_RESULTS) {
            scored.subList(0, MAX_SEARCH_RESULTS)
        } else {
            scored
        }
        return capped.map { it.first }
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

        val qFolded = stripDiacritics(cleanQuery.lowercase())
        val qTokens = qFolded.split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }

        var bestScore = 0
        for (candidate in candidates) {
            val score = scoreCandidateInternal(qFolded, qTokens, candidate)
            if (score > bestScore) {
                bestScore = score
                if (bestScore == 100) return 100
            }
        }
        return bestScore
    }

    fun stripDiacritics(text: String): String {
        if (text.isEmpty() || text.all { it.code < ASCII_MAX_CODE }) return text
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

    private fun evaluateTypoMatch(cFolded: String, qFolded: String, qTokens: List<String>): Int {
        if (qTokens.size != 1 || qFolded.length < MIN_TYPO_QUERY_LENGTH) return 0
        val words = cFolded.split(WORD_DELIMITER_REGEX).filter { it.isNotEmpty() }
        return evaluateWordsTypoMatch(words, qFolded)
    }

    private fun scoreCandidateInternal(
        qFolded: String,
        qTokens: List<String>,
        candidate: String
    ): Int {
        val cFolded = stripDiacritics(candidate.lowercase().trim())
        if (qFolded.isEmpty() || cFolded.isEmpty()) return 0

        val direct = evaluateDirectMatch(cFolded, qFolded)
        val tokenScore = if (direct > 0) direct else evaluateTokensOrAcronym(cFolded, qFolded, qTokens)
        return if (tokenScore > 0) tokenScore else evaluateTypoMatch(cFolded, qFolded, qTokens)
    }
}
