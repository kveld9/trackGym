package com.kveld9.trackgym.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseTranslationCoverageTest {

    @Test
    fun `every default exercise has a non-null translation resource`() {
        val missing = DefaultExercises.list.map { it.name }.filter { name ->
            ExerciseTranslationRegistry.getNameRes(name) == null
        }
        assertTrue("Exercises missing translation resource: $missing", missing.isEmpty())
    }

    @Test
    fun `every default exercise has a non-empty spanish fallback`() {
        val missingSpanish = DefaultExercises.list.map { it.name }.filter { name ->
            ExerciseTranslationRegistry.getSpanishName(name).isNullOrBlank()
        }
        assertTrue("Exercises missing Spanish fallback: $missingSpanish", missingSpanish.isEmpty())
    }

    @Test
    fun `every registry entry exists in default exercises catalog`() {
        val catalogNames = DefaultExercises.list.map { it.name.lowercase() }.toSet()
        val unmapped = registryEntries()
            .map { it.englishName }
            .filter { it.lowercase() !in catalogNames }
        assertTrue("Registry entries not found in DefaultExercises: $unmapped", unmapped.isEmpty())
    }

    @Test
    fun `registry and catalog exercise counts are identical`() {
        assertEquals(
            "Registry entry count does not match DefaultExercises count",
            DefaultExercises.list.size,
            registryEntries().size
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun registryEntries(): List<ExerciseTranslationRegistry.TranslationEntry> {
        val field = ExerciseTranslationRegistry::class.java.getDeclaredField("entries")
        field.isAccessible = true
        return field.get(ExerciseTranslationRegistry) as List<ExerciseTranslationRegistry.TranslationEntry>
    }
}
