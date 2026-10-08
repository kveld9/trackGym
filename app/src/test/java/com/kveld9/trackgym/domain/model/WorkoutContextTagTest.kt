package com.kveld9.trackgym.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WorkoutContextTagTest {

    @Test
    fun standardContextTag_fromKey_findsMatchingTag() {
        assertEquals(StandardContextTag.FASTED, StandardContextTag.fromKey("fasted"))
        assertEquals(StandardContextTag.LOW_SLEEP, StandardContextTag.fromKey("low_sleep"))
        assertEquals(StandardContextTag.HIGH_STRESS, StandardContextTag.fromKey("HIGH_STRESS"))
        assertNull(StandardContextTag.fromKey("unknown_tag"))
    }

    @Test
    fun parse_emptyOrNull_returnsEmptyList() {
        assertTrue(WorkoutContextTagParser.parse(null).isEmpty())
        assertTrue(WorkoutContextTagParser.parse("").isEmpty())
        assertTrue(WorkoutContextTagParser.parse("   ").isEmpty())
    }

    @Test
    fun parse_validCsv_returnsDistinctTags() {
        val raw = "fasted, low_sleep, fasted, joint_pain"
        val tags = WorkoutContextTagParser.parse(raw)
        assertEquals(listOf("fasted", "low_sleep", "joint_pain"), tags)
    }

    @Test
    fun serialize_listOfTags_producesCleanCsv() {
        val tags = listOf("fasted", "joint_pain", "pre_workout")
        val csv = WorkoutContextTagParser.serialize(tags)
        assertEquals("fasted,joint_pain,pre_workout", csv)
    }

    @Test
    fun toggleTag_addsWhenAbsent_removesWhenPresent() {
        val initial = listOf("fasted", "pre_workout")
        val added = WorkoutContextTagParser.toggleTag(initial, "joint_pain")
        assertTrue(added.contains("joint_pain"))
        assertEquals(3, added.size)

        val removed = WorkoutContextTagParser.toggleTag(added, "fasted")
        assertFalse(removed.contains("fasted"))
        assertEquals(2, removed.size)
    }

    @Test
    fun embedAndExtractTags_roundTripPreservesNotesAndTags() {
        val tags = listOf("fasted", "low_sleep")
        val userNotes = "Felt great despite low sleep, hit a PR on bench!"
        val embedded = WorkoutContextTagParser.embedTagsIntoNotes(tags, userNotes)

        val extractedTags = WorkoutContextTagParser.extractTagsFromNotes(embedded)
        val cleanedNotes = WorkoutContextTagParser.extractCleanNotes(embedded)

        assertEquals(tags, extractedTags)
        assertEquals(userNotes, cleanedNotes)
    }

    @Test
    fun extractCleanNotes_withoutTags_returnsOriginalNotes() {
        val regularNotes = "Just standard bench session."
        val extractedTags = WorkoutContextTagParser.extractTagsFromNotes(regularNotes)
        val cleaned = WorkoutContextTagParser.extractCleanNotes(regularNotes)

        assertTrue(extractedTags.isEmpty())
        assertEquals(regularNotes, cleaned)
    }
}
