package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.PlateCalculator
import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import com.kveld9.trackgym.domain.model.WeightUnit
import java.util.UUID

private const val MANAGE_SHEET_MAX_HEIGHT_FRACTION = 0.9f

@OptIn(ExperimentalMaterial3Api::class)
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

    if (showCreateDialog || editingProfile != null) {
        GymEquipmentProfileEditDialog(
            initialProfile = editingProfile,
            weightUnit = weightUnit,
            onSave = {
                onSaveProfile(it)
                showCreateDialog = false
                editingProfile = null
            },
            onDismiss = {
                showCreateDialog = false
                editingProfile = null
            }
        )
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.fillMaxHeight(MANAGE_SHEET_MAX_HEIGHT_FRACTION)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            GymProfilesSheetHeader(onAddClick = { showCreateDialog = true })
            Spacer(modifier = Modifier.height(8.dp))
            GymProfilesList(
                profiles = profiles,
                activeProfileId = activeProfileId,
                weightUnit = weightUnit,
                onSelectProfile = onSelectProfile,
                onEditProfile = { editingProfile = it },
                onDeleteProfile = onDeleteProfile
            )
        }
    }
}

@Composable
private fun GymProfilesSheetHeader(onAddClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.HomeRepairService,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.gym_profiles_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        IconButton(
            onClick = onAddClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.gym_profile_add),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    Text(
        text = stringResource(R.string.gym_profiles_desc),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

@Composable
private fun GymProfilesList(
    profiles: List<GymEquipmentProfile>,
    activeProfileId: String,
    weightUnit: WeightUnit,
    onSelectProfile: (String) -> Unit,
    onEditProfile: (GymEquipmentProfile) -> Unit,
    onDeleteProfile: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        profiles.forEach { profile ->
            GymProfileCard(
                profile = profile,
                isActive = profile.id == activeProfileId,
                weightUnit = weightUnit,
                canDelete = profiles.size > 1,
                onSelectProfile = onSelectProfile,
                onEditProfile = { onEditProfile(profile) },
                onDeleteProfile = { onDeleteProfile(profile.id) }
            )
        }
    }
}

@Composable
private fun GymProfileActions(
    canDelete: Boolean,
    onEditProfile: () -> Unit,
    onDeleteProfile: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = onEditProfile,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.gym_profile_edit),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }

        if (canDelete) {
            IconButton(
                onClick = onDeleteProfile,
                modifier = Modifier.size(48.dp)
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

@Composable
private fun GymProfileHeader(
    profile: GymEquipmentProfile,
    isActive: Boolean,
    canDelete: Boolean,
    onEditProfile: () -> Unit,
    onDeleteProfile: () -> Unit
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
                color = if (isActive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
            if (profile.isDefault) {
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = stringResource(R.string.gym_profile_default_badge),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        softWrap = false,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }
        }

        GymProfileActions(
            canDelete = canDelete,
            onEditProfile = onEditProfile,
            onDeleteProfile = onDeleteProfile
        )
    }
}

@Composable
private fun GymProfileSpecs(
    profile: GymEquipmentProfile,
    weightUnit: WeightUnit
) {
    val barText = "${profile.barWeight(weightUnit)} ${weightUnit.symbol}"
    val incText = "${profile.minWeightIncrement(weightUnit)} ${weightUnit.symbol}"
    val platesText = profile.availablePlates(weightUnit).joinToString(", ") {
        if (it % 1.0 == 0.0) "${it.toInt()}" else "$it"
    }

    Text(
        text = "${stringResource(R.string.gym_profile_bar_weight)}: $barText • " +
            "${stringResource(R.string.gym_profile_step_label)}: $incText",
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Text(
        text = "${stringResource(R.string.gym_profile_plates)}: $platesText ${weightUnit.symbol}",
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun GymProfileFooter(
    isActive: Boolean,
    profileId: String,
    onSelectProfile: (String) -> Unit
) {
    if (!isActive) {
        OutlinedButton(
            onClick = { onSelectProfile(profileId) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
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

@Composable
private fun GymProfileCard(
    profile: GymEquipmentProfile,
    isActive: Boolean,
    weightUnit: WeightUnit,
    canDelete: Boolean,
    onSelectProfile: (String) -> Unit,
    onEditProfile: () -> Unit,
    onDeleteProfile: () -> Unit
) {
    val borderColor = if (isActive) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
    }
    val containerColor = if (isActive) {
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
    } else {
        MaterialTheme.colorScheme.surfaceContainerHigh
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(width = if (isActive) 1.5.dp else 1.dp, color = borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            GymProfileHeader(profile, isActive, canDelete, onEditProfile, onDeleteProfile)
            Spacer(modifier = Modifier.height(4.dp))
            GymProfileSpecs(profile, weightUnit)
            Spacer(modifier = Modifier.height(8.dp))
            GymProfileFooter(isActive, profile.id, onSelectProfile)
        }
    }
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
        mutableStateOf(formatNumericInput(defaultBarWeight))
    }

    val defaultIncrement = initialProfile?.minWeightIncrement(weightUnit) ?: 2.5
    var minIncrementInput by remember {
        mutableStateOf(formatNumericInput(defaultIncrement))
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
            val titleRes = if (initialProfile == null) {
                R.string.gym_profile_add
            } else {
                R.string.gym_profile_edit
            }
            Text(
                text = stringResource(titleRes),
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
                        label = {
                            Text(
                                text = "${stringResource(R.string.gym_profile_bar_weight)} (${weightUnit.symbol})",
                                fontSize = 11.sp
                            )
                        },
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
                        label = {
                            Text(
                                text = "${stringResource(R.string.gym_profile_min_increment)} (${weightUnit.symbol})",
                                fontSize = 11.sp
                            )
                        },
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

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    standardPlates.forEach { plate ->
                        val isSelected = selectedPlates.contains(plate)
                        val label = if (plate % 1.0 == 0.0) {
                            "${plate.toInt()}${weightUnit.symbol}"
                        } else {
                            "$plate${weightUnit.symbol}"
                        }
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
                modifier = Modifier.defaultMinSize(minHeight = 48.dp),
                enabled = name.isNotBlank()
            ) {
                Text(stringResource(R.string.action_save))
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

private fun formatNumericInput(value: Double): String =
    if (value % 1.0 == 0.0) value.toInt().toString() else value.toString()

