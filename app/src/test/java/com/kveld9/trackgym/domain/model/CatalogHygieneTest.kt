package com.kveld9.trackgym.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CatalogHygieneTest {

    @Test
    fun defaultExercisesCatalog_containsNoHdSuffixExercises() {
        val exercises = DefaultExercises.list
        val names = exercises.map { it.name }.toSet()

        val hdExercises = exercises.filter { it.name.endsWith(" HD", ignoreCase = true) }
        assertTrue(
            "Catalog must not contain any exercises ending with ' HD': ${hdExercises.map { it.name }}",
            hdExercises.isEmpty()
        )

        val obsoleteHdNames = listOf(
            "Bench Dips On Floor HD",
            "Bodyweight lunge HD",
            "Bodyweight Squat HD",
            "High Knee Skips HD",
            "Jumping Jack HD"
        )
        obsoleteHdNames.forEach { oldName ->
            assertFalse("Catalog should not contain obsolete name: $oldName", names.contains(oldName))
        }

        val canonicalCleanNames = listOf(
            "Bench Dips On Floor",
            "Bodyweight Lunge",
            "Bodyweight Squat",
            "High Knee Skips",
            "Jumping Jack"
        )
        canonicalCleanNames.forEach { cleanName ->
            assertTrue("Catalog must contain canonical clean name: $cleanName", names.contains(cleanName))
        }
    }

    @Test
    fun defaultExercisesCatalog_containsNoLegacyNonEnglishNames_andEnglishTargetsExist() {
        val names = DefaultExercises.list.map { it.name }.toSet()

        val obsoleteLegacyNames = listOf(
            "Schwimmen",
            "Talons fesses",
            "Supino inclinado",
            "Isometria alle parallele",
            "Isometria trazioni impugnatura inversa",
            "EZ-Bar Military Press mit SZ-Bar",
            "Rest (for timed workouts)"
        )
        obsoleteLegacyNames.forEach { oldName ->
            assertFalse("Catalog should not contain obsolete legacy name: $oldName", names.contains(oldName))
        }

        val englishCanonicalNames = listOf(
            "Swimming",
            "Butt Kicks",
            "Incline Bench Press",
            "Parallel Bar Isometric Hold",
            "Reverse Grip Pull-Up Isometric Hold",
            "EZ-Bar Military Press",
            "Rest Timer"
        )
        englishCanonicalNames.forEach { enName ->
            assertTrue("Catalog must contain canonical English name: $enName", names.contains(enName))
        }
    }

    @Test
    fun defaultExercisesCatalog_curatedCategoryFixes_matchAuthoritativeValues() {
        val expectedCategories = mapOf(
            "Machine chest fly" to ExerciseCategory.MACHINE,
            "Machine Hip Abduction" to ExerciseCategory.MACHINE,
            "Machine Lat Pulldown" to ExerciseCategory.MACHINE,
            "Pullover Machine" to ExerciseCategory.MACHINE,
            "Glute Kickback (Machine)" to ExerciseCategory.MACHINE,
            "Smith Machine Hip Raise" to ExerciseCategory.SMITH_MACHINE,
            "Smith Machine Leg Press" to ExerciseCategory.SMITH_MACHINE,
            "Smith Machine Squat" to ExerciseCategory.SMITH_MACHINE,
            "Smith Machine Bent Over Row" to ExerciseCategory.SMITH_MACHINE,
            "Smith Incline Shoulder Raise" to ExerciseCategory.SMITH_MACHINE,
            "High-Incline Smith Machine Press" to ExerciseCategory.SMITH_MACHINE,
            "Decline Smith Press" to ExerciseCategory.SMITH_MACHINE,
            "Assisted chin-ups" to ExerciseCategory.BODYWEIGHT,
            "Axle Deadlift" to ExerciseCategory.BARBELL,
            "Flat Machine Press" to ExerciseCategory.MACHINE,
            "Leverage Machine Chest Press" to ExerciseCategory.MACHINE,
            "Squats on Multipress" to ExerciseCategory.SMITH_MACHINE,
            "Calf Raises on Hackenschmitt Machine" to ExerciseCategory.MACHINE,
            "Pendulum Squat" to ExerciseCategory.MACHINE,
            "Trap Bar Shrug" to ExerciseCategory.BARBELL,
            "Pec Deck" to ExerciseCategory.MACHINE,
            "Pec deck rear delt fly" to ExerciseCategory.MACHINE,
            "Pause Hack Squats" to ExerciseCategory.MACHINE,
            "Pendular hack" to ExerciseCategory.MACHINE,
            "Radio Taiso" to ExerciseCategory.OTHER
        )

        expectedCategories.forEach { (exerciseName, expectedCategory) ->
            val matches = DefaultExercises.list.filter { it.name.equals(exerciseName, ignoreCase = true) }
            assertTrue("Exercise '$exerciseName' must exist in catalog", matches.isNotEmpty())
            matches.forEach { exercise ->
                assertEquals(
                    "Exercise '$exerciseName' category mismatch",
                    expectedCategory,
                    exercise.category
                )
            }
        }
    }

    @Test
    fun defaultExercisesCatalog_curatedMuscleGroupFixes_matchAuthoritativeValues() {
        val expectedMuscleGroups = mapOf(
            "Bench Press - Powerlifting" to MuscleGroup.CHEST,
            "Bench Press with Chains" to MuscleGroup.CHEST,
            "Cable Overhand Cable Curl" to MuscleGroup.ARMS,
            "Australian pull-ups" to MuscleGroup.BACK,
            "High plank" to MuscleGroup.CORE,
            "Walking Calf Raise" to MuscleGroup.LEGS,
            "Front lever pull-up" to MuscleGroup.BACK
        )

        expectedMuscleGroups.forEach { (exerciseName, expectedGroup) ->
            val matches = DefaultExercises.list.filter { it.name.equals(exerciseName, ignoreCase = true) }
            assertTrue("Exercise '$exerciseName' must exist in catalog", matches.isNotEmpty())
            matches.forEach { exercise ->
                assertEquals(
                    "Exercise '$exerciseName' muscle group mismatch",
                    expectedGroup,
                    exercise.muscleGroup
                )
            }
        }
    }

    @Test
    fun catalogContainsNoExactDuplicateNames() {
        val names = DefaultExercises.list.map { it.name }
        val duplicates = names.groupingBy { it }.eachCount().filter { it.value > 1 }
        assertTrue(
            "DefaultExercises.list must contain no duplicate exercise names, but found: ${duplicates.keys}",
            duplicates.isEmpty()
        )
    }
}
