package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FuzzyExerciseSearchEngineTest {

    private val bench = Exercise(
        id = 1,
        name = "Barbell Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    private val squat = Exercise(
        id = 2,
        name = "Barbell Back Squat",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    private val deadlift = Exercise(
        id = 3,
        name = "Romanian Deadlift",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.COMPOUND
    )

    private val curl = Exercise(
        id = 4,
        name = "EZ-Bar Bicep Curl",
        muscleGroup = MuscleGroup.ARMS,
        category = ExerciseCategory.BARBELL,
        mechanics = MechanicsType.ISOLATION
    )

    private val legExtension = Exercise(
        id = 5,
        name = "Leg Extension",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.MACHINE,
        mechanics = MechanicsType.ISOLATION
    )

    private val latPulldown = Exercise(
        id = 6,
        name = "Lat Pulldown",
        muscleGroup = MuscleGroup.BACK,
        category = ExerciseCategory.CABLE,
        mechanics = MechanicsType.COMPOUND
    )

    private val exercises = listOf(bench, squat, deadlift, curl, legExtension, latPulldown)

    @Test
    fun testBlankQueryReturnsAllExercisesUnchanged() {
        val result = FuzzyExerciseSearchEngine.filterAndRank("", exercises)
        assertEquals(exercises, result)

        val spaceResult = FuzzyExerciseSearchEngine.filterAndRank("   ", exercises)
        assertEquals(exercises, spaceResult)
    }

    @Test
    fun testTypoMatchesSquat() {
        val result = FuzzyExerciseSearchEngine.filterAndRank("skwat", exercises)
        assertTrue(result.isNotEmpty())
        assertEquals("Barbell Back Squat", result.first().name)
    }

    @Test
    fun testPrefixMatchesBench() {
        val result = FuzzyExerciseSearchEngine.filterAndRank("benc", exercises)
        assertTrue(result.isNotEmpty())
        assertEquals("Barbell Bench Press", result.first().name)
    }

    @Test
    fun testSpanishNameResolutionAndDiacritics() {
        // "sentadilla" -> "Sentadilla trasera con barra" (Squat)
        val resultSpanish = FuzzyExerciseSearchEngine.filterAndRank("sentadilla", exercises, keepEnglish = false)
        assertTrue(resultSpanish.isNotEmpty())
        assertEquals("Barbell Back Squat", resultSpanish.first().name)

        // When keepEnglish = true, Spanish terms should not match
        val resultEnglishOnly = FuzzyExerciseSearchEngine.filterAndRank("sentadilla", exercises, keepEnglish = true)
        assertFalse(resultEnglishOnly.any { it.id == squat.id })

        // "jalon" (without accent) matches "Jalón al pecho"
        val resultJalon = FuzzyExerciseSearchEngine.filterAndRank("jalon", exercises, keepEnglish = false)
        assertTrue(resultJalon.isNotEmpty())
        assertEquals("Lat Pulldown", resultJalon.first().name)

        // "maquina" (without accent) matches "Extensión de piernas en máquina"
        val resultMaquina = FuzzyExerciseSearchEngine.filterAndRank("maquina", exercises, keepEnglish = false)
        assertTrue(resultMaquina.isNotEmpty())
        assertTrue(resultMaquina.any { it.id == legExtension.id })
    }

    @Test
    fun testSpanishAcronymMatching() {
        // "edp" -> "Extensión de piernas en máquina"
        val score = FuzzyExerciseSearchEngine.calculateScore("edp", legExtension, keepEnglish = false)
        assertTrue("Acronym score for edp should be high ($score)", score >= 90)

        val resultEdp = FuzzyExerciseSearchEngine.filterAndRank("edp", exercises, keepEnglish = false)
        assertTrue(resultEdp.isNotEmpty())
        assertEquals("Leg Extension", resultEdp.first().name)
    }

    @Test
    fun testAcronymMatchingEnglish() {
        val resultBbp = FuzzyExerciseSearchEngine.filterAndRank("bbp", exercises)
        assertTrue(resultBbp.isNotEmpty())
        assertEquals("Barbell Bench Press", resultBbp.first().name)

        val resultRd = FuzzyExerciseSearchEngine.filterAndRank("rd", exercises)
        assertTrue(resultRd.isNotEmpty())
        assertEquals("Romanian Deadlift", resultRd.first().name)
    }

    @Test
    fun testUnrelatedQueryReturnsEmpty() {
        val result = FuzzyExerciseSearchEngine.filterAndRank("xyzabc123", exercises)
        assertTrue(result.isEmpty())
    }

    @Test
    fun testRankingOrdersBestMatchFirst() {
        val result = FuzzyExerciseSearchEngine.filterAndRank("bench press", exercises)
        assertEquals(1, result.first().id)
    }
}
