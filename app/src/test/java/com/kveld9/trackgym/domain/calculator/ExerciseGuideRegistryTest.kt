package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DefaultExercises
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseGuideRegistryTest {

    @Test
    fun getGuideForExercise_allStandardExercises_haveCompleteGuides() {
        val exercises = DefaultExercises.list

        for (exercise in exercises) {
            val guide = ExerciseGuideRegistry.getGuideForExercise(exercise)
            assertNotNull("Guide should not be null for ${exercise.name}", guide)
            assertFalse("Setup steps should not be empty for ${exercise.name}", guide.setupSteps.isEmpty())
            assertFalse("Execution steps should not be empty for ${exercise.name}", guide.executionSteps.isEmpty())
            assertFalse("Common mistakes should not be empty for ${exercise.name}", guide.commonMistakes.isEmpty())
            assertTrue("Breathing cue resource should be non-zero", guide.breathingCueRes != 0)
            assertTrue("Tempo cue resource should be non-zero", guide.tempoCueRes != 0)
        }
    }

    @Test
    fun getGuideForExercise_benchPress_returnsSpecificCues() {
        val benchPress = Exercise(
            name = "Barbell Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL,
            mechanics = MechanicsType.COMPOUND
        )
        val guide = ExerciseGuideRegistry.getGuideForExercise(benchPress)

        assertTrue(guide.setupSteps.size >= 2)
        assertTrue(guide.executionSteps.size >= 2)
        assertTrue(guide.commonMistakes.size >= 2)
    }

    @Test
    fun getGuideForExercise_squat_returnsSpecificCues() {
        val squat = Exercise(
            name = "Barbell Back Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL,
            mechanics = MechanicsType.COMPOUND
        )
        val guide = ExerciseGuideRegistry.getGuideForExercise(squat)

        assertTrue(guide.setupSteps.size >= 2)
        assertTrue(guide.executionSteps.size >= 2)
    }

    @Test
    fun getGuideForExercise_customExercise_usesFallbackByMuscleGroupAndMechanics() {
        val customPush = Exercise(
            name = "Special Viking Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER,
            mechanics = MechanicsType.COMPOUND,
            isCustom = true
        )
        val guide = ExerciseGuideRegistry.getGuideForExercise(customPush)

        assertNotNull(guide)
        assertFalse(guide.setupSteps.isEmpty())
        assertFalse(guide.executionSteps.isEmpty())
        assertFalse(guide.commonMistakes.isEmpty())
    }
}
