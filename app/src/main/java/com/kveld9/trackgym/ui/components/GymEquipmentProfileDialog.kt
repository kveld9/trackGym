package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import com.kveld9.trackgym.domain.calculator.PlateCalculator
import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import com.kveld9.trackgym.domain.model.WeightUnit
import java.util.UUID

@Composable
fun GymEquipmentProfilesManageDialog(
    profiles: List<GymEquipmentProfile>,
    activeProfileId: String,
    weightUnit: WeightUnit,
    onSelectProfile: (String) -> Unit,
    onSaveProfile: (GymEquipmentProfile) -> Unit,
    onDeleteProfile: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var editingProfile by remember { mutableStateOf<GymEquipmentProfile?>(null) }
    var showCreateDialog by remember { mutableStateOf(false) }

    if (showCreateDialog) {
        GymEquipmentProfileEditDialog(
            initialProfile = null,
            weightUnit = weightUnit,
            onSave = {
                onSaveProfile(it)
                showCreateDialog = false
            },
            onDismiss = { showCreateDialog = false }
        )
    }

    editingProfile?.let { prof ->
        GymEquipmentProfileEditDialog(
            initialProfile = prof,
            weightUnit = weightUnit,
            onSave = {
                onSaveProfile(it)
                editingProfile = null
            },
            onDismiss = { editingProfile = null }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.gym_profiles_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                IconButton(
                    onClick = { showCreateDialog = true },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.gym_profile_add),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.gym_profiles_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                profiles.forEach { profile ->
                    val isActive = profile.id == activeProfileId
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isActive) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            }
                        ),
                        border = BorderStroke(
                            width = if (isActive) 1.5.dp else 1.dp,
                            color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = profile.name,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    if (profile.isDefault) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = stringResource(R.string.gym_profile_default_badge),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { editingProfile = profile },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = stringResource(R.string.gym_profile_edit),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    if (profiles.size > 1) {
                                        IconButton(
                                            onClick = { onDeleteProfile(profile.id) },
                                            modifier = Modifier.size(36.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = stringResource(R.string.gym_profile_delete),
                                                tint = MaterialTheme.colorScheme.error,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            val barText = "${profile.barWeight(weightUnit)} ${weightUnit.symbol}"
                            val incText = "${profile.minWeightIncrement(weightUnit)} ${weightUnit.symbol}"
                            val platesText = profile.availablePlates(weightUnit).joinToString(", ") {
                                if (it % 1.0 == 0.0) "${it.toInt()}" else "$it"
                            }

                            Text(
                                text = "${stringResource(R.string.gym_profile_bar_weight)}: $barText • Step: $incText",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "${stringResource(R.string.gym_profile_plates)}: $platesText ${weightUnit.symbol}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            if (!isActive) {
                                OutlinedButton(
                                    onClick = { onSelectProfile(profile.id) },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.gym_profile_select),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = stringResource(R.string.gym_profile_active),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_close))
            }
        }
    )
}

@Composable
fun GymEquipmentProfileEditDialog(
    initialProfile: GymEquipmentProfile?,
    weightUnit: WeightUnit,
    onSave: (GymEquipmentProfile) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember {
        mutableStateOf(initialProfile?.name ?: "")
    }

    val defaultBarWeight = initialProfile?.barWeight(weightUnit) ?: 20.0
    var barWeightInput by remember {
        mutableStateOf(if (defaultBarWeight % 1.0 == 0.0) defaultBarWeight.toInt().toString() else defaultBarWeight.toString())
    }

    val defaultIncrement = initialProfile?.minWeightIncrement(weightUnit) ?: 2.5
    var minIncrementInput by remember {
        mutableStateOf(if (defaultIncrement % 1.0 == 0.0) defaultIncrement.toInt().toString() else defaultIncrement.toString())
    }

    var isDefault by remember {
        mutableStateOf(initialProfile?.isDefault ?: false)
    }

    val standardPlates = remember(weightUnit) {
        PlateCalculator.defaultPlates(weightUnit)
    }

    var selectedPlates by remember(weightUnit) {
        val initialPlates = initialProfile?.availablePlates(weightUnit)?.toSet() ?: standardPlates.toSet()
        mutableStateOf(initialPlates)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Text(
                text = if (initialProfile == null) stringResource(R.string.gym_profile_add) else stringResource(R.string.gym_profile_edit),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.gym_profile_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = barWeightInput,
                        onValueChange = { barWeightInput = it },
                        label = { Text("${stringResource(R.string.gym_profile_bar_weight)} (${weightUnit.symbol})", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = minIncrementInput,
                        onValueChange = { minIncrementInput = it },
                        label = { Text("${stringResource(R.string.gym_profile_min_increment)} (${weightUnit.symbol})", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Text(
                    text = stringResource(R.string.gym_profile_plates),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    standardPlates.forEach { plate ->
                        val isSelected = selectedPlates.contains(plate)
                        val label = if (plate % 1.0 == 0.0) "${plate.toInt()}${weightUnit.symbol}" else "$plate${weightUnit.symbol}"
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedPlates = if (isSelected) {
                                    if (selectedPlates.size > 1) selectedPlates - plate else selectedPlates
                                } else {
                                    selectedPlates + plate
                                }
                            },
                            label = { Text(label, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.gym_profile_set_default),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Switch(
                        checked = isDefault,
                        onCheckedChange = { isDefault = it }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val finalName = if (name.isNotBlank()) name.trim() else "Gym Profile"
                    val parsedBar = barWeightInput.replace(',', '.').toDoubleOrNull() ?: 20.0
                    val parsedInc = minIncrementInput.replace(',', '.').toDoubleOrNull() ?: 2.5
                    val barKg = weightUnit.toKg(parsedBar)
                    val incKg = weightUnit.toKg(parsedInc)
                    val platesKg = selectedPlates.sortedDescending().map { weightUnit.toKg(it) }

                    val profile = GymEquipmentProfile(
                        id = initialProfile?.id ?: UUID.randomUUID().toString(),
                        name = finalName,
                        barWeightKg = if (barKg > 0.0) barKg else 20.0,
                        availablePlatesKg = if (platesKg.isNotEmpty()) platesKg else listOf(20.0, 10.0, 5.0, 2.5),
                        minWeightIncrementKg = if (incKg > 0.0) incKg else 2.5,
                        isDefault = isDefault
                    )
                    onSave(profile)
                },
                enabled = name.isNotBlank()
            ) {
                Text(stringResource(R.string.action_save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
