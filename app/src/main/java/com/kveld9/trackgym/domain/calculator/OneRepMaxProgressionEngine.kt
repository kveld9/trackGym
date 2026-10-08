package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseHistoryEntry
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Single data point on an estimated 1RM progression timeline.
 */
data class OneRepMaxTimelinePoint(
    val dateMillis: Long,
    val dateLabel: String,
    val oneRmKg: Double,
    val maxWeightKg: Double,
    val workoutName: String
)

/**
 * Complete historical progression series of 1RM for an exercise.
 */
data class OneRepMaxProgressionSeries(
    val points: List<OneRepMaxTimelinePoint>,
    val min1RmKg: Double,
    val max1RmKg: Double,
    val deltaKg: Double,
    val percentageGrowth: Float
)

/**
 * Domain engine calculating chronological 1RM evolution curve and milestones.
 */
object OneRepMaxProgressionEngine {

    fun calculateProgression(history: List<ExerciseHistoryEntry>): OneRepMaxProgressionSeries {
        val validPoints = history
            .filter { it.best1RmKg > 0.0 }
            .sortedBy { it.dateMillis }

        if (validPoints.isEmpty()) {
            return OneRepMaxProgressionSeries(
                points = emptyList(),
                min1RmKg = 0.0,
                max1RmKg = 0.0,
                deltaKg = 0.0,
                percentageGrowth = 0f
            )
        }

        val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
        val points = validPoints.map { entry ->
            OneRepMaxTimelinePoint(
                dateMillis = entry.dateMillis,
                dateLabel = dateFormat.format(Date(entry.dateMillis)),
                oneRmKg = entry.best1RmKg,
                maxWeightKg = entry.maxWeightKg,
                workoutName = entry.workoutName
            )
        }

        val first1Rm = points.first().oneRmKg
        val last1Rm = points.last().oneRmKg
        val min1Rm = points.minOf { it.oneRmKg }
        val max1Rm = points.maxOf { it.oneRmKg }
        val delta = last1Rm - first1Rm
        val growth = if (first1Rm > 0.0) ((delta / first1Rm) * 100).toFloat() else 0f

        return OneRepMaxProgressionSeries(
            points = points,
            min1RmKg = min1Rm,
            max1RmKg = max1Rm,
            deltaKg = delta,
            percentageGrowth = growth
        )
    }
}
