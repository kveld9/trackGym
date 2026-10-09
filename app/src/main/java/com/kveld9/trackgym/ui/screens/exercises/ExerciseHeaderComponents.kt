package com.kveld9.trackgym.ui.screens.exercises

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

@Composable
fun ExerciseSearchHeader(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    activeFilterCount: Int,
    onOpenFilterSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchTextField(
            searchQuery = searchQuery,
            onSearchChange = onSearchChange,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onOpenFilterSheet,
            modifier = Modifier.size(48.dp)
        ) {
            BadgedBox(
                badge = {
                    if (activeFilterCount > 0) {
                        Badge {
                            Text(text = activeFilterCount.toString())
                        }
                    }
                }
            ) {
                val iconTint = if (activeFilterCount > 0) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = stringResource(R.string.action_filters),
                    tint = iconTint
                )
            }
        }
    }
}

@Composable
private fun SearchTextField(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        placeholder = {
            Text(
                text = stringResource(R.string.search_exercise_placeholder),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = if (searchQuery.isNotEmpty()) {
            {
                IconButton(
                    onClick = { onSearchChange("") },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.action_clear_filters),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else null,
        singleLine = true,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun ActiveFilterStrip(
    activeFilterCount: Int,
    resultCount: Int,
    selectedOrigin: GymViewModel.ExerciseOriginFilter,
    onOriginClear: () -> Unit,
    selectedMuscle: MuscleGroup?,
    onMuscleClear: () -> Unit,
    selectedEquipment: ExerciseCategory?,
    onEquipmentClear: () -> Unit,
    selectedMechanics: MechanicsType?,
    onMechanicsClear: () -> Unit,
    selectedForce: ForceType?,
    onForceClear: () -> Unit,
    selectedDifficulty: DifficultyLevel?,
    onDifficultyClear: () -> Unit,
    selectedCustomCategory: String?,
    onCustomCategoryClear: () -> Unit,
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (activeFilterCount > 0) Modifier.horizontalScroll(rememberScrollState())
                else Modifier
            )
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.exercises_result_count, resultCount),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (activeFilterCount > 0) {
            ActiveFilterChips(
                selectedOrigin = selectedOrigin,
                onOriginClear = onOriginClear,
                selectedMuscle = selectedMuscle,
                onMuscleClear = onMuscleClear,
                selectedEquipment = selectedEquipment,
                onEquipmentClear = onEquipmentClear,
                selectedMechanics = selectedMechanics,
                onMechanicsClear = onMechanicsClear,
                selectedForce = selectedForce,
                onForceClear = onForceClear,
                selectedDifficulty = selectedDifficulty,
                onDifficultyClear = onDifficultyClear,
                selectedCustomCategory = selectedCustomCategory,
                onCustomCategoryClear = onCustomCategoryClear,
                onClearAll = onClearAll
            )
        }
    }
}

@Composable
fun ActiveFilterChips(
    selectedOrigin: GymViewModel.ExerciseOriginFilter,
    onOriginClear: () -> Unit,
    selectedMuscle: MuscleGroup?,
    onMuscleClear: () -> Unit,
    selectedEquipment: ExerciseCategory?,
    onEquipmentClear: () -> Unit,
    selectedMechanics: MechanicsType?,
    onMechanicsClear: () -> Unit,
    selectedForce: ForceType?,
    onForceClear: () -> Unit,
    selectedDifficulty: DifficultyLevel?,
    onDifficultyClear: () -> Unit,
    selectedCustomCategory: String?,
    onCustomCategoryClear: () -> Unit,
    onClearAll: () -> Unit
) {
    if (selectedOrigin != GymViewModel.ExerciseOriginFilter.ALL) {
        val label = stringResource(
            if (selectedOrigin == GymViewModel.ExerciseOriginFilter.PRELOADED) R.string.chip_origin_preloaded
            else R.string.chip_origin_custom
        )
        ActiveFilterChip(label = label, onClear = onOriginClear)
    }
    if (selectedMuscle != null) {
        ActiveFilterChip(label = stringResource(selectedMuscle.nameRes), onClear = onMuscleClear)
    }
    if (selectedEquipment != null) {
        ActiveFilterChip(label = stringResource(selectedEquipment.nameRes), onClear = onEquipmentClear)
    }
    if (selectedMechanics != null) {
        ActiveFilterChip(label = stringResource(selectedMechanics.nameRes), onClear = onMechanicsClear)
    }
    if (selectedForce != null) {
        ActiveFilterChip(label = stringResource(selectedForce.nameRes), onClear = onForceClear)
    }
    if (selectedDifficulty != null) {
        ActiveFilterChip(label = stringResource(selectedDifficulty.nameRes), onClear = onDifficultyClear)
    }
    if (selectedCustomCategory != null) {
        ActiveFilterChip(label = "#$selectedCustomCategory", onClear = onCustomCategoryClear)
    }

    InputChip(
        selected = false,
        onClick = onClearAll,
        label = { Text(stringResource(R.string.action_clear_filters), style = MaterialTheme.typography.labelSmall) },
        colors = InputChipDefaults.inputChipColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f),
            labelColor = MaterialTheme.colorScheme.error
        ),
        border = null,
        modifier = Modifier.heightIn(min = 48.dp)
    )
}

@Composable
fun ActiveFilterChip(
    label: String,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    InputChip(
        selected = true,
        onClick = onClear,
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        modifier = modifier.heightIn(min = 48.dp)
    )
}

@Composable
fun ExerciseEmptyState(
    hasFiltersOrSearch: Boolean,
    onCreateExercise: () -> Unit,
    onClearFilters: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(
                    if (hasFiltersOrSearch) R.string.exercises_no_results_title
                    else R.string.exercises_empty_title
                ),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = if (hasFiltersOrSearch) onClearFilters else onCreateExercise,
                modifier = Modifier.heightIn(min = 48.dp)
            ) {
                Text(
                    text = stringResource(
                        if (hasFiltersOrSearch) R.string.exercises_no_results_cta
                        else R.string.exercises_empty_cta
                    )
                )
            }
        }
    }
}
