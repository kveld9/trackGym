package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.RpeTargetWeightCalculator
import com.kveld9.trackgym.domain.model.RpeScale
import com.kveld9.trackgym.domain.model.WeightUnit

@Composable
fun RpeTargetWeightDialog(
    exerciseName: String,
    initial1RmKg: Double,
    initialReps: Int = 5,
    initialRpe: Double = 8.0,
    weightUnit: WeightUnit = WeightUnit.KG,
    onApplyTargetWeight: (Double, Int, Double) -> Unit,
    onDismiss: () -> Unit
) {
    val initialDisplay1Rm = if (initial1RmKg > 0.0) weightUnit.formatValue(initial1RmKg) else ""
    var oneRmInput by remember { mutableStateOf(initialDisplay1Rm) }
    var targetReps by remember { mutableIntStateOf(if (initialReps > 0) initialReps else 5) }
    var targetRpe by remember { mutableDoubleStateOf(initialRpe.coerceIn(6.0, 10.0)) }

    val parsed1Rm = oneRmInput.replace(',', '.').toDoubleOrNull() ?: 0.0
    val parsed1RmKg = weightUnit.toKg(parsed1Rm)

    val stepInUnit = if (weightUnit == WeightUnit.LB) 5.0 else 2.5
    val stepKg = weightUnit.toKg(stepInUnit)

    val result = remember(parsed1RmKg, targetReps, targetRpe, stepKg) {
        RpeTargetWeightCalculator.calculateTargetWeight(
            estimated1RmKg = parsed1RmKg,
            targetReps = targetReps,
            targetRpe = targetRpe,
            roundingStepKg = stepKg
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.padding(24.dp).fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Column {
                Text(
                    text = stringResource(R.string.rpe_calc_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = exerciseName,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Estimated 1RM input
                Column {
                    Text(
                        text = stringResource(R.string.rpe_calc_exercise_1rm_label, weightUnit.symbol.uppercase()),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = oneRmInput,
                        onValueChange = { oneRmInput = it },
                        singleLine = true,
                        placeholder = { Text("0.0") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }

                // Target Reps Stepper
                Column {
                    Text(
                        text = stringResource(R.string.rpe_calc_target_reps_label),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { if (targetReps > 1) targetReps-- },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(Icons.Default.Remove, contentDescription = "-1 Rep", tint = MaterialTheme.colorScheme.primary)
                        }
                        Text(
                            text = "$targetReps reps",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        IconButton(
                            onClick = { if (targetReps < 20) targetReps++ },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "+1 Rep", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                // Target RPE Selector Chips
                Column {
                    Text(
                        text = stringResource(R.string.rpe_calc_target_rpe_label),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(RpeScale.options) { option ->
                            val rpeVal = option.rpe ?: return@items
                            val isSelected = kotlin.math.abs(targetRpe - rpeVal) < 0.01
                            val rpeLabel = RpeScale.formatRpe(rpeVal) ?: ""
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
                                modifier = Modifier.clickable { targetRpe = rpeVal }
                            ) {
                                Text(
                                    text = "@$rpeLabel",
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }

                // Calculation Result Card
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.rpe_calc_pct_1rm_format, result.percentage * 100.0),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            val rir = (10.0 - targetRpe).coerceAtLeast(0.0)
                            val rirStr = if (rir % 1.0 == 0.0) "${rir.toInt()} RIR" else "$rir RIR"
                            Text(
                                text = rirStr,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val displaySuggested = weightUnit.format(result.suggestedWeightKg)
                        val displayTheoretical = weightUnit.format(result.theoreticalWeightKg)

                        Text(
                            text = stringResource(R.string.rpe_calc_suggested_load, displaySuggested),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(R.string.rpe_calc_exact_load, displayTheoretical),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onApplyTargetWeight(result.suggestedWeightKg, targetReps, targetRpe)
                    onDismiss()
                },
                enabled = result.suggestedWeightKg > 0.0,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.rpe_calc_action_apply),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.action_cancel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}
