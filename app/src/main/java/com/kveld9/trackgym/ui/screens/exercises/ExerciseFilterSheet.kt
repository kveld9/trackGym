package com.kveld9.trackgym.ui.screens.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.CustomExerciseCategory
import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseFilterSheet(
    sheetState: SheetState,
    selectedOrigin: GymViewModel.ExerciseOriginFilter,
    onOriginSelected: (GymViewModel.ExerciseOriginFilter) -> Unit,
    selectedMuscle: MuscleGroup?,
    onMuscleSelected: (MuscleGroup?) -> Unit,
    selectedEquipment: ExerciseCategory?,
    onEquipmentSelected: (ExerciseCategory?) -> Unit,
    selectedMechanics: MechanicsType?,
    onMechanicsSelected: (MechanicsType?) -> Unit,
    selectedForce: ForceType?,
    onForceSelected: (ForceType?) -> Unit,
    selectedDifficulty: DifficultyLevel?,
    onDifficultySelected: (DifficultyLevel?) -> Unit,
    customCategories: List<CustomExerciseCategory>,
    selectedCustomCategory: String?,
    onCustomCategorySelected: (String?) -> Unit,
    onManageCategories: () -> Unit,
    resultCount: Int,
    onClearAll: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = modifier
    ) {
        ExerciseFilterSheetContent(
            selectedOrigin = selectedOrigin,
            onOriginSelected = onOriginSelected,
            selectedMuscle = selectedMuscle,
            onMuscleSelected = onMuscleSelected,
            selectedEquipment = selectedEquipment,
            onEquipmentSelected = onEquipmentSelected,
            selectedMechanics = selectedMechanics,
            onMechanicsSelected = onMechanicsSelected,
            selectedForce = selectedForce,
            onForceSelected = onForceSelected,
            selectedDifficulty = selectedDifficulty,
            onDifficultySelected = onDifficultySelected,
            customCategories = customCategories,
            selectedCustomCategory = selectedCustomCategory,
            onCustomCategorySelected = onCustomCategorySelected,
            onManageCategories = onManageCategories,
            resultCount = resultCount,
            onClearAll = onClearAll,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun ExerciseFilterSheetContent(
    selectedOrigin: GymViewModel.ExerciseOriginFilter,
    onOriginSelected: (GymViewModel.ExerciseOriginFilter) -> Unit,
    selectedMuscle: MuscleGroup?,
    onMuscleSelected: (MuscleGroup?) -> Unit,
    selectedEquipment: ExerciseCategory?,
    onEquipmentSelected: (ExerciseCategory?) -> Unit,
    selectedMechanics: MechanicsType?,
    onMechanicsSelected: (MechanicsType?) -> Unit,
    selectedForce: ForceType?,
    onForceSelected: (ForceType?) -> Unit,
    selectedDifficulty: DifficultyLevel?,
    onDifficultySelected: (DifficultyLevel?) -> Unit,
    customCategories: List<CustomExerciseCategory>,
    selectedCustomCategory: String?,
    onCustomCategorySelected: (String?) -> Unit,
    onManageCategories: () -> Unit,
    resultCount: Int,
    onClearAll: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OriginFilterSection(
                selectedOrigin = selectedOrigin,
                onOriginSelected = onOriginSelected
            )
            MuscleFilterSection(
                selectedMuscle = selectedMuscle,
                onMuscleSelected = onMuscleSelected
            )
            EquipmentFilterSection(
                selectedEquipment = selectedEquipment,
                onEquipmentSelected = onEquipmentSelected
            )
            MechanicsFilterSection(
                selectedMechanics = selectedMechanics,
                onMechanicsSelected = onMechanicsSelected
            )
            ForceFilterSection(
                selectedForce = selectedForce,
                onForceSelected = onForceSelected
            )
            DifficultyFilterSection(
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = onDifficultySelected
            )
            CustomTagsFilterSection(
                customCategories = customCategories,
                selectedCustomCategory = selectedCustomCategory,
                onCustomCategorySelected = onCustomCategorySelected,
                onManageCategories = onManageCategories
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        FilterSheetFooter(
            resultCount = resultCount,
            onClearAll = onClearAll,
            onDismiss = onDismiss
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OriginFilterSection(
    selectedOrigin: GymViewModel.ExerciseOriginFilter,
    onOriginSelected: (GymViewModel.ExerciseOriginFilter) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_exercise_origin))
    val options = remember {
        listOf(
            GymViewModel.ExerciseOriginFilter.ALL to R.string.chip_origin_all,
            GymViewModel.ExerciseOriginFilter.PRELOADED to R.string.chip_origin_preloaded,
            GymViewModel.ExerciseOriginFilter.CUSTOM to R.string.chip_origin_custom
        )
    }
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { index, (originOption, labelRes) ->
            SegmentedButton(
                selected = selectedOrigin == originOption,
                onClick = { onOriginSelected(originOption) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(
                    text = stringResource(labelRes),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MuscleFilterSection(
    selectedMuscle: MuscleGroup?,
    onMuscleSelected: (MuscleGroup?) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_muscle_group))
    val muscles = remember { MuscleGroup.entries.filter { it != MuscleGroup.OTHER } }
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        muscles.forEach { group ->
            val isSelected = selectedMuscle == group
            FilterChip(
                selected = isSelected,
                onClick = { onMuscleSelected(if (isSelected) null else group) },
                label = { Text(stringResource(group.nameRes)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EquipmentFilterSection(
    selectedEquipment: ExerciseCategory?,
    onEquipmentSelected: (ExerciseCategory?) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_equipment_category))
    val categories = remember { ExerciseCategory.entries.filter { it != ExerciseCategory.OTHER } }
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { cat ->
            val isSelected = selectedEquipment == cat
            FilterChip(
                selected = isSelected,
                onClick = { onEquipmentSelected(if (isSelected) null else cat) },
                label = { Text(stringResource(cat.nameRes)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MechanicsFilterSection(
    selectedMechanics: MechanicsType?,
    onMechanicsSelected: (MechanicsType?) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_mechanics_type))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MechanicsType.entries.forEach { mech ->
            val isSelected = selectedMechanics == mech
            FilterChip(
                selected = isSelected,
                onClick = { onMechanicsSelected(if (isSelected) null else mech) },
                label = { Text(stringResource(mech.nameRes)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ForceFilterSection(
    selectedForce: ForceType?,
    onForceSelected: (ForceType?) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_force_vector))
    val forces = remember { ForceType.entries.filter { it != ForceType.OTHER } }
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        forces.forEach { force ->
            val isSelected = selectedForce == force
            FilterChip(
                selected = isSelected,
                onClick = { onForceSelected(if (isSelected) null else force) },
                label = { Text(stringResource(force.nameRes)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DifficultyFilterSection(
    selectedDifficulty: DifficultyLevel?,
    onDifficultySelected: (DifficultyLevel?) -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_difficulty_level))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DifficultyLevel.entries.forEach { level ->
            val isSelected = selectedDifficulty == level
            FilterChip(
                selected = isSelected,
                onClick = { onDifficultySelected(if (isSelected) null else level) },
                label = { Text(stringResource(level.nameRes)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CustomTagsFilterSection(
    customCategories: List<CustomExerciseCategory>,
    selectedCustomCategory: String?,
    onCustomCategorySelected: (String?) -> Unit,
    onManageCategories: () -> Unit
) {
    FilterSectionHeader(title = stringResource(R.string.label_custom_categories))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        customCategories.forEach { category ->
            val isSelected = selectedCustomCategory.equals(category.name, ignoreCase = true)
            FilterChip(
                selected = isSelected,
                onClick = { onCustomCategorySelected(if (isSelected) null else category.name) },
                label = { Text("#${category.name}") }
            )
        }
        AssistChip(
            onClick = onManageCategories,
            label = {
                Text(
                    text = stringResource(R.string.action_manage_custom_categories),
                    fontWeight = FontWeight.SemiBold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Label,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        )
    }
}

@Composable
private fun FilterSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun FilterSheetFooter(
    resultCount: Int,
    onClearAll: () -> Unit,
    onDismiss: () -> Unit
) {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onClearAll,
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(stringResource(R.string.action_clear_filters))
        }
        Button(
            onClick = onDismiss,
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(stringResource(R.string.action_show_results, resultCount))
        }
    }
}
