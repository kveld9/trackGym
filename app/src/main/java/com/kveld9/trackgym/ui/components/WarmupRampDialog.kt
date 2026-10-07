package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.WarmupGenerator
import com.kveld9.trackgym.domain.calculator.WarmupSetConfig
import com.kveld9.trackgym.domain.model.WeightUnit
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun WarmupRampDialog(
    exerciseName: String,
    workingWeightKg: Double,
    currentProtocol: List<WarmupSetConfig>?,
    weightUnit: WeightUnit = WeightUnit.KG,
    onSaveProtocol: (List<WarmupSetConfig>?) -> Unit,
    onApplyAndAddSets: (List<WarmupSetConfig>) -> Unit,
    onDismiss: () -> Unit
) {
    val effectiveWeight = if (workingWeightKg > 0.0) workingWeightKg else 60.0
    val steps = remember {
        mutableStateListOf<WarmupSetConfig>().apply {
            addAll(currentProtocol ?: WarmupGenerator.DEFAULT_WARMUP_PROTOCOL)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 16.dp),
        title = {
            Column {
                Text(
                    text = exerciseName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.warmup_ramp_working_weight, weightUnit.format(effectiveWeight)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.warmup_ramp_presets_label),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Presets Row
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val presets = listOf(
                        Pair(R.string.warmup_ramp_preset_standard, WarmupGenerator.PRESET_STANDARD),
                        Pair(R.string.warmup_ramp_preset_pyramid, WarmupGenerator.PRESET_PYRAMID),
                        Pair(R.string.warmup_ramp_preset_volume, WarmupGenerator.PRESET_VOLUME),
                        Pair(R.string.warmup_ramp_preset_express, WarmupGenerator.PRESET_EXPRESS)
                    )
                    items(presets) { (nameRes, presetList) ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceContainerHigh,
                            modifier = Modifier.clickable {
                                steps.clear()
                                steps.addAll(presetList)
                            }
                        ) {
                            Text(
                                text = stringResource(nameRes),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Steps list
                steps.forEachIndexed { index, step ->
                    WarmupStepCard(
                        index = index,
                        step = step,
                        effectiveWeightKg = effectiveWeight,
                        weightUnit = weightUnit,
                        canDelete = steps.size > 1,
                        onUpdateStep = { updated ->
                            steps[index] = updated
                        },
                        onDeleteStep = {
                            if (steps.size > 1) {
                                steps.removeAt(index)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Add Step Button
                OutlinedButton(
                    onClick = {
                        val lastPct = steps.lastOrNull()?.percentage ?: 0.50
                        val newPct = (lastPct + 0.10).coerceAtMost(0.90)
                        val newReps = (steps.lastOrNull()?.reps ?: 3).coerceAtLeast(1)
                        steps.add(WarmupSetConfig(percentage = newPct, reps = newReps, isBarOnly = false))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.warmup_ramp_add_step),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Reset to Default button
                TextButton(
                    onClick = {
                        steps.clear()
                        steps.addAll(WarmupGenerator.DEFAULT_WARMUP_PROTOCOL)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.warmup_ramp_reset),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onApplyAndAddSets(steps.toList()) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.warmup_ramp_action_apply),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                TextButton(
                    onClick = { onSaveProtocol(steps.toList()) },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.warmup_ramp_action_save),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.action_cancel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    )
}

@Composable
private fun WarmupStepCard(
    index: Int,
    step: WarmupSetConfig,
    effectiveWeightKg: Double,
    weightUnit: WeightUnit,
    canDelete: Boolean,
    onUpdateStep: (WarmupSetConfig) -> Unit,
    onDeleteStep: () -> Unit
) {
    val minWeightKg = 20.0
    val rawWeight = if (step.isBarOnly) minWeightKg else (effectiveWeightKg * step.percentage).coerceAtLeast(minWeightKg)
    val roundedWeight = round(rawWeight / 2.5) * 2.5
    val displayWeightStr = weightUnit.format(roundedWeight)

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header Row: Set Number, Calculated Load, Delete
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.warmup_ramp_set_number, index + 1),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(
                            text = "$displayWeightStr × ${step.reps}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }

                    if (canDelete) {
                        IconButton(
                            onClick = onDeleteStep,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Controls Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Empty Bar Toggle
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        onUpdateStep(step.copy(isBarOnly = !step.isBarOnly))
                    }
                ) {
                    Checkbox(
                        checked = step.isBarOnly,
                        onCheckedChange = { isChecked ->
                            onUpdateStep(step.copy(isBarOnly = isChecked))
                        },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.warmup_ramp_bar_only_checkbox),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Percentage stepper (if not bar only)
                if (!step.isBarOnly) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            onClick = {
                                val currentPctInt = (step.percentage * 100).roundToInt()
                                val newPct = ((currentPctInt - 5).coerceAtLeast(20)) / 100.0
                                onUpdateStep(step.copy(percentage = newPct))
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                        val pctInt = (step.percentage * 100).roundToInt()
                        Text(
                            text = stringResource(R.string.warmup_ramp_pct_format, pctInt),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(36.dp)
                        )

                        IconButton(
                            onClick = {
                                val currentPctInt = (step.percentage * 100).roundToInt()
                                val newPct = ((currentPctInt + 5).coerceAtMost(95)) / 100.0
                                onUpdateStep(step.copy(percentage = newPct))
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }

                // Reps stepper
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = {
                            val newReps = (step.reps - 1).coerceAtLeast(1)
                            onUpdateStep(step.copy(reps = newReps))
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }

                    Text(
                        text = stringResource(R.string.warmup_ramp_reps_format, step.reps),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.width(44.dp)
                    )

                    IconButton(
                        onClick = {
                            val newReps = (step.reps + 1).coerceAtMost(25)
                            onUpdateStep(step.copy(reps = newReps))
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}
