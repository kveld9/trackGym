package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DefaultExercises
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ExerciseModality
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseSubstitutionEngineTest {

    private val exercises = DefaultExercises.list

    @Test
    fun findSubstitutes_forBarbellBenchPress_ranksChestCompoundsAtTop() {
        val benchPress = exercises.first { it.name == "Barbell Bench Press" }
        val substitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = benchPress,
            allExercises = exercises
        )

        assertTrue("Substitutes list should not be empty", substitutes.isNotEmpty())
        // Candidate should never be the target itself
        assertFalse("Target exercise should not be included in substitutes", substitutes.any { it.exercise.name == benchPress.name })

        // Top substitutes should be chest exercises
        val topMatch = substitutes.first()
        assertEquals(MuscleGroup.CHEST, topMatch.exercise.muscleGroup)
        assertTrue("Top substitute should have high affinity score (>= 80)", topMatch.matchScore >= 80)
        assertEquals(SubstitutionReason.EXACT_ANATOMY_AND_MECHANICS, topMatch.reason)

        // All compounds with same primary muscle should rank higher than isolation crossover
        val compoundMatches = substitutes.filter { it.exercise.mechanics == MechanicsType.COMPOUND }
        val isolationMatches = substitutes.filter { it.exercise.mechanics == MechanicsType.ISOLATION }

        assertTrue(compoundMatches.isNotEmpty())
        if (isolationMatches.isNotEmpty()) {
            assertTrue(compoundMatches.first().matchScore > isolationMatches.first().matchScore)
        }

        // Leg exercises should NOT be in the results
        assertFalse("Squats should not substitute Bench Press", substitutes.any { it.exercise.name.contains("Squat") })
    }

    @Test
    fun findSubstitutes_filteredByTargetCategory_returnsOnlySpecifiedEquipment() {
        val benchPress = exercises.first { it.name == "Barbell Bench Press" }
        val dumbbellSubstitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = benchPress,
            allExercises = exercises,
            targetCategory = ExerciseCategory.DUMBBELL,
            limit = exercises.size
        )

        assertTrue(dumbbellSubstitutes.isNotEmpty())
        assertTrue("All returned exercises must be dumbbells", dumbbellSubstitutes.all { it.exercise.category == ExerciseCategory.DUMBBELL })
        assertTrue(dumbbellSubstitutes.any { it.exercise.name == "Incline Dumbbell Press" })
    }

    @Test
    fun findSubstitutes_excludingCategory_excludesSpecifiedEquipment() {
        val benchPress = exercises.first { it.name == "Barbell Bench Press" }
        val substitutesWithoutBarbell = ExerciseSubstitutionEngine.findSubstitutes(
            target = benchPress,
            allExercises = exercises,
            excludedCategory = ExerciseCategory.BARBELL
        )

        assertTrue(substitutesWithoutBarbell.isNotEmpty())
        assertFalse("No barbell exercise should be present", substitutesWithoutBarbell.any { it.exercise.category == ExerciseCategory.BARBELL })
    }

    @Test
    fun findSubstitutes_forArmIsolation_recommendsOtherArmExercises() {
        val bicepCurl = exercises.first { it.name == "EZ-Bar Bicep Curl" }
        val substitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = bicepCurl,
            allExercises = exercises,
            limit = exercises.size
        )

        assertTrue(substitutes.isNotEmpty())
        val hammerCurl = substitutes.find { it.exercise.name == "Dumbbell Hammer Curl" }
        assertTrue("Hammer curl should be recommended for bicep curl", hammerCurl != null)
        assertEquals(MuscleGroup.ARMS, hammerCurl!!.exercise.muscleGroup)
    }

    @Test
    fun findSubstitutes_whenNoExercisesMatch_returnsEmptyList() {
        val isolatedExercise = Exercise(
            id = 999L,
            name = "Special Neck Bridge",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        )

        val substitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = isolatedExercise,
            allExercises = emptyList()
        )

        assertTrue(substitutes.isEmpty())
    }

    @Test
    fun findSubstitutes_prefersCandidateWithMatchingModality() {
        val target = Exercise(
            id = 1L,
            name = "Flat Dumbbell Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL,
            modality = ExerciseModality.STRENGTH,
            mechanics = MechanicsType.COMPOUND
        )
        val strengthCandidate = Exercise(
            id = 2L,
            name = "Incline Dumbbell Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL,
            modality = ExerciseModality.STRENGTH,
            mechanics = MechanicsType.COMPOUND
        )
        val cardioCandidate = Exercise(
            id = 3L,
            name = "Chest Cable Conditioning",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL,
            modality = ExerciseModality.CARDIO,
            mechanics = MechanicsType.COMPOUND
        )

        val substitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = target,
            allExercises = listOf(strengthCandidate, cardioCandidate)
        )

        assertEquals(2, substitutes.size)
        assertEquals("Incline Dumbbell Press", substitutes[0].exercise.name)
        assertEquals("Chest Cable Conditioning", substitutes[1].exercise.name)
        assertTrue(substitutes[0].matchScore > substitutes[1].matchScore)
        assertEquals(10, substitutes[0].matchScore - substitutes[1].matchScore)
    }

    @Test
    fun findSubstitutes_crossModalityReturnedWhenNoCloserCandidateExists() {
        val target = Exercise(
            id = 1L,
            name = "Target Strength Movement",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE,
            modality = ExerciseModality.STRENGTH,
            mechanics = MechanicsType.COMPOUND
        )
        val crossModalityCandidate = Exercise(
            id = 2L,
            name = "Leg Cardio Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE,
            modality = ExerciseModality.CARDIO,
            mechanics = MechanicsType.COMPOUND
        )

        val substitutes = ExerciseSubstitutionEngine.findSubstitutes(
            target = target,
            allExercises = listOf(crossModalityCandidate)
        )

        assertEquals(1, substitutes.size)
        assertEquals("Leg Cardio Machine", substitutes[0].exercise.name)
        assertEquals(ExerciseModality.CARDIO, substitutes[0].exercise.modality)
        assertTrue("Candidate should retain positive score without matching modality", substitutes[0].matchScore > 0)
    }
}
