package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DefaultExercises
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ProgramFinderEngineTest {

    @Test
    fun catalog_allProgramsHaveValidRoutinesAndExercises() {
        val defaultExerciseNames = DefaultExercises.list.map { it.name.lowercase().trim() }.toSet()

        assertFalse(ProgramFinderEngine.CATALOG.isEmpty())
        for (prog in ProgramFinderEngine.CATALOG) {
            assertTrue(prog.name.isNotBlank())
            assertTrue(prog.description.isNotBlank())
            assertTrue(prog.targetFrequencyDays in 3..6)
            assertFalse(prog.routines.isEmpty())

            for (routine in prog.routines) {
                assertTrue(routine.name.isNotBlank())
                assertFalse(routine.exercises.isEmpty())

                for (ex in routine.exercises) {
                    assertTrue(ex.exerciseName.isNotBlank())
                    assertTrue(ex.targetSets in 1..10)
                    assertTrue(ex.defaultReps in 1..50)
                    // Every exercise should map to a recognized default exercise name
                    assertTrue(
                        "Exercise ${ex.exerciseName} should match a DefaultExercise",
                        defaultExerciseNames.contains(ex.exerciseName.lowercase().trim())
                    )
                }
            }
        }
    }

    @Test
    fun findRecommendations_3DaysBeginnerStrength_returnsFullBody3xFirst() {
        val results = ProgramFinderEngine.findRecommendations(
            daysPerWeek = 3,
            duration = SessionDuration.STANDARD,
            experience = ExperienceLevel.BEGINNER,
            goal = TrainingGoal.STRENGTH
        )
        assertNotNull(results)
        assertFalse(results.isEmpty())
        assertEquals("full_body_3x", results.first().id)
    }

    @Test
    fun findRecommendations_3DaysExpress_returnsFullBodyExpressFirst() {
        val results = ProgramFinderEngine.findRecommendations(
            daysPerWeek = 3,
            duration = SessionDuration.EXPRESS,
            experience = ExperienceLevel.BEGINNER,
            goal = TrainingGoal.HYPERTROPHY
        )
        assertNotNull(results)
        assertFalse(results.isEmpty())
        assertEquals("full_body_express_3x", results.first().id)
    }

    @Test
    fun findRecommendations_4DaysIntermediate_returnsUpperLowerFirst() {
        val results = ProgramFinderEngine.findRecommendations(
            daysPerWeek = 4,
            duration = SessionDuration.STANDARD,
            experience = ExperienceLevel.INTERMEDIATE,
            goal = TrainingGoal.HYBRID
        )
        assertNotNull(results)
        assertFalse(results.isEmpty())
        assertEquals("upper_lower_4x", results.first().id)
    }

    @Test
    fun findRecommendations_5DaysAdvanced_returnsPowerbuildingFirst() {
        val results = ProgramFinderEngine.findRecommendations(
            daysPerWeek = 5,
            duration = SessionDuration.LONG,
            experience = ExperienceLevel.ADVANCED,
            goal = TrainingGoal.HYBRID
        )
        assertNotNull(results)
        assertFalse(results.isEmpty())
        assertEquals("powerbuilding_5x", results.first().id)
    }

    @Test
    fun findRecommendations_6DaysHypertrophy_returnsPplFirst() {
        val results = ProgramFinderEngine.findRecommendations(
            daysPerWeek = 6,
            duration = SessionDuration.STANDARD,
            experience = ExperienceLevel.INTERMEDIATE,
            goal = TrainingGoal.HYPERTROPHY
        )
        assertNotNull(results)
        assertFalse(results.isEmpty())
        assertEquals("ppl_6x", results.first().id)
    }
}
