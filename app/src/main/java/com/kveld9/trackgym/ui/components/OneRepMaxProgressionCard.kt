package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.OneRepMaxProgressionEngine
import com.kveld9.trackgym.domain.calculator.OneRepMaxProgressionSeries
import com.kveld9.trackgym.domain.model.ExerciseHistoryEntry
import com.kveld9.trackgym.domain.model.WeightUnit

@Composable
fun OneRepMaxProgressionCard(
    history: List<ExerciseHistoryEntry>,
    weightUnit: WeightUnit,
    modifier: Modifier = Modifier
) {
    val series: OneRepMaxProgressionSeries = remember(history) {
        OneRepMaxProgressionEngine.calculateProgression(history)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header: Title & Delta summary
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.one_rm_progression_title),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                if (series.points.size >= 2) {
                    val isPositive = series.deltaKg >= 0.0
                    val deltaStr = weightUnit.format(kotlin.math.abs(series.deltaKg))
                    val pctStr = String.format(java.util.Locale.US, "%.1f%%", series.percentageGrowth)
                    val textRes = if (isPositive) R.string.one_rm_progression_gain else R.string.one_rm_progression_drop

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isPositive) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer
                    ) {
                        Text(
                            text = stringResource(textRes, deltaStr, pctStr),
                            color = if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (series.points.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.one_rm_progression_no_data),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            } else if (series.points.size == 1) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = series.points.first().dateLabel,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                    Text(
                        text = weightUnit.format(series.points.first().oneRmKg),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                // Interactive / High-contrast OLED timeline curve
                OneRepMaxTimelineCanvas(
                    series = series,
                    weightUnit = weightUnit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Date timeline markers at extremes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = series.points.first().dateLabel,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    )
                    Text(
                        text = series.points.last().dateLabel,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun OneRepMaxTimelineCanvas(
    series: OneRepMaxProgressionSeries,
    weightUnit: WeightUnit,
    modifier: Modifier = Modifier
) {
    val lineColor = MaterialTheme.colorScheme.primary
    val pointColor = MaterialTheme.colorScheme.onSurface
    val gridColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val paddingVertical = 16.dp.toPx()
        val paddingHorizontal = 12.dp.toPx()

        val usableWidth = width - (paddingHorizontal * 2)
        val usableHeight = height - (paddingVertical * 2)

        val minVal = series.min1RmKg
        val maxVal = series.max1RmKg
        val range = (maxVal - minVal).coerceAtLeast(1.0)

        val points = series.points
        val stepX = usableWidth / (points.size - 1).coerceAtLeast(1)

        val computedOffsets = points.mapIndexed { index, point ->
            val x = paddingHorizontal + (index * stepX)
            val normalizedY = ((point.oneRmKg - minVal) / range).toFloat()
            // Invert Y so highest value is at top
            val y = height - paddingVertical - (normalizedY * usableHeight)
            Offset(x, y)
        }

        // Draw horizontal baseline
        drawLine(
            color = gridColor,
            start = Offset(paddingHorizontal, height - paddingVertical),
            end = Offset(width - paddingHorizontal, height - paddingVertical),
            strokeWidth = 1.dp.toPx()
        )

        // Draw line curve
        val path = Path().apply {
            computedOffsets.forEachIndexed { index, offset ->
                if (index == 0) moveTo(offset.x, offset.y) else lineTo(offset.x, offset.y)
            }
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = 2.5.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // Draw data node circles
        computedOffsets.forEach { offset ->
            drawCircle(
                color = lineColor,
                radius = 3.5.dp.toPx(),
                center = offset
            )
            drawCircle(
                color = pointColor,
                radius = 1.5.dp.toPx(),
                center = offset
            )
        }
    }
}
