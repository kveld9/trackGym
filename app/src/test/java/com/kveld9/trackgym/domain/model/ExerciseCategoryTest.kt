package com.kveld9.trackgym.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseCategoryTest {

    @Test
    fun fromString_withCardio_fallsBackToOther() {
        assertEquals(ExerciseCategory.OTHER, ExerciseCategory.fromString("CARDIO"))
        assertEquals(ExerciseCategory.OTHER, ExerciseCategory.fromString("cardio"))
        assertEquals(ExerciseCategory.OTHER, ExerciseCategory.fromString("Cardio"))
    }

    @Test
    fun entries_containsNineValues_andNoCardio() {
        assertEquals(9, ExerciseCategory.entries.size)
        assertTrue(ExerciseCategory.entries.none { it.name.equals("CARDIO", ignoreCase = true) })
        assertTrue(ExerciseCategory.entries.none { it.displayName.equals("Cardio", ignoreCase = true) })
    }

    @Test
    fun defaultExercisesCatalog_containsZeroCardioExercises() {
        val exercises = DefaultExercises.list
        assertTrue(exercises.isNotEmpty())
        assertTrue(exercises.none { it.category.name.equals("CARDIO", ignoreCase = true) })
    }

    @Test
    fun defaultExercisesCatalog_excludesDeletedDistanceVariants() {
        val exerciseNames = DefaultExercises.list.map { it.name }.toSet()
        val deletedVariants = listOf(
            "100m Row",
            "100m Sprint",
            "10km Row",
            "1km Row",
            "200m Sprint",
            "2km Row",
            "400m Sprint",
            "500m Row",
            "Swimming 50m sprints"
        )
        deletedVariants.forEach { variant ->
            assertFalse("Catalog should not contain deleted variant: $variant", exerciseNames.contains(variant))
        }
    }

    @Test
    fun defaultExercisesCatalog_preservesCanonicalSurvivors() {
        val exerciseNames = DefaultExercises.list.map { it.name }.toSet()
        assertTrue("Sprint should exist as canonical survivor", exerciseNames.contains("Sprint"))
        assertTrue(
            "Row canonical survivor should exist",
            exerciseNames.contains("Rowing Machine") || exerciseNames.contains("Rowing, Stationary")
        )
        assertTrue(
            "Swim canonical survivor should exist",
            exerciseNames.contains("Swim") || exerciseNames.contains("Swimming Crawl Style")
        )
    }

    @Test
    fun classifyLegacyCardioCategory_machineKeywords_resolveToMachine() {
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Treadmill"))
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Stationary Bike"))
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Elliptical Trainer"))
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Indoor Rowing"))
    }

    @Test
    fun classifyLegacyCardioCategory_dumbbellKeywords_resolveToDumbbell() {
        assertEquals(ExerciseCategory.DUMBBELL, classifyLegacyCardioCategory("Dumbbell Complex"))
    }

    @Test
    fun classifyLegacyCardioCategory_bodyweightKeywords_resolveToBodyweight() {
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("Outdoor Run"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("Pool Swim"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("Morning Jog"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("Brisk Walk"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("100m Sprint"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("Shadow Boxing"))
    }

    @Test
    fun classifyLegacyCardioCategory_genericOrUnknown_fallsBackToOther() {
        assertEquals(ExerciseCategory.OTHER, classifyLegacyCardioCategory("Cardio Blast"))
        assertEquals(ExerciseCategory.OTHER, classifyLegacyCardioCategory("Unknown Conditioning"))
        assertEquals(ExerciseCategory.OTHER, classifyLegacyCardioCategory(""))
        assertEquals(ExerciseCategory.OTHER, classifyLegacyCardioCategory("   "))
    }

    @Test
    fun classifyLegacyCardioCategory_caseInsensitivity_matchesAllCasingVariants() {
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("TREADMILL"))
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("bIkE"))
        assertEquals(ExerciseCategory.DUMBBELL, classifyLegacyCardioCategory("DUMBBELL PRESS"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("sPrInT"))
        assertEquals(ExerciseCategory.BODYWEIGHT, classifyLegacyCardioCategory("sWiM"))
    }

    @Test
    fun classifyLegacyCardioCategory_priorityOrder_machineWinsOverBodyweight() {
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Treadmill Run"))
        assertEquals(ExerciseCategory.MACHINE, classifyLegacyCardioCategory("Bike Sprint"))
    }
}

