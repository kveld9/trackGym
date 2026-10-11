package com.kveld9.trackgym.ui.screens.active

import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Test

class ExercisePickerFilterTest {

    private val benchPress = Exercise(
        id = 1L,
        name = "Barbell Bench Press",
        muscleGroup = MuscleGroup.CHEST,
        category = ExerciseCategory.BARBELL
    )

    private val squat = Exercise(
        id = 2L,
        name = "Barbell Squat",
        muscleGroup = MuscleGroup.LEGS,
        category = ExerciseCategory.BARBELL
    )

    private val dumbbellCurl = Exercise(
        id = 3L,
        name = "Dumbbell Bicep Curl",
        muscleGroup = MuscleGroup.ARMS,
        category = ExerciseCategory.DUMBBELL
    )

    private val allExercises = listOf(benchPress, squat, dumbbellCurl)

    @Test
    fun filterPickerExercises_blankQueryAndMuscleChip_filtersList() {
        val result = filterPickerExercises(
            exercises = allExercises,
            exerciseToSubstitute = null,
            searchQuery = "",
            keepEnglish = true,
            selectedMuscleFilter = MuscleGroup.CHEST,
            selectedEquipmentFilter = null
        )
        assertEquals(listOf(benchPress), result)
    }

    @Test
    fun filterPickerExercises_substituteMode_ignoresChips() {
        val result = filterPickerExercises(
            exercises = allExercises,
            exerciseToSubstitute = benchPress,
            searchQuery = "",
            keepEnglish = true,
            selectedMuscleFilter = MuscleGroup.LEGS,
            selectedEquipmentFilter = ExerciseCategory.DUMBBELL
        )
        assertEquals(allExercises, result)
    }

    @Test
    fun filterPickerExercises_blankQuery_returnsBaseOrderUnchanged() {
        val result = filterPickerExercises(
            exercises = allExercises,
            exerciseToSubstitute = null,
            searchQuery = "   ",
            keepEnglish = false,
            selectedMuscleFilter = null,
            selectedEquipmentFilter = null
        )
        assertEquals(allExercises, result)
    }
}
