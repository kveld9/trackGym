package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Test

class MechanicsClassifierTest {

    @Test
    fun classify_compoundMultiJointMovements_returnsCompound() {
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Bench Press", ExerciseCategory.BARBELL, MuscleGroup.CHEST))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Barbell Squat", ExerciseCategory.BARBELL, MuscleGroup.LEGS))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Deadlift", ExerciseCategory.BARBELL, MuscleGroup.BACK))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Overhead Press", ExerciseCategory.BARBELL, MuscleGroup.SHOULDERS))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Pull Up", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.ARMS))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Close-Grip Bench Press", ExerciseCategory.BARBELL, MuscleGroup.ARMS))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Leg Press", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
    }

    @Test
    fun classify_isolationSingleJointMovements_returnsIsolation() {
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Bicep Curl", ExerciseCategory.DUMBBELL, MuscleGroup.ARMS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Tricep Pushdown (Cable)", ExerciseCategory.CABLE, MuscleGroup.ARMS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Lateral Raise", ExerciseCategory.DUMBBELL, MuscleGroup.SHOULDERS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Chest Fly (Machine)", ExerciseCategory.MACHINE, MuscleGroup.CHEST))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Cable Crossover", ExerciseCategory.CABLE, MuscleGroup.CHEST))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Calf Raise", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Leg Extension", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Leg Curl", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Face Pull", ExerciseCategory.CABLE, MuscleGroup.SHOULDERS))
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Barbell Shrug", ExerciseCategory.BARBELL, MuscleGroup.BACK))
    }

    @Test
    fun classify_armsDefault_returnsIsolationUnlessMultiJoint() {
        assertEquals(MechanicsType.ISOLATION, MechanicsClassifier.classify("Hammer Grip Lift", ExerciseCategory.DUMBBELL, MuscleGroup.ARMS))
        assertEquals(MechanicsType.COMPOUND, MechanicsClassifier.classify("Triceps Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.ARMS))
    }
}
