package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.PeriodizedRoutineEngine
import com.kveld9.trackgym.domain.model.PeriodizationPhase
import com.kveld9.trackgym.domain.model.PeriodizedCycle
import com.kveld9.trackgym.domain.model.PeriodizedWeek

@Composable
fun PeriodizedCycleDialog(
    routineName: String,
    initialIsPeriodized: Boolean,
    initialCycle: PeriodizedCycle?,
    onSave: (isPeriodized: Boolean, cycle: PeriodizedCycle?) -> Unit,
    onDismiss: () -> Unit
) {
    val defaultCycle = initialCycle ?: PeriodizedRoutineEngine.createDefaultCycle(4)
    var isEnabled by remember { mutableStateOf(initialIsPeriodized) }
    var totalWeeks by remember { mutableIntStateOf(defaultCycle.totalWeeks) }
    var currentWeek by remember { mutableIntStateOf(defaultCycle.currentWeek) }
    var autoAdvance by remember { mutableStateOf(defaultCycle.autoAdvanceOnCompletion) }
    var weeksList by remember {
        mutableStateOf(
            if (defaultCycle.weeks.isNotEmpty() && defaultCycle.weeks.size == defaultCycle.totalWeeks) {
                defaultCycle.weeks
            } else {
                PeriodizedRoutineEngine.createDefaultCycle(totalWeeks).weeks
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 16.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Column {
                    Text(
                        text = stringResource(R.string.periodized_cycle_title),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = routineName,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Master Enable Switch
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(R.string.periodized_cycle_enable_label),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.periodized_cycle_enable_desc),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = isEnabled,
                            onCheckedChange = { isEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary,
                                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }

                if (isEnabled) {
                    // Total Weeks Selector
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.periodized_cycle_total_weeks_label),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        val presetWeeks = listOf(4, 6, 8, 12)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            presetWeeks.forEach { weeks ->
                                val selected = totalWeeks == weeks
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHighest,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            totalWeeks = weeks
                                            if (currentWeek > weeks) currentWeek = weeks
                                            weeksList = PeriodizedRoutineEngine.createDefaultCycle(weeks).weeks
                                        }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = stringResource(R.string.periodized_cycle_weeks_count, weeks),
                                            fontSize = 13.sp,
                                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Auto Advance Switch
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(R.string.periodized_cycle_auto_advance_label),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = stringResource(R.string.periodized_cycle_auto_advance_desc),
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = autoAdvance,
                                onCheckedChange = { autoAdvance = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }

                    // Weeks Breakdown
                    Text(
                        text = stringResource(R.string.periodized_cycle_breakdown_label),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        weeksList.forEach { week ->
                            val isActive = currentWeek == week.weekNumber
                            PeriodizedWeekCard(
                                week = week,
                                isActive = isActive,
                                onSetActive = { currentWeek = week.weekNumber }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isEnabled) {
                        val cycle = PeriodizedCycle(
                            totalWeeks = totalWeeks,
                            currentWeek = currentWeek.coerceIn(1, totalWeeks),
                            autoAdvanceOnCompletion = autoAdvance,
                            weeks = weeksList
                        )
                        onSave(true, cycle)
                    } else {
                        onSave(false, null)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(44.dp)
            ) {
                Text(
                    text = stringResource(R.string.action_save),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.height(44.dp)
            ) {
                Text(
                    text = stringResource(R.string.action_cancel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Composable
private fun PeriodizedWeekCard(
    week: PeriodizedWeek,
    isActive: Boolean,
    onSetActive: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onSetActive() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isActive) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            }
        ),
        border = if (isActive) {
            CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary))
        } else {
            CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.periodized_cycle_week_number, week.weekNumber),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    PhaseBadge(phase = week.phase)
                    if (isActive) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(
                                text = stringResource(R.string.periodized_cycle_active_badge),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                val volPct = (week.volumeMultiplier * 100).toInt()
                val intPct = (week.intensityMultiplier * 100).toInt()
                val deltaInt = intPct - 100
                val intSign = if (deltaInt > 0) "+$deltaInt%" else "$deltaInt%"
                val rpeText = week.targetRpe?.let { " • RPE $it" } ?: ""

                Text(
                    text = "${stringResource(R.string.periodized_volume_label)}: $volPct% • ${stringResource(R.string.periodized_load_label)}: $intSign$rpeText",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PhaseBadge(phase: PeriodizationPhase) {
    val (labelRes, containerColor, textColor) = when (phase) {
        PeriodizationPhase.ACCUMULATION -> Triple(
            R.string.periodization_phase_accumulation,
            MaterialTheme.colorScheme.surfaceContainerHighest,
            MaterialTheme.colorScheme.primary
        )
        PeriodizationPhase.INTENSIFICATION -> Triple(
            R.string.periodization_phase_intensification,
            MaterialTheme.colorScheme.surfaceContainerHighest,
            MaterialTheme.colorScheme.tertiary
        )
        PeriodizationPhase.PEAK -> Triple(
            R.string.periodization_phase_peak,
            MaterialTheme.colorScheme.surfaceContainerHighest,
            MaterialTheme.colorScheme.error
        )
        PeriodizationPhase.DELOAD -> Triple(
            R.string.periodization_phase_deload,
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer
        )
        PeriodizationPhase.CUSTOM -> Triple(
            R.string.periodization_phase_custom,
            MaterialTheme.colorScheme.surfaceContainerHighest,
            MaterialTheme.colorScheme.onSurface
        )
    }

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = containerColor
    ) {
        Text(
            text = stringResource(labelRes),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}
