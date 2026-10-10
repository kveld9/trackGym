package com.kveld9.trackgym.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Wc
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.BiologicalSex
import com.kveld9.trackgym.domain.model.DistanceUnit
import com.kveld9.trackgym.domain.model.WeightUnit

@Composable
fun UserProfileCard(
    bodyWeightKg: Double,
    sex: String,
    age: Int,
    onWeightClick: () -> Unit,
    onSexChanged: (String) -> Unit,
    onAgeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsClickableRow(
                icon = Icons.Default.Person,
                title = stringResource(R.string.settings_user_bodyweight_title),
                description = stringResource(R.string.settings_user_bodyweight_desc),
                trailingText = "$bodyWeightKg kg",
                onClick = onWeightClick
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            BiologicalSexRow(
                sex = sex,
                onSexChanged = onSexChanged
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            SettingsClickableRow(
                icon = Icons.Default.CalendarMonth,
                title = stringResource(R.string.settings_user_age_title),
                description = stringResource(R.string.settings_user_age_desc),
                trailingText = stringResource(R.string.strength_user_age_years, age),
                onClick = onAgeClick
            )
        }
    }
}

@Composable
private fun BiologicalSexRow(
    sex: String,
    onSexChanged: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Wc,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.settings_user_sex_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.settings_user_sex_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    val currentSex = if (sex == BiologicalSex.FEMALE.name) BiologicalSex.FEMALE else BiologicalSex.MALE
    val sexEntries = BiologicalSex.entries
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        sexEntries.forEachIndexed { index, s ->
            val isSelected = currentSex == s
            SegmentedButton(
                selected = isSelected,
                onClick = { onSexChanged(s.name) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = sexEntries.size),
                label = {
                    Text(
                        text = stringResource(s.displayNameRes),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
fun UnitsCard(
    weightUnit: String,
    distanceUnit: String,
    onSetWeightUnit: (String) -> Unit,
    onSetDistanceUnit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeightUnitRow(
                weightUnit = weightUnit,
                onSetWeightUnit = onSetWeightUnit
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            DistanceUnitRow(
                distanceUnit = distanceUnit,
                onSetDistanceUnit = onSetDistanceUnit
            )
        }
    }
}

@Composable
private fun WeightUnitRow(
    weightUnit: String,
    onSetWeightUnit: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Scale,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.setting_weight_unit_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.setting_weight_unit_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    val weightOptions = listOf(
        WeightUnit.KG.name to stringResource(R.string.unit_kilograms),
        WeightUnit.LB.name to stringResource(R.string.unit_pounds)
    )

    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        weightOptions.forEachIndexed { index, (unit, label) ->
            val isSelected = weightUnit.equals(unit, ignoreCase = true)
            SegmentedButton(
                selected = isSelected,
                onClick = { onSetWeightUnit(unit) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = weightOptions.size),
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
private fun DistanceUnitRow(
    distanceUnit: String,
    onSetDistanceUnit: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Straighten,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.setting_distance_unit_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.setting_distance_unit_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    val distanceOptions = listOf(
        DistanceUnit.KM.name to stringResource(R.string.unit_kilometers),
        DistanceUnit.MI.name to stringResource(R.string.unit_miles)
    )

    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        distanceOptions.forEachIndexed { index, (unit, label) ->
            val isSelected = distanceUnit.equals(unit, ignoreCase = true)
            SegmentedButton(
                selected = isSelected,
                onClick = { onSetDistanceUnit(unit) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = distanceOptions.size),
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
fun BodyWeightInputDialog(
    initialWeight: Double,
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    var weightText by remember { mutableStateOf(initialWeight.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.settings_user_bodyweight_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.settings_user_bodyweight_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = weightText,
                    onValueChange = { weightText = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val parsed = weightText.toDoubleOrNull() ?: initialWeight
                    onConfirm(parsed)
                },
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(stringResource(android.R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            ) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}

@Composable
fun AgeInputDialog(
    initialAge: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var ageText by remember { mutableStateOf(initialAge.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.settings_user_age_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.settings_user_age_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = ageText,
                    onValueChange = { ageText = it.filter { ch -> ch.isDigit() } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val parsed = ageText.toIntOrNull()?.coerceIn(12, 100) ?: initialAge
                    onConfirm(parsed)
                },
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(stringResource(android.R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            ) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
