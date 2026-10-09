package com.kveld9.trackgym.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.BodyMeasurementTrendEngine
import com.kveld9.trackgym.domain.calculator.MeasurementTrendSeries
import com.kveld9.trackgym.domain.model.BodyMeasurement
import com.kveld9.trackgym.domain.model.BodyMeasurementType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BodyTelemetryCard(
    measurements: List<BodyMeasurement>,
    onLogMeasurement: (type: BodyMeasurementType, value: Double, notes: String) -> Unit,
    onDeleteMeasurement: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }
    var selectedType by remember { mutableStateOf(BodyMeasurementType.WEIGHT) }
    var showLogDialog by remember { mutableStateOf(false) }

    // Filter measurements for selected metric
    val filtered = remember(measurements, selectedType) {
        measurements.filter { it.type == selectedType }
    }

    val trendSeries: MeasurementTrendSeries = remember(filtered) {
        BodyMeasurementTrendEngine.calculateTrend(filtered)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Straighten,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = stringResource(R.string.body_telemetry_title),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(selectedType.displayNameRes),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { showLogDialog = true },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.body_meas_log_entry),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier.padding(top = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.body_telemetry_subtitle),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    // Horizontal Type Selector Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BodyMeasurementType.entries.forEach { type ->
                            val isSelected = selectedType == type
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedType = type },
                                label = { Text(stringResource(type.displayNameRes), fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    selectedLabelColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    // Stats summary card
                    if (trendSeries.points.isNotEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.body_meas_latest, trendSeries.currentValue, selectedType.defaultUnit),
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (trendSeries.points.size > 1) {
                                val deltaColor = if (selectedType == BodyMeasurementType.WEIGHT || selectedType == BodyMeasurementType.WAIST) {
                                    if (trendSeries.netDelta <= 0.0) Color(0xFF4CAF50) else Color(0xFFFFA000)
                                } else {
                                    if (trendSeries.netDelta >= 0.0) Color(0xFF4CAF50) else Color(0xFFFFA000)
                                }
                                Text(
                                    text = stringResource(R.string.body_meas_net_delta, trendSeries.netDelta, selectedType.defaultUnit, trendSeries.percentDelta),
                                    color = deltaColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Trend Curve Canvas
                        if (trendSeries.points.size >= 2) {
                            BodyMeasurementTrendCanvas(
                                series = trendSeries,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.body_meas_no_data),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp
                            )
                        }
                    }

                    // Recent logs list for this type (up to 3 items)
                    if (filtered.isNotEmpty()) {
                        val recentDateFormat = remember { SimpleDateFormat("d MMM yyyy", Locale.getDefault()) }
                        val recentList = filtered.sortedByDescending { it.measuredAt }.take(3)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            recentList.forEach { item ->
                                val dateStr = recentDateFormat.format(Date(item.measuredAt))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "${item.value} ${selectedType.defaultUnit}",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = if (item.notes.isNotBlank()) "$dateStr • ${item.notes}" else dateStr,
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    IconButton(
                                        onClick = { onDeleteMeasurement(item.id) },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = stringResource(R.string.action_delete),
                                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showLogDialog) {
        LogMeasurementDialog(
            type = selectedType,
            onDismiss = { showLogDialog = false },
            onConfirm = { value, notes ->
                onLogMeasurement(selectedType, value, notes)
                showLogDialog = false
            }
        )
    }
}

@Composable
private fun BodyMeasurementTrendCanvas(
    series: MeasurementTrendSeries,
    modifier: Modifier = Modifier
) {
    val lineColor = MaterialTheme.colorScheme.primary
    val movingAvgColor = Color(0xFFFFA000) // Amber moving average

    val points = series.points
    if (points.size < 2) return

    val minVal = series.minValue
    val maxVal = series.maxValue
    val range = (maxVal - minVal).coerceAtLeast(0.5)

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val padding = 16f

        val plotW = width - padding * 2
        val plotH = height - padding * 2

        fun getX(index: Int): Float {
            return padding + (index.toFloat() / (points.size - 1)) * plotW
        }

        fun getY(value: Double): Float {
            val norm = (value - minVal) / range
            return (padding + plotH - (norm * plotH)).toFloat()
        }

        // Draw Raw Values Line
        val rawPath = Path().apply {
            moveTo(getX(0), getY(points.first().value))
            for (i in 1 until points.size) {
                lineTo(getX(i), getY(points[i].value))
            }
        }
        drawPath(
            path = rawPath,
            color = lineColor.copy(alpha = 0.5f),
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Draw Moving Average Trend Line (Smoothed)
        val maPath = Path().apply {
            moveTo(getX(0), getY(points.first().movingAverage))
            for (i in 1 until points.size) {
                lineTo(getX(i), getY(points[i].movingAverage))
            }
        }
        drawPath(
            path = maPath,
            color = movingAvgColor,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Draw points on moving average
        for (i in points.indices) {
            val center = Offset(getX(i), getY(points[i].movingAverage))
            drawCircle(
                color = movingAvgColor,
                radius = 3.dp.toPx(),
                center = center
            )
        }
    }
}

@Composable
private fun LogMeasurementDialog(
    type: BodyMeasurementType,
    onDismiss: () -> Unit,
    onConfirm: (Double, String) -> Unit
) {
    var valueText by remember { mutableStateOf("") }
    var notesText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "${stringResource(R.string.body_meas_log_entry)}: ${stringResource(type.displayNameRes)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = valueText,
                    onValueChange = { valueText = it },
                    label = { Text("${stringResource(R.string.body_meas_value_label)} (${type.defaultUnit})") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text(stringResource(R.string.label_notes_optional)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val parsed = valueText.toDoubleOrNull()
                    if (parsed != null && parsed > 0.0) {
                        onConfirm(parsed, notesText.trim())
                    }
                }
            ) {
                Text(stringResource(R.string.action_save), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
