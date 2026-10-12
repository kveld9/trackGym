package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

object ExerciseTranslationRegistry {

    data class TranslationEntry(
        val englishName: String,
        @get:StringRes val nameRes: Int,
        val spanishFallback: String
    )

    private val entries = listOf(
        TranslationEntry("Barbell Bench Press", R.string.exercise_barbell_bench_press, "Press de banca con barra"),
        TranslationEntry("Incline Dumbbell Press", R.string.exercise_incline_dumbbell_press, "Press inclinado con mancuernas"),
        TranslationEntry("Parallel Bar Dips", R.string.exercise_parallel_bar_dips, "Fondos en paralelas"),
        TranslationEntry("Cable Crossover", R.string.exercise_cable_crossover, "Cruces en polea"),
        TranslationEntry("Chest Press Machine", R.string.exercise_chest_press_machine, "Press de pecho en máquina"),
        TranslationEntry("Pull-ups", R.string.exercise_pull_ups, "Dominadas"),
        TranslationEntry("Lat Pulldown", R.string.exercise_lat_pulldown, "Jalón al pecho"),
        TranslationEntry("Barbell Bent-Over Row", R.string.exercise_barbell_bent_over_row, "Remo con barra inclinado"),
        TranslationEntry("One-Arm Dumbbell Row", R.string.exercise_one_arm_dumbbell_row, "Remo con mancuerna a una mano"),
        TranslationEntry("Seated Cable Row", R.string.exercise_seated_cable_row, "Remo sentado en polea"),
        TranslationEntry("Conventional Deadlift", R.string.exercise_conventional_deadlift, "Peso muerto convencional"),
        TranslationEntry("Barbell Back Squat", R.string.exercise_barbell_back_squat, "Sentadilla trasera con barra"),
        TranslationEntry("Leg Press 45°", R.string.exercise_leg_press_45, "Prensa de piernas 45°"),
        TranslationEntry("Romanian Deadlift", R.string.exercise_romanian_deadlift, "Peso muerto rumano"),
        TranslationEntry("Leg Extension", R.string.exercise_leg_extension, "Extensión de piernas en máquina"),
        TranslationEntry("Lying Leg Curl", R.string.exercise_lying_leg_curl, "Curl femoral acostado"),
        TranslationEntry("Standing Calf Raise", R.string.exercise_standing_calf_raise, "Elevación de talones de pie"),
        TranslationEntry("Overhead Barbell Press", R.string.exercise_overhead_barbell_press, "Press militar con barra"),
        TranslationEntry("Seated Dumbbell Shoulder Press", R.string.exercise_seated_dumbbell_shoulder_press, "Press de hombros sentado con mancuernas"),
        TranslationEntry("Dumbbell Lateral Raise", R.string.exercise_dumbbell_lateral_raise, "Elevaciones laterales con mancuernas"),
        TranslationEntry("Cable Lateral Raise", R.string.exercise_cable_lateral_raise, "Elevaciones laterales en polea"),
        TranslationEntry("Face Pull", R.string.exercise_face_pull, "Face pull en polea"),
        TranslationEntry("EZ-Bar Bicep Curl", R.string.exercise_ez_bar_bicep_curl, "Curl de bíceps con barra EZ"),
        TranslationEntry("Dumbbell Hammer Curl", R.string.exercise_dumbbell_hammer_curl, "Curl martillo con mancuernas"),
        TranslationEntry("Cable Triceps Pushdown", R.string.exercise_cable_triceps_pushdown, "Extensión de tríceps en polea"),
        TranslationEntry("EZ-Bar Skull Crusher", R.string.exercise_ez_bar_skull_crusher, "Press francés con barra EZ"),
        TranslationEntry("Plank", R.string.exercise_plank, "Plancha abdominal"),
        TranslationEntry("Hanging Leg Raise", R.string.exercise_hanging_leg_raise, "Elevación de piernas colgado"),
        TranslationEntry("Cable Crunch", R.string.exercise_cable_crunch, "Crunch en polea"),
        TranslationEntry(
            "1 Leg Box Squat",
            R.string.exercise_1_leg_box_squat,
            "Sentadilla a una pierna sobre cajón"
        ),
        TranslationEntry(
            "1/2 Kneeling Thoracic Rotation",
            R.string.exercise_1_2_kneeling_thoracic_rotation,
            "Rotación torácica de rodillas"
        ),
        TranslationEntry(
            "3/4 Sit-Up",
            R.string.exercise_3_4_sit_up,
            "Sit-up 3/4"
        ),
        TranslationEntry(
            "4-count Burpees",
            R.string.exercise_4_count_burpees,
            "Burpees en 4 tiempos"
        ),
        TranslationEntry(
            "45 Degree Side Bend",
            R.string.exercise_45_degree_side_bend,
            "Flexión lateral a 45 grados"
        ),
        TranslationEntry(
            "90/90 Breathing",
            R.string.exercise_90_90_breathing,
            "Respiración 90/90"
        ),
        TranslationEntry(
            "90/90 Hamstring",
            R.string.exercise_90_90_hamstring,
            "Estiramiento de isquiotibiales 90/90"
        ),
        TranslationEntry(
            "90/90 Hamstring / Barre-Style Stretch",
            R.string.exercise_90_90_hamstring_barre_style_stretch,
            "Estiramiento de isquiotibiales en barra 90/90"
        ),
        TranslationEntry(
            "90/90 Hip Switches",
            R.string.exercise_90_90_hip_switches,
            "Transiciones de cadera 90/90"
        ),
        TranslationEntry(
            "Ab Crunch Machine",
            R.string.exercise_ab_crunch_machine,
            "Crunch abdominal en máquina"
        ),
        TranslationEntry(
            "Ab wheel",
            R.string.exercise_ab_wheel,
            "Rueda abdominal"
        ),
        TranslationEntry(
            "Abdominal Crunch",
            R.string.exercise_abdominal_crunch,
            "Crunch abdominal"
        ),
        TranslationEntry(
            "Abdominal Draw-In",
            R.string.exercise_abdominal_draw_in,
            "Vacío abdominal"
        ),
        TranslationEntry(
            "Abdominal Stabilization",
            R.string.exercise_abdominal_stabilization,
            "Estabilización abdominal"
        ),
        TranslationEntry(
            "Abduction while standing",
            R.string.exercise_abduction_while_standing,
            "Abducción de pie"
        ),
        TranslationEntry(
            "Adductor",
            R.string.exercise_adductor,
            "Aductor"
        ),
        TranslationEntry(
            "Adductor Rock Backs",
            R.string.exercise_adductor_rock_backs,
            "Balanceo de aductores en cuadrupedia"
        ),
        TranslationEntry(
            "Adductor/Groin",
            R.string.exercise_adductor_groin,
            "Estiramiento de aductores e ingle"
        ),
        TranslationEntry(
            "Advanced Kettlebell Windmill",
            R.string.exercise_advanced_kettlebell_windmill,
            "Molino avanzado con kettlebell"
        ),
        TranslationEntry(
            "Air Bike",
            R.string.exercise_air_bike,
            "Bicicleta de aire"
        ),
        TranslationEntry(
            "All Fours Quad Stretch",
            R.string.exercise_all_fours_quad_stretch,
            "Estiramiento de cuádriceps en cuadrupedia"
        ),
        TranslationEntry(
            "Alternate back lunges",
            R.string.exercise_alternate_back_lunges,
            "Zancadas hacia atrás alternas"
        ),
        TranslationEntry(
            "Alternate Hammer Curl",
            R.string.exercise_alternate_hammer_curl,
            "Curl martillo alterno"
        ),
        TranslationEntry(
            "Alternate Heel Touchers",
            R.string.exercise_alternate_heel_touchers,
            "Toques de talón alternos"
        ),
        TranslationEntry(
            "Alternate Incline Dumbbell Curl",
            R.string.exercise_alternate_incline_dumbbell_curl,
            "Curl inclinado alterno con mancuernas"
        ),
        TranslationEntry(
            "Alternate Leg Diagonal Bound",
            R.string.exercise_alternate_leg_diagonal_bound,
            "Saltos diagonales alternos a una pierna"
        ),
        TranslationEntry(
            "Alternate Walking",
            R.string.exercise_alternate_walking,
            "Caminata alterna"
        ),
        TranslationEntry(
            "Alternating Cable Shoulder Press",
            R.string.exercise_alternating_cable_shoulder_press,
            "Press de hombros alterno en polea"
        ),
        TranslationEntry(
            "Alternating Deltoid Raise",
            R.string.exercise_alternating_deltoid_raise,
            "Elevaciones alternas de deltoides"
        ),
        TranslationEntry(
            "Alternating Floor Press",
            R.string.exercise_alternating_floor_press,
            "Press de suelo alterno"
        ),
        TranslationEntry(
            "Alternating Hang Clean",
            R.string.exercise_alternating_hang_clean,
            "Hang clean alterno"
        ),
        TranslationEntry(
            "Alternating Kettlebell Press",
            R.string.exercise_alternating_kettlebell_press,
            "Press militar alterno con kettlebell"
        ),
        TranslationEntry(
            "Alternating Kettlebell Row",
            R.string.exercise_alternating_kettlebell_row,
            "Remo alterno con kettlebell"
        ),
        TranslationEntry(
            "Alternating Renegade Row",
            R.string.exercise_alternating_renegade_row,
            "Remo renegade alterno"
        ),
        TranslationEntry(
            "Ankle Circles",
            R.string.exercise_ankle_circles,
            "Círculos de tobillo"
        ),
        TranslationEntry(
            "Ankle Dorsiflexion Rocks",
            R.string.exercise_ankle_dorsiflexion_rocks,
            "Movilidad de flexión dorsal de tobillo"
        ),
        TranslationEntry(
            "Ankle On The Knee",
            R.string.exercise_ankle_on_the_knee,
            "Estiramiento de tobillo sobre rodilla"
        ),
        TranslationEntry(
            "Ankle Roll",
            R.string.exercise_ankle_roll,
            "Rotación de tobillos"
        ),
        TranslationEntry(
            "Anterior Tibialis-SMR",
            R.string.exercise_anterior_tibialis_smr,
            "SMR de tibial anterior"
        ),
        TranslationEntry(
            "Anti-Gravity Press",
            R.string.exercise_anti_gravity_press,
            "Press antigravedad"
        ),
        TranslationEntry(
            "Arabesque",
            R.string.exercise_arabesque,
            "Arabesque"
        ),
        TranslationEntry(
            "Arch Hang",
            R.string.exercise_arch_hang,
            "Colgado en arco"
        ),
        TranslationEntry(
            "Archer Pull Up",
            R.string.exercise_archer_pull_up,
            "Dominadas arquero"
        ),
        TranslationEntry(
            "Arm and neck stretch",
            R.string.exercise_arm_and_neck_stretch,
            "Estiramiento de brazos y cuello"
        ),
        TranslationEntry(
            "Arm Circles",
            R.string.exercise_arm_circles,
            "Círculos con los brazos"
        ),
        TranslationEntry(
            "Arm Raises (T/Y/I)",
            R.string.exercise_arm_raises_t_y_i,
            "Elevaciones de brazos (T/Y/I)"
        ),
        TranslationEntry(
            "Arnold Dumbbell Press",
            R.string.exercise_arnold_dumbbell_press,
            "Press Arnold con mancuernas"
        ),
        TranslationEntry(
            "Around The Worlds",
            R.string.exercise_around_the_worlds,
            "Around the worlds"
        ),
        TranslationEntry(
            "Assault Bike Run",
            R.string.exercise_assault_bike_run,
            "Carrera en Assault Bike"
        ),
        TranslationEntry(
            "Assisted Chest Dip Kneeling",
            R.string.exercise_assisted_chest_dip_kneeling,
            "Fondos de pecho asistidos de rodillas"
        ),
        TranslationEntry(
            "Assisted chin-ups",
            R.string.exercise_assisted_chin_ups,
            "Dominadas supinas asistidas"
        ),
        TranslationEntry(
            "Assisted Close Grip Underhand Chin-Up",
            R.string.exercise_assisted_close_grip_underhand_chin_up,
            "Dominadas supinas asistidas agarre cerrado"
        ),
        TranslationEntry(
            "Assisted Pull-Up",
            R.string.exercise_assisted_pull_up,
            "Dominadas asistidas"
        ),
        TranslationEntry(
            "Assisted Triceps Dip Kneeling",
            R.string.exercise_assisted_triceps_dip_kneeling,
            "Fondos de tríceps asistidos de rodillas"
        ),
        TranslationEntry(
            "Assisted Weighted Push-Up",
            R.string.exercise_assisted_weighted_push_up,
            "Flexiones asistidas con lastre"
        ),
        TranslationEntry(
            "Atlas Stone Trainer",
            R.string.exercise_atlas_stone_trainer,
            "Entrenamiento con piedras de Atlas"
        ),
        TranslationEntry(
            "Atlas Stones",
            R.string.exercise_atlas_stones,
            "Piedras de Atlas"
        ),
        TranslationEntry(
            "Australian pull-ups",
            R.string.exercise_australian_pull_ups,
            "Dominadas australianas"
        ),
        TranslationEntry(
            "Axle Deadlift",
            R.string.exercise_axle_deadlift,
            "Peso muerto con barra Axle"
        ),
        TranslationEntry(
            "Back bridge",
            R.string.exercise_back_bridge,
            "Puente de espalda"
        ),
        TranslationEntry(
            "Back Flyes - With Bands",
            R.string.exercise_back_flyes_with_bands,
            "Aperturas posteriores con bandas"
        ),
        TranslationEntry(
            "Back Lever",
            R.string.exercise_back_lever,
            "Back lever"
        ),
        TranslationEntry(
            "Back neck stretch",
            R.string.exercise_back_neck_stretch,
            "Estiramiento posterior de cuello"
        ),
        TranslationEntry(
            "Backward arm circles",
            R.string.exercise_backward_arm_circles,
            "Círculos de brazos hacia atrás"
        ),
        TranslationEntry(
            "Backward Drag",
            R.string.exercise_backward_drag,
            "Arrastre hacia atrás"
        ),
        TranslationEntry(
            "Backward Medicine Ball Throw",
            R.string.exercise_backward_medicine_ball_throw,
            "Lanzamiento hacia atrás con balón medicinal"
        ),
        TranslationEntry(
            "Backward shoulder rotation",
            R.string.exercise_backward_shoulder_rotation,
            "Rotación hacia atrás de hombros"
        ),
        TranslationEntry(
            "Bag training",
            R.string.exercise_bag_training,
            "Entrenamiento con saco de boxeo"
        ),
        TranslationEntry(
            "Balance Board",
            R.string.exercise_balance_board,
            "Tabla de equilibrio"
        ),
        TranslationEntry(
            "Ball crunches",
            R.string.exercise_ball_crunches,
            "Crunches sobre balón suizo"
        ),
        TranslationEntry(
            "Ball Leg Curl",
            R.string.exercise_ball_leg_curl,
            "Curl femoral sobre balón suizo"
        ),
        TranslationEntry(
            "Ball Slams",
            R.string.exercise_ball_slams,
            "Slams con balón medicinal"
        ),
        TranslationEntry(
            "Band Assisted Pull-Up",
            R.string.exercise_band_assisted_pull_up,
            "Dominadas asistidas con banda"
        ),
        TranslationEntry(
            "Band Bent Over Row",
            R.string.exercise_band_bent_over_row,
            "Remo inclinado con banda"
        ),
        TranslationEntry(
            "Band Chest Fly",
            R.string.exercise_band_chest_fly,
            "Aperturas de pecho con banda"
        ),
        TranslationEntry(
            "Band Close Grip Pulldown",
            R.string.exercise_band_close_grip_pulldown,
            "Jalón con banda agarre cerrado"
        ),
        TranslationEntry(
            "Band Deadlift",
            R.string.exercise_band_deadlift,
            "Peso muerto con banda"
        ),
        TranslationEntry(
            "Band Fixed Back Underhand Pulldown",
            R.string.exercise_band_fixed_back_underhand_pulldown,
            "Jalón supino con banda y espalda fija"
        ),
        TranslationEntry(
            "Band Front Raise",
            R.string.exercise_band_front_raise,
            "Elevaciones frontales con banda"
        ),
        TranslationEntry(
            "Band Good Morning",
            R.string.exercise_band_good_morning,
            "Buenos días con banda"
        ),
        TranslationEntry(
            "Band Good Morning (Pull Through)",
            R.string.exercise_band_good_morning_pull_through,
            "Buenos días con banda (Pull Through)"
        ),
        TranslationEntry(
            "Band Hip Adductions",
            R.string.exercise_band_hip_adductions,
            "Aducción de cadera con banda"
        ),
        TranslationEntry(
            "Band Kneeling Lat Pulldown",
            R.string.exercise_band_kneeling_lat_pulldown,
            "Jalón dorsal con banda de rodillas"
        ),
        TranslationEntry(
            "Band Kneeling Pulldown",
            R.string.exercise_band_kneeling_pulldown,
            "Jalón con banda de rodillas"
        ),
        TranslationEntry(
            "Band Middle Fly",
            R.string.exercise_band_middle_fly,
            "Aperturas medias con banda"
        ),
        TranslationEntry(
            "Band Pull Apart",
            R.string.exercise_band_pull_apart,
            "Band pull-apart"
        ),
        TranslationEntry(
            "Band pull-apart with external rotation",
            R.string.exercise_band_pull_apart_with_external_rotation,
            "Band pull-apart con rotación externa"
        ),
        TranslationEntry(
            "Band Skull Crusher",
            R.string.exercise_band_skull_crusher,
            "Press francés con banda"
        ),
        TranslationEntry(
            "Band Squat",
            R.string.exercise_band_squat,
            "Sentadilla con banda"
        ),
        TranslationEntry(
            "Band Standing Chest Press",
            R.string.exercise_band_standing_chest_press,
            "Press de pecho de pie con banda"
        ),
        TranslationEntry(
            "Band Standing Hammer Curl",
            R.string.exercise_band_standing_hammer_curl,
            "Curl martillo de pie con banda"
        ),
        TranslationEntry(
            "Banded Ankle Mobility",
            R.string.exercise_banded_ankle_mobility,
            "Movilidad de tobillo con banda"
        ),
        TranslationEntry(
            "Banded Clamshell",
            R.string.exercise_banded_clamshell,
            "Clamshell con banda"
        ),
        TranslationEntry(
            "Banded Monster Walk",
            R.string.exercise_banded_monster_walk,
            "Paseo de monstruo con banda"
        ),
        TranslationEntry(
            "Banded Scapular Retraction",
            R.string.exercise_banded_scapular_retraction,
            "Retracción escapular con banda"
        ),
        TranslationEntry(
            "Banded Shoulder Drills",
            R.string.exercise_banded_shoulder_drills,
            "Ejercicios de hombros con banda"
        ),
        TranslationEntry(
            "Barbell Ab Rollout",
            R.string.exercise_barbell_ab_rollout,
            "Rueda abdominal con barra"
        ),
        TranslationEntry(
            "Barbell Ab Rollout - On Knees",
            R.string.exercise_barbell_ab_rollout_on_knees,
            "Rueda abdominal con barra de rodillas"
        ),
        TranslationEntry(
            "Barbell Bench Press - Medium Grip",
            R.string.exercise_barbell_bench_press_medium_grip,
            "Press de banca con barra - Agarre medio"
        ),
        TranslationEntry(
            "Barbell Bench Press Isometric",
            R.string.exercise_barbell_bench_press_isometric,
            "Press de banca isométrico con barra"
        ),
        TranslationEntry(
            "Barbell Bench Press Narrow Grip",
            R.string.exercise_barbell_bench_press_narrow_grip,
            "Press de banca con barra agarre cerrado"
        ),
        TranslationEntry(
            "Barbell Bent Over Rowing",
            R.string.exercise_barbell_bent_over_rowing,
            "Remo con barra inclinado"
        ),
        TranslationEntry(
            "Barbell Bent Over Rowing Reverse",
            R.string.exercise_barbell_bent_over_rowing_reverse,
            "Remo con barra inclinado agarre supino"
        ),
        TranslationEntry(
            "Barbell Biceps Curls With Barbell",
            R.string.exercise_barbell_biceps_curls_with_barbell,
            "Curl de bíceps con barra"
        ),
        TranslationEntry(
            "Barbell Box Squat",
            R.string.exercise_barbell_box_squat,
            "Sentadilla en cajón con barra"
        ),
        TranslationEntry(
            "Barbell Clean",
            R.string.exercise_barbell_clean,
            "Cargada con barra"
        ),
        TranslationEntry(
            "Barbell Clean and Jerk OL",
            R.string.exercise_barbell_clean_and_jerk_ol,
            "Cargada y envión con barra"
        ),
        TranslationEntry(
            "Barbell Clean and Press",
            R.string.exercise_barbell_clean_and_press,
            "Clean and press con barra"
        ),
        TranslationEntry(
            "Barbell Clean Pull",
            R.string.exercise_barbell_clean_pull,
            "Tirón de cargada con barra"
        ),
        TranslationEntry(
            "Barbell Curl",
            R.string.exercise_barbell_curl,
            "Curl con barra"
        ),
        TranslationEntry(
            "Barbell Curls Lying Against An Incline",
            R.string.exercise_barbell_curls_lying_against_an_incline,
            "Curl de bíceps con barra prono inclinado"
        ),
        TranslationEntry(
            "Barbell Deadlift",
            R.string.exercise_barbell_deadlift,
            "Peso muerto con barra"
        ),
        TranslationEntry(
            "Barbell Decline Bench Press",
            R.string.exercise_barbell_decline_bench_press,
            "Press de banca declinado con barra"
        ),
        TranslationEntry(
            "Barbell Deficit Deadlift",
            R.string.exercise_barbell_deficit_deadlift,
            "Peso muerto con déficit con barra"
        ),
        TranslationEntry(
            "Barbell Drag Curl",
            R.string.exercise_barbell_drag_curl,
            "Drag curl con barra"
        ),
        TranslationEntry(
            "Barbell External Rotation Stretch",
            R.string.exercise_barbell_external_rotation_stretch,
            "Estiramiento de rotación externa con barra"
        ),
        TranslationEntry(
            "Barbell Floor Chest Press",
            R.string.exercise_barbell_floor_chest_press,
            "Press de pecho en suelo con barra"
        ),
        TranslationEntry(
            "Barbell Front Pull narrow",
            R.string.exercise_barbell_front_pull_narrow,
            "Jalón frontal con barra agarre cerrado"
        ),
        TranslationEntry(
            "Barbell Front pull wide",
            R.string.exercise_barbell_front_pull_wide,
            "Jalón frontal con barra agarre ancho"
        ),
        TranslationEntry(
            "Barbell Front Raise",
            R.string.exercise_barbell_front_raise,
            "Elevaciones frontales con barra"
        ),
        TranslationEntry(
            "Barbell Front Squat",
            R.string.exercise_barbell_front_squat,
            "Sentadilla frontal con barra"
        ),
        TranslationEntry(
            "Barbell Full Clean",
            R.string.exercise_barbell_full_clean,
            "Clean completo con barra"
        ),
        TranslationEntry(
            "Barbell Full Squat",
            R.string.exercise_barbell_full_squat,
            "Sentadilla profunda con barra"
        ),
        TranslationEntry(
            "Barbell Full Zercher Squat",
            R.string.exercise_barbell_full_zercher_squat,
            "Sentadilla Zercher profunda con barra"
        ),
        TranslationEntry(
            "Barbell Glute Bridge",
            R.string.exercise_barbell_glute_bridge,
            "Puente de glúteos con barra"
        ),
        TranslationEntry(
            "Barbell Good Morning",
            R.string.exercise_barbell_good_morning,
            "Buenos días con barra"
        ),
        TranslationEntry(
            "Barbell Guillotine Bench Press",
            R.string.exercise_barbell_guillotine_bench_press,
            "Press guillotina con barra"
        ),
        TranslationEntry(
            "Barbell Hack Squat",
            R.string.exercise_barbell_hack_squat,
            "Sentadilla Hack con barra"
        ),
        TranslationEntry(
            "Barbell Hang Clean",
            R.string.exercise_barbell_hang_clean,
            "Hang clean con barra"
        ),
        TranslationEntry(
            "Barbell Hang Snatch",
            R.string.exercise_barbell_hang_snatch,
            "Hang snatch con barra"
        ),
        TranslationEntry(
            "Barbell Hang Snatch Below the Knees",
            R.string.exercise_barbell_hang_snatch_below_the_knees,
            "Hang snatch bajo rodillas con barra"
        ),
        TranslationEntry(
            "Barbell Hip hinge",
            R.string.exercise_barbell_hip_hinge,
            "Bisagra de cadera con barra"
        ),
        TranslationEntry(
            "Barbell Hip Thrust",
            R.string.exercise_barbell_hip_thrust,
            "Hip thrust con barra"
        ),
        TranslationEntry(
            "Barbell Incline Bench Press",
            R.string.exercise_barbell_incline_bench_press,
            "Press de banca inclinado con barra"
        ),
        TranslationEntry(
            "Barbell Incline Bench Press - Medium Grip",
            R.string.exercise_barbell_incline_bench_press_medium_grip,
            "Press inclinado con barra - Agarre medio"
        ),
        TranslationEntry(
            "Barbell Incline Close Grip Barbell Bench Press",
            R.string.exercise_barbell_incline_close_grip_barbell_bench_press,
            "Press inclinado con barra agarre cerrado"
        ),
        TranslationEntry(
            "Barbell Incline Shoulder Raise",
            R.string.exercise_barbell_incline_shoulder_raise,
            "Elevaciones inclinadas de hombros con barra"
        ),
        TranslationEntry(
            "Barbell Incline Shoulders Press Inside Squat Cage",
            R.string.exercise_barbell_incline_shoulders_press_inside_squat_cage,
            "Press inclinado de hombros en jaula con barra"
        ),
        TranslationEntry(
            "Barbell Jerk OL",
            R.string.exercise_barbell_jerk_ol,
            "Envión con barra"
        ),
        TranslationEntry(
            "Barbell JM Bench Press",
            R.string.exercise_barbell_jm_bench_press,
            "JM Press de banca con barra"
        ),
        TranslationEntry(
            "Barbell JM Press",
            R.string.exercise_barbell_jm_press,
            "JM Press con barra"
        ),
        TranslationEntry(
            "Barbell KAS Glute Bridge",
            R.string.exercise_barbell_kas_glute_bridge,
            "Puente de glúteos KAS con barra"
        ),
        TranslationEntry(
            "Barbell Kelso Shrugs",
            R.string.exercise_barbell_kelso_shrugs,
            "Encogimientos Kelso con barra"
        ),
        TranslationEntry(
            "Barbell Kickstand RDL",
            R.string.exercise_barbell_kickstand_rdl,
            "Peso muerto rumano unilateral con barra"
        ),
        TranslationEntry(
            "Barbell Landmine press",
            R.string.exercise_barbell_landmine_press,
            "Press landmine con barra"
        ),
        TranslationEntry(
            "Barbell Landmine Rotation",
            R.string.exercise_barbell_landmine_rotation,
            "Rotación en landmine con barra"
        ),
        TranslationEntry(
            "Barbell Landmine Squat to Press",
            R.string.exercise_barbell_landmine_squat_to_press,
            "Sentadilla a press en landmine con barra"
        ),
        TranslationEntry(
            "Barbell Larsen Press",
            R.string.exercise_barbell_larsen_press,
            "Press Larsen con barra"
        ),
        TranslationEntry(
            "Barbell Low Box Squat - Wide Stance",
            R.string.exercise_barbell_low_box_squat_wide_stance,
            "Sentadilla cajón bajo postura ancha con barra"
        ),
        TranslationEntry(
            "Barbell Lunge",
            R.string.exercise_barbell_lunge,
            "Zancadas con barra"
        ),
        TranslationEntry(
            "Barbell Lunges Standing",
            R.string.exercise_barbell_lunges_standing,
            "Zancadas estáticas con barra"
        ),
        TranslationEntry(
            "Barbell Lying Seal Row on Rack",
            R.string.exercise_barbell_lying_seal_row_on_rack,
            "Remo seal acostado en rack con barra"
        ),
        TranslationEntry(
            "Barbell Lying Triceps Extension Skull Crusher",
            R.string.exercise_barbell_lying_triceps_extension_skull_crusher,
            "Press francés acostado con barra"
        ),
        TranslationEntry(
            "Barbell Lying Triceps Extensions",
            R.string.exercise_barbell_lying_triceps_extensions,
            "Extensión de tríceps acostado con barra"
        ),
        TranslationEntry(
            "Barbell Meadows Row",
            R.string.exercise_barbell_meadows_row,
            "Remo Meadows con barra"
        ),
        TranslationEntry(
            "Barbell Overhead Press Isometric",
            R.string.exercise_barbell_overhead_press_isometric,
            "Press militar isométrico con barra"
        ),
        TranslationEntry(
            "Barbell Overhead Squat",
            R.string.exercise_barbell_overhead_squat,
            "Sentadilla sobre la cabeza con barra"
        ),
        TranslationEntry(
            "Barbell Pause Full Squat",
            R.string.exercise_barbell_pause_full_squat,
            "Sentadilla profunda con pausa con barra"
        ),
        TranslationEntry(
            "Barbell Paused Squats",
            R.string.exercise_barbell_paused_squats,
            "Sentadillas con pausa con barra"
        ),
        TranslationEntry(
            "Barbell Pendlay Row",
            R.string.exercise_barbell_pendlay_row,
            "Remo Pendlay con barra"
        ),
        TranslationEntry(
            "Barbell Perpendicular Unilateral Landmine Row",
            R.string.exercise_barbell_perpendicular_unilateral_landmine_row,
            "Remo landmine unilateral perpendicular barra"
        ),
        TranslationEntry(
            "Barbell Pin Bench Press BB",
            R.string.exercise_barbell_pin_bench_press_bb,
            "Press de banca sobre pines con barra"
        ),
        TranslationEntry(
            "Barbell Pin OHP",
            R.string.exercise_barbell_pin_ohp,
            "Press militar sobre pines con barra"
        ),
        TranslationEntry(
            "Barbell Pin Squat",
            R.string.exercise_barbell_pin_squat,
            "Sentadilla sobre pines con barra"
        ),
        TranslationEntry(
            "Barbell Power Clean",
            R.string.exercise_barbell_power_clean,
            "Power clean con barra"
        ),
        TranslationEntry(
            "Barbell Power Snatch",
            R.string.exercise_barbell_power_snatch,
            "Power snatch con barra"
        ),
        TranslationEntry(
            "Barbell Preacher Curl",
            R.string.exercise_barbell_preacher_curl,
            "Curl predicador con barra"
        ),
        TranslationEntry(
            "Barbell Prone Incline Curl",
            R.string.exercise_barbell_prone_incline_curl,
            "Curl inclinado prono con barra"
        ),
        TranslationEntry(
            "Barbell Push OHP",
            R.string.exercise_barbell_push_ohp,
            "Push press con barra"
        ),
        TranslationEntry(
            "Barbell Rack Pull",
            R.string.exercise_barbell_rack_pull,
            "Rack pull con barra"
        ),
        TranslationEntry(
            "Barbell Rear Delt Row",
            R.string.exercise_barbell_rear_delt_row,
            "Remo para deltoides posterior con barra"
        ),
        TranslationEntry(
            "Barbell Rear Lunge",
            R.string.exercise_barbell_rear_lunge,
            "Zancadas hacia atrás con barra"
        ),
        TranslationEntry(
            "Barbell Revers Wrist Curl II",
            R.string.exercise_barbell_revers_wrist_curl_ii,
            "Curl inverso de muñeca II con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Curl",
            R.string.exercise_barbell_reverse_curl,
            "Curl inverso con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Grip Barbell Curls",
            R.string.exercise_barbell_reverse_grip_barbell_curls,
            "Curl con agarre inverso con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Grip Bench Press",
            R.string.exercise_barbell_reverse_grip_bench_press,
            "Press de banca agarre inverso con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Wrist Curl",
            R.string.exercise_barbell_reverse_wrist_curl,
            "Curl inverso de muñeca con barra"
        ),
        TranslationEntry(
            "Barbell Rollout from Bench",
            R.string.exercise_barbell_rollout_from_bench,
            "Rueda abdominal desde banco con barra"
        ),
        TranslationEntry(
            "Barbell Romanian Deadlift",
            R.string.exercise_barbell_romanian_deadlift,
            "Peso muerto rumano con barra"
        ),
        TranslationEntry(
            "Barbell Row",
            R.string.exercise_barbell_row,
            "Remo con barra"
        ),
        TranslationEntry(
            "Barbell Row (Overhand)",
            R.string.exercise_barbell_row_overhand,
            "Remo con barra (pronado)"
        ),
        TranslationEntry(
            "Barbell Row (Underhand)",
            R.string.exercise_barbell_row_underhand,
            "Remo con barra (supino)"
        ),
        TranslationEntry(
            "Barbell Seated Calf Raise",
            R.string.exercise_barbell_seated_calf_raise,
            "Elevación de talones sentado con barra"
        ),
        TranslationEntry(
            "Barbell Seated Overhead Triceps Extension",
            R.string.exercise_barbell_seated_overhead_triceps_extension,
            "Extensión tríceps sobre cabeza sentado barra"
        ),
        TranslationEntry(
            "Barbell Shoulder Grip Upright Row",
            R.string.exercise_barbell_shoulder_grip_upright_row,
            "Remo al mentón agarre hombros con barra"
        ),
        TranslationEntry(
            "Barbell Shoulder Press",
            R.string.exercise_barbell_shoulder_press,
            "Press de hombros con barra"
        ),
        TranslationEntry(
            "Barbell Shrug",
            R.string.exercise_barbell_shrug,
            "Encogimientos con barra"
        ),
        TranslationEntry(
            "Barbell Shrug Behind The Back",
            R.string.exercise_barbell_shrug_behind_the_back,
            "Encogimientos traseros con barra"
        ),
        TranslationEntry(
            "Barbell Side Bend",
            R.string.exercise_barbell_side_bend,
            "Flexión lateral con barra"
        ),
        TranslationEntry(
            "Barbell Side Split Squat",
            R.string.exercise_barbell_side_split_squat,
            "Sentadilla split lateral con barra"
        ),
        TranslationEntry(
            "Barbell Side-laying interior rotation",
            R.string.exercise_barbell_side_laying_interior_rotation,
            "Rotación interna acostado de lado con barra"
        ),
        TranslationEntry(
            "Barbell Silverback Shrug",
            R.string.exercise_barbell_silverback_shrug,
            "Encogimientos Silverback con barra"
        ),
        TranslationEntry(
            "Barbell Single Leg Deadlift",
            R.string.exercise_barbell_single_leg_deadlift,
            "Peso muerto a una pierna con barra"
        ),
        TranslationEntry(
            "Barbell Single Leg Split Squat",
            R.string.exercise_barbell_single_leg_split_squat,
            "Sentadilla split a una pierna con barra"
        ),
        TranslationEntry(
            "Barbell Sliding Lateral Lunge",
            R.string.exercise_barbell_sliding_lateral_lunge,
            "Zancada lateral deslizante con barra"
        ),
        TranslationEntry(
            "Barbell Smith Machine Split Squat",
            R.string.exercise_barbell_smith_machine_split_squat,
            "Sentadilla split en máquina Smith con barra"
        ),
        TranslationEntry(
            "Barbell Snatch",
            R.string.exercise_barbell_snatch,
            "Arrancada con barra"
        ),
        TranslationEntry(
            "Barbell Snatch OL",
            R.string.exercise_barbell_snatch_ol,
            "Snatch olímpico con barra"
        ),
        TranslationEntry(
            "Barbell Split Jerk",
            R.string.exercise_barbell_split_jerk,
            "Split jerk con barra"
        ),
        TranslationEntry(
            "Barbell Squat",
            R.string.exercise_barbell_squat,
            "Sentadilla con barra"
        ),
        TranslationEntry(
            "Barbell Squat To A Bench",
            R.string.exercise_barbell_squat_to_a_bench,
            "Sentadilla a banco con barra"
        ),
        TranslationEntry(
            "Barbell Standing Back Wrist Curl",
            R.string.exercise_barbell_standing_back_wrist_curl,
            "Curl de muñeca tras nuca de pie con barra"
        ),
        TranslationEntry(
            "Barbell Standing Calf Raise",
            R.string.exercise_barbell_standing_calf_raise,
            "Elevación de talones de pie con barra"
        ),
        TranslationEntry(
            "Barbell Standing Dowel Shoulder press",
            R.string.exercise_barbell_standing_dowel_shoulder_press,
            "Press de hombros de pie con barra"
        ),
        TranslationEntry(
            "Barbell Standing Leg Calf Raise",
            R.string.exercise_barbell_standing_leg_calf_raise,
            "Elevación de pantorrillas de pie con barra"
        ),
        TranslationEntry(
            "Barbell Standing Military Press Without Rack",
            R.string.exercise_barbell_standing_military_press_without_rack,
            "Press militar de pie sin soporte barra"
        ),
        TranslationEntry(
            "Barbell Standing Single Leg Calf Raise",
            R.string.exercise_barbell_standing_single_leg_calf_raise,
            "Elevación talón a una pierna de pie con barra"
        ),
        TranslationEntry(
            "Barbell Step Back Lunge",
            R.string.exercise_barbell_step_back_lunge,
            "Zancada hacia atrás con barra"
        ),
        TranslationEntry(
            "Barbell Step Ups",
            R.string.exercise_barbell_step_ups,
            "Subidas al cajón con barra"
        ),
        TranslationEntry(
            "Barbell Stiff Legged Deadlift",
            R.string.exercise_barbell_stiff_legged_deadlift,
            "Peso muerto piernas rígidas con barra"
        ),
        TranslationEntry(
            "Barbell Sumo Deadlift",
            R.string.exercise_barbell_sumo_deadlift,
            "Peso muerto sumo con barra"
        ),
        TranslationEntry(
            "Barbell Sumo Squat",
            R.string.exercise_barbell_sumo_squat,
            "Sentadilla sumo con barra"
        ),
        TranslationEntry(
            "Barbell Supine press",
            R.string.exercise_barbell_supine_press,
            "Press supino con barra"
        ),
        TranslationEntry(
            "Barbell T-Bar row",
            R.string.exercise_barbell_t_bar_row,
            "Remo en barra T con barra"
        ),
        TranslationEntry(
            "Barbell Thruster",
            R.string.exercise_barbell_thruster,
            "Thruster con barra"
        ),
        TranslationEntry(
            "Barbell Trap press",
            R.string.exercise_barbell_trap_press,
            "Press de trapecio con barra"
        ),
        TranslationEntry(
            "Barbell Triceps Extension",
            R.string.exercise_barbell_triceps_extension,
            "Extensión de tríceps con barra"
        ),
        TranslationEntry(
            "Barbell Walking Lunge",
            R.string.exercise_barbell_walking_lunge,
            "Zancadas caminando con barra"
        ),
        TranslationEntry(
            "Barbell Wrist Curl",
            R.string.exercise_barbell_wrist_curl,
            "Curl de muñeca con barra"
        ),
        TranslationEntry(
            "Battle Ropes",
            R.string.exercise_battle_ropes,
            "Cuerdas de combate"
        ),
        TranslationEntry(
            "Bear Crawl",
            R.string.exercise_bear_crawl,
            "Paso del oso"
        ),
        TranslationEntry(
            "Bear Crawl Low Hip",
            R.string.exercise_bear_crawl_low_hip,
            "Paso del oso cadera baja"
        ),
        TranslationEntry(
            "Bear Crawl Sled Drags",
            R.string.exercise_bear_crawl_sled_drags,
            "Arrastre de trineo en paso del oso"
        ),
        TranslationEntry(
            "Bear Walk 2",
            R.string.exercise_bear_walk_2,
            "Caminata de oso 2"
        ),
        TranslationEntry(
            "Behind Head Chest Stretch",
            R.string.exercise_behind_head_chest_stretch,
            "Estiramiento de pecho tras la cabeza"
        ),
        TranslationEntry(
            "Belt Squat",
            R.string.exercise_belt_squat,
            "Sentadilla con cinturón"
        ),
        TranslationEntry(
            "Bench Dip on Floor",
            R.string.exercise_bench_dip_on_floor,
            "Fondos en banco desde el suelo"
        ),
        TranslationEntry(
            "Bench Dips",
            R.string.exercise_bench_dips,
            "Fondos en banco"
        ),
        TranslationEntry(
            "Bench Dips On Floor",
            R.string.exercise_bench_dips_on_floor,
            "Fondos de tríceps en suelo"
        ),
        TranslationEntry(
            "Bench Jump",
            R.string.exercise_bench_jump,
            "Saltos sobre banco"
        ),
        TranslationEntry(
            "Bench Press - Powerlifting",
            R.string.exercise_bench_press_powerlifting,
            "Press de banca - Powerlifting"
        ),
        TranslationEntry(
            "Bench Press - With Bands",
            R.string.exercise_bench_press_with_bands,
            "Press de banca con bandas"
        ),
        TranslationEntry(
            "Bench Press with Chains",
            R.string.exercise_bench_press_with_chains,
            "Press de banca con cadenas"
        ),
        TranslationEntry(
            "Bench Sprint",
            R.string.exercise_bench_sprint,
            "Sprints sobre banco"
        ),
        TranslationEntry(
            "Bent Leg Kickback Kneeling",
            R.string.exercise_bent_leg_kickback_kneeling,
            "Patada de glúteo de rodillas pierna flexionada"
        ),
        TranslationEntry(
            "Bent Leg Side Kick Kneeling",
            R.string.exercise_bent_leg_side_kick_kneeling,
            "Patada lateral de rodillas pierna flexionada"
        ),
        TranslationEntry(
            "Bent Over Dumbbell Rear Delt Raise With Head On Bench",
            R.string.exercise_bent_over_dumbbell_rear_delt_raise_with_head_on_bench,
            "Elevaciones posteriores cabeza en banco mancuernas"
        ),
        TranslationEntry(
            "Bent Over Low-Pulley Side Lateral",
            R.string.exercise_bent_over_low_pulley_side_lateral,
            "Elevaciones laterales inclinado en polea baja"
        ),
        TranslationEntry(
            "Bent Over One-Arm Long Bar Row",
            R.string.exercise_bent_over_one_arm_long_bar_row,
            "Remo inclinado con barra a una mano"
        ),
        TranslationEntry(
            "Bent over row to external rotation",
            R.string.exercise_bent_over_row_to_external_rotation,
            "Remo inclinado a rotación externa"
        ),
        TranslationEntry(
            "Bent Over Two-Arm Long Bar Row",
            R.string.exercise_bent_over_two_arm_long_bar_row,
            "Remo inclinado con barra a dos manos"
        ),
        TranslationEntry(
            "Bent Over Two-Dumbbell Row",
            R.string.exercise_bent_over_two_dumbbell_row,
            "Remo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Bent Over Two-Dumbbell Row With Palms In",
            R.string.exercise_bent_over_two_dumbbell_row_with_palms_in,
            "Remo inclinado mancuernas palmas enfrentadas"
        ),
        TranslationEntry(
            "Bent Press",
            R.string.exercise_bent_press,
            "Bent press"
        ),
        TranslationEntry(
            "Bent-Arm Barbell Pullover",
            R.string.exercise_bent_arm_barbell_pullover,
            "Pullover con barra brazos flexionados"
        ),
        TranslationEntry(
            "Bent-Arm Dumbbell Pullover",
            R.string.exercise_bent_arm_dumbbell_pullover,
            "Pullover con mancuerna brazos flexionados"
        ),
        TranslationEntry(
            "Bent-Knee Hip Raise",
            R.string.exercise_bent_knee_hip_raise,
            "Elevación de cadera rodillas flexionadas"
        ),
        TranslationEntry(
            "Bent-knee Iron Cross",
            R.string.exercise_bent_knee_iron_cross,
            "Cruz de hierro con rodillas flexionadas"
        ),
        TranslationEntry(
            "Bent-Leg Hamstring Stretch",
            R.string.exercise_bent_leg_hamstring_stretch,
            "Estiramiento isquiotibiales rodilla flexionada"
        ),
        TranslationEntry(
            "Biceps Close Grip Pull Down",
            R.string.exercise_biceps_close_grip_pull_down,
            "Jalón para bíceps agarre cerrado"
        ),
        TranslationEntry(
            "Biceps with TRX",
            R.string.exercise_biceps_with_trx,
            "Curl de bíceps con TRX"
        ),
        TranslationEntry(
            "Bicycle Crunch",
            R.string.exercise_bicycle_crunch,
            "Crunch bicicleta"
        ),
        TranslationEntry(
            "Bicycle Recline Walk",
            R.string.exercise_bicycle_recline_walk,
            "Caminata reclinada en bicicleta"
        ),
        TranslationEntry(
            "Bicycle Twisting Crunch",
            R.string.exercise_bicycle_twisting_crunch,
            "Crunch bicicleta con giro"
        ),
        TranslationEntry(
            "Bicycling",
            R.string.exercise_bicycling,
            "Ciclismo"
        ),
        TranslationEntry(
            "Bicycling, Stationary",
            R.string.exercise_bicycling_stationary,
            "Bicicleta fija"
        ),
        TranslationEntry(
            "Bird Dog",
            R.string.exercise_bird_dog,
            "Bird dog"
        ),
        TranslationEntry(
            "Black Widow Knee Slides",
            R.string.exercise_black_widow_knee_slides,
            "Deslizamientos de rodilla Black Widow"
        ),
        TranslationEntry(
            "Blackroll",
            R.string.exercise_blackroll,
            "Rodillo de espuma Blackroll"
        ),
        TranslationEntry(
            "Board Press",
            R.string.exercise_board_press,
            "Board press"
        ),
        TranslationEntry(
            "Bobbing Exhale Drill",
            R.string.exercise_bobbing_exhale_drill,
            "Ejercicio de respiración y exhalación"
        ),
        TranslationEntry(
            "Body Tricep Press",
            R.string.exercise_body_tricep_press,
            "Press de tríceps con peso corporal"
        ),
        TranslationEntry(
            "Body-Up",
            R.string.exercise_body_up,
            "Body-up"
        ),
        TranslationEntry(
            "Bodyweight Biceps Curl",
            R.string.exercise_bodyweight_biceps_curl,
            "Curl de bíceps con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Box Squat",
            R.string.exercise_bodyweight_box_squat,
            "Sentadilla en cajón con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Flyes",
            R.string.exercise_bodyweight_flyes,
            "Aperturas con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Mid Row",
            R.string.exercise_bodyweight_mid_row,
            "Remo medio con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Muscleup",
            R.string.exercise_bodyweight_muscleup,
            "Muscle-up con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Rear Lunge",
            R.string.exercise_bodyweight_rear_lunge,
            "Zancadas hacia atrás con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Reverse Lunge",
            R.string.exercise_bodyweight_reverse_lunge,
            "Zancadas inversas con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Squat",
            R.string.exercise_bodyweight_squat,
            "Sentadilla con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Standing Calf Raise",
            R.string.exercise_bodyweight_standing_calf_raise,
            "Elevación de talones de pie peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Walking Lunge",
            R.string.exercise_bodyweight_walking_lunge,
            "Zancadas caminando con peso corporal"
        ),
        TranslationEntry(
            "Bosu Ball Cable Crunch With Side Bends",
            R.string.exercise_bosu_ball_cable_crunch_with_side_bends,
            "Crunch en polea sobre Bosu con giros"
        ),
        TranslationEntry(
            "Bottoms Up",
            R.string.exercise_bottoms_up,
            "Bottoms-up con kettlebell"
        ),
        TranslationEntry(
            "Bottoms-Up Clean From The Hang Position",
            R.string.exercise_bottoms_up_clean_from_the_hang_position,
            "Clean bottoms-up desde posición colgada"
        ),
        TranslationEntry(
            "Box Handstand Push-up",
            R.string.exercise_box_handstand_push_up,
            "Flexiones verticales sobre cajón"
        ),
        TranslationEntry(
            "Box Jump (Multiple Response)",
            R.string.exercise_box_jump_multiple_response,
            "Saltos al cajón repetidos"
        ),
        TranslationEntry(
            "Box Skip",
            R.string.exercise_box_skip,
            "Saltos coordinados sobre cajón"
        ),
        TranslationEntry(
            "Box Squat",
            R.string.exercise_box_squat,
            "Sentadilla en cajón"
        ),
        TranslationEntry(
            "Box Squat with Bands",
            R.string.exercise_box_squat_with_bands,
            "Sentadilla en cajón con bandas"
        ),
        TranslationEntry(
            "Box Squat with Chains",
            R.string.exercise_box_squat_with_chains,
            "Sentadilla en cajón con cadenas"
        ),
        TranslationEntry(
            "Boxing",
            R.string.exercise_boxing,
            "Boxeo"
        ),
        TranslationEntry(
            "Boxing Cross (with Bag and Gloves)",
            R.string.exercise_boxing_cross_with_bag_and_gloves,
            "Directo de boxeo con saco y guantes"
        ),
        TranslationEntry(
            "Braced Squat",
            R.string.exercise_braced_squat,
            "Sentadilla con peso al frente"
        ),
        TranslationEntry(
            "Brachialis-SMR",
            R.string.exercise_brachialis_smr,
            "SMR de braquial"
        ),
        TranslationEntry(
            "Bradford/Rocky Presses",
            R.string.exercise_bradford_rocky_presses,
            "Press Bradford Rocky"
        ),
        TranslationEntry(
            "Bretzel stretch",
            R.string.exercise_bretzel_stretch,
            "Estiramiento Bretzel"
        ),
        TranslationEntry(
            "Bronco",
            R.string.exercise_bronco,
            "Bronco"
        ),
        TranslationEntry(
            "Bulgarian split squats left",
            R.string.exercise_bulgarian_split_squats_left,
            "Sentadilla búlgara pierna izquierda"
        ),
        TranslationEntry(
            "Bulgarian split squats right",
            R.string.exercise_bulgarian_split_squats_right,
            "Sentadilla búlgara pierna derecha"
        ),
        TranslationEntry(
            "Burpee Long Jump with Push-Up",
            R.string.exercise_burpee_long_jump_with_push_up,
            "Burpee con salto largo y flexión"
        ),
        TranslationEntry(
            "Burpee Over the Bar",
            R.string.exercise_burpee_over_the_bar,
            "Burpee sobre la barra"
        ),
        TranslationEntry(
            "Burpees",
            R.string.exercise_burpees,
            "Burpees"
        ),
        TranslationEntry(
            "Bus Drivers",
            R.string.exercise_bus_drivers,
            "Bus drivers con disco"
        ),
        TranslationEntry(
            "Butchers Block Stretch",
            R.string.exercise_butchers_block_stretch,
            "Estiramiento butcher block"
        ),
        TranslationEntry(
            "Butt Lift (Bridge)",
            R.string.exercise_butt_lift_bridge,
            "Elevación de glúteos (puente)"
        ),
        TranslationEntry(
            "Butt-Ups",
            R.string.exercise_butt_ups,
            "Butt-ups abdominales"
        ),
        TranslationEntry(
            "Butterfly",
            R.string.exercise_butterfly,
            "Aperturas en mariposa"
        ),
        TranslationEntry(
            "Butterfly Narrow Grip",
            R.string.exercise_butterfly_narrow_grip,
            "Aperturas en mariposa agarre cerrado"
        ),
        TranslationEntry(
            "Butterfly Pull-Up",
            R.string.exercise_butterfly_pull_up,
            "Dominadas estilo mariposa"
        ),
        TranslationEntry(
            "Butterfly Reverse",
            R.string.exercise_butterfly_reverse,
            "Aperturas invertidas en mariposa"
        ),
        TranslationEntry(
            "Butterfly Sit Up",
            R.string.exercise_butterfly_sit_up,
            "Sit-up mariposa"
        ),
        TranslationEntry(
            "Butterfly Stretch",
            R.string.exercise_butterfly_stretch,
            "Estiramiento mariposa"
        ),
        TranslationEntry(
            "Butterfly Superman",
            R.string.exercise_butterfly_superman,
            "Superman mariposa"
        ),
        TranslationEntry(
            "Butterfly Yoga Flaps",
            R.string.exercise_butterfly_yoga_flaps,
            "Aleteo de mariposa de yoga"
        ),
        TranslationEntry(
            "Cable 1-Arm Half-Kneeling Lat Pulldown",
            R.string.exercise_cable_1_arm_half_kneeling_lat_pulldown,
            "Jalón dorsal polea a una mano arrodillado"
        ),
        TranslationEntry(
            "Cable 45 Degrees Single Arm Reverse Fly",
            R.string.exercise_cable_45_degrees_single_arm_reverse_fly,
            "Aperturas inversas en polea a 45° a un brazo"
        ),
        TranslationEntry(
            "Cable Alternate Triceps Extension",
            R.string.exercise_cable_alternate_triceps_extension,
            "Extensión alterna de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Alternating High Cable Row",
            R.string.exercise_cable_alternating_high_cable_row,
            "Remo alto alterno en polea"
        ),
        TranslationEntry(
            "Cable Bar Lateral Pulldown",
            R.string.exercise_cable_bar_lateral_pulldown,
            "Jalón dorsal con barra en polea"
        ),
        TranslationEntry(
            "Cable Behind the Back Cable Lateral Raise",
            R.string.exercise_cable_behind_the_back_cable_lateral_raise,
            "Elevaciones laterales tras la espalda en polea"
        ),
        TranslationEntry(
            "Cable Bench Press",
            R.string.exercise_cable_bench_press,
            "Press de banca en polea"
        ),
        TranslationEntry(
            "Cable Bent over Cable Flye",
            R.string.exercise_cable_bent_over_cable_flye,
            "Aperturas inclinadas en polea"
        ),
        TranslationEntry(
            "Cable Chest Press",
            R.string.exercise_cable_chest_press,
            "Press de pecho en polea"
        ),
        TranslationEntry(
            "Cable Chest Press - Decline",
            R.string.exercise_cable_chest_press_decline,
            "Press declinado de pecho en polea"
        ),
        TranslationEntry(
            "Cable Close-grip supinated lat pulldown",
            R.string.exercise_cable_close_grip_supinated_lat_pulldown,
            "Jalón dorsal supino agarre cerrado en polea"
        ),
        TranslationEntry(
            "Cable Concentration Curl",
            R.string.exercise_cable_concentration_curl,
            "Curl concentrado en polea"
        ),
        TranslationEntry(
            "Cable Cross-Body Cable Y-Raise",
            R.string.exercise_cable_cross_body_cable_y_raise,
            "Elevación en Y cruzada en polea"
        ),
        TranslationEntry(
            "Cable Curls",
            R.string.exercise_cable_curls,
            "Curl de bíceps en polea"
        ),
        TranslationEntry(
            "Cable Deadlifts",
            R.string.exercise_cable_deadlifts,
            "Peso muerto en polea"
        ),
        TranslationEntry(
            "Cable Donkey Kickback",
            R.string.exercise_cable_donkey_kickback,
            "Patada de glúteo donkey en polea"
        ),
        TranslationEntry(
            "Cable Drag Pushdown",
            R.string.exercise_cable_drag_pushdown,
            "Pushdown drag en polea"
        ),
        TranslationEntry(
            "Cable Facepull",
            R.string.exercise_cable_facepull,
            "Face pull en polea"
        ),
        TranslationEntry(
            "Cable Fly Lower Chest",
            R.string.exercise_cable_fly_lower_chest,
            "Aperturas en polea para pecho inferior"
        ),
        TranslationEntry(
            "Cable Fly Middle Chest",
            R.string.exercise_cable_fly_middle_chest,
            "Aperturas en polea para pecho medio"
        ),
        TranslationEntry(
            "Cable Fly Upper Chest",
            R.string.exercise_cable_fly_upper_chest,
            "Aperturas en polea para pecho superior"
        ),
        TranslationEntry(
            "Cable Fly With Cable",
            R.string.exercise_cable_fly_with_cable,
            "Aperturas en polea"
        ),
        TranslationEntry(
            "Cable Front Raise",
            R.string.exercise_cable_front_raise,
            "Elevaciones frontales en polea"
        ),
        TranslationEntry(
            "Cable Front Raise with a small bar",
            R.string.exercise_cable_front_raise_with_a_small_bar,
            "Elevaciones frontales en polea con barra corta"
        ),
        TranslationEntry(
            "Cable glute extension",
            R.string.exercise_cable_glute_extension,
            "Extensión de glúteos en polea"
        ),
        TranslationEntry(
            "Cable Hammer Curl",
            R.string.exercise_cable_hammer_curl,
            "Curl martillo en polea"
        ),
        TranslationEntry(
            "Cable Hammer Curl with Rope",
            R.string.exercise_cable_hammer_curl_with_rope,
            "Curl martillo con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Hercules Pillars",
            R.string.exercise_cable_hercules_pillars,
            "Pilares de Hércules en polea"
        ),
        TranslationEntry(
            "Cable High Row",
            R.string.exercise_cable_high_row,
            "Remo alto en polea"
        ),
        TranslationEntry(
            "Cable High-Cable Cross Tricep Extention - NB",
            R.string.exercise_cable_high_cable_cross_tricep_extention_nb,
            "Extensión cruzada de tríceps en polea alta"
        ),
        TranslationEntry(
            "Cable High-Cable Lateral Raise",
            R.string.exercise_cable_high_cable_lateral_raise,
            "Elevaciones laterales en polea alta"
        ),
        TranslationEntry(
            "Cable High-pulley pullover",
            R.string.exercise_cable_high_pulley_pullover,
            "Pullover en polea alta"
        ),
        TranslationEntry(
            "Cable Hip Abduction",
            R.string.exercise_cable_hip_abduction,
            "Abducción de cadera en polea"
        ),
        TranslationEntry(
            "Cable Hip Adduction",
            R.string.exercise_cable_hip_adduction,
            "Aducción de cadera en polea"
        ),
        TranslationEntry(
            "Cable Horizontal Pallof Press",
            R.string.exercise_cable_horizontal_pallof_press,
            "Press Pallof horizontal en polea"
        ),
        TranslationEntry(
            "Cable Incline bench pulldown",
            R.string.exercise_cable_incline_bench_pulldown,
            "Jalón en banco inclinado en polea"
        ),
        TranslationEntry(
            "Cable Incline Pushdown",
            R.string.exercise_cable_incline_pushdown,
            "Pushdown inclinado en polea"
        ),
        TranslationEntry(
            "Cable Incline Triceps Extension",
            R.string.exercise_cable_incline_triceps_extension,
            "Extensión inclinada de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Internal Rotation",
            R.string.exercise_cable_internal_rotation,
            "Rotación interna en polea"
        ),
        TranslationEntry(
            "Cable Iron Cross",
            R.string.exercise_cable_iron_cross,
            "Cruz de hierro en polea"
        ),
        TranslationEntry(
            "Cable Judo Flip",
            R.string.exercise_cable_judo_flip,
            "Giro de judo en polea"
        ),
        TranslationEntry(
            "Cable Kickback II",
            R.string.exercise_cable_kickback_ii,
            "Patada de tríceps en polea II"
        ),
        TranslationEntry(
            "Cable Kneeling Crunch",
            R.string.exercise_cable_kneeling_crunch,
            "Crunch de rodillas en polea"
        ),
        TranslationEntry(
            "Cable Lat Pulldown - Cross Body Single Arm",
            R.string.exercise_cable_lat_pulldown_cross_body_single_arm,
            "Jalón dorsal cruzado a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Lat Pulldown Isometric",
            R.string.exercise_cable_lat_pulldown_isometric,
            "Jalón dorsal isométrico en polea"
        ),
        TranslationEntry(
            "Cable Lateral Pulldown with MAG Grip",
            R.string.exercise_cable_lateral_pulldown_with_mag_grip,
            "Jalón dorsal en polea con agarre MAG"
        ),
        TranslationEntry(
            "Cable Lateral Raises (Single Arm)",
            R.string.exercise_cable_lateral_raises_single_arm,
            "Elevaciones laterales a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Lateral Rows on Cable, One Armed",
            R.string.exercise_cable_lateral_rows_on_cable_one_armed,
            "Remo lateral a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Lean-Away Cable Lateral Raise",
            R.string.exercise_cable_lean_away_cable_lateral_raise,
            "Elevaciones laterales inclinadas en polea"
        ),
        TranslationEntry(
            "Cable Long-Pulley (low Row)",
            R.string.exercise_cable_long_pulley_low_row,
            "Remo bajo en polea"
        ),
        TranslationEntry(
            "Cable Long-Pulley, Narrow",
            R.string.exercise_cable_long_pulley_narrow,
            "Remo en polea baja agarre estrecho"
        ),
        TranslationEntry(
            "Cable Low Fly",
            R.string.exercise_cable_low_fly,
            "Aperturas bajas en polea"
        ),
        TranslationEntry(
            "Cable Low Pulley Cable Fly",
            R.string.exercise_cable_low_pulley_cable_fly,
            "Aperturas en polea baja"
        ),
        TranslationEntry(
            "Cable Low Seated Row",
            R.string.exercise_cable_low_seated_row,
            "Remo bajo sentado en polea"
        ),
        TranslationEntry(
            "Cable Low Seated Row with V-Bar",
            R.string.exercise_cable_low_seated_row_with_v_bar,
            "Remo bajo sentado en polea con barra en V"
        ),
        TranslationEntry(
            "Cable Low-Cable Cross-Over - NB",
            R.string.exercise_cable_low_cable_cross_over_nb,
            "Cruces en polea baja"
        ),
        TranslationEntry(
            "Cable Lying Triceps Extension",
            R.string.exercise_cable_lying_triceps_extension,
            "Extensión acostada de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Mentzer Pulldown",
            R.string.exercise_cable_mentzer_pulldown,
            "Jalón Mentzer en polea"
        ),
        TranslationEntry(
            "Cable Modified pulldown",
            R.string.exercise_cable_modified_pulldown,
            "Jalón modificado en polea"
        ),
        TranslationEntry(
            "Cable Neck extension",
            R.string.exercise_cable_neck_extension,
            "Extensión de cuello en polea"
        ),
        TranslationEntry(
            "Cable Neutral Grip Kickback",
            R.string.exercise_cable_neutral_grip_kickback,
            "Patada de tríceps agarre neutro en polea"
        ),
        TranslationEntry(
            "Cable Neutral Grip Lat Pulldown",
            R.string.exercise_cable_neutral_grip_lat_pulldown,
            "Jalón dorsal agarre neutro en polea"
        ),
        TranslationEntry(
            "Cable Neutral-grip chest pulldown",
            R.string.exercise_cable_neutral_grip_chest_pulldown,
            "Jalón al pecho agarre neutro en polea"
        ),
        TranslationEntry(
            "Cable Omni Cable Cross-over",
            R.string.exercise_cable_omni_cable_cross_over,
            "Cruces omni en polea"
        ),
        TranslationEntry(
            "Cable One Arm Bent Row",
            R.string.exercise_cable_one_arm_bent_row,
            "Remo inclinado a un brazo en polea"
        ),
        TranslationEntry(
            "Cable One Arm Curl",
            R.string.exercise_cable_one_arm_curl,
            "Curl a un brazo en polea"
        ),
        TranslationEntry(
            "Cable One Arm Lat Pulldown",
            R.string.exercise_cable_one_arm_lat_pulldown,
            "Jalón dorsal a un brazo en polea"
        ),
        TranslationEntry(
            "Cable One Arm Lateral Raise",
            R.string.exercise_cable_one_arm_lateral_raise,
            "Elevaciones laterales a un brazo en polea"
        ),
        TranslationEntry(
            "Cable One Arm Overhead Cable Tricep Extension",
            R.string.exercise_cable_one_arm_overhead_cable_tricep_extension,
            "Extensión tríceps sobre cabeza a un brazo polea"
        ),
        TranslationEntry(
            "Cable One Arm Tricep Extension",
            R.string.exercise_cable_one_arm_tricep_extension,
            "Extensión de tríceps a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Overhand Cable Curl",
            R.string.exercise_cable_overhand_cable_curl,
            "Curl pronado en polea"
        ),
        TranslationEntry(
            "Cable Overhead Cable Tricep Extension",
            R.string.exercise_cable_overhead_cable_tricep_extension,
            "Extensión de tríceps sobre la cabeza en polea"
        ),
        TranslationEntry(
            "Cable Overhead Triceps Extension Rope Attachment",
            R.string.exercise_cable_overhead_triceps_extension_rope_attachment,
            "Extensión tríceps sobre cabeza con cuerda polea"
        ),
        TranslationEntry(
            "Cable Pallof Press",
            R.string.exercise_cable_pallof_press,
            "Press Pallof en polea"
        ),
        TranslationEntry(
            "Cable Preacher Curl",
            R.string.exercise_cable_preacher_curl,
            "Curl predicador en polea"
        ),
        TranslationEntry(
            "Cable Press Around",
            R.string.exercise_cable_press_around,
            "Press around en polea"
        ),
        TranslationEntry(
            "Cable Pull Through",
            R.string.exercise_cable_pull_through,
            "Pull-through en polea"
        ),
        TranslationEntry(
            "Cable Pullback",
            R.string.exercise_cable_pullback,
            "Pullback en polea"
        ),
        TranslationEntry(
            "Cable Push Down with Rope Attachment",
            R.string.exercise_cable_push_down_with_rope_attachment,
            "Pushdown en polea con cuerda"
        ),
        TranslationEntry(
            "Cable Rear Delt Fly",
            R.string.exercise_cable_rear_delt_fly,
            "Aperturas posteriores en polea"
        ),
        TranslationEntry(
            "Cable Rear-Delt Fly (single arm)",
            R.string.exercise_cable_rear_delt_fly_single_arm,
            "Aperturas posteriores a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Reverse Cable Flye",
            R.string.exercise_cable_reverse_cable_flye,
            "Aperturas invertidas en polea"
        ),
        TranslationEntry(
            "Cable Reverse Crunch",
            R.string.exercise_cable_reverse_crunch,
            "Crunch inverso en polea"
        ),
        TranslationEntry(
            "Cable Reverse EZ Bar Cable Curls",
            R.string.exercise_cable_reverse_ez_bar_cable_curls,
            "Curl inverso en polea con barra EZ"
        ),
        TranslationEntry(
            "Cable Reverse Fly Standing",
            R.string.exercise_cable_reverse_fly_standing,
            "Aperturas posteriores de pie en polea"
        ),
        TranslationEntry(
            "Cable Reverse Grip Pulldown",
            R.string.exercise_cable_reverse_grip_pulldown,
            "Jalón agarre supino en polea"
        ),
        TranslationEntry(
            "Cable Reverse Grip Straight Back Seated High Row",
            R.string.exercise_cable_reverse_grip_straight_back_seated_high_row,
            "Remo alto sentado agarre supino en polea"
        ),
        TranslationEntry(
            "Cable Reverse Grip Triceps Push Down SZ Bar",
            R.string.exercise_cable_reverse_grip_triceps_push_down_sz_bar,
            "Pushdown tríceps agarre inverso barra SZ polea"
        ),
        TranslationEntry(
            "Cable Reverse Woodchop",
            R.string.exercise_cable_reverse_woodchop,
            "Woodchop inverso en polea"
        ),
        TranslationEntry(
            "Cable Rocking Triceps Pushdown",
            R.string.exercise_cable_rocking_triceps_pushdown,
            "Pushdown tríceps con balanceo en polea"
        ),
        TranslationEntry(
            "Cable Rope Overhead Triceps Extension",
            R.string.exercise_cable_rope_overhead_triceps_extension,
            "Extensión sobre cabeza con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Rope Pullover/row",
            R.string.exercise_cable_rope_pullover_row,
            "Pullover y remo con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Rope Rear-Delt Rows",
            R.string.exercise_cable_rope_rear_delt_rows,
            "Remo para deltoides posterior con cuerda polea"
        ),
        TranslationEntry(
            "Cable Russian Twists",
            R.string.exercise_cable_russian_twists,
            "Giros rusos en polea"
        ),
        TranslationEntry(
            "Cable Seated Cable Mid Trap Shrug",
            R.string.exercise_cable_seated_cable_mid_trap_shrug,
            "Encogimientos para trapecio medio en polea"
        ),
        TranslationEntry(
            "Cable Seated Chest Fly",
            R.string.exercise_cable_seated_chest_fly,
            "Aperturas de pecho sentado en polea"
        ),
        TranslationEntry(
            "Cable Seated Crunch",
            R.string.exercise_cable_seated_crunch,
            "Crunch sentado en polea"
        ),
        TranslationEntry(
            "Cable Seated Lateral Raise",
            R.string.exercise_cable_seated_lateral_raise,
            "Elevaciones laterales sentado en polea"
        ),
        TranslationEntry(
            "Cable Seated Row with V-Bar",
            R.string.exercise_cable_seated_row_with_v_bar,
            "Remo sentado en polea con barra en V"
        ),
        TranslationEntry(
            "Cable Seated V-Grip Row",
            R.string.exercise_cable_seated_v_grip_row,
            "Remo sentado en polea con agarre en V"
        ),
        TranslationEntry(
            "Cable Seated Wide Grip Row",
            R.string.exercise_cable_seated_wide_grip_row,
            "Remo sentado en polea con agarre ancho"
        ),
        TranslationEntry(
            "Cable Shotgun Row",
            R.string.exercise_cable_shotgun_row,
            "Remo shotgun en polea"
        ),
        TranslationEntry(
            "Cable Shoulder External Rotation (Cable)",
            R.string.exercise_cable_shoulder_external_rotation_cable,
            "Rotación externa de hombro en polea"
        ),
        TranslationEntry(
            "Cable Shoulder Internal Rotation (Cable)",
            R.string.exercise_cable_shoulder_internal_rotation_cable,
            "Rotación interna de hombro en polea"
        ),
        TranslationEntry(
            "Cable Shoulder Press",
            R.string.exercise_cable_shoulder_press,
            "Press de hombros en polea"
        ),
        TranslationEntry(
            "Cable Shoulder Y-pull cable",
            R.string.exercise_cable_shoulder_y_pull_cable,
            "Tirón en Y para hombros en polea"
        ),
        TranslationEntry(
            "Cable Shrug",
            R.string.exercise_cable_shrug,
            "Encogimientos en polea"
        ),
        TranslationEntry(
            "Cable Shrug-In",
            R.string.exercise_cable_shrug_in,
            "Encogimientos hacia adentro en polea"
        ),
        TranslationEntry(
            "Cable Side Lateral Raise (Cable)",
            R.string.exercise_cable_side_lateral_raise_cable,
            "Elevaciones laterales en polea"
        ),
        TranslationEntry(
            "Cable Side lateral raise - Front (Cable)",
            R.string.exercise_cable_side_lateral_raise_front_cable,
            "Elevaciones laterales frontales en polea"
        ),
        TranslationEntry(
            "Cable Side Straight-Arm Pulldown (Cable)",
            R.string.exercise_cable_side_straight_arm_pulldown_cable,
            "Jalón lateral con brazos rectos en polea"
        ),
        TranslationEntry(
            "Cable Single arm row",
            R.string.exercise_cable_single_arm_row,
            "Remo a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Single Arm Triceps Push Down Rope Attachment",
            R.string.exercise_cable_single_arm_triceps_push_down_rope_attachment,
            "Pushdown tríceps a un brazo con cuerda polea"
        ),
        TranslationEntry(
            "Cable Single-arm cable pushdown",
            R.string.exercise_cable_single_arm_cable_pushdown,
            "Pushdown a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Single-Arm Cable Row /w Rotation (floor)",
            R.string.exercise_cable_single_arm_cable_row_w_rotation_floor,
            "Remo a un brazo con rotación en suelo polea"
        ),
        TranslationEntry(
            "Cable Split Stance Single Arm Row",
            R.string.exercise_cable_split_stance_single_arm_row,
            "Remo a un brazo en postura dividida en polea"
        ),
        TranslationEntry(
            "Cable Squat Row",
            R.string.exercise_cable_squat_row,
            "Sentadilla con remo en polea"
        ),
        TranslationEntry(
            "Cable Squat Row with Rope Attachment",
            R.string.exercise_cable_squat_row_with_rope_attachment,
            "Sentadilla con remo con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Standing Adduction (Cable)",
            R.string.exercise_cable_standing_adduction_cable,
            "Aducción de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Crossover High Reverse Fly",
            R.string.exercise_cable_standing_crossover_high_reverse_fly,
            "Aperturas inversas altas cruzadas de pie polea"
        ),
        TranslationEntry(
            "Cable Standing Hip Abduction",
            R.string.exercise_cable_standing_hip_abduction,
            "Abducción de cadera de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Hip Adduction",
            R.string.exercise_cable_standing_hip_adduction,
            "Aducción de cadera de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing in Ner Curl",
            R.string.exercise_cable_standing_in_ner_curl,
            "Curl interior de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Reverse Grip Curl Straight Bar",
            R.string.exercise_cable_standing_reverse_grip_curl_straight_bar,
            "Curl de pie agarre inverso barra recta polea"
        ),
        TranslationEntry(
            "Cable Standing Single Arm Fly",
            R.string.exercise_cable_standing_single_arm_fly,
            "Aperturas a un brazo de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Supinated Face Pull with Rope",
            R.string.exercise_cable_standing_supinated_face_pull_with_rope,
            "Face pull supino de pie con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Standing Y Raise",
            R.string.exercise_cable_standing_y_raise,
            "Elevaciones en Y de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing-Up Straight Crossovers",
            R.string.exercise_cable_standing_up_straight_crossovers,
            "Cruces de pie erguido en polea"
        ),
        TranslationEntry(
            "Cable Straight Arm Pulldown",
            R.string.exercise_cable_straight_arm_pulldown,
            "Jalón con brazos rectos en polea"
        ),
        TranslationEntry(
            "Cable Straight Arm Pulldown with Rope",
            R.string.exercise_cable_straight_arm_pulldown_with_rope,
            "Jalón con brazos rectos con cuerda en polea"
        ),
        TranslationEntry(
            "Cable Straight Bar Cable Curls",
            R.string.exercise_cable_straight_bar_cable_curls,
            "Curl con barra recta en polea"
        ),
        TranslationEntry(
            "Cable Straight Bar Cable Front Raise",
            R.string.exercise_cable_straight_bar_cable_front_raise,
            "Elevaciones frontales con barra recta en polea"
        ),
        TranslationEntry(
            "Cable Straight-arm Pull Down (bar Attachment)",
            R.string.exercise_cable_straight_arm_pull_down_bar_attachment,
            "Jalón con brazos rectos con barra en polea"
        ),
        TranslationEntry(
            "Cable SZ Bar Close Grip Curl",
            R.string.exercise_cable_sz_bar_close_grip_curl,
            "Curl agarre cerrado con barra SZ en polea"
        ),
        TranslationEntry(
            "Cable Tri Extension - Internal Rotation",
            R.string.exercise_cable_tri_extension_internal_rotation,
            "Extensión tríceps con rotación interna polea"
        ),
        TranslationEntry(
            "Cable Tricep Kickback",
            R.string.exercise_cable_tricep_kickback,
            "Patada de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Triceps Extensions on Cable With Bar",
            R.string.exercise_cable_triceps_extensions_on_cable_with_bar,
            "Extensión de tríceps con barra en polea"
        ),
        TranslationEntry(
            "Cable Triceps Press",
            R.string.exercise_cable_triceps_press,
            "Press de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Triceps Push Down SZ Bar",
            R.string.exercise_cable_triceps_push_down_sz_bar,
            "Pushdown de tríceps con barra SZ en polea"
        ),
        TranslationEntry(
            "Cable Triceps Push Down V-Bar",
            R.string.exercise_cable_triceps_push_down_v_bar,
            "Pushdown de tríceps con barra en V en polea"
        ),
        TranslationEntry(
            "Cable Triceps Pushdown Isometric",
            R.string.exercise_cable_triceps_pushdown_isometric,
            "Pushdown de tríceps isométrico en polea"
        ),
        TranslationEntry(
            "Cable Trunk Rotation With Cable",
            R.string.exercise_cable_trunk_rotation_with_cable,
            "Rotación de torso en polea"
        ),
        TranslationEntry(
            "Cable Unilateral Bicep Curl",
            R.string.exercise_cable_unilateral_bicep_curl,
            "Curl unilateral de bíceps en polea"
        ),
        TranslationEntry(
            "Cable Unilateral Cable row",
            R.string.exercise_cable_unilateral_cable_row,
            "Remo unilateral en polea"
        ),
        TranslationEntry(
            "Cable Unilateral Cross Body Cable Pull Down",
            R.string.exercise_cable_unilateral_cross_body_cable_pull_down,
            "Jalón unilateral cruzado en polea"
        ),
        TranslationEntry(
            "Cable Upright Row",
            R.string.exercise_cable_upright_row,
            "Remo al mentón en polea"
        ),
        TranslationEntry(
            "Cable V-Bar Pulldown",
            R.string.exercise_cable_v_bar_pulldown,
            "Jalón con barra en V en polea"
        ),
        TranslationEntry(
            "Cable Wide-grip Pulldown",
            R.string.exercise_cable_wide_grip_pulldown,
            "Jalón con agarre ancho en polea"
        ),
        TranslationEntry(
            "Cable Wide-grip supinated lat pulldown",
            R.string.exercise_cable_wide_grip_supinated_lat_pulldown,
            "Jalón dorsal supino agarre ancho en polea"
        ),
        TranslationEntry(
            "Cable Woodchoppers",
            R.string.exercise_cable_woodchoppers,
            "Woodchoppers en polea"
        ),
        TranslationEntry(
            "Cable Wrist Curl",
            R.string.exercise_cable_wrist_curl,
            "Curl de muñeca en polea"
        ),
        TranslationEntry(
            "Calf Press",
            R.string.exercise_calf_press,
            "Prensa para pantorrillas"
        ),
        TranslationEntry(
            "Calf Press On The Leg Press Machine",
            R.string.exercise_calf_press_on_the_leg_press_machine,
            "Prensa de pantorrillas en prensa de piernas"
        ),
        TranslationEntry(
            "Calf Raise On A Dumbbell",
            R.string.exercise_calf_raise_on_a_dumbbell,
            "Elevación de talones sobre mancuerna"
        ),
        TranslationEntry(
            "Calf Raise using Hack Squat Machine",
            R.string.exercise_calf_raise_using_hack_squat_machine,
            "Elevación de talones en máquina Hack"
        ),
        TranslationEntry(
            "Calf Raises - With Bands",
            R.string.exercise_calf_raises_with_bands,
            "Elevación de talones con bandas"
        ),
        TranslationEntry(
            "Calf Raises on Hackenschmitt Machine",
            R.string.exercise_calf_raises_on_hackenschmitt_machine,
            "Elevación de talones en máquina Hackenschmitt"
        ),
        TranslationEntry(
            "Calf raises, left leg",
            R.string.exercise_calf_raises_left_leg,
            "Elevación de talones, pierna izquierda"
        ),
        TranslationEntry(
            "Calf raises, one legged",
            R.string.exercise_calf_raises_one_legged,
            "Elevación de talones a una pierna"
        ),
        TranslationEntry(
            "Calf raises, right leg",
            R.string.exercise_calf_raises_right_leg,
            "Elevación de talones, pierna derecha"
        ),
        TranslationEntry(
            "Calf Stretch Elbows Against Wall",
            R.string.exercise_calf_stretch_elbows_against_wall,
            "Estiramiento de gemelos con codos en pared"
        ),
        TranslationEntry(
            "Calf Stretch Hands Against Wall",
            R.string.exercise_calf_stretch_hands_against_wall,
            "Estiramiento de gemelos con manos en pared"
        ),
        TranslationEntry(
            "Calf-Machine Shoulder Shrug",
            R.string.exercise_calf_machine_shoulder_shrug,
            "Encogimientos de hombros en máquina gemelos"
        ),
        TranslationEntry(
            "Calves foam roller",
            R.string.exercise_calves_foam_roller,
            "Rodillo de espuma para pantorrillas"
        ),
        TranslationEntry(
            "Calves-SMR",
            R.string.exercise_calves_smr,
            "SMR de pantorrillas"
        ),
        TranslationEntry(
            "Captains Chair Leg Raise",
            R.string.exercise_captains_chair_leg_raise,
            "Elevación de piernas en silla de capitán"
        ),
        TranslationEntry(
            "Captains Chair Straight Leg Raise",
            R.string.exercise_captains_chair_straight_leg_raise,
            "Elevación piernas rectas en silla de capitán"
        ),
        TranslationEntry(
            "Car Deadlift",
            R.string.exercise_car_deadlift,
            "Peso muerto con coche"
        ),
        TranslationEntry(
            "Car Drivers",
            R.string.exercise_car_drivers,
            "Car drivers con disco"
        ),
        TranslationEntry(
            "Carioca Quick Step",
            R.string.exercise_carioca_quick_step,
            "Paso rápido carioca"
        ),
        TranslationEntry(
            "Cat Plank",
            R.string.exercise_cat_plank,
            "Plancha gato"
        ),
        TranslationEntry(
            "Cat Stretch",
            R.string.exercise_cat_stretch,
            "Estiramiento del gato"
        ),
        TranslationEntry(
            "Cat-Cow",
            R.string.exercise_cat_cow,
            "Gato-vaca"
        ),
        TranslationEntry(
            "Catch and Overhead Throw",
            R.string.exercise_catch_and_overhead_throw,
            "Recepción y lanzamiento sobre la cabeza"
        ),
        TranslationEntry(
            "Chain Handle Extension",
            R.string.exercise_chain_handle_extension,
            "Extensión con agarre de cadenas"
        ),
        TranslationEntry(
            "Chain Press",
            R.string.exercise_chain_press,
            "Press con cadenas"
        ),
        TranslationEntry(
            "Chair dips",
            R.string.exercise_chair_dips,
            "Fondos en silla"
        ),
        TranslationEntry(
            "Chair Leg Extended Stretch",
            R.string.exercise_chair_leg_extended_stretch,
            "Estiramiento de pierna extendida en silla"
        ),
        TranslationEntry(
            "Chair Lower Back Stretch",
            R.string.exercise_chair_lower_back_stretch,
            "Estiramiento lumbar en silla"
        ),
        TranslationEntry(
            "Chair Squat",
            R.string.exercise_chair_squat,
            "Sentadilla en silla"
        ),
        TranslationEntry(
            "Chair Upper Body Stretch",
            R.string.exercise_chair_upper_body_stretch,
            "Estiramiento del tren superior en silla"
        ),
        TranslationEntry(
            "Chest And Front Of Shoulder Stretch",
            R.string.exercise_chest_and_front_of_shoulder_stretch,
            "Estiramiento de pecho y hombro anterior"
        ),
        TranslationEntry(
            "Chest Dip",
            R.string.exercise_chest_dip,
            "Fondos para pecho"
        ),
        TranslationEntry(
            "Chest Dip on Dip Pullup Cage",
            R.string.exercise_chest_dip_on_dip_pullup_cage,
            "Fondos para pecho en jaula de dominadas"
        ),
        TranslationEntry(
            "Chest Press",
            R.string.exercise_chest_press,
            "Press de pecho"
        ),
        TranslationEntry(
            "Chest Push (multiple response)",
            R.string.exercise_chest_push_multiple_response,
            "Empuje de pecho (respuesta múltiple)"
        ),
        TranslationEntry(
            "Chest Push (single response)",
            R.string.exercise_chest_push_single_response,
            "Empuje de pecho (respuesta simple)"
        ),
        TranslationEntry(
            "Chest Push from 3 point stance",
            R.string.exercise_chest_push_from_3_point_stance,
            "Empuje de pecho desde 3 puntos"
        ),
        TranslationEntry(
            "Chest Push with Run Release",
            R.string.exercise_chest_push_with_run_release,
            "Empuje de pecho con carrera"
        ),
        TranslationEntry(
            "Chest Stretch on Stability Ball",
            R.string.exercise_chest_stretch_on_stability_ball,
            "Estiramiento de pecho en balón suizo"
        ),
        TranslationEntry(
            "Child's Pose",
            R.string.exercise_childs_pose,
            "Postura del niño"
        ),
        TranslationEntry(
            "Chin To Chest Stretch",
            R.string.exercise_chin_to_chest_stretch,
            "Estiramiento de mentón al pecho"
        ),
        TranslationEntry(
            "Chin tuck",
            R.string.exercise_chin_tuck,
            "Retracción cervical (chin tuck)"
        ),
        TranslationEntry(
            "Chin-Up",
            R.string.exercise_chin_up,
            "Dominadas supinas"
        ),
        TranslationEntry(
            "Circus Bell",
            R.string.exercise_circus_bell,
            "Mancuerna de circo"
        ),
        TranslationEntry(
            "Clamshell",
            R.string.exercise_clamshell,
            "Clamshell"
        ),
        TranslationEntry(
            "Clamshell to Reverse Clamshell",
            R.string.exercise_clamshell_to_reverse_clamshell,
            "Clamshell a clamshell inverso"
        ),
        TranslationEntry(
            "Clap Push-Up",
            R.string.exercise_clap_push_up,
            "Flexiones con aplauso"
        ),
        TranslationEntry(
            "Claps over the head",
            R.string.exercise_claps_over_the_head,
            "Aplausos sobre la cabeza"
        ),
        TranslationEntry(
            "Clean",
            R.string.exercise_clean,
            "Cargada (Clean)"
        ),
        TranslationEntry(
            "Clean and Jerk",
            R.string.exercise_clean_and_jerk,
            "Cargada y envión"
        ),
        TranslationEntry(
            "Clean and Press",
            R.string.exercise_clean_and_press,
            "Cargada y press militar"
        ),
        TranslationEntry(
            "Clean Deadlift",
            R.string.exercise_clean_deadlift,
            "Peso muerto estilo cargada"
        ),
        TranslationEntry(
            "Clean from Blocks",
            R.string.exercise_clean_from_blocks,
            "Cargada desde bloques"
        ),
        TranslationEntry(
            "Clean Pull",
            R.string.exercise_clean_pull,
            "Tirón de cargada"
        ),
        TranslationEntry(
            "Clean Shrug",
            R.string.exercise_clean_shrug,
            "Encogimiento de cargada"
        ),
        TranslationEntry(
            "ClimbMill",
            R.string.exercise_climbmill,
            "ClimbMill (escaladora)"
        ),
        TranslationEntry(
            "Clock Push-Up",
            R.string.exercise_clock_push_up,
            "Flexiones reloj"
        ),
        TranslationEntry(
            "Clockwise neck circles",
            R.string.exercise_clockwise_neck_circles,
            "Círculos de cuello en sentido horario"
        ),
        TranslationEntry(
            "Close Grip Pushup",
            R.string.exercise_close_grip_pushup,
            "Flexiones con agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip Barbell Bench Press",
            R.string.exercise_close_grip_barbell_bench_press,
            "Press de banca con barra agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip Dumbbell Press",
            R.string.exercise_close_grip_dumbbell_press,
            "Press con mancuernas agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip EZ Bar Curl",
            R.string.exercise_close_grip_ez_bar_curl,
            "Curl con barra EZ agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip EZ-Bar Curl with Band",
            R.string.exercise_close_grip_ez_bar_curl_with_band,
            "Curl con barra EZ y banda agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip EZ-Bar Press",
            R.string.exercise_close_grip_ez_bar_press,
            "Press con barra EZ agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip Front Lat Pulldown",
            R.string.exercise_close_grip_front_lat_pulldown,
            "Jalón al pecho agarre cerrado"
        ),
        TranslationEntry(
            "Close-grip Lat Pull Down",
            R.string.exercise_close_grip_lat_pull_down,
            "Jalón dorsal agarre cerrado"
        ),
        TranslationEntry(
            "Close-grip Press-ups",
            R.string.exercise_close_grip_press_ups,
            "Flexiones agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip Push-Up off of a Dumbbell",
            R.string.exercise_close_grip_push_up_off_of_a_dumbbell,
            "Flexiones sobre mancuerna agarre cerrado"
        ),
        TranslationEntry(
            "Close-Grip Standing Barbell Curl",
            R.string.exercise_close_grip_standing_barbell_curl,
            "Curl de pie con barra agarre cerrado"
        ),
        TranslationEntry(
            "Cobra Stretch",
            R.string.exercise_cobra_stretch,
            "Estiramiento de cobra"
        ),
        TranslationEntry(
            "Cocoons",
            R.string.exercise_cocoons,
            "Cocoons abdominales"
        ),
        TranslationEntry(
            "Commando Pull-ups",
            R.string.exercise_commando_pull_ups,
            "Dominadas comando"
        ),
        TranslationEntry(
            "Conan's Wheel",
            R.string.exercise_conans_wheel,
            "Rueda de Conan"
        ),
        TranslationEntry(
            "Concentration Curls",
            R.string.exercise_concentration_curls,
            "Curl concentrado"
        ),
        TranslationEntry(
            "Controlled Kegels",
            R.string.exercise_controlled_kegels,
            "Ejercicios de Kegel controlados"
        ),
        TranslationEntry(
            "Cool-Down Swim",
            R.string.exercise_cool_down_swim,
            "Nado de enfriamiento"
        ),
        TranslationEntry(
            "Copenhagen Adduction Exercise",
            R.string.exercise_copenhagen_adduction_exercise,
            "Ejercicio de aducción Copenhague"
        ),
        TranslationEntry(
            "Core Rotation",
            R.string.exercise_core_rotation,
            "Rotación de core"
        ),
        TranslationEntry(
            "Cossack squat",
            R.string.exercise_cossack_squat,
            "Sentadilla cosaca"
        ),
        TranslationEntry(
            "Couch Stretch",
            R.string.exercise_couch_stretch,
            "Estiramiento de sofá"
        ),
        TranslationEntry(
            "Counterclockwise neck circles",
            R.string.exercise_counterclockwise_neck_circles,
            "Círculos cuello sentido antihorario"
        ),
        TranslationEntry(
            "Cross Body Hammer Curl",
            R.string.exercise_cross_body_hammer_curl,
            "Curl martillo cruzado"
        ),
        TranslationEntry(
            "Cross Over - With Bands",
            R.string.exercise_cross_over_with_bands,
            "Cruces con bandas"
        ),
        TranslationEntry(
            "Cross-Body Crunch",
            R.string.exercise_cross_body_crunch,
            "Crunch cruzado"
        ),
        TranslationEntry(
            "Crossbody Hamstring Stretch",
            R.string.exercise_crossbody_hamstring_stretch,
            "Estiramiento cruzado de isquiotibiales"
        ),
        TranslationEntry(
            "Crossbody Leg Swings",
            R.string.exercise_crossbody_leg_swings,
            "Balanceos cruzados de pierna"
        ),
        TranslationEntry(
            "Crossover Reverse Lunge",
            R.string.exercise_crossover_reverse_lunge,
            "Zancadas inversas cruzadas"
        ),
        TranslationEntry(
            "Crucifix",
            R.string.exercise_crucifix,
            "Crucifijo"
        ),
        TranslationEntry(
            "Crunch - Hands Overhead",
            R.string.exercise_crunch_hands_overhead,
            "Crunch con manos sobre la cabeza"
        ),
        TranslationEntry(
            "Crunch - Legs On Exercise Ball",
            R.string.exercise_crunch_legs_on_exercise_ball,
            "Crunch con piernas en balón suizo"
        ),
        TranslationEntry(
            "Crunch Floor",
            R.string.exercise_crunch_floor,
            "Crunch en suelo"
        ),
        TranslationEntry(
            "Crunches",
            R.string.exercise_crunches,
            "Crunches abdominales"
        ),
        TranslationEntry(
            "Crunches on Machine",
            R.string.exercise_crunches_on_machine,
            "Crunches en máquina"
        ),
        TranslationEntry(
            "Crunches With Legs Up",
            R.string.exercise_crunches_with_legs_up,
            "Crunches con piernas elevadas"
        ),
        TranslationEntry(
            "Cuban Press",
            R.string.exercise_cuban_press,
            "Press cubano"
        ),
        TranslationEntry(
            "Cycling",
            R.string.exercise_cycling,
            "Ciclismo"
        ),
        TranslationEntry(
            "Dancer's Stretch",
            R.string.exercise_dancers_stretch,
            "Estiramiento del bailarín"
        ),
        TranslationEntry(
            "Dead Bug",
            R.string.exercise_dead_bug,
            "Dead bug"
        ),
        TranslationEntry(
            "Dead Hang Stretch",
            R.string.exercise_dead_hang_stretch,
            "Estiramiento colgado pasivo"
        ),
        TranslationEntry(
            "Deadlift with Chains",
            R.string.exercise_deadlift_with_chains,
            "Peso muerto con cadenas"
        ),
        TranslationEntry(
            "Decline Bench Leg Raise",
            R.string.exercise_decline_bench_leg_raise,
            "Elevación de piernas en banco declinado"
        ),
        TranslationEntry(
            "Decline Close-Grip Bench To Skull Crusher",
            R.string.exercise_decline_close_grip_bench_to_skull_crusher,
            "Press declinado cerrado a press francés"
        ),
        TranslationEntry(
            "Decline Crunch",
            R.string.exercise_decline_crunch,
            "Crunch declinado"
        ),
        TranslationEntry(
            "Decline Dumbbell Flyes",
            R.string.exercise_decline_dumbbell_flyes,
            "Aperturas declinadas con mancuernas"
        ),
        TranslationEntry(
            "Decline Dumbbell Triceps Extension",
            R.string.exercise_decline_dumbbell_triceps_extension,
            "Extensión declinada de tríceps con mancuernas"
        ),
        TranslationEntry(
            "Decline EZ Bar Triceps Extension",
            R.string.exercise_decline_ez_bar_triceps_extension,
            "Extensión declinada tríceps con barra EZ"
        ),
        TranslationEntry(
            "Decline Oblique Crunch",
            R.string.exercise_decline_oblique_crunch,
            "Crunch declinado para oblicuos"
        ),
        TranslationEntry(
            "Decline Push-Up",
            R.string.exercise_decline_push_up,
            "Flexiones declinadas"
        ),
        TranslationEntry(
            "Decline Reverse Crunch",
            R.string.exercise_decline_reverse_crunch,
            "Crunch inverso declinado"
        ),
        TranslationEntry(
            "Decline Smith Press",
            R.string.exercise_decline_smith_press,
            "Press declinado en Smith"
        ),
        TranslationEntry(
            "Deep breathing (standing or seated)",
            R.string.exercise_deep_breathing_standing_or_seated,
            "Respiración profunda (de pie o sentado)"
        ),
        TranslationEntry(
            "Deficit Deadlift",
            R.string.exercise_deficit_deadlift,
            "Peso muerto con déficit"
        ),
        TranslationEntry(
            "Deficit Push ups",
            R.string.exercise_deficit_push_ups,
            "Flexiones con déficit"
        ),
        TranslationEntry(
            "Degrees Side Bend",
            R.string.exercise_degrees_side_bend,
            "Flexión lateral de tronco"
        ),
        TranslationEntry(
            "Delt Stretch",
            R.string.exercise_delt_stretch,
            "Estiramiento de deltoides"
        ),
        TranslationEntry(
            "Depth Jump Leap",
            R.string.exercise_depth_jump_leap,
            "Salto de profundidad a longitud"
        ),
        TranslationEntry(
            "Diagonal Shoulder Press",
            R.string.exercise_diagonal_shoulder_press,
            "Press diagonal de hombros"
        ),
        TranslationEntry(
            "Diamond push ups",
            R.string.exercise_diamond_push_ups,
            "Flexiones diamante"
        ),
        TranslationEntry(
            "Diaphragmatic Breathing",
            R.string.exercise_diaphragmatic_breathing,
            "Respiración diafragmática"
        ),
        TranslationEntry(
            "Dips",
            R.string.exercise_dips,
            "Fondos"
        ),
        TranslationEntry(
            "Dips - Chest Version",
            R.string.exercise_dips_chest_version,
            "Fondos - Versión para pecho"
        ),
        TranslationEntry(
            "Dips - Triceps Version",
            R.string.exercise_dips_triceps_version,
            "Fondos - Versión para tríceps"
        ),
        TranslationEntry(
            "Dips Between Two Benches",
            R.string.exercise_dips_between_two_benches,
            "Fondos entre dos bancos"
        ),
        TranslationEntry(
            "Donkey Calf Raises",
            R.string.exercise_donkey_calf_raises,
            "Elevación de talones estilo burro"
        ),
        TranslationEntry(
            "Doorway Pectoral Stretch",
            R.string.exercise_doorway_pectoral_stretch,
            "Estiramiento pectoral en puerta"
        ),
        TranslationEntry(
            "Double Kettlebell Alternating Hang Clean",
            R.string.exercise_double_kettlebell_alternating_hang_clean,
            "Hang clean alterno con dos kettlebells"
        ),
        TranslationEntry(
            "Double Kettlebell Jerk",
            R.string.exercise_double_kettlebell_jerk,
            "Envión con dos kettlebells"
        ),
        TranslationEntry(
            "Double Kettlebell Push Press",
            R.string.exercise_double_kettlebell_push_press,
            "Push press con dos kettlebells"
        ),
        TranslationEntry(
            "Double Kettlebell Snatch",
            R.string.exercise_double_kettlebell_snatch,
            "Arrancada con dos kettlebells"
        ),
        TranslationEntry(
            "Double Kettlebell Windmill",
            R.string.exercise_double_kettlebell_windmill,
            "Molino con dos kettlebells"
        ),
        TranslationEntry(
            "Double Leg Butt Kick",
            R.string.exercise_double_leg_butt_kick,
            "Talones a los glúteos a dos piernas"
        ),
        TranslationEntry(
            "Double Leg Calf Raise",
            R.string.exercise_double_leg_calf_raise,
            "Elevación de talones a dos piernas"
        ),
        TranslationEntry(
            "Double Under Jump Rope",
            R.string.exercise_double_under_jump_rope,
            "Saltos dobles con cuerda"
        ),
        TranslationEntry(
            "Double-Leg Abdominal Press",
            R.string.exercise_double_leg_abdominal_press,
            "Press abdominal a dos piernas"
        ),
        TranslationEntry(
            "Downward Facing Balance",
            R.string.exercise_downward_facing_balance,
            "Equilibrio boca abajo"
        ),
        TranslationEntry(
            "Downward Facing Dog",
            R.string.exercise_downward_facing_dog,
            "Perro boca abajo"
        ),
        TranslationEntry(
            "Downward Facing Dog Adhomukha Svanasana",
            R.string.exercise_downward_facing_dog_adhomukha_svanasana,
            "Perro boca abajo (Adho Mukha Svanasana)"
        ),
        TranslationEntry(
            "Drag Curl",
            R.string.exercise_drag_curl,
            "Drag curl"
        ),
        TranslationEntry(
            "Dragon squat",
            R.string.exercise_dragon_squat,
            "Sentadilla dragón"
        ),
        TranslationEntry(
            "Dragon-flag",
            R.string.exercise_dragon_flag,
            "Dragon flag"
        ),
        TranslationEntry(
            "Drop Push",
            R.string.exercise_drop_push,
            "Drop push"
        ),
        TranslationEntry(
            "Dumbbell 3D lunge warmup",
            R.string.exercise_dumbbell_3d_lunge_warmup,
            "Calentamiento de zancadas 3D con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell 45° Lateral Raises",
            R.string.exercise_dumbbell_45_lateral_raises,
            "Elevaciones laterales a 45° con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Alternate Bicep Curl",
            R.string.exercise_dumbbell_alternate_bicep_curl,
            "Curl alterno de bíceps con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Alternating Biceps Curls With Dumbbell",
            R.string.exercise_dumbbell_alternating_biceps_curls_with_dumbbell,
            "Curl de bíceps alterno con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Alternating dumbbell hammer curl",
            R.string.exercise_dumbbell_alternating_dumbbell_hammer_curl,
            "Curl martillo alterno con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Alternative DB Gorilla rows",
            R.string.exercise_dumbbell_alternative_db_gorilla_rows,
            "Remo gorila alterno con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Arnold Press II",
            R.string.exercise_dumbbell_arnold_press_ii,
            "Press Arnold II con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Arnold Shoulder Press",
            R.string.exercise_dumbbell_arnold_shoulder_press,
            "Press Arnold de hombros con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Around Pullover",
            R.string.exercise_dumbbell_around_pullover,
            "Pullover circular con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Axe Hold",
            R.string.exercise_dumbbell_axe_hold,
            "Sostenimiento en hacha con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Barbell Clean and press",
            R.string.exercise_dumbbell_barbell_clean_and_press,
            "Clean and press con mancuerna y barra"
        ),
        TranslationEntry(
            "Dumbbell Bear crawl pull through",
            R.string.exercise_dumbbell_bear_crawl_pull_through,
            "Paso del oso con arrastre de mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Bench Press",
            R.string.exercise_dumbbell_bench_press,
            "Press de banca con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bench Press with Neutral Grip",
            R.string.exercise_dumbbell_bench_press_with_neutral_grip,
            "Press banca con mancuernas agarre neutro"
        ),
        TranslationEntry(
            "Dumbbell Bench Seated Press",
            R.string.exercise_dumbbell_bench_seated_press,
            "Press sentado en banco con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent High Pulls",
            R.string.exercise_dumbbell_bent_high_pulls,
            "Tirones altos inclinados con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Dumbbell Rows (Single Arm)",
            R.string.exercise_dumbbell_bent_over_dumbbell_rows_single_arm,
            "Remo inclinado a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Dumbbell Rows (Two Arms)",
            R.string.exercise_dumbbell_bent_over_dumbbell_rows_two_arms,
            "Remo inclinado a dos brazos con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Face Pull",
            R.string.exercise_dumbbell_bent_over_face_pull,
            "Face pull inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Row",
            R.string.exercise_dumbbell_bent_over_row,
            "Remo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent-Over Dumbbell Triceps Extension",
            R.string.exercise_dumbbell_bent_over_dumbbell_triceps_extension,
            "Patada de tríceps inclinada con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Bent-over Lateral Raises",
            R.string.exercise_dumbbell_bent_over_lateral_raises,
            "Elevaciones laterales inclinadas mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bent-Over Reverse Fly",
            R.string.exercise_dumbbell_bent_over_reverse_fly,
            "Aperturas posteriores inclinadas mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bicep Curl",
            R.string.exercise_dumbbell_bicep_curl,
            "Curl de bíceps con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell bicep curl to press",
            R.string.exercise_dumbbell_bicep_curl_to_press,
            "Curl de bíceps a press con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Biceps Curls With Dumbbell",
            R.string.exercise_dumbbell_biceps_curls_with_dumbbell,
            "Curl de bíceps con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bizeps Curls Trifecta",
            R.string.exercise_dumbbell_bizeps_curls_trifecta,
            "Curl de bíceps trifecta con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Blaze",
            R.string.exercise_dumbbell_blaze,
            "Blaze con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bradford press",
            R.string.exercise_dumbbell_bradford_press,
            "Press Bradford con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bulgarian Split Squat",
            R.string.exercise_dumbbell_bulgarian_split_squat,
            "Sentadilla búlgara con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bulgarian Squat with Dumbbells",
            R.string.exercise_dumbbell_bulgarian_squat_with_dumbbells,
            "Sentadilla búlgara con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Calf Raise",
            R.string.exercise_dumbbell_calf_raise,
            "Elevación de talones con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Cheat Curl",
            R.string.exercise_dumbbell_cheat_curl,
            "Curl tramposo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Chest-Supported Rear Delt Raise",
            R.string.exercise_dumbbell_chest_supported_rear_delt_raise,
            "Elevaciones posteriores pecho apoyado mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Clean",
            R.string.exercise_dumbbell_clean,
            "Cargada con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell close grip bench press",
            R.string.exercise_dumbbell_close_grip_bench_press,
            "Press banca agarre cerrado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Concentration Curl",
            R.string.exercise_dumbbell_concentration_curl,
            "Curl concentrado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Cross-Bench Dumbbell Pullovers",
            R.string.exercise_dumbbell_cross_bench_dumbbell_pullovers,
            "Pullover cruzado en banco con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Crunches",
            R.string.exercise_dumbbell_crunches,
            "Crunches con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Curl",
            R.string.exercise_dumbbell_curl,
            "Curl con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Curl - With Shoulder Elevated",
            R.string.exercise_dumbbell_curl_with_shoulder_elevated,
            "Curl con mancuerna con hombro elevado"
        ),
        TranslationEntry(
            "Dumbbell Curtsey Lunge",
            R.string.exercise_dumbbell_curtsey_lunge,
            "Zancada cruzada con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell DB Underhand bench press",
            R.string.exercise_dumbbell_db_underhand_bench_press,
            "Press de banca supino con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell DB Upper Chest Variation",
            R.string.exercise_dumbbell_db_upper_chest_variation,
            "Variación de pecho superior con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Deadlift",
            R.string.exercise_dumbbell_deadlift,
            "Peso muerto con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Decline Bench Press",
            R.string.exercise_dumbbell_decline_bench_press,
            "Press de banca declinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Decline Fly",
            R.string.exercise_dumbbell_decline_fly,
            "Aperturas declinadas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Devil’s Press",
            R.string.exercise_dumbbell_devils_press,
            "Devil's press con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell donkey kick",
            R.string.exercise_dumbbell_donkey_kick,
            "Patada de burro con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell drag curls",
            R.string.exercise_dumbbell_drag_curls,
            "Drag curl con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Drop Curl",
            R.string.exercise_dumbbell_drop_curl,
            "Drop curl con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Elbows Tucked DB Bench Press",
            R.string.exercise_dumbbell_elbows_tucked_db_bench_press,
            "Press de banca codos pegados con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell farmer's carry",
            R.string.exercise_dumbbell_farmers_carry,
            "Paseo del granjero con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Floor Press",
            R.string.exercise_dumbbell_floor_press,
            "Press de suelo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Fly",
            R.string.exercise_dumbbell_fly,
            "Aperturas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Fly With Dumbbells, Decline Bench",
            R.string.exercise_dumbbell_fly_with_dumbbells_decline_bench,
            "Aperturas declinadas en banco con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Forearm Curls (underhand grip)",
            R.string.exercise_dumbbell_forearm_curls_underhand_grip,
            "Curl de antebrazo supino con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Frog Hip Thrust",
            R.string.exercise_dumbbell_frog_hip_thrust,
            "Hip thrust en rana con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Frog Press",
            R.string.exercise_dumbbell_frog_press,
            "Press rana con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Front Raise",
            R.string.exercise_dumbbell_front_raise,
            "Elevaciones frontales con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Front Squat",
            R.string.exercise_dumbbell_front_squat,
            "Sentadilla frontal con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Glute Bridge Single-Arm Press",
            R.string.exercise_dumbbell_glute_bridge_single_arm_press,
            "Puente de glúteos con press a un brazo"
        ),
        TranslationEntry(
            "Dumbbell Goblet Squat",
            R.string.exercise_dumbbell_goblet_squat,
            "Sentadilla copa con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Hang Power Cleans",
            R.string.exercise_dumbbell_hang_power_cleans,
            "Cargada colgada de potencia con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Helms Row",
            R.string.exercise_dumbbell_helms_row,
            "Remo Helms con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Hex Press",
            R.string.exercise_dumbbell_hex_press,
            "Hex press con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Hip Thrust",
            R.string.exercise_dumbbell_hip_thrust,
            "Hip thrust con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Bench Press",
            R.string.exercise_dumbbell_incline_bench_press,
            "Press de banca inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Bench Reverse Fly",
            R.string.exercise_dumbbell_incline_bench_reverse_fly,
            "Aperturas inversas banco inclinado mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Biceps Curl",
            R.string.exercise_dumbbell_incline_biceps_curl,
            "Curl de bíceps inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Chest-Supported Dumbbell Row",
            R.string.exercise_dumbbell_incline_chest_supported_dumbbell_row,
            "Remo inclinado pecho apoyado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Dumbbell Row",
            R.string.exercise_dumbbell_incline_dumbbell_row,
            "Remo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Fly",
            R.string.exercise_dumbbell_incline_fly,
            "Aperturas inclinadas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Hammer Curl",
            R.string.exercise_dumbbell_incline_hammer_curl,
            "Curl martillo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline OHP DB",
            R.string.exercise_dumbbell_incline_ohp_db,
            "Press de hombros inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Rear Fly",
            R.string.exercise_dumbbell_incline_rear_fly,
            "Elevaciones posteriores inclinadas mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Row",
            R.string.exercise_dumbbell_incline_row,
            "Remo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Shoulder Raise",
            R.string.exercise_dumbbell_incline_shoulder_raise,
            "Elevación de hombros inclinada con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Static Hold",
            R.string.exercise_dumbbell_incline_static_hold,
            "Sostenimiento estático inclinado mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Y Raise",
            R.string.exercise_dumbbell_incline_y_raise,
            "Elevaciones en Y inclinadas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Ipsilateral Single Leg Stiff Leg Deadlift",
            R.string.exercise_dumbbell_ipsilateral_single_leg_stiff_leg_deadlift,
            "Peso muerto rígido ipsilateral a una pierna"
        ),
        TranslationEntry(
            "Dumbbell Kettlebell One Legged Deadlift",
            R.string.exercise_dumbbell_kettlebell_one_legged_deadlift,
            "Peso muerto a una pierna con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Kickback",
            R.string.exercise_dumbbell_kickback,
            "Patada de tríceps con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Kong Curl",
            R.string.exercise_dumbbell_kong_curl,
            "Kong curl con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Kreis Press DB",
            R.string.exercise_dumbbell_kreis_press_db,
            "Press circular con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lat Pull DB",
            R.string.exercise_dumbbell_lat_pull_db,
            "Jalón dorsal con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lateral Raise Isometric",
            R.string.exercise_dumbbell_lateral_raise_isometric,
            "Elevaciones laterales isométricas mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lateral-to-Front Raises",
            R.string.exercise_dumbbell_lateral_to_front_raises,
            "Elevaciones laterales a frontales mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lento Avanti Seduto",
            R.string.exercise_dumbbell_lento_avanti_seduto,
            "Press militar sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lunge",
            R.string.exercise_dumbbell_lunge,
            "Zancada con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lunges Standing",
            R.string.exercise_dumbbell_lunges_standing,
            "Zancadas de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying Dumbbell Curls",
            R.string.exercise_dumbbell_lying_dumbbell_curls,
            "Curl acostado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying Dumbbell Row SS Seated Shrug",
            R.string.exercise_dumbbell_lying_dumbbell_row_ss_seated_shrug,
            "Remo acostado y encogimiento sentado"
        ),
        TranslationEntry(
            "Dumbbell Lying Neutral Grip Row on Rack",
            R.string.exercise_dumbbell_lying_neutral_grip_row_on_rack,
            "Remo acostado agarre neutro en rack"
        ),
        TranslationEntry(
            "Dumbbell Lying on Floor Chest Press",
            R.string.exercise_dumbbell_lying_on_floor_chest_press,
            "Press de pecho en suelo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying One-Arm Rear Lateral Raise",
            R.string.exercise_dumbbell_lying_one_arm_rear_lateral_raise,
            "Elevación posterior acostado a un brazo"
        ),
        TranslationEntry(
            "Dumbbell Lying Pronation",
            R.string.exercise_dumbbell_lying_pronation,
            "Pronación acostado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Lying Rear Lateral Raise",
            R.string.exercise_dumbbell_lying_rear_lateral_raise,
            "Elevaciones posteriores acostado mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying Rotator Cuff Exercise",
            R.string.exercise_dumbbell_lying_rotator_cuff_exercise,
            "Rotadores de hombro acostado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Lying Seal Row on Rack",
            R.string.exercise_dumbbell_lying_seal_row_on_rack,
            "Remo foca acostado en rack con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying Supination",
            R.string.exercise_dumbbell_lying_supination,
            "Supinación acostado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Lying Triceps Kickback",
            R.string.exercise_dumbbell_lying_triceps_kickback,
            "Patada de tríceps acostado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell No Leg Drive Dumbbell Chest Press",
            R.string.exercise_dumbbell_no_leg_drive_dumbbell_chest_press,
            "Press de pecho sin impulso con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell One Arm Bent Over Row",
            R.string.exercise_dumbbell_one_arm_bent_over_row,
            "Remo inclinado a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One Arm Snatch",
            R.string.exercise_dumbbell_one_arm_snatch,
            "Arrancada a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One Arm Triceps Extension on Bench",
            R.string.exercise_dumbbell_one_arm_triceps_extension_on_bench,
            "Extensión tríceps a un brazo en banco"
        ),
        TranslationEntry(
            "Dumbbell One-Arm Heavy Row",
            R.string.exercise_dumbbell_one_arm_heavy_row,
            "Remo pesado a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One-Arm Shoulder Press",
            R.string.exercise_dumbbell_one_arm_shoulder_press,
            "Press de hombros a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One-Arm Triceps Extension",
            R.string.exercise_dumbbell_one_arm_triceps_extension,
            "Extensión de tríceps a un brazo mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One-Arm Upright Row",
            R.string.exercise_dumbbell_one_arm_upright_row,
            "Remo al mentón a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell or Machine Calf Raise",
            R.string.exercise_dumbbell_or_machine_calf_raise,
            "Elevación de talones con mancuerna o máquina"
        ),
        TranslationEntry(
            "Dumbbell Overhead Lunge",
            R.string.exercise_dumbbell_overhead_lunge,
            "Zancada sobre la cabeza con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Patadas traseras",
            R.string.exercise_dumbbell_patadas_traseras,
            "Patadas traseras con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Preacher Curl",
            R.string.exercise_dumbbell_preacher_curl,
            "Curl predicador con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Preacher Curl - Externally Rotated",
            R.string.exercise_dumbbell_preacher_curl_externally_rotated,
            "Curl predicador rotación externa mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Preacher Curl - Internally Rotated",
            R.string.exercise_dumbbell_preacher_curl_internally_rotated,
            "Curl predicador rotación interna mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Preacher Curl Isometric",
            R.string.exercise_dumbbell_preacher_curl_isometric,
            "Curl predicador isométrico con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Prone Incline Curl",
            R.string.exercise_dumbbell_prone_incline_curl,
            "Curl inclinado en prono con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Pullover",
            R.string.exercise_dumbbell_pullover,
            "Pullover con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Push-Up",
            R.string.exercise_dumbbell_push_up,
            "Flexiones sobre mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Raise",
            R.string.exercise_dumbbell_raise,
            "Elevaciones con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rear Delt Fly",
            R.string.exercise_dumbbell_rear_delt_fly,
            "Aperturas posteriores con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rear Delt Raises",
            R.string.exercise_dumbbell_rear_delt_raises,
            "Elevaciones posteriores con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rear Lateral Raise",
            R.string.exercise_dumbbell_rear_lateral_raise,
            "Elevaciones laterales posteriores mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rear Lunge",
            R.string.exercise_dumbbell_rear_lunge,
            "Zancada hacia atrás con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Renegade Row",
            R.string.exercise_dumbbell_renegade_row,
            "Remo renegado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Reverse Fly",
            R.string.exercise_dumbbell_reverse_fly,
            "Aperturas inversas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Reverse Grip Biceps Curl",
            R.string.exercise_dumbbell_reverse_grip_biceps_curl,
            "Curl bíceps agarre inverso con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Reverse Wrist Curl",
            R.string.exercise_dumbbell_reverse_wrist_curl,
            "Curl de muñeca inverso con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell roman chair RDLS",
            R.string.exercise_dumbbell_roman_chair_rdls,
            "Peso muerto rumano en silla romana"
        ),
        TranslationEntry(
            "Dumbbell Romanian Deadlift",
            R.string.exercise_dumbbell_romanian_deadlift,
            "Peso muerto rumano con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Russian Twist",
            R.string.exercise_dumbbell_russian_twist,
            "Giro ruso con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Scaption",
            R.string.exercise_dumbbell_scaption,
            "Escaption con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Arnold Press",
            R.string.exercise_dumbbell_seated_arnold_press,
            "Press Arnold sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Box Jump",
            R.string.exercise_dumbbell_seated_box_jump,
            "Salto al cajón sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Dumbbell Curls",
            R.string.exercise_dumbbell_seated_dumbbell_curls,
            "Curl sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Dumbbell Side Lateral",
            R.string.exercise_dumbbell_seated_dumbbell_side_lateral,
            "Elevaciones laterales sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Lateral Raise",
            R.string.exercise_dumbbell_seated_lateral_raise,
            "Elevaciones laterales sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated One-Leg Calf Raise",
            R.string.exercise_dumbbell_seated_one_leg_calf_raise,
            "Elevación talón sentado una pierna mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Seated Palms-Up Wrist Curl",
            R.string.exercise_dumbbell_seated_palms_up_wrist_curl,
            "Curl de muñeca sentado palmas arriba"
        ),
        TranslationEntry(
            "Dumbbell Seated Preacher Curl",
            R.string.exercise_dumbbell_seated_preacher_curl,
            "Curl predicador sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated rear delt rise",
            R.string.exercise_dumbbell_seated_rear_delt_rise,
            "Elevaciones posteriores sentado mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Reverse Grip Concentration Curl",
            R.string.exercise_dumbbell_seated_reverse_grip_concentration_curl,
            "Curl concentrado inverso sentado mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Seated Triceps Press",
            R.string.exercise_dumbbell_seated_triceps_press,
            "Press de tríceps sentado con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Seated W Curl",
            R.string.exercise_dumbbell_seated_w_curl,
            "Curl en W sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Shoulder Dumbbell Pendular Exercise",
            R.string.exercise_dumbbell_shoulder_dumbbell_pendular_exercise,
            "Ejercicio pendular de hombro con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Shoulder External Rotation with Dumbbell",
            R.string.exercise_dumbbell_shoulder_external_rotation_with_dumbbell,
            "Rotación externa de hombro con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Shoulder Press",
            R.string.exercise_dumbbell_shoulder_press,
            "Press de hombros con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Shoulder Raise (Dumbbell)",
            R.string.exercise_dumbbell_shoulder_raise_dumbbell,
            "Elevación de hombros con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Shoulder Raise Side and Front DB",
            R.string.exercise_dumbbell_shoulder_raise_side_and_front_db,
            "Elevación lateral y frontal de hombro"
        ),
        TranslationEntry(
            "Dumbbell Shoulder Rotations",
            R.string.exercise_dumbbell_shoulder_rotations,
            "Rotaciones de hombro con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Shrug",
            R.string.exercise_dumbbell_shrug,
            "Encogimientos con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Side Bend",
            R.string.exercise_dumbbell_side_bend,
            "Flexión lateral con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Side Bends on Machine",
            R.string.exercise_dumbbell_side_bends_on_machine,
            "Flexión lateral en máquina con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Side Dumbbell Trunk Flexion",
            R.string.exercise_dumbbell_side_dumbbell_trunk_flexion,
            "Flexión lateral de tronco con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Side Squat",
            R.string.exercise_dumbbell_side_squat,
            "Sentadilla lateral con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Calf Raise",
            R.string.exercise_dumbbell_single_leg_calf_raise,
            "Elevación de talón a una pierna mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Hip Thrust",
            R.string.exercise_dumbbell_single_leg_hip_thrust,
            "Hip thrust a una pierna con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Romanian Deadlift Leg Raise",
            R.string.exercise_dumbbell_single_leg_romanian_deadlift_leg_raise,
            "Peso muerto rumano una pierna con elevación"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Split Squat",
            R.string.exercise_dumbbell_single_leg_split_squat,
            "Sentadilla búlgara con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Single-arm Preacher Curl",
            R.string.exercise_dumbbell_single_arm_preacher_curl,
            "Curl predicador a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Sit-Up",
            R.string.exercise_dumbbell_sit_up,
            "Sit-up con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Snatch",
            R.string.exercise_dumbbell_snatch,
            "Arrancada con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Split Squat",
            R.string.exercise_dumbbell_split_squat,
            "Sentadilla dividida con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Squat",
            R.string.exercise_dumbbell_squat,
            "Sentadilla con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Squat To A Bench",
            R.string.exercise_dumbbell_squat_to_a_bench,
            "Sentadilla a un banco con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Squeeze Bench Press",
            R.string.exercise_dumbbell_squeeze_bench_press,
            "Squeeze press de banca con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Bicep Curl",
            R.string.exercise_dumbbell_standing_bicep_curl,
            "Curl de bíceps de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Calf Raise",
            R.string.exercise_dumbbell_standing_calf_raise,
            "Elevación de talones de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Lateral Raise",
            R.string.exercise_dumbbell_standing_lateral_raise,
            "Elevaciones laterales de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Overhead Press",
            R.string.exercise_dumbbell_standing_overhead_press,
            "Press militar de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Step-Up",
            R.string.exercise_dumbbell_step_up,
            "Subidas al cajón con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Suitcase Carry",
            R.string.exercise_dumbbell_suitcase_carry,
            "Paseo de la maleta con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell sumo deadlift",
            R.string.exercise_dumbbell_sumo_deadlift,
            "Peso muerto sumo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Sumo Squat",
            R.string.exercise_dumbbell_sumo_squat,
            "Sentadilla sumo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Supine Hip Abduction",
            R.string.exercise_dumbbell_supine_hip_abduction,
            "Abducción de cadera supina con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Tate Press",
            R.string.exercise_dumbbell_tate_press,
            "Tate press con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Thruster",
            R.string.exercise_dumbbell_thruster,
            "Thruster con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Tricep Extension -Pronated Grip",
            R.string.exercise_dumbbell_tricep_extension_pronated_grip,
            "Extensión de tríceps prono con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Triceps Extension",
            R.string.exercise_dumbbell_triceps_extension,
            "Extensión de tríceps con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Triceps Overhead (Dumbbell)",
            R.string.exercise_dumbbell_triceps_overhead_dumbbell,
            "Extensión de tríceps sobre cabeza mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Turkish Get-Up",
            R.string.exercise_dumbbell_turkish_get_up,
            "Levantamiento turco con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Underhand Dead Row",
            R.string.exercise_dumbbell_underhand_dead_row,
            "Remo muerto supino con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Upright Row",
            R.string.exercise_dumbbell_upright_row,
            "Remo al mentón con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Waiter Biceps Curl",
            R.string.exercise_dumbbell_waiter_biceps_curl,
            "Curl camarero con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Walking Lunge",
            R.string.exercise_dumbbell_walking_lunge,
            "Zancadas caminando con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Weighted Crunch",
            R.string.exercise_dumbbell_weighted_crunch,
            "Crunch con carga con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell wide bicep curls",
            R.string.exercise_dumbbell_wide_bicep_curls,
            "Curl de bíceps ancho con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Wrist curl, dumbbells",
            R.string.exercise_dumbbell_wrist_curl_dumbbells,
            "Curl de muñeca con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell YTWL Exercise",
            R.string.exercise_dumbbell_ytwl_exercise,
            "Ejercicio YTWL con mancuernas"
        ),
        TranslationEntry(
            "Dumbbells on Scott Machine",
            R.string.exercise_dumbbells_on_scott_machine,
            "Curl Scott con mancuernas"
        ),
        TranslationEntry(
            "Dynamic Back Stretch",
            R.string.exercise_dynamic_back_stretch,
            "Estiramiento dinámico de espalda"
        ),
        TranslationEntry(
            "Dynamic Chest Stretch",
            R.string.exercise_dynamic_chest_stretch,
            "Estiramiento dinámico de pecho"
        ),
        TranslationEntry(
            "Dynamic Planche",
            R.string.exercise_dynamic_planche,
            "Plancha dinámica"
        ),
        TranslationEntry(
            "Dynamic side plank",
            R.string.exercise_dynamic_side_plank,
            "Plancha lateral dinámica"
        ),
        TranslationEntry(
            "Easy Continuous Swim (Freestyle)",
            R.string.exercise_easy_continuous_swim_freestyle,
            "Nado continuo suave (estilo libre)"
        ),
        TranslationEntry(
            "Elbow Circles",
            R.string.exercise_elbow_circles,
            "Círculos de codos"
        ),
        TranslationEntry(
            "Elbow Lever",
            R.string.exercise_elbow_lever,
            "Palanca de codos"
        ),
        TranslationEntry(
            "Elbow to Knee",
            R.string.exercise_elbow_to_knee,
            "Codo a rodilla"
        ),
        TranslationEntry(
            "Elbow Up and Down Dynamic Plank",
            R.string.exercise_elbow_up_and_down_dynamic_plank,
            "Plancha dinámica subiendo y bajando codos"
        ),
        TranslationEntry(
            "Elbows Back",
            R.string.exercise_elbows_back,
            "Codos atrás"
        ),
        TranslationEntry(
            "Elephant Walks",
            R.string.exercise_elephant_walks,
            "Paso de elefante"
        ),
        TranslationEntry(
            "Elevated Back Lunge",
            R.string.exercise_elevated_back_lunge,
            "Zancada hacia atrás elevada"
        ),
        TranslationEntry(
            "Elevated Cable Rows",
            R.string.exercise_elevated_cable_rows,
            "Remo elevado en polea"
        ),
        TranslationEntry(
            "Elevated Hamstring Stretch",
            R.string.exercise_elevated_hamstring_stretch,
            "Estiramiento elevado de isquiotibiales"
        ),
        TranslationEntry(
            "Elevated prayer stretch",
            R.string.exercise_elevated_prayer_stretch,
            "Estiramiento de la oración elevado"
        ),
        TranslationEntry(
            "Elliptical",
            R.string.exercise_elliptical,
            "Elíptica"
        ),
        TranslationEntry(
            "Elliptical Machine Walk",
            R.string.exercise_elliptical_machine_walk,
            "Caminata en máquina elíptica"
        ),
        TranslationEntry(
            "Elliptical Trainer",
            R.string.exercise_elliptical_trainer,
            "Entrenador elíptico"
        ),
        TranslationEntry(
            "Exercise Ball Crunch",
            R.string.exercise_exercise_ball_crunch,
            "Crunch en pelota de ejercicio"
        ),
        TranslationEntry(
            "Exercise Ball Pull-In",
            R.string.exercise_exercise_ball_pull_in,
            "Encogimiento en pelota de ejercicio"
        ),
        TranslationEntry(
            "Exercise Band Dorsiflexion",
            R.string.exercise_exercise_band_dorsiflexion,
            "Dorsiflexión con banda elástica"
        ),
        TranslationEntry(
            "Exercise Band Plantarflexion",
            R.string.exercise_exercise_band_plantarflexion,
            "Plantiflexión con banda elástica"
        ),
        TranslationEntry(
            "Explosive Push Ups",
            R.string.exercise_explosive_push_ups,
            "Flexiones explosivas"
        ),
        TranslationEntry(
            "Extended Arm Child's Pose",
            R.string.exercise_extended_arm_childs_pose,
            "Postura del niño con brazos extendidos"
        ),
        TranslationEntry(
            "Extended Range One-Arm Kettlebell Floor Press",
            R.string.exercise_extended_range_one_arm_kettlebell_floor_press,
            "Press de suelo a un brazo rango extendido kettlebell"
        ),
        TranslationEntry(
            "External Rotation",
            R.string.exercise_external_rotation,
            "Rotación externa"
        ),
        TranslationEntry(
            "External Rotation with Band",
            R.string.exercise_external_rotation_with_band,
            "Rotación externa con banda"
        ),
        TranslationEntry(
            "External Rotation with Cable",
            R.string.exercise_external_rotation_with_cable,
            "Rotación externa en polea"
        ),
        TranslationEntry(
            "Extreme Lat Stretch",
            R.string.exercise_extreme_lat_stretch,
            "Estiramiento extremo de dorsales"
        ),
        TranslationEntry(
            "Extreme Pec Stretch",
            R.string.exercise_extreme_pec_stretch,
            "Estiramiento extremo de pectorales"
        ),
        TranslationEntry(
            "Extreme Shoulder Stretch",
            R.string.exercise_extreme_shoulder_stretch,
            "Estiramiento extremo de hombros"
        ),
        TranslationEntry(
            "Ez Barbell Standing Back Wrist Curl",
            R.string.exercise_ez_barbell_standing_back_wrist_curl,
            "Curl de muñeca de pie tras nuca barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Standing Wide Grip Biceps Curl",
            R.string.exercise_ez_barbell_standing_wide_grip_biceps_curl,
            "Curl de bíceps de pie agarre ancho barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Biceps Curls With SZ-bar",
            R.string.exercise_ez_bar_biceps_curls_with_sz_bar,
            "Curl de bíceps con barra SZ"
        ),
        TranslationEntry(
            "EZ-Bar Curl",
            R.string.exercise_ez_bar_curl,
            "Curl con barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Floor Skull Crusher",
            R.string.exercise_ez_bar_floor_skull_crusher,
            "Press francés de suelo con barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Military Press",
            R.string.exercise_ez_bar_military_press,
            "Press militar con barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Overhead Triceps Extension",
            R.string.exercise_ez_bar_overhead_triceps_extension,
            "Extensión de tríceps sobre cabeza barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Preacher Curls",
            R.string.exercise_ez_bar_preacher_curls,
            "Curl predicador con barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Reverse Bar Curl",
            R.string.exercise_ez_bar_reverse_bar_curl,
            "Curl inverso con barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Reverse Preacher Curl (Close Grip)",
            R.string.exercise_ez_bar_reverse_preacher_curl_close_grip,
            "Curl predicador inverso cerrado barra EZ"
        ),
        TranslationEntry(
            "EZ-Bar Skullcrusher SZ-bar",
            R.string.exercise_ez_bar_skullcrusher_sz_bar,
            "Press francés con barra SZ"
        ),
        TranslationEntry(
            "EZ-Bar Upright Row, SZ-bar",
            R.string.exercise_ez_bar_upright_row_sz_bar,
            "Remo al mentón con barra SZ"
        ),
        TranslationEntry(
            "Face pulls with yellow/green band",
            R.string.exercise_face_pulls_with_yellow_green_band,
            "Face pull con banda elástica"
        ),
        TranslationEntry(
            "Farmers Walk",
            R.string.exercise_farmers_walk,
            "Paseo del granjero"
        ),
        TranslationEntry(
            "Fast Skipping",
            R.string.exercise_fast_skipping,
            "Salto de cuerda rápido"
        ),
        TranslationEntry(
            "Finger Curls",
            R.string.exercise_finger_curls,
            "Curl de dedos con barra"
        ),
        TranslationEntry(
            "Finger Pushup",
            R.string.exercise_finger_pushup,
            "Flexiones sobre los dedos"
        ),
        TranslationEntry(
            "Fingerboard 20 mm edge",
            R.string.exercise_fingerboard_20_mm_edge,
            "Suspensiones en regleta de 20 mm"
        ),
        TranslationEntry(
            "Fire Hydrant Circles",
            R.string.exercise_fire_hydrant_circles,
            "Círculos boca abajo en hidrante"
        ),
        TranslationEntry(
            "Flat Bench Cable Flyes",
            R.string.exercise_flat_bench_cable_flyes,
            "Aperturas en banco plano en polea"
        ),
        TranslationEntry(
            "Flat Bench Leg Pull-In",
            R.string.exercise_flat_bench_leg_pull_in,
            "Encogimiento de piernas en banco plano"
        ),
        TranslationEntry(
            "Flat Bench Lying Leg Raise",
            R.string.exercise_flat_bench_lying_leg_raise,
            "Elevación de piernas en banco plano"
        ),
        TranslationEntry(
            "Flat Machine Press",
            R.string.exercise_flat_machine_press,
            "Press plano en máquina"
        ),
        TranslationEntry(
            "FlexBar Tyler Twist",
            R.string.exercise_flexbar_tyler_twist,
            "Giro Tyler con FlexBar"
        ),
        TranslationEntry(
            "FlexBar Wrist Extension",
            R.string.exercise_flexbar_wrist_extension,
            "Extensión de muñeca con FlexBar"
        ),
        TranslationEntry(
            "FlexBar Wrist Flexion",
            R.string.exercise_flexbar_wrist_flexion,
            "Flexión de muñeca con FlexBar"
        ),
        TranslationEntry(
            "Flexor Incline Dumbbell Curls",
            R.string.exercise_flexor_incline_dumbbell_curls,
            "Curl flexor inclinado con mancuernas"
        ),
        TranslationEntry(
            "Floor dips",
            R.string.exercise_floor_dips,
            "Fondos en el suelo"
        ),
        TranslationEntry(
            "Floor Glider Hamstring Curls",
            R.string.exercise_floor_glider_hamstring_curls,
            "Curl de isquiotibiales con deslizador"
        ),
        TranslationEntry(
            "Floor Glute-Ham Raise",
            R.string.exercise_floor_glute_ham_raise,
            "Elevación glúteo-femoral en suelo"
        ),
        TranslationEntry(
            "Floor Press",
            R.string.exercise_floor_press,
            "Press de suelo con barra"
        ),
        TranslationEntry(
            "Floor Press with Chains",
            R.string.exercise_floor_press_with_chains,
            "Press de suelo con cadenas"
        ),
        TranslationEntry(
            "Flutter Kicks",
            R.string.exercise_flutter_kicks,
            "Patadas de tijera"
        ),
        TranslationEntry(
            "Foam Roll IT Band",
            R.string.exercise_foam_roll_it_band,
            "Rodillo de espuma en banda iliotibial"
        ),
        TranslationEntry(
            "Foam Roller Adductors",
            R.string.exercise_foam_roller_adductors,
            "Rodillo de espuma en aductores"
        ),
        TranslationEntry(
            "Foam Roller Anterior tibialis",
            R.string.exercise_foam_roller_anterior_tibialis,
            "Rodillo de espuma en tibial anterior"
        ),
        TranslationEntry(
            "Foam Roller Gluteus",
            R.string.exercise_foam_roller_gluteus,
            "Rodillo de espuma en glúteos"
        ),
        TranslationEntry(
            "Foam Roller Iliotibial band",
            R.string.exercise_foam_roller_iliotibial_band,
            "Rodillo de espuma en banda iliotibial"
        ),
        TranslationEntry(
            "Foam Roller quadriceps",
            R.string.exercise_foam_roller_quadriceps,
            "Rodillo de espuma en cuádriceps"
        ),
        TranslationEntry(
            "Foot-SMR",
            R.string.exercise_foot_smr,
            "Liberación miofascial del pie"
        ),
        TranslationEntry(
            "Forward arm circles",
            R.string.exercise_forward_arm_circles,
            "Círculos de brazos hacia adelante"
        ),
        TranslationEntry(
            "Forward Drag with Press",
            R.string.exercise_forward_drag_with_press,
            "Arrastre frontal con press"
        ),
        TranslationEntry(
            "Forward shoulder rotation",
            R.string.exercise_forward_shoulder_rotation,
            "Rotación frontal de hombros"
        ),
        TranslationEntry(
            "Frankenstein Squat",
            R.string.exercise_frankenstein_squat,
            "Sentadilla Frankenstein"
        ),
        TranslationEntry(
            "Freehand Jump Squat",
            R.string.exercise_freehand_jump_squat,
            "Sentadilla libre con salto"
        ),
        TranslationEntry(
            "Frog Hops",
            R.string.exercise_frog_hops,
            "Saltos de rana"
        ),
        TranslationEntry(
            "Frog Jump",
            R.string.exercise_frog_jump,
            "Salto de rana"
        ),
        TranslationEntry(
            "Frog Sit-Ups",
            R.string.exercise_frog_sit_ups,
            "Sit-ups en rana"
        ),
        TranslationEntry(
            "Frog stand",
            R.string.exercise_frog_stand,
            "Equilibrio en postura de rana"
        ),
        TranslationEntry(
            "Frog Stretch",
            R.string.exercise_frog_stretch,
            "Estiramiento de rana"
        ),
        TranslationEntry(
            "Front Barbell Squat To A Bench",
            R.string.exercise_front_barbell_squat_to_a_bench,
            "Sentadilla frontal con barra a un banco"
        ),
        TranslationEntry(
            "Front Box Jump",
            R.string.exercise_front_box_jump,
            "Salto frontal al cajón"
        ),
        TranslationEntry(
            "Front Cone Hops (or hurdle hops)",
            R.string.exercise_front_cone_hops_or_hurdle_hops,
            "Saltos frontales sobre conos o vallas"
        ),
        TranslationEntry(
            "Front Elbow Plank",
            R.string.exercise_front_elbow_plank,
            "Plancha frontal sobre codos"
        ),
        TranslationEntry(
            "Front Incline Dumbbell Raise",
            R.string.exercise_front_incline_dumbbell_raise,
            "Elevación frontal inclinada con mancuernas"
        ),
        TranslationEntry(
            "Front Leg Raises",
            R.string.exercise_front_leg_raises,
            "Elevaciones frontales de piernas"
        ),
        TranslationEntry(
            "Front Lever",
            R.string.exercise_front_lever,
            "Front lever"
        ),
        TranslationEntry(
            "Front Lever Hold",
            R.string.exercise_front_lever_hold,
            "Mantenimiento en front lever"
        ),
        TranslationEntry(
            "Front lever pull-up",
            R.string.exercise_front_lever_pull_up,
            "Dominada en front lever"
        ),
        TranslationEntry(
            "Front lever tuck",
            R.string.exercise_front_lever_tuck,
            "Front lever agrupado"
        ),
        TranslationEntry(
            "Front neck stretch",
            R.string.exercise_front_neck_stretch,
            "Estiramiento frontal de cuello"
        ),
        TranslationEntry(
            "Front Plank",
            R.string.exercise_front_plank,
            "Plancha frontal"
        ),
        TranslationEntry(
            "Front Plate Raise",
            R.string.exercise_front_plate_raise,
            "Elevación frontal con disco"
        ),
        TranslationEntry(
            "Front Raise And Pullover",
            R.string.exercise_front_raise_and_pullover,
            "Elevación frontal y pullover con barra"
        ),
        TranslationEntry(
            "Front Split Stretch",
            R.string.exercise_front_split_stretch,
            "Estiramiento de split frontal"
        ),
        TranslationEntry(
            "Front Squat (Clean Grip)",
            R.string.exercise_front_squat_clean_grip,
            "Sentadilla frontal con agarre de cargada"
        ),
        TranslationEntry(
            "Front Squats With Two Kettlebells",
            R.string.exercise_front_squats_with_two_kettlebells,
            "Sentadillas frontales con dos kettlebells"
        ),
        TranslationEntry(
            "Front Two-Dumbbell Raise",
            R.string.exercise_front_two_dumbbell_raise,
            "Elevación frontal con dos mancuernas"
        ),
        TranslationEntry(
            "Front Wood Chop",
            R.string.exercise_front_wood_chop,
            "Leñador frontal"
        ),
        TranslationEntry(
            "Full Range-Of-Motion Lat Pulldown",
            R.string.exercise_full_range_of_motion_lat_pulldown,
            "Jalón dorsal en polea rango completo"
        ),
        TranslationEntry(
            "Full Sit Outs",
            R.string.exercise_full_sit_outs,
            "Sit outs completos"
        ),
        TranslationEntry(
            "Gentle Reverse Kegels",
            R.string.exercise_gentle_reverse_kegels,
            "Kegels inversos suaves"
        ),
        TranslationEntry(
            "Gentle Thoracic Rotation",
            R.string.exercise_gentle_thoracic_rotation,
            "Rotación torácica suave"
        ),
        TranslationEntry(
            "Gentle Upward-Facing Dog / Cobra",
            R.string.exercise_gentle_upward_facing_dog_cobra,
            "Perro boca arriba suave o cobra"
        ),
        TranslationEntry(
            "Gironda Sternum Chins",
            R.string.exercise_gironda_sternum_chins,
            "Dominadas al esternón Gironda"
        ),
        TranslationEntry(
            "Glute Bridge",
            R.string.exercise_glute_bridge,
            "Puente de glúteos"
        ),
        TranslationEntry(
            "Glute Bridge From Bench",
            R.string.exercise_glute_bridge_from_bench,
            "Puente de glúteos desde banco"
        ),
        TranslationEntry(
            "Glute Drive",
            R.string.exercise_glute_drive,
            "Glute drive en máquina"
        ),
        TranslationEntry(
            "Glute Ham Raise",
            R.string.exercise_glute_ham_raise,
            "Elevación glúteo-femoral"
        ),
        TranslationEntry(
            "Glute Kickback",
            R.string.exercise_glute_kickback,
            "Patada de glúteos"
        ),
        TranslationEntry(
            "Glute Kickback (Machine)",
            R.string.exercise_glute_kickback_machine,
            "Patada de glúteos en máquina"
        ),
        TranslationEntry(
            "Glute Stretch",
            R.string.exercise_glute_stretch,
            "Estiramiento de glúteos"
        ),
        TranslationEntry(
            "Goblet Squat",
            R.string.exercise_goblet_squat,
            "Sentadilla copa con kettlebell"
        ),
        TranslationEntry(
            "Good Morning",
            R.string.exercise_good_morning,
            "Buenos días con barra"
        ),
        TranslationEntry(
            "Good Morning off Pins",
            R.string.exercise_good_morning_off_pins,
            "Buenos días desde soportes con barra"
        ),
        TranslationEntry(
            "Gorilla Chin/Crunch",
            R.string.exercise_gorilla_chin_crunch,
            "Dominada y crunch gorila"
        ),
        TranslationEntry(
            "Groin and Back Stretch",
            R.string.exercise_groin_and_back_stretch,
            "Estiramiento de ingle y espalda"
        ),
        TranslationEntry(
            "Groiners",
            R.string.exercise_groiners,
            "Groiners"
        ),
        TranslationEntry(
            "Guided or free meditation",
            R.string.exercise_guided_or_free_meditation,
            "Meditación guiada o libre"
        ),
        TranslationEntry(
            "Hack Squat",
            R.string.exercise_hack_squat,
            "Sentadilla hack en máquina"
        ),
        TranslationEntry(
            "Hammer Curls",
            R.string.exercise_hammer_curls,
            "Curl martillo con mancuernas"
        ),
        TranslationEntry(
            "Hammer Grip Incline DB Bench Press",
            R.string.exercise_hammer_grip_incline_db_bench_press,
            "Press inclinado agarre martillo con mancuernas"
        ),
        TranslationEntry(
            "Hammerstrength Decline Chest Press",
            R.string.exercise_hammerstrength_decline_chest_press,
            "Press declinado Hammer Strength"
        ),
        TranslationEntry(
            "Hamstring Bridge",
            R.string.exercise_hamstring_bridge,
            "Puente para isquiotibiales"
        ),
        TranslationEntry(
            "Hamstring Chokes",
            R.string.exercise_hamstring_chokes,
            "Contracciones de isquiotibiales"
        ),
        TranslationEntry(
            "Hamstring Foam roller",
            R.string.exercise_hamstring_foam_roller,
            "Rodillo de espuma en isquiotibiales"
        ),
        TranslationEntry(
            "Hamstring Kicks",
            R.string.exercise_hamstring_kicks,
            "Patadas para isquiotibiales"
        ),
        TranslationEntry(
            "Hamstring Stretch",
            R.string.exercise_hamstring_stretch,
            "Estiramiento de isquiotibiales"
        ),
        TranslationEntry(
            "Hamstring-SMR",
            R.string.exercise_hamstring_smr,
            "Liberación miofascial de isquiotibiales"
        ),
        TranslationEntry(
            "Hand Grip",
            R.string.exercise_hand_grip,
            "Apretón de manos con pinza"
        ),
        TranslationEntry(
            "Handstand",
            R.string.exercise_handstand,
            "Pino de manos"
        ),
        TranslationEntry(
            "Handstand Hold on Wall",
            R.string.exercise_handstand_hold_on_wall,
            "Mantenimiento en pino contra la pared"
        ),
        TranslationEntry(
            "Handstand Push-Ups",
            R.string.exercise_handstand_push_ups,
            "Flexiones en pino"
        ),
        TranslationEntry(
            "Hang Clean",
            R.string.exercise_hang_clean,
            "Cargada colgada con barra"
        ),
        TranslationEntry(
            "Hang Clean - Below the Knees",
            R.string.exercise_hang_clean_below_the_knees,
            "Cargada colgada por debajo de rodillas barra"
        ),
        TranslationEntry(
            "Hang Snatch",
            R.string.exercise_hang_snatch,
            "Arrancada colgada con barra"
        ),
        TranslationEntry(
            "Hang Snatch - Below Knees",
            R.string.exercise_hang_snatch_below_knees,
            "Arrancada colgada por debajo de rodillas barra"
        ),
        TranslationEntry(
            "Hanging Bar Good Morning",
            R.string.exercise_hanging_bar_good_morning,
            "Buenos días con barra colgada"
        ),
        TranslationEntry(
            "Hanging Front Lever Raise",
            R.string.exercise_hanging_front_lever_raise,
            "Elevaciones a front lever colgado"
        ),
        TranslationEntry(
            "Hanging Knee Raise",
            R.string.exercise_hanging_knee_raise,
            "Elevación de rodillas colgado"
        ),
        TranslationEntry(
            "Hanging Leg Hip Raise",
            R.string.exercise_hanging_leg_hip_raise,
            "Elevación de piernas y cadera colgado"
        ),
        TranslationEntry(
            "Hanging Pike",
            R.string.exercise_hanging_pike,
            "Pike colgado en barra"
        ),
        TranslationEntry(
            "Hanging Straight Leg Hip Raise",
            R.string.exercise_hanging_straight_leg_hip_raise,
            "Elevación de piernas rectas y cadera colgado"
        ),
        TranslationEntry(
            "Hanging Straight Leg Raise",
            R.string.exercise_hanging_straight_leg_raise,
            "Elevación de piernas rectas colgado"
        ),
        TranslationEntry(
            "Happy Baby",
            R.string.exercise_happy_baby,
            "Postura del bebé feliz"
        ),
        TranslationEntry(
            "Head tilts",
            R.string.exercise_head_tilts,
            "Inclinaciones de cabeza"
        ),
        TranslationEntry(
            "Head turns",
            R.string.exercise_head_turns,
            "Giros de cabeza"
        ),
        TranslationEntry(
            "Heaving Snatch Balance",
            R.string.exercise_heaving_snatch_balance,
            "Snatch balance con impulso con barra"
        ),
        TranslationEntry(
            "Heavy Bag Thrust",
            R.string.exercise_heavy_bag_thrust,
            "Empujes a saco pesado"
        ),
        TranslationEntry(
            "Heel Touches",
            R.string.exercise_heel_touches,
            "Toques de talón"
        ),
        TranslationEntry(
            "High Cable Curls",
            R.string.exercise_high_cable_curls,
            "Curl en polea alta"
        ),
        TranslationEntry(
            "High Knee Jumps",
            R.string.exercise_high_knee_jumps,
            "Saltos con rodillas al pecho"
        ),
        TranslationEntry(
            "High Knee Skips",
            R.string.exercise_high_knee_skips,
            "Skiping con rodillas altas"
        ),
        TranslationEntry(
            "High knees",
            R.string.exercise_high_knees,
            "Rodillas al pecho"
        ),
        TranslationEntry(
            "High plank",
            R.string.exercise_high_plank,
            "Plancha alta sobre manos"
        ),
        TranslationEntry(
            "High Pull",
            R.string.exercise_high_pull,
            "Tirón alto"
        ),
        TranslationEntry(
            "High-Incline Smith Machine Press",
            R.string.exercise_high_incline_smith_machine_press,
            "Press inclinado alto en máquina Smith"
        ),
        TranslationEntry(
            "Hindu Pushups",
            R.string.exercise_hindu_pushups,
            "Flexiones hindúes"
        ),
        TranslationEntry(
            "Hindu Squats",
            R.string.exercise_hindu_squats,
            "Sentadillas hindúes"
        ),
        TranslationEntry(
            "Hip Bridge",
            R.string.exercise_hip_bridge,
            "Puente de cadera"
        ),
        TranslationEntry(
            "Hip Circles",
            R.string.exercise_hip_circles,
            "Círculos de cadera"
        ),
        TranslationEntry(
            "Hip Circles (prone)",
            R.string.exercise_hip_circles_prone,
            "Círculos de cadera en prono"
        ),
        TranslationEntry(
            "Hip Crossover",
            R.string.exercise_hip_crossover,
            "Cruce de cadera"
        ),
        TranslationEntry(
            "Hip Extension with Bands",
            R.string.exercise_hip_extension_with_bands,
            "Extensión de cadera con banda elástica"
        ),
        TranslationEntry(
            "Hip Flexion with Band",
            R.string.exercise_hip_flexion_with_band,
            "Flexión de cadera con banda elástica"
        ),
        TranslationEntry(
            "Hip Flexor Stretch",
            R.string.exercise_hip_flexor_stretch,
            "Estiramiento de flexores de cadera"
        ),
        TranslationEntry(
            "Hip Lift with Band",
            R.string.exercise_hip_lift_with_band,
            "Elevación de cadera con banda elástica"
        ),
        TranslationEntry(
            "Hip Raise, Lying",
            R.string.exercise_hip_raise_lying,
            "Elevación de cadera acostado"
        ),
        TranslationEntry(
            "Hip Thrusts",
            R.string.exercise_hip_thrusts,
            "Hip thrusts"
        ),
        TranslationEntry(
            "Hip-Out and Hip-In Lateral Steps",
            R.string.exercise_hip_out_and_hip_in_lateral_steps,
            "Pasos laterales de cadera con banda"
        ),
        TranslationEntry(
            "Hollow Hold",
            R.string.exercise_hollow_hold,
            "Hollow hold"
        ),
        TranslationEntry(
            "Hollow Rock",
            R.string.exercise_hollow_rock,
            "Hollow rock"
        ),
        TranslationEntry(
            "Horizontal Shoulder Flexion Stretch",
            R.string.exercise_horizontal_shoulder_flexion_stretch,
            "Estiramiento de flexión horizontal de hombros"
        ),
        TranslationEntry(
            "Horizontal traction isometry",
            R.string.exercise_horizontal_traction_isometry,
            "Isometría de tracción horizontal"
        ),
        TranslationEntry(
            "Horse Stance (Side Splits)",
            R.string.exercise_horse_stance_side_splits,
            "Postura de caballo (apertura lateral)"
        ),
        TranslationEntry(
            "Hug A Ball",
            R.string.exercise_hug_a_ball,
            "Abrazo a pelota"
        ),
        TranslationEntry(
            "Hug Knees To Chest",
            R.string.exercise_hug_knees_to_chest,
            "Abrazo de rodillas al pecho"
        ),
        TranslationEntry(
            "Hurdle Hops",
            R.string.exercise_hurdle_hops,
            "Saltos de vallas"
        ),
        TranslationEntry(
            "Hyper Y W Combo",
            R.string.exercise_hyper_y_w_combo,
            "Combo hiperextensión en Y y W"
        ),
        TranslationEntry(
            "Hyperextension",
            R.string.exercise_hyperextension,
            "Hiperextensión"
        ),
        TranslationEntry(
            "Hyperextensions (Back Extensions)",
            R.string.exercise_hyperextensions_back_extensions,
            "Hiperextensiones de espalda"
        ),
        TranslationEntry(
            "Hyperextensions With No Hyperextension Bench",
            R.string.exercise_hyperextensions_with_no_hyperextension_bench,
            "Hiperextensiones en suelo sin banco"
        ),
        TranslationEntry(
            "Ice cream maker",
            R.string.exercise_ice_cream_maker,
            "Ice cream maker"
        ),
        TranslationEntry(
            "Iliotibial Tract-SMR",
            R.string.exercise_iliotibial_tract_smr,
            "Liberación miofascial del tracto iliotibial"
        ),
        TranslationEntry(
            "Inchworm",
            R.string.exercise_inchworm,
            "Gusano (inchworm)"
        ),
        TranslationEntry(
            "Incline Barbell Triceps Extension",
            R.string.exercise_incline_barbell_triceps_extension,
            "Extensión de tríceps con barra inclinado"
        ),
        TranslationEntry(
            "Incline Bench Press - MP",
            R.string.exercise_incline_bench_press_mp,
            "Press de banca inclinado en multipower"
        ),
        TranslationEntry(
            "Incline Bench Pull",
            R.string.exercise_incline_bench_pull,
            "Remo tumbado en banco inclinado"
        ),
        TranslationEntry(
            "Incline Cable Chest Press",
            R.string.exercise_incline_cable_chest_press,
            "Press de pecho inclinado en polea"
        ),
        TranslationEntry(
            "Incline Cable Flye",
            R.string.exercise_incline_cable_flye,
            "Aperturas inclinadas en polea"
        ),
        TranslationEntry(
            "Incline Crunches",
            R.string.exercise_incline_crunches,
            "Crunches en banco inclinado"
        ),
        TranslationEntry(
            "Incline Dumbbell Bench With Palms Facing In",
            R.string.exercise_incline_dumbbell_bench_with_palms_facing_in,
            "Press inclinado con mancuernas agarre neutro"
        ),
        TranslationEntry(
            "Incline Dumbbell Curl",
            R.string.exercise_incline_dumbbell_curl,
            "Curl inclinado con mancuernas"
        ),
        TranslationEntry(
            "Incline Dumbbell Flyes",
            R.string.exercise_incline_dumbbell_flyes,
            "Aperturas inclinadas con mancuernas"
        ),
        TranslationEntry(
            "Incline Dumbbell Flyes - With A Twist",
            R.string.exercise_incline_dumbbell_flyes_with_a_twist,
            "Aperturas inclinadas con mancuernas y giro"
        ),
        TranslationEntry(
            "Incline Hammer Curls",
            R.string.exercise_incline_hammer_curls,
            "Curl martillo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Incline Inner Biceps Curl",
            R.string.exercise_incline_inner_biceps_curl,
            "Curl inclinado de bíceps interno mancuernas"
        ),
        TranslationEntry(
            "Incline Plank With Alternate Floor Touch",
            R.string.exercise_incline_plank_with_alternate_floor_touch,
            "Plancha inclinada con toques alternos de suelo"
        ),
        TranslationEntry(
            "Incline Push Press",
            R.string.exercise_incline_push_press,
            "Push press inclinado"
        ),
        TranslationEntry(
            "Incline Push-Up",
            R.string.exercise_incline_push_up,
            "Flexiones inclinadas"
        ),
        TranslationEntry(
            "Incline Push-Up Close-Grip",
            R.string.exercise_incline_push_up_close_grip,
            "Flexiones inclinadas con agarre cerrado"
        ),
        TranslationEntry(
            "Incline Push-Up Depth Jump",
            R.string.exercise_incline_push_up_depth_jump,
            "Flexiones inclinadas con salto en profundidad"
        ),
        TranslationEntry(
            "Incline Push-Up Medium",
            R.string.exercise_incline_push_up_medium,
            "Flexiones inclinadas con agarre medio"
        ),
        TranslationEntry(
            "Incline Push-Up Reverse Grip",
            R.string.exercise_incline_push_up_reverse_grip,
            "Flexiones inclinadas con agarre supino"
        ),
        TranslationEntry(
            "Incline Push-Up Wide",
            R.string.exercise_incline_push_up_wide,
            "Flexiones inclinadas con agarre ancho"
        ),
        TranslationEntry(
            "Incline Pushup on Box",
            R.string.exercise_incline_pushup_on_box,
            "Flexiones inclinadas sobre cajón"
        ),
        TranslationEntry(
            "Incline Row - Rings",
            R.string.exercise_incline_row_rings,
            "Remo inclinado en anillas"
        ),
        TranslationEntry(
            "Incline Shoulder Press Up",
            R.string.exercise_incline_shoulder_press_up,
            "Press inclinado hacia arriba de hombros"
        ),
        TranslationEntry(
            "Incline Skull Crush",
            R.string.exercise_incline_skull_crush,
            "Press francés inclinado"
        ),
        TranslationEntry(
            "Inline Skating",
            R.string.exercise_inline_skating,
            "Patinaje en línea"
        ),
        TranslationEntry(
            "Intermediate Groin Stretch",
            R.string.exercise_intermediate_groin_stretch,
            "Estiramiento intermedio de ingle"
        ),
        TranslationEntry(
            "Intermediate Hip Flexor and Quad Stretch",
            R.string.exercise_intermediate_hip_flexor_and_quad_stretch,
            "Estiramiento intermedio de psoas y cuádriceps"
        ),
        TranslationEntry(
            "Internal Rotation with Band",
            R.string.exercise_internal_rotation_with_band,
            "Rotación interna con banda elástica"
        ),
        TranslationEntry(
            "Inverted Lat Pull Down",
            R.string.exercise_inverted_lat_pull_down,
            "Jalón dorsal invertido"
        ),
        TranslationEntry(
            "Inverted Row",
            R.string.exercise_inverted_row,
            "Remo invertido"
        ),
        TranslationEntry(
            "Inverted Row with Straps",
            R.string.exercise_inverted_row_with_straps,
            "Remo invertido con correas de suspensión"
        ),
        TranslationEntry(
            "Iron Cross",
            R.string.exercise_iron_cross,
            "Cruz de hierro con mancuernas"
        ),
        TranslationEntry(
            "Iron Crosses (stretch)",
            R.string.exercise_iron_crosses_stretch,
            "Estiramiento de cruz de hierro"
        ),
        TranslationEntry(
            "Parallel Bar Isometric Hold",
            R.string.exercise_parallel_bar_isometric_hold,
            "Mantenimiento isométrico en paralelas"
        ),
        TranslationEntry(
            "Reverse Grip Pull-Up Isometric Hold",
            R.string.exercise_reverse_grip_pull_up_isometric_hold,
            "Isometría en dominada con agarre supino"
        ),
        TranslationEntry(
            "Isometric Chest Squeezes",
            R.string.exercise_isometric_chest_squeezes,
            "Apretón isométrico de pecho"
        ),
        TranslationEntry(
            "Isometric Neck Exercise - Front And Back",
            R.string.exercise_isometric_neck_exercise_front_and_back,
            "Ejercicio isométrico de cuello frontal y dorsal"
        ),
        TranslationEntry(
            "Isometric Neck Exercise - Sides",
            R.string.exercise_isometric_neck_exercise_sides,
            "Ejercicio isométrico de cuello lateral"
        ),
        TranslationEntry(
            "Isometric Squat to Failure",
            R.string.exercise_isometric_squat_to_failure,
            "Sentadilla isométrica al fallo"
        ),
        TranslationEntry(
            "Isometric Wipers",
            R.string.exercise_isometric_wipers,
            "Limpiaparabrisas isométricos"
        ),
        TranslationEntry(
            "IT Band and Glute Stretch",
            R.string.exercise_it_band_and_glute_stretch,
            "Estiramiento de cintilla iliotibial y glúteo"
        ),
        TranslationEntry(
            "Jack Knife Sit-Up",
            R.string.exercise_jack_knife_sit_up,
            "Sit-up en navaja"
        ),
        TranslationEntry(
            "Janda Sit-Up",
            R.string.exercise_janda_sit_up,
            "Sit-up Janda"
        ),
        TranslationEntry(
            "Jefferson Squats",
            R.string.exercise_jefferson_squats,
            "Sentadilla Jefferson con barra"
        ),
        TranslationEntry(
            "Jerk Balance",
            R.string.exercise_jerk_balance,
            "Jerk balance con barra"
        ),
        TranslationEntry(
            "Jerk Dip Squat",
            R.string.exercise_jerk_dip_squat,
            "Sentadilla jerk dip con barra"
        ),
        TranslationEntry(
            "JM Press",
            R.string.exercise_jm_press,
            "Press JM con barra"
        ),
        TranslationEntry(
            "Jogging",
            R.string.exercise_jogging,
            "Trote"
        ),
        TranslationEntry(
            "Jogging, Treadmill",
            R.string.exercise_jogging_treadmill,
            "Trote en cinta"
        ),
        TranslationEntry(
            "Jump Box",
            R.string.exercise_jump_box,
            "Salto al cajón"
        ),
        TranslationEntry(
            "Jump Rope",
            R.string.exercise_jump_rope,
            "Salto de cuerda"
        ),
        TranslationEntry(
            "Jump rope: basic jumps",
            R.string.exercise_jump_rope_basic_jumps,
            "Salto de cuerda: saltos básicos"
        ),
        TranslationEntry(
            "Jump Shrug",
            R.string.exercise_jump_shrug,
            "Encogimiento con salto"
        ),
        TranslationEntry(
            "Jump Split",
            R.string.exercise_jump_split,
            "Salto en tijera"
        ),
        TranslationEntry(
            "Jump Squat",
            R.string.exercise_jump_squat,
            "Sentadilla con salto"
        ),
        TranslationEntry(
            "Jumping Jack",
            R.string.exercise_jumping_jack,
            "Jumping jacks"
        ),
        TranslationEntry(
            "Keg Load",
            R.string.exercise_keg_load,
            "Carga de barril"
        ),
        TranslationEntry(
            "Kegel Exercise",
            R.string.exercise_kegel_exercise,
            "Ejercicios de Kegel"
        ),
        TranslationEntry(
            "Kettlebell 2 Handed Kettlebell Swing",
            R.string.exercise_kettlebell_2_handed_kettlebell_swing,
            "Swing con kettlebell a dos manos"
        ),
        TranslationEntry(
            "Kettlebell Arnold Press",
            R.string.exercise_kettlebell_arnold_press,
            "Press Arnold con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Around Head Rotation",
            R.string.exercise_kettlebell_around_head_rotation,
            "Rotación alrededor de cabeza con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Biceps Curl",
            R.string.exercise_kettlebell_biceps_curl,
            "Curl de bíceps con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Curl with kettlebell two hands",
            R.string.exercise_kettlebell_curl_with_kettlebell_two_hands,
            "Curl a dos manos con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Dead Clean",
            R.string.exercise_kettlebell_dead_clean,
            "Cargada muerta con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell deadlifts",
            R.string.exercise_kettlebell_deadlifts,
            "Peso muerto con kettlebells"
        ),
        TranslationEntry(
            "Kettlebell Double Kettlebell Clean and Press",
            R.string.exercise_kettlebell_double_kettlebell_clean_and_press,
            "Cargada y press con dos kettlebells"
        ),
        TranslationEntry(
            "Kettlebell Double Kettlebell Front Squat",
            R.string.exercise_kettlebell_double_kettlebell_front_squat,
            "Sentadilla frontal con dos kettlebells"
        ),
        TranslationEntry(
            "Kettlebell Dynamic side hold",
            R.string.exercise_kettlebell_dynamic_side_hold,
            "Sostén lateral dinámico con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Figure 8",
            R.string.exercise_kettlebell_figure_8,
            "Figura en 8 con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Goblet Squat",
            R.string.exercise_kettlebell_goblet_squat,
            "Sentadilla goblet con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Gorilla Row",
            R.string.exercise_kettlebell_gorilla_row,
            "Remo gorila con kettlebells"
        ),
        TranslationEntry(
            "Kettlebell Halo",
            R.string.exercise_kettlebell_halo,
            "Halo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Halo with Overhead Extension",
            R.string.exercise_kettlebell_halo_with_overhead_extension,
            "Halo con extensión sobre cabeza con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Hang Clean",
            R.string.exercise_kettlebell_hang_clean,
            "Cargada colgada con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Hip Airplane",
            R.string.exercise_kettlebell_hip_airplane,
            "Avión de cadera con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell One-handed Kettlebell Curls",
            R.string.exercise_kettlebell_one_handed_kettlebell_curls,
            "Curl a una mano con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell One-Legged Deadlift",
            R.string.exercise_kettlebell_one_legged_deadlift,
            "Peso muerto a una pierna con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Overhead Triceps Extension",
            R.string.exercise_kettlebell_overhead_triceps_extension,
            "Extensión de tríceps sobre cabeza con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Pass Between The Legs",
            R.string.exercise_kettlebell_pass_between_the_legs,
            "Paso entre piernas con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Pirate Ships",
            R.string.exercise_kettlebell_pirate_ships,
            "Barco pirata con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Pistol Squat",
            R.string.exercise_kettlebell_pistol_squat,
            "Sentadilla pistol con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Seated Press",
            R.string.exercise_kettlebell_seated_press,
            "Press sentado con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Seesaw Press",
            R.string.exercise_kettlebell_seesaw_press,
            "Press balancín con kettlebells"
        ),
        TranslationEntry(
            "Kettlebell Single Arm Clean",
            R.string.exercise_kettlebell_single_arm_clean,
            "Cargada a un brazo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Single Arm Plank to Row",
            R.string.exercise_kettlebell_single_arm_plank_to_row,
            "Plancha con remo a un brazo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Single Arm Shoulder Press",
            R.string.exercise_kettlebell_single_arm_shoulder_press,
            "Press de hombro a un brazo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Single-Leg Balance with Kettlebell Circles",
            R.string.exercise_kettlebell_single_leg_balance_with_kettlebell_circles,
            "Equilibrio unipodal con círculos de kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Single-Leg Lunge with Kettlebell:",
            R.string.exercise_kettlebell_single_leg_lunge_with_kettlebell,
            "Zancada a una pierna con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Snatch",
            R.string.exercise_kettlebell_snatch,
            "Arrancada con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Standing Slingshots",
            R.string.exercise_kettlebell_standing_slingshots,
            "Slingshots de pie con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Strict Press",
            R.string.exercise_kettlebell_strict_press,
            "Press estricto con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Sumo Deadlift",
            R.string.exercise_kettlebell_sumo_deadlift,
            "Peso muerto sumo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Sumo High Pull",
            R.string.exercise_kettlebell_sumo_high_pull,
            "Tirón alto sumo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Sumo Squat",
            R.string.exercise_kettlebell_sumo_squat,
            "Sentadilla sumo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Swings",
            R.string.exercise_kettlebell_swings,
            "Swings con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Thruster",
            R.string.exercise_kettlebell_thruster,
            "Thruster con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Turkish Get-Up (Lunge style)",
            R.string.exercise_kettlebell_turkish_get_up_lunge_style,
            "Levantamiento turco con kettlebell (estilo zancada)"
        ),
        TranslationEntry(
            "Kettlebell Turkish Getup Squat Style",
            R.string.exercise_kettlebell_turkish_getup_squat_style,
            "Levantamiento turco kettlebell (estilo sentadilla)"
        ),
        TranslationEntry(
            "Kettlebell Windmill",
            R.string.exercise_kettlebell_windmill,
            "Molino con kettlebell"
        ),
        TranslationEntry(
            "Kick with Board",
            R.string.exercise_kick_with_board,
            "Pataleo con tabla de natación"
        ),
        TranslationEntry(
            "Kipping Muscle Up",
            R.string.exercise_kipping_muscle_up,
            "Muscle-up con impulso (kipping)"
        ),
        TranslationEntry(
            "Knee Across The Body",
            R.string.exercise_knee_across_the_body,
            "Rodilla cruzada al cuerpo"
        ),
        TranslationEntry(
            "Knee Circles",
            R.string.exercise_knee_circles,
            "Círculos de rodillas"
        ),
        TranslationEntry(
            "Knee Push-ups",
            R.string.exercise_knee_push_ups,
            "Flexiones de rodillas"
        ),
        TranslationEntry(
            "Knee Raises",
            R.string.exercise_knee_raises,
            "Elevaciones de rodillas"
        ),
        TranslationEntry(
            "Knee to Chest Stretch",
            R.string.exercise_knee_to_chest_stretch,
            "Estiramiento de rodilla al pecho"
        ),
        TranslationEntry(
            "Knee Tuck Jump",
            R.string.exercise_knee_tuck_jump,
            "Salto con rodillas al pecho"
        ),
        TranslationEntry(
            "Knee/Hip Raise On Parallel Bars",
            R.string.exercise_knee_hip_raise_on_parallel_bars,
            "Elevación de rodillas/cadera en paralelas"
        ),
        TranslationEntry(
            "Kneeling Arm Drill",
            R.string.exercise_kneeling_arm_drill,
            "Técnica de braceo de rodillas"
        ),
        TranslationEntry(
            "Kneeling Cable Crunch With Alternating Oblique Twists",
            R.string.exercise_kneeling_cable_crunch_with_alternating_oblique_twists,
            "Crunch en polea de rodillas con giro oblicuo"
        ),
        TranslationEntry(
            "Kneeling Cable Triceps Extension",
            R.string.exercise_kneeling_cable_triceps_extension,
            "Extensión de tríceps en polea de rodillas"
        ),
        TranslationEntry(
            "Kneeling Forearm Stretch",
            R.string.exercise_kneeling_forearm_stretch,
            "Estiramiento de antebrazos de rodillas"
        ),
        TranslationEntry(
            "Kneeling High Pulley Row",
            R.string.exercise_kneeling_high_pulley_row,
            "Remo en polea alta de rodillas"
        ),
        TranslationEntry(
            "Kneeling Hip Flexor",
            R.string.exercise_kneeling_hip_flexor,
            "Estiramiento de flexor de cadera de rodillas"
        ),
        TranslationEntry(
            "Kneeling Jump Squat",
            R.string.exercise_kneeling_jump_squat,
            "Sentadilla de rodillas con salto con barra"
        ),
        TranslationEntry(
            "Kneeling kickbacks",
            R.string.exercise_kneeling_kickbacks,
            "Patada trasera de glúteos de rodillas"
        ),
        TranslationEntry(
            "Kneeling Pushup",
            R.string.exercise_kneeling_pushup,
            "Flexiones de rodillas en el suelo"
        ),
        TranslationEntry(
            "Kneeling Single-Arm High Pulley Row",
            R.string.exercise_kneeling_single_arm_high_pulley_row,
            "Remo en polea alta a un brazo de rodillas"
        ),
        TranslationEntry(
            "Kneeling Squat",
            R.string.exercise_kneeling_squat,
            "Sentadilla de rodillas con barra"
        ),
        TranslationEntry(
            "Kneeling Superman",
            R.string.exercise_kneeling_superman,
            "Superman de rodillas (bird-dog)"
        ),
        TranslationEntry(
            "Knieheben Beinhebemaschine",
            R.string.exercise_knieheben_beinhebemaschine,
            "Elevación de rodillas en torre de fondos"
        ),
        TranslationEntry(
            "Knieheben Beinhebemaschine seitlich",
            R.string.exercise_knieheben_beinhebemaschine_seitlich,
            "Elevación de rodillas lateral en torre de fondos"
        ),
        TranslationEntry(
            "Kroc Row",
            R.string.exercise_kroc_row,
            "Remo Kroc con mancuerna"
        ),
        TranslationEntry(
            "L Hold",
            R.string.exercise_l_hold,
            "Mantenimiento en L"
        ),
        TranslationEntry(
            "L Sit",
            R.string.exercise_l_sit,
            "L-sit"
        ),
        TranslationEntry(
            "L-Sit (Foot Supported)",
            R.string.exercise_l_sit_foot_supported,
            "L-sit con apoyo de pies"
        ),
        TranslationEntry(
            "L-Sit Pull-ups",
            R.string.exercise_l_sit_pull_ups,
            "Dominadas en L-sit"
        ),
        TranslationEntry(
            "Landmine",
            R.string.exercise_landmine,
            "Landmine"
        ),
        TranslationEntry(
            "Landmine 180",
            R.string.exercise_landmine_180,
            "Landmine 180"
        ),
        TranslationEntry(
            "Landmine 180's",
            R.string.exercise_landmine_180s,
            "Landmine 180 con barra"
        ),
        TranslationEntry(
            "Landmine Linear Jammer",
            R.string.exercise_landmine_linear_jammer,
            "Empuje jammer con landmine"
        ),
        TranslationEntry(
            "Landmine Single Arm Press",
            R.string.exercise_landmine_single_arm_press,
            "Press a un brazo con landmine"
        ),
        TranslationEntry(
            "Landmine Squat and Press",
            R.string.exercise_landmine_squat_and_press,
            "Sentadilla y press con landmine"
        ),
        TranslationEntry(
            "Lat Pull Down (Leaning Back)",
            R.string.exercise_lat_pull_down_leaning_back,
            "Jalón dorsal en polea inclinado atrás"
        ),
        TranslationEntry(
            "Lat Pull Down (Straight Back)",
            R.string.exercise_lat_pull_down_straight_back,
            "Jalón dorsal en polea espalda recta"
        ),
        TranslationEntry(
            "Lateral Bound",
            R.string.exercise_lateral_bound,
            "Saltos laterales de patinador"
        ),
        TranslationEntry(
            "Lateral Box Jump",
            R.string.exercise_lateral_box_jump,
            "Salto lateral al cajón"
        ),
        TranslationEntry(
            "Lateral Cone Hops",
            R.string.exercise_lateral_cone_hops,
            "Saltos laterales sobre cono"
        ),
        TranslationEntry(
            "Lateral Push Off",
            R.string.exercise_lateral_push_off,
            "Empuje lateral sobre cajón"
        ),
        TranslationEntry(
            "Lateral Raise - With Bands",
            R.string.exercise_lateral_raise_with_bands,
            "Elevaciones laterales con banda elástica"
        ),
        TranslationEntry(
            "Lateral Walk",
            R.string.exercise_lateral_walk,
            "Caminata lateral con banda"
        ),
        TranslationEntry(
            "Latissimus Dorsi-SMR",
            R.string.exercise_latissimus_dorsi_smr,
            "Liberación miofascial del dorsal ancho"
        ),
        TranslationEntry(
            "Left levator scapulae stretch",
            R.string.exercise_left_levator_scapulae_stretch,
            "Estiramiento del elevador de la escápula izquierdo"
        ),
        TranslationEntry(
            "Left neck stretch",
            R.string.exercise_left_neck_stretch,
            "Estiramiento de cuello izquierdo"
        ),
        TranslationEntry(
            "Leg and hip stretch",
            R.string.exercise_leg_and_hip_stretch,
            "Estiramiento de pierna y cadera"
        ),
        TranslationEntry(
            "Leg Curl",
            R.string.exercise_leg_curl,
            "Curl femoral"
        ),
        TranslationEntry(
            "Leg curl with elastic",
            R.string.exercise_leg_curl_with_elastic,
            "Curl femoral con banda elástica"
        ),
        TranslationEntry(
            "Leg Curls (laying)",
            R.string.exercise_leg_curls_laying,
            "Curl femoral tumbado"
        ),
        TranslationEntry(
            "Leg Curls (sitting)",
            R.string.exercise_leg_curls_sitting,
            "Curl femoral sentado"
        ),
        TranslationEntry(
            "Leg Extension Isometric",
            R.string.exercise_leg_extension_isometric,
            "Extensión isométrica de piernas"
        ),
        TranslationEntry(
            "Leg Lift",
            R.string.exercise_leg_lift,
            "Elevación de piernas"
        ),
        TranslationEntry(
            "Leg Press",
            R.string.exercise_leg_press,
            "Prensa de piernas"
        ),
        TranslationEntry(
            "Leg Press on Hackenschmidt Machine",
            R.string.exercise_leg_press_on_hackenschmidt_machine,
            "Prensa de piernas en máquina Hackenschmidt"
        ),
        TranslationEntry(
            "Leg Press Toe Press",
            R.string.exercise_leg_press_toe_press,
            "Prensa de gemelos en máquina de piernas"
        ),
        TranslationEntry(
            "Leg Presses (narrow)",
            R.string.exercise_leg_presses_narrow,
            "Prensa de piernas con pies juntos"
        ),
        TranslationEntry(
            "Leg Presses (wide)",
            R.string.exercise_leg_presses_wide,
            "Prensa de piernas con pies separados"
        ),
        TranslationEntry(
            "Leg Pull-In",
            R.string.exercise_leg_pull_in,
            "Encogimiento de piernas"
        ),
        TranslationEntry(
            "Leg Raise",
            R.string.exercise_leg_raise,
            "Elevación de piernas"
        ),
        TranslationEntry(
            "Leg Raise Dragon Flag",
            R.string.exercise_leg_raise_dragon_flag,
            "Dragon flag con elevación de piernas"
        ),
        TranslationEntry(
            "Leg raises pull up bar",
            R.string.exercise_leg_raises_pull_up_bar,
            "Elevación de piernas en barra de dominadas"
        ),
        TranslationEntry(
            "Leg Raises, Standing",
            R.string.exercise_leg_raises_standing,
            "Elevación de piernas de pie"
        ),
        TranslationEntry(
            "Leg Swings (Front–Back)",
            R.string.exercise_leg_swings_front_back,
            "Balanceos de pierna (adelante y atrás)"
        ),
        TranslationEntry(
            "Leg Wheel",
            R.string.exercise_leg_wheel,
            "Rueda de piernas"
        ),
        TranslationEntry(
            "Leg-Over Floor Press",
            R.string.exercise_leg_over_floor_press,
            "Press de suelo con pierna cruzada"
        ),
        TranslationEntry(
            "Leg-Up Hamstring Stretch",
            R.string.exercise_leg_up_hamstring_stretch,
            "Estiramiento de isquiotibiales con pierna arriba"
        ),
        TranslationEntry(
            "Legend Chest Press",
            R.string.exercise_legend_chest_press,
            "Press de pecho Legend"
        ),
        TranslationEntry(
            "Legend Incline Bench Press",
            R.string.exercise_legend_incline_bench_press,
            "Press de banca inclinado Legend"
        ),
        TranslationEntry(
            "Lever Alternate Biceps Curl",
            R.string.exercise_lever_alternate_biceps_curl,
            "Curl de bíceps alterno en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Back Extension",
            R.string.exercise_lever_back_extension,
            "Extensión de espalda en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Belt Squat",
            R.string.exercise_lever_belt_squat,
            "Sentadilla con cinturón en máquina"
        ),
        TranslationEntry(
            "Lever Bent Over Row",
            R.string.exercise_lever_bent_over_row,
            "Remo inclinado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Bent Over Row Plate Loaded",
            R.string.exercise_lever_bent_over_row_plate_loaded,
            "Remo inclinado de palanca con discos"
        ),
        TranslationEntry(
            "Lever Bent Over Row with V-Bar Plate Loaded",
            R.string.exercise_lever_bent_over_row_with_v_bar_plate_loaded,
            "Remo de palanca con agarre en V con discos"
        ),
        TranslationEntry(
            "Lever Biceps Curl",
            R.string.exercise_lever_biceps_curl,
            "Curl de bíceps en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Chest Press",
            R.string.exercise_lever_chest_press,
            "Press de pecho en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Chest Press Plate Loaded",
            R.string.exercise_lever_chest_press_plate_loaded,
            "Press de pecho de palanca con discos"
        ),
        TranslationEntry(
            "Lever Cross Lat Pulldown",
            R.string.exercise_lever_cross_lat_pulldown,
            "Jalón dorsal cruzado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Decline Chest Press",
            R.string.exercise_lever_decline_chest_press,
            "Press de pecho declinado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Front Pulldown",
            R.string.exercise_lever_front_pulldown,
            "Jalón frontal en máquina de palanca"
        ),
        TranslationEntry(
            "Lever High Row",
            R.string.exercise_lever_high_row,
            "Remo alto en máquina de palanca"
        ),
        TranslationEntry(
            "Lever High Row Plate Loaded",
            R.string.exercise_lever_high_row_plate_loaded,
            "Remo alto de palanca con discos"
        ),
        TranslationEntry(
            "Lever Hip Thrust",
            R.string.exercise_lever_hip_thrust,
            "Hip thrust en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Horizontal One Leg Press",
            R.string.exercise_lever_horizontal_one_leg_press,
            "Prensa horizontal a una pierna en máquina"
        ),
        TranslationEntry(
            "Lever Incline Chest Press",
            R.string.exercise_lever_incline_chest_press,
            "Press inclinado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Lateral Raise",
            R.string.exercise_lever_lateral_raise,
            "Elevaciones laterales en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Leg Extension",
            R.string.exercise_lever_leg_extension,
            "Extensión de piernas en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Low Row",
            R.string.exercise_lever_low_row,
            "Remo bajo en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Low Row Plate Loaded",
            R.string.exercise_lever_low_row_plate_loaded,
            "Remo bajo de palanca con discos"
        ),
        TranslationEntry(
            "Lever Lying Leg Curl",
            R.string.exercise_lever_lying_leg_curl,
            "Curl femoral acostado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Lying T-Bar Row",
            R.string.exercise_lever_lying_t_bar_row,
            "Remo en T acostado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Military Press",
            R.string.exercise_lever_military_press,
            "Press militar en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Military Press Plate Loaded",
            R.string.exercise_lever_military_press_plate_loaded,
            "Press militar de palanca con discos"
        ),
        TranslationEntry(
            "Lever Neutral Grip Seated Row Plate Loaded",
            R.string.exercise_lever_neutral_grip_seated_row_plate_loaded,
            "Remo sentado agarre neutro de palanca con discos"
        ),
        TranslationEntry(
            "Lever One Leg Extension",
            R.string.exercise_lever_one_leg_extension,
            "Extensión a una pierna en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Pec Deck Fly",
            R.string.exercise_lever_pec_deck_fly,
            "Aperturas pec deck en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Pendulum Squat",
            R.string.exercise_lever_pendulum_squat,
            "Sentadilla péndulo en máquina"
        ),
        TranslationEntry(
            "Lever Pendulum Squat Plate Loaded",
            R.string.exercise_lever_pendulum_squat_plate_loaded,
            "Sentadilla péndulo de palanca con discos"
        ),
        TranslationEntry(
            "Lever Preacher Curl",
            R.string.exercise_lever_preacher_curl,
            "Curl predicador en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Pullover",
            R.string.exercise_lever_pullover,
            "Pullover en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Pullover Plate Loaded",
            R.string.exercise_lever_pullover_plate_loaded,
            "Pullover de palanca con discos"
        ),
        TranslationEntry(
            "Lever Pure Rear Kick",
            R.string.exercise_lever_pure_rear_kick,
            "Patada trasera en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Reverse Hyperextension",
            R.string.exercise_lever_reverse_hyperextension,
            "Hiperextensión inversa en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Reverse Hyperextension Plate Loaded",
            R.string.exercise_lever_reverse_hyperextension_plate_loaded,
            "Hiperextensión inversa de palanca con discos"
        ),
        TranslationEntry(
            "Lever Rotary Calf",
            R.string.exercise_lever_rotary_calf,
            "Gemelos rotatorio en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Calf Raise",
            R.string.exercise_lever_seated_calf_raise,
            "Elevación de gemelos sentado en máquina"
        ),
        TranslationEntry(
            "Lever Seated Calf Raise Plate Loaded",
            R.string.exercise_lever_seated_calf_raise_plate_loaded,
            "Elevación de gemelos sentado palanca con discos"
        ),
        TranslationEntry(
            "Lever Seated Crunch",
            R.string.exercise_lever_seated_crunch,
            "Crunch sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Crunch Chest Pad",
            R.string.exercise_lever_seated_crunch_chest_pad,
            "Crunch sentado con apoyo de pecho en máquina"
        ),
        TranslationEntry(
            "Lever Seated Dip",
            R.string.exercise_lever_seated_dip,
            "Fondos sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Fly",
            R.string.exercise_lever_seated_fly,
            "Aperturas sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Hip Abduction",
            R.string.exercise_lever_seated_hip_abduction,
            "Abducción de cadera sentado en máquina"
        ),
        TranslationEntry(
            "Lever Seated Hip Adduction",
            R.string.exercise_lever_seated_hip_adduction,
            "Aducción de cadera sentado en máquina"
        ),
        TranslationEntry(
            "Lever Seated Leg Curl",
            R.string.exercise_lever_seated_leg_curl,
            "Curl femoral sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Leg Extension",
            R.string.exercise_lever_seated_leg_extension,
            "Extensión de piernas sentado en máquina"
        ),
        TranslationEntry(
            "Lever Seated Leg Press",
            R.string.exercise_lever_seated_leg_press,
            "Prensa de piernas sentado en máquina"
        ),
        TranslationEntry(
            "Lever Seated Reverse Fly",
            R.string.exercise_lever_seated_reverse_fly,
            "Pájaros sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Row",
            R.string.exercise_lever_seated_row,
            "Remo sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Seated Shoulder Press",
            R.string.exercise_lever_seated_shoulder_press,
            "Press de hombros sentado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Shrug",
            R.string.exercise_lever_shrug,
            "Encogimiento de hombros en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Shrug Plate Loaded",
            R.string.exercise_lever_shrug_plate_loaded,
            "Encogimiento de palanca con discos"
        ),
        TranslationEntry(
            "Lever Squat",
            R.string.exercise_lever_squat,
            "Sentadilla en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Squat Plate Loaded",
            R.string.exercise_lever_squat_plate_loaded,
            "Sentadilla de palanca con discos"
        ),
        TranslationEntry(
            "Lever Standing Calf Raise",
            R.string.exercise_lever_standing_calf_raise,
            "Elevación de gemelos de pie en máquina"
        ),
        TranslationEntry(
            "Lever Standing Rear Kick",
            R.string.exercise_lever_standing_rear_kick,
            "Patada trasera de pie en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Standing Single Leg Calf Raise",
            R.string.exercise_lever_standing_single_leg_calf_raise,
            "Elevación de gemelos a una pierna en máquina"
        ),
        TranslationEntry(
            "Lever Standing Single Leg Curl Plate Loaded",
            R.string.exercise_lever_standing_single_leg_curl_plate_loaded,
            "Curl femoral a una pierna de palanca con discos"
        ),
        TranslationEntry(
            "Lever T-Bar Row",
            R.string.exercise_lever_t_bar_row,
            "Remo en T en máquina de palanca"
        ),
        TranslationEntry(
            "Lever T-Bar Row Plate Loaded",
            R.string.exercise_lever_t_bar_row_plate_loaded,
            "Remo en T de palanca con discos"
        ),
        TranslationEntry(
            "Lever Torso Rotation",
            R.string.exercise_lever_torso_rotation,
            "Rotación de torso en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Triceps Dip Plate Loaded",
            R.string.exercise_lever_triceps_dip_plate_loaded,
            "Fondos de tríceps de palanca con discos"
        ),
        TranslationEntry(
            "Lever Triceps Extension",
            R.string.exercise_lever_triceps_extension,
            "Extensión de tríceps en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Vertical Squat",
            R.string.exercise_lever_vertical_squat,
            "Sentadilla vertical en máquina de palanca"
        ),
        TranslationEntry(
            "Leverage Chest Press",
            R.string.exercise_leverage_chest_press,
            "Press de pecho de palanca"
        ),
        TranslationEntry(
            "Leverage Deadlift",
            R.string.exercise_leverage_deadlift,
            "Peso muerto en máquina de palanca"
        ),
        TranslationEntry(
            "Leverage Decline Chest Press",
            R.string.exercise_leverage_decline_chest_press,
            "Press declinado de palanca"
        ),
        TranslationEntry(
            "Leverage High Row",
            R.string.exercise_leverage_high_row,
            "Remo alto en máquina de palanca articulada"
        ),
        TranslationEntry(
            "Leverage Incline Chest Press",
            R.string.exercise_leverage_incline_chest_press,
            "Press inclinado de palanca"
        ),
        TranslationEntry(
            "Leverage Iso Row",
            R.string.exercise_leverage_iso_row,
            "Remo unilateral en máquina de palanca"
        ),
        TranslationEntry(
            "Leverage Machine Chest Press",
            R.string.exercise_leverage_machine_chest_press,
            "Press de pecho en máquina Leverage"
        ),
        TranslationEntry(
            "Leverage Machine Iso Row",
            R.string.exercise_leverage_machine_iso_row,
            "Remo unilateral en máquina Leverage"
        ),
        TranslationEntry(
            "Leverage Shoulder Press",
            R.string.exercise_leverage_shoulder_press,
            "Press de hombros de palanca"
        ),
        TranslationEntry(
            "Leverage Shrug",
            R.string.exercise_leverage_shrug,
            "Encogimiento de hombros de palanca"
        ),
        TranslationEntry(
            "Levitation Crunch",
            R.string.exercise_levitation_crunch,
            "Crunch levitación"
        ),
        TranslationEntry(
            "Limber 11",
            R.string.exercise_limber_11,
            "Rutina Limber 11"
        ),
        TranslationEntry(
            "Linear 3-Part Start Technique",
            R.string.exercise_linear_3_part_start_technique,
            "Técnica de salida lineal en 3 partes"
        ),
        TranslationEntry(
            "Linear Acceleration Wall Drill",
            R.string.exercise_linear_acceleration_wall_drill,
            "Ejercicio de aceleración lineal contra pared"
        ),
        TranslationEntry(
            "Linear Depth Jump",
            R.string.exercise_linear_depth_jump,
            "Salto en profundidad lineal"
        ),
        TranslationEntry(
            "Log Lift",
            R.string.exercise_log_lift,
            "Levantamiento de tronco (log press)"
        ),
        TranslationEntry(
            "London Bridges",
            R.string.exercise_london_bridges,
            "Puentes de Londres"
        ),
        TranslationEntry(
            "Long Distance Running",
            R.string.exercise_long_distance_running,
            "Carrera de larga distancia"
        ),
        TranslationEntry(
            "Long Lunge Pulse Stretch",
            R.string.exercise_long_lunge_pulse_stretch,
            "Estiramiento en zancada larga con rebotes"
        ),
        TranslationEntry(
            "Looking At Ceiling",
            R.string.exercise_looking_at_ceiling,
            "Mirada al techo (estiramiento de cuello)"
        ),
        TranslationEntry(
            "Low Cable Crossover",
            R.string.exercise_low_cable_crossover,
            "Cruce en polea baja"
        ),
        TranslationEntry(
            "Low Cable Triceps Extension",
            R.string.exercise_low_cable_triceps_extension,
            "Extensión de tríceps en polea baja"
        ),
        TranslationEntry(
            "Low Pulley Row To Neck",
            R.string.exercise_low_pulley_row_to_neck,
            "Remo al cuello en polea baja"
        ),
        TranslationEntry(
            "Lower Back Curl",
            R.string.exercise_lower_back_curl,
            "Flexión de espalda baja"
        ),
        TranslationEntry(
            "Lower Back Extensions",
            R.string.exercise_lower_back_extensions,
            "Extensiones lumbares"
        ),
        TranslationEntry(
            "Lower Back-SMR",
            R.string.exercise_lower_back_smr,
            "Liberación miofascial de espalda baja"
        ),
        TranslationEntry(
            "Lunge Pass Through",
            R.string.exercise_lunge_pass_through,
            "Paso de kettlebell bajo zancada"
        ),
        TranslationEntry(
            "Lunge Sprint",
            R.string.exercise_lunge_sprint,
            "Sprint desde zancada"
        ),
        TranslationEntry(
            "Lunge with Twist Stretch",
            R.string.exercise_lunge_with_twist_stretch,
            "Estiramiento en zancada con torsión"
        ),
        TranslationEntry(
            "Lunges",
            R.string.exercise_lunges,
            "Zancadas"
        ),
        TranslationEntry(
            "Lying Bent Leg Groin",
            R.string.exercise_lying_bent_leg_groin,
            "Estiramiento acostado de aductores flexionado"
        ),
        TranslationEntry(
            "Lying Cable Curl",
            R.string.exercise_lying_cable_curl,
            "Curl tumbado en polea"
        ),
        TranslationEntry(
            "Lying Cambered Barbell Row",
            R.string.exercise_lying_cambered_barbell_row,
            "Remo tumbado con barra curvada"
        ),
        TranslationEntry(
            "Lying Close-Grip Bar Curl On High Pulley",
            R.string.exercise_lying_close_grip_bar_curl_on_high_pulley,
            "Curl tumbado agarre cerrado en polea alta"
        ),
        TranslationEntry(
            "Lying Close-Grip Barbell Triceps Extension Behind The Head",
            R.string.exercise_lying_close_grip_barbell_triceps_extension_behind_the_head,
            "Press francés tras nuca agarre cerrado"
        ),
        TranslationEntry(
            "Lying Close-Grip Barbell Triceps Press To Chin",
            R.string.exercise_lying_close_grip_barbell_triceps_press_to_chin,
            "Press francés al mentón con barra"
        ),
        TranslationEntry(
            "Lying Crossover",
            R.string.exercise_lying_crossover,
            "Cruce de piernas acostado"
        ),
        TranslationEntry(
            "Lying Dumbbell Tricep Extension",
            R.string.exercise_lying_dumbbell_tricep_extension,
            "Extensión de tríceps acostado con mancuernas"
        ),
        TranslationEntry(
            "Lying Elbow to Knee",
            R.string.exercise_lying_elbow_to_knee,
            "Codo a rodilla acostado"
        ),
        TranslationEntry(
            "Lying Face Down Plate Neck Resistance",
            R.string.exercise_lying_face_down_plate_neck_resistance,
            "Extensión de cuello prono con disco"
        ),
        TranslationEntry(
            "Lying Face Up Plate Neck Resistance",
            R.string.exercise_lying_face_up_plate_neck_resistance,
            "Flexión de cuello supino con disco"
        ),
        TranslationEntry(
            "Lying Figure Four Stretch",
            R.string.exercise_lying_figure_four_stretch,
            "Estiramiento en figura de 4 acostado"
        ),
        TranslationEntry(
            "Lying Glute",
            R.string.exercise_lying_glute,
            "Estiramiento acostado de glúteos"
        ),
        TranslationEntry(
            "Lying Hamstring",
            R.string.exercise_lying_hamstring,
            "Estiramiento acostado de isquiotibiales"
        ),
        TranslationEntry(
            "Lying Hamstring Stretch with Band",
            R.string.exercise_lying_hamstring_stretch_with_band,
            "Estiramiento de isquiotibiales con banda"
        ),
        TranslationEntry(
            "Lying High Bench Barbell Curl",
            R.string.exercise_lying_high_bench_barbell_curl,
            "Curl tumbado en banco alto con barra"
        ),
        TranslationEntry(
            "Lying Hip Leg Raise on Floor",
            R.string.exercise_lying_hip_leg_raise_on_floor,
            "Elevación de cadera y pierna en suelo"
        ),
        TranslationEntry(
            "Lying Knee Raise",
            R.string.exercise_lying_knee_raise,
            "Elevación de rodillas acostado"
        ),
        TranslationEntry(
            "Lying Leg Raise",
            R.string.exercise_lying_leg_raise,
            "Elevación de piernas acostado"
        ),
        TranslationEntry(
            "Lying Machine Squat",
            R.string.exercise_lying_machine_squat,
            "Sentadilla en máquina acostado"
        ),
        TranslationEntry(
            "Lying Neck Extension",
            R.string.exercise_lying_neck_extension,
            "Extensión de cuello acostado"
        ),
        TranslationEntry(
            "Lying Neck Flexion",
            R.string.exercise_lying_neck_flexion,
            "Flexión de cuello acostado"
        ),
        TranslationEntry(
            "Lying One-Arm Lateral Raise",
            R.string.exercise_lying_one_arm_lateral_raise,
            "Elevación lateral tumbado a un brazo"
        ),
        TranslationEntry(
            "Lying Prone Quadriceps",
            R.string.exercise_lying_prone_quadriceps,
            "Estiramiento de cuádriceps en prono"
        ),
        TranslationEntry(
            "Lying Rear Delt Raise",
            R.string.exercise_lying_rear_delt_raise,
            "Pájaros acostado con mancuernas"
        ),
        TranslationEntry(
            "Lying Scissors Cross",
            R.string.exercise_lying_scissors_cross,
            "Tijeras cruzadas acostado"
        ),
        TranslationEntry(
            "Lying Supine Dumbbell Curl",
            R.string.exercise_lying_supine_dumbbell_curl,
            "Curl acostado boca arriba con mancuernas"
        ),
        TranslationEntry(
            "Lying T-Bar Row",
            R.string.exercise_lying_t_bar_row,
            "Remo en T tumbado"
        ),
        TranslationEntry(
            "Lying Toe Touch",
            R.string.exercise_lying_toe_touch,
            "Toques de puntas acostado"
        ),
        TranslationEntry(
            "Lying Triceps Press",
            R.string.exercise_lying_triceps_press,
            "Press de tríceps acostado con barra"
        ),
        TranslationEntry(
            "Machine Bench Press",
            R.string.exercise_machine_bench_press,
            "Press de banca en máquina"
        ),
        TranslationEntry(
            "Machine Bicep Curl",
            R.string.exercise_machine_bicep_curl,
            "Curl de bíceps en máquina"
        ),
        TranslationEntry(
            "Machine chest fly",
            R.string.exercise_machine_chest_fly,
            "Aperturas de pecho en máquina"
        ),
        TranslationEntry(
            "Machine Dip or Triceps Extension",
            R.string.exercise_machine_dip_or_triceps_extension,
            "Fondos o extensión de tríceps en máquina"
        ),
        TranslationEntry(
            "Machine glute extension",
            R.string.exercise_machine_glute_extension,
            "Extensión de glúteos en máquina"
        ),
        TranslationEntry(
            "Machine Hip Abduction",
            R.string.exercise_machine_hip_abduction,
            "Abducción de cadera en máquina"
        ),
        TranslationEntry(
            "Machine Incline Chest Press",
            R.string.exercise_machine_incline_chest_press,
            "Press inclinado de pecho en máquina"
        ),
        TranslationEntry(
            "Machine Lat Pulldown",
            R.string.exercise_machine_lat_pulldown,
            "Jalón dorsal en máquina"
        ),
        TranslationEntry(
            "Machine Lateral Raise",
            R.string.exercise_machine_lateral_raise,
            "Elevaciones laterales en máquina"
        ),
        TranslationEntry(
            "Machine Preacher Curls",
            R.string.exercise_machine_preacher_curls,
            "Curl predicador en máquina"
        ),
        TranslationEntry(
            "Machine Seated Calf Raise",
            R.string.exercise_machine_seated_calf_raise,
            "Elevación de gemelos sentado en máquina"
        ),
        TranslationEntry(
            "Machine Shoulder (Military) Press",
            R.string.exercise_machine_shoulder_military_press,
            "Press militar de hombros en máquina"
        ),
        TranslationEntry(
            "Machine Side Lateral Raises",
            R.string.exercise_machine_side_lateral_raises,
            "Elevaciones laterales en máquina guiada"
        ),
        TranslationEntry(
            "Machine Triceps Extension",
            R.string.exercise_machine_triceps_extension,
            "Extensión de tríceps en máquina"
        ),
        TranslationEntry(
            "March or jog in place",
            R.string.exercise_march_or_jog_in_place,
            "Marcha o trote en el sitio"
        ),
        TranslationEntry(
            "Marching High Knees",
            R.string.exercise_marching_high_knees,
            "Marcha con rodillas altas"
        ),
        TranslationEntry(
            "Medicine ball booklet crunch",
            R.string.exercise_medicine_ball_booklet_crunch,
            "Crunch libro con balón medicinal"
        ),
        TranslationEntry(
            "Medicine Ball Chest Pass",
            R.string.exercise_medicine_ball_chest_pass,
            "Pase de pecho con balón medicinal"
        ),
        TranslationEntry(
            "Medicine Ball Full Twist",
            R.string.exercise_medicine_ball_full_twist,
            "Giro completo con balón medicinal"
        ),
        TranslationEntry(
            "Medicine Ball Scoop Throw",
            R.string.exercise_medicine_ball_scoop_throw,
            "Lanzamiento en cuchara con balón medicinal"
        ),
        TranslationEntry(
            "Medicine Ball Slam",
            R.string.exercise_medicine_ball_slam,
            "Slam con balón medicinal"
        ),
        TranslationEntry(
            "Medicine Ball Throw Squat with Wall",
            R.string.exercise_medicine_ball_throw_squat_with_wall,
            "Sentadilla y lanzamiento a pared con balón"
        ),
        TranslationEntry(
            "Medicine ball twist",
            R.string.exercise_medicine_ball_twist,
            "Giro ruso con balón medicinal"
        ),
        TranslationEntry(
            "Middle Back Shrug",
            R.string.exercise_middle_back_shrug,
            "Encogimiento para espalda media"
        ),
        TranslationEntry(
            "Middle Back Stretch",
            R.string.exercise_middle_back_stretch,
            "Estiramiento de espalda media"
        ),
        TranslationEntry(
            "Mixed Grip Chin",
            R.string.exercise_mixed_grip_chin,
            "Dominada con agarre mixto"
        ),
        TranslationEntry(
            "Monster Walk",
            R.string.exercise_monster_walk,
            "Caminata de monstruo con banda"
        ),
        TranslationEntry(
            "Mountain Climbers",
            R.string.exercise_mountain_climbers,
            "Escaladores (mountain climbers)"
        ),
        TranslationEntry(
            "Moving Claw Series",
            R.string.exercise_moving_claw_series,
            "Serie de garra en movimiento"
        ),
        TranslationEntry(
            "Muscle Snatch",
            R.string.exercise_muscle_snatch,
            "Arrancada de fuerza (muscle snatch)"
        ),
        TranslationEntry(
            "Muscle-Up",
            R.string.exercise_muscle_up,
            "Muscle-up"
        ),
        TranslationEntry(
            "Muscleup on Vertical Bar",
            R.string.exercise_muscleup_on_vertical_bar,
            "Muscle-up en barra vertical"
        ),
        TranslationEntry(
            "Narrow Stance Hack Squats",
            R.string.exercise_narrow_stance_hack_squats,
            "Sentadilla hack con pies juntos"
        ),
        TranslationEntry(
            "Narrow Stance Leg Press",
            R.string.exercise_narrow_stance_leg_press,
            "Prensa de piernas con pies juntos máquina"
        ),
        TranslationEntry(
            "Narrow Stance Squats",
            R.string.exercise_narrow_stance_squats,
            "Sentadilla con pies juntos barra"
        ),
        TranslationEntry(
            "Natural Glute Ham Raise",
            R.string.exercise_natural_glute_ham_raise,
            "Elevación glúteo-femoral natural en suelo"
        ),
        TranslationEntry(
            "Neck CARs",
            R.string.exercise_neck_cars,
            "Rotaciones articulares controladas de cuello"
        ),
        TranslationEntry(
            "Neck half circles",
            R.string.exercise_neck_half_circles,
            "Semicírculos de cuello"
        ),
        TranslationEntry(
            "Neck Press",
            R.string.exercise_neck_press,
            "Press al cuello (Guillotine press)"
        ),
        TranslationEntry(
            "Neck-SMR",
            R.string.exercise_neck_smr,
            "Liberación miofascial de cuello"
        ),
        TranslationEntry(
            "Negative Crunches",
            R.string.exercise_negative_crunches,
            "Crunches negativos"
        ),
        TranslationEntry(
            "Neutral-grip pull-ups or TRX rows",
            R.string.exercise_neutral_grip_pull_ups_or_trx_rows,
            "Dominadas neutras o remo en TRX"
        ),
        TranslationEntry(
            "No push-up burpees",
            R.string.exercise_no_push_up_burpees,
            "Burpees sin flexión"
        ),
        TranslationEntry(
            "Nordic Curl",
            R.string.exercise_nordic_curl,
            "Curl nórdico"
        )
    )

    private val nameToEntry = entries.associateBy { it.englishName.lowercase() }

    fun getNameRes(name: String): Int? {
        return nameToEntry[name.lowercase()]?.nameRes
    }

    fun getSpanishName(name: String): String? {
        return nameToEntry[name.lowercase()]?.spanishFallback
    }

    fun matchesQuery(name: String, query: String): Boolean {
        if (query.isBlank()) return true
        val entry = nameToEntry[name.lowercase()] ?: return false
        return entry.englishName.contains(query, ignoreCase = true) ||
                entry.spanishFallback.contains(query, ignoreCase = true)
    }
}
