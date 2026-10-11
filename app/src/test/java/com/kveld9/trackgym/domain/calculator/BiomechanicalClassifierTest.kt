package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Test

class BiomechanicalClassifierTest {

    @Test
    fun classifyForce_staticExercises_returnsStatic() {
        assertEquals(ForceType.STATIC, BiomechanicalClassifier.classifyForce("Plank", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE))
        assertEquals(ForceType.STATIC, BiomechanicalClassifier.classifyForce("Side Plank Hold", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE))
        assertEquals(ForceType.STATIC, BiomechanicalClassifier.classifyForce("Wall Sit", ExerciseCategory.BODYWEIGHT, MuscleGroup.LEGS))
        assertEquals(ForceType.STATIC, BiomechanicalClassifier.classifyForce("Dead Hang", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK))
        assertEquals(ForceType.STATIC, BiomechanicalClassifier.classifyForce("L-Sit", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE))
    }

    @Test
    fun classifyForce_pullExercises_returnsPull() {
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Conventional Deadlift", ExerciseCategory.BARBELL, MuscleGroup.BACK))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Lat Pulldown", ExerciseCategory.CABLE, MuscleGroup.BACK))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Pull-ups", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Barbell Bent-Over Row", ExerciseCategory.BARBELL, MuscleGroup.BACK))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("EZ-Bar Bicep Curl", ExerciseCategory.BARBELL, MuscleGroup.ARMS))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Face Pull", ExerciseCategory.CABLE, MuscleGroup.SHOULDERS))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Lying Leg Curl", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Barbell Shrug", ExerciseCategory.BARBELL, MuscleGroup.BACK))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Hanging Leg Raise", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Hang Clean", ExerciseCategory.BARBELL, MuscleGroup.LEGS))
        assertEquals(ForceType.PULL, BiomechanicalClassifier.classifyForce("Sit-up", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE))
    }

    @Test
    fun classifyForce_pushExercises_returnsPush() {
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Barbell Bench Press", ExerciseCategory.BARBELL, MuscleGroup.CHEST))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Overhead Barbell Press", ExerciseCategory.BARBELL, MuscleGroup.SHOULDERS))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Parallel Bar Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.CHEST))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Barbell Back Squat", ExerciseCategory.BARBELL, MuscleGroup.LEGS))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Leg Press 45°", ExerciseCategory.MACHINE, MuscleGroup.LEGS))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Cable Triceps Pushdown", ExerciseCategory.CABLE, MuscleGroup.ARMS))
        assertEquals(ForceType.PUSH, BiomechanicalClassifier.classifyForce("Dumbbell Lateral Raise", ExerciseCategory.DUMBBELL, MuscleGroup.SHOULDERS))
    }

    @Test
    fun classifyDifficulty_expertMovements_returnsExpert() {
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Snatch", ExerciseCategory.BARBELL, MuscleGroup.LEGS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Clean and Jerk", ExerciseCategory.BARBELL, MuscleGroup.LEGS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Hang Clean", ExerciseCategory.BARBELL, MuscleGroup.LEGS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Barbell Muscle-Up", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Pistol Squat", ExerciseCategory.BODYWEIGHT, MuscleGroup.LEGS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.EXPERT, BiomechanicalClassifier.classifyDifficulty("Dragon Flag", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE, MechanicsType.COMPOUND))
    }

    @Test
    fun classifyDifficulty_intermediateMovements_returnsIntermediate() {
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Barbell Back Squat", ExerciseCategory.BARBELL, MuscleGroup.LEGS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Conventional Deadlift", ExerciseCategory.BARBELL, MuscleGroup.BACK, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Barbell Bench Press", ExerciseCategory.BARBELL, MuscleGroup.CHEST, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Overhead Barbell Press", ExerciseCategory.BARBELL, MuscleGroup.SHOULDERS, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Parallel Bar Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.CHEST, MechanicsType.COMPOUND))
        assertEquals(DifficultyLevel.INTERMEDIATE, BiomechanicalClassifier.classifyDifficulty("Pull-ups", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK, MechanicsType.COMPOUND))
    }

    @Test
    fun classifyDifficulty_beginnerMovements_returnsBeginner() {
        assertEquals(DifficultyLevel.BEGINNER, BiomechanicalClassifier.classifyDifficulty("Leg Extension", ExerciseCategory.MACHINE, MuscleGroup.LEGS, MechanicsType.ISOLATION))
        assertEquals(DifficultyLevel.BEGINNER, BiomechanicalClassifier.classifyDifficulty("Lying Leg Curl", ExerciseCategory.MACHINE, MuscleGroup.LEGS, MechanicsType.ISOLATION))
        assertEquals(DifficultyLevel.BEGINNER, BiomechanicalClassifier.classifyDifficulty("Plank", ExerciseCategory.BODYWEIGHT, MuscleGroup.CORE, MechanicsType.ISOLATION))
        assertEquals(DifficultyLevel.BEGINNER, BiomechanicalClassifier.classifyDifficulty("Cable Triceps Pushdown", ExerciseCategory.CABLE, MuscleGroup.ARMS, MechanicsType.ISOLATION))
        assertEquals(DifficultyLevel.BEGINNER, BiomechanicalClassifier.classifyDifficulty("Cable Crunch", ExerciseCategory.CABLE, MuscleGroup.CORE, MechanicsType.ISOLATION))
    }

    @Test
    fun classifyMechanics_compoundMultiJointMovements_returnsCompound() {
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Bench Press", ExerciseCategory.BARBELL, MuscleGroup.CHEST
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Barbell Squat", ExerciseCategory.BARBELL, MuscleGroup.LEGS
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Deadlift", ExerciseCategory.BARBELL, MuscleGroup.BACK
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Overhead Press", ExerciseCategory.BARBELL, MuscleGroup.SHOULDERS
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Pull Up", ExerciseCategory.BODYWEIGHT, MuscleGroup.BACK
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.ARMS
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Close-Grip Bench Press", ExerciseCategory.BARBELL, MuscleGroup.ARMS
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Leg Press", ExerciseCategory.MACHINE, MuscleGroup.LEGS
        ))
    }

    @Test
    fun classifyMechanics_isolationSingleJointMovements_returnsIsolation() {
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Bicep Curl", ExerciseCategory.DUMBBELL, MuscleGroup.ARMS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Tricep Pushdown (Cable)", ExerciseCategory.CABLE, MuscleGroup.ARMS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Lateral Raise", ExerciseCategory.DUMBBELL, MuscleGroup.SHOULDERS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Chest Fly (Machine)", ExerciseCategory.MACHINE, MuscleGroup.CHEST
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Cable Crossover", ExerciseCategory.CABLE, MuscleGroup.CHEST
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Calf Raise", ExerciseCategory.MACHINE, MuscleGroup.LEGS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Leg Extension", ExerciseCategory.MACHINE, MuscleGroup.LEGS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Leg Curl", ExerciseCategory.MACHINE, MuscleGroup.LEGS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Face Pull", ExerciseCategory.CABLE, MuscleGroup.SHOULDERS
        ))
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Barbell Shrug", ExerciseCategory.BARBELL, MuscleGroup.BACK
        ))
    }

    @Test
    fun classifyMechanics_armsDefault_returnsIsolationUnlessMultiJoint() {
        assertEquals(MechanicsType.ISOLATION, BiomechanicalClassifier.classifyMechanics(
            "Hammer Grip Lift", ExerciseCategory.DUMBBELL, MuscleGroup.ARMS
        ))
        assertEquals(MechanicsType.COMPOUND, BiomechanicalClassifier.classifyMechanics(
            "Triceps Dips", ExerciseCategory.BODYWEIGHT, MuscleGroup.ARMS
        ))
    }
}
