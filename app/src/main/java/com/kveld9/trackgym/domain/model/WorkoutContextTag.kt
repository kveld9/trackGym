package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Standard predefined context tags for tagging environmental, physiological,
 * or mental variables during a workout session.
 */
enum class StandardContextTag(
    val tagKey: String,
    @get:StringRes val labelRes: Int
) {
    FASTED("fasted", R.string.context_tag_fasted),
    LOW_SLEEP("low_sleep", R.string.context_tag_low_sleep),
    JOINT_PAIN("joint_pain", R.string.context_tag_joint_pain),
    PRE_WORKOUT("pre_workout", R.string.context_tag_pre_workout),
    HIGH_STRESS("high_stress", R.string.context_tag_high_stress),
    COMPETITION("competition", R.string.context_tag_competition),
    CALORIC_DEFICIT("caloric_deficit", R.string.context_tag_caloric_deficit),
    DELOAD_FEELING("deload", R.string.context_tag_deload);

    companion object {
        fun fromKey(key: String): StandardContextTag? = entries.firstOrNull { it.tagKey.equals(key, ignoreCase = true) }
    }
}

/**
 * Utility parser for serializing and deserializing workout context tags to/from CSV string.
 * Also provides utilities to embed and extract context tags to/from the workout notes field:
 * format: `[tags:fasted,low_sleep]\nUser notes...`
 */
object WorkoutContextTagParser {
    private const val DELIMITER = ","
    private const val TAG_PREFIX = "[tags:"
    private const val TAG_SUFFIX = "]"

    fun parse(raw: String?): List<String> {
        if (raw.isNullOrBlank()) return emptyList()
        return raw.split(DELIMITER)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
    }

    fun serialize(tags: List<String>): String {
        return tags
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .joinToString(DELIMITER)
    }

    fun toggleTag(currentTags: List<String>, tag: String): List<String> {
        val trimmed = tag.trim()
        if (trimmed.isEmpty()) return currentTags
        return if (currentTags.any { it.equals(trimmed, ignoreCase = true) }) {
            currentTags.filterNot { it.equals(trimmed, ignoreCase = true) }
        } else {
            currentTags + trimmed
        }
    }

    /**
     * Extracts context tags embedded in workout notes.
     */
    fun extractTagsFromNotes(notes: String?): List<String> {
        if (notes.isNullOrBlank()) return emptyList()
        val startIndex = notes.indexOf(TAG_PREFIX)
        if (startIndex == -1) return emptyList()
        val endIndex = notes.indexOf(TAG_SUFFIX, startIndex + TAG_PREFIX.length)
        if (endIndex == -1) return emptyList()
        val rawTags = notes.substring(startIndex + TAG_PREFIX.length, endIndex)
        return parse(rawTags)
    }

    /**
     * Strips embedded tag tokens from workout notes to return clean user notes.
     */
    fun extractCleanNotes(notes: String?): String {
        if (notes.isNullOrBlank()) return ""
        val startIndex = notes.indexOf(TAG_PREFIX)
        if (startIndex == -1) return notes.trim()
        val endIndex = notes.indexOf(TAG_SUFFIX, startIndex + TAG_PREFIX.length)
        if (endIndex == -1) return notes.trim()
        val cleaned = notes.removeRange(startIndex, endIndex + TAG_SUFFIX.length)
        return cleaned.trim()
    }

    /**
     * Embeds context tags into workout notes.
     */
    fun embedTagsIntoNotes(tags: List<String>, cleanNotes: String): String {
        val distinctTags = tags.map { it.trim() }.filter { it.isNotEmpty() }.distinct()
        val trimmedNotes = cleanNotes.trim()
        if (distinctTags.isEmpty()) return trimmedNotes
        val header = "$TAG_PREFIX${serialize(distinctTags)}$TAG_SUFFIX"
        return if (trimmedNotes.isEmpty()) header else "$header\n$trimmedNotes"
    }
}
