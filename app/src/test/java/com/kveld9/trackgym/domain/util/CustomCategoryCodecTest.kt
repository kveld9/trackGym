package com.kveld9.trackgym.domain.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CustomCategoryCodecTest {

    @Test
    fun serialize_emptyList_returnsEmptyString() {
        assertEquals("", CustomCategoryCodec.serialize(emptyList()))
    }

    @Test
    fun serialize_listWithBlanksAndDuplicates_normalizesCorrectly() {
        val input = listOf(" Calisthenics ", "Powerlifting", "", "  ", "calisthenics", "Mobility")
        val serialized = CustomCategoryCodec.serialize(input)
        assertEquals("Calisthenics,Powerlifting,Mobility", serialized)
    }

    @Test
    fun serialize_tagsWithCommas_stripsCommasAndPreventsCorruption() {
        val input = listOf("Chest, Triceps", "Back, Biceps")
        val serialized = CustomCategoryCodec.serialize(input)
        assertEquals("Chest  Triceps,Back  Biceps", serialized)
        val deserialized = CustomCategoryCodec.deserialize(serialized)
        assertEquals(listOf("Chest  Triceps", "Back  Biceps"), deserialized)
    }

    @Test
    fun deserialize_nullOrBlank_returnsEmptyList() {
        assertTrue(CustomCategoryCodec.deserialize(null).isEmpty())
        assertTrue(CustomCategoryCodec.deserialize("").isEmpty())
        assertTrue(CustomCategoryCodec.deserialize("   ").isEmpty())
    }

    @Test
    fun deserialize_validString_splitsAndTrimsCorrectly() {
        val input = "Calisthenics , Powerlifting, Mobility ,  Calisthenics"
        val list = CustomCategoryCodec.deserialize(input)
        assertEquals(listOf("Calisthenics", "Powerlifting", "Mobility"), list)
    }

    @Test
    fun rename_existingTag_renamesCaseInsensitively() {
        val input = "Calisthenics,Powerlifting,Mobility"
        val renamed = CustomCategoryCodec.rename(input, "calisthenics", "Street Workout")
        assertEquals("Street Workout,Powerlifting,Mobility", renamed)
    }

    @Test
    fun rename_nonExistingTag_leavesStringUnchanged() {
        val input = "Powerlifting,Mobility"
        val renamed = CustomCategoryCodec.rename(input, "Calisthenics", "Street Workout")
        assertEquals("Powerlifting,Mobility", renamed)
    }

    @Test
    fun remove_existingTag_removesCaseInsensitively() {
        val input = "Calisthenics,Powerlifting,Mobility"
        val result = CustomCategoryCodec.remove(input, "POWERLIFTING")
        assertEquals("Calisthenics,Mobility", result)
    }

    @Test
    fun remove_nonExistingTag_leavesStringUnchanged() {
        val input = "Calisthenics,Mobility"
        val result = CustomCategoryCodec.remove(input, "Crossfit")
        assertEquals("Calisthenics,Mobility", result)
    }
}
