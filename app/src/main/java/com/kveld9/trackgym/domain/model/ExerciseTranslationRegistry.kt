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
