package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMeasurement
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

data class MeasurementDataPoint(
    val id: Long,
    val value: Double,
    val movingAverage: Double,
    val timestamp: Long,
    val dateLabel: String,
    val notes: String = ""
)

data class MeasurementTrendSeries(
    val typeName: String,
    val unit: String,
    val points: List<MeasurementDataPoint>,
    val currentValue: Double,
    val netDelta: Double,
    val percentDelta: Double,
    val minValue: Double,
    val maxValue: Double
)

/**
 * Calculates trend curves, moving averages (rolling 3-point/7-day window) to filter water retention
 * and short-term fluctuations, and statistical progress deltas.
 */
object BodyMeasurementTrendEngine {

    fun calculateTrend(
        measurements: List<BodyMeasurement>,
        windowSize: Int = 3,
        dateFormat: String = "d MMM"
    ): MeasurementTrendSeries {
        if (measurements.isEmpty()) {
            return MeasurementTrendSeries(
                typeName = "",
                unit = "",
                points = emptyList(),
                currentValue = 0.0,
                netDelta = 0.0,
                percentDelta = 0.0,
                minValue = 0.0,
                maxValue = 0.0
            )
        }

        // Sort ascending by measured date
        val sorted = measurements.sortedBy { it.measuredAt }
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val unit = sorted.first().type.defaultUnit

        val points = mutableListOf<MeasurementDataPoint>()
        val recentValues = ArrayDeque<Double>()

        for (item in sorted) {
            recentValues.addLast(item.value)
            if (recentValues.size > windowSize.coerceAtLeast(1)) {
                recentValues.removeFirst()
            }
            val ma = (recentValues.average() * 100.0).roundToInt() / 100.0
            val dateLabel = formatter.format(Date(item.measuredAt))

            points.add(
                MeasurementDataPoint(
                    id = item.id,
                    value = item.value,
                    movingAverage = ma,
                    timestamp = item.measuredAt,
                    dateLabel = dateLabel,
                    notes = item.notes
                )
            )
        }

        val firstVal = points.first().value
        val lastVal = points.last().value
        val netDelta = ((lastVal - firstVal) * 100.0).roundToInt() / 100.0
        val pctDelta = if (firstVal > 0.0) {
            ((netDelta / firstVal) * 1000.0).roundToInt() / 10.0
        } else {
            0.0
        }

        val min = points.minOf { it.value }
        val max = points.maxOf { it.value }

        return MeasurementTrendSeries(
            typeName = sorted.first().type.name,
            unit = unit,
            points = points,
            currentValue = lastVal,
            netDelta = netDelta,
            percentDelta = pctDelta,
            minValue = min,
            maxValue = max
        )
    }
}
