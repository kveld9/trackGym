package com.kveld9.trackgym.domain.model

object DefaultExercises {
    private fun chunk0(): List<Exercise> = listOf(
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
        Exercise(name = "Cable Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "1 Leg Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "1/2 Kneeling Thoracic Rotation",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "3/4 Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "4-count Burpees", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "45 Degree Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "90/90 Breathing", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "90/90 Hamstring", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "90/90 Hamstring / Barre-Style Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "90/90 Hip Switches", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ab Crunch Machine", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(name = "Ab wheel", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Abdominal Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Abdominal Draw-In", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Abdominal Stabilization",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Abduction while standing",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Adductor", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Adductor Rock Backs", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Adductor/Groin", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Advanced Kettlebell Windmill",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Air Bike", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "All Fours Quad Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Alternate back lunges",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Alternate Hammer Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Alternate Heel Touchers",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Alternate Incline Dumbbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Alternate Leg Diagonal Bound",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Alternate Walking", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Alternating Cable Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Alternating Deltoid Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Alternating Floor Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Alternating Hang Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Alternating Kettlebell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Alternating Kettlebell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Alternating Renegade Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Ankle Circles", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Ankle Dorsiflexion Rocks",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Ankle On The Knee", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Ankle Roll", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Anterior Tibialis-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Anti-Gravity Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Arabesque", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Arch Hang", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Archer Pull Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Arm and neck stretch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Arm Circles", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Arm Raises (T/Y/I)", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Arnold Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Around The Worlds", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Assault Bike Run", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Assisted Chest Dip Kneeling",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Assisted chin-ups", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Assisted Close Grip Underhand Chin-Up",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Assisted Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Assisted Triceps Dip Kneeling",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Assisted Weighted Push-Up",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Atlas Stone Trainer", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Atlas Stones", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Australian pull-ups", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Axle Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Back bridge", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Back Flyes - With Bands",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Back Lever", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Back neck stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Backward arm circles", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Backward Drag", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Backward Medicine Ball Throw",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Backward shoulder rotation",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bag training", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Balance Board", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Ball crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Ball Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Ball Slams", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Band Assisted Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Bent Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Chest Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BAND),
        Exercise(name = "Band Close Grip Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Fixed Back Underhand Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Front Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Good Morning", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Good Morning (Pull Through)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Hip Adductions", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Kneeling Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Kneeling Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Middle Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BAND),
        Exercise(name = "Band Pull Apart", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band pull-apart with external rotation",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Skull Crusher", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Standing Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BAND),
        Exercise(name = "Band Standing Hammer Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Terminal Knee extension",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Banded Ankle Mobility", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Banded Clamshell", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Banded Monster Walk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Banded Scapular Retraction", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(
            name = "Banded Shoulder Drills",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Barbell Ab Rollout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Ab Rollout - On Knees",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Bench Press - Medium Grip",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Bench Press Isometric",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Bench Press Narrow Grip",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Bent Over Rowing",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Bent Over Rowing Reverse",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Biceps Curls With Barbell",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Clean and Jerk OL",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Clean and Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Clean Pull", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Curls Lying Against An Incline",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Decline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Deficit Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Drag Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell External Rotation Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Floor Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Front Pull narrow",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Front pull wide", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Front Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Full Clean", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Full Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Full Zercher Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Glute Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Good Morning", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Guillotine Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Hack Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Hang Clean", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Hang Snatch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Hang Snatch Below the Knees",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Hip hinge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Incline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Incline Bench Press - Medium Grip",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Incline Close Grip Barbell Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Incline Shoulder Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Incline Shoulders Press Inside Squat Cage",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Jerk OL", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell JM Bench Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell JM Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
    )

    private fun chunk1(): List<Exercise> = listOf(
        Exercise(
            name = "Barbell KAS Glute Bridge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Kelso Shrugs", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Kickstand RDL", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Landmine press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Landmine Rotation",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Landmine Squat to Press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Larsen Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Low Box Squat - Wide Stance",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Lunges Standing", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Lying Seal Row on Rack",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Lying Triceps Extension Skull Crusher",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Lying Triceps Extensions",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Meadows Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Overhead Press Isometric",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Overhead Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Pause Full Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Paused Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Pendlay Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Perpendicular Unilateral Landmine Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Pin Bench Press BB",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Pin OHP", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Pin Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Power Clean",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Power Snatch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Prone Incline Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Push OHP", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Rack Pull", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Rear Delt Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Rear Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Revers Wrist Curl II",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Reverse Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Reverse Grip Barbell Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Reverse Grip Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Reverse Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Rollout from Bench",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Romanian Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Row (Overhand)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Row (Underhand)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Seated Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Seated Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Shoulder Grip Upright Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Shrug Behind The Back",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Side Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Side-laying interior rotation",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Silverback Shrug",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Single Leg Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Single Leg Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Sliding Lateral Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Smith Machine Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Snatch", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Snatch OL", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Split Jerk", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Squat To A Bench",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Back Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Dowel Shoulder press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Military Press Without Rack",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Single Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Step Back Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Step Ups", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Stiff Legged Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Sumo Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Sumo Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Supine press", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell T-Bar row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Thruster", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Trap press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Walking Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Wrist Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Battle Ropes", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Bear Crawl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Bear Crawl Low Hip", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Bear Crawl Sled Drags", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Bear Walk 2", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Behind Head Chest Stretch",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Belt Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Bench Dip on Floor", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bench Dips", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bench Dips On Floor HD",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bench Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bench Press - Powerlifting",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Bench Press - With Bands", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BAND),
        Exercise(name = "Bench Press with Chains", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Bench Sprint", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Bent Leg Kickback Kneeling",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Bent Leg Side Kick Kneeling",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bent Over Dumbbell Rear Delt Raise With Head On Bench",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Bent Over Low-Pulley Side Lateral",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Bent Over One-Arm Long Bar Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Bent over row to external rotation",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Bent Over Two-Arm Long Bar Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Bent Over Two-Dumbbell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Bent Over Two-Dumbbell Row With Palms In",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Bent Press", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Bent-Arm Barbell Pullover",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Bent-Arm Dumbbell Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Bent-Knee Hip Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bent-knee Iron Cross", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Bent-Leg Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Biceps Close Grip Pull Down",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Biceps with TRX", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bicycle Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bicycle Recline Walk", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Bicycle Twisting Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bicycling", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Bicycling, Stationary", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Bird Dog", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Black Widow Knee Slides", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Blackroll", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Board Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Bobbing Exhale Drill", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Body Tricep Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Body-Up", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bodyweight Flyes", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Bodyweight lunge HD", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bodyweight Mid Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Bodyweight Muscleup", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Rear Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Reverse Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bodyweight Squat HD", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Standing Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Walking Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Bosu Ball Cable Crunch With Side Bends",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Bottoms Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bottoms-Up Clean From The Hang Position",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Box Handstand Push-up",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Box Jump (Multiple Response)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Box Skip", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Box Squat with Bands", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Box Squat with Chains", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Boxing", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Boxing Cross (with Bag and Gloves)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Braced Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Brachialis-SMR", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Bradford/Rocky Presses",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Bretzel stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bronco", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Bulgarian split squats left",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bulgarian split squats right",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Burpee Long Jump with Push-Up",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Burpee Over the Bar", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Burpees", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Bus Drivers", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Butchers Block Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Butt Lift (Bridge)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butt-Ups", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butterfly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(name = "Butterfly Narrow Grip", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Butterfly Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butterfly Reverse", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Butterfly Sit Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butterfly Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butterfly Superman", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Butterfly Yoga Flaps", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Cable 1-Arm Half-Kneeling Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
    )

    private fun chunk2(): List<Exercise> = listOf(
        Exercise(
            name = "Cable 45 Degrees Single Arm Reverse Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Alternate Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Alternating High Cable Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Bar Lateral Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Behind the Back Cable Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Bent over Cable Flye",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Chest Press - Decline",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Close-grip supinated lat pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Concentration Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Cross-Body Cable Y-Raise",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Deadlifts", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Donkey Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Drag Pushdown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Facepull", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Fly Lower Chest", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Fly Middle Chest", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Fly Upper Chest", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Fly With Cable", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Front Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Front Raise with a small bar",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable glute extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Hammer Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Hammer Curl with Rope",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Hercules Pillars", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable High-Cable Cross Tricep Extention - NB",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable High-Cable Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable High-pulley pullover",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Hip Abduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Hip Adduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Horizontal Pallof Press",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Incline bench pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Incline Pushdown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Incline Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Internal Rotation",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Iron Cross", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Judo Flip", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Kickback II", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Kneeling Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Lat Pulldown - Cross Body Single Arm",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lat Pulldown Isometric",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lateral Pulldown with MAG Grip",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lateral Raises (Single Arm)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lateral Rows on Cable, One Armed",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lean-Away Cable Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Long-Pulley (low Row)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Long-Pulley, Narrow", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Low Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Low Pulley Cable Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Low Seated Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Low Seated Row with V-Bar",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Low-Cable Cross-Over - NB",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lying Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Mentzer Pulldown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Modified pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Neck extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Neutral Grip Kickback",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Neutral Grip Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Neutral-grip chest pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Omni Cable Cross-over",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable One Arm Bent Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable One Arm Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable One Arm Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable One Arm Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable One Arm Overhead Cable Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable One Arm Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Overhand Cable Curl", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Overhead Cable Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Overhead Triceps Extension Rope Attachment",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Pallof Press", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Press Around", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Pull Through", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Pullback", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Push Down with Rope Attachment",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Rear Delt Fly", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Rear-Delt Fly (single arm)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Reverse Cable Flye", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Reverse Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Reverse EZ Bar Cable Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Reverse Fly Standing",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Reverse Grip Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Reverse Grip Straight Back Seated High Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Reverse Grip Triceps Push Down SZ Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Reverse Woodchop", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Rocking Triceps Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Rope Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Rope Pullover/row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Rope Rear-Delt Rows",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Russian Twists", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Seated Cable Mid Trap Shrug",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Seated Chest Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Seated Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Seated Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Seated Row with V-Bar",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Seated V-Grip Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Seated Wide Grip Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Shotgun Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Shoulder External Rotation (Cable)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Shoulder Internal Rotation (Cable)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Shoulder Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Shoulder Y-pull cable",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Shrug-In", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Side Lateral Raise (Cable)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Side lateral raise - Front (Cable)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Side Straight-Arm Pulldown (Cable)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Single arm row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Single Arm Triceps Push Down Rope Attachment",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Single-arm cable pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Single-Arm Cable Row /w Rotation (floor)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Split Stance Single Arm Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Squat Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Squat Row with Rope Attachment",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Adduction (Cable)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Crossover High Reverse Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Hip Abduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Hip Adduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing in Ner Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Reverse Grip Curl Straight Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Single Arm Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Supinated Face Pull with Rope",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Y Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing-Up Straight Crossovers",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight Arm Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight Arm Pulldown with Rope",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight Bar Cable Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight Bar Cable Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight-arm Pull Down (bar Attachment)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable SZ Bar Close Grip Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Tri Extension - Internal Rotation",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Tricep Kickback", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Triceps Extensions on Cable With Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Triceps Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Triceps Push Down SZ Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Triceps Push Down V-Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Triceps Pushdown Isometric",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Trunk Rotation With Cable",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Unilateral Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Unilateral Cable row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Unilateral Cross Body Cable Pull Down",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Upright Row", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable V-Bar Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Wide-grip Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Wide-grip supinated lat pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Woodchoppers", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Wrist Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Calf Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Calf Press On The Leg Press Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Calf Raise On A Dumbbell",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Calf Raise using Hack Squat Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Calf Raises - With Bands", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Calf Raises on Hackenschmitt Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
    )

    private fun chunk3(): List<Exercise> = listOf(
        Exercise(
            name = "Calf raises, left leg",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Calf raises, one legged", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Calf raises, right leg",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Calf Stretch Elbows Against Wall",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Calf Stretch Hands Against Wall",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Calf-Machine Shoulder Shrug",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Calves foam roller", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Calves-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Captains Chair Leg Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Captains Chair Straight Leg Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Car Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Car Drivers", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Carioca Quick Step", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Cat Plank", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cat Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Cat-Cow", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Catch and Overhead Throw", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Chain Handle Extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Chain Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Chair dips", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Chair Leg Extended Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Chair Lower Back Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Chair Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Chair Upper Body Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Chest And Front Of Shoulder Stretch",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Chest Dip", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Chest Dip on Dip Pullup Cage",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Chest Push (multiple response)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Chest Push (single response)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Chest Push from 3 point stance",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Chest Push with Run Release",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Chest Stretch on Stability Ball",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Child's Pose", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Chin To Chest Stretch", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(name = "Chin tuck", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Circus Bell", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Clamshell", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Clamshell to Reverse Clamshell",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Clap Push-Up", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Claps over the head", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean and Jerk", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean and Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean from Blocks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean Pull", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Clean Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "ClimbMill", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Clock Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Clockwise neck circles",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Close Grip Pushup", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Close-Grip Barbell Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Close-Grip Dumbbell Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Close-Grip EZ Bar Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Close-Grip EZ-Bar Curl with Band",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Close-Grip EZ-Bar Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Close-Grip Front Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Close-grip Lat Pull Down", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Close-grip Press-ups",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Close-Grip Push-Up off of a Dumbbell",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Close-Grip Standing Barbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Cobra Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cocoons", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Commando Pull-ups", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Conan's Wheel", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Concentration Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Controlled Kegels", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Cool-Down Swim", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Copenhagen Adduction Exercise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Core Rotation", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cossack squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Couch Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Counterclockwise neck circles",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Cross Body Hammer Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Cross Over - With Bands", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BAND),
        Exercise(name = "Cross-Body Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Crossbody Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Crossbody Leg Swings", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Crossover Reverse Lunge", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Crucifix", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Crunch - Hands Overhead",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Crunch - Legs On Exercise Ball",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Crunch Floor", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Crunches on Machine", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Crunches With Legs Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Cuban Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Cycling", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Dancer's Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Dead Bug", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Dead Hang Stretch", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(name = "Deadlift with Chains", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Decline Bench Leg Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Decline Close-Grip Bench To Skull Crusher",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Decline Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Decline Dumbbell Flyes",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Decline Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Decline EZ Bar Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Decline Oblique Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Decline Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Decline Reverse Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Decline Smith Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Deep breathing (standing or seated)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Deficit Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Deficit Push ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Degrees Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Delt Stretch", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Depth Jump Leap", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Diagonal Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Diamond push ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Diaphragmatic Breathing", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Dip Machine", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Dips", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Dips - Chest Version", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Dips - Triceps Version",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Dips Between Two Benches",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Donkey Calf Raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Doorway Pectoral Stretch",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Double Kettlebell Alternating Hang Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Double Kettlebell Jerk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Double Kettlebell Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Double Kettlebell Snatch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Double Kettlebell Windmill",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Double Leg Butt Kick", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Double Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Double Under Jump Rope", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Double-Leg Abdominal Press",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Downward Facing Balance", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Downward Facing Dog", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Downward Facing Dog Adhomukha Svanasana",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Drag Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Dragon squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Dragon-flag", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Drop Push", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Dumbbell 3D lunge warmup",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(
            name = "Dumbbell 45° Lateral Raises",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Alternate Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Alternating Biceps Curls With Dumbbell",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Alternating dumbbell hammer curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Alternative DB Gorilla rows",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Arnold Press II",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Arnold Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Around Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Axe Hold", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Barbell Clean and press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bear crawl pull through",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Bench Press with Neutral Grip",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bench Seated Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent High Pulls",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent Over Dumbbell Rows (Single Arm)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent Over Dumbbell Rows (Two Arms)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent Over Face Pull",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Bent Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Bent-Over Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent-over Lateral Raises",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent-Over Reverse Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell bicep curl to press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Biceps Curls With Dumbbell",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bizeps Curls Trifecta",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Blaze", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Dumbbell Bradford press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bulgarian Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bulgarian Squat with Dumbbells",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Cheat Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Chest-Supported Rear Delt Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell close grip bench press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
    )

    private fun chunk4(): List<Exercise> = listOf(
        Exercise(
            name = "Dumbbell Concentration Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Cross-Bench Dumbbell Pullovers",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Curl - With Shoulder Elevated",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Curtsey Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell DB Underhand bench press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell DB Upper Chest Variation",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Decline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Decline Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Devil’s Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell donkey kick", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell drag curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Drop Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Elbows Tucked DB Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell farmer's carry",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Floor Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Fly With Dumbbells, Decline Bench",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Forearm Curls (underhand grip)",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Frog Hip Thrust",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Frog Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Front Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Glute Bridge Single-Arm Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Goblet Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Hang Power Cleans",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Helms Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Hex Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Incline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Bench Reverse Fly",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Chest-Supported Dumbbell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Dumbbell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Incline Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Incline Hammer Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline OHP DB",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Rear Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Incline Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Incline Shoulder Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Static Hold",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Y Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Ipsilateral Single Leg Stiff Leg Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Kettlebell One Legged Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Kickback", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Kong Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Kreis Press DB",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Lat Pull DB", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Lateral Raise Isometric",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lateral-to-Front Raises",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lento Avanti Seduto",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Lunges Standing",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Dumbbell Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Dumbbell Row SS Seated Shrug",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Neutral Grip Row on Rack",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying on Floor Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying One-Arm Rear Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Pronation",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Rear Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Rotator Cuff Exercise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Seal Row on Rack",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Supination",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Triceps Kickback",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell No Leg Drive Dumbbell Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One Arm Bent Over Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One Arm Snatch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One Arm Triceps Extension on Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One-Arm Heavy Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One-Arm Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One-Arm Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One-Arm Upright Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell or Machine Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Overhead Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Patadas traseras",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Preacher Curl - Externally Rotated",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Preacher Curl - Internally Rotated",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Preacher Curl Isometric",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Prone Incline Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Pullover", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Rear Delt Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Rear Delt Raises",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Rear Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Rear Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Renegade Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Reverse Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Reverse Grip Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Reverse Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell roman chair RDLS",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Romanian Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Russian Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Scaption", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Seated Arnold Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Box Jump",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Dumbbell Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Dumbbell Side Lateral",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated One-Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Palms-Up Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated rear delt rise",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Reverse Grip Concentration Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Triceps Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Seated W Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Shoulder Dumbbell Pendular Exercise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Shoulder External Rotation with Dumbbell",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Shoulder Raise (Dumbbell)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Shoulder Raise Side and Front DB",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Shoulder Rotations",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Side Bends on Machine",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Side Dumbbell Trunk Flexion",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Side Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Dumbbell Single Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Leg Hip Thrust",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Leg Romanian Deadlift Leg Raise",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Leg Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single-arm Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Split Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Squat To A Bench",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Squeeze Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Step Ups", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Step-Up", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Suitcase Carry",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell sumo deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Sumo Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Supine Hip Abduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Tate Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Thruster", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Tricep Dumbbell Kickback",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Tricep Extension -Pronated Grip",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Triceps Overhead (Dumbbell)",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Turkish Get-Up",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Underhand Dead Row",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Upright Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Waiter Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Walking Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Weighted Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell wide bicep curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Wrist curl, dumbbells",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell YTWL Exercise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
    )

    private fun chunk5(): List<Exercise> = listOf(
        Exercise(
            name = "Dumbbells on Scott Machine",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dynamic Back Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Dynamic Chest Stretch", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Dynamic Planche", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Dynamic side plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Easy Continuous Swim (Freestyle)",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Elbow Circles", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Elbow Lever", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Elbow to Knee", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Elbow Up and Down Dynamic Plank",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Elbows Back", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Elephant Walks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Elevated Back Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Elevated Cable Rows", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Elevated Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Elevated prayer stretch",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Elliptical", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Elliptical Machine Walk", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Elliptical Trainer", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Exercise Ball Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Exercise Ball Pull-In", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Exercise Band Dorsiflexion", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Exercise Band Plantarflexion",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Explosive Push Ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Extended Arm Child's Pose", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Extended Range One-Arm Kettlebell Floor Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "External Rotation", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "External Rotation with Band",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "External Rotation with Cable",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Extreme Lat Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Extreme Pec Stretch", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Extreme Shoulder Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Ez Barbell Standing Back Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Standing Wide Grip Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ-Bar Biceps Curls With SZ-bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "EZ-Bar Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "EZ-Bar Floor Skull Crusher",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ-Bar Military Press mit SZ-Bar",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ-Bar Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "EZ-Bar Preacher Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "EZ-Bar Reverse Bar Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "EZ-Bar Reverse Preacher Curl (Close Grip)",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ-Bar Skullcrusher SZ-bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ-Bar Upright Row, SZ-bar",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Face pulls with yellow/green band",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Farmers Walk", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Fast Skipping", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Finger Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Finger Pushup", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Fingerboard 20 mm edge", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Fire Hydrant Circles", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Flat Bench Cable Flyes", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Flat Bench Leg Pull-In",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Flat Bench Lying Leg Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Flat Machine Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "FlexBar Tyler Twist", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "FlexBar Wrist Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "FlexBar Wrist Flexion",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Flexor Incline Dumbbell Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Floor dips", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Floor Glider Hamstring Curls",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Floor Glute-Ham Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Floor Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Floor Press with Chains", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Flutter Kicks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Foam Roll IT Band", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Foam Roller Adductors",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Foam Roller Anterior tibialis",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Foam Roller Gluteus", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Foam Roller Iliotibial band",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Foam Roller quadriceps",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Foot-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Forward arm circles", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Forward Drag with Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Forward shoulder rotation",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Frankenstein Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Freehand Jump Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Frog Hops", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Frog Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Frog Sit-Ups", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Frog stand", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Frog Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Front Barbell Squat To A Bench",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Front Box Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Front Cone Hops (or hurdle hops)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Front Elbow Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Front Incline Dumbbell Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Front Leg Raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front Lever", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front Lever Hold", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front lever pull-up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front lever tuck", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front neck stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Front Plate Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Front Raise And Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Front Split Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Front Squat (Clean Grip)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Front Squats With Two Kettlebells",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Front Two-Dumbbell Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Front Wood Chop", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Full Range-Of-Motion Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Full Sit Outs", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Gentle Reverse Kegels", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Gentle Thoracic Rotation", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Gentle Upward-Facing Dog / Cobra",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Gironda Sternum Chins", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Glute Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Glute Bridge From Bench",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Glute Drive", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Glute Ham Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Glute Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Glute Kickback (Machine)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Glute Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Goblet Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.KETTLEBELL),
        Exercise(name = "Good Morning", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Good Morning off Pins", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Gorilla Chin/Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Groin and Back Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Groiners", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Guided or free meditation",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Hack Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Hammer Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Hammer Grip Incline DB Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Hammerstrength Decline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Hamstring Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hamstring Chokes", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Hamstring Foam roller",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hamstring Kicks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hamstring Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hamstring-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hand Grip", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Handstand", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Handstand Hold on Wall",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Handstand Push-Ups",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hang Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Hang Clean - Below the Knees",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Hang Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Hang Snatch - Below Knees",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Hanging Bar Good Morning",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Hanging Front Lever Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hanging Knee Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Hanging Leg Hip Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hanging Pike", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Hanging Straight Leg Hip Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Hanging Straight Leg Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Happy Baby", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Head tilts", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Head turns", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Heaving Snatch Balance", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Heavy Bag Thrust", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Heel Touches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "High Cable Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "High Knee Jumps", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "High Knee Skips", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "High Knee Skips HD", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "High knees", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "High plank", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "High Pull", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "High-Incline Smith Machine Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Hindu Pushups", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hindu Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hip Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hip Circles", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hip Circles (prone)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hip Crossover", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hip Extension with Bands", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Hip Flexion with Band", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Hip Flexor Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hip Lift with Band", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Hip Raise, Lying", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hip Thrusts", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Hip-Out and Hip-In Lateral Steps",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
    )

    private fun chunk6(): List<Exercise> = listOf(
        Exercise(name = "Hollow Hold", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hollow Rock", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Horizontal Shoulder Flexion Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Horizontal traction isometry",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Horse Stance (Side Splits)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hug A Ball", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Hug Knees To Chest", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Hurdle Hops", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Hyper Y W Combo", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Hyperextension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Hyperextensions (Back Extensions)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Hyperextensions With No Hyperextension Bench",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Ice cream maker", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Iliotibial Tract-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Inchworm", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Incline Barbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Incline Bench Press - MP", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Incline Bench Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Incline Cable Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Incline Cable Flye", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(name = "Incline Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Incline Dumbbell Bench With Palms Facing In",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Incline Dumbbell Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Incline Dumbbell Flyes",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Incline Dumbbell Flyes - With A Twist",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Incline Hammer Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Incline Inner Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Incline Plank With Alternate Floor Touch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Incline Push Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Incline Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Incline Push-Up Close-Grip",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Incline Push-Up Depth Jump",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Incline Push-Up Medium",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Incline Push-Up Reverse Grip",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Incline Push-Up Wide",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Incline Pushup on Box",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Incline Row - Rings", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Incline Shoulder Press Up",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Incline Skull Crush", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Inline Skating", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Intermediate Groin Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Intermediate Hip Flexor and Quad Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Internal Rotation with Band",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Inverted Lat Pull Down",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Inverted Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Inverted Row with Straps", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Iron Cross", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Iron Crosses (stretch)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Isometria alle parallele",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Isometria trazioni impugnatura inversa",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Isometric Chest Squeezes",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Isometric Neck Exercise - Front And Back",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Isometric Neck Exercise - Sides",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Isometric Squat to Failure",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Isometric Wipers", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "IT Band and Glute Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Jack Knife Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Janda Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jefferson Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Jerk Balance", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Jerk Dip Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "JM Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Jogging", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jogging, Treadmill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jump Box", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jump Rope", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jump rope: basic jumps", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jump Shrug", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jump Split", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jump Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jumping Jack", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jumping Jack HD", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Keg Load", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Kegel Exercise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Kettlebell 2 Handed Kettlebell Swing",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Arnold Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Around Head Rotation",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Curl with kettlebell two hands",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Dead Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell deadlifts", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Double Kettlebell Clean and Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Double Kettlebell Front Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Dynamic side hold",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Figure 8", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Goblet Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Gorilla Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Halo", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Halo with Overhead Extension",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Hang Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Hip Airplane",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell One-handed Kettlebell Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell One-Legged Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Pass Between The Legs",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Pirate Ships",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Pistol Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Seated Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Seesaw Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single Arm Clean",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single Arm Plank to Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single Arm Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single-Leg Balance with Kettlebell Circles",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single-Leg Lunge with Kettlebell:",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Snatch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Standing Slingshots",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Strict Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Sumo Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Sumo High Pull",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Sumo Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Swings", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Thruster",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Turkish Get-Up (Lunge style)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Turkish Getup Squat Style",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Windmill", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.KETTLEBELL),
        Exercise(name = "Kick with Board", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Kipping Muscle Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Knee Across The Body", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Knee Circles", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Knee Push-ups", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Knee Raises", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Knee to Chest Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Knee Tuck Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Knee/Hip Raise On Parallel Bars",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Kneeling Arm Drill", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Kneeling Cable Crunch With Alternating Oblique Twists",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Kneeling Cable Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Kneeling Forearm Stretch", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Kneeling High Pulley Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Kneeling Hip Flexor", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Kneeling Jump Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Kneeling kickbacks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Kneeling Pushup", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Kneeling Single-Arm High Pulley Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Kneeling Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Kneeling Superman", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Knieheben Beinhebemaschine",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Knieheben Beinhebemaschine seitlich",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Kroc Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "L Hold", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "L Sit", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "L-Sit (Foot Supported)",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "L-Sit Pull-ups", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Landmine", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Landmine 180", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Landmine 180's", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Landmine Linear Jammer",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Landmine One Arm Bent Over Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Landmine Single Arm Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Landmine Squat and Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Lat Pull Down (Leaning Back)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Lat Pull Down (Straight Back)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Lateral Bound", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lateral Box Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lateral Cone Hops", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Lateral Push Off", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Lateral Raise - With Bands",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Lateral Walk", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Latissimus Dorsi-SMR", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Left levator scapulae stretch",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Left neck stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Leg and hip stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg curl with elastic", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Leg Curls (laying)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Curls (sitting)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Extension Isometric", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Lift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Leg Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Leg Press on Hackenschmidt Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Leg Press Toe Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Presses (narrow)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Presses (wide)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Leg Pull-In", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Leg Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Leg Raise Dragon Flag",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Leg raises pull up bar",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
    )

    private fun chunk7(): List<Exercise> = listOf(
        Exercise(name = "Leg Raises, Standing", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Leg Swings (Front–Back)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Leg Wheel", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Leg-Over Floor Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Leg-Up Hamstring Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Legend Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Legend Incline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Lever Alternate Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Back Extension", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Belt Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Bent Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Bent Over Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Bent Over Row with V-Bar Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Biceps Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Chest Press Plate Loaded",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Cross Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Decline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Front Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever High Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Horizontal One Leg Press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Incline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Low Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Low Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Lying Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Lying T-Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Military Press Plate Loaded",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Neutral Grip Seated Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever One Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Pec Deck Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Pendulum Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Pendulum Squat Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Pullover", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Pullover Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Pure Rear Kick", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Reverse Hyperextension",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Reverse Hyperextension Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Rotary Calf", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Seated Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Calf Raise Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Crunch Chest Pad",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Dip", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Seated Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Hip Abduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Hip Adduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Leg Extension",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Leg Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Reverse Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Shrug Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Squat Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Rear Kick",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Single Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Single Leg Curl Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever T-Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever T-Bar Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Torso Rotation", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Triceps Dip Plate Loaded",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Triceps Extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lever Vertical Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Leverage Chest Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(name = "Leverage Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Leverage Decline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Leverage High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Leverage Incline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Leverage Iso Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Leverage Machine Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Leverage Machine Iso Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Leverage Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Leverage Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Levitation Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Limber 11", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Linear 3-Part Start Technique",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Linear Acceleration Wall Drill",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Linear Depth Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Log Lift", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "London Bridges", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Long Distance Running", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Long Lunge Pulse Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Looking At Ceiling", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Low Cable Crossover", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Low Cable Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Low Pulley Row To Neck",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Lower Back Curl", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lower Back Extensions", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Lower Back-SMR", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Lunge Pass Through", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.KETTLEBELL),
        Exercise(name = "Lunge Sprint", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lunge with Twist Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Lunges", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lying Bent Leg Groin", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Lying Cable Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Lying Cambered Barbell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Lying Close-Grip Bar Curl On High Pulley",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Lying Close-Grip Barbell Triceps Extension Behind The Head",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Lying Close-Grip Barbell Triceps Press To Chin",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Lying Crossover", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Lying Dumbbell Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Lying Elbow to Knee", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Lying Face Down Plate Neck Resistance",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Lying Face Up Plate Neck Resistance",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Lying Figure Four Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Lying Glute", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lying Hamstring", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Lying Hamstring Stretch with Band",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Lying High Bench Barbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Lying Hip Leg Raise on Floor",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Lying Knee Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lying Leg Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lying Machine Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lying Neck Extension", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(name = "Lying Neck Flexion", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Lying One-Arm Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Lying Prone Quadriceps",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Lying Rear Delt Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Lying Scissors Cross", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Lying Supine Dumbbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Lying T-Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Lying Toe Touch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lying Triceps Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Machine Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(name = "Machine Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Machine chest fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Machine Dip or Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Machine glute extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Machine Hip Abduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Machine Incline Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Machine Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Machine Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Machine Preacher Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Machine Seated Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Machine Seated Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Machine Shoulder (Military) Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Machine Side Lateral Raises",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Machine Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "March or jog in place", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Marching High Knees", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Medicine ball booklet crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Medicine Ball Chest Pass", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Medicine Ball Full Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Medicine Ball Scoop Throw",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Medicine Ball Slam", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Medicine Ball Throw Squat with Wall",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Medicine ball twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Middle Back Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Middle Back Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Mixed Grip Chin", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Monster Walk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Mountain Climbers", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Moving Claw Series", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Muscle Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Muscle-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Muscleup on Vertical Bar",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Narrow Stance Hack Squats",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Narrow Stance Leg Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Narrow Stance Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Natural Glute Ham Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Neck CARs", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Neck half circles", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Neck Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Neck-SMR", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(name = "Negative Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Neutral-grip pull-ups or TRX rows",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "No push-up burpees", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Nordic Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
    )

    private fun chunk8(): List<Exercise> = listOf(
        Exercise(
            name = "Nordic Hamstring Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Oblique Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Oblique Crunches Floor",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Olympic Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "On Your Side Quad Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "On-Your-Back Quad Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "One Arm Against Wall", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "One Arm Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "One Arm Dumbbell Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "One Arm Dumbbell Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "One Arm Floor Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "One Arm Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "One Arm Pronated Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "One Arm Supinated Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "One Half Locust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "One Handed Hang", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "One Knee To Chest", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "One Leg Barbell Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "One-Arm Flat Bench Dumbbell Flye",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "One-Arm High-Pulley Cable Side Bends",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "One-Arm Incline Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Clean and Jerk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Floor Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Jerk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Military Press To The Side",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Para Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Snatch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Split Jerk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Split Snatch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Kettlebell Swings",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "One-Arm Long Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "One-Arm Medicine Ball Slam",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "One-Arm Open Palm Kettlebell Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "One-Arm Overhead Kettlebell Squats",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "One-Arm Side Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "One-Arm Side Laterals",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "One-Legged Cable Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Open Book", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Open Palm Kettlebell Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Otis-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Overhead Cable Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Overhead Lat", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Overhead Slam", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Overhead Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Overhead Stretch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Overhead Triceps", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Pallof Press", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Pallof Press With Rotation",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Palms-Down Dumbbell Wrist Curl Over A Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Palms-Down Wrist Curl Over A Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Palms-Up Barbell Wrist Curl Over A Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Palms-Up Dumbbell Wrist Curl Over A Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Parallel Bar Hold", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pause Hack Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pec Deck", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Pec deck rear delt fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Pelvic Endurance Hold", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Pelvic Tilt Into Bridge", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Pelvic Tilts", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Pelvic Wave", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Pendular hack", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pendulum Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Peroneals Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Peroneals-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Physioball Hip Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pigeon Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Pike Push Ups", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Pin Presses", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Piriformis-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pistol Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Pistol squats right", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Planche Lean Hold",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Plank Alternate Knee Tuck",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Plank Jacks", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Plank Reach", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Plank Shoulder Taps", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Plank with Alternating Leg Lift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Plank-to-Elbow Extension",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Plantarflexion Stretch with Band",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Plate Pinch", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Plate Pinch Hold", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Plate Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Plate-Loaded Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Platform Hamstring Slides", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Plie Dumbbell Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Plyo Kettlebell Pushups",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Plyo Push-up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Posterior Pelvic Tilt",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Posterior Tibialis Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Power Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Power Clean from Blocks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Power Jerk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Power Partials", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Power Sled Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CARDIO),
        Exercise(name = "Power Sled Push", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Power Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Power Snatch from Blocks",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Power Stairs", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Preacher Hammer Dumbbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Press Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(name = "Press Under", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Prisoner Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Prone Manual Hamstring", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Prone Scapular Retraction - Arms at Side",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Prowler Sprint", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Pseudo Planche Push-up",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Pull Through", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Pull Ups on Machine", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Pull-Up (negative)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Pull-up Isometric Hold",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Pull-Ups (Neutral Grip)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Pull-Ups (Wide Grip)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Pullover Machine", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Pullup on fingerboard",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Punches", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Push Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Push Press - Behind the Neck",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Push Up to Side Plank",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Push-up rotations", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Push-Up Wide", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Push-Ups - Close Triceps Position",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Push-Ups With Feet Elevated",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Push-Ups With Feet On An Exercise Ball",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Push-Ups | Parallettes", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Pushup on Knees", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Pushups (Close and Wide Hand Positions)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Pyramid", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Quad Sets", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Quad Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Quadriceps-SMR", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Quadriped Arm and Leg Raise",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Quadruped Hip Abduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Quadruped thoracic rotation left",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Quadruped thoracic rotation right",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Quick Kegels", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Quick Leap", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Rack Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Rack Delivery", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Rack Pull with Bands", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Rack Pulls", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Radio Taiso", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Reach ups", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Rear Decline Bridge", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Rear Leg Raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Rear-foot-elevated Hip Flexor Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Recovery Bobbing", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Recruitment Pulls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Recumbent Bike", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Relaxed Diaphragmatic Breathing",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Resistance Band Hammer Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Resistance Band Lateral Walk",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Resistance Band Side Plank Clamshell",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Rest (for timed workouts)", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Retro Treadmill Walking", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Return Push from Stance",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Reverse Band Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Reverse Band Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Reverse Band Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Reverse Band Power Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Reverse Band Sumo Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Reverse Barbell Preacher Curls",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Reverse Cable Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Reverse Clamshell", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Reverse Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
    )

    private fun chunk9(): List<Exercise> = listOf(
        Exercise(name = "Reverse Flyes", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Reverse Flyes With External Rotation",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Reverse Grip Bent-Over Rows",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Reverse Grip Triceps Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Reverse Hyperextension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Reverse lunges", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Reverse Machine Flyes",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Reverse Nordic Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Reverse Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Reverse Plate Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Reverse Snow Angel", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Reverse Triceps Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Reverse V-Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Reverse Wood Chops", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Reverse-grip pull-ups",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Rhomboids-SMR", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Rickshaw Carry", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Rickshaw Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Right levator scapulae stretch",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Right neck stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Ring Dips", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Ring Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Support Hold", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Rocket Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Rocking Standing Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Rocky Pull-Ups/Pulldowns", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Roll Down", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Roll-overs into V-sits", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Roman Chair Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Romanian Deadlift from Deficit",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Rope Climb", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Rope Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Rope Jumping", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Rope Straight-Arm Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Rotary Torso Machine", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Round The World Shoulder Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Rowing Machine", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Rowing seated, narrow grip",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Rowing Straight Back with Rowing Machine",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(
            name = "Rowing with Rowing Machine",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Rowing with TRX band", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Rowing, Lying on Bench", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Rowing, Stationary", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Rowing, T-bar", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Rubber Band Glute Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Run", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Run - Interval Training", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Run - Treadmill", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Runner's Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Runners Lunge Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Running", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Running, Treadmill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Russian Push Ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Russian Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Russian Twist Beginner Level",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Sandbag Load", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Scapula Pulls", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Scapular Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Schwimmen", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Scissor Kick", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Scissors", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Scissors Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Scorpion Kick", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Seated Band Hamstring Curl",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Seated Band Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(
            name = "Seated Barbell Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Seated Barbell Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(name = "Seated Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Seated Bent-Over One-Arm Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Bent-Over Rear Delt Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Bent-Over Two-Arm Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Seated Biceps", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Seated Cable Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Seated Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Seated Calf Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Seated Close-Grip Concentration Barbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Seated Corkscrew", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Seated Dumbbell Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Seated Dumbbell Inner Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Dumbbell Palms-Down Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Seated Figure Four", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Seated Flat Bench Leg Pull-In",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Seated Floor Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Seated Front Deltoid",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Seated Glute", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Seated Good Mornings", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Seated Hamstring", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Seated Hamstring and Calf Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Seated Head Harness Neck Resistance",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Seated Hip Abduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Seated Hip Adduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Seated Knee Extension with Overpressure",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Seated Knee Tuck", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Seated Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Seated Leg Tucks", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Seated One-arm Cable Pulley Rows",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Seated One-Arm Dumbbell Palms-Down Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated One-Arm Dumbbell Palms-Up Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Seated Overhead Stretch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Seated Palm-Up Barbell Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Seated Palms-Down Barbell Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Seated Pancake Good Morning",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Seated Piriformis Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Seated Row (Machine)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Seated Shoulder Extension Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Seated Side Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Single Leg Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Seated Triceps Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Seated Two-Arm Palms-Up Low-Pulley Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "See-Saw Press (Alternating Side Press)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Serratus Wall Slide Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Shinbox IR Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Shotgun Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Shoulder Circles", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Shoulder dislocates", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Shoulder Grip Pull-Up",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Shoulder Press - With Bands",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Shoulder Press, on Machine",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Shoulder Press, on Multi Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Shoulder Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Shoulder Shrug", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Shoulder Stretch", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Shoulder Tap Pushup", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Shoulder width three-point push-up",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Shrimp Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Shrugs on Multipress", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side Bridge", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side Hop-Sprint", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Side Jackknife", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Side Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Side Laterals to Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Side Leg Raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side Lying Groin Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Side Lying Hip Abduction",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Side Neck Stretch", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.OTHER),
        Exercise(name = "Side Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side plank right", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side Slides + Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Side split squats left",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Side split squats right",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Side Standing Long Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Side Step Deep Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Side to Side Box Shuffle", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Side To Side Chins", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Side to Side Push Ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Side Wrist Pull", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Side-Kick Breathing (Kickboard)",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(
            name = "Side-lying External Rotation",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Side-Lying Floor Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Sideway Leg Lift with a Chair",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Single Arm Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Single Dumbbell Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Single Leg Bridge Without Stretched Leg Left",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Single Leg Butt Kick", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Single Leg Calf Raise off Step",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Single Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Single Leg Glute Bridge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single Leg Hamstring Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Single leg press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Single Leg Push-off", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Single Leg Squat Pistol",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single Leg Squat with Support",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single Leg Squat with Support Pistol",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Single Leg Thrusts", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Single Straight Leg Glute Bridge Hold Left",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single-Arm Cable Crossover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Single-Arm Linear Jammer",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Single-Cone Sprint Drill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Single-Leg Balance with Eyes Closed",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single-leg BOSU balance",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single-leg hamstring curl",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
    )

    private fun chunk10(): List<Exercise> = listOf(
        Exercise(name = "Single-Leg High Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Single-Leg Hop Progression",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Single-Leg Lateral Hop", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Single-leg side glute press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single-Leg Soft-Landing Holds",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Single-Leg Stride Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sit & Reach", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Sit (wall)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sit Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sit Up Elbow Thrust", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Sit-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Sit-ups", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Sitting Calf Raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Sitting Calf Stretch (Dorsiflexion)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Skating", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Ski Ergometer", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Ski Machine", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Ski Runners", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Skipping - Standard", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Skydiver with arms in T-position",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Sled 45 Calf Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Sled 45 Degrees Leg Press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Sled 45 Leg Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Sled Calf Press on Leg Press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Sled Drag - Harness", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sled Hack Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Sled Overhead Backward Walk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Sled Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Sled Push", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sled Reverse Flye", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Sled Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Sledgehammer Swings", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Sleeper Stretch", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Sloper hanging", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Slow Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Smith Back Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(
            name = "Smith Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Bent Over Pronated Grip Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Smith Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(name = "Smith Chair Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(
            name = "Smith Decline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Smith Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(
            name = "Smith Incline Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Incline Shoulder Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Smith Machine Behind the Back Shrug",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Bent Over Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Close-Grip Bench Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Decline Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Hang Power Clean",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Smith Machine Hip Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(name = "Smith Machine Leg Press", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Smith Machine One-Arm Upright Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Overhead Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Pistol Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Reverse Calf Raises",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Slight Incline Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Smith Machine Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Smith Machine Stiff-Legged Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Smith Machine Upright Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Smith Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Smith Single-Leg Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "SMR Glutes (lax ball)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Snap Down", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Snatch Balance", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Snatch Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Snatch from Blocks", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Snatch Pull", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Snatch Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Soleus / Achilles Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Solo Hip Flexor Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Speed Band Overhead Triceps",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Speed Box Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Speed Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Speed Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Spell Caster", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Sphinx", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Spider Crawl", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Spider Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Spider Man Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Spider Plank", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Spinal Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Splinter Sit-ups", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Split Clean", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Split Jerk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Split Snatch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Split Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Split squats left", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Split squats right", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Sprint", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Squat -> Stand Coordination",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Squat Jerk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squat Sky Reach Stretch", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Squat Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Squat with Chains", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squat with Plate Movers", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squats on Multipress", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Stairmaster", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Standing Alternating Arm Lift",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Alternating Arms Swing",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Standing Alternating Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Barbell Press Behind Neck",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Standing Bent-Over One-Arm Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Bent-Over Two-Arm Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Biceps Cable Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Standing Biceps Stretch", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Standing biceps stretch left",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing biceps stretch right",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Bradford Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Standing Cable Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Standing Cable Lift", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Standing Cable Wood Chop", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Standing Calf Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Concentration Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Dumbbell Reverse Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Dumbbell Straight-Arm Front Delt Raise Above Head",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Dumbbell Upright Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Elevated Quad Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Standing Front Barbell Raise Over Head",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Standing Gastrocnemius Calf Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Standing Hamstring and Calf Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Standing Hip Circles", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Standing Hip Flexors", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Standing Inner-Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing IT Band Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Standing Lateral Stretch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Standing Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Standing Long Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Standing Low-Pulley Deltoid Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Standing Low-Pulley One-Arm Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Standing Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Standing Olympic Plate Hand Squeeze",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Standing One-Arm Cable Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Standing One-Arm Dumbbell Curl Over Incline Bench",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing One-Arm Dumbbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Overhead Barbell Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Standing Palm-In One-Arm Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Palms-In Dumbbell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Standing Palms-Up Barbell Behind The Back Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Standing Pancake", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Standing Pancake Good Morning",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Standing Pelvic Tilt", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Standing Plate Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Standing Rope Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Standing Rope Forearm", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Standing Side Crunches",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Single Side Leg Raise Chair Supported",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Soleus And Achilles Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Standing Soleus Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Standing Toe Touches", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Standing Towel Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Standing Two-Arm Overhead Throw",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Star Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Stationary Bike", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Stationary bike cardio", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Stationary Bike Run", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Step Calf Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Step Jack", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Step Mill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Step Side Jump Over Stepbox",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Step-Up on Box", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Step-up with Knee Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Step-ups", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Sternum Pull-Up (gironda)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Stiff Leg Barbell Good Morning",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Stiff-Legged Dumbbell Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Stomach Vacuum", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Straddle L-Sit", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
    )

    private fun chunk11(): List<Exercise> = listOf(
        Exercise(
            name = "Straight Bar Bench Mid Rows",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Straight Raises on Incline Bench",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Straight-Arm Dumbbell Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Stride Jump Crossover", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Stroke-and-Roll Drill", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Sumo Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Sumo Deadlift High Pull", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Sumo Deadlift with Bands",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Sumo Deadlift with Chains",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Sumo Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Superman", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Superman Hold", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Supine Chest Throw", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Supine One-Arm Overhead Throw",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(
            name = "Supine Two-Arm Overhead Throw",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Supino inclinado", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Supported Calf Stretch",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Supported Deep Squat Breathing",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Supported Split Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Suspended crossess", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Suspended Fallout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Suspended Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Suspended Reverse Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Suspended Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Suspended Split Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Suspender Arm Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Suspender Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Suspender Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Suspender Hip Lift Knee Tuck",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Suspender Jack Knife", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Suspender Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Suspender Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Suspender Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Suspension Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Suspension Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Svend Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.OTHER),
        Exercise(name = "Swimming 50m sprints", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Swimming Crawl Style", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "T-Bar Row with Handle", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Talons fesses", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Tate Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "The Straddle", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Thigh Abductor", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Thigh Adductor", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Tibialis raises", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Tire Flip", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Toe Taps", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Toe Touch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Toe Touchers", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Torso Rotation", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Torso rotation stretch",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Torso Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Towel Superman", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Trail Running/Walking", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Trap Bar Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Trap Bar Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Trap-3 Raise", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Treadmill Cardio", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Tricep Dumbbell Kickback",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Tricep Side Stretch", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Triceps Dip", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Triceps Dip Floor", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Triceps Dips (Assisted)", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Triceps on Machine", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Triceps Overhead Extension with Rope",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Triceps Pushdown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Triceps Pushdown - Rope Attachment",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Triceps Pushdown - V-Bar Attachment",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Triceps Stretch", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Triceps stretch left", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Triceps stretch right",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "TRX dips", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "TRX gorilla biceps curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "TRX hammer curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "TRX Obliques", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "TRX roll out", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "TRX Rows", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Trx Single Arm Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "TRX Tricep Extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Tuck Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Tuck L-sit", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Tuck planche", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Two-Arm Dumbbell Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Two-Arm Kettlebell Clean",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Two-Arm Kettlebell Jerk",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Two-Arm Kettlebell Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Two-Arm Kettlebell Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Typewriter Pull-ups", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Tyson push-ups", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Underhand Cable Pulldowns", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Unilateral Hip Thrust",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Unilateral Lunges", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Upper Back", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Upper Back Stretch", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Upper Back-Leg Grab", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Upper External Oblique",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Upright Barbell Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Upright Row - With Bands", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(
            name = "Upright Row, on Multi Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Upward Stretch", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "V-Bar Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "V-Bar Pullup", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "V-Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Vertical Leg Raise on Parallel Bars",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Vertical Swing", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Vpushup", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "W-Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Walking", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Walking Bridge", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Walking Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Walking on Stepmill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Walking, Treadmill", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(name = "Wall Angels", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.OTHER),
        Exercise(name = "Wall Baby Breathing", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Wall balls", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Wall Calf Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Wall Drills", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall Handstand", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall Pushup", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall Slides", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall-Hold Rotation", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Weighted Ball Hyperextension",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Weighted Ball Side Bend", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(name = "Weighted Bench Dip", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(name = "Weighted Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Weighted Close Grip Chinup on Dip Cage",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Crunches", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Weighted Decline Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Frog Pump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Weighted Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Hyperextension",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Jump Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Weighted Lying Neck Extension",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Lying Neck Flexion",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted neck flexion",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Plate LU Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Plate Lying Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Plate Standing Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Pull Ups", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Weighted push-ups", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Weighted Russian Twist",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Sandbag Walking Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Sissy Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Weighted Sit-Ups - With Bands",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Weighted Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Weighted Step-ups", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Weighted Svend Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Weighted Tricep Dips", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wheel Rollout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Wide Stance Barbell Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Wide Stance Stiff Legs", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Wide-Grip Barbell Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Wide-Grip Decline Barbell Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Wide-Grip Decline Barbell Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Wide-Grip Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Wide-Grip Pulldown Behind The Neck",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Wide-Grip Rear Pull-Up",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Wide-Grip Standing Barbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Wind Sprints", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Windmills", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Windshield Wipers", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "World's Greatest Stretch", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "Wrist Circles", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wrist Roller", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.OTHER),
        Exercise(
            name = "Wrist Rotations with Straight Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Yoga exercise: Cow-cat",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Yoke Walk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.OTHER),
        Exercise(name = "YTW Raises", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "YWTs", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.OTHER),
        Exercise(name = "Zercher Squats", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Zone 2 Running", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Zottman Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Zottman Preacher Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
    )

    private fun chunk12(): List<Exercise> = listOf(
        Exercise(
            name = "1/2 Kneeling Adductor Rock Back",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "100m Row", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "100m Sprint", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "10km Row", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "1km Row", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "200m Sprint", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "2km Row", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "400m Sprint", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "45 Degree Hyperextension Arms in Front of Chest",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "45 Degrees Reverse Hyperextension",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "500m Row", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "90/90 Hip Flow", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "A Skip", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Abs Crunch (Weighted)", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Alternating Dumbbell Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Alternating Lateral Ball Slams",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Alternating Lunge with Rotation",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Arnold Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Back Extension", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Back Extension (Weighted)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Band Alternate Low Chest Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Assisted Chin-Up From Knee",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Assisted Pull-Up Version 3",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Bent Over Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Bent Over Rear Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Clamshells", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Cross Body One Arm Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Face Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Glute Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Hip Abduction", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Incline Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Kneeling One Arm Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(
            name = "Band Lateral Raise Version 2",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BAND
        ),
        Exercise(
            name = "Band Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BAND
        ),
        Exercise(name = "Band Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Reverse Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BAND),
        Exercise(name = "Band Seated Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BAND),
        Exercise(name = "Band Standard Biceps Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BAND),
        Exercise(name = "Band Standing Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BAND),
        Exercise(
            name = "Barbell Banded Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Behind the Back Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Behind-the-Neck Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Block Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Bulgarian Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Chest Supported Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Clean and Jerk",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Clean Grip Front Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Cossack Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Curtsey Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Floor Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell High Bar Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Incline Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Incline Shoulders Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Jefferson Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Lateral Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Legs Up Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell One Leg Hip Thrust",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Overhead Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Pause Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Pause Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Pin Front Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Pin Press Bench",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Push Press", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Reeves Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Reverse Grip Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Reverse Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Seated Behind Head Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Seated Military Press Inside Squat Cage",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Seated Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Seated Pin Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Skull Crusher", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Sling Shot Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Split Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Standing Behind Neck Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Standing Shoulders Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Step-Up", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Sumo Romanian Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Tempo Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Barbell Weighted Chains Bench Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Wide Grip Upright Row",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Wrist Curl Version 2",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Barbell Wrist Reverse Curl Version 2",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Barbell Z Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Barbell Zercher Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Batwing Row", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Bayesian Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Bear Craw Isometric Hold",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Behind-the-Neck Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Bench Press (Close Grip)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Bench Press (Paused)", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Bench Press (Wide Grip)",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Bench T-Spine Stretch",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bent Leg Raise", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bent Over Row (Barbell, Paused)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Bent-over Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bilateral Snapdown", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Blank Bodyweight Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Abs Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Bulgarian Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Cossack Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Decline Sit Up",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Dip", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Eccentric Reverse Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Glute Bridge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Good Morning",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Hip Thrust",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Bodyweight Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Bodyweight Pull-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bodyweight Reverse Abs Crunch",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Single Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Skull Crusher",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Bodyweight Tricep Dip",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Broad Jump", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Bulgarian Split Squat Smith Machine",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Butt Kicks", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Cable Behind The Back Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Bent Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Biceps Curl Sz Bar", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Chest Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Close Grip Front Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Cross Over Lateral Pulldown",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Cross Over Revers Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Decline Seated Wide Grip Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Flexion Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Front Raise Rope Attachment",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Front Seated Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Front Squat with V Bar",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Glute Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Goblet Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Half Kneeling Single Arm Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Half Kneeling Single Arm Row Version 2",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable High Row with Chest Support",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Hip Abduction Version 2",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Incline Biceps Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Incline Inner Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Incline Single Arm Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Kickback", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Kneeling One Arm Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Kneeling Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lat Pulldown Full Range of Motion",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lateral Pulldown with Rope Attachment",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Lateral Pulldown with V Bar",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Low Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Lying Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Lying Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Lying Pullover", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable One Arm Bent Over Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable One Arm Side Triceps Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable One Arm Tricep Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
    )

    private fun chunk13(): List<Exercise> = listOf(
        Exercise(
            name = "Cable Overhead Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Overhead Tricep Extension Straight Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Parallel Grip Lat Pulldown on Floor",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Partial Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Pull Apart", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Pulldown Pro Lat Bar",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Reverse Grip Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Seated Overhead Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Seated Overhead Curl Sz Bar",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Shoulder Internal Rotation",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Side Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Single Arm High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Single Arm Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Standing Crossover", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Standing Crunch with Rope Attachment",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Standing Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Standing Pullover", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Cable Standing Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Standing Shoulder External Rotation",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Straight Back Seated Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Tricep Extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable Twin Handle Parallel Grip Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Cable Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Cable V-Handle Tricep Pushdown",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(
            name = "Cable Wide Grip Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Calf Raise (Leg Press)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Cambered Bar Lying Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Cambered Bar Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Cannonball Crunch", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Cardio", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Cardio (LISS)", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Cardio (Zone 2)", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Cat Cow and T-Spine Rotation",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Chest Expander Pull-Apart (Horizontal)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Chest Expander Pull-Apart (Vertical)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Chest Supported Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Chin Ups Narrow Parallel Grip",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.OTHER
        ),
        Exercise(name = "Conditioning Circuit", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Copenhagen Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Couch Stretch with Reachovers",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Deadlift (Paused)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Decline Sit Up (Weighted)",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Deficit Handstand Push Up",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Dip (Assisted)", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.MACHINE),
        Exercise(name = "Dip (Weighted)", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dips Between Chairs", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Double Leg Multi-Directional Pogo",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Downward Dog", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Downward Dog to Lunge",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Dragon Fly", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Dumbbell 21s", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Alternate Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell B-Stance Romanian Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Barrel Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Bench Pullover",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent Over Row Single Arm on Bench",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Bent Over Y Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Chest Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Chest Supported Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Cossack Squats Version 2",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Decline Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Decline One Arm Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Decline Twist Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Face Down Lying Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Floor Chest Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Fly on Exercise Ball",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Fly Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Glute Bridge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Gobelt Curtsey Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Goblet Walking Lunge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Half Kneeling Arnold Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Chest Fly",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Fly Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Inner Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline One Arm Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Shoulders Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Incline Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Isometric Row Hold",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell JM Press", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Lateral Step-Up",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Leg Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Lying Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying Hammer Press Version 2",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Lying on Floor Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One Arm Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell One Arm Wrist Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Overhead Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Overhead Tricep Extension",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Partial Incline Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Partial Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Pec Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Plyo Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Pullover Version 2",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Rear Fly", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Reverse Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Reverse Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Rolling Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Romanian Deadlift to Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Russian Twist with Legs Floor off",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Scott Press Version 2",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Alternate Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Revers Grip Concentration Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Seated Shoulder Press Parallel Grip",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Arm Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Arm Rear Delt Fly",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Arm Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Leg Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Single Leg Glute Bridge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Skull Crusher", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dumbbell Squat to Press",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Behind Back Shoulders Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Bent Arm Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Bent Arm Lateral Raise Male Sh",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Close Grip Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Hammer Curl to Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Kickback",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Shoulder Press to Pec Dec",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Single Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Standing Single Leg Calf Raise Female C",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Stiff Leg Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Dumbbell Wrist Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Dumbbell Y Raise", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Dumbbell Z Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Dynamic Hamstring Sweeps",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Eccentric Push Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Elevated Pigeon Stretch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "EZ Bar 21s", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "EZ Bar Overhead Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "EZ Bar Pullover", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "EZ Bar Reverse Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Bar Seated Close Grip Concentration Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Bar Seated Close Grip Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "EZ Bar Seated Overhead Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Ez Barbell Close Grip Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Close Grip Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Lying Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Reverse Grip Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Reverse Grip Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Ez Barbell Seated Curls",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BARBELL
        ),
    )

    private fun chunk14(): List<Exercise> = listOf(
        Exercise(name = "Ez Barbell Spider Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Ez Barbell Standing Preacher Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Face Away Cable Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Farmer's Walk (Weighted)",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Flexion Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.CABLE),
        Exercise(name = "Forward Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "French Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.CABLE),
        Exercise(name = "Front Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Front Squat (Paused)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Glute Bridge Hold", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Glute Bridge Walkout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Glute Med Kickback", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Goblet Duck Walk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.KETTLEBELL),
        Exercise(name = "Goblet March", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.KETTLEBELL),
        Exercise(name = "Halting Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Hammer Curl (Rope)", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Hammer Grip Pull-Up on Dip Cage",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hamstring Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Hamstring Stretch with Rotation",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Hands on Hips Countermovement Jump",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hang Power Clean", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Hanging Oblique Knee Raise",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Hanging Toes To Bar", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Hatfield Squat (Safety Squat Bar)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Hip Bridge March", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Hip Extension", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Hip Openers", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "I, Y, T (IYT) Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Inch Worm + Push Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Inchworm Walkout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Incline Twisting Sit-Up Version 2",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Inverted Face Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Jammer Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Jog", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Jump Switch Lunge", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Katana Extension", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(
            name = "Kelso Shrug (Chest Supported)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Kettlebell Bent Over Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Clean", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Clean and Press",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Front Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Half Kneeling Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Incline Hammer Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Kettlebell Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.KETTLEBELL),
        Exercise(
            name = "Kettlebell Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Russian Twist",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Seated Neutral Grip Shoulders Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Seated Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Single Arm Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Kettlebell Standing Bottoms up One Arm Shoulder Pr",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(
            name = "Knee Raise (Captain's Chair)",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Landmine Clean And Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Landmine Kneeling One Arm Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Landmine Oblique Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(name = "Landmine Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Landmine Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Landmine Seated Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Landmine Shoulder to Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Landmine Shoulders Press with Viking Press Attachm",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Landmine Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BARBELL),
        Exercise(name = "Lat Prayer", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lat Pulldown (Close Grip)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lat Pulldown (Neutral Grip)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lat Pulldown (Single Arm)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lateral Banded Walk", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lateral Bear Crawl", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lateral Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Lateral Lunge", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Lateral Lunge (Weighted)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Lateral Med Ball Slam",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Lateral Med Ball Throw",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Lateral Shuffle", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Leg Extension (Banded)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CABLE),
        Exercise(name = "Leg Press (45 Degrees)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Leg Raise (Captain's Chair)",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Lever Angled Leg Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Assisted Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Assisted Standing Chin-Up",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Bent Over Low Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Bent Over Neutral Grip Row with Chest Support",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Chest Press Version 3",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Chest Press Version 4",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Chest Press Version 5",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Cross Lat Pulldown Version 2",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever High Row Plate Loaded Version 2",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever High Row Plate Loaded Version 3",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Hip Thrust Plate Loaded",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Incline Hammer Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Pulldown Plate Loaded",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Raise Plate Loaded",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Raise Version 2",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Raise with Chest Support",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lateral Wide Pulldown",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Lying Chest Press Plate Loaded",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever One Arm Lateral High Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever One Arm Lateral Wide Pulldown Plate Loaded",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever One Arm Shoulder Press Plate Loaded",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Overhead Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Parallel Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Pendulum Squat Plate Loaded Female Thigh",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Preacher Curl Plate Loaded",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Pronated Grip Seated Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Reverse Grip High Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Reverse Grip Lateral Pulldown Plate Loaded",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Reverse Seated Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Reverse T Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Reverse Vertical Hack Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Row Plate Loaded", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Fly Version 2",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Hammer Grip Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Row Plate Loaded",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Row Version 2",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Single Arm Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Seated Single Arm Wide Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Lever Seated Twist", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Lever Seated Wide Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Shoulder Press Plate Loaded Version 2",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Shoulder Press Plate Loaded Version 3",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Single Arm Shoulder Press Plate Loaded",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Squat Plate Loaded Version 2",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Calf Raise Calf",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Lever Standing Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Library Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Light Jog", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Log Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Long Pause Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Lu Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Lying Bicep Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Lying Chest Pass Med Ball Throw",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Lying Rear Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Lying Reverse Fly", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Lying Side Lateral Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Machine Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Machine Chest Supported Row",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Machine Hip Adductor", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Machine Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Machine Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Machine Rear Delt Fly",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Machine Seated Dip", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Machine Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Marching Glute Bridge",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Meadows Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Med Ball Slam", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Modified Dragon Fly", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Modified Jumping Jack",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Monastery Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.CABLE
        ),
        Exercise(name = "Narrow Push Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Neck Curl", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Neck Extension", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Neck Flexion", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "One Arm Bent Over Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
    )

    private fun chunk15(): List<Exercise> = listOf(
        Exercise(
            name = "One Arm Overhead Carry with March",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Partial Calf Raise", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Partial Chin-Up", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Pigeon Stretch with Thoracic Rotation",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Pike Push-Up on Bench Version 2",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Pinwheel Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Plank (Weighted)", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.MACHINE),
        Exercise(name = "Plank on Swissball", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Plank to Push Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Plank Walkout", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Plate Hop", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Platz Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Pogo Hop", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Powell Raise", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Power Shrug", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Prisoner Good Morning",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Prowler Push", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Pull-Up (Neutral Grip, Bodyweight)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Pull-Up (Neutral Grip, Weighted)",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Punching Bag", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(name = "Reaching Situp", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Rear Delt Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Rear Foot Elevated Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Renegade Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Resistance Band Horizontal Pallof Press",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Resistance Band Horizontal Pallof Press Female W",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Resistance Band Kneeling Cross Body Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Resistance Band Kneeling Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Resistance Band Standing Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Reverse Grip Machine Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Reverse Pec Deck", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Ring Archer Push-Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Arm Curl", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Face Pull", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring High Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Push Up", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Ring Row (Weighted)", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Ring Russian Push-Up",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Ring Triceps Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Roman Chair Sit Up", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Rope Pressdown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.CABLE),
        Exercise(name = "Rotating Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Rotational Landmine Push Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Row Between Chairs", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Run Strides", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Runners Lunge Thoracic Extension",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Safety Bar Front Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Safety Bar Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Scapular Push Up",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Seal Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Seated Anterior Delt Press (AD)",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Seated Front Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Seated Hamstring Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Self Assisted Inverted Pullover",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Shadow Boxing", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Shadow Kick Boxing", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Shoulder Press (Plate Loaded)",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Shoulder YTWL", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Side Leg Swings", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Side Plank with Reach Through",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single Arm Farmer Carry",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Single Arm Floor Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Single Arm Iso Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Single Arm Kettlebell Swing",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.KETTLEBELL
        ),
        Exercise(name = "Single Arm Landmine Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Single Arm Overhead Tricep Extension",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Single Arm Pushdown", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.MACHINE),
        Exercise(
            name = "Single Arm Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(
            name = "Single Arm Side Straight Arm Lat Pulldown",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Single Leg Calf Raise (Weighted)",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(name = "Single Leg Deadlift", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Single Leg Hip Thrust", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Single Leg Plate Hop", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Single Leg Romanian Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Single-Leg Leg Curl", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.MACHINE),
        Exercise(name = "Sissy Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Skater Hop", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "SkiErg", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Sled 45 Degrees One Leg Press",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Sled Full Hack Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.CARDIO),
        Exercise(
            name = "Smith Calf Raise Version 2",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Front Squat Clean Grip",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Smith Full Squat", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(
            name = "Smith Lateral Step-Up",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine AD Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Behind-The-Neck Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Bicep Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine JM Press",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Larsen Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Seated Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Machine Split Squat Front Foot Elevated",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Seated Shoulder Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Smith Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.SMITH_MACHINE),
        Exercise(
            name = "Smith Standing Military Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Smith Sumo Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(name = "Split Squat Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Spoto Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squat (Low Bar)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squat (Paused)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(name = "Squat Thruster", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Stair Climber",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.SMITH_MACHINE
        ),
        Exercise(
            name = "Standing March (Bands)",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Stationary Bike Run Version 4",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.CARDIO
        ),
        Exercise(name = "Stiff Leg Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Stiff Leg Deficit Deadlift",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Straight Bar Lat Extension",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.MACHINE
        ),
        Exercise(
            name = "Straight Leg Calf Raise",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.DUMBBELL
        ),
        Exercise(name = "Stretching", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Suitcase Carry", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Suitcase Carry March", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Suitcase Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Sumo Deadlift (Paused)", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Supine Spinal Twist Stretch",
            muscleGroup = MuscleGroup.OTHER,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Suspender Biceps Curl",
            muscleGroup = MuscleGroup.ARMS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Suspender Pike", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Suspender Y Raise",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Suspension Chest Press",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Swim", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "T-Bar Row", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Tempo Bench Press", muscleGroup = MuscleGroup.CHEST, category = ExerciseCategory.BARBELL),
        Exercise(name = "Tempo Deadlift", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Terminal Knee Extension",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Thoracic Extension Stretch",
            muscleGroup = MuscleGroup.FULL_BODY,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Trap Bar B-Stance Romanian Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(
            name = "Trap Bar Overhead Press",
            muscleGroup = MuscleGroup.SHOULDERS,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Trap Bar Power Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(
            name = "Trap Bar Romanian Deadlift",
            muscleGroup = MuscleGroup.BACK,
            category = ExerciseCategory.BARBELL
        ),
        Exercise(name = "Trap Bar Shrug", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Trapbar High Pull", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.BARBELL),
        Exercise(name = "Treadmill", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(name = "Tricep Kickback", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(
            name = "Triceps Dip Bench Leg",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Triceps Dip on High Parallel Bars",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Turkish Get Up", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Underhand Lat Pulldown", muscleGroup = MuscleGroup.BACK, category = ExerciseCategory.MACHINE),
        Exercise(name = "Up Down Plank", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Vertical Jump", muscleGroup = MuscleGroup.LEGS, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Viking Press", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.BARBELL),
        Exercise(name = "VO2 Max Intervals", muscleGroup = MuscleGroup.FULL_BODY, category = ExerciseCategory.CARDIO),
        Exercise(name = "Walk", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.CARDIO),
        Exercise(name = "Walking Calf Raise", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Walking Knee Hugs", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(name = "Wall Press Dead Bug", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Weighted Push-Up with Vest",
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(
            name = "Weighted Russian Twist with Legs Floor off",
            muscleGroup = MuscleGroup.CORE,
            category = ExerciseCategory.BODYWEIGHT
        ),
        Exercise(name = "Wood Chop", muscleGroup = MuscleGroup.CORE, category = ExerciseCategory.CABLE),
        Exercise(name = "Wrist Curls", muscleGroup = MuscleGroup.ARMS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Y Raise", muscleGroup = MuscleGroup.SHOULDERS, category = ExerciseCategory.DUMBBELL),
        Exercise(name = "Yoga", muscleGroup = MuscleGroup.OTHER, category = ExerciseCategory.BODYWEIGHT),
        Exercise(
            name = "Zercher Front-Foot Elevated Split Squat",
            muscleGroup = MuscleGroup.LEGS,
            category = ExerciseCategory.BARBELL
        ),
    )

    private val baseList: List<Exercise> = chunk0() + chunk1() + chunk2() +
        chunk3() + chunk4() + chunk5() + chunk6() +
        chunk7() + chunk8() + chunk9() + chunk10() + chunk11() +
        chunk12() + chunk13() + chunk14() + chunk15()

    val list: List<Exercise> = baseList.map { ex ->
        val involvements = com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry.getInvolvementsForExercise(ex)
        val primary = involvements.firstOrNull { it.isPrimary }?.muscle
        val secondaries = involvements.filter { !it.isPrimary }
        val mechanics = com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier.classifyMechanics(
            ex.name,
            ex.category,
            ex.muscleGroup
        )
        val force = com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier.classifyForce(ex.name, ex.category, ex.muscleGroup)
        val level = com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier.classifyDifficulty(ex.name, ex.category, ex.muscleGroup, mechanics)
        ex.copy(primaryMuscle = primary, secondaryMuscles = secondaries, mechanics = mechanics, force = force, level = level)
    }
}
