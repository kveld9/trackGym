package com.kveld9.trackgym.domain.calculator

import androidx.annotation.StringRes
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup

/**
 * Categorization of the rationale behind an exercise substitution recommendation.
 */
enum class SubstitutionReason(@get:StringRes val nameRes: Int) {
    EXACT_ANATOMY_AND_MECHANICS(R.string.substitute_reason_exact),
    SAME_PRIMARY_MUSCLE(R.string.substitute_reason_same_primary),
    SAME_MUSCLE_GROUP(R.string.substitute_reason_same_group),
    SECONDARY_ASSISTANT(R.string.substitute_reason_secondary)
}

/**
 * Represents an exercise substitution candidate with its match score and rationale.
 */
data class ExerciseSubstitute(
    val exercise: Exercise,
    val matchScore: Int,
    val reason: SubstitutionReason,
    val isSameMechanics: Boolean,
    val isSamePrimaryMuscle: Boolean
)

/**
 * Domain engine that computes and ranks equivalent substitute exercises when
 * equipment is unavailable, broken, or busy.
 *
 * Considers primary mover muscles, secondary synergists, biomechanical mechanics
 * (compound vs isolation), and equipment diversity.
 */
object ExerciseSubstitutionEngine {

    /**
     * Finds and ranks suitable substitute exercises for a given target exercise.
     *
     * @param target The exercise to replace.
     * @param allExercises The catalog of exercises available.
     * @param excludedCategory Optional equipment category to exclude (e.g., equipment currently busy).
     * @param targetCategory Optional equipment category to filter for (e.g., user only wants dumbbell substitutes).
     * @param limit Maximum number of substitutes to return (default 15).
     */
    fun findSubstitutes(
        target: Exercise,
        allExercises: List<Exercise>,
        excludedCategory: ExerciseCategory? = null,
        targetCategory: ExerciseCategory? = null,
        limit: Int = 15
    ): List<ExerciseSubstitute> {
        val targetInvolvements = MuscleAnatomyRegistry.getInvolvementsForExercise(target)
        val targetPrimary = targetInvolvements.firstOrNull { it.isPrimary }?.muscle ?: target.primaryMuscle
        val targetSecondaries = targetInvolvements.filter { !it.isPrimary }.map { it.muscle }.toSet()

        val results = mutableListOf<ExerciseSubstitute>()

        for (candidate in allExercises) {
            // Exclude the target exercise itself
            if (candidate.id == target.id && candidate.id != 0L) continue
            if (candidate.name.equals(target.name, ignoreCase = true)) continue

            // Filter by equipment availability
            if (excludedCategory != null && candidate.category == excludedCategory) continue
            if (targetCategory != null && candidate.category != targetCategory) continue

            val candidateInvolvements = MuscleAnatomyRegistry.getInvolvementsForExercise(candidate)
            val candidatePrimary = candidateInvolvements.firstOrNull { it.isPrimary }?.muscle ?: candidate.primaryMuscle
            val candidateSecondaries = candidateInvolvements.filter { !it.isPrimary }.map { it.muscle }.toSet()

            val samePrimary = targetPrimary != null && candidatePrimary != null && targetPrimary == candidatePrimary
            val sameMechanics = target.mechanics == candidate.mechanics
            val sameMuscleGroup = target.muscleGroup == candidate.muscleGroup

            val baseScore: Int
            val reason: SubstitutionReason

            if (samePrimary && sameMechanics) {
                baseScore = 80
                reason = SubstitutionReason.EXACT_ANATOMY_AND_MECHANICS
            } else if (samePrimary) {
                baseScore = 65
                reason = SubstitutionReason.SAME_PRIMARY_MUSCLE
            } else if (sameMuscleGroup && sameMechanics) {
                baseScore = 60
                reason = SubstitutionReason.SAME_MUSCLE_GROUP
            } else if (sameMuscleGroup) {
                baseScore = 45
                reason = SubstitutionReason.SAME_MUSCLE_GROUP
            } else if (targetPrimary != null && candidateSecondaries.contains(targetPrimary)) {
                baseScore = 30
                reason = SubstitutionReason.SECONDARY_ASSISTANT
            } else if (candidatePrimary != null && targetSecondaries.contains(candidatePrimary)) {
                baseScore = 25
                reason = SubstitutionReason.SECONDARY_ASSISTANT
            } else {
                // No anatomical correlation
                continue
            }

            var finalScore = baseScore

            // Bonus for secondary muscle overlap (up to +9)
            val sharedSecondaries = targetSecondaries.intersect(candidateSecondaries).size
            finalScore += (sharedSecondaries * 3).coerceAtMost(9)

            // Equipment diversity bonus: when substituting, different equipment is desirable (+5)
            if (candidate.category != target.category) {
                finalScore += 5
            }

            // Built-in standard exercises get slight preference (+2)
            if (!candidate.isCustom) {
                finalScore += 2
            }

            val clampedScore = finalScore.coerceIn(1, 100)

            results.add(
                ExerciseSubstitute(
                    exercise = candidate,
                    matchScore = clampedScore,
                    reason = reason,
                    isSameMechanics = sameMechanics,
                    isSamePrimaryMuscle = samePrimary
                )
            )
        }

        return results
            .sortedWith(
                compareByDescending<ExerciseSubstitute> { it.matchScore }
                    .thenByDescending { it.isSameMechanics }
                    .thenBy { it.exercise.name }
            )
            .take(limit)
    }
}
