package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import java.util.Locale

enum class ExerciseCategory(@get:StringRes val nameRes: Int, val displayName: String) {
    BARBELL(R.string.category_barbell, "Barbell"),
    DUMBBELL(R.string.category_dumbbell, "Dumbbell"),
    MACHINE(R.string.category_machine, "Machine"),
    CABLE(R.string.category_cable, "Cable"),
    BODYWEIGHT(R.string.category_bodyweight, "Bodyweight"),
    SMITH_MACHINE(R.string.category_smith_machine, "Smith Machine"),
    KETTLEBELL(R.string.category_kettlebell, "Kettlebell"),
    BAND(R.string.category_band, "Band"),
    OTHER(R.string.category_other, "Other");

    companion object {
        fun fromString(value: String): ExerciseCategory {
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: OTHER
        }
    }
}

private val legacyCardioMachineKeywords = listOf(
    "treadmill",
    "bike",
    "cycling",
    "bicycling",
    "elliptical",
    "rower",
    "rowing",
    "climbmill",
    "stepmill",
    "step mill",
    "stairmaster",
    "skierg",
    "sled",
    "prowler",
    "recumbent"
)

private val legacyCardioDumbbellKeywords = listOf(
    "dumbbell"
)

private val legacyCardioBodyweightKeywords = listOf(
    "run",
    "sprint",
    "jog",
    "walk",
    "swim",
    "schwimmen",
    "jump rope",
    "jumping jack",
    "bear crawl",
    "boxing",
    "shadow",
    "skating",
    "burpee",
    "high knee",
    "rope jump",
    "skipping"
)

/**
 * Classifies an exercise with the obsolete legacy 'CARDIO' category into a concrete
 * equipment category ([ExerciseCategory.MACHINE], [ExerciseCategory.DUMBBELL],
 * [ExerciseCategory.BODYWEIGHT], or [ExerciseCategory.OTHER]) based on its name keywords.
 *
 * This mirrors the exact keyword heuristic of MIGRATION_19_20 in GymDatabase for import boundaries
 * (backup restore and routine sharing payloads) and must stay consistent with it.
 */
fun classifyLegacyCardioCategory(name: String): ExerciseCategory {
    val lower = name.lowercase(Locale.ROOT)
    return when {
        legacyCardioMachineKeywords.any { lower.contains(it) } -> ExerciseCategory.MACHINE
        legacyCardioDumbbellKeywords.any { lower.contains(it) } -> ExerciseCategory.DUMBBELL
        legacyCardioBodyweightKeywords.any { lower.contains(it) } -> ExerciseCategory.BODYWEIGHT
        else -> ExerciseCategory.OTHER
    }
}

