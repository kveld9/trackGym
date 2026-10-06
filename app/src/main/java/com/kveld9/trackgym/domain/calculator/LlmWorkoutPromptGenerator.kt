package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutComparison
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Generates structured Markdown and JSON prompts summarizing workout session performance,
 * progressive overload deltas, and unlocked PRs for LLMs (ChatGPT, Claude, DeepSeek).
 */
object LlmWorkoutPromptGenerator {

    private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)

    fun generateMarkdownPrompt(
        comparison: WorkoutComparison,
        weightUnit: WeightUnit = WeightUnit.KG
    ): String {
        val workout = comparison.currentWorkout
        val unitLabel = weightUnit.symbol
        val dateStr = DATE_FORMAT.format(Date(workout.completedAt ?: workout.startedAt))
        val durationMin = workout.durationSeconds / 60

        return buildString {
            append("# Workout Session Analysis Request\n\n")
            append("Please analyze my latest workout performance and provide actionable coaching recommendations ")
            append("focusing on progressive overload, fatigue management, and recovery.\n\n")

            append("## 1. Session Overview\n")
            append("- **Workout Name**: ").append(workout.name).append('\n')
            append("- **Date & Time**: ").append(dateStr).append('\n')
            append("- **Duration**: ").append(durationMin).append(" min\n")
            append("- **Total Volume**: ").append(String.format(Locale.US, "%.1f", workout.totalVolume)).append(" ").append(unitLabel).append('\n')
            append("- **Personal Records Broken**: ").append(comparison.totalRecordsUnlocked.size).append("\n\n")

            if (comparison.totalRecordsUnlocked.isNotEmpty()) {
                appendRecordsSection(this, comparison.totalRecordsUnlocked)
            }

            append("## 2. Exercise Performance & Progression\n")
            for (exComp in comparison.exerciseComparisons) {
                appendExerciseSection(this, exComp, unitLabel)
            }

            append("## 3. Coaching Questions\n")
            append("1. **Progressive Overload Assessment**: Based on the load and volume changes compared to previous sessions, is the progression trajectory optimal?\n")
            append("2. **Fatigue & Volume**: Are there any signs of excessive junk volume or overreaching in specific muscle groups?\n")
            append("3. **Next Session Targets**: What specific loads and rep targets should I aim for in each exercise in the next session?\n")
            append("4. **Recovery & Readiness**: What recovery protocol and rest timeline do you recommend before training these muscles again?\n")
        }
    }

    private fun appendRecordsSection(sb: StringBuilder, records: List<PersonalRecord>) {
        sb.append("## Personal Records Unlocked\n")
        for (pr in records) {
            sb.append("- ").append(pr.description).append('\n')
        }
        sb.append('\n')
    }

    private fun appendExerciseSection(
        sb: StringBuilder,
        exComp: ExerciseComparison,
        unitLabel: String
    ) {
        val deltaSign = if (exComp.totalVolumeDeltaKg >= 0.0) "+" else ""
        sb.append("### ").append(exComp.exercise.name)
            .append(" (").append(exComp.exercise.muscleGroup.name).append(")\n")
        sb.append("- **Volume Delta**: ")
            .append(deltaSign).append(String.format(Locale.US, "%.1f", exComp.totalVolumeDeltaKg)).append(" ")
            .append(unitLabel).append('\n')

        for (setComp in exComp.setComparisons) {
            appendSetComparison(sb, setComp, unitLabel)
        }
        sb.append('\n')
    }

    private fun appendSetComparison(
        sb: StringBuilder,
        setComp: SetComparison,
        unitLabel: String
    ) {
        val curr = setComp.currentSet
        val prev = setComp.previousSet
        val rpePart = if (curr.rpe != null) " @ RPE ${curr.rpe}" else ""
        val typePart = if (curr.setType.name != "NORMAL") " [${curr.setType.name}]" else ""

        sb.append("  - Set ").append(curr.setNumber).append(": ")
            .append(curr.weightKg).append(" ").append(unitLabel).append(" × ")
            .append(curr.reps).append(" reps")
            .append(rpePart).append(typePart)

        if (prev != null) {
            val weightDelta = curr.weightKg - prev.weightKg
            val repsDelta = curr.reps - prev.reps
            val wSign = if (weightDelta >= 0.0) "+" else ""
            val rSign = if (repsDelta >= 0) "+" else ""
            sb.append(" (Prev: ").append(prev.weightKg).append(" × ").append(prev.reps)
                .append(" | ").append(wSign).append(weightDelta).append(" ").append(unitLabel)
                .append(", ").append(rSign).append(repsDelta).append(" reps)")
        } else {
            sb.append(" (New set)")
        }
        sb.append('\n')
    }
}
