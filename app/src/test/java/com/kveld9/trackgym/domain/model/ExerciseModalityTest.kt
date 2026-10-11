package com.kveld9.trackgym.domain.model

import com.kveld9.trackgym.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseModalityTest {

    @Test
    fun enumOrderAndStringResources_matchContract() {
        val entries = ExerciseModality.entries
        assertEquals(3, entries.size)
        assertEquals(ExerciseModality.STRENGTH, entries[0])
        assertEquals(ExerciseModality.CARDIO, entries[1])
        assertEquals(ExerciseModality.MOBILITY, entries[2])

        assertEquals(R.string.modality_strength, ExerciseModality.STRENGTH.nameRes)
        assertEquals(R.string.modality_cardio, ExerciseModality.CARDIO.nameRes)
        assertEquals(R.string.modality_mobility, ExerciseModality.MOBILITY.nameRes)

        assertEquals("Strength", ExerciseModality.STRENGTH.displayName)
        assertEquals("Cardio", ExerciseModality.CARDIO.displayName)
        assertEquals("Mobility", ExerciseModality.MOBILITY.displayName)
    }

    @Test
    fun fromString_parsesValidNamesAndDisplayNames() {
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("STRENGTH"))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("Strength"))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("strength"))

        assertEquals(ExerciseModality.CARDIO, ExerciseModality.fromString("CARDIO"))
        assertEquals(ExerciseModality.CARDIO, ExerciseModality.fromString("Cardio"))
        assertEquals(ExerciseModality.CARDIO, ExerciseModality.fromString("cardio"))

        assertEquals(ExerciseModality.MOBILITY, ExerciseModality.fromString("MOBILITY"))
        assertEquals(ExerciseModality.MOBILITY, ExerciseModality.fromString("Mobility"))
        assertEquals(ExerciseModality.MOBILITY, ExerciseModality.fromString("mobility"))
    }

    @Test
    fun fromString_unknownOrEmpty_fallsBackToStrength() {
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("UNKNOWN"))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("OTHER"))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("BARBELL"))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString(""))
        assertEquals(ExerciseModality.STRENGTH, ExerciseModality.fromString("   "))
    }

    @Test
    fun classifyExerciseModality_cardioRepresentativeNames() {
        val cardioCases = listOf(
            "Run",
            "Running",
            "Trail Running",
            "Trail Run",
            "Jog",
            "Walk",
            "Swim",
            "Sprint",
            "Treadmill",
            "Rowing Machine",
            "Jump Rope",
            "Stationary Bike",
            "Cycling",
            "Elliptical",
            "StairMaster",
            "SkiErg",
            "Outdoor Jogging",
            "Sprint 100m",
            "Brisk Walk",
            "Shadow Boxing",
            "Ice Skating"
        )
        cardioCases.forEach { name ->
            assertEquals("Expected CARDIO for: $name", ExerciseModality.CARDIO, classifyExerciseModality(name))
        }
    }

    @Test
    fun classifyExerciseModality_mobilityRepresentativeNames() {
        val mobilityCases = listOf(
            "Hamstring Stretch",
            "Foam Roll",
            "Diaphragmatic Breathing",
            "Yoga",
            "Thoracic Spine Mobility",
            "Hip Warmup Drill",
            "Glute Activation",
            "SMR IT Band",
            "Meditation"
        )
        mobilityCases.forEach { name ->
            assertEquals("Expected MOBILITY for: $name", ExerciseModality.MOBILITY, classifyExerciseModality(name))
        }
    }

    @Test
    fun classifyExerciseModality_strengthRepresentativeNames() {
        val strengthCases = listOf(
            "Barbell Bench Press",
            "Barbell Back Squat",
            "Conventional Deadlift",
            "Barbell Row",
            "Barbell Bent-Over Row",
            "Cable Crunch",
            "Seated Cable Row",
            "Dumbbell Overhead Press",
            "Incline Bicep Curl"
        )
        strengthCases.forEach { name ->
            assertEquals("Expected STRENGTH for: $name", ExerciseModality.STRENGTH, classifyExerciseModality(name))
        }
    }

    @Test
    fun classifyExerciseModality_bareKeywordCollisions_resolveToStrength() {
        val collisionCases = listOf(
            "Barbell Bent-Over Row",
            "Cable Crunch",
            "Seated Cable Row"
        )
        collisionCases.forEach { name ->
            val message = "Expected STRENGTH (prevent collision) for: $name"
            assertEquals(message, ExerciseModality.STRENGTH, classifyExerciseModality(name))
        }
    }

    @Test
    fun classifyExerciseModality_ambiguousMovements_resolveToStrength() {
        val ambiguousStrengthCases = listOf(
            "Farmers Walk",
            "Farmer's Walk",
            "Farmer Walk",
            "Battle Ropes",
            "Battle Rope",
            "Sled Push",
            "Power Sled Pull",
            "Prowler Sprint",
            "Sled Drag - Harness"
        )
        ambiguousStrengthCases.forEach { name ->
            val message = "Expected STRENGTH for ambiguous movement: $name"
            assertEquals(message, ExerciseModality.STRENGTH, classifyExerciseModality(name))
        }
    }

    @Test
    fun classifyExerciseModality_caseInsensitiveMatching() {
        assertEquals(ExerciseModality.CARDIO, classifyExerciseModality("RUN"))
        assertEquals(ExerciseModality.CARDIO, classifyExerciseModality("treadmill"))
        assertEquals(ExerciseModality.CARDIO, classifyExerciseModality("ROWING MACHINE"))
        assertEquals(ExerciseModality.MOBILITY, classifyExerciseModality("HAMSTRING STRETCH"))
        assertEquals(ExerciseModality.MOBILITY, classifyExerciseModality("yoga"))
        assertEquals(ExerciseModality.MOBILITY, classifyExerciseModality("FOAM ROLL"))
        assertEquals(ExerciseModality.STRENGTH, classifyExerciseModality("FARMERS WALK"))
        assertEquals(ExerciseModality.STRENGTH, classifyExerciseModality("BATTLE ROPES"))
        assertEquals(ExerciseModality.STRENGTH, classifyExerciseModality("SLED PUSH"))
    }

    @Test
    fun classifyExerciseModality_tieBreaking_mobilityWinsOverCardio() {
        // When overlapping keywords occur, mobility must win over cardio
        assertEquals(ExerciseModality.MOBILITY, classifyExerciseModality("Dynamic Stretch Run"))
        assertEquals(ExerciseModality.MOBILITY, classifyExerciseModality("Warmup Jog"))
    }

    @Test
    fun classifyExerciseModality_blankOrEmpty_defaultsToStrength() {
        assertEquals(ExerciseModality.STRENGTH, classifyExerciseModality(""))
        assertEquals(ExerciseModality.STRENGTH, classifyExerciseModality("   "))
    }

    @Test
    fun defaultExercisesCatalog_populatesAllModalities() {
        val catalog = DefaultExercises.list
        assertTrue(catalog.isNotEmpty())
        assertTrue(
            "Catalog should contain STRENGTH exercises",
            catalog.any { it.modality == ExerciseModality.STRENGTH }
        )
        assertTrue(
            "Catalog should contain CARDIO exercises",
            catalog.any { it.modality == ExerciseModality.CARDIO }
        )
        assertTrue(
            "Catalog should contain MOBILITY exercises",
            catalog.any { it.modality == ExerciseModality.MOBILITY }
        )
    }
}
