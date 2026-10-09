package com.kveld9.trackgym.ui.screens.active

import java.util.Locale
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.WeightUnit
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import com.kveld9.trackgym.ui.components.GymEquipmentProfilesManageDialog
import com.kveld9.trackgym.ui.theme.GymBlue
import com.kveld9.trackgym.ui.theme.GymWarmupAmber

@Composable
fun PlateCalculatorDialog(
    exerciseName: String,
    weightUnit: WeightUnit,
    initialWeightKg: Double,
    activeProfile: GymEquipmentProfile = GymEquipmentProfile.defaultProfiles().first(),
    allProfiles: List<GymEquipmentProfile> = emptyList(),
    onSelectProfile: (String) -> Unit = {},
    onSaveProfile: (GymEquipmentProfile) -> Unit = {},
    onDeleteProfile: (String) -> Unit = {},
    onDismiss: () -> Unit
) {
    var showManageProfilesDialog by remember { mutableStateOf(false) }

    if (showManageProfilesDialog) {
        GymEquipmentProfilesManageDialog(
            profiles = if (allProfiles.isNotEmpty()) allProfiles else GymEquipmentProfile.defaultProfiles(),
            activeProfileId = activeProfile.id,
            weightUnit = weightUnit,
            onSelectProfile = { id ->
                onSelectProfile(id)
                showManageProfilesDialog = false
            },
            onSaveProfile = onSaveProfile,
            onDeleteProfile = onDeleteProfile,
            onDismiss = { showManageProfilesDialog = false }
        )
    }

    val initialDisplay = weightUnit.formatValue(initialWeightKg)
    var targetWeightInput by remember { mutableStateOf(initialDisplay) }
    val defaultBar = activeProfile.barWeight(weightUnit)
    var barWeightInput by remember(activeProfile) {
        mutableStateOf(if (defaultBar % 1.0 == 0.0) defaultBar.toInt().toString() else defaultBar.toString())
    }

    var includeCollars by remember { mutableStateOf(false) }
    val defaultCollars = remember(weightUnit) { com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultCollarsWeight(weightUnit) }

    val allDefaultPlates = remember(weightUnit, activeProfile) {
        activeProfile.availablePlates(weightUnit)
    }
    var activePlates by remember(weightUnit, activeProfile) { mutableStateOf(allDefaultPlates.toSet()) }

    val currentTarget = targetWeightInput.replace(',', '.').toDoubleOrNull() ?: 0.0
    val currentBar = barWeightInput.replace(',', '.').toDoubleOrNull() ?: defaultBar
    val collarsWeight = if (includeCollars) defaultCollars else 0.0

    val calcResult = remember(currentTarget, currentBar, collarsWeight, activePlates) {
        com.kveld9.trackgym.domain.calculator.PlateCalculator.calculatePlates(
            targetWeight = currentTarget,
            barWeight = currentBar,
            collarsWeight = collarsWeight,
            availablePlates = activePlates.toList()
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = false),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Column {
                Text(
                    text = stringResource(R.string.plate_calc_title),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Gym Equipment Profiles Selector
                if (allProfiles.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.gym_profiles_title),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(
                            onClick = { showManageProfilesDialog = true },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(R.string.gym_profile_edit),
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        allProfiles.forEach { profile ->
                            val isSelected = profile.id == activeProfile.id
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    onSelectProfile(profile.id)
                                    val newBar = profile.barWeight(weightUnit)
                                    barWeightInput = if (newBar % 1.0 == 0.0) newBar.toInt().toString() else newBar.toString()
                                    activePlates = profile.availablePlates(weightUnit).toSet()
                                },
                                label = { Text(profile.name, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }
                }
                // Target and Bar Inputs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = targetWeightInput,
                        onValueChange = { targetWeightInput = it },
                        label = { Text("${stringResource(R.string.plate_calc_target_weight)} (${weightUnit.symbol})", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = barWeightInput,
                        onValueChange = { barWeightInput = it },
                        label = { Text("${stringResource(R.string.plate_calc_bar_weight)} (${weightUnit.symbol})", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                // Bar Profiles & Collars Selection
                Text(
                    text = stringResource(R.string.plate_calc_bar_profiles),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    com.kveld9.trackgym.domain.calculator.BarbellProfile.entries.forEach { profile ->
                        val pWeight = profile.weight(weightUnit)
                        val pWeightStr = if (pWeight % 1.0 == 0.0) "${pWeight.toInt()}${weightUnit.symbol}" else "$pWeight${weightUnit.symbol}"
                        val isBarSelected = currentBar == pWeight
                        FilterChip(
                            selected = isBarSelected,
                            onClick = {
                                barWeightInput = if (pWeight % 1.0 == 0.0) pWeight.toInt().toString() else pWeight.toString()
                            },
                            label = { Text(stringResource(profile.nameRes, pWeightStr), fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }

                    // Collar clips chip
                    val collarStr = if (defaultCollars % 1.0 == 0.0) "${defaultCollars.toInt()}${weightUnit.symbol}" else "$defaultCollars${weightUnit.symbol}"
                    FilterChip(
                        selected = includeCollars,
                        onClick = { includeCollars = !includeCollars },
                        label = { Text(stringResource(R.string.plate_calc_collars_toggle, collarStr), fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }

                // Available Plates Selection
                Text(
                    text = stringResource(R.string.plate_calc_available_plates),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    allDefaultPlates.forEach { plate ->
                        val isEnabled = activePlates.contains(plate)
                        val label = if (plate % 1.0 == 0.0) "${plate.toInt()}${weightUnit.symbol}" else "$plate${weightUnit.symbol}"
                        FilterChip(
                            selected = isEnabled,
                            onClick = {
                                activePlates = if (isEnabled) {
                                    if (activePlates.size > 1) activePlates - plate else activePlates
                                } else {
                                    activePlates + plate
                                }
                            },
                            label = { Text(label, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                                selectedLabelColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                // Summary banner
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.plate_calc_each_side),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            val weightPerSideText = if (calcResult.weightPerSide % 1.0 == 0.0) {
                                "${calcResult.weightPerSide.toInt()} ${weightUnit.symbol}"
                            } else {
                                String.format(java.util.Locale.US, "%.2f %s", calcResult.weightPerSide, weightUnit.symbol)
                            }
                            Text(
                                text = weightPerSideText,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        if (calcResult.remainderPerSide > 0.0) {
                            val remText = String.format(java.util.Locale.US, "%.2f %s", calcResult.remainderPerSide, weightUnit.symbol)
                            Text(
                                text = stringResource(R.string.plate_calc_remainder, remText),
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                if (calcResult.platesPerSide.isEmpty()) {
                    val emptyMsg = if (currentTarget <= currentBar) {
                        stringResource(R.string.plate_calc_under_bar)
                    } else {
                        stringResource(R.string.plate_calc_no_plates)
                    }
                    Text(
                        text = emptyMsg,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        calcResult.platesPerSide.forEach { item ->
                            val plateLabel = if (item.weight % 1.0 == 0.0) "${item.weight.toInt()} ${weightUnit.symbol}" else "${item.weight} ${weightUnit.symbol}"
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(
                                            modifier = Modifier.size(12.dp),
                                            shape = CircleShape,
                                            color = MaterialTheme.colorScheme.primary
                                        ) {}
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = plateLabel,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
                                    ) {
                                        Text(
                                            text = "${item.count}x",
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.action_close),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Composable
fun ExerciseRestDurationDialog(
    exerciseName: String,
    currentRestSeconds: Int?,
    defaultGlobalSeconds: Int,
    onSaveRest: (Int?) -> Unit,
    onDismiss: () -> Unit
) {
    var textInput by remember { mutableStateOf(currentRestSeconds?.toString().orEmpty()) }
    val quickDurations = listOf(30, 60, 90, 120, 180, 240)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.dialog_set_exercise_rest_title),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = stringResource(R.string.dialog_set_exercise_rest_desc, exerciseName),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = textInput,
                    onValueChange = { input ->
                        if (input.isEmpty() || input.all { it.isDigit() }) {
                            textInput = input
                        }
                    },
                    label = { Text(stringResource(R.string.exercise_rest_custom_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    quickDurations.forEach { seconds ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (textInput == seconds.toString()) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            },
                            modifier = Modifier
                                .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                                .clickable { textInput = seconds.toString() }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "${seconds}s",
                                    color = if (textInput == seconds.toString()) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val parsed = textInput.toIntOrNull()
                    onSaveRest(if (parsed != null && parsed > 0) parsed else null)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(stringResource(R.string.action_save), color = MaterialTheme.colorScheme.onPrimary)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onSaveRest(null) }
            ) {
                Text(
                    text = stringResource(R.string.exercise_rest_use_global, defaultGlobalSeconds),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Composable
fun SupersetGroupDialog(
    exerciseName: String,
    currentGroup: String?,
    onSelectGroup: (String?) -> Unit,
    onDismiss: () -> Unit
) {
    val groups = listOf(
        "A" to (stringResource(R.string.superset_tag_a) to MaterialTheme.colorScheme.primary),
        "B" to (stringResource(R.string.superset_tag_b) to GymBlue),
        "C" to (stringResource(R.string.superset_tag_c) to GymWarmupAmber),
        "D" to (stringResource(R.string.superset_tag_d) to MaterialTheme.colorScheme.tertiary)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.menu_group_superset),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = exerciseName,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                groups.forEach { (groupId, pair) ->
                    val (label, color) = pair
                    val isSelected = currentGroup == groupId
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) color.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isSelected) BorderStroke(1.5.dp, color) else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectGroup(groupId)
                                onDismiss()
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(color, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = label,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                if (currentGroup != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    TextButton(
                        onClick = {
                            onSelectGroup(null)
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.menu_ungroup_superset),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
