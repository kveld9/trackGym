package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import java.util.Locale

/**
 * Exercise modality axis classifying physical training intent:
 * - STRENGTH: Resistance training targeting muscular tension, force, and hypertrophy.
 * - CARDIO: Conditioning targeting cardiovascular and aerobic/anaerobic capacity.
 * - MOBILITY: Recovery, joint mobility, flexibility, breathing, and activation work.
 */
enum class ExerciseModality(@get:StringRes val nameRes: Int, val displayName: String) {
    STRENGTH(R.string.modality_strength, "Strength"),
    CARDIO(R.string.modality_cardio, "Cardio"),
    MOBILITY(R.string.modality_mobility, "Mobility");

    companion object {
        fun parseOrNull(value: String?): ExerciseModality? {
            if (value == null) return null
            return entries.find {
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            }
        }

        fun fromString(value: String?): ExerciseModality {
            return parseOrNull(value) ?: STRENGTH
        }
    }
}

private val ambiguousStrengthKeywords = listOf(
    "farmers walk",
    "farmer's walk",
    "farmer walk",
    "battle ropes",
    "battle rope",
    "sled push",
    "sled pull",
    "sled drag",
    "sled work",
    "sled",
    "prowler"
)

private val mobilityKeywords = listOf(
    "warm-up",
    "warmup",
    "stretch",
    "mobility",
    "yoga",
    "breathing",
    "meditation",
    "foam roll",
    "foam",
    "smr",
    "activation",
    "drill"
)

private val cardioLongKeywords = listOf(
    "step mill",
    "stepmill",
    "climb mill",
    "climbmill",
    "jump rope",
    "rope jump",
    "skipping rope",
    "rowing machine",
    "row machine",
    "row erg",
    "stair master",
    "stairmaster",
    "ski erg",
    "skierg",
    "treadmill",
    "bicycling",
    "cycling",
    "bike",
    "elliptical",
    "rowing",
    "rower",
    "skating",
    "swimming",
    "jogging",
    "walking",
    "sprinting",
    "running",
    "boxing",
    "shadow",
    "cardio"
)

private val cardioPaddedShortKeywords = listOf(
    " run ",
    " jog ",
    " walk ",
    " swim ",
    " sprint "
)

/**
 * Classifies an exercise into an [ExerciseModality] based on its name.
 * Ambiguous strength/conditioning movements (such as Farmers Walk, Battle Ropes, or Sled work)
 * default to [ExerciseModality.STRENGTH].
 * When names overlap between mobility and cardio (e.g. dynamic stretch runs), MOBILITY takes precedence.
 */
fun classifyExerciseModality(name: String): ExerciseModality {
    val clean = name.lowercase(Locale.ROOT).trim()
    val padded = " $clean "
    return when {
        clean.isEmpty() -> ExerciseModality.STRENGTH
        ambiguousStrengthKeywords.any { clean.contains(it) } -> ExerciseModality.STRENGTH
        mobilityKeywords.any { clean.contains(it) } -> ExerciseModality.MOBILITY
        cardioLongKeywords.any { clean.contains(it) } ||
            cardioPaddedShortKeywords.any { padded.contains(it) } -> ExerciseModality.CARDIO
        else -> ExerciseModality.STRENGTH
    }
}
