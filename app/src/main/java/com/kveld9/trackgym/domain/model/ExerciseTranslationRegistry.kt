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
        TranslationEntry("Cable Crunch", R.string.exercise_cable_crunch, "Crunch en polea")
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
