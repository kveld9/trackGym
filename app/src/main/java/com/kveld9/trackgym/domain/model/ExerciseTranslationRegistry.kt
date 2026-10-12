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
        ),
        TranslationEntry(
            "Nordic Hamstring Curl",
            R.string.exercise_nordic_hamstring_curl,
            "Curl femoral nórdico"
        ),
        TranslationEntry(
            "Oblique Crunches",
            R.string.exercise_oblique_crunches,
            "Crunches para oblicuos"
        ),
        TranslationEntry(
            "Oblique Crunches Floor",
            R.string.exercise_oblique_crunches_floor,
            "Crunches para oblicuos en suelo"
        ),
        TranslationEntry(
            "Olympic Squat",
            R.string.exercise_olympic_squat,
            "Sentadilla olímpica"
        ),
        TranslationEntry(
            "On Your Side Quad Stretch",
            R.string.exercise_on_your_side_quad_stretch,
            "Estiramiento de cuádriceps de lado"
        ),
        TranslationEntry(
            "On-Your-Back Quad Stretch",
            R.string.exercise_on_your_back_quad_stretch,
            "Estiramiento de cuádriceps boca arriba"
        ),
        TranslationEntry(
            "One Arm Against Wall",
            R.string.exercise_one_arm_against_wall,
            "Estiramiento a una mano contra la pared"
        ),
        TranslationEntry(
            "One Arm Chin-Up",
            R.string.exercise_one_arm_chin_up,
            "Dominada supina a una mano"
        ),
        TranslationEntry(
            "One Arm Dumbbell Bench Press",
            R.string.exercise_one_arm_dumbbell_bench_press,
            "Press de banca con mancuerna a una mano"
        ),
        TranslationEntry(
            "One Arm Dumbbell Preacher Curl",
            R.string.exercise_one_arm_dumbbell_preacher_curl,
            "Curl predicador con mancuerna a una mano"
        ),
        TranslationEntry(
            "One Arm Lat Pulldown",
            R.string.exercise_one_arm_lat_pulldown,
            "Jalón al pecho a un brazo"
        ),
        TranslationEntry(
            "One Arm Pronated Dumbbell Triceps Extension",
            R.string.exercise_one_arm_pronated_dumbbell_triceps_extension,
            "Extensión de tríceps pronada con mancuerna"
        ),
        TranslationEntry(
            "One Arm Supinated Dumbbell Triceps Extension",
            R.string.exercise_one_arm_supinated_dumbbell_triceps_extension,
            "Extensión de tríceps supinada con mancuerna"
        ),
        TranslationEntry(
            "One Half Locust",
            R.string.exercise_one_half_locust,
            "Media langosta (yoga)"
        ),
        TranslationEntry(
            "One Handed Hang",
            R.string.exercise_one_handed_hang,
            "Suspensión a una mano"
        ),
        TranslationEntry(
            "One Knee To Chest",
            R.string.exercise_one_knee_to_chest,
            "Rodilla al pecho"
        ),
        TranslationEntry(
            "One Leg Barbell Squat",
            R.string.exercise_one_leg_barbell_squat,
            "Sentadilla a una pierna con barra"
        ),
        TranslationEntry(
            "One-Arm Flat Bench Dumbbell Flye",
            R.string.exercise_one_arm_flat_bench_dumbbell_flye,
            "Aperturas planas con mancuerna a un brazo"
        ),
        TranslationEntry(
            "One-Arm High-Pulley Cable Side Bends",
            R.string.exercise_one_arm_high_pulley_cable_side_bends,
            "Flexión lateral en polea alta a un brazo"
        ),
        TranslationEntry(
            "One-Arm Incline Lateral Raise",
            R.string.exercise_one_arm_incline_lateral_raise,
            "Elevación lateral inclinada a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Clean",
            R.string.exercise_one_arm_kettlebell_clean,
            "Cargada con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Clean and Jerk",
            R.string.exercise_one_arm_kettlebell_clean_and_jerk,
            "Cargada y envión con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Floor Press",
            R.string.exercise_one_arm_kettlebell_floor_press,
            "Press de suelo con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Jerk",
            R.string.exercise_one_arm_kettlebell_jerk,
            "Envión con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Military Press To The Side",
            R.string.exercise_one_arm_kettlebell_military_press_to_the_side,
            "Press militar lateral con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Para Press",
            R.string.exercise_one_arm_kettlebell_para_press,
            "Press para con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Push Press",
            R.string.exercise_one_arm_kettlebell_push_press,
            "Push press con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Row",
            R.string.exercise_one_arm_kettlebell_row,
            "Remo con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Snatch",
            R.string.exercise_one_arm_kettlebell_snatch,
            "Arrancada con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Split Jerk",
            R.string.exercise_one_arm_kettlebell_split_jerk,
            "Envión en tijera con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Kettlebell Split Snatch",
            R.string.exercise_one_arm_kettlebell_split_snatch,
            "Arrancada en tijera con kettlebell a un brazo"
        ),
        TranslationEntry(
            "One-Arm Long Bar Row",
            R.string.exercise_one_arm_long_bar_row,
            "Remo a un brazo con barra larga"
        ),
        TranslationEntry(
            "One-Arm Medicine Ball Slam",
            R.string.exercise_one_arm_medicine_ball_slam,
            "Lanzamiento de balón medicinal a un brazo"
        ),
        TranslationEntry(
            "One-Arm Open Palm Kettlebell Clean",
            R.string.exercise_one_arm_open_palm_kettlebell_clean,
            "Cargada con palma abierta con kettlebell"
        ),
        TranslationEntry(
            "One-Arm Overhead Kettlebell Squats",
            R.string.exercise_one_arm_overhead_kettlebell_squats,
            "Sentadilla sobre la cabeza con kettlebell"
        ),
        TranslationEntry(
            "One-Arm Side Deadlift",
            R.string.exercise_one_arm_side_deadlift,
            "Peso muerto lateral a un brazo"
        ),
        TranslationEntry(
            "One-Arm Side Laterals",
            R.string.exercise_one_arm_side_laterals,
            "Elevaciones laterales a un brazo"
        ),
        TranslationEntry(
            "One-Legged Cable Kickback",
            R.string.exercise_one_legged_cable_kickback,
            "Patada de glúteo en polea a una pierna"
        ),
        TranslationEntry(
            "Open Book",
            R.string.exercise_open_book,
            "Libro abierto (movilidad torácica)"
        ),
        TranslationEntry(
            "Open Palm Kettlebell Clean",
            R.string.exercise_open_palm_kettlebell_clean,
            "Cargada con kettlebell palma abierta"
        ),
        TranslationEntry(
            "Otis-Up",
            R.string.exercise_otis_up,
            "Otis-up con peso"
        ),
        TranslationEntry(
            "Overhead Cable Curl",
            R.string.exercise_overhead_cable_curl,
            "Curl de bíceps en polea alta"
        ),
        TranslationEntry(
            "Overhead Lat",
            R.string.exercise_overhead_lat,
            "Estiramiento dorsal por encima de la cabeza"
        ),
        TranslationEntry(
            "Overhead Slam",
            R.string.exercise_overhead_slam,
            "Lanzamiento sobre la cabeza contra el suelo"
        ),
        TranslationEntry(
            "Overhead Squat",
            R.string.exercise_overhead_squat,
            "Sentadilla de sobrecabeza (overhead squat)"
        ),
        TranslationEntry(
            "Overhead Stretch",
            R.string.exercise_overhead_stretch,
            "Estiramiento sobre la cabeza"
        ),
        TranslationEntry(
            "Overhead Triceps",
            R.string.exercise_overhead_triceps,
            "Extensión de tríceps sobre la cabeza"
        ),
        TranslationEntry(
            "Pallof Press",
            R.string.exercise_pallof_press,
            "Press Pallof"
        ),
        TranslationEntry(
            "Pallof Press With Rotation",
            R.string.exercise_pallof_press_with_rotation,
            "Press Pallof con rotación"
        ),
        TranslationEntry(
            "Palms-Down Dumbbell Wrist Curl Over A Bench",
            R.string.exercise_palms_down_dumbbell_wrist_curl_over_a_bench,
            "Curl de antebrazo pronado en banco con mancuernas"
        ),
        TranslationEntry(
            "Palms-Down Wrist Curl Over A Bench",
            R.string.exercise_palms_down_wrist_curl_over_a_bench,
            "Curl de antebrazo pronado en banco con barra"
        ),
        TranslationEntry(
            "Palms-Up Barbell Wrist Curl Over A Bench",
            R.string.exercise_palms_up_barbell_wrist_curl_over_a_bench,
            "Curl de antebrazo supinado en banco con barra"
        ),
        TranslationEntry(
            "Palms-Up Dumbbell Wrist Curl Over A Bench",
            R.string.exercise_palms_up_dumbbell_wrist_curl_over_a_bench,
            "Curl de antebrazo supinado en banco con mancuernas"
        ),
        TranslationEntry(
            "Parallel Bar Hold",
            R.string.exercise_parallel_bar_hold,
            "Soporte isométrico en paralelas"
        ),
        TranslationEntry(
            "Pause Hack Squats",
            R.string.exercise_pause_hack_squats,
            "Sentadilla hack con pausa"
        ),
        TranslationEntry(
            "Pec Deck",
            R.string.exercise_pec_deck,
            "Contractor de pecho (pec deck)"
        ),
        TranslationEntry(
            "Pec deck rear delt fly",
            R.string.exercise_pec_deck_rear_delt_fly,
            "Aperturas posteriores en pec deck"
        ),
        TranslationEntry(
            "Pelvic Endurance Hold",
            R.string.exercise_pelvic_endurance_hold,
            "Retención pélvica isométrica"
        ),
        TranslationEntry(
            "Pelvic Tilt Into Bridge",
            R.string.exercise_pelvic_tilt_into_bridge,
            "Basculación pélvica a puente"
        ),
        TranslationEntry(
            "Pelvic Tilts",
            R.string.exercise_pelvic_tilts,
            "Basculación pélvica"
        ),
        TranslationEntry(
            "Pelvic Wave",
            R.string.exercise_pelvic_wave,
            "Onda pélvica"
        ),
        TranslationEntry(
            "Pendular hack",
            R.string.exercise_pendular_hack,
            "Sentadilla hack pendular"
        ),
        TranslationEntry(
            "Pendulum Squat",
            R.string.exercise_pendulum_squat,
            "Sentadilla péndulo"
        ),
        TranslationEntry(
            "Peroneals Stretch",
            R.string.exercise_peroneals_stretch,
            "Estiramiento de peroneos"
        ),
        TranslationEntry(
            "Peroneals-SMR",
            R.string.exercise_peroneals_smr,
            "Liberación miofascial de peroneos"
        ),
        TranslationEntry(
            "Physioball Hip Bridge",
            R.string.exercise_physioball_hip_bridge,
            "Puente de glúteos sobre fitball"
        ),
        TranslationEntry(
            "Pigeon Stretch",
            R.string.exercise_pigeon_stretch,
            "Postura de la paloma (estiramiento)"
        ),
        TranslationEntry(
            "Pike Push Ups",
            R.string.exercise_pike_push_ups,
            "Flexiones en pica"
        ),
        TranslationEntry(
            "Pin Presses",
            R.string.exercise_pin_presses,
            "Press desde soportes (pin press)"
        ),
        TranslationEntry(
            "Piriformis-SMR",
            R.string.exercise_piriformis_smr,
            "Liberación miofascial del piriforme"
        ),
        TranslationEntry(
            "Pistol Squat",
            R.string.exercise_pistol_squat,
            "Sentadilla pistol"
        ),
        TranslationEntry(
            "Pistol squats right",
            R.string.exercise_pistol_squats_right,
            "Sentadilla pistol derecha"
        ),
        TranslationEntry(
            "Planche Lean Hold",
            R.string.exercise_planche_lean_hold,
            "Inclinación de plancha isométrica"
        ),
        TranslationEntry(
            "Plank Alternate Knee Tuck",
            R.string.exercise_plank_alternate_knee_tuck,
            "Plancha con flexión alterna de rodilla"
        ),
        TranslationEntry(
            "Plank Jacks",
            R.string.exercise_plank_jacks,
            "Plancha jack"
        ),
        TranslationEntry(
            "Plank Reach",
            R.string.exercise_plank_reach,
            "Plancha con alcance de brazo"
        ),
        TranslationEntry(
            "Plank Shoulder Taps",
            R.string.exercise_plank_shoulder_taps,
            "Plancha con toques al hombro"
        ),
        TranslationEntry(
            "Plank with Alternating Leg Lift",
            R.string.exercise_plank_with_alternating_leg_lift,
            "Plancha con elevación alterna de pierna"
        ),
        TranslationEntry(
            "Plank-to-Elbow Extension",
            R.string.exercise_plank_to_elbow_extension,
            "Plancha a extensión de codos"
        ),
        TranslationEntry(
            "Plantarflexion Stretch with Band",
            R.string.exercise_plantarflexion_stretch_with_band,
            "Estiramiento de flexión plantar con banda"
        ),
        TranslationEntry(
            "Plate Pinch",
            R.string.exercise_plate_pinch,
            "Pellizco de discos"
        ),
        TranslationEntry(
            "Plate Pinch Hold",
            R.string.exercise_plate_pinch_hold,
            "Agarre en pinza con disco isométrico"
        ),
        TranslationEntry(
            "Plate Twist",
            R.string.exercise_plate_twist,
            "Giros de torso con disco"
        ),
        TranslationEntry(
            "Plate-Loaded Lat Pulldown",
            R.string.exercise_plate_loaded_lat_pulldown,
            "Jalón al pecho de carga con discos"
        ),
        TranslationEntry(
            "Platform Hamstring Slides",
            R.string.exercise_platform_hamstring_slides,
            "Deslizamientos femorales en plataforma"
        ),
        TranslationEntry(
            "Plie Dumbbell Squat",
            R.string.exercise_plie_dumbbell_squat,
            "Sentadilla plié con mancuerna"
        ),
        TranslationEntry(
            "Plyo Kettlebell Pushups",
            R.string.exercise_plyo_kettlebell_pushups,
            "Flexiones pliométricas sobre kettlebells"
        ),
        TranslationEntry(
            "Plyo Push-up",
            R.string.exercise_plyo_push_up,
            "Flexiones pliométricas"
        ),
        TranslationEntry(
            "Posterior Pelvic Tilt",
            R.string.exercise_posterior_pelvic_tilt,
            "Retroversión pélvica"
        ),
        TranslationEntry(
            "Posterior Tibialis Stretch",
            R.string.exercise_posterior_tibialis_stretch,
            "Estiramiento del tibial posterior"
        ),
        TranslationEntry(
            "Power Clean",
            R.string.exercise_power_clean,
            "Cargada de fuerza (power clean)"
        ),
        TranslationEntry(
            "Power Clean from Blocks",
            R.string.exercise_power_clean_from_blocks,
            "Cargada de fuerza desde bloques"
        ),
        TranslationEntry(
            "Power Jerk",
            R.string.exercise_power_jerk,
            "Envión de fuerza (power jerk)"
        ),
        TranslationEntry(
            "Power Partials",
            R.string.exercise_power_partials,
            "Repeticiones parciales de potencia"
        ),
        TranslationEntry(
            "Power Sled Pull",
            R.string.exercise_power_sled_pull,
            "Arrastre de trineo de potencia"
        ),
        TranslationEntry(
            "Power Sled Push",
            R.string.exercise_power_sled_push,
            "Empuje de trineo de potencia"
        ),
        TranslationEntry(
            "Power Snatch",
            R.string.exercise_power_snatch,
            "Arrancada de fuerza (power snatch)"
        ),
        TranslationEntry(
            "Power Snatch from Blocks",
            R.string.exercise_power_snatch_from_blocks,
            "Arrancada de fuerza desde bloques"
        ),
        TranslationEntry(
            "Power Stairs",
            R.string.exercise_power_stairs,
            "Escaleras de fuerza (power stairs)"
        ),
        TranslationEntry(
            "Preacher Curl",
            R.string.exercise_preacher_curl,
            "Curl predicador con barra"
        ),
        TranslationEntry(
            "Preacher Hammer Dumbbell Curl",
            R.string.exercise_preacher_hammer_dumbbell_curl,
            "Curl martillo predicador con mancuerna"
        ),
        TranslationEntry(
            "Press Sit-Up",
            R.string.exercise_press_sit_up,
            "Sit-up con press"
        ),
        TranslationEntry(
            "Press Under",
            R.string.exercise_press_under,
            "Press under con barra"
        ),
        TranslationEntry(
            "Prisoner Squat",
            R.string.exercise_prisoner_squat,
            "Sentadilla de prisionero"
        ),
        TranslationEntry(
            "Prone Manual Hamstring",
            R.string.exercise_prone_manual_hamstring,
            "Isquiosurales manuales en decúbito prono"
        ),
        TranslationEntry(
            "Prone Scapular Retraction - Arms at Side",
            R.string.exercise_prone_scapular_retraction_arms_at_side,
            "Retracción escapular prona brazos a los lados"
        ),
        TranslationEntry(
            "Prowler Sprint",
            R.string.exercise_prowler_sprint,
            "Esprint con prowler"
        ),
        TranslationEntry(
            "Pseudo Planche Push-up",
            R.string.exercise_pseudo_planche_push_up,
            "Flexiones en pseudoplancha"
        ),
        TranslationEntry(
            "Pull Through",
            R.string.exercise_pull_through,
            "Pull-through en polea"
        ),
        TranslationEntry(
            "Pull Ups on Machine",
            R.string.exercise_pull_ups_on_machine,
            "Dominadas en máquina asistida"
        ),
        TranslationEntry(
            "Pull-Up (negative)",
            R.string.exercise_pull_up_negative,
            "Dominadas excéntricas (negativas)"
        ),
        TranslationEntry(
            "Pull-up Isometric Hold",
            R.string.exercise_pull_up_isometric_hold,
            "Dominada isométrica"
        ),
        TranslationEntry(
            "Pull-Ups (Neutral Grip)",
            R.string.exercise_pull_ups_neutral_grip,
            "Dominadas con agarre neutro"
        ),
        TranslationEntry(
            "Pull-Ups (Wide Grip)",
            R.string.exercise_pull_ups_wide_grip,
            "Dominadas con agarre ancho"
        ),
        TranslationEntry(
            "Pullover Machine",
            R.string.exercise_pullover_machine,
            "Pullover en máquina"
        ),
        TranslationEntry(
            "Pullup on fingerboard",
            R.string.exercise_pullup_on_fingerboard,
            "Dominadas en tabla de suspensión"
        ),
        TranslationEntry(
            "Punches",
            R.string.exercise_punches,
            "Golpes de puño"
        ),
        TranslationEntry(
            "Push Press",
            R.string.exercise_push_press,
            "Push press"
        ),
        TranslationEntry(
            "Push Press - Behind the Neck",
            R.string.exercise_push_press_behind_the_neck,
            "Push press tras nuca"
        ),
        TranslationEntry(
            "Push Up to Side Plank",
            R.string.exercise_push_up_to_side_plank,
            "Flexión a plancha lateral"
        ),
        TranslationEntry(
            "Push-Up",
            R.string.exercise_push_up,
            "Flexiones estándar"
        ),
        TranslationEntry(
            "Push-up rotations",
            R.string.exercise_push_up_rotations,
            "Flexiones con rotación"
        ),
        TranslationEntry(
            "Push-Up Wide",
            R.string.exercise_push_up_wide,
            "Flexiones con agarre ancho"
        ),
        TranslationEntry(
            "Push-Ups - Close Triceps Position",
            R.string.exercise_push_ups_close_triceps_position,
            "Flexiones diamante para tríceps"
        ),
        TranslationEntry(
            "Push-Ups With Feet Elevated",
            R.string.exercise_push_ups_with_feet_elevated,
            "Flexiones declinadas con pies elevados"
        ),
        TranslationEntry(
            "Push-Ups With Feet On An Exercise Ball",
            R.string.exercise_push_ups_with_feet_on_an_exercise_ball,
            "Flexiones con pies en fitball"
        ),
        TranslationEntry(
            "Push-Ups | Parallettes",
            R.string.exercise_push_ups_parallettes,
            "Flexiones en barras paralelas bajas"
        ),
        TranslationEntry(
            "Pushup on Knees",
            R.string.exercise_pushup_on_knees,
            "Flexiones de rodillas"
        ),
        TranslationEntry(
            "Pushups (Close and Wide Hand Positions)",
            R.string.exercise_pushups_close_and_wide_hand_positions,
            "Flexiones variando apertura de manos"
        ),
        TranslationEntry(
            "Pyramid",
            R.string.exercise_pyramid,
            "Postura de la pirámide (yoga)"
        ),
        TranslationEntry(
            "Quad Sets",
            R.string.exercise_quad_sets,
            "Contracciones isométricas de cuádriceps"
        ),
        TranslationEntry(
            "Quad Stretch",
            R.string.exercise_quad_stretch,
            "Estiramiento de cuádriceps"
        ),
        TranslationEntry(
            "Quadriceps-SMR",
            R.string.exercise_quadriceps_smr,
            "Liberación miofascial de cuádriceps"
        ),
        TranslationEntry(
            "Quadriped Arm and Leg Raise",
            R.string.exercise_quadriped_arm_and_leg_raise,
            "Pájaro-perro en cuadrupedia"
        ),
        TranslationEntry(
            "Quadruped Hip Abduction",
            R.string.exercise_quadruped_hip_abduction,
            "Abducción de cadera en cuadrupedia con banda"
        ),
        TranslationEntry(
            "Quadruped thoracic rotation left",
            R.string.exercise_quadruped_thoracic_rotation_left,
            "Rotación torácica en cuadrupedia izquierda"
        ),
        TranslationEntry(
            "Quadruped thoracic rotation right",
            R.string.exercise_quadruped_thoracic_rotation_right,
            "Rotación torácica en cuadrupedia derecha"
        ),
        TranslationEntry(
            "Quick Kegels",
            R.string.exercise_quick_kegels,
            "Contracciones rápidas de Kegel"
        ),
        TranslationEntry(
            "Quick Leap",
            R.string.exercise_quick_leap,
            "Salto rápido hacia adelante"
        ),
        TranslationEntry(
            "Rack Deadlift",
            R.string.exercise_rack_deadlift,
            "Peso muerto desde el rack"
        ),
        TranslationEntry(
            "Rack Delivery",
            R.string.exercise_rack_delivery,
            "Recepción en rack con barra"
        ),
        TranslationEntry(
            "Rack Pull with Bands",
            R.string.exercise_rack_pull_with_bands,
            "Rack pull con bandas elásticas"
        ),
        TranslationEntry(
            "Rack Pulls",
            R.string.exercise_rack_pulls,
            "Rack pulls desde soportes"
        ),
        TranslationEntry(
            "Radio Taiso",
            R.string.exercise_radio_taiso,
            "Radio Taiso (calistenia japonesa)"
        ),
        TranslationEntry(
            "Reach ups",
            R.string.exercise_reach_ups,
            "Crunches con alcance vertical"
        ),
        TranslationEntry(
            "Rear Decline Bridge",
            R.string.exercise_rear_decline_bridge,
            "Puente invertido en declive"
        ),
        TranslationEntry(
            "Rear Leg Raises",
            R.string.exercise_rear_leg_raises,
            "Elevaciones posteriores de pierna"
        ),
        TranslationEntry(
            "Rear-foot-elevated Hip Flexor Stretch",
            R.string.exercise_rear_foot_elevated_hip_flexor_stretch,
            "Estiramiento flexor cadera pie elevado atrás"
        ),
        TranslationEntry(
            "Recovery Bobbing",
            R.string.exercise_recovery_bobbing,
            "Rebotes de recuperación en agua"
        ),
        TranslationEntry(
            "Recruitment Pulls",
            R.string.exercise_recruitment_pulls,
            "Tirones de reclutamiento muscular"
        ),
        TranslationEntry(
            "Recumbent Bike",
            R.string.exercise_recumbent_bike,
            "Bicicleta reclinada"
        ),
        TranslationEntry(
            "Relaxed Diaphragmatic Breathing",
            R.string.exercise_relaxed_diaphragmatic_breathing,
            "Respiración diafragmática relajada"
        ),
        TranslationEntry(
            "Resistance Band Hammer Curl",
            R.string.exercise_resistance_band_hammer_curl,
            "Curl martillo con banda de resistencia"
        ),
        TranslationEntry(
            "Resistance Band Lateral Walk",
            R.string.exercise_resistance_band_lateral_walk,
            "Paseo lateral con banda de resistencia"
        ),
        TranslationEntry(
            "Resistance Band Side Plank Clamshell",
            R.string.exercise_resistance_band_side_plank_clamshell,
            "Plancha lateral con clamshell y banda"
        ),
        TranslationEntry(
            "Rest Timer",
            R.string.exercise_rest_timer,
            "Temporizador de descanso"
        ),
        TranslationEntry(
            "Retro Treadmill Walking",
            R.string.exercise_retro_treadmill_walking,
            "Caminata hacia atrás en cinta"
        ),
        TranslationEntry(
            "Return Push from Stance",
            R.string.exercise_return_push_from_stance,
            "Empuje de retorno desde posición"
        ),
        TranslationEntry(
            "Reverse Band Bench Press",
            R.string.exercise_reverse_band_bench_press,
            "Press de banca con banda invertida"
        ),
        TranslationEntry(
            "Reverse Band Box Squat",
            R.string.exercise_reverse_band_box_squat,
            "Sentadilla a cajón con banda invertida"
        ),
        TranslationEntry(
            "Reverse Band Deadlift",
            R.string.exercise_reverse_band_deadlift,
            "Peso muerto con banda invertida"
        ),
        TranslationEntry(
            "Reverse Band Power Squat",
            R.string.exercise_reverse_band_power_squat,
            "Sentadilla de potencia con banda invertida"
        ),
        TranslationEntry(
            "Reverse Band Sumo Deadlift",
            R.string.exercise_reverse_band_sumo_deadlift,
            "Peso muerto sumo con banda invertida"
        ),
        TranslationEntry(
            "Reverse Barbell Preacher Curls",
            R.string.exercise_reverse_barbell_preacher_curls,
            "Curl predicador invertido con barra"
        ),
        TranslationEntry(
            "Reverse Cable Curl",
            R.string.exercise_reverse_cable_curl,
            "Curl invertido en polea"
        ),
        TranslationEntry(
            "Reverse Clamshell",
            R.string.exercise_reverse_clamshell,
            "Clamshell inverso"
        ),
        TranslationEntry(
            "Reverse Crunch",
            R.string.exercise_reverse_crunch,
            "Crunch invertido"
        ),
        TranslationEntry(
            "Reverse Flyes",
            R.string.exercise_reverse_flyes,
            "Aperturas inversas con mancuernas"
        ),
        TranslationEntry(
            "Reverse Flyes With External Rotation",
            R.string.exercise_reverse_flyes_with_external_rotation,
            "Aperturas inversas con rotación externa"
        ),
        TranslationEntry(
            "Reverse Grip Bent-Over Rows",
            R.string.exercise_reverse_grip_bent_over_rows,
            "Remo inclinado con agarre supino"
        ),
        TranslationEntry(
            "Reverse Grip Triceps Pushdown",
            R.string.exercise_reverse_grip_triceps_pushdown,
            "Extensión de tríceps en polea agarre invertido"
        ),
        TranslationEntry(
            "Reverse Hyperextension",
            R.string.exercise_reverse_hyperextension,
            "Hiperextensión inversa en máquina"
        ),
        TranslationEntry(
            "Reverse lunges",
            R.string.exercise_reverse_lunges,
            "Zancadas hacia atrás"
        ),
        TranslationEntry(
            "Reverse Machine Flyes",
            R.string.exercise_reverse_machine_flyes,
            "Aperturas inversas en máquina"
        ),
        TranslationEntry(
            "Reverse Nordic Curl",
            R.string.exercise_reverse_nordic_curl,
            "Curl nórdico inverso"
        ),
        TranslationEntry(
            "Reverse Plank",
            R.string.exercise_reverse_plank,
            "Plancha inversa"
        ),
        TranslationEntry(
            "Reverse Plate Curls",
            R.string.exercise_reverse_plate_curls,
            "Curl invertido con disco"
        ),
        TranslationEntry(
            "Reverse Snow Angel",
            R.string.exercise_reverse_snow_angel,
            "Ángel de nieve inverso"
        ),
        TranslationEntry(
            "Reverse Triceps Bench Press",
            R.string.exercise_reverse_triceps_bench_press,
            "Press de banca con agarre invertido tríceps"
        ),
        TranslationEntry(
            "Reverse V-Squat",
            R.string.exercise_reverse_v_squat,
            "Sentadilla en V inversa"
        ),
        TranslationEntry(
            "Reverse Wood Chops",
            R.string.exercise_reverse_wood_chops,
            "Leñador inverso"
        ),
        TranslationEntry(
            "Reverse-grip pull-ups",
            R.string.exercise_reverse_grip_pull_ups,
            "Dominadas con agarre supino"
        ),
        TranslationEntry(
            "Rhomboids-SMR",
            R.string.exercise_rhomboids_smr,
            "Liberación miofascial de romboides"
        ),
        TranslationEntry(
            "Rickshaw Carry",
            R.string.exercise_rickshaw_carry,
            "Paseo del granjero en rickshaw"
        ),
        TranslationEntry(
            "Rickshaw Deadlift",
            R.string.exercise_rickshaw_deadlift,
            "Peso muerto en rickshaw"
        ),
        TranslationEntry(
            "Right levator scapulae stretch",
            R.string.exercise_right_levator_scapulae_stretch,
            "Estiramiento del elevador escápula derecho"
        ),
        TranslationEntry(
            "Right neck stretch",
            R.string.exercise_right_neck_stretch,
            "Estiramiento del cuello lado derecho"
        ),
        TranslationEntry(
            "Ring Dips",
            R.string.exercise_ring_dips,
            "Fondos en anillas"
        ),
        TranslationEntry(
            "Ring Pull-Up",
            R.string.exercise_ring_pull_up,
            "Dominadas en anillas"
        ),
        TranslationEntry(
            "Ring Support Hold",
            R.string.exercise_ring_support_hold,
            "Soporte isométrico en anillas"
        ),
        TranslationEntry(
            "Rocket Jump",
            R.string.exercise_rocket_jump,
            "Salto del cohete"
        ),
        TranslationEntry(
            "Rocking Standing Calf Raise",
            R.string.exercise_rocking_standing_calf_raise,
            "Elevación de gemelos con balanceo de pie"
        ),
        TranslationEntry(
            "Rocky Pull-Ups/Pulldowns",
            R.string.exercise_rocky_pull_ups_pulldowns,
            "Dominadas o jalones estilo Rocky"
        ),
        TranslationEntry(
            "Roll Down",
            R.string.exercise_roll_down,
            "Desenrollamiento de columna vertebral"
        ),
        TranslationEntry(
            "Roll-overs into V-sits",
            R.string.exercise_roll_overs_into_v_sits,
            "Rodamiento hacia atrás a sentado en V"
        ),
        TranslationEntry(
            "Roman Chair Crunch",
            R.string.exercise_roman_chair_crunch,
            "Crunch en silla romana"
        ),
        TranslationEntry(
            "Romanian Deadlift from Deficit",
            R.string.exercise_romanian_deadlift_from_deficit,
            "Peso muerto rumano desde déficit"
        ),
        TranslationEntry(
            "Rope Climb",
            R.string.exercise_rope_climb,
            "Subida de cuerda"
        ),
        TranslationEntry(
            "Rope Crunch",
            R.string.exercise_rope_crunch,
            "Crunch en polea con cuerda"
        ),
        TranslationEntry(
            "Rope Jumping",
            R.string.exercise_rope_jumping,
            "Salto de comba"
        ),
        TranslationEntry(
            "Rope Straight-Arm Pulldown",
            R.string.exercise_rope_straight_arm_pulldown,
            "Jalón con brazos rectos en polea con cuerda"
        ),
        TranslationEntry(
            "Rotary Torso Machine",
            R.string.exercise_rotary_torso_machine,
            "Máquina de torsión de torso"
        ),
        TranslationEntry(
            "Round The World Shoulder Stretch",
            R.string.exercise_round_the_world_shoulder_stretch,
            "Estiramiento de hombros alrededor del mundo"
        ),
        TranslationEntry(
            "Rowing Machine",
            R.string.exercise_rowing_machine,
            "Remo en máquina ergométrica"
        ),
        TranslationEntry(
            "Rowing seated, narrow grip",
            R.string.exercise_rowing_seated_narrow_grip,
            "Remo sentado con agarre estrecho"
        ),
        TranslationEntry(
            "Rowing Straight Back with Rowing Machine",
            R.string.exercise_rowing_straight_back_with_rowing_machine,
            "Remo con espalda recta en máquina"
        ),
        TranslationEntry(
            "Rowing with Rowing Machine",
            R.string.exercise_rowing_with_rowing_machine,
            "Remo en máquina de cardio"
        ),
        TranslationEntry(
            "Rowing with TRX band",
            R.string.exercise_rowing_with_trx_band,
            "Remo con correas TRX"
        ),
        TranslationEntry(
            "Rowing, Lying on Bench",
            R.string.exercise_rowing_lying_on_bench,
            "Remo acostado sobre banco"
        ),
        TranslationEntry(
            "Rowing, Stationary",
            R.string.exercise_rowing_stationary,
            "Remo estacionario"
        ),
        TranslationEntry(
            "Rowing, T-bar",
            R.string.exercise_rowing_t_bar,
            "Remo en barra T"
        ),
        TranslationEntry(
            "Rubber Band Glute Kickback",
            R.string.exercise_rubber_band_glute_kickback,
            "Patada de glúteos con banda elástica"
        ),
        TranslationEntry(
            "Run",
            R.string.exercise_run,
            "Carrera continua"
        ),
        TranslationEntry(
            "Run - Interval Training",
            R.string.exercise_run_interval_training,
            "Carrera en intervalos"
        ),
        TranslationEntry(
            "Run - Treadmill",
            R.string.exercise_run_treadmill,
            "Carrera en cinta de correr"
        ),
        TranslationEntry(
            "Runner's Stretch",
            R.string.exercise_runners_stretch,
            "Estiramiento del corredor"
        ),
        TranslationEntry(
            "Runners Lunge Stretch",
            R.string.exercise_runners_lunge_stretch,
            "Estiramiento en zancada de corredor"
        ),
        TranslationEntry(
            "Running",
            R.string.exercise_running,
            "Correr"
        ),
        TranslationEntry(
            "Running, Treadmill",
            R.string.exercise_running_treadmill,
            "Correr en cinta estática"
        ),
        TranslationEntry(
            "Russian Push Ups",
            R.string.exercise_russian_push_ups,
            "Flexiones rusas"
        ),
        TranslationEntry(
            "Russian Twist",
            R.string.exercise_russian_twist,
            "Giros rusos"
        ),
        TranslationEntry(
            "Russian Twist Beginner Level",
            R.string.exercise_russian_twist_beginner_level,
            "Giros rusos nivel principiante"
        ),
        TranslationEntry(
            "Sandbag Load",
            R.string.exercise_sandbag_load,
            "Carga de saco de arena"
        ),
        TranslationEntry(
            "Scapula Pulls",
            R.string.exercise_scapula_pulls,
            "Tirones escapulares en barra"
        ),
        TranslationEntry(
            "Scapular Pull-Up",
            R.string.exercise_scapular_pull_up,
            "Dominadas escapulares"
        ),
        TranslationEntry(
            "Swimming",
            R.string.exercise_swimming,
            "Natación"
        ),
        TranslationEntry(
            "Scissor Kick",
            R.string.exercise_scissor_kick,
            "Patadas de tijera"
        ),
        TranslationEntry(
            "Scissors",
            R.string.exercise_scissors,
            "Tijeras para abdomen"
        ),
        TranslationEntry(
            "Scissors Jump",
            R.string.exercise_scissors_jump,
            "Salto con tijeras"
        ),
        TranslationEntry(
            "Scorpion Kick",
            R.string.exercise_scorpion_kick,
            "Patada del escorpión"
        ),
        TranslationEntry(
            "Seated Band Hamstring Curl",
            R.string.exercise_seated_band_hamstring_curl,
            "Curl femoral sentado con banda elástica"
        ),
        TranslationEntry(
            "Seated Band Row",
            R.string.exercise_seated_band_row,
            "Remo sentado con banda elástica"
        ),
        TranslationEntry(
            "Seated Barbell Military Press",
            R.string.exercise_seated_barbell_military_press,
            "Press militar sentado con barra"
        ),
        TranslationEntry(
            "Seated Barbell Twist",
            R.string.exercise_seated_barbell_twist,
            "Giros rusos sentado con barra"
        ),
        TranslationEntry(
            "Seated Bench Press",
            R.string.exercise_seated_bench_press,
            "Press de pecho sentado"
        ),
        TranslationEntry(
            "Seated Bent-Over One-Arm Dumbbell Triceps Extension",
            R.string.exercise_seated_bent_over_one_arm_dumbbell_triceps_extension,
            "Extensión patada tríceps sentado una mano"
        ),
        TranslationEntry(
            "Seated Bent-Over Rear Delt Raise",
            R.string.exercise_seated_bent_over_rear_delt_raise,
            "Pájaros sentado para deltoides posterior"
        ),
        TranslationEntry(
            "Seated Bent-Over Two-Arm Dumbbell Triceps Extension",
            R.string.exercise_seated_bent_over_two_arm_dumbbell_triceps_extension,
            "Patada de tríceps sentado a dos manos"
        ),
        TranslationEntry(
            "Seated Biceps",
            R.string.exercise_seated_biceps,
            "Curl de bíceps sentado"
        ),
        TranslationEntry(
            "Seated Cable Shoulder Press",
            R.string.exercise_seated_cable_shoulder_press,
            "Press de hombros sentado en polea"
        ),
        TranslationEntry(
            "Seated Calf Raise",
            R.string.exercise_seated_calf_raise,
            "Elevación de gemelos sentado en máquina"
        ),
        TranslationEntry(
            "Seated Calf Stretch",
            R.string.exercise_seated_calf_stretch,
            "Estiramiento de gemelos sentado"
        ),
        TranslationEntry(
            "Seated Close-Grip Concentration Barbell Curl",
            R.string.exercise_seated_close_grip_concentration_barbell_curl,
            "Curl concentrado sentado barra agarre estrecho"
        ),
        TranslationEntry(
            "Seated Corkscrew",
            R.string.exercise_seated_corkscrew,
            "Sacacorchos sentado"
        ),
        TranslationEntry(
            "Seated Dumbbell Curl",
            R.string.exercise_seated_dumbbell_curl,
            "Curl de bíceps sentado con mancuernas"
        ),
        TranslationEntry(
            "Seated Dumbbell Inner Biceps Curl",
            R.string.exercise_seated_dumbbell_inner_biceps_curl,
            "Curl bíceps interno sentado con mancuernas"
        ),
        TranslationEntry(
            "Seated Dumbbell Palms-Down Wrist Curl",
            R.string.exercise_seated_dumbbell_palms_down_wrist_curl,
            "Curl antebrazo pronado sentado con mancuernas"
        ),
        TranslationEntry(
            "Seated Dumbbell Press",
            R.string.exercise_seated_dumbbell_press,
            "Press de hombros sentado con mancuernas"
        ),
        TranslationEntry(
            "Seated Figure Four",
            R.string.exercise_seated_figure_four,
            "Estiramiento figura 4 sentado"
        ),
        TranslationEntry(
            "Seated Flat Bench Leg Pull-In",
            R.string.exercise_seated_flat_bench_leg_pull_in,
            "Encogimientos de piernas sentado en banco plano"
        ),
        TranslationEntry(
            "Seated Floor Hamstring Stretch",
            R.string.exercise_seated_floor_hamstring_stretch,
            "Estiramiento de isquiosurales sentado en suelo"
        ),
        TranslationEntry(
            "Seated Front Deltoid",
            R.string.exercise_seated_front_deltoid,
            "Elevación frontal sentado"
        ),
        TranslationEntry(
            "Seated Glute",
            R.string.exercise_seated_glute,
            "Estiramiento de glúteos sentado"
        ),
        TranslationEntry(
            "Seated Good Mornings",
            R.string.exercise_seated_good_mornings,
            "Buenos días sentado con barra"
        ),
        TranslationEntry(
            "Seated Hamstring",
            R.string.exercise_seated_hamstring,
            "Estiramiento femoral sentado"
        ),
        TranslationEntry(
            "Seated Hamstring and Calf Stretch",
            R.string.exercise_seated_hamstring_and_calf_stretch,
            "Estiramiento de femoral y gemelo sentado"
        ),
        TranslationEntry(
            "Seated Head Harness Neck Resistance",
            R.string.exercise_seated_head_harness_neck_resistance,
            "Resistencia de cuello sentado con arnés"
        ),
        TranslationEntry(
            "Seated Hip Abduction",
            R.string.exercise_seated_hip_abduction,
            "Abducción de cadera sentado"
        ),
        TranslationEntry(
            "Seated Hip Adduction",
            R.string.exercise_seated_hip_adduction,
            "Aducción de cadera sentado"
        ),
        TranslationEntry(
            "Seated Knee Extension with Overpressure",
            R.string.exercise_seated_knee_extension_with_overpressure,
            "Extensión de rodilla sentada con sobrepresión"
        ),
        TranslationEntry(
            "Seated Knee Tuck",
            R.string.exercise_seated_knee_tuck,
            "Flexión de rodillas al pecho sentado"
        ),
        TranslationEntry(
            "Seated Leg Curl",
            R.string.exercise_seated_leg_curl,
            "Curl de piernas sentado en máquina"
        ),
        TranslationEntry(
            "Seated Leg Tucks",
            R.string.exercise_seated_leg_tucks,
            "Encogimiento de piernas sentado"
        ),
        TranslationEntry(
            "Seated One-arm Cable Pulley Rows",
            R.string.exercise_seated_one_arm_cable_pulley_rows,
            "Remo sentado en polea a un brazo"
        ),
        TranslationEntry(
            "Seated One-Arm Dumbbell Palms-Down Wrist Curl",
            R.string.exercise_seated_one_arm_dumbbell_palms_down_wrist_curl,
            "Curl antebrazo pronado a una mano en banco"
        ),
        TranslationEntry(
            "Seated One-Arm Dumbbell Palms-Up Wrist Curl",
            R.string.exercise_seated_one_arm_dumbbell_palms_up_wrist_curl,
            "Curl antebrazo supinado a una mano en banco"
        ),
        TranslationEntry(
            "Seated Overhead Stretch",
            R.string.exercise_seated_overhead_stretch,
            "Estiramiento sobre la cabeza sentado"
        ),
        TranslationEntry(
            "Seated Palm-Up Barbell Wrist Curl",
            R.string.exercise_seated_palm_up_barbell_wrist_curl,
            "Curl de muñeca supinado sentado con barra"
        ),
        TranslationEntry(
            "Seated Palms-Down Barbell Wrist Curl",
            R.string.exercise_seated_palms_down_barbell_wrist_curl,
            "Curl de muñeca pronado sentado con barra"
        ),
        TranslationEntry(
            "Seated Pancake Good Morning",
            R.string.exercise_seated_pancake_good_morning,
            "Buenos días en pancake sentado"
        ),
        TranslationEntry(
            "Seated Piriformis Stretch",
            R.string.exercise_seated_piriformis_stretch,
            "Estiramiento del piriforme sentado"
        ),
        TranslationEntry(
            "Seated Row (Machine)",
            R.string.exercise_seated_row_machine,
            "Remo sentado en máquina"
        ),
        TranslationEntry(
            "Seated Shoulder Extension Stretch",
            R.string.exercise_seated_shoulder_extension_stretch,
            "Estiramiento extensión de hombros sentado"
        ),
        TranslationEntry(
            "Seated Side Lateral Raise",
            R.string.exercise_seated_side_lateral_raise,
            "Elevación lateral sentado con mancuernas"
        ),
        TranslationEntry(
            "Seated Single Leg Hamstring Stretch",
            R.string.exercise_seated_single_leg_hamstring_stretch,
            "Estiramiento isquiosural una pierna sentado"
        ),
        TranslationEntry(
            "Seated Triceps Press",
            R.string.exercise_seated_triceps_press,
            "Press de tríceps sentado con mancuerna"
        ),
        TranslationEntry(
            "Seated Two-Arm Palms-Up Low-Pulley Wrist Curl",
            R.string.exercise_seated_two_arm_palms_up_low_pulley_wrist_curl,
            "Curl de muñeca en polea baja a dos manos"
        ),
        TranslationEntry(
            "See-Saw Press (Alternating Side Press)",
            R.string.exercise_see_saw_press_alternating_side_press,
            "Press balancín alterno (see-saw press)"
        ),
        TranslationEntry(
            "Serratus Wall Slide Stretch",
            R.string.exercise_serratus_wall_slide_stretch,
            "Deslizamiento en pared para serrato"
        ),
        TranslationEntry(
            "Shinbox IR Stretch",
            R.string.exercise_shinbox_ir_stretch,
            "Estiramiento de rotación interna en shinbox"
        ),
        TranslationEntry(
            "Shotgun Row",
            R.string.exercise_shotgun_row,
            "Remo shotgun en polea"
        ),
        TranslationEntry(
            "Shoulder Circles",
            R.string.exercise_shoulder_circles,
            "Círculos de hombros"
        ),
        TranslationEntry(
            "Shoulder dislocates",
            R.string.exercise_shoulder_dislocates,
            "Dislocaciones de hombro"
        ),
        TranslationEntry(
            "Shoulder Grip Pull-Up",
            R.string.exercise_shoulder_grip_pull_up,
            "Dominadas con agarre al ancho de hombros"
        ),
        TranslationEntry(
            "Shoulder Press - With Bands",
            R.string.exercise_shoulder_press_with_bands,
            "Press de hombros con bandas elásticas"
        ),
        TranslationEntry(
            "Shoulder Press, on Multi Press",
            R.string.exercise_shoulder_press_on_multi_press,
            "Press de hombros en multipower"
        ),
        TranslationEntry(
            "Shoulder Raise",
            R.string.exercise_shoulder_raise,
            "Elevaciones de hombro"
        ),
        TranslationEntry(
            "Shoulder Shrug",
            R.string.exercise_shoulder_shrug,
            "Encogimiento de hombros"
        ),
        TranslationEntry(
            "Shoulder Stretch",
            R.string.exercise_shoulder_stretch,
            "Estiramiento de hombros"
        ),
        TranslationEntry(
            "Shoulder Tap Pushup",
            R.string.exercise_shoulder_tap_pushup,
            "Flexiones con toque al hombro"
        ),
        TranslationEntry(
            "Shoulder width three-point push-up",
            R.string.exercise_shoulder_width_three_point_push_up,
            "Flexión en tres puntos ancho de hombros"
        ),
        TranslationEntry(
            "Shrimp Squat",
            R.string.exercise_shrimp_squat,
            "Sentadilla camarón (shrimp squat)"
        ),
        TranslationEntry(
            "Shrugs on Multipress",
            R.string.exercise_shrugs_on_multipress,
            "Encogimientos en multipower"
        ),
        TranslationEntry(
            "Side Bend",
            R.string.exercise_side_bend,
            "Inclinación lateral de torso"
        ),
        TranslationEntry(
            "Side Bridge",
            R.string.exercise_side_bridge,
            "Puente lateral"
        ),
        TranslationEntry(
            "Side Crunch",
            R.string.exercise_side_crunch,
            "Crunch lateral"
        ),
        TranslationEntry(
            "Side Hop-Sprint",
            R.string.exercise_side_hop_sprint,
            "Saltos laterales y esprint"
        ),
        TranslationEntry(
            "Side Jackknife",
            R.string.exercise_side_jackknife,
            "Navaja lateral para oblicuos"
        ),
        TranslationEntry(
            "Side Lateral Raise",
            R.string.exercise_side_lateral_raise,
            "Elevación lateral con mancuernas"
        ),
        TranslationEntry(
            "Side Laterals to Front Raise",
            R.string.exercise_side_laterals_to_front_raise,
            "Elevaciones laterales combinadas a frontales"
        ),
        TranslationEntry(
            "Side Leg Raises",
            R.string.exercise_side_leg_raises,
            "Elevaciones laterales de pierna"
        ),
        TranslationEntry(
            "Side Lying Groin Stretch",
            R.string.exercise_side_lying_groin_stretch,
            "Estiramiento de ingle acostado de lado"
        ),
        TranslationEntry(
            "Side Lying Hip Abduction",
            R.string.exercise_side_lying_hip_abduction,
            "Abducción de cadera acostado de lado"
        ),
        TranslationEntry(
            "Side Neck Stretch",
            R.string.exercise_side_neck_stretch,
            "Estiramiento lateral de cuello"
        ),
        TranslationEntry(
            "Side Plank",
            R.string.exercise_side_plank,
            "Plancha lateral"
        ),
        TranslationEntry(
            "Side plank right",
            R.string.exercise_side_plank_right,
            "Plancha lateral derecha"
        ),
        TranslationEntry(
            "Side Slides + Squats",
            R.string.exercise_side_slides_squats,
            "Desplazamientos laterales con sentadillas"
        ),
        TranslationEntry(
            "Side split squats left",
            R.string.exercise_side_split_squats_left,
            "Sentadilla búlgara lateral izquierda"
        ),
        TranslationEntry(
            "Side split squats right",
            R.string.exercise_side_split_squats_right,
            "Sentadilla búlgara lateral derecha"
        ),
        TranslationEntry(
            "Side Standing Long Jump",
            R.string.exercise_side_standing_long_jump,
            "Salto de longitud lateral de pie"
        ),
        TranslationEntry(
            "Side Step Deep Squat",
            R.string.exercise_side_step_deep_squat,
            "Paso lateral a sentadilla profunda"
        ),
        TranslationEntry(
            "Side stretch",
            R.string.exercise_side_stretch,
            "Estiramiento de costado"
        ),
        TranslationEntry(
            "Side to Side Box Shuffle",
            R.string.exercise_side_to_side_box_shuffle,
            "Desplazamiento lateral sobre cajón"
        ),
        TranslationEntry(
            "Side To Side Chins",
            R.string.exercise_side_to_side_chins,
            "Dominadas supinas lado a lado"
        ),
        TranslationEntry(
            "Side to Side Push Ups",
            R.string.exercise_side_to_side_push_ups,
            "Flexiones lado a lado"
        ),
        TranslationEntry(
            "Side Wrist Pull",
            R.string.exercise_side_wrist_pull,
            "Tracción lateral de muñeca"
        ),
        TranslationEntry(
            "Side-Kick Breathing (Kickboard)",
            R.string.exercise_side_kick_breathing_kickboard,
            "Respiración con patada lateral con tabla"
        ),
        TranslationEntry(
            "Side-lying External Rotation",
            R.string.exercise_side_lying_external_rotation,
            "Rotación externa recostado de lado"
        ),
        TranslationEntry(
            "Side-Lying Floor Stretch",
            R.string.exercise_side_lying_floor_stretch,
            "Estiramiento en suelo recostado de lado"
        ),
        TranslationEntry(
            "Sideway Leg Lift with a Chair",
            R.string.exercise_sideway_leg_lift_with_a_chair,
            "Elevación lateral de pierna con silla"
        ),
        TranslationEntry(
            "Single Arm Push-Up",
            R.string.exercise_single_arm_push_up,
            "Flexión con un brazo"
        ),
        TranslationEntry(
            "Single Dumbbell Raise",
            R.string.exercise_single_dumbbell_raise,
            "Elevación frontal con una mancuerna"
        ),
        TranslationEntry(
            "Single Leg Bridge Without Stretched Leg Left",
            R.string.exercise_single_leg_bridge_without_stretched_leg_left,
            "Puente de glúteos a una pierna izquierda"
        ),
        TranslationEntry(
            "Single Leg Butt Kick",
            R.string.exercise_single_leg_butt_kick,
            "Talón al glúteo a una pierna"
        ),
        TranslationEntry(
            "Single Leg Calf Raise off Step",
            R.string.exercise_single_leg_calf_raise_off_step,
            "Elevación de talones a una pierna en escalón"
        ),
        TranslationEntry(
            "Single Leg Extension",
            R.string.exercise_single_leg_extension,
            "Extensión a una pierna"
        ),
        TranslationEntry(
            "Single Leg Glute Bridge",
            R.string.exercise_single_leg_glute_bridge,
            "Puente de glúteos a una pierna"
        ),
        TranslationEntry(
            "Single Leg Hamstring Stretch",
            R.string.exercise_single_leg_hamstring_stretch,
            "Estiramiento femoral a una pierna"
        ),
        TranslationEntry(
            "Single leg press",
            R.string.exercise_single_leg_press,
            "Prensa a una pierna"
        ),
        TranslationEntry(
            "Single Leg Push-off",
            R.string.exercise_single_leg_push_off,
            "Empuje a una pierna sobre cajón"
        ),
        TranslationEntry(
            "Single Leg Squat Pistol",
            R.string.exercise_single_leg_squat_pistol,
            "Sentadilla a una pierna pistol"
        ),
        TranslationEntry(
            "Single Leg Squat with Support",
            R.string.exercise_single_leg_squat_with_support,
            "Sentadilla a una pierna con soporte"
        ),
        TranslationEntry(
            "Single Leg Squat with Support Pistol",
            R.string.exercise_single_leg_squat_with_support_pistol,
            "Sentadilla pistol con soporte"
        ),
        TranslationEntry(
            "Single Leg Thrusts",
            R.string.exercise_single_leg_thrusts,
            "Hip thrust a una pierna"
        ),
        TranslationEntry(
            "Single Straight Leg Glute Bridge Hold Left",
            R.string.exercise_single_straight_leg_glute_bridge_hold_left,
            "Puente isométrico pierna recta izquierda"
        ),
        TranslationEntry(
            "Single-Arm Cable Crossover",
            R.string.exercise_single_arm_cable_crossover,
            "Cruce de polea a un brazo"
        ),
        TranslationEntry(
            "Single-Arm Linear Jammer",
            R.string.exercise_single_arm_linear_jammer,
            "Empuje angular con barra a un brazo (jammer)"
        ),
        TranslationEntry(
            "Single-Cone Sprint Drill",
            R.string.exercise_single_cone_sprint_drill,
            "Ejercicio de esprint alrededor de cono"
        ),
        TranslationEntry(
            "Single-Leg Balance with Eyes Closed",
            R.string.exercise_single_leg_balance_with_eyes_closed,
            "Equilibrio a una pierna con ojos cerrados"
        ),
        TranslationEntry(
            "Single-leg BOSU balance",
            R.string.exercise_single_leg_bosu_balance,
            "Equilibrio a una pierna en BOSU"
        ),
        TranslationEntry(
            "Single-leg hamstring curl",
            R.string.exercise_single_leg_hamstring_curl,
            "Curl femoral a una pierna"
        ),
        TranslationEntry(
            "Single-Leg High Box Squat",
            R.string.exercise_single_leg_high_box_squat,
            "Sentadilla a una pierna sobre cajón alto"
        ),
        TranslationEntry(
            "Single-Leg Hop Progression",
            R.string.exercise_single_leg_hop_progression,
            "Progresión de saltos a una pierna"
        ),
        TranslationEntry(
            "Single-Leg Lateral Hop",
            R.string.exercise_single_leg_lateral_hop,
            "Salto lateral a una pierna"
        ),
        TranslationEntry(
            "Single-leg side glute press",
            R.string.exercise_single_leg_side_glute_press,
            "Prensa lateral de glúteo a una pierna"
        ),
        TranslationEntry(
            "Single-Leg Soft-Landing Holds",
            R.string.exercise_single_leg_soft_landing_holds,
            "Caídas suaves a una pierna isométricas"
        ),
        TranslationEntry(
            "Single-Leg Stride Jump",
            R.string.exercise_single_leg_stride_jump,
            "Salto en zancada a una pierna"
        ),
        TranslationEntry(
            "Sit & Reach",
            R.string.exercise_sit_reach,
            "Flexión de tronco sentado (sit and reach)"
        ),
        TranslationEntry(
            "Sit (wall)",
            R.string.exercise_sit_wall,
            "Sentadilla isométrica en pared"
        ),
        TranslationEntry(
            "Sit Squats",
            R.string.exercise_sit_squats,
            "Sentadillas al asiento"
        ),
        TranslationEntry(
            "Sit Up Elbow Thrust",
            R.string.exercise_sit_up_elbow_thrust,
            "Sit-up con empuje de codo"
        ),
        TranslationEntry(
            "Sit-Up",
            R.string.exercise_sit_up,
            "Sit-up abdominal"
        ),
        TranslationEntry(
            "Sit-ups",
            R.string.exercise_sit_ups,
            "Abdominales sit-ups"
        ),
        TranslationEntry(
            "Sitting Calf Raises",
            R.string.exercise_sitting_calf_raises,
            "Elevación de gemelos sentado"
        ),
        TranslationEntry(
            "Sitting Calf Stretch (Dorsiflexion)",
            R.string.exercise_sitting_calf_stretch_dorsiflexion,
            "Estiramiento de gemelos sentado dorsiflexión"
        ),
        TranslationEntry(
            "Skating",
            R.string.exercise_skating,
            "Patinaje simulado"
        ),
        TranslationEntry(
            "Ski Runners",
            R.string.exercise_ski_runners,
            "Zancadas de esquiador"
        ),
        TranslationEntry(
            "Skipping - Standard",
            R.string.exercise_skipping_standard,
            "Salto de cuerda estándar"
        ),
        TranslationEntry(
            "Skydiver with arms in T-position",
            R.string.exercise_skydiver_with_arms_in_t_position,
            "Paracaidista con brazos en T"
        ),
        TranslationEntry(
            "Sled 45 Calf Press",
            R.string.exercise_sled_45_calf_press,
            "Prensa de gemelos en trineo a 45 grados"
        ),
        TranslationEntry(
            "Sled 45 Degrees Leg Press",
            R.string.exercise_sled_45_degrees_leg_press,
            "Prensa de piernas en trineo a 45 grados"
        ),
        TranslationEntry(
            "Sled 45 Leg Press",
            R.string.exercise_sled_45_leg_press,
            "Prensa de piernas inclinada 45°"
        ),
        TranslationEntry(
            "Sled Calf Press on Leg Press",
            R.string.exercise_sled_calf_press_on_leg_press,
            "Elevación de gemelos en prensa de trineo"
        ),
        TranslationEntry(
            "Sled Drag - Harness",
            R.string.exercise_sled_drag_harness,
            "Arrastre de trineo con arnés"
        ),
        TranslationEntry(
            "Sled Hack Squat",
            R.string.exercise_sled_hack_squat,
            "Sentadilla hack en trineo"
        ),
        TranslationEntry(
            "Sled Overhead Backward Walk",
            R.string.exercise_sled_overhead_backward_walk,
            "Caminata hacia atrás con trineo sobre cabeza"
        ),
        TranslationEntry(
            "Sled Overhead Triceps Extension",
            R.string.exercise_sled_overhead_triceps_extension,
            "Extensión de tríceps con trineo sobre cabeza"
        ),
        TranslationEntry(
            "Sled Push",
            R.string.exercise_sled_push,
            "Empuje de trineo"
        ),
        TranslationEntry(
            "Sled Reverse Flye",
            R.string.exercise_sled_reverse_flye,
            "Apertura inversa con trineo"
        ),
        TranslationEntry(
            "Sled Row",
            R.string.exercise_sled_row,
            "Remo con trineo"
        ),
        TranslationEntry(
            "Sledgehammer Swings",
            R.string.exercise_sledgehammer_swings,
            "Golpes con maza"
        ),
        TranslationEntry(
            "Sleeper Stretch",
            R.string.exercise_sleeper_stretch,
            "Estiramiento sleeper para hombro"
        ),
        TranslationEntry(
            "Sloper hanging",
            R.string.exercise_sloper_hanging,
            "Suspensión en agarres romos (slopers)"
        ),
        TranslationEntry(
            "Slow Squat",
            R.string.exercise_slow_squat,
            "Sentadilla lenta con tempo"
        ),
        TranslationEntry(
            "Smith Back Shrug",
            R.string.exercise_smith_back_shrug,
            "Encogimiento posterior en máquina Smith"
        ),
        TranslationEntry(
            "Smith Bench Press",
            R.string.exercise_smith_bench_press,
            "Press de banca en máquina Smith"
        ),
        TranslationEntry(
            "Smith Bent Over Pronated Grip Row",
            R.string.exercise_smith_bent_over_pronated_grip_row,
            "Remo inclinado en Smith agarre pronado"
        ),
        TranslationEntry(
            "Smith Calf Raise",
            R.string.exercise_smith_calf_raise,
            "Elevación de gemelos en máquina Smith"
        ),
        TranslationEntry(
            "Smith Chair Squat",
            R.string.exercise_smith_chair_squat,
            "Sentadilla a la silla en máquina Smith"
        ),
        TranslationEntry(
            "Smith Decline Bench Press",
            R.string.exercise_smith_decline_bench_press,
            "Press declinado en máquina Smith"
        ),
        TranslationEntry(
            "Smith Hip Thrust",
            R.string.exercise_smith_hip_thrust,
            "Hip thrust en máquina Smith"
        ),
        TranslationEntry(
            "Smith Incline Bench Press",
            R.string.exercise_smith_incline_bench_press,
            "Press inclinado en máquina Smith"
        ),
        TranslationEntry(
            "Smith Incline Shoulder Raise",
            R.string.exercise_smith_incline_shoulder_raise,
            "Elevación de hombros inclinada en Smith"
        ),
        TranslationEntry(
            "Smith Machine Behind the Back Shrug",
            R.string.exercise_smith_machine_behind_the_back_shrug,
            "Encogimientos traseros en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Bent Over Row",
            R.string.exercise_smith_machine_bent_over_row,
            "Remo inclinado en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Close-Grip Bench Press",
            R.string.exercise_smith_machine_close_grip_bench_press,
            "Press cerrado en máquina Smith para tríceps"
        ),
        TranslationEntry(
            "Smith Machine Decline Press",
            R.string.exercise_smith_machine_decline_press,
            "Press declinado en máquina Smith pecho"
        ),
        TranslationEntry(
            "Smith Machine Hang Power Clean",
            R.string.exercise_smith_machine_hang_power_clean,
            "Cargada colgante en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Hip Raise",
            R.string.exercise_smith_machine_hip_raise,
            "Elevación de cadera en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Leg Press",
            R.string.exercise_smith_machine_leg_press,
            "Prensa de piernas vertical en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine One-Arm Upright Row",
            R.string.exercise_smith_machine_one_arm_upright_row,
            "Remo al mentón a un brazo en Smith"
        ),
        TranslationEntry(
            "Smith Machine Overhead Shoulder Press",
            R.string.exercise_smith_machine_overhead_shoulder_press,
            "Press militar sobre la cabeza en Smith"
        ),
        TranslationEntry(
            "Smith Machine Pistol Squat",
            R.string.exercise_smith_machine_pistol_squat,
            "Sentadilla pistol en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Reverse Calf Raises",
            R.string.exercise_smith_machine_reverse_calf_raises,
            "Elevación inversa de gemelos en Smith"
        ),
        TranslationEntry(
            "Smith Machine Slight Incline Press",
            R.string.exercise_smith_machine_slight_incline_press,
            "Press ligeramente inclinado en Smith"
        ),
        TranslationEntry(
            "Smith Machine Squat",
            R.string.exercise_smith_machine_squat,
            "Sentadilla en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Stiff-Legged Deadlift",
            R.string.exercise_smith_machine_stiff_legged_deadlift,
            "Peso muerto piernas rígidas en Smith"
        ),
        TranslationEntry(
            "Smith Machine Upright Row",
            R.string.exercise_smith_machine_upright_row,
            "Remo al mentón en máquina Smith"
        ),
        TranslationEntry(
            "Smith Press",
            R.string.exercise_smith_press,
            "Press militar en máquina Smith"
        ),
        TranslationEntry(
            "Smith Single-Leg Split Squat",
            R.string.exercise_smith_single_leg_split_squat,
            "Sentadilla búlgara en máquina Smith"
        ),
        TranslationEntry(
            "SMR Glutes (lax ball)",
            R.string.exercise_smr_glutes_lax_ball,
            "Liberación miofascial de glúteos con pelota"
        ),
        TranslationEntry(
            "Snap Down",
            R.string.exercise_snap_down,
            "Snap down atlético"
        ),
        TranslationEntry(
            "Snatch",
            R.string.exercise_snatch,
            "Arrancada olímpica (snatch)"
        ),
        TranslationEntry(
            "Snatch Balance",
            R.string.exercise_snatch_balance,
            "Snatch balance con barra"
        ),
        TranslationEntry(
            "Snatch Deadlift",
            R.string.exercise_snatch_deadlift,
            "Peso muerto con agarre de arrancada"
        ),
        TranslationEntry(
            "Snatch from Blocks",
            R.string.exercise_snatch_from_blocks,
            "Arrancada desde bloques"
        ),
        TranslationEntry(
            "Snatch Pull",
            R.string.exercise_snatch_pull,
            "Tirón de arrancada (snatch pull)"
        ),
        TranslationEntry(
            "Snatch Shrug",
            R.string.exercise_snatch_shrug,
            "Encogimiento con agarre de arrancada"
        ),
        TranslationEntry(
            "Soleus / Achilles Stretch",
            R.string.exercise_soleus_achilles_stretch,
            "Estiramiento de sóleo y tendón de Aquiles"
        ),
        TranslationEntry(
            "Solo Hip Flexor Stretch",
            R.string.exercise_solo_hip_flexor_stretch,
            "Estiramiento individual flexor de cadera"
        ),
        TranslationEntry(
            "Speed Band Overhead Triceps",
            R.string.exercise_speed_band_overhead_triceps,
            "Tríceps sobre la cabeza con banda rápida"
        ),
        TranslationEntry(
            "Speed Box Squat",
            R.string.exercise_speed_box_squat,
            "Sentadilla a cajón de velocidad"
        ),
        TranslationEntry(
            "Speed Deadlift",
            R.string.exercise_speed_deadlift,
            "Peso muerto de velocidad"
        ),
        TranslationEntry(
            "Speed Squats",
            R.string.exercise_speed_squats,
            "Sentadillas de velocidad"
        ),
        TranslationEntry(
            "Spell Caster",
            R.string.exercise_spell_caster,
            "Lanzador de hechizos con mancuernas"
        ),
        TranslationEntry(
            "Sphinx",
            R.string.exercise_sphinx,
            "Postura de la esfinge (yoga)"
        ),
        TranslationEntry(
            "Spider Crawl",
            R.string.exercise_spider_crawl,
            "Paso del hombre araña"
        ),
        TranslationEntry(
            "Spider Curl",
            R.string.exercise_spider_curl,
            "Curl araña (spider curl)"
        ),
        TranslationEntry(
            "Spider Man Plank",
            R.string.exercise_spider_man_plank,
            "Plancha Spiderman"
        ),
        TranslationEntry(
            "Spider Plank",
            R.string.exercise_spider_plank,
            "Plancha de araña"
        ),
        TranslationEntry(
            "Spinal Stretch",
            R.string.exercise_spinal_stretch,
            "Estiramiento espinal"
        ),
        TranslationEntry(
            "Splinter Sit-ups",
            R.string.exercise_splinter_sit_ups,
            "Sit-ups astilla"
        ),
        TranslationEntry(
            "Split Clean",
            R.string.exercise_split_clean,
            "Cargada en tijera (split clean)"
        ),
        TranslationEntry(
            "Split Jerk",
            R.string.exercise_split_jerk,
            "Envión en tijera (split jerk)"
        ),
        TranslationEntry(
            "Split Snatch",
            R.string.exercise_split_snatch,
            "Arrancada en tijera (split snatch)"
        ),
        TranslationEntry(
            "Split Squats",
            R.string.exercise_split_squats,
            "Sentadillas búlgaras (split squat)"
        ),
        TranslationEntry(
            "Split squats left",
            R.string.exercise_split_squats_left,
            "Sentadilla búlgara pierna izquierda"
        ),
        TranslationEntry(
            "Split squats right",
            R.string.exercise_split_squats_right,
            "Sentadilla búlgara pierna derecha"
        ),
        TranslationEntry(
            "Sprint",
            R.string.exercise_sprint,
            "Esprint a máxima velocidad"
        ),
        TranslationEntry(
            "Squat",
            R.string.exercise_squat,
            "Sentadilla libre"
        ),
        TranslationEntry(
            "Squat -> Stand Coordination",
            R.string.exercise_squat_stand_coordination,
            "Coordinación de sentadilla a bipedestación"
        ),
        TranslationEntry(
            "Squat Jerk",
            R.string.exercise_squat_jerk,
            "Envión en sentadilla (squat jerk)"
        ),
        TranslationEntry(
            "Squat Sky Reach Stretch",
            R.string.exercise_squat_sky_reach_stretch,
            "Sentadilla profunda con alcance al cielo"
        ),
        TranslationEntry(
            "Squat Thrust",
            R.string.exercise_squat_thrust,
            "Sentadilla con flexión de cadera y empuje"
        ),
        TranslationEntry(
            "Squat with Chains",
            R.string.exercise_squat_with_chains,
            "Sentadilla con cadenas"
        ),
        TranslationEntry(
            "Squat with Plate Movers",
            R.string.exercise_squat_with_plate_movers,
            "Sentadilla con deslizamiento de discos"
        ),
        TranslationEntry(
            "Squats on Multipress",
            R.string.exercise_squats_on_multipress,
            "Sentadillas en multipower"
        ),
        TranslationEntry(
            "Stairmaster",
            R.string.exercise_stairmaster,
            "Máquina de escaleras (StairMaster)"
        ),
        TranslationEntry(
            "Standing Alternating Arm Lift",
            R.string.exercise_standing_alternating_arm_lift,
            "Elevación alterna de brazos de pie"
        ),
        TranslationEntry(
            "Standing Alternating Arms Swing",
            R.string.exercise_standing_alternating_arms_swing,
            "Balanceo alterno de brazos de pie"
        ),
        TranslationEntry(
            "Standing Alternating Dumbbell Press",
            R.string.exercise_standing_alternating_dumbbell_press,
            "Press de hombros alterno de pie con mancuernas"
        ),
        TranslationEntry(
            "Standing Barbell Press Behind Neck",
            R.string.exercise_standing_barbell_press_behind_neck,
            "Press militar tras nuca de pie con barra"
        ),
        TranslationEntry(
            "Standing Bent-Over One-Arm Dumbbell Triceps Extension",
            R.string.exercise_standing_bent_over_one_arm_dumbbell_triceps_extension,
            "Patada de tríceps inclinada a un brazo"
        ),
        TranslationEntry(
            "Standing Bent-Over Two-Arm Dumbbell Triceps Extension",
            R.string.exercise_standing_bent_over_two_arm_dumbbell_triceps_extension,
            "Patada de tríceps inclinada a dos brazos"
        ),
        TranslationEntry(
            "Standing Biceps Cable Curl",
            R.string.exercise_standing_biceps_cable_curl,
            "Curl de bíceps de pie en polea"
        ),
        TranslationEntry(
            "Standing Biceps Stretch",
            R.string.exercise_standing_biceps_stretch,
            "Estiramiento de bíceps de pie"
        ),
        TranslationEntry(
            "Standing biceps stretch left",
            R.string.exercise_standing_biceps_stretch_left,
            "Estiramiento de bíceps de pie izquierdo"
        ),
        TranslationEntry(
            "Standing biceps stretch right",
            R.string.exercise_standing_biceps_stretch_right,
            "Estiramiento de bíceps de pie derecho"
        ),
        TranslationEntry(
            "Standing Bradford Press",
            R.string.exercise_standing_bradford_press,
            "Press Bradford de pie con barra"
        ),
        TranslationEntry(
            "Standing Cable Chest Press",
            R.string.exercise_standing_cable_chest_press,
            "Press de pecho de pie en polea"
        ),
        TranslationEntry(
            "Standing Cable Lift",
            R.string.exercise_standing_cable_lift,
            "Elevación diagonal en polea de pie"
        ),
        TranslationEntry(
            "Standing Cable Wood Chop",
            R.string.exercise_standing_cable_wood_chop,
            "Leñador en polea de pie"
        ),
        TranslationEntry(
            "Standing Calf Stretch",
            R.string.exercise_standing_calf_stretch,
            "Estiramiento de gemelos de pie"
        ),
        TranslationEntry(
            "Standing Concentration Curl",
            R.string.exercise_standing_concentration_curl,
            "Curl concentrado de pie con mancuerna"
        ),
        TranslationEntry(
            "Standing Dumbbell Press",
            R.string.exercise_standing_dumbbell_press,
            "Press de hombros de pie con mancuernas"
        ),
        TranslationEntry(
            "Standing Dumbbell Reverse Curl",
            R.string.exercise_standing_dumbbell_reverse_curl,
            "Curl invertido de pie con mancuernas"
        ),
        TranslationEntry(
            "Standing Dumbbell Straight-Arm Front Delt Raise Above Head",
            R.string.exercise_standing_dumbbell_straight_arm_front_delt_raise_above_head,
            "Elevación frontal brazos rectos sobre cabeza"
        ),
        TranslationEntry(
            "Standing Dumbbell Triceps Extension",
            R.string.exercise_standing_dumbbell_triceps_extension,
            "Extensión de tríceps de pie con mancuerna"
        ),
        TranslationEntry(
            "Standing Dumbbell Upright Row",
            R.string.exercise_standing_dumbbell_upright_row,
            "Remo al mentón de pie con mancuernas"
        ),
        TranslationEntry(
            "Standing Elevated Quad Stretch",
            R.string.exercise_standing_elevated_quad_stretch,
            "Estiramiento de cuádriceps elevado de pie"
        ),
        TranslationEntry(
            "Standing Front Barbell Raise Over Head",
            R.string.exercise_standing_front_barbell_raise_over_head,
            "Elevación frontal de pie sobre cabeza barra"
        ),
        TranslationEntry(
            "Standing Gastrocnemius Calf Stretch",
            R.string.exercise_standing_gastrocnemius_calf_stretch,
            "Estiramiento de gastrocnemio de pie"
        ),
        TranslationEntry(
            "Standing Hamstring and Calf Stretch",
            R.string.exercise_standing_hamstring_and_calf_stretch,
            "Estiramiento de isquiosurales y gemelos"
        ),
        TranslationEntry(
            "Standing Hip Circles",
            R.string.exercise_standing_hip_circles,
            "Círculos de cadera de pie"
        ),
        TranslationEntry(
            "Standing Hip Flexors",
            R.string.exercise_standing_hip_flexors,
            "Estiramiento de flexores de cadera de pie"
        ),
        TranslationEntry(
            "Standing Inner-Biceps Curl",
            R.string.exercise_standing_inner_biceps_curl,
            "Curl de bíceps interno de pie con mancuernas"
        ),
        TranslationEntry(
            "Standing IT Band Stretch",
            R.string.exercise_standing_it_band_stretch,
            "Estiramiento de banda iliotibial de pie"
        ),
        TranslationEntry(
            "Standing Lateral Stretch",
            R.string.exercise_standing_lateral_stretch,
            "Estiramiento lateral de pie"
        ),
        TranslationEntry(
            "Standing Leg Curl",
            R.string.exercise_standing_leg_curl,
            "Curl femoral de pie en máquina"
        ),
        TranslationEntry(
            "Standing Long Jump",
            R.string.exercise_standing_long_jump,
            "Salto de longitud sin impulso"
        ),
        TranslationEntry(
            "Standing Low-Pulley Deltoid Raise",
            R.string.exercise_standing_low_pulley_deltoid_raise,
            "Elevación lateral en polea baja de pie"
        ),
        TranslationEntry(
            "Standing Low-Pulley One-Arm Triceps Extension",
            R.string.exercise_standing_low_pulley_one_arm_triceps_extension,
            "Extensión tríceps a una mano en polea baja"
        ),
        TranslationEntry(
            "Standing Military Press",
            R.string.exercise_standing_military_press,
            "Press militar de pie con barra"
        ),
        TranslationEntry(
            "Standing Olympic Plate Hand Squeeze",
            R.string.exercise_standing_olympic_plate_hand_squeeze,
            "Compresión de disco olímpico con manos"
        ),
        TranslationEntry(
            "Standing One-Arm Cable Curl",
            R.string.exercise_standing_one_arm_cable_curl,
            "Curl a un brazo de pie en polea"
        ),
        TranslationEntry(
            "Standing One-Arm Dumbbell Curl Over Incline Bench",
            R.string.exercise_standing_one_arm_dumbbell_curl_over_incline_bench,
            "Curl mancuerna a un brazo sobre banco inclinado"
        ),
        TranslationEntry(
            "Standing One-Arm Dumbbell Triceps Extension",
            R.string.exercise_standing_one_arm_dumbbell_triceps_extension,
            "Extensión tríceps a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Standing Overhead Barbell Triceps Extension",
            R.string.exercise_standing_overhead_barbell_triceps_extension,
            "Extensión tríceps sobre cabeza con barra"
        ),
        TranslationEntry(
            "Standing Palm-In One-Arm Dumbbell Press",
            R.string.exercise_standing_palm_in_one_arm_dumbbell_press,
            "Press mancuerna a una mano agarre neutro"
        ),
        TranslationEntry(
            "Standing Palms-In Dumbbell Press",
            R.string.exercise_standing_palms_in_dumbbell_press,
            "Press de hombros de pie agarre neutro"
        ),
        TranslationEntry(
            "Standing Palms-Up Barbell Behind The Back Wrist Curl",
            R.string.exercise_standing_palms_up_barbell_behind_the_back_wrist_curl,
            "Curl muñeca tras espalda con barra de pie"
        ),
        TranslationEntry(
            "Standing Pancake",
            R.string.exercise_standing_pancake,
            "Pancake de pie (estiramiento)"
        ),
        TranslationEntry(
            "Standing Pancake Good Morning",
            R.string.exercise_standing_pancake_good_morning,
            "Buenos días en pancake de pie"
        ),
        TranslationEntry(
            "Standing Pelvic Tilt",
            R.string.exercise_standing_pelvic_tilt,
            "Basculación pélvica de pie"
        ),
        TranslationEntry(
            "Standing Plate Press",
            R.string.exercise_standing_plate_press,
            "Press con disco de pie (Svend press)"
        ),
        TranslationEntry(
            "Standing Rope Crunch",
            R.string.exercise_standing_rope_crunch,
            "Crunch de pie en polea con cuerda"
        ),
        TranslationEntry(
            "Standing Rope Forearm",
            R.string.exercise_standing_rope_forearm,
            "Enrollamiento de cuerda para antebrazo"
        ),
        TranslationEntry(
            "Standing Side Crunches",
            R.string.exercise_standing_side_crunches,
            "Crunches laterales de pie"
        ),
        TranslationEntry(
            "Standing Single Side Leg Raise Chair Supported",
            R.string.exercise_standing_single_side_leg_raise_chair_supported,
            "Elevación lateral de pierna con apoyo en silla"
        ),
        TranslationEntry(
            "Standing Soleus And Achilles Stretch",
            R.string.exercise_standing_soleus_and_achilles_stretch,
            "Estiramiento de pie de sóleo y Aquiles"
        ),
        TranslationEntry(
            "Standing Soleus Stretch",
            R.string.exercise_standing_soleus_stretch,
            "Estiramiento de sóleo de pie"
        ),
        TranslationEntry(
            "Standing Toe Touches",
            R.string.exercise_standing_toe_touches,
            "Toque de puntas de los pies de pie"
        ),
        TranslationEntry(
            "Standing Towel Triceps Extension",
            R.string.exercise_standing_towel_triceps_extension,
            "Extensión de tríceps de pie con toalla"
        ),
        TranslationEntry(
            "Standing Two-Arm Overhead Throw",
            R.string.exercise_standing_two_arm_overhead_throw,
            "Lanzamiento sobre cabeza a dos manos de pie"
        ),
        TranslationEntry(
            "Star Jump",
            R.string.exercise_star_jump,
            "Salto en estrella"
        ),
        TranslationEntry(
            "Stationary Bike",
            R.string.exercise_stationary_bike,
            "Bicicleta estática"
        ),
        TranslationEntry(
            "Stationary Bike Run",
            R.string.exercise_stationary_bike_run,
            "Bicicleta estática alta intensidad"
        ),
        TranslationEntry(
            "Step Calf Stretch",
            R.string.exercise_step_calf_stretch,
            "Estiramiento de gemelos en escalón"
        ),
        TranslationEntry(
            "Step Jack",
            R.string.exercise_step_jack,
            "Jumping jack con paso bajo impacto"
        ),
        TranslationEntry(
            "Step Mill",
            R.string.exercise_step_mill,
            "Escaladora mecánica (stepmill)"
        ),
        TranslationEntry(
            "Step Side Jump Over Stepbox",
            R.string.exercise_step_side_jump_over_stepbox,
            "Salto lateral sobre step"
        ),
        TranslationEntry(
            "Step-Up on Box",
            R.string.exercise_step_up_on_box,
            "Subidas a cajón (step-up)"
        ),
        TranslationEntry(
            "Step-up with Knee Raise",
            R.string.exercise_step_up_with_knee_raise,
            "Step-up con elevación de rodilla"
        ),
        TranslationEntry(
            "Step-ups",
            R.string.exercise_step_ups,
            "Step-ups en banco o plataforma"
        ),
        TranslationEntry(
            "Sternum Pull-Up (gironda)",
            R.string.exercise_sternum_pull_up_gironda,
            "Dominadas al esternón (Gironda)"
        ),
        TranslationEntry(
            "Stiff Leg Barbell Good Morning",
            R.string.exercise_stiff_leg_barbell_good_morning,
            "Buenos días piernas rígidas con barra"
        ),
        TranslationEntry(
            "Stiff-Legged Dumbbell Deadlift",
            R.string.exercise_stiff_legged_dumbbell_deadlift,
            "Peso muerto piernas rígidas con mancuernas"
        ),
        TranslationEntry(
            "Stomach Vacuum",
            R.string.exercise_stomach_vacuum,
            "Vacío abdominal (stomach vacuum)"
        ),
        TranslationEntry(
            "Straddle L-Sit",
            R.string.exercise_straddle_l_sit,
            "L-sit en posición abierta (straddle)"
        ),
        TranslationEntry(
            "Straight Bar Bench Mid Rows",
            R.string.exercise_straight_bar_bench_mid_rows,
            "Remo medio en banco con barra recta"
        ),
        TranslationEntry(
            "Straight Raises on Incline Bench",
            R.string.exercise_straight_raises_on_incline_bench,
            "Elevaciones frontales en banco inclinado"
        ),
        TranslationEntry(
            "Straight-Arm Dumbbell Pullover",
            R.string.exercise_straight_arm_dumbbell_pullover,
            "Pullover con mancuerna brazos rectos"
        ),
        TranslationEntry(
            "Stride Jump Crossover",
            R.string.exercise_stride_jump_crossover,
            "Salto en zancada cruzado"
        ),
        TranslationEntry(
            "Stroke-and-Roll Drill",
            R.string.exercise_stroke_and_roll_drill,
            "Ejercicio de brazada y giro de natación"
        ),
        TranslationEntry(
            "Sumo Deadlift",
            R.string.exercise_sumo_deadlift,
            "Peso muerto sumo"
        ),
        TranslationEntry(
            "Sumo Deadlift High Pull",
            R.string.exercise_sumo_deadlift_high_pull,
            "Tirón alto de peso muerto sumo"
        ),
        TranslationEntry(
            "Sumo Deadlift with Bands",
            R.string.exercise_sumo_deadlift_with_bands,
            "Peso muerto sumo con bandas elásticas"
        ),
        TranslationEntry(
            "Sumo Deadlift with Chains",
            R.string.exercise_sumo_deadlift_with_chains,
            "Peso muerto sumo con cadenas"
        ),
        TranslationEntry(
            "Sumo Squats",
            R.string.exercise_sumo_squats,
            "Sentadillas sumo"
        ),
        TranslationEntry(
            "Superman",
            R.string.exercise_superman,
            "Supermán para espalda baja"
        ),
        TranslationEntry(
            "Superman Hold",
            R.string.exercise_superman_hold,
            "Supermán isométrico"
        ),
        TranslationEntry(
            "Supine Chest Throw",
            R.string.exercise_supine_chest_throw,
            "Lanzamiento de pecho acostado boca arriba"
        ),
        TranslationEntry(
            "Supine One-Arm Overhead Throw",
            R.string.exercise_supine_one_arm_overhead_throw,
            "Lanzamiento sobre cabeza a un brazo boca arriba"
        ),
        TranslationEntry(
            "Supine Two-Arm Overhead Throw",
            R.string.exercise_supine_two_arm_overhead_throw,
            "Lanzamiento sobre cabeza a dos manos boca arriba"
        ),
        TranslationEntry(
            "Incline Bench Press",
            R.string.exercise_incline_bench_press,
            "Press de banca inclinado"
        ),
        TranslationEntry(
            "Supported Calf Stretch",
            R.string.exercise_supported_calf_stretch,
            "Estiramiento de gemelos con apoyo"
        ),
        TranslationEntry(
            "Supported Deep Squat Breathing",
            R.string.exercise_supported_deep_squat_breathing,
            "Respiración en sentadilla profunda apoyada"
        ),
        TranslationEntry(
            "Supported Split Squat",
            R.string.exercise_supported_split_squat,
            "Sentadilla búlgara con apoyo"
        ),
        TranslationEntry(
            "Suspended crossess",
            R.string.exercise_suspended_crossess,
            "Cruces en suspensión TRX"
        ),
        TranslationEntry(
            "Suspended Fallout",
            R.string.exercise_suspended_fallout,
            "Fallout en suspensión TRX"
        ),
        TranslationEntry(
            "Suspended Push-Up",
            R.string.exercise_suspended_push_up,
            "Flexiones en suspensión"
        ),
        TranslationEntry(
            "Suspended Reverse Crunch",
            R.string.exercise_suspended_reverse_crunch,
            "Crunch invertido en suspensión"
        ),
        TranslationEntry(
            "Suspended Split Squat",
            R.string.exercise_suspended_split_squat,
            "Sentadilla búlgara en suspensión"
        ),
        TranslationEntry(
            "Suspender Arm Curl",
            R.string.exercise_suspender_arm_curl,
            "Curl de bíceps en suspensión"
        ),
        TranslationEntry(
            "Suspender Fly",
            R.string.exercise_suspender_fly,
            "Aperturas de pecho en suspensión"
        ),
        TranslationEntry(
            "Suspender Front Raise",
            R.string.exercise_suspender_front_raise,
            "Elevación frontal en suspensión"
        ),
        TranslationEntry(
            "Suspender Hip Lift Knee Tuck",
            R.string.exercise_suspender_hip_lift_knee_tuck,
            "Elevación de cadera y rodillas en suspensión"
        ),
        TranslationEntry(
            "Suspender Jack Knife",
            R.string.exercise_suspender_jack_knife,
            "Navaja para abdomen en suspensión"
        ),
        TranslationEntry(
            "Suspender Row",
            R.string.exercise_suspender_row,
            "Remo en suspensión"
        ),
        TranslationEntry(
            "Suspender Squat",
            R.string.exercise_suspender_squat,
            "Sentadilla en suspensión"
        ),
        TranslationEntry(
            "Suspender Triceps Extension",
            R.string.exercise_suspender_triceps_extension,
            "Extensión de tríceps en suspensión"
        ),
        TranslationEntry(
            "Svend Press",
            R.string.exercise_svend_press,
            "Press Svend con discos"
        ),
        TranslationEntry(
            "Swimming Crawl Style",
            R.string.exercise_swimming_crawl_style,
            "Natación estilo crol"
        ),
        TranslationEntry(
            "T-Bar Row with Handle",
            R.string.exercise_t_bar_row_with_handle,
            "Remo en barra T con agarre"
        ),
        TranslationEntry(
            "Tate Press",
            R.string.exercise_tate_press,
            "Press Tate para tríceps"
        ),
        TranslationEntry(
            "The Straddle",
            R.string.exercise_the_straddle,
            "Apertura de piernas en suelo (straddle)"
        ),
        TranslationEntry(
            "Thigh Abductor",
            R.string.exercise_thigh_abductor,
            "Abductor de muslos en máquina"
        ),
        TranslationEntry(
            "Tibialis raises",
            R.string.exercise_tibialis_raises,
            "Elevaciones de tibial anterior"
        ),
        TranslationEntry(
            "Tire Flip",
            R.string.exercise_tire_flip,
            "Volteo de neumático de camión"
        ),
        TranslationEntry(
            "Toe Taps",
            R.string.exercise_toe_taps,
            "Toques de puntera"
        ),
        TranslationEntry(
            "Toe Touch",
            R.string.exercise_toe_touch,
            "Toque de dedos de los pies"
        ),
        TranslationEntry(
            "Toe Touchers",
            R.string.exercise_toe_touchers,
            "Abdominales con toque a las puntas"
        ),
        TranslationEntry(
            "Torso Rotation",
            R.string.exercise_torso_rotation,
            "Rotación de torso"
        ),
        TranslationEntry(
            "Torso rotation stretch",
            R.string.exercise_torso_rotation_stretch,
            "Estiramiento de rotación de torso"
        ),
        TranslationEntry(
            "Torso Twist",
            R.string.exercise_torso_twist,
            "Giros de torso"
        ),
        TranslationEntry(
            "Towel Superman",
            R.string.exercise_towel_superman,
            "Supermán con toalla"
        ),
        TranslationEntry(
            "Trail Running/Walking",
            R.string.exercise_trail_running_walking,
            "Carrera o caminata de montaña (trail)"
        ),
        TranslationEntry(
            "Trap Bar Deadlift",
            R.string.exercise_trap_bar_deadlift,
            "Peso muerto con barra hexagonal (trap bar)"
        ),
        TranslationEntry(
            "Trap Bar Squat",
            R.string.exercise_trap_bar_squat,
            "Sentadilla con barra hexagonal"
        ),
        TranslationEntry(
            "Trap-3 Raise",
            R.string.exercise_trap_3_raise,
            "Elevación para trapecio inferior (trap 3)"
        ),
        TranslationEntry(
            "Tricep Side Stretch",
            R.string.exercise_tricep_side_stretch,
            "Estiramiento lateral de tríceps"
        ),
        TranslationEntry(
            "Triceps Dip",
            R.string.exercise_triceps_dip,
            "Fondos de tríceps en banco"
        ),
        TranslationEntry(
            "Triceps Dip Floor",
            R.string.exercise_triceps_dip_floor,
            "Fondos de tríceps en el suelo"
        ),
        TranslationEntry(
            "Triceps Dips (Assisted)",
            R.string.exercise_triceps_dips_assisted,
            "Fondos de tríceps asistidos en máquina"
        ),
        TranslationEntry(
            "Triceps on Machine",
            R.string.exercise_triceps_on_machine,
            "Extensión de tríceps en máquina"
        ),
        TranslationEntry(
            "Triceps Overhead Extension with Rope",
            R.string.exercise_triceps_overhead_extension_with_rope,
            "Extensión sobre la cabeza con cuerda en polea"
        ),
        TranslationEntry(
            "Triceps Pushdown",
            R.string.exercise_triceps_pushdown,
            "Empuje de tríceps hacia abajo en polea"
        ),
        TranslationEntry(
            "Triceps Pushdown - Rope Attachment",
            R.string.exercise_triceps_pushdown_rope_attachment,
            "Jalón de tríceps en polea con cuerda"
        ),
        TranslationEntry(
            "Triceps Pushdown - V-Bar Attachment",
            R.string.exercise_triceps_pushdown_v_bar_attachment,
            "Jalón de tríceps en polea con barra V"
        ),
        TranslationEntry(
            "Triceps Stretch",
            R.string.exercise_triceps_stretch,
            "Estiramiento de tríceps"
        ),
        TranslationEntry(
            "Triceps stretch left",
            R.string.exercise_triceps_stretch_left,
            "Estiramiento de tríceps izquierdo"
        ),
        TranslationEntry(
            "Triceps stretch right",
            R.string.exercise_triceps_stretch_right,
            "Estiramiento de tríceps derecho"
        ),
        TranslationEntry(
            "TRX dips",
            R.string.exercise_trx_dips,
            "Fondos en TRX"
        ),
        TranslationEntry(
            "TRX gorilla biceps curl",
            R.string.exercise_trx_gorilla_biceps_curl,
            "Curl de bíceps gorila en TRX"
        ),
        TranslationEntry(
            "TRX hammer curl",
            R.string.exercise_trx_hammer_curl,
            "Curl martillo en TRX"
        ),
        TranslationEntry(
            "TRX Obliques",
            R.string.exercise_trx_obliques,
            "Oblicuos en TRX"
        ),
        TranslationEntry(
            "TRX roll out",
            R.string.exercise_trx_roll_out,
            "Rollout abdominal en TRX"
        ),
        TranslationEntry(
            "TRX Rows",
            R.string.exercise_trx_rows,
            "Remo en TRX"
        ),
        TranslationEntry(
            "Trx Single Arm Bicep Curl",
            R.string.exercise_trx_single_arm_bicep_curl,
            "Curl de bíceps a un brazo en TRX"
        ),
        TranslationEntry(
            "TRX Tricep Extension",
            R.string.exercise_trx_tricep_extension,
            "Extensión de tríceps en TRX"
        ),
        TranslationEntry(
            "Tuck Crunch",
            R.string.exercise_tuck_crunch,
            "Crunch encogido"
        ),
        TranslationEntry(
            "Tuck L-sit",
            R.string.exercise_tuck_l_sit,
            "L-sit agrupado (tuck L-sit)"
        ),
        TranslationEntry(
            "Tuck planche",
            R.string.exercise_tuck_planche,
            "Plancha agrupada (tuck planche)"
        ),
        TranslationEntry(
            "Two-Arm Dumbbell Preacher Curl",
            R.string.exercise_two_arm_dumbbell_preacher_curl,
            "Curl predicador a dos manos con mancuernas"
        ),
        TranslationEntry(
            "Two-Arm Kettlebell Clean",
            R.string.exercise_two_arm_kettlebell_clean,
            "Cargada con dos kettlebells"
        ),
        TranslationEntry(
            "Two-Arm Kettlebell Jerk",
            R.string.exercise_two_arm_kettlebell_jerk,
            "Envión con dos kettlebells"
        ),
        TranslationEntry(
            "Two-Arm Kettlebell Military Press",
            R.string.exercise_two_arm_kettlebell_military_press,
            "Press militar con dos kettlebells"
        ),
        TranslationEntry(
            "Two-Arm Kettlebell Row",
            R.string.exercise_two_arm_kettlebell_row,
            "Remo con dos kettlebells"
        ),
        TranslationEntry(
            "Typewriter Pull-ups",
            R.string.exercise_typewriter_pull_ups,
            "Dominadas máquina de escribir"
        ),
        TranslationEntry(
            "Tyson push-ups",
            R.string.exercise_tyson_push_ups,
            "Flexiones estilo Tyson"
        ),
        TranslationEntry(
            "Underhand Cable Pulldowns",
            R.string.exercise_underhand_cable_pulldowns,
            "Jalón al pecho agarre supino en polea"
        ),
        TranslationEntry(
            "Unilateral Hip Thrust",
            R.string.exercise_unilateral_hip_thrust,
            "Hip thrust unilateral a una pierna"
        ),
        TranslationEntry(
            "Unilateral Lunges",
            R.string.exercise_unilateral_lunges,
            "Zancadas unilaterales"
        ),
        TranslationEntry(
            "Upper Back",
            R.string.exercise_upper_back,
            "Estiramiento de espalda superior"
        ),
        TranslationEntry(
            "Upper Back Stretch",
            R.string.exercise_upper_back_stretch,
            "Estiramiento de la parte superior de espalda"
        ),
        TranslationEntry(
            "Upper Back-Leg Grab",
            R.string.exercise_upper_back_leg_grab,
            "Agarre de pierna para espalda superior"
        ),
        TranslationEntry(
            "Upper External Oblique",
            R.string.exercise_upper_external_oblique,
            "Oblicuo externo superior"
        ),
        TranslationEntry(
            "Upright Barbell Row",
            R.string.exercise_upright_barbell_row,
            "Remo al mentón con barra"
        ),
        TranslationEntry(
            "Upright Row - With Bands",
            R.string.exercise_upright_row_with_bands,
            "Remo al mentón con bandas elásticas"
        ),
        TranslationEntry(
            "Upright Row, on Multi Press",
            R.string.exercise_upright_row_on_multi_press,
            "Remo al mentón en multipower"
        ),
        TranslationEntry(
            "Upward Stretch",
            R.string.exercise_upward_stretch,
            "Estiramiento hacia arriba"
        ),
        TranslationEntry(
            "V-Bar Pulldown",
            R.string.exercise_v_bar_pulldown,
            "Jalón al pecho con agarre en V"
        ),
        TranslationEntry(
            "V-Bar Pullup",
            R.string.exercise_v_bar_pullup,
            "Dominadas con agarre en V"
        ),
        TranslationEntry(
            "V-Up",
            R.string.exercise_v_up,
            "V-up abdominales"
        ),
        TranslationEntry(
            "Vertical Leg Raise on Parallel Bars",
            R.string.exercise_vertical_leg_raise_on_parallel_bars,
            "Elevación vertical de piernas en paralelas"
        ),
        TranslationEntry(
            "Vertical Swing",
            R.string.exercise_vertical_swing,
            "Balanceo vertical con mancuerna"
        ),
        TranslationEntry(
            "Vpushup",
            R.string.exercise_vpushup,
            "Flexiones en V"
        ),
        TranslationEntry(
            "W-Raise",
            R.string.exercise_w_raise,
            "Elevaciones en W"
        ),
        TranslationEntry(
            "Walking Bridge",
            R.string.exercise_walking_bridge,
            "Puente de glúteos caminando"
        ),
        TranslationEntry(
            "Walking Lunge",
            R.string.exercise_walking_lunge,
            "Zancadas caminando"
        ),
        TranslationEntry(
            "Walking on Stepmill",
            R.string.exercise_walking_on_stepmill,
            "Caminata en escaladora mecánica"
        ),
        TranslationEntry(
            "Walking, Treadmill",
            R.string.exercise_walking_treadmill,
            "Caminata en cinta de correr"
        ),
        TranslationEntry(
            "Wall Angels",
            R.string.exercise_wall_angels,
            "Ángeles en la pared"
        ),
        TranslationEntry(
            "Wall Baby Breathing",
            R.string.exercise_wall_baby_breathing,
            "Respiración postura de bebé en pared"
        ),
        TranslationEntry(
            "Wall balls",
            R.string.exercise_wall_balls,
            "Lanzamientos a la pared con balón (wall balls)"
        ),
        TranslationEntry(
            "Wall Calf Stretch",
            R.string.exercise_wall_calf_stretch,
            "Estiramiento de gemelos en pared"
        ),
        TranslationEntry(
            "Wall Drills",
            R.string.exercise_wall_drills,
            "Ejercicios de aceleración contra la pared"
        ),
        TranslationEntry(
            "Wall Handstand",
            R.string.exercise_wall_handstand,
            "Pino contra la pared"
        ),
        TranslationEntry(
            "Wall Pushup",
            R.string.exercise_wall_pushup,
            "Flexiones contra la pared"
        ),
        TranslationEntry(
            "Wall Slides",
            R.string.exercise_wall_slides,
            "Deslizamientos de brazos en pared"
        ),
        TranslationEntry(
            "Wall Squat",
            R.string.exercise_wall_squat,
            "Sentadilla isométrica contra la pared"
        ),
        TranslationEntry(
            "Wall-Hold Rotation",
            R.string.exercise_wall_hold_rotation,
            "Rotación de tronco apoyado en pared"
        ),
        TranslationEntry(
            "Weighted Ball Hyperextension",
            R.string.exercise_weighted_ball_hyperextension,
            "Hiperextensión con balón medicinal"
        ),
        TranslationEntry(
            "Weighted Ball Side Bend",
            R.string.exercise_weighted_ball_side_bend,
            "Flexión lateral con balón medicinal"
        ),
        TranslationEntry(
            "Weighted Bench Dip",
            R.string.exercise_weighted_bench_dip,
            "Fondos en banco con peso"
        ),
        TranslationEntry(
            "Weighted Chin-Up",
            R.string.exercise_weighted_chin_up,
            "Dominadas supinas con lastre"
        ),
        TranslationEntry(
            "Weighted Close Grip Chinup on Dip Cage",
            R.string.exercise_weighted_close_grip_chinup_on_dip_cage,
            "Dominada supina cerrada con lastre en jaula"
        ),
        TranslationEntry(
            "Weighted Crunches",
            R.string.exercise_weighted_crunches,
            "Crunches con peso"
        ),
        TranslationEntry(
            "Weighted Decline Crunch",
            R.string.exercise_weighted_decline_crunch,
            "Crunch declinado con peso"
        ),
        TranslationEntry(
            "Weighted Frog Pump",
            R.string.exercise_weighted_frog_pump,
            "Frog pump con peso para glúteos"
        ),
        TranslationEntry(
            "Weighted Front Raise",
            R.string.exercise_weighted_front_raise,
            "Elevación frontal con peso"
        ),
        TranslationEntry(
            "Weighted Hyperextension",
            R.string.exercise_weighted_hyperextension,
            "Hiperextensión con lastre"
        ),
        TranslationEntry(
            "Weighted Jump Squat",
            R.string.exercise_weighted_jump_squat,
            "Sentadilla con salto con peso"
        ),
        TranslationEntry(
            "Weighted Lying Neck Extension",
            R.string.exercise_weighted_lying_neck_extension,
            "Extensión de cuello acostado con peso"
        ),
        TranslationEntry(
            "Weighted Lying Neck Flexion",
            R.string.exercise_weighted_lying_neck_flexion,
            "Flexión de cuello acostado con peso"
        ),
        TranslationEntry(
            "Weighted neck flexion",
            R.string.exercise_weighted_neck_flexion,
            "Flexión de cuello con peso"
        ),
        TranslationEntry(
            "Weighted Plate LU Raise",
            R.string.exercise_weighted_plate_lu_raise,
            "Elevación Lu con disco"
        ),
        TranslationEntry(
            "Weighted Plate Lying Crunch",
            R.string.exercise_weighted_plate_lying_crunch,
            "Crunch acostado con disco"
        ),
        TranslationEntry(
            "Weighted Plate Standing Biceps Curl",
            R.string.exercise_weighted_plate_standing_biceps_curl,
            "Curl de bíceps de pie con disco"
        ),
        TranslationEntry(
            "Weighted Pull Ups",
            R.string.exercise_weighted_pull_ups,
            "Dominadas con lastre"
        ),
        TranslationEntry(
            "Weighted push-ups",
            R.string.exercise_weighted_push_ups,
            "Flexiones con lastre"
        ),
        TranslationEntry(
            "Weighted Russian Twist",
            R.string.exercise_weighted_russian_twist,
            "Giros rusos con peso"
        ),
        TranslationEntry(
            "Weighted Sandbag Walking Lunge",
            R.string.exercise_weighted_sandbag_walking_lunge,
            "Zancadas caminando con saco de arena"
        ),
        TranslationEntry(
            "Weighted Sissy Squat",
            R.string.exercise_weighted_sissy_squat,
            "Sentadilla sissy con peso"
        ),
        TranslationEntry(
            "Weighted Sit-Ups - With Bands",
            R.string.exercise_weighted_sit_ups_with_bands,
            "Sit-ups con bandas elásticas de resistencia"
        ),
        TranslationEntry(
            "Weighted Squat",
            R.string.exercise_weighted_squat,
            "Sentadilla con peso añadido"
        ),
        TranslationEntry(
            "Weighted Step-ups",
            R.string.exercise_weighted_step_ups,
            "Step-ups con peso"
        ),
        TranslationEntry(
            "Weighted Svend Press",
            R.string.exercise_weighted_svend_press,
            "Press Svend con peso añadido"
        ),
        TranslationEntry(
            "Weighted Tricep Dips",
            R.string.exercise_weighted_tricep_dips,
            "Fondos de tríceps con lastre"
        ),
        TranslationEntry(
            "Wheel Rollout",
            R.string.exercise_wheel_rollout,
            "Rueda abdominal (ab rollout)"
        ),
        TranslationEntry(
            "Wide Stance Barbell Squat",
            R.string.exercise_wide_stance_barbell_squat,
            "Sentadilla con barra posición abierta"
        ),
        TranslationEntry(
            "Wide Stance Stiff Legs",
            R.string.exercise_wide_stance_stiff_legs,
            "Peso muerto piernas rígidas posición abierta"
        ),
        TranslationEntry(
            "Wide-Grip Barbell Bench Press",
            R.string.exercise_wide_grip_barbell_bench_press,
            "Press de banca con barra agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Decline Barbell Bench Press",
            R.string.exercise_wide_grip_decline_barbell_bench_press,
            "Press declinado con barra agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Decline Barbell Pullover",
            R.string.exercise_wide_grip_decline_barbell_pullover,
            "Pullover declinado con barra agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Lat Pulldown",
            R.string.exercise_wide_grip_lat_pulldown,
            "Jalón al pecho con agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Pulldown Behind The Neck",
            R.string.exercise_wide_grip_pulldown_behind_the_neck,
            "Jalón tras nuca con agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Rear Pull-Up",
            R.string.exercise_wide_grip_rear_pull_up,
            "Dominadas tras nuca con agarre ancho"
        ),
        TranslationEntry(
            "Wide-Grip Standing Barbell Curl",
            R.string.exercise_wide_grip_standing_barbell_curl,
            "Curl de bíceps de pie barra agarre ancho"
        ),
        TranslationEntry(
            "Wind Sprints",
            R.string.exercise_wind_sprints,
            "Esprints intermitentes de velocidad"
        ),
        TranslationEntry(
            "Windmills",
            R.string.exercise_windmills,
            "Molinos de viento con kettlebell"
        ),
        TranslationEntry(
            "Windshield Wipers",
            R.string.exercise_windshield_wipers,
            "Limpiaparabrisas para abdomen"
        ),
        TranslationEntry(
            "World's Greatest Stretch",
            R.string.exercise_worlds_greatest_stretch,
            "El mejor estiramiento del mundo"
        ),
        TranslationEntry(
            "Wrist Circles",
            R.string.exercise_wrist_circles,
            "Círculos de muñecas"
        ),
        TranslationEntry(
            "Wrist Roller",
            R.string.exercise_wrist_roller,
            "Rodillo de muñeca para antebrazo"
        ),
        TranslationEntry(
            "Wrist Rotations with Straight Bar",
            R.string.exercise_wrist_rotations_with_straight_bar,
            "Rotaciones de muñeca con barra recta"
        ),
        TranslationEntry(
            "Yoga exercise: Cow-cat",
            R.string.exercise_yoga_exercise_cow_cat,
            "Ejercicio de yoga: gato-vaca"
        ),
        TranslationEntry(
            "Yoke Walk",
            R.string.exercise_yoke_walk,
            "Paseo de yugo (yoke walk)"
        ),
        TranslationEntry(
            "YTW Raises",
            R.string.exercise_ytw_raises,
            "Elevaciones en Y, T y W"
        ),
        TranslationEntry(
            "YWTs",
            R.string.exercise_ywts,
            "Elevaciones posturales Y-W-T"
        ),
        TranslationEntry(
            "Zercher Squats",
            R.string.exercise_zercher_squats,
            "Sentadilla Zercher"
        ),
        TranslationEntry(
            "Zone 2 Running",
            R.string.exercise_zone_2_running,
            "Carrera en zona 2 aeróbica"
        ),
        TranslationEntry(
            "Zottman Curl",
            R.string.exercise_zottman_curl,
            "Curl Zottman con mancuernas"
        ),
        TranslationEntry(
            "Zottman Preacher Curl",
            R.string.exercise_zottman_preacher_curl,
            "Curl predicador Zottman con mancuernas"
        ),
        TranslationEntry(
            "1/2 Kneeling Adductor Rock Back",
            R.string.exercise_1_2_kneeling_adductor_rock_back,
            "Balanceo de aductores en media rodilla"
        ),
        TranslationEntry(
            "45 Degree Hyperextension Arms in Front of Chest",
            R.string.exercise_45_degree_hyperextension_arms_in_front_of_chest,
            "Hiperextensión a 45° con brazos al pecho"
        ),
        TranslationEntry(
            "45 Degrees Reverse Hyperextension",
            R.string.exercise_45_degrees_reverse_hyperextension,
            "Hiperextensión invertida a 45°"
        ),
        TranslationEntry(
            "90/90 Hip Flow",
            R.string.exercise_90_90_hip_flow,
            "Flujo de cadera 90/90"
        ),
        TranslationEntry(
            "A Skip",
            R.string.exercise_a_skip,
            "Skipping tipo A"
        ),
        TranslationEntry(
            "Abs Crunch (Weighted)",
            R.string.exercise_abs_crunch_weighted,
            "Crunch abdominal con peso"
        ),
        TranslationEntry(
            "Alternating Dumbbell Curl",
            R.string.exercise_alternating_dumbbell_curl,
            "Curl alterno con mancuernas"
        ),
        TranslationEntry(
            "Alternating Lateral Ball Slams",
            R.string.exercise_alternating_lateral_ball_slams,
            "Lanzamientos laterales alternos con balón"
        ),
        TranslationEntry(
            "Alternating Lunge with Rotation",
            R.string.exercise_alternating_lunge_with_rotation,
            "Zancada alterna con rotación"
        ),
        TranslationEntry(
            "Arnold Press",
            R.string.exercise_arnold_press,
            "Press Arnold"
        ),
        TranslationEntry(
            "Back Extension",
            R.string.exercise_back_extension,
            "Extensión lumbar en máquina"
        ),
        TranslationEntry(
            "Back Extension (Weighted)",
            R.string.exercise_back_extension_weighted,
            "Extensión lumbar con peso"
        ),
        TranslationEntry(
            "Band Alternate Low Chest Fly",
            R.string.exercise_band_alternate_low_chest_fly,
            "Aperturas bajas alternas de pecho con banda"
        ),
        TranslationEntry(
            "Band Assisted Chin-Up From Knee",
            R.string.exercise_band_assisted_chin_up_from_knee,
            "Dominadas supinas asistidas con banda de rodillas"
        ),
        TranslationEntry(
            "Band Bent Over Lat Pulldown",
            R.string.exercise_band_bent_over_lat_pulldown,
            "Jalón dorsal inclinado con banda"
        ),
        TranslationEntry(
            "Band Bent Over Rear Lateral Raise",
            R.string.exercise_band_bent_over_rear_lateral_raise,
            "Elevaciones laterales posteriores inclinado banda"
        ),
        TranslationEntry(
            "Band Clamshells",
            R.string.exercise_band_clamshells,
            "Clamshells con banda elástica"
        ),
        TranslationEntry(
            "Band Cross Body One Arm Chest Press",
            R.string.exercise_band_cross_body_one_arm_chest_press,
            "Press de pecho cruzado a un brazo con banda"
        ),
        TranslationEntry(
            "Band Face Pull",
            R.string.exercise_band_face_pull,
            "Face pull con banda elástica"
        ),
        TranslationEntry(
            "Band Glute Bridge",
            R.string.exercise_band_glute_bridge,
            "Puente de glúteos con banda"
        ),
        TranslationEntry(
            "Band Hip Abduction",
            R.string.exercise_band_hip_abduction,
            "Abducción de cadera con banda"
        ),
        TranslationEntry(
            "Band Hip Thrust",
            R.string.exercise_band_hip_thrust,
            "Hip thrust con banda elástica"
        ),
        TranslationEntry(
            "Band Incline Hammer Press",
            R.string.exercise_band_incline_hammer_press,
            "Press martillo inclinado con banda"
        ),
        TranslationEntry(
            "Band Kneeling One Arm Pulldown",
            R.string.exercise_band_kneeling_one_arm_pulldown,
            "Jalón a un brazo de rodillas con banda"
        ),
        TranslationEntry(
            "Band Lat Pulldown",
            R.string.exercise_band_lat_pulldown,
            "Jalón al pecho con banda elástica"
        ),
        TranslationEntry(
            "Band Lateral Raise Version 2",
            R.string.exercise_band_lateral_raise_version_2,
            "Elevaciones laterales con banda (variación 2)"
        ),
        TranslationEntry(
            "Band Overhead Triceps Extension",
            R.string.exercise_band_overhead_triceps_extension,
            "Extensión de tríceps sobre la cabeza con banda"
        ),
        TranslationEntry(
            "Band Pull-Up",
            R.string.exercise_band_pull_up,
            "Dominadas con banda elástica"
        ),
        TranslationEntry(
            "Band Reverse Crunch",
            R.string.exercise_band_reverse_crunch,
            "Crunch inverso con banda elástica"
        ),
        TranslationEntry(
            "Band Seated Leg Extension",
            R.string.exercise_band_seated_leg_extension,
            "Extensión de piernas sentado con banda"
        ),
        TranslationEntry(
            "Band Shrug",
            R.string.exercise_band_shrug,
            "Encogimientos de hombros con banda"
        ),
        TranslationEntry(
            "Band Standard Biceps Curl",
            R.string.exercise_band_standard_biceps_curl,
            "Curl de bíceps estándar con banda"
        ),
        TranslationEntry(
            "Band Standing Leg Curl",
            R.string.exercise_band_standing_leg_curl,
            "Curl femoral de pie con banda"
        ),
        TranslationEntry(
            "Barbell Banded Bench Press",
            R.string.exercise_barbell_banded_bench_press,
            "Press de banca con barra y bandas elásticas"
        ),
        TranslationEntry(
            "Barbell Behind the Back Push Press",
            R.string.exercise_barbell_behind_the_back_push_press,
            "Push press con barra tras nuca"
        ),
        TranslationEntry(
            "Barbell Behind-the-Neck Press",
            R.string.exercise_barbell_behind_the_neck_press,
            "Press tras nuca con barra"
        ),
        TranslationEntry(
            "Barbell Bicep Curl",
            R.string.exercise_barbell_bicep_curl,
            "Curl de bíceps con barra"
        ),
        TranslationEntry(
            "Barbell Block Pull",
            R.string.exercise_barbell_block_pull,
            "Tirón desde bloques con barra"
        ),
        TranslationEntry(
            "Barbell Bulgarian Split Squat",
            R.string.exercise_barbell_bulgarian_split_squat,
            "Sentadilla búlgara con barra"
        ),
        TranslationEntry(
            "Barbell Chest Supported Row",
            R.string.exercise_barbell_chest_supported_row,
            "Remo con barra con apoyo en pecho"
        ),
        TranslationEntry(
            "Barbell Clean and Jerk",
            R.string.exercise_barbell_clean_and_jerk,
            "Dos tiempos (clean and jerk) con barra"
        ),
        TranslationEntry(
            "Barbell Clean Grip Front Squat",
            R.string.exercise_barbell_clean_grip_front_squat,
            "Sentadilla frontal con agarre de clean"
        ),
        TranslationEntry(
            "Barbell Cossack Squat",
            R.string.exercise_barbell_cossack_squat,
            "Sentadilla cosaca con barra"
        ),
        TranslationEntry(
            "Barbell Curtsey Lunge",
            R.string.exercise_barbell_curtsey_lunge,
            "Zancada cruzada tipo reverencia con barra"
        ),
        TranslationEntry(
            "Barbell Floor Press",
            R.string.exercise_barbell_floor_press,
            "Press de suelo con barra"
        ),
        TranslationEntry(
            "Barbell High Bar Squat",
            R.string.exercise_barbell_high_bar_squat,
            "Sentadilla con barra alta"
        ),
        TranslationEntry(
            "Barbell Incline Row",
            R.string.exercise_barbell_incline_row,
            "Remo inclinado con barra"
        ),
        TranslationEntry(
            "Barbell Incline Shoulders Press",
            R.string.exercise_barbell_incline_shoulders_press,
            "Press inclinado de hombros con barra"
        ),
        TranslationEntry(
            "Barbell Jefferson Squat",
            R.string.exercise_barbell_jefferson_squat,
            "Sentadilla Jefferson con barra"
        ),
        TranslationEntry(
            "Barbell Lateral Lunge",
            R.string.exercise_barbell_lateral_lunge,
            "Zancada lateral con barra"
        ),
        TranslationEntry(
            "Barbell Legs Up Bench Press",
            R.string.exercise_barbell_legs_up_bench_press,
            "Press de banca con piernas elevadas con barra"
        ),
        TranslationEntry(
            "Barbell Military Press",
            R.string.exercise_barbell_military_press,
            "Press militar con barra"
        ),
        TranslationEntry(
            "Barbell One Leg Hip Thrust",
            R.string.exercise_barbell_one_leg_hip_thrust,
            "Hip thrust a una pierna con barra"
        ),
        TranslationEntry(
            "Barbell Overhead Tricep Extension",
            R.string.exercise_barbell_overhead_tricep_extension,
            "Extensión de tríceps sobre la cabeza con barra"
        ),
        TranslationEntry(
            "Barbell Pause Bench Press",
            R.string.exercise_barbell_pause_bench_press,
            "Press de banca con pausa con barra"
        ),
        TranslationEntry(
            "Barbell Pause Deadlift",
            R.string.exercise_barbell_pause_deadlift,
            "Peso muerto con pausa con barra"
        ),
        TranslationEntry(
            "Barbell Pin Front Squat",
            R.string.exercise_barbell_pin_front_squat,
            "Sentadilla frontal sobre soportes (pin) con barra"
        ),
        TranslationEntry(
            "Barbell Pin Press Bench",
            R.string.exercise_barbell_pin_press_bench,
            "Press de banca sobre soportes con barra"
        ),
        TranslationEntry(
            "Barbell Push Press",
            R.string.exercise_barbell_push_press,
            "Push press con barra"
        ),
        TranslationEntry(
            "Barbell Reeves Deadlift",
            R.string.exercise_barbell_reeves_deadlift,
            "Peso muerto Reeves con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Grip Overhead Press",
            R.string.exercise_barbell_reverse_grip_overhead_press,
            "Press militar con agarre supino con barra"
        ),
        TranslationEntry(
            "Barbell Reverse Lunge",
            R.string.exercise_barbell_reverse_lunge,
            "Zancada hacia atrás con barra"
        ),
        TranslationEntry(
            "Barbell Seated Behind Head Military Press",
            R.string.exercise_barbell_seated_behind_head_military_press,
            "Press militar sentado tras nuca con barra"
        ),
        TranslationEntry(
            "Barbell Seated Military Press Inside Squat Cage",
            R.string.exercise_barbell_seated_military_press_inside_squat_cage,
            "Press militar sentado en jaula con barra"
        ),
        TranslationEntry(
            "Barbell Seated Overhead Press",
            R.string.exercise_barbell_seated_overhead_press,
            "Press sobre la cabeza sentado con barra"
        ),
        TranslationEntry(
            "Barbell Seated Pin Press",
            R.string.exercise_barbell_seated_pin_press,
            "Press sobre soportes sentado con barra"
        ),
        TranslationEntry(
            "Barbell Skull Crusher",
            R.string.exercise_barbell_skull_crusher,
            "Press francés con barra"
        ),
        TranslationEntry(
            "Barbell Sling Shot Bench Press",
            R.string.exercise_barbell_sling_shot_bench_press,
            "Press de banca con Slingshot y barra"
        ),
        TranslationEntry(
            "Barbell Split Squat",
            R.string.exercise_barbell_split_squat,
            "Sentadilla split con barra"
        ),
        TranslationEntry(
            "Barbell Standing Behind Neck Shoulder Press",
            R.string.exercise_barbell_standing_behind_neck_shoulder_press,
            "Press de hombros de pie tras nuca con barra"
        ),
        TranslationEntry(
            "Barbell Standing Shoulders Press",
            R.string.exercise_barbell_standing_shoulders_press,
            "Press de hombros de pie con barra"
        ),
        TranslationEntry(
            "Barbell Step-Up",
            R.string.exercise_barbell_step_up,
            "Step-up con barra"
        ),
        TranslationEntry(
            "Barbell Sumo Romanian Deadlift",
            R.string.exercise_barbell_sumo_romanian_deadlift,
            "Peso muerto rumano sumo con barra"
        ),
        TranslationEntry(
            "Barbell Tempo Squat",
            R.string.exercise_barbell_tempo_squat,
            "Sentadilla con tempo con barra"
        ),
        TranslationEntry(
            "Barbell Weighted Chains Bench Press",
            R.string.exercise_barbell_weighted_chains_bench_press,
            "Press de banca con barra y cadenas"
        ),
        TranslationEntry(
            "Barbell Wide Grip Upright Row",
            R.string.exercise_barbell_wide_grip_upright_row,
            "Remo al mentón con barra agarre ancho"
        ),
        TranslationEntry(
            "Barbell Wrist Reverse Curl Version 2",
            R.string.exercise_barbell_wrist_reverse_curl_version_2,
            "Curl invertido de muñeca con barra (variación 2)"
        ),
        TranslationEntry(
            "Barbell Z Press",
            R.string.exercise_barbell_z_press,
            "Press Z con barra"
        ),
        TranslationEntry(
            "Batwing Row",
            R.string.exercise_batwing_row,
            "Remo batwing con mancuernas"
        ),
        TranslationEntry(
            "Bayesian Curl",
            R.string.exercise_bayesian_curl,
            "Curl bayesiano en polea"
        ),
        TranslationEntry(
            "Bear Craw Isometric Hold",
            R.string.exercise_bear_craw_isometric_hold,
            "Paso de oso isométrico estático"
        ),
        TranslationEntry(
            "Behind-the-Neck Lat Pulldown",
            R.string.exercise_behind_the_neck_lat_pulldown,
            "Jalón tras nuca en máquina"
        ),
        TranslationEntry(
            "Bench Press (Close Grip)",
            R.string.exercise_bench_press_close_grip,
            "Press de banca con agarre cerrado"
        ),
        TranslationEntry(
            "Bench Press (Paused)",
            R.string.exercise_bench_press_paused,
            "Press de banca con pausa"
        ),
        TranslationEntry(
            "Bench Press (Wide Grip)",
            R.string.exercise_bench_press_wide_grip,
            "Press de banca con agarre ancho"
        ),
        TranslationEntry(
            "Bench T-Spine Stretch",
            R.string.exercise_bench_t_spine_stretch,
            "Estiramiento torácico en banco"
        ),
        TranslationEntry(
            "Bent Leg Raise",
            R.string.exercise_bent_leg_raise,
            "Elevación de piernas flexionadas"
        ),
        TranslationEntry(
            "Bent Over Row (Barbell, Paused)",
            R.string.exercise_bent_over_row_barbell_paused,
            "Remo inclinado con barra con pausa"
        ),
        TranslationEntry(
            "Bent-over Twist",
            R.string.exercise_bent_over_twist,
            "Giro de tronco inclinado"
        ),
        TranslationEntry(
            "Bilateral Snapdown",
            R.string.exercise_bilateral_snapdown,
            "Snapdown bilateral de aterrizaje"
        ),
        TranslationEntry(
            "Blank Bodyweight Row",
            R.string.exercise_blank_bodyweight_row,
            "Remo con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Abs Crunch",
            R.string.exercise_bodyweight_abs_crunch,
            "Crunch abdominal con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Bulgarian Split Squat",
            R.string.exercise_bodyweight_bulgarian_split_squat,
            "Sentadilla búlgara con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Calf Raise",
            R.string.exercise_bodyweight_calf_raise,
            "Elevación de talones con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Chin-Up",
            R.string.exercise_bodyweight_chin_up,
            "Dominadas supinas con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Cossack Squat",
            R.string.exercise_bodyweight_cossack_squat,
            "Sentadilla cosaca con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Decline Sit Up",
            R.string.exercise_bodyweight_decline_sit_up,
            "Sit-up declinado con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Dip",
            R.string.exercise_bodyweight_dip,
            "Fondos con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Eccentric Reverse Lunge",
            R.string.exercise_bodyweight_eccentric_reverse_lunge,
            "Zancada excéntrica hacia atrás con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Glute Bridge",
            R.string.exercise_bodyweight_glute_bridge,
            "Puente de glúteos con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Good Morning",
            R.string.exercise_bodyweight_good_morning,
            "Buenos días con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Hip Thrust",
            R.string.exercise_bodyweight_hip_thrust,
            "Hip thrust con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Lunge",
            R.string.exercise_bodyweight_lunge,
            "Zancada con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Pull-Up",
            R.string.exercise_bodyweight_pull_up,
            "Dominadas con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Reverse Abs Crunch",
            R.string.exercise_bodyweight_reverse_abs_crunch,
            "Crunch inverso con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Single Leg Calf Raise",
            R.string.exercise_bodyweight_single_leg_calf_raise,
            "Elevación de talón a una pierna peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Skull Crusher",
            R.string.exercise_bodyweight_skull_crusher,
            "Press francés con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Split Squat",
            R.string.exercise_bodyweight_split_squat,
            "Sentadilla split con peso corporal"
        ),
        TranslationEntry(
            "Bodyweight Tricep Dip",
            R.string.exercise_bodyweight_tricep_dip,
            "Fondos de tríceps con peso corporal"
        ),
        TranslationEntry(
            "Broad Jump",
            R.string.exercise_broad_jump,
            "Salto horizontal de longitud"
        ),
        TranslationEntry(
            "Bulgarian Split Squat Smith Machine",
            R.string.exercise_bulgarian_split_squat_smith_machine,
            "Sentadilla búlgara en máquina Smith"
        ),
        TranslationEntry(
            "Butt Kicks",
            R.string.exercise_butt_kicks,
            "Talones al glúteo"
        ),
        TranslationEntry(
            "Cable Behind The Back Lateral Raise",
            R.string.exercise_cable_behind_the_back_lateral_raise,
            "Elevaciones laterales en polea tras espalda"
        ),
        TranslationEntry(
            "Cable Bent Over Row",
            R.string.exercise_cable_bent_over_row,
            "Remo inclinado en polea"
        ),
        TranslationEntry(
            "Cable Bicep Curl",
            R.string.exercise_cable_bicep_curl,
            "Curl de bíceps en polea"
        ),
        TranslationEntry(
            "Cable Biceps Curl Sz Bar",
            R.string.exercise_cable_biceps_curl_sz_bar,
            "Curl de bíceps en polea con barra SZ"
        ),
        TranslationEntry(
            "Cable Chest Fly",
            R.string.exercise_cable_chest_fly,
            "Aperturas de pecho en polea"
        ),
        TranslationEntry(
            "Cable Close Grip Front Lat Pulldown",
            R.string.exercise_cable_close_grip_front_lat_pulldown,
            "Jalón al pecho agarre cerrado en polea"
        ),
        TranslationEntry(
            "Cable Cross Over Lateral Pulldown",
            R.string.exercise_cable_cross_over_lateral_pulldown,
            "Jalón lateral cruzado en polea"
        ),
        TranslationEntry(
            "Cable Cross Over Revers Fly",
            R.string.exercise_cable_cross_over_revers_fly,
            "Cruces posteriores en polea"
        ),
        TranslationEntry(
            "Cable Decline Seated Wide Grip Row",
            R.string.exercise_cable_decline_seated_wide_grip_row,
            "Remo sentado declinado agarre ancho en polea"
        ),
        TranslationEntry(
            "Cable Flexion Row",
            R.string.exercise_cable_flexion_row,
            "Remo con flexión en polea"
        ),
        TranslationEntry(
            "Cable Front Raise Rope Attachment",
            R.string.exercise_cable_front_raise_rope_attachment,
            "Elevaciones frontales en polea con cuerda"
        ),
        TranslationEntry(
            "Cable Front Seated Row",
            R.string.exercise_cable_front_seated_row,
            "Remo frontal sentado en polea"
        ),
        TranslationEntry(
            "Cable Front Squat with V Bar",
            R.string.exercise_cable_front_squat_with_v_bar,
            "Sentadilla frontal en polea con barra en V"
        ),
        TranslationEntry(
            "Cable Glute Kickback",
            R.string.exercise_cable_glute_kickback,
            "Patada de glúteo en polea"
        ),
        TranslationEntry(
            "Cable Goblet Squat",
            R.string.exercise_cable_goblet_squat,
            "Sentadilla goblet en polea"
        ),
        TranslationEntry(
            "Cable Half Kneeling Single Arm Row",
            R.string.exercise_cable_half_kneeling_single_arm_row,
            "Remo a un brazo en media rodilla en polea"
        ),
        TranslationEntry(
            "Cable High Row with Chest Support",
            R.string.exercise_cable_high_row_with_chest_support,
            "Remo alto con apoyo en pecho en polea"
        ),
        TranslationEntry(
            "Cable Incline Biceps Curl",
            R.string.exercise_cable_incline_biceps_curl,
            "Curl de bíceps inclinado en polea"
        ),
        TranslationEntry(
            "Cable Incline Inner Biceps Curl",
            R.string.exercise_cable_incline_inner_biceps_curl,
            "Curl de bíceps interior inclinado en polea"
        ),
        TranslationEntry(
            "Cable Incline Single Arm Lat Pulldown",
            R.string.exercise_cable_incline_single_arm_lat_pulldown,
            "Jalón dorsal a un brazo inclinado en polea"
        ),
        TranslationEntry(
            "Cable Kickback",
            R.string.exercise_cable_kickback,
            "Patada de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Kneeling One Arm Lat Pulldown",
            R.string.exercise_cable_kneeling_one_arm_lat_pulldown,
            "Jalón dorsal a un brazo de rodillas en polea"
        ),
        TranslationEntry(
            "Cable Kneeling Shoulder Press",
            R.string.exercise_cable_kneeling_shoulder_press,
            "Press de hombros de rodillas en polea"
        ),
        TranslationEntry(
            "Cable Lat Pulldown Full Range of Motion",
            R.string.exercise_cable_lat_pulldown_full_range_of_motion,
            "Jalón al pecho rango completo en polea"
        ),
        TranslationEntry(
            "Cable Lateral Pulldown with Rope Attachment",
            R.string.exercise_cable_lateral_pulldown_with_rope_attachment,
            "Jalón lateral en polea con cuerda"
        ),
        TranslationEntry(
            "Cable Lateral Pulldown with V Bar",
            R.string.exercise_cable_lateral_pulldown_with_v_bar,
            "Jalón lateral en polea con barra en V"
        ),
        TranslationEntry(
            "Cable Low Row",
            R.string.exercise_cable_low_row,
            "Remo bajo en polea"
        ),
        TranslationEntry(
            "Cable Lying Bicep Curl",
            R.string.exercise_cable_lying_bicep_curl,
            "Curl de bíceps acostado en polea"
        ),
        TranslationEntry(
            "Cable Lying Lateral Raise",
            R.string.exercise_cable_lying_lateral_raise,
            "Elevaciones laterales acostado en polea"
        ),
        TranslationEntry(
            "Cable Lying Pullover",
            R.string.exercise_cable_lying_pullover,
            "Pullover acostado en polea"
        ),
        TranslationEntry(
            "Cable One Arm Bent Over Row",
            R.string.exercise_cable_one_arm_bent_over_row,
            "Remo inclinado a un brazo en polea"
        ),
        TranslationEntry(
            "Cable One Arm Side Triceps Pushdown",
            R.string.exercise_cable_one_arm_side_triceps_pushdown,
            "Extensión lateral tríceps a un brazo polea"
        ),
        TranslationEntry(
            "Cable One Arm Tricep Pushdown",
            R.string.exercise_cable_one_arm_tricep_pushdown,
            "Extensión de tríceps a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Overhead Tricep Extension",
            R.string.exercise_cable_overhead_tricep_extension,
            "Extensión de tríceps sobre la cabeza en polea"
        ),
        TranslationEntry(
            "Cable Overhead Tricep Extension Straight Bar",
            R.string.exercise_cable_overhead_tricep_extension_straight_bar,
            "Extensión de tríceps sobre la cabeza en polea barra recta"
        ),
        TranslationEntry(
            "Cable Parallel Grip Lat Pulldown on Floor",
            R.string.exercise_cable_parallel_grip_lat_pulldown_on_floor,
            "Jalón dorsal en el suelo con agarre neutro en polea"
        ),
        TranslationEntry(
            "Cable Partial Lateral Raise",
            R.string.exercise_cable_partial_lateral_raise,
            "Elevaciones laterales parciales en polea"
        ),
        TranslationEntry(
            "Cable Pull Apart",
            R.string.exercise_cable_pull_apart,
            "Aperturas inversas en polea (pull apart)"
        ),
        TranslationEntry(
            "Cable Pulldown Pro Lat Bar",
            R.string.exercise_cable_pulldown_pro_lat_bar,
            "Jalón dorsal en polea con barra pro lat"
        ),
        TranslationEntry(
            "Cable Reverse Grip Pushdown",
            R.string.exercise_cable_reverse_grip_pushdown,
            "Extensión de tríceps agarre invertido en polea"
        ),
        TranslationEntry(
            "Cable Seated Overhead Curl",
            R.string.exercise_cable_seated_overhead_curl,
            "Curl sentado sobre la cabeza en polea"
        ),
        TranslationEntry(
            "Cable Seated Overhead Curl Sz Bar",
            R.string.exercise_cable_seated_overhead_curl_sz_bar,
            "Curl sentado sobre la cabeza en polea barra SZ"
        ),
        TranslationEntry(
            "Cable Shoulder Internal Rotation",
            R.string.exercise_cable_shoulder_internal_rotation,
            "Rotación interna de hombro en polea"
        ),
        TranslationEntry(
            "Cable Side Crunch",
            R.string.exercise_cable_side_crunch,
            "Crunch lateral en polea"
        ),
        TranslationEntry(
            "Cable Single Arm High Row",
            R.string.exercise_cable_single_arm_high_row,
            "Remo alto a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Single Arm Tricep Extension",
            R.string.exercise_cable_single_arm_tricep_extension,
            "Extensión de tríceps a un brazo en polea"
        ),
        TranslationEntry(
            "Cable Standing Crossover",
            R.string.exercise_cable_standing_crossover,
            "Cruces de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Crunch with Rope Attachment",
            R.string.exercise_cable_standing_crunch_with_rope_attachment,
            "Crunch de pie en polea con cuerda"
        ),
        TranslationEntry(
            "Cable Standing Lat Pulldown",
            R.string.exercise_cable_standing_lat_pulldown,
            "Jalón dorsal de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Pullover",
            R.string.exercise_cable_standing_pullover,
            "Pullover de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Row",
            R.string.exercise_cable_standing_row,
            "Remo de pie en polea"
        ),
        TranslationEntry(
            "Cable Standing Shoulder External Rotation",
            R.string.exercise_cable_standing_shoulder_external_rotation,
            "Rotación externa de hombro de pie en polea"
        ),
        TranslationEntry(
            "Cable Straight Back Seated Row",
            R.string.exercise_cable_straight_back_seated_row,
            "Remo sentado con espalda recta en polea"
        ),
        TranslationEntry(
            "Cable Tricep Extension",
            R.string.exercise_cable_tricep_extension,
            "Extensión de tríceps en polea"
        ),
        TranslationEntry(
            "Cable Twin Handle Parallel Grip Lat Pulldown",
            R.string.exercise_cable_twin_handle_parallel_grip_lat_pulldown,
            "Jalón dorsal en polea doble maneral agarre neutro"
        ),
        TranslationEntry(
            "Cable Twist",
            R.string.exercise_cable_twist,
            "Giro de tronco en polea"
        ),
        TranslationEntry(
            "Cable V-Handle Tricep Pushdown",
            R.string.exercise_cable_v_handle_tricep_pushdown,
            "Extensión de tríceps en polea con maneral en V"
        ),
        TranslationEntry(
            "Cable Wide Grip Lat Pulldown",
            R.string.exercise_cable_wide_grip_lat_pulldown,
            "Jalón al pecho agarre ancho en polea"
        ),
        TranslationEntry(
            "Calf Raise (Leg Press)",
            R.string.exercise_calf_raise_leg_press,
            "Elevación de talones en prensa de piernas"
        ),
        TranslationEntry(
            "Cambered Bar Lying Row",
            R.string.exercise_cambered_bar_lying_row,
            "Remo acostado con barra curvada (cambered)"
        ),
        TranslationEntry(
            "Cambered Bar Squat",
            R.string.exercise_cambered_bar_squat,
            "Sentadilla con barra curvada (cambered)"
        ),
        TranslationEntry(
            "Cannonball Crunch",
            R.string.exercise_cannonball_crunch,
            "Crunch en bola de cañón"
        ),
        TranslationEntry(
            "Cardio",
            R.string.exercise_cardio,
            "Cardio"
        ),
        TranslationEntry(
            "Cardio (LISS)",
            R.string.exercise_cardio_liss,
            "Cardio LISS de baja intensidad"
        ),
        TranslationEntry(
            "Cardio (Zone 2)",
            R.string.exercise_cardio_zone_2,
            "Cardio en zona 2"
        ),
        TranslationEntry(
            "Cat Cow and T-Spine Rotation",
            R.string.exercise_cat_cow_and_t_spine_rotation,
            "Gato-vaca con rotación torácica"
        ),
        TranslationEntry(
            "Chest Expander Pull-Apart (Horizontal)",
            R.string.exercise_chest_expander_pull_apart_horizontal,
            "Aperturas horizontales con tensor de pecho"
        ),
        TranslationEntry(
            "Chest Expander Pull-Apart (Vertical)",
            R.string.exercise_chest_expander_pull_apart_vertical,
            "Aperturas verticales con tensor de pecho"
        ),
        TranslationEntry(
            "Chest Supported Lateral Raise",
            R.string.exercise_chest_supported_lateral_raise,
            "Elevaciones laterales con apoyo en pecho"
        ),
        TranslationEntry(
            "Chin Ups Narrow Parallel Grip",
            R.string.exercise_chin_ups_narrow_parallel_grip,
            "Dominadas supinas agarre neutro estrecho"
        ),
        TranslationEntry(
            "Conditioning Circuit",
            R.string.exercise_conditioning_circuit,
            "Circuito de acondicionamiento físico"
        ),
        TranslationEntry(
            "Copenhagen Plank",
            R.string.exercise_copenhagen_plank,
            "Plancha Copenhague"
        ),
        TranslationEntry(
            "Couch Stretch with Reachovers",
            R.string.exercise_couch_stretch_with_reachovers,
            "Estiramiento couch con inclinaciones laterales"
        ),
        TranslationEntry(
            "Deadlift (Paused)",
            R.string.exercise_deadlift_paused,
            "Peso muerto con pausa"
        ),
        TranslationEntry(
            "Decline Sit Up (Weighted)",
            R.string.exercise_decline_sit_up_weighted,
            "Sit-up declinado con peso"
        ),
        TranslationEntry(
            "Deficit Handstand Push Up",
            R.string.exercise_deficit_handstand_push_up,
            "Flexiones en pino con déficit"
        ),
        TranslationEntry(
            "Dip (Assisted)",
            R.string.exercise_dip_assisted,
            "Fondos asistidos en máquina"
        ),
        TranslationEntry(
            "Dip (Weighted)",
            R.string.exercise_dip_weighted,
            "Fondos con lastre"
        ),
        TranslationEntry(
            "Dips Between Chairs",
            R.string.exercise_dips_between_chairs,
            "Fondos entre sillas"
        ),
        TranslationEntry(
            "Double Leg Multi-Directional Pogo",
            R.string.exercise_double_leg_multi_directional_pogo,
            "Saltos pogo multidireccionales a dos piernas"
        ),
        TranslationEntry(
            "Downward Dog",
            R.string.exercise_downward_dog,
            "Perro boca abajo (downward dog)"
        ),
        TranslationEntry(
            "Downward Dog to Lunge",
            R.string.exercise_downward_dog_to_lunge,
            "Perro boca abajo a zancada"
        ),
        TranslationEntry(
            "Dragon Fly",
            R.string.exercise_dragon_fly,
            "Dragon flag abdominal"
        ),
        TranslationEntry(
            "Dumbbell 21s",
            R.string.exercise_dumbbell_21s,
            "Curl 21 con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Alternate Shoulder Press",
            R.string.exercise_dumbbell_alternate_shoulder_press,
            "Press de hombros alterno con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell B-Stance Romanian Deadlift",
            R.string.exercise_dumbbell_b_stance_romanian_deadlift,
            "Peso muerto rumano en postura B con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Barrel Press",
            R.string.exercise_dumbbell_barrel_press,
            "Press de barril con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Bench Pullover",
            R.string.exercise_dumbbell_bench_pullover,
            "Pullover en banco con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Row Single Arm on Bench",
            R.string.exercise_dumbbell_bent_over_row_single_arm_on_bench,
            "Remo inclinado a una mano sobre banco mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Bent Over Y Raise",
            R.string.exercise_dumbbell_bent_over_y_raise,
            "Elevaciones en Y inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Chest Fly",
            R.string.exercise_dumbbell_chest_fly,
            "Aperturas de pecho con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Chest Supported Row",
            R.string.exercise_dumbbell_chest_supported_row,
            "Remo con apoyo en pecho con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Cossack Squats Version 2",
            R.string.exercise_dumbbell_cossack_squats_version_2,
            "Sentadilla cosaca con mancuerna (variación 2)"
        ),
        TranslationEntry(
            "Dumbbell Decline Hammer Press",
            R.string.exercise_dumbbell_decline_hammer_press,
            "Press martillo declinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Decline One Arm Hammer Press",
            R.string.exercise_dumbbell_decline_one_arm_hammer_press,
            "Press martillo declinado a un brazo mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Decline Twist Fly",
            R.string.exercise_dumbbell_decline_twist_fly,
            "Aperturas declinadas con giro con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Face Down Lying Shoulder Press",
            R.string.exercise_dumbbell_face_down_lying_shoulder_press,
            "Press hombros acostado boca abajo mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Floor Chest Fly",
            R.string.exercise_dumbbell_floor_chest_fly,
            "Aperturas de pecho en el suelo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Fly on Exercise Ball",
            R.string.exercise_dumbbell_fly_on_exercise_ball,
            "Aperturas de pecho sobre pelota suiza mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Fly Press",
            R.string.exercise_dumbbell_fly_press,
            "Press apertura (fly press) con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Glute Bridge",
            R.string.exercise_dumbbell_glute_bridge,
            "Puente de glúteos con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Gobelt Curtsey Lunge",
            R.string.exercise_dumbbell_gobelt_curtsey_lunge,
            "Zancada tipo reverencia en copa con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Goblet Walking Lunge",
            R.string.exercise_dumbbell_goblet_walking_lunge,
            "Zancadas caminando en copa con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Half Kneeling Arnold Press",
            R.string.exercise_dumbbell_half_kneeling_arnold_press,
            "Press Arnold en media rodilla con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Chest Fly",
            R.string.exercise_dumbbell_incline_chest_fly,
            "Aperturas inclinadas de pecho con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Fly Press",
            R.string.exercise_dumbbell_incline_fly_press,
            "Press apertura inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Hammer Press",
            R.string.exercise_dumbbell_incline_hammer_press,
            "Press martillo inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Inner Biceps Curl",
            R.string.exercise_dumbbell_incline_inner_biceps_curl,
            "Curl de bíceps interno inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline One Arm Hammer Press",
            R.string.exercise_dumbbell_incline_one_arm_hammer_press,
            "Press martillo inclinado a un brazo mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Incline Shoulders Press",
            R.string.exercise_dumbbell_incline_shoulders_press,
            "Press de hombros inclinado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Incline Tricep Extension",
            R.string.exercise_dumbbell_incline_tricep_extension,
            "Extensión de tríceps inclinada con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Isometric Row Hold",
            R.string.exercise_dumbbell_isometric_row_hold,
            "Remo isométrico estático con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell JM Press",
            R.string.exercise_dumbbell_jm_press,
            "Press JM con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lateral Step-Up",
            R.string.exercise_dumbbell_lateral_step_up,
            "Step-up lateral con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Leg Extension",
            R.string.exercise_dumbbell_leg_extension,
            "Extensión de piernas con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Lying Hammer Press",
            R.string.exercise_dumbbell_lying_hammer_press,
            "Press martillo acostado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Lying on Floor Hammer Press",
            R.string.exercise_dumbbell_lying_on_floor_hammer_press,
            "Press martillo en el suelo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell One Arm Lateral Raise",
            R.string.exercise_dumbbell_one_arm_lateral_raise,
            "Elevación lateral a una mano con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell One Arm Wrist Curl",
            R.string.exercise_dumbbell_one_arm_wrist_curl,
            "Curl de muñeca a una mano con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Overhead Extension",
            R.string.exercise_dumbbell_overhead_extension,
            "Extensión de tríceps sobre la cabeza mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Overhead Press",
            R.string.exercise_dumbbell_overhead_press,
            "Press militar sobre la cabeza con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Overhead Tricep Extension",
            R.string.exercise_dumbbell_overhead_tricep_extension,
            "Extensión de tríceps sobre la cabeza mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Partial Incline Press",
            R.string.exercise_dumbbell_partial_incline_press,
            "Press inclinado parcial con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Partial Row",
            R.string.exercise_dumbbell_partial_row,
            "Remo parcial con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Pec Fly",
            R.string.exercise_dumbbell_pec_fly,
            "Aperturas pectorales con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Plyo Squat",
            R.string.exercise_dumbbell_plyo_squat,
            "Sentadilla pliométrica con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rear Fly",
            R.string.exercise_dumbbell_rear_fly,
            "Aperturas posteriores (pájaro) con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Reverse Bicep Curl",
            R.string.exercise_dumbbell_reverse_bicep_curl,
            "Curl de bíceps invertido con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Reverse Lunge",
            R.string.exercise_dumbbell_reverse_lunge,
            "Zancada hacia atrás con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Rolling Tricep Extension",
            R.string.exercise_dumbbell_rolling_tricep_extension,
            "Extensión rodada de tríceps con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Romanian Deadlift to Row",
            R.string.exercise_dumbbell_romanian_deadlift_to_row,
            "Peso muerto rumano a remo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Row",
            R.string.exercise_dumbbell_row,
            "Remo con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Russian Twist with Legs Floor off",
            R.string.exercise_dumbbell_russian_twist_with_legs_floor_off,
            "Giro ruso con piernas elevadas con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Scott Press Version 2",
            R.string.exercise_dumbbell_scott_press_version_2,
            "Press Scott con mancuernas (variación 2)"
        ),
        TranslationEntry(
            "Dumbbell Seated Alternate Shoulder Press",
            R.string.exercise_dumbbell_seated_alternate_shoulder_press,
            "Press de hombros sentado alterno mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Calf Raise",
            R.string.exercise_dumbbell_seated_calf_raise,
            "Elevación de talones sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Overhead Press",
            R.string.exercise_dumbbell_seated_overhead_press,
            "Press sobre la cabeza sentado con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Seated Revers Grip Concentration Curl",
            R.string.exercise_dumbbell_seated_revers_grip_concentration_curl,
            "Curl concentrado sentado invertido mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Seated Shoulder Press Parallel Grip",
            R.string.exercise_dumbbell_seated_shoulder_press_parallel_grip,
            "Press hombros sentado agarre neutro mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Single Arm Push Press",
            R.string.exercise_dumbbell_single_arm_push_press,
            "Push press a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Single Arm Rear Delt Fly",
            R.string.exercise_dumbbell_single_arm_rear_delt_fly,
            "Pájaro deltoides posterior a un brazo mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Single Arm Row",
            R.string.exercise_dumbbell_single_arm_row,
            "Remo a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Deadlift",
            R.string.exercise_dumbbell_single_leg_deadlift,
            "Peso muerto a una pierna con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Single Leg Glute Bridge",
            R.string.exercise_dumbbell_single_leg_glute_bridge,
            "Puente de glúteos a una pierna con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Skull Crusher",
            R.string.exercise_dumbbell_skull_crusher,
            "Press francés con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Squat to Press",
            R.string.exercise_dumbbell_squat_to_press,
            "Sentadilla con press (thruster) con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Behind Back Shoulders Press",
            R.string.exercise_dumbbell_standing_behind_back_shoulders_press,
            "Press hombros de pie por detrás mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Bent Arm Lateral Raise",
            R.string.exercise_dumbbell_standing_bent_arm_lateral_raise,
            "Elevaciones laterales brazos flexionados mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Standing Close Grip Shoulder Press",
            R.string.exercise_dumbbell_standing_close_grip_shoulder_press,
            "Press hombros de pie agarre cerrado mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Hammer Curl to Press",
            R.string.exercise_dumbbell_standing_hammer_curl_to_press,
            "Curl martillo a press de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Kickback",
            R.string.exercise_dumbbell_standing_kickback,
            "Patada de tríceps de pie con mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Standing Shoulder Press",
            R.string.exercise_dumbbell_standing_shoulder_press,
            "Press de hombros de pie con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Shoulder Press to Pec Dec",
            R.string.exercise_dumbbell_standing_shoulder_press_to_pec_dec,
            "Press hombros de pie a peck deck mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Standing Single Leg Calf Raise",
            R.string.exercise_dumbbell_standing_single_leg_calf_raise,
            "Elevación talón a una pierna de pie mancuerna"
        ),
        TranslationEntry(
            "Dumbbell Stiff Leg Deadlift",
            R.string.exercise_dumbbell_stiff_leg_deadlift,
            "Peso muerto piernas rígidas con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Wrist Extension",
            R.string.exercise_dumbbell_wrist_extension,
            "Extensión de muñeca con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Y Raise",
            R.string.exercise_dumbbell_y_raise,
            "Elevaciones en Y con mancuernas"
        ),
        TranslationEntry(
            "Dumbbell Z Press",
            R.string.exercise_dumbbell_z_press,
            "Press Z con mancuernas"
        ),
        TranslationEntry(
            "Dynamic Hamstring Sweeps",
            R.string.exercise_dynamic_hamstring_sweeps,
            "Barrer de isquiotibiales dinámico"
        ),
        TranslationEntry(
            "Eccentric Push Up",
            R.string.exercise_eccentric_push_up,
            "Flexiones excéntricas"
        ),
        TranslationEntry(
            "Elevated Pigeon Stretch",
            R.string.exercise_elevated_pigeon_stretch,
            "Estiramiento de la paloma elevado"
        ),
        TranslationEntry(
            "EZ Bar 21s",
            R.string.exercise_ez_bar_21s,
            "Curl 21 con barra EZ"
        ),
        TranslationEntry(
            "EZ Bar Overhead Extension",
            R.string.exercise_ez_bar_overhead_extension,
            "Extensión sobre la cabeza con barra EZ"
        ),
        TranslationEntry(
            "EZ Bar Pullover",
            R.string.exercise_ez_bar_pullover,
            "Pullover con barra EZ"
        ),
        TranslationEntry(
            "EZ Bar Reverse Bicep Curl",
            R.string.exercise_ez_bar_reverse_bicep_curl,
            "Curl de bíceps invertido con barra EZ"
        ),
        TranslationEntry(
            "Ez Bar Seated Close Grip Concentration Curl",
            R.string.exercise_ez_bar_seated_close_grip_concentration_curl,
            "Curl concentrado sentado agarre cerrado barra EZ"
        ),
        TranslationEntry(
            "Ez Bar Seated Close Grip Shoulder Press",
            R.string.exercise_ez_bar_seated_close_grip_shoulder_press,
            "Press de hombros sentado agarre cerrado barra EZ"
        ),
        TranslationEntry(
            "EZ Bar Seated Overhead Extension",
            R.string.exercise_ez_bar_seated_overhead_extension,
            "Extensión sobre la cabeza sentado barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Close Grip Curl",
            R.string.exercise_ez_barbell_close_grip_curl,
            "Curl agarre cerrado con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Close Grip Preacher Curl",
            R.string.exercise_ez_barbell_close_grip_preacher_curl,
            "Curl predicador agarre cerrado con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Lying Triceps Extension",
            R.string.exercise_ez_barbell_lying_triceps_extension,
            "Extensión de tríceps acostado con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Reverse Grip Curl",
            R.string.exercise_ez_barbell_reverse_grip_curl,
            "Curl agarre invertido con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Reverse Grip Preacher Curl",
            R.string.exercise_ez_barbell_reverse_grip_preacher_curl,
            "Curl predicador agarre invertido con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Seated Curls",
            R.string.exercise_ez_barbell_seated_curls,
            "Curl de bíceps sentado con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Spider Curl",
            R.string.exercise_ez_barbell_spider_curl,
            "Curl araña con barra EZ"
        ),
        TranslationEntry(
            "Ez Barbell Standing Preacher Curl",
            R.string.exercise_ez_barbell_standing_preacher_curl,
            "Curl predicador de pie con barra EZ"
        ),
        TranslationEntry(
            "Face Away Cable Curl",
            R.string.exercise_face_away_cable_curl,
            "Curl en polea de espaldas a la torre"
        ),
        TranslationEntry(
            "Farmer's Walk (Weighted)",
            R.string.exercise_farmers_walk_weighted,
            "Paseo del granjero con peso"
        ),
        TranslationEntry(
            "Flexion Row",
            R.string.exercise_flexion_row,
            "Remo con flexión en polea"
        ),
        TranslationEntry(
            "Forward Jump",
            R.string.exercise_forward_jump,
            "Salto hacia adelante"
        ),
        TranslationEntry(
            "French Press",
            R.string.exercise_french_press,
            "Press francés en polea"
        ),
        TranslationEntry(
            "Front Raise",
            R.string.exercise_front_raise,
            "Elevaciones frontales con mancuernas"
        ),
        TranslationEntry(
            "Front Squat (Paused)",
            R.string.exercise_front_squat_paused,
            "Sentadilla frontal con pausa con barra"
        ),
        TranslationEntry(
            "Glute Bridge Hold",
            R.string.exercise_glute_bridge_hold,
            "Puente de glúteos estático isométrico"
        ),
        TranslationEntry(
            "Glute Bridge Walkout",
            R.string.exercise_glute_bridge_walkout,
            "Puente de glúteos con paso caminando"
        ),
        TranslationEntry(
            "Glute Med Kickback",
            R.string.exercise_glute_med_kickback,
            "Patada de glúteo medio en polea"
        ),
        TranslationEntry(
            "Goblet Duck Walk",
            R.string.exercise_goblet_duck_walk,
            "Caminata de pato en copa con kettlebell"
        ),
        TranslationEntry(
            "Goblet March",
            R.string.exercise_goblet_march,
            "Marcha en copa con kettlebell"
        ),
        TranslationEntry(
            "Halting Deadlift",
            R.string.exercise_halting_deadlift,
            "Peso muerto con detención intermedia"
        ),
        TranslationEntry(
            "Hammer Curl (Rope)",
            R.string.exercise_hammer_curl_rope,
            "Curl martillo en polea con cuerda"
        ),
        TranslationEntry(
            "Hammer Grip Pull-Up on Dip Cage",
            R.string.exercise_hammer_grip_pull_up_on_dip_cage,
            "Dominadas agarre neutro en jaula de fondos"
        ),
        TranslationEntry(
            "Hamstring Curl",
            R.string.exercise_hamstring_curl,
            "Curl femoral en máquina"
        ),
        TranslationEntry(
            "Hamstring Stretch with Rotation",
            R.string.exercise_hamstring_stretch_with_rotation,
            "Estiramiento de isquiotibiales con rotación"
        ),
        TranslationEntry(
            "Hands on Hips Countermovement Jump",
            R.string.exercise_hands_on_hips_countermovement_jump,
            "Salto con contramovimiento manos en cadera"
        ),
        TranslationEntry(
            "Hang Power Clean",
            R.string.exercise_hang_power_clean,
            "Power clean colgante con barra"
        ),
        TranslationEntry(
            "Hanging Oblique Knee Raise",
            R.string.exercise_hanging_oblique_knee_raise,
            "Elevación oblicua de rodillas colgado"
        ),
        TranslationEntry(
            "Hanging Toes To Bar",
            R.string.exercise_hanging_toes_to_bar,
            "Pies a la barra colgado (toes to bar)"
        ),
        TranslationEntry(
            "Hatfield Squat (Safety Squat Bar)",
            R.string.exercise_hatfield_squat_safety_squat_bar,
            "Sentadilla Hatfield con barra de seguridad"
        ),
        TranslationEntry(
            "High Row",
            R.string.exercise_high_row,
            "Remo alto en máquina"
        ),
        TranslationEntry(
            "Hip Bridge March",
            R.string.exercise_hip_bridge_march,
            "Marcha en puente de cadera"
        ),
        TranslationEntry(
            "Hip Extension",
            R.string.exercise_hip_extension,
            "Extensión de cadera en máquina"
        ),
        TranslationEntry(
            "Hip Openers",
            R.string.exercise_hip_openers,
            "Aperturas de cadera de movilidad"
        ),
        TranslationEntry(
            "I, Y, T (IYT) Raise",
            R.string.exercise_i_y_t_iyt_raise,
            "Elevaciones en I, Y y T con mancuernas"
        ),
        TranslationEntry(
            "Inch Worm + Push Up",
            R.string.exercise_inch_worm_push_up,
            "Gusano (inchworm) con flexión"
        ),
        TranslationEntry(
            "Inchworm Walkout",
            R.string.exercise_inchworm_walkout,
            "Gusano (inchworm) caminando"
        ),
        TranslationEntry(
            "Incline Twisting Sit-Up Version 2",
            R.string.exercise_incline_twisting_sit_up_version_2,
            "Sit-up inclinado con giro (variación 2)"
        ),
        TranslationEntry(
            "Inverted Face Pull",
            R.string.exercise_inverted_face_pull,
            "Face pull invertido con peso corporal"
        ),
        TranslationEntry(
            "Jammer Press",
            R.string.exercise_jammer_press,
            "Press en máquina jammer"
        ),
        TranslationEntry(
            "Jog",
            R.string.exercise_jog,
            "Trote suave"
        ),
        TranslationEntry(
            "Jump Switch Lunge",
            R.string.exercise_jump_switch_lunge,
            "Zancada con salto y cambio de pierna"
        ),
        TranslationEntry(
            "Katana Extension",
            R.string.exercise_katana_extension,
            "Extensión katana en polea"
        ),
        TranslationEntry(
            "Kettlebell Bent Over Row",
            R.string.exercise_kettlebell_bent_over_row,
            "Remo inclinado con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Clean",
            R.string.exercise_kettlebell_clean,
            "Clean (cargada) con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Clean and Press",
            R.string.exercise_kettlebell_clean_and_press,
            "Clean and press con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Front Squat",
            R.string.exercise_kettlebell_front_squat,
            "Sentadilla frontal con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Half Kneeling Shoulder Press",
            R.string.exercise_kettlebell_half_kneeling_shoulder_press,
            "Press de hombros en media rodilla kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Incline Hammer Press",
            R.string.exercise_kettlebell_incline_hammer_press,
            "Press martillo inclinado con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Lunge",
            R.string.exercise_kettlebell_lunge,
            "Zancada con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Press",
            R.string.exercise_kettlebell_press,
            "Press de hombros con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Russian Twist",
            R.string.exercise_kettlebell_russian_twist,
            "Giro ruso con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Seated Neutral Grip Shoulders Press",
            R.string.exercise_kettlebell_seated_neutral_grip_shoulders_press,
            "Press hombros sentado agarre neutro kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Seated Shoulder Press",
            R.string.exercise_kettlebell_seated_shoulder_press,
            "Press de hombros sentado con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Single Arm Row",
            R.string.exercise_kettlebell_single_arm_row,
            "Remo a un brazo con kettlebell"
        ),
        TranslationEntry(
            "Kettlebell Standing Bottoms up One Arm Shoulder Press",
            R.string.exercise_kettlebell_standing_bottoms_up_one_arm_shoulder_press,
            "Press hombros invertido a un brazo kettlebell"
        ),
        TranslationEntry(
            "Knee Raise (Captain's Chair)",
            R.string.exercise_knee_raise_captains_chair,
            "Elevación de rodillas en silla de capitán"
        ),
        TranslationEntry(
            "Landmine Clean And Press",
            R.string.exercise_landmine_clean_and_press,
            "Clean and press en landmine"
        ),
        TranslationEntry(
            "Landmine Kneeling One Arm Shoulder Press",
            R.string.exercise_landmine_kneeling_one_arm_shoulder_press,
            "Press hombros a un brazo rodillas landmine"
        ),
        TranslationEntry(
            "Landmine Oblique Twist",
            R.string.exercise_landmine_oblique_twist,
            "Giro oblicuo en landmine"
        ),
        TranslationEntry(
            "Landmine Press",
            R.string.exercise_landmine_press,
            "Press en landmine con barra"
        ),
        TranslationEntry(
            "Landmine Push Press",
            R.string.exercise_landmine_push_press,
            "Push press en landmine con barra"
        ),
        TranslationEntry(
            "Landmine Seated Shoulder Press",
            R.string.exercise_landmine_seated_shoulder_press,
            "Press de hombros sentado en landmine"
        ),
        TranslationEntry(
            "Landmine Shoulder to Shoulder Press",
            R.string.exercise_landmine_shoulder_to_shoulder_press,
            "Press de hombro a hombro en landmine"
        ),
        TranslationEntry(
            "Landmine Shoulders Press with Viking Press Attachment",
            R.string.exercise_landmine_shoulders_press_with_viking_press_attachment,
            "Press hombros en landmine con agarre vikingo"
        ),
        TranslationEntry(
            "Landmine Twist",
            R.string.exercise_landmine_twist,
            "Giro rotacional en landmine"
        ),
        TranslationEntry(
            "Lat Prayer",
            R.string.exercise_lat_prayer,
            "Jalón pullover en polea (lat prayer)"
        ),
        TranslationEntry(
            "Lat Pulldown (Close Grip)",
            R.string.exercise_lat_pulldown_close_grip,
            "Jalón al pecho agarre cerrado en máquina"
        ),
        TranslationEntry(
            "Lat Pulldown (Neutral Grip)",
            R.string.exercise_lat_pulldown_neutral_grip,
            "Jalón al pecho agarre neutro en máquina"
        ),
        TranslationEntry(
            "Lat Pulldown (Single Arm)",
            R.string.exercise_lat_pulldown_single_arm,
            "Jalón al pecho a un brazo en máquina"
        ),
        TranslationEntry(
            "Lateral Bear Crawl",
            R.string.exercise_lateral_bear_crawl,
            "Paso de oso lateral"
        ),
        TranslationEntry(
            "Lateral Jump",
            R.string.exercise_lateral_jump,
            "Salto lateral"
        ),
        TranslationEntry(
            "Lateral Lunge",
            R.string.exercise_lateral_lunge,
            "Zancada lateral con peso corporal"
        ),
        TranslationEntry(
            "Lateral Lunge (Weighted)",
            R.string.exercise_lateral_lunge_weighted,
            "Zancada lateral con peso añadido"
        ),
        TranslationEntry(
            "Lateral Med Ball Slam",
            R.string.exercise_lateral_med_ball_slam,
            "Lanzamiento lateral con balón medicinal suelo"
        ),
        TranslationEntry(
            "Lateral Med Ball Throw",
            R.string.exercise_lateral_med_ball_throw,
            "Lanzamiento lateral con balón medicinal"
        ),
        TranslationEntry(
            "Lateral Shuffle",
            R.string.exercise_lateral_shuffle,
            "Desplazamiento lateral rápido"
        ),
        TranslationEntry(
            "Leg Extension (Banded)",
            R.string.exercise_leg_extension_banded,
            "Extensión de piernas con banda elástica"
        ),
        TranslationEntry(
            "Leg Press (45 Degrees)",
            R.string.exercise_leg_press_45_degrees,
            "Prensa de piernas a 45 grados"
        ),
        TranslationEntry(
            "Lever Angled Leg Press",
            R.string.exercise_lever_angled_leg_press,
            "Prensa de piernas inclinada palanca"
        ),
        TranslationEntry(
            "Lever Assisted Chin-Up",
            R.string.exercise_lever_assisted_chin_up,
            "Dominadas supinas asistidas palanca"
        ),
        TranslationEntry(
            "Lever Assisted Standing Chin-Up",
            R.string.exercise_lever_assisted_standing_chin_up,
            "Dominadas supinas asistidas de pie máquina"
        ),
        TranslationEntry(
            "Lever Bent Over Low Row",
            R.string.exercise_lever_bent_over_low_row,
            "Remo bajo inclinado en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Bent Over Neutral Grip Row with Chest Support",
            R.string.exercise_lever_bent_over_neutral_grip_row_with_chest_support,
            "Remo neutro inclinado apoyo pecho palanca"
        ),
        TranslationEntry(
            "Lever Hip Thrust Plate Loaded",
            R.string.exercise_lever_hip_thrust_plate_loaded,
            "Hip thrust en máquina de palanca con discos"
        ),
        TranslationEntry(
            "Lever Incline Hammer Chest Press",
            R.string.exercise_lever_incline_hammer_chest_press,
            "Press martillo inclinado de pecho palanca"
        ),
        TranslationEntry(
            "Lever Lateral Pulldown Plate Loaded",
            R.string.exercise_lever_lateral_pulldown_plate_loaded,
            "Jalón lateral en máquina palanca con discos"
        ),
        TranslationEntry(
            "Lever Lateral Raise Plate Loaded",
            R.string.exercise_lever_lateral_raise_plate_loaded,
            "Elevaciones laterales palanca con discos"
        ),
        TranslationEntry(
            "Lever Lateral Raise with Chest Support",
            R.string.exercise_lever_lateral_raise_with_chest_support,
            "Elevaciones laterales apoyo pecho palanca"
        ),
        TranslationEntry(
            "Lever Lateral Wide Pulldown",
            R.string.exercise_lever_lateral_wide_pulldown,
            "Jalón lateral ancho en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Lying Chest Press Plate Loaded",
            R.string.exercise_lever_lying_chest_press_plate_loaded,
            "Press de pecho acostado palanca con discos"
        ),
        TranslationEntry(
            "Lever One Arm Lateral High Row",
            R.string.exercise_lever_one_arm_lateral_high_row,
            "Remo alto lateral a un brazo en palanca"
        ),
        TranslationEntry(
            "Lever One Arm Lateral Wide Pulldown Plate Loaded",
            R.string.exercise_lever_one_arm_lateral_wide_pulldown_plate_loaded,
            "Jalón lateral ancho a un brazo palanca discos"
        ),
        TranslationEntry(
            "Lever One Arm Shoulder Press Plate Loaded",
            R.string.exercise_lever_one_arm_shoulder_press_plate_loaded,
            "Press de hombros a un brazo palanca discos"
        ),
        TranslationEntry(
            "Lever Overhead Triceps Extension",
            R.string.exercise_lever_overhead_triceps_extension,
            "Extensión sobre la cabeza tríceps palanca"
        ),
        TranslationEntry(
            "Lever Parallel Chest Press",
            R.string.exercise_lever_parallel_chest_press,
            "Press de pecho paralelo en máquina palanca"
        ),
        TranslationEntry(
            "Lever Pendulum Squat Plate Loaded Female Thigh",
            R.string.exercise_lever_pendulum_squat_plate_loaded_female_thigh,
            "Sentadilla péndulo en palanca con discos"
        ),
        TranslationEntry(
            "Lever Preacher Curl Plate Loaded",
            R.string.exercise_lever_preacher_curl_plate_loaded,
            "Curl predicador en palanca con discos"
        ),
        TranslationEntry(
            "Lever Pronated Grip Seated Row Plate Loaded",
            R.string.exercise_lever_pronated_grip_seated_row_plate_loaded,
            "Remo sentado prono en palanca con discos"
        ),
        TranslationEntry(
            "Lever Reverse Grip High Row Plate Loaded",
            R.string.exercise_lever_reverse_grip_high_row_plate_loaded,
            "Remo alto agarre supino palanca con discos"
        ),
        TranslationEntry(
            "Lever Reverse Grip Lateral Pulldown Plate Loaded",
            R.string.exercise_lever_reverse_grip_lateral_pulldown_plate_loaded,
            "Jalón lateral supino en palanca con discos"
        ),
        TranslationEntry(
            "Lever Reverse Seated Shoulder Press",
            R.string.exercise_lever_reverse_seated_shoulder_press,
            "Press hombros sentado invertido palanca"
        ),
        TranslationEntry(
            "Lever Reverse T Bar Row",
            R.string.exercise_lever_reverse_t_bar_row,
            "Remo en T invertido en máquina de palanca"
        ),
        TranslationEntry(
            "Lever Reverse Vertical Hack Squat",
            R.string.exercise_lever_reverse_vertical_hack_squat,
            "Sentadilla hack vertical invertida palanca"
        ),
        TranslationEntry(
            "Lever Row Plate Loaded",
            R.string.exercise_lever_row_plate_loaded,
            "Remo en máquina de palanca con discos"
        ),
        TranslationEntry(
            "Lever Seated Hammer Grip Shoulder Press",
            R.string.exercise_lever_seated_hammer_grip_shoulder_press,
            "Press hombros sentado agarre martillo palanca"
        ),
        TranslationEntry(
            "Lever Seated Row Plate Loaded",
            R.string.exercise_lever_seated_row_plate_loaded,
            "Remo sentado en máquina de palanca discos"
        ),
        TranslationEntry(
            "Lever Seated Single Arm Shoulder Press",
            R.string.exercise_lever_seated_single_arm_shoulder_press,
            "Press hombros sentado a un brazo palanca"
        ),
        TranslationEntry(
            "Lever Seated Single Arm Wide Lat Pulldown",
            R.string.exercise_lever_seated_single_arm_wide_lat_pulldown,
            "Jalón dorsal ancho sentado a un brazo palanca"
        ),
        TranslationEntry(
            "Lever Seated Twist",
            R.string.exercise_lever_seated_twist,
            "Giro de tronco sentado en máquina palanca"
        ),
        TranslationEntry(
            "Lever Seated Wide Lat Pulldown",
            R.string.exercise_lever_seated_wide_lat_pulldown,
            "Jalón dorsal ancho sentado en palanca"
        ),
        TranslationEntry(
            "Lever Shoulder Press Plate Loaded Version 2",
            R.string.exercise_lever_shoulder_press_plate_loaded_version_2,
            "Press hombros palanca con discos (variación 2)"
        ),
        TranslationEntry(
            "Lever Shoulder Press Plate Loaded Version 3",
            R.string.exercise_lever_shoulder_press_plate_loaded_version_3,
            "Press hombros palanca con discos (variación 3)"
        ),
        TranslationEntry(
            "Lever Single Arm Shoulder Press Plate Loaded",
            R.string.exercise_lever_single_arm_shoulder_press_plate_loaded,
            "Press hombros a un brazo palanca con discos"
        ),
        TranslationEntry(
            "Lever Standing Calf Raise Calf",
            R.string.exercise_lever_standing_calf_raise_calf,
            "Elevación talones de pie en palanca"
        ),
        TranslationEntry(
            "Lever Standing Lateral Raise",
            R.string.exercise_lever_standing_lateral_raise,
            "Elevaciones laterales de pie en palanca"
        ),
        TranslationEntry(
            "Library Deadlift",
            R.string.exercise_library_deadlift,
            "Peso muerto en biblioteca (library deadlift)"
        ),
        TranslationEntry(
            "Log Press",
            R.string.exercise_log_press,
            "Press con tronco (log press)"
        ),
        TranslationEntry(
            "Long Pause Bench Press",
            R.string.exercise_long_pause_bench_press,
            "Press de banca con pausa larga"
        ),
        TranslationEntry(
            "Lu Raise",
            R.string.exercise_lu_raise,
            "Elevación Lu para hombros"
        ),
        TranslationEntry(
            "Lying Bicep Curl",
            R.string.exercise_lying_bicep_curl,
            "Curl de bíceps acostado con mancuernas"
        ),
        TranslationEntry(
            "Lying Chest Pass Med Ball Throw",
            R.string.exercise_lying_chest_pass_med_ball_throw,
            "Pase de pecho acostado con balón medicinal"
        ),
        TranslationEntry(
            "Lying Rear Lateral Raise",
            R.string.exercise_lying_rear_lateral_raise,
            "Elevaciones laterales posteriores acostado"
        ),
        TranslationEntry(
            "Lying Reverse Fly",
            R.string.exercise_lying_reverse_fly,
            "Aperturas inversas acostado con mancuernas"
        ),
        TranslationEntry(
            "Lying Side Lateral Raise",
            R.string.exercise_lying_side_lateral_raise,
            "Elevaciones laterales de lado acostado"
        ),
        TranslationEntry(
            "Machine Calf Raise",
            R.string.exercise_machine_calf_raise,
            "Elevación de talones en máquina"
        ),
        TranslationEntry(
            "Machine Chest Supported Row",
            R.string.exercise_machine_chest_supported_row,
            "Remo con apoyo en pecho en máquina"
        ),
        TranslationEntry(
            "Machine Hip Adductor",
            R.string.exercise_machine_hip_adductor,
            "Aductores de cadera en máquina"
        ),
        TranslationEntry(
            "Machine Hip Thrust",
            R.string.exercise_machine_hip_thrust,
            "Hip thrust en máquina"
        ),
        TranslationEntry(
            "Machine Overhead Press",
            R.string.exercise_machine_overhead_press,
            "Press militar sobre la cabeza en máquina"
        ),
        TranslationEntry(
            "Machine Rear Delt Fly",
            R.string.exercise_machine_rear_delt_fly,
            "Pájaros para deltoides posterior en máquina"
        ),
        TranslationEntry(
            "Machine Seated Dip",
            R.string.exercise_machine_seated_dip,
            "Fondos sentado en máquina"
        ),
        TranslationEntry(
            "Machine Shoulder Press",
            R.string.exercise_machine_shoulder_press,
            "Press de hombros en máquina"
        ),
        TranslationEntry(
            "Marching Glute Bridge",
            R.string.exercise_marching_glute_bridge,
            "Puente de glúteos con marcha alterna"
        ),
        TranslationEntry(
            "Meadows Curl",
            R.string.exercise_meadows_curl,
            "Curl Meadows con mancuerna"
        ),
        TranslationEntry(
            "Modified Dragon Fly",
            R.string.exercise_modified_dragon_fly,
            "Dragon flag modificado para abdomen"
        ),
        TranslationEntry(
            "Modified Jumping Jack",
            R.string.exercise_modified_jumping_jack,
            "Jumping jack modificado de bajo impacto"
        ),
        TranslationEntry(
            "Monastery Tricep Extension",
            R.string.exercise_monastery_tricep_extension,
            "Extensión de tríceps monasterio en polea"
        ),
        TranslationEntry(
            "Narrow Push Up",
            R.string.exercise_narrow_push_up,
            "Flexiones con agarre estrecho"
        ),
        TranslationEntry(
            "Neck Curl",
            R.string.exercise_neck_curl,
            "Curl de cuello con peso"
        ),
        TranslationEntry(
            "Neck Extension",
            R.string.exercise_neck_extension,
            "Extensión de cuello con peso"
        ),
        TranslationEntry(
            "Neck Flexion",
            R.string.exercise_neck_flexion,
            "Flexión de cuello con peso"
        ),
        TranslationEntry(
            "One Arm Bent Over Row",
            R.string.exercise_one_arm_bent_over_row,
            "Remo inclinado a un brazo con barra"
        ),
        TranslationEntry(
            "One Arm Overhead Carry with March",
            R.string.exercise_one_arm_overhead_carry_with_march,
            "Paseo sobre la cabeza a un brazo con marcha"
        ),
        TranslationEntry(
            "Partial Calf Raise",
            R.string.exercise_partial_calf_raise,
            "Elevación parcial de talones en máquina"
        ),
        TranslationEntry(
            "Partial Chin-Up",
            R.string.exercise_partial_chin_up,
            "Dominadas supinas parciales"
        ),
        TranslationEntry(
            "Pigeon Stretch with Thoracic Rotation",
            R.string.exercise_pigeon_stretch_with_thoracic_rotation,
            "Estiramiento de paloma con rotación torácica"
        ),
        TranslationEntry(
            "Pike Push-Up on Bench Version 2",
            R.string.exercise_pike_push_up_on_bench_version_2,
            "Flexiones en pica sobre banco (variación 2)"
        ),
        TranslationEntry(
            "Pinwheel Curl",
            R.string.exercise_pinwheel_curl,
            "Curl pinwheel cruzado con mancuernas"
        ),
        TranslationEntry(
            "Plank (Weighted)",
            R.string.exercise_plank_weighted,
            "Plancha abdominal con peso"
        ),
        TranslationEntry(
            "Plank on Swissball",
            R.string.exercise_plank_on_swissball,
            "Plancha abdominal sobre pelota suiza"
        ),
        TranslationEntry(
            "Plank to Push Up",
            R.string.exercise_plank_to_push_up,
            "Plancha dinámica a flexión"
        ),
        TranslationEntry(
            "Plank Walkout",
            R.string.exercise_plank_walkout,
            "Plancha caminando con las manos"
        ),
        TranslationEntry(
            "Plate Hop",
            R.string.exercise_plate_hop,
            "Saltos sobre disco de pesas"
        ),
        TranslationEntry(
            "Platz Squat",
            R.string.exercise_platz_squat,
            "Sentadilla estilo Tom Platz con barra"
        ),
        TranslationEntry(
            "Pogo Hop",
            R.string.exercise_pogo_hop,
            "Saltos pogo con rebote"
        ),
        TranslationEntry(
            "Powell Raise",
            R.string.exercise_powell_raise,
            "Elevaciones Powell con mancuerna"
        ),
        TranslationEntry(
            "Power Shrug",
            R.string.exercise_power_shrug,
            "Encogimientos de potencia con barra"
        ),
        TranslationEntry(
            "Prisoner Good Morning",
            R.string.exercise_prisoner_good_morning,
            "Buenos días prisionero manos en nuca"
        ),
        TranslationEntry(
            "Prowler Push",
            R.string.exercise_prowler_push,
            "Empuje de trineo prowler"
        ),
        TranslationEntry(
            "Pull-Up (Neutral Grip, Bodyweight)",
            R.string.exercise_pull_up_neutral_grip_bodyweight,
            "Dominadas agarre neutro con peso corporal"
        ),
        TranslationEntry(
            "Pull-Up (Neutral Grip, Weighted)",
            R.string.exercise_pull_up_neutral_grip_weighted,
            "Dominadas agarre neutro con lastre"
        ),
        TranslationEntry(
            "Punching Bag",
            R.string.exercise_punching_bag,
            "Entrenamiento con saco de boxeo"
        ),
        TranslationEntry(
            "Reaching Situp",
            R.string.exercise_reaching_situp,
            "Sit-up con alcance de brazos hacia arriba"
        ),
        TranslationEntry(
            "Rear Delt Row",
            R.string.exercise_rear_delt_row,
            "Remo para deltoides posterior con mancuernas"
        ),
        TranslationEntry(
            "Rear Foot Elevated Split Squat",
            R.string.exercise_rear_foot_elevated_split_squat,
            "Sentadilla split con pie trasero elevado"
        ),
        TranslationEntry(
            "Renegade Row",
            R.string.exercise_renegade_row,
            "Remo renegado en plancha con mancuernas"
        ),
        TranslationEntry(
            "Resistance Band Horizontal Pallof Press",
            R.string.exercise_resistance_band_horizontal_pallof_press,
            "Press Pallof horizontal con banda elástica"
        ),
        TranslationEntry(
            "Resistance Band Kneeling Cross Body Lat Pulldown",
            R.string.exercise_resistance_band_kneeling_cross_body_lat_pulldown,
            "Jalón cruzado dorsal de rodillas con banda"
        ),
        TranslationEntry(
            "Resistance Band Kneeling Shoulder Press",
            R.string.exercise_resistance_band_kneeling_shoulder_press,
            "Press de hombros de rodillas con banda elástica"
        ),
        TranslationEntry(
            "Resistance Band Standing Lat Pulldown",
            R.string.exercise_resistance_band_standing_lat_pulldown,
            "Jalón dorsal de pie con banda elástica"
        ),
        TranslationEntry(
            "Reverse Grip Machine Lat Pulldown",
            R.string.exercise_reverse_grip_machine_lat_pulldown,
            "Jalón dorsal agarre supino en máquina"
        ),
        TranslationEntry(
            "Reverse Pec Deck",
            R.string.exercise_reverse_pec_deck,
            "Pájaros posteriores en peck deck invertido"
        ),
        TranslationEntry(
            "Ring Archer Push-Up",
            R.string.exercise_ring_archer_push_up,
            "Flexiones de arquero en anillas"
        ),
        TranslationEntry(
            "Ring Arm Curl",
            R.string.exercise_ring_arm_curl,
            "Curl de bíceps en anillas"
        ),
        TranslationEntry(
            "Ring Face Pull",
            R.string.exercise_ring_face_pull,
            "Face pull en anillas"
        ),
        TranslationEntry(
            "Ring High Row",
            R.string.exercise_ring_high_row,
            "Remo alto en anillas"
        ),
        TranslationEntry(
            "Ring Push Up",
            R.string.exercise_ring_push_up,
            "Flexiones en anillas"
        ),
        TranslationEntry(
            "Ring Row",
            R.string.exercise_ring_row,
            "Remo invertido en anillas"
        ),
        TranslationEntry(
            "Ring Row (Weighted)",
            R.string.exercise_ring_row_weighted,
            "Remo invertido en anillas con lastre"
        ),
        TranslationEntry(
            "Ring Russian Push-Up",
            R.string.exercise_ring_russian_push_up,
            "Flexiones rusas en anillas"
        ),
        TranslationEntry(
            "Ring Triceps Extension",
            R.string.exercise_ring_triceps_extension,
            "Extensión de tríceps en anillas"
        ),
        TranslationEntry(
            "Roman Chair Sit Up",
            R.string.exercise_roman_chair_sit_up,
            "Sit-up en silla romana"
        ),
        TranslationEntry(
            "Rope Pressdown",
            R.string.exercise_rope_pressdown,
            "Extensión de tríceps con cuerda en polea"
        ),
        TranslationEntry(
            "Rotating Plank",
            R.string.exercise_rotating_plank,
            "Plancha con rotación lateral"
        ),
        TranslationEntry(
            "Rotational Landmine Push Press",
            R.string.exercise_rotational_landmine_push_press,
            "Push press rotacional en landmine con barra"
        ),
        TranslationEntry(
            "Row Between Chairs",
            R.string.exercise_row_between_chairs,
            "Remo invertido entre sillas"
        ),
        TranslationEntry(
            "Run Strides",
            R.string.exercise_run_strides,
            "Zancadas de aceleración en carrera"
        ),
        TranslationEntry(
            "Runners Lunge Thoracic Extension",
            R.string.exercise_runners_lunge_thoracic_extension,
            "Zancada de corredor con extensión torácica"
        ),
        TranslationEntry(
            "Safety Bar Front Squat",
            R.string.exercise_safety_bar_front_squat,
            "Sentadilla frontal con barra de seguridad"
        ),
        TranslationEntry(
            "Safety Bar Squat",
            R.string.exercise_safety_bar_squat,
            "Sentadilla con barra de seguridad (SSB)"
        ),
        TranslationEntry(
            "Scapular Push Up",
            R.string.exercise_scapular_push_up,
            "Flexiones escapulares"
        ),
        TranslationEntry(
            "Seal Row",
            R.string.exercise_seal_row,
            "Remo seal row en banco alto con barra"
        ),
        TranslationEntry(
            "Seated Anterior Delt Press (AD)",
            R.string.exercise_seated_anterior_delt_press_ad,
            "Press sentado deltoides anterior mancuernas"
        ),
        TranslationEntry(
            "Seated Front Raise",
            R.string.exercise_seated_front_raise,
            "Elevaciones frontales sentado con mancuernas"
        ),
        TranslationEntry(
            "Self Assisted Inverted Pullover",
            R.string.exercise_self_assisted_inverted_pullover,
            "Pullover invertido autoasistido peso corporal"
        ),
        TranslationEntry(
            "Shadow Boxing",
            R.string.exercise_shadow_boxing,
            "Boxeo de sombra"
        ),
        TranslationEntry(
            "Shadow Kick Boxing",
            R.string.exercise_shadow_kick_boxing,
            "Kickboxing de sombra"
        ),
        TranslationEntry(
            "Shoulder Press (Plate Loaded)",
            R.string.exercise_shoulder_press_plate_loaded,
            "Press de hombros en máquina de discos"
        ),
        TranslationEntry(
            "Shoulder YTWL",
            R.string.exercise_shoulder_ytwl,
            "Elevaciones Y-T-W-L para hombros"
        ),
        TranslationEntry(
            "Side Leg Swings",
            R.string.exercise_side_leg_swings,
            "Balanceo lateral de piernas dinámico"
        ),
        TranslationEntry(
            "Side Plank with Reach Through",
            R.string.exercise_side_plank_with_reach_through,
            "Plancha lateral con rotación por debajo"
        ),
        TranslationEntry(
            "Single Arm Farmer Carry",
            R.string.exercise_single_arm_farmer_carry,
            "Paseo del granjero a una mano con mancuerna"
        ),
        TranslationEntry(
            "Single Arm Floor Press",
            R.string.exercise_single_arm_floor_press,
            "Press de suelo a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Single Arm Iso Row",
            R.string.exercise_single_arm_iso_row,
            "Remo unilateral en máquina"
        ),
        TranslationEntry(
            "Single Arm Kettlebell Swing",
            R.string.exercise_single_arm_kettlebell_swing,
            "Balanceo con kettlebell a una mano"
        ),
        TranslationEntry(
            "Single Arm Landmine Row",
            R.string.exercise_single_arm_landmine_row,
            "Remo en landmine a un brazo con barra"
        ),
        TranslationEntry(
            "Single Arm Overhead Tricep Extension",
            R.string.exercise_single_arm_overhead_tricep_extension,
            "Extensión sobre la cabeza a un brazo mancuerna"
        ),
        TranslationEntry(
            "Single Arm Pushdown",
            R.string.exercise_single_arm_pushdown,
            "Extensión de tríceps a un brazo en máquina"
        ),
        TranslationEntry(
            "Single Arm Shoulder Press",
            R.string.exercise_single_arm_shoulder_press,
            "Press de hombros a un brazo con mancuerna"
        ),
        TranslationEntry(
            "Single Arm Side Straight Arm Lat Pulldown",
            R.string.exercise_single_arm_side_straight_arm_lat_pulldown,
            "Jalón lateral con brazo recto a un brazo"
        ),
        TranslationEntry(
            "Single Leg Calf Raise (Weighted)",
            R.string.exercise_single_leg_calf_raise_weighted,
            "Elevación de talón a una pierna con peso"
        ),
        TranslationEntry(
            "Single Leg Deadlift",
            R.string.exercise_single_leg_deadlift,
            "Peso muerto a una pierna con mancuernas"
        ),
        TranslationEntry(
            "Single Leg Hip Thrust",
            R.string.exercise_single_leg_hip_thrust,
            "Hip thrust a una pierna con mancuerna"
        ),
        TranslationEntry(
            "Single Leg Plate Hop",
            R.string.exercise_single_leg_plate_hop,
            "Saltos sobre disco a una pierna"
        ),
        TranslationEntry(
            "Single Leg Romanian Deadlift",
            R.string.exercise_single_leg_romanian_deadlift,
            "Peso muerto rumano a una pierna con mancuernas"
        ),
        TranslationEntry(
            "Single-Leg Leg Curl",
            R.string.exercise_single_leg_leg_curl,
            "Curl femoral a una pierna en máquina"
        ),
        TranslationEntry(
            "Sissy Squat",
            R.string.exercise_sissy_squat,
            "Sentadilla sissy con peso corporal"
        ),
        TranslationEntry(
            "Skater Hop",
            R.string.exercise_skater_hop,
            "Saltos laterales de patinador"
        ),
        TranslationEntry(
            "SkiErg",
            R.string.exercise_skierg,
            "Simulador de esquí de fondo SkiErg"
        ),
        TranslationEntry(
            "Sled 45 Degrees One Leg Press",
            R.string.exercise_sled_45_degrees_one_leg_press,
            "Prensa de piernas a 45° a una pierna en trineo"
        ),
        TranslationEntry(
            "Sled Full Hack Squat",
            R.string.exercise_sled_full_hack_squat,
            "Sentadilla hack completa en trineo"
        ),
        TranslationEntry(
            "Smith Front Squat Clean Grip",
            R.string.exercise_smith_front_squat_clean_grip,
            "Sentadilla frontal agarre clean en máquina Smith"
        ),
        TranslationEntry(
            "Smith Lateral Step-Up",
            R.string.exercise_smith_lateral_step_up,
            "Step-up lateral en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine AD Press",
            R.string.exercise_smith_machine_ad_press,
            "Press deltoides anterior en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Behind-The-Neck Press",
            R.string.exercise_smith_machine_behind_the_neck_press,
            "Press tras nuca en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Bicep Curl",
            R.string.exercise_smith_machine_bicep_curl,
            "Curl de bíceps en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Deadlift",
            R.string.exercise_smith_machine_deadlift,
            "Peso muerto en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine JM Press",
            R.string.exercise_smith_machine_jm_press,
            "Press JM en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Larsen Press",
            R.string.exercise_smith_machine_larsen_press,
            "Press Larsen en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Seated Military Press",
            R.string.exercise_smith_machine_seated_military_press,
            "Press militar sentado en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Split Squat",
            R.string.exercise_smith_machine_split_squat,
            "Sentadilla split en máquina Smith"
        ),
        TranslationEntry(
            "Smith Machine Split Squat Front Foot Elevated",
            R.string.exercise_smith_machine_split_squat_front_foot_elevated,
            "Sentadilla split pie delantero elevado en Smith"
        ),
        TranslationEntry(
            "Smith Seated Shoulder Press",
            R.string.exercise_smith_seated_shoulder_press,
            "Press de hombros sentado en máquina Smith"
        ),
        TranslationEntry(
            "Smith Shrug",
            R.string.exercise_smith_shrug,
            "Encogimientos de hombros en máquina Smith"
        ),
        TranslationEntry(
            "Smith Standing Military Press",
            R.string.exercise_smith_standing_military_press,
            "Press militar de pie en máquina Smith"
        ),
        TranslationEntry(
            "Smith Sumo Deadlift",
            R.string.exercise_smith_sumo_deadlift,
            "Peso muerto sumo en máquina Smith"
        ),
        TranslationEntry(
            "Split Squat Jump",
            R.string.exercise_split_squat_jump,
            "Sentadilla split con salto"
        ),
        TranslationEntry(
            "Spoto Press",
            R.string.exercise_spoto_press,
            "Press Spoto con pausa en el aire con barra"
        ),
        TranslationEntry(
            "Squat (Low Bar)",
            R.string.exercise_squat_low_bar,
            "Sentadilla con barra baja"
        ),
        TranslationEntry(
            "Squat (Paused)",
            R.string.exercise_squat_paused,
            "Sentadilla con pausa con barra"
        ),
        TranslationEntry(
            "Squat Thruster",
            R.string.exercise_squat_thruster,
            "Thruster (sentadilla con press) con mancuernas"
        ),
        TranslationEntry(
            "Stair Climber",
            R.string.exercise_stair_climber,
            "Escaladora en máquina (stair climber)"
        ),
        TranslationEntry(
            "Standing March (Bands)",
            R.string.exercise_standing_march_bands,
            "Marcha de pie con bandas elásticas"
        ),
        TranslationEntry(
            "Stiff Leg Deadlift",
            R.string.exercise_stiff_leg_deadlift,
            "Peso muerto piernas rígidas con barra"
        ),
        TranslationEntry(
            "Stiff Leg Deficit Deadlift",
            R.string.exercise_stiff_leg_deficit_deadlift,
            "Peso muerto piernas rígidas con déficit barra"
        ),
        TranslationEntry(
            "Straight Leg Calf Raise",
            R.string.exercise_straight_leg_calf_raise,
            "Elevación talones piernas rectas mancuernas"
        ),
        TranslationEntry(
            "Stretching",
            R.string.exercise_stretching,
            "Estiramiento general y flexibilidad"
        ),
        TranslationEntry(
            "Suitcase Carry",
            R.string.exercise_suitcase_carry,
            "Paseo de la maleta (suitcase carry)"
        ),
        TranslationEntry(
            "Suitcase Carry March",
            R.string.exercise_suitcase_carry_march,
            "Marcha de la maleta con mancuerna"
        ),
        TranslationEntry(
            "Suitcase Deadlift",
            R.string.exercise_suitcase_deadlift,
            "Peso muerto tipo maleta con barra"
        ),
        TranslationEntry(
            "Sumo Deadlift (Paused)",
            R.string.exercise_sumo_deadlift_paused,
            "Peso muerto sumo con pausa con barra"
        ),
        TranslationEntry(
            "Supine Spinal Twist Stretch",
            R.string.exercise_supine_spinal_twist_stretch,
            "Torsión espinal acostado boca arriba"
        ),
        TranslationEntry(
            "Suspender Pike",
            R.string.exercise_suspender_pike,
            "Pike en suspensión (TRX)"
        ),
        TranslationEntry(
            "Suspender Y Raise",
            R.string.exercise_suspender_y_raise,
            "Elevaciones en Y en suspensión (TRX)"
        ),
        TranslationEntry(
            "Suspension Chest Press",
            R.string.exercise_suspension_chest_press,
            "Press de pecho en suspensión (TRX)"
        ),
        TranslationEntry(
            "Swim",
            R.string.exercise_swim,
            "Natación"
        ),
        TranslationEntry(
            "T-Bar Row",
            R.string.exercise_t_bar_row,
            "Remo en barra T en máquina"
        ),
        TranslationEntry(
            "Tempo Bench Press",
            R.string.exercise_tempo_bench_press,
            "Press de banca con tempo controlado"
        ),
        TranslationEntry(
            "Tempo Deadlift",
            R.string.exercise_tempo_deadlift,
            "Peso muerto con tempo controlado"
        ),
        TranslationEntry(
            "Terminal Knee Extension",
            R.string.exercise_terminal_knee_extension,
            "Extensión terminal de rodilla (TKE)"
        ),
        TranslationEntry(
            "Thoracic Extension Stretch",
            R.string.exercise_thoracic_extension_stretch,
            "Estiramiento de extensión torácica"
        ),
        TranslationEntry(
            "Trap Bar B-Stance Romanian Deadlift",
            R.string.exercise_trap_bar_b_stance_romanian_deadlift,
            "Peso muerto rumano postura B barra hexagonal"
        ),
        TranslationEntry(
            "Trap Bar Overhead Press",
            R.string.exercise_trap_bar_overhead_press,
            "Press sobre la cabeza con barra hexagonal"
        ),
        TranslationEntry(
            "Trap Bar Power Shrug",
            R.string.exercise_trap_bar_power_shrug,
            "Encogimientos de potencia con barra hexagonal"
        ),
        TranslationEntry(
            "Trap Bar Romanian Deadlift",
            R.string.exercise_trap_bar_romanian_deadlift,
            "Peso muerto rumano con barra hexagonal"
        ),
        TranslationEntry(
            "Trap Bar Shrug",
            R.string.exercise_trap_bar_shrug,
            "Encogimientos de hombros con barra hexagonal"
        ),
        TranslationEntry(
            "Trapbar High Pull",
            R.string.exercise_trapbar_high_pull,
            "Tirón alto con barra hexagonal"
        ),
        TranslationEntry(
            "Treadmill",
            R.string.exercise_treadmill,
            "Caminadora eléctrica (treadmill)"
        ),
        TranslationEntry(
            "Tricep Kickback",
            R.string.exercise_tricep_kickback,
            "Patada de tríceps con mancuerna"
        ),
        TranslationEntry(
            "Triceps Dip Bench Leg",
            R.string.exercise_triceps_dip_bench_leg,
            "Fondos de tríceps en banco piernas apoyadas"
        ),
        TranslationEntry(
            "Triceps Dip on High Parallel Bars",
            R.string.exercise_triceps_dip_on_high_parallel_bars,
            "Fondos tríceps en barras paralelas altas"
        ),
        TranslationEntry(
            "Turkish Get Up",
            R.string.exercise_turkish_get_up,
            "Levantamiento turco (Turkish get-up)"
        ),
        TranslationEntry(
            "Underhand Lat Pulldown",
            R.string.exercise_underhand_lat_pulldown,
            "Jalón al pecho agarre supino en máquina"
        ),
        TranslationEntry(
            "Up Down Plank",
            R.string.exercise_up_down_plank,
            "Plancha dinámica sube y baja"
        ),
        TranslationEntry(
            "Vertical Jump",
            R.string.exercise_vertical_jump,
            "Salto vertical de potencia"
        ),
        TranslationEntry(
            "Viking Press",
            R.string.exercise_viking_press,
            "Press vikingo con barra"
        ),
        TranslationEntry(
            "VO2 Max Intervals",
            R.string.exercise_vo2_max_intervals,
            "Intervalos de alta intensidad VO2 máx"
        ),
        TranslationEntry(
            "Walk",
            R.string.exercise_walk,
            "Caminata al aire libre o en cinta"
        ),
        TranslationEntry(
            "Walking Calf Raise",
            R.string.exercise_walking_calf_raise,
            "Elevación de talones caminando"
        ),
        TranslationEntry(
            "Walking Knee Hugs",
            R.string.exercise_walking_knee_hugs,
            "Abrazos de rodilla al pecho caminando"
        ),
        TranslationEntry(
            "Wall Press Dead Bug",
            R.string.exercise_wall_press_dead_bug,
            "Dead bug con presión isométrica contra pared"
        ),
        TranslationEntry(
            "Weighted Push-Up with Vest",
            R.string.exercise_weighted_push_up_with_vest,
            "Flexiones con chaleco de lastre"
        ),
        TranslationEntry(
            "Weighted Russian Twist with Legs Floor off",
            R.string.exercise_weighted_russian_twist_with_legs_floor_off,
            "Giro ruso con peso y piernas elevadas"
        ),
        TranslationEntry(
            "Wood Chop",
            R.string.exercise_wood_chop,
            "Leñador (wood chop) en polea"
        ),
        TranslationEntry(
            "Wrist Curls",
            R.string.exercise_wrist_curls,
            "Curl de muñeca con mancuernas"
        ),
        TranslationEntry(
            "Y Raise",
            R.string.exercise_y_raise,
            "Elevaciones en Y con mancuernas"
        ),
        TranslationEntry(
            "Yoga",
            R.string.exercise_yoga,
            "Sesión de yoga y movilidad"
        ),
        TranslationEntry(
            "Zercher Front-Foot Elevated Split Squat",
            R.string.exercise_zercher_front_foot_elevated_split_squat,
            "Sentadilla split Zercher pie delantero elevado"
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
