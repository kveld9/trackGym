package com.kveld9.trackgym.domain.model

object DefaultExercises {
    private val baseList: List<Exercise> = listOf(
        // Chest
        Exercise(name = "Barbell Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Incline Dumbbell Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Parallel Bar Dips", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cable Crossover", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Chest Press Machine", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),

        // Back
        Exercise(name = "Pull-ups", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Barbell Bent-Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "One-Arm Dumbbell Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Seated Cable Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Conventional Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),

        // Legs
        Exercise(name = "Barbell Back Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Leg Press 45°", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Romanian Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lying Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Standing Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),

        // Shoulders
        Exercise(name = "Overhead Barbell Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Seated Dumbbell Shoulder Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Lateral Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Cable Lateral Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(name = "Face Pull", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),

        // Arms
        Exercise(name = "EZ-Bar Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Dumbbell Hammer Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Cable Triceps Pushdown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "EZ-Bar Skull Crusher", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),

        // Core
        Exercise(name = "Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hanging Leg Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cable Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE)
    )

    val list: List<Exercise> = baseList.map { ex ->
        val involvements = com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry.getInvolvementsForExercise(ex)
        val primary = involvements.firstOrNull { it.isPrimary }?.muscle
        val secondaries = involvements.filter { !it.isPrimary }
        val mechanics = com.kveld9.trackgym.domain.calculator.MechanicsClassifier.classify(ex.name, ex.category, ex.muscleGroup)
        val force = com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier.classifyForce(ex.name, ex.category, ex.muscleGroup)
        val level = com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier.classifyDifficulty(ex.name, ex.category, ex.muscleGroup, mechanics)
        ex.copy(primaryMuscle = primary, secondaryMuscles = secondaries, mechanics = mechanics, force = force, level = level)
    }
}
