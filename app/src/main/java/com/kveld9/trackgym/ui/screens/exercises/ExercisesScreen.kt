package com.kveld9.trackgym.ui.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import com.kveld9.trackgym.domain.model.CustomExerciseCategory
import com.kveld9.trackgym.domain.calculator.ExerciseSubstitutionEngine
import com.kveld9.trackgym.ui.components.ExerciseHistoryDialog
import com.kveld9.trackgym.ui.components.ExerciseTechniqueDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.window.DialogProperties
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.BiomechanicalClassifier
import com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry
import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.domain.model.MuscleInvolvement
import com.kveld9.trackgym.ui.util.displayName
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(
    viewModel: GymViewModel,
    modifier: Modifier = Modifier
) {
    val exercises by viewModel.filteredExercises.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedFilter by viewModel.selectedMuscleFilter.collectAsStateWithLifecycle()
    val selectedOriginFilter by viewModel.selectedOriginFilter.collectAsStateWithLifecycle()
    val selectedEquipmentFilter by viewModel.selectedEquipmentFilter.collectAsStateWithLifecycle()
    val selectedMechanicsFilter by viewModel.selectedMechanicsFilter.collectAsStateWithLifecycle()
    val selectedForceFilter by viewModel.selectedForceFilter.collectAsStateWithLifecycle()
    val selectedDifficultyFilter by viewModel.selectedDifficultyFilter.collectAsStateWithLifecycle()
    val allCustomCategories by viewModel.allCustomCategories.collectAsStateWithLifecycle()
    val selectedCustomCategoryFilter by viewModel.selectedCustomCategoryFilter.collectAsStateWithLifecycle()
    val allExercises by viewModel.allExercises.collectAsStateWithLifecycle()
    val completedWorkouts by viewModel.completedWorkouts.collectAsStateWithLifecycle()
    val weightUnit by viewModel.weightUnit.collectAsStateWithLifecycle()
    val userBodyWeight by viewModel.userBodyWeight.collectAsStateWithLifecycle()

    var showCreateDialog by remember { mutableStateOf(false) }
    var selectedExerciseForHistory by remember { mutableStateOf<Exercise?>(null) }
    var showManageCategoriesDialog by remember { mutableStateOf(false) }
    var exerciseForTagging by remember { mutableStateOf<Exercise?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.exercises_title),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.action_create_exercise))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Box
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                placeholder = { Text(stringResource(R.string.search_exercise_placeholder), color = MaterialTheme.colorScheme.onSurfaceVariant) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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

            Spacer(modifier = Modifier.height(10.dp))

            // Origin Filter (All / Preloaded / Manual)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val originFilters = listOf(
                    GymViewModel.ExerciseOriginFilter.ALL to R.string.chip_origin_all,
                    GymViewModel.ExerciseOriginFilter.PRELOADED to R.string.chip_origin_preloaded,
                    GymViewModel.ExerciseOriginFilter.CUSTOM to R.string.chip_origin_custom
                )
                originFilters.forEach { (filterType, labelRes) ->
                    val isSelected = selectedOriginFilter == filterType
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                                else MaterialTheme.colorScheme.surfaceContainerHigh
                            )
                            .border(
                                width = 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable { viewModel.setOriginFilter(filterType) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = stringResource(labelRes),
                            color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Muscle Group Horizontal Scroll
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_muscles),
                    isSelected = selectedFilter == null,
                    onClick = { viewModel.setMuscleFilter(null) }
                )
                MuscleGroup.entries.filter { it != MuscleGroup.OTHER }.forEach { group ->
                    MuscleChip(
                        label = stringResource(group.nameRes),
                        isSelected = selectedFilter == group,
                        onClick = { viewModel.setMuscleFilter(group) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Equipment Category Horizontal Scroll
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_equipment),
                    isSelected = selectedEquipmentFilter == null,
                    onClick = { viewModel.setEquipmentFilter(null) }
                )
                ExerciseCategory.entries.filter { it != ExerciseCategory.OTHER }.forEach { cat ->
                    MuscleChip(
                        label = stringResource(cat.nameRes),
                        isSelected = selectedEquipmentFilter == cat,
                        onClick = { viewModel.setEquipmentFilter(cat) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mechanics (Compound / Isolation) & Clear Filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_mechanics),
                    isSelected = selectedMechanicsFilter == null,
                    onClick = { viewModel.setMechanicsFilter(null) }
                )
                MechanicsType.entries.forEach { mech ->
                    MuscleChip(
                        label = stringResource(mech.nameRes),
                        isSelected = selectedMechanicsFilter == mech,
                        onClick = { viewModel.setMechanicsFilter(mech) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Force Vector (Push / Pull / Static)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_force),
                    isSelected = selectedForceFilter == null,
                    onClick = { viewModel.setForceFilter(null) }
                )
                ForceType.entries.filter { it != ForceType.OTHER }.forEach { force ->
                    MuscleChip(
                        label = stringResource(force.nameRes),
                        isSelected = selectedForceFilter == force,
                        onClick = { viewModel.setForceFilter(force) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Difficulty Level (Beginner / Intermediate / Expert) & Clear Filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_difficulty),
                    isSelected = selectedDifficultyFilter == null,
                    onClick = { viewModel.setDifficultyFilter(null) }
                )
                DifficultyLevel.entries.forEach { level ->
                    MuscleChip(
                        label = stringResource(level.nameRes),
                        isSelected = selectedDifficultyFilter == level,
                        onClick = { viewModel.setDifficultyFilter(level) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // User-Defined Categories & Tags Filter Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MuscleChip(
                    label = stringResource(R.string.chip_all_custom_categories),
                    isSelected = selectedCustomCategoryFilter == null,
                    onClick = { viewModel.setCustomCategoryFilter(null) }
                )
                allCustomCategories.forEach { category ->
                    MuscleChip(
                        label = category.name,
                        isSelected = selectedCustomCategoryFilter.equals(category.name, ignoreCase = true),
                        onClick = {
                            if (selectedCustomCategoryFilter.equals(category.name, ignoreCase = true)) {
                                viewModel.setCustomCategoryFilter(null)
                            } else {
                                viewModel.setCustomCategoryFilter(category.name)
                            }
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .clickable { showManageCategoriesDialog = true }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Label,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = stringResource(R.string.action_manage_custom_categories),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                val hasActiveFilters = selectedFilter != null ||
                    selectedOriginFilter != GymViewModel.ExerciseOriginFilter.ALL ||
                    selectedEquipmentFilter != null ||
                    selectedMechanicsFilter != null ||
                    selectedForceFilter != null ||
                    selectedDifficultyFilter != null ||
                    selectedCustomCategoryFilter != null ||
                    searchQuery.isNotBlank()

                if (hasActiveFilters) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f))
                            .clickable { viewModel.clearAllExerciseFilters() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.action_clear_filters),
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Exercise List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(exercises, key = { it.id }) { exercise ->
                    ExerciseRowCard(
                        exercise = exercise,
                        allExercises = allExercises,
                        onOpenHistory = { selectedExerciseForHistory = exercise },
                        onEditTags = { exerciseForTagging = exercise }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

    if (showCreateDialog) {
        CreateExerciseDialog(
            allCategories = allCustomCategories,
            onDismiss = { showCreateDialog = false },
            onCreate = { name, group, category, notes, primaryMuscle, secondaryMuscles, mechanics, force, level, customCategories ->
                viewModel.createCustomExercise(name, group, category, notes, primaryMuscle, secondaryMuscles, mechanics, force, level, customCategories)
                showCreateDialog = false
            }
        )
    }

    if (showManageCategoriesDialog) {
        ManageCategoriesDialog(
            categories = allCustomCategories,
            onDismiss = { showManageCategoriesDialog = false },
            onCreate = { viewModel.createCustomCategory(it) },
            onRename = { cat, newName -> viewModel.renameCustomCategory(cat, newName) },
            onDelete = { viewModel.deleteCustomCategory(it) }
        )
    }

    exerciseForTagging?.let { targetExercise ->
        EditExerciseTagsDialog(
            exercise = targetExercise,
            allCategories = allCustomCategories,
            onDismiss = { exerciseForTagging = null },
            onSave = { updatedTags ->
                viewModel.updateExerciseCustomCategories(targetExercise.id, updatedTags)
                exerciseForTagging = null
            }
        )
    }

    selectedExerciseForHistory?.let { exercise ->
        ExerciseHistoryDialog(
            exercise = exercise,
            completedWorkouts = completedWorkouts,
            weightUnit = weightUnit,
            userBodyWeightKg = userBodyWeight,
            onDismiss = { selectedExerciseForHistory = null }
        )
    }
}

@Composable
fun MuscleChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ExerciseRowCard(
    exercise: Exercise,
    allExercises: List<Exercise> = emptyList(),
    onOpenHistory: () -> Unit = {},
    onEditTags: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var showAlternatives by remember { mutableStateOf(false) }
    var showTechniqueDialog by remember { mutableStateOf(false) }
    val involvements = remember(exercise) { MuscleAnatomyRegistry.getInvolvementsForExercise(exercise) }
    val primary = remember(involvements) { involvements.firstOrNull { it.isPrimary } ?: involvements.firstOrNull() }
    val secondaries = remember(involvements) { involvements.filter { !it.isPrimary } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .clickable { expanded = !expanded }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.displayName(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "${stringResource(exercise.muscleGroup.nameRes)} • ${stringResource(exercise.category.nameRes)} • ${stringResource(exercise.mechanics.nameRes)} • ${stringResource(exercise.force.nameRes)} • ${stringResource(exercise.level.nameRes)}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp
                )
            }

            if (exercise.isCustom) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.badge_manual),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.badge_preloaded),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Muscle Involvement Chips Summary
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (primary != null) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.badge_primary_muscle, stringResource(primary.muscle.nameRes), primary.percentage),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            secondaries.take(2).forEach { sec ->
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.badge_secondary_muscle, stringResource(sec.muscle.nameRes), sec.percentage),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            if (secondaries.size > 2) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "+${secondaries.size - 2}",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }

        if (exercise.customCategories.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                exercise.customCategories.forEach { tag ->
                    Surface(
                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "#$tag",
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }

        // Expanded view showing detailed anatomical breakdown with percentage bars
        if (expanded) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.label_anatomy_breakdown),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))

            involvements.forEach { inv ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(0.45f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(if (inv.isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                        )
                        Text(
                            text = stringResource(inv.muscle.nameRes),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            fontWeight = if (inv.isPrimary) FontWeight.Bold else FontWeight.Normal
                        )
                    }

                    // Visual Progress Bar
                    Box(
                        modifier = Modifier
                            .weight(0.40f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(inv.percentage / 100f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(if (inv.isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                        )
                    }

                    Text(
                        text = "${inv.percentage}%",
                        color = if (inv.isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.End,
                        modifier = Modifier.weight(0.15f)
                    )
                }
            }

            // Alternative Exercises Section
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { showAlternatives = !showAlternatives }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.title_substitutes),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = if (showAlternatives) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }

            if (showAlternatives) {
                val substitutes = remember(exercise, allExercises) {
                    ExerciseSubstitutionEngine.findSubstitutes(
                        target = exercise,
                        allExercises = allExercises,
                        limit = 4
                    )
                }

                if (substitutes.isEmpty()) {
                    Text(
                        text = stringResource(R.string.substitute_no_alternatives),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        substitutes.forEach { sub ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = sub.exercise.displayName(),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "${stringResource(sub.exercise.category.nameRes)} • ${stringResource(sub.exercise.mechanics.nameRes)}",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 10.sp
                                    )
                                }
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "${sub.matchScore}%",
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Technique & Execution Guide Button
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { showTechniqueDialog = true },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.action_technique_guide),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                OutlinedButton(
                    onClick = onOpenHistory,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.action_exercise_history),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onEditTags,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Label,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.action_edit_exercise_tags),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    if (showTechniqueDialog) {
        ExerciseTechniqueDialog(
            exercise = exercise,
            onDismiss = { showTechniqueDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExerciseDialog(
    allCategories: List<CustomExerciseCategory> = emptyList(),
    onDismiss: () -> Unit,
    onCreate: (String, MuscleGroup, ExerciseCategory, String, BodyMuscle?, List<MuscleInvolvement>, MechanicsType, ForceType, DifficultyLevel, List<String>) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedGroup by remember { mutableStateOf(MuscleGroup.CHEST) }
    var selectedCategory by remember { mutableStateOf(ExerciseCategory.BARBELL) }
    var selectedMechanics by remember { mutableStateOf(MechanicsType.COMPOUND) }
    var selectedForce by remember { mutableStateOf(ForceType.PUSH) }
    var selectedLevel by remember { mutableStateOf(DifficultyLevel.BEGINNER) }
    var userModifiedForce by remember { mutableStateOf(false) }
    var userModifiedLevel by remember { mutableStateOf(false) }
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var newTagText by remember { mutableStateOf("") }
    var primaryMuscle by remember(selectedGroup) {
        mutableStateOf(BodyMuscle.entries.firstOrNull { it.muscleGroup == selectedGroup } ?: BodyMuscle.CHEST)
    }
    var notes by remember { mutableStateOf("") }

    var groupExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var mechanicsExpanded by remember { mutableStateOf(false) }
    var forceExpanded by remember { mutableStateOf(false) }
    var levelExpanded by remember { mutableStateOf(false) }
    var primaryExpanded by remember { mutableStateOf(false) }

    androidx.compose.runtime.LaunchedEffect(name, selectedGroup, selectedCategory, selectedMechanics) {
        if (!userModifiedForce) {
            selectedForce = BiomechanicalClassifier.classifyForce(name, selectedCategory, selectedGroup)
        }
        if (!userModifiedLevel) {
            selectedLevel = BiomechanicalClassifier.classifyDifficulty(name, selectedCategory, selectedGroup, selectedMechanics)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = false),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Text(stringResource(R.string.dialog_create_exercise_title), color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.label_exercise_name)) },
                    placeholder = { Text(stringResource(R.string.placeholder_exercise_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                // Muscle Group Dropdown
                ExposedDropdownMenuBox(
                    expanded = groupExpanded,
                    onExpandedChange = { groupExpanded = !groupExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedGroup.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_muscle_group)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = groupExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = groupExpanded,
                        onDismissRequest = { groupExpanded = false }
                    ) {
                        MuscleGroup.entries.forEach { group ->
                            DropdownMenuItem(
                                text = { Text(stringResource(group.nameRes)) },
                                onClick = {
                                    selectedGroup = group
                                    groupExpanded = false
                                }
                            )
                        }
                    }
                }

                // Primary Target Muscle Dropdown
                val candidateMuscles = remember(selectedGroup) {
                    val matching = BodyMuscle.entries.filter { it.muscleGroup == selectedGroup }
                    if (matching.isNotEmpty()) matching else BodyMuscle.entries
                }
                ExposedDropdownMenuBox(
                    expanded = primaryExpanded,
                    onExpandedChange = { primaryExpanded = !primaryExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(primaryMuscle.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_primary_muscle)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = primaryExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = primaryExpanded,
                        onDismissRequest = { primaryExpanded = false }
                    ) {
                        candidateMuscles.forEach { muscle ->
                            DropdownMenuItem(
                                text = { Text(stringResource(muscle.nameRes)) },
                                onClick = {
                                    primaryMuscle = muscle
                                    primaryExpanded = false
                                }
                            )
                        }
                    }
                }

                // Exercise Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedCategory.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_equipment_category)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        ExerciseCategory.entries.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(stringResource(cat.nameRes)) },
                                onClick = {
                                    selectedCategory = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                // Mechanics Type Dropdown
                ExposedDropdownMenuBox(
                    expanded = mechanicsExpanded,
                    onExpandedChange = { mechanicsExpanded = !mechanicsExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedMechanics.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_mechanics_type)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mechanicsExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = mechanicsExpanded,
                        onDismissRequest = { mechanicsExpanded = false }
                    ) {
                        MechanicsType.entries.forEach { mech ->
                            DropdownMenuItem(
                                text = { Text(stringResource(mech.nameRes)) },
                                onClick = {
                                    selectedMechanics = mech
                                    mechanicsExpanded = false
                                }
                            )
                        }
                    }
                }

                // Force Vector Dropdown
                ExposedDropdownMenuBox(
                    expanded = forceExpanded,
                    onExpandedChange = { forceExpanded = !forceExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedForce.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_force_vector)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = forceExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = forceExpanded,
                        onDismissRequest = { forceExpanded = false }
                    ) {
                        ForceType.entries.forEach { force ->
                            DropdownMenuItem(
                                text = { Text(stringResource(force.nameRes)) },
                                onClick = {
                                    selectedForce = force
                                    userModifiedForce = true
                                    forceExpanded = false
                                }
                            )
                        }
                    }
                }

                // Difficulty Level Dropdown
                ExposedDropdownMenuBox(
                    expanded = levelExpanded,
                    onExpandedChange = { levelExpanded = !levelExpanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedLevel.nameRes),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_difficulty_level)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = levelExpanded) },
                        modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = levelExpanded,
                        onDismissRequest = { levelExpanded = false }
                    ) {
                        DifficultyLevel.entries.forEach { level ->
                            DropdownMenuItem(
                                text = { Text(stringResource(level.nameRes)) },
                                onClick = {
                                    selectedLevel = level
                                    userModifiedLevel = true
                                    levelExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(stringResource(R.string.label_notes_optional)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                // Custom Categories / Tags Selection
                Text(
                    text = stringResource(R.string.label_custom_categories),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

                val availableTags = remember(allCategories, selectedCategories) {
                    (allCategories.map { it.name } + selectedCategories).distinct()
                }

                if (availableTags.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        availableTags.forEach { tag ->
                            val isChecked = selectedCategories.contains(tag)
                            FilterChip(
                                selected = isChecked,
                                onClick = {
                                    selectedCategories = if (isChecked) {
                                        selectedCategories - tag
                                    } else {
                                        selectedCategories + tag
                                    }
                                },
                                label = { Text("#$tag", fontSize = 11.sp) }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newTagText,
                        onValueChange = { newTagText = it.replace(",", "") },
                        placeholder = { Text(stringResource(R.string.dialog_manage_categories_add_placeholder), fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    IconButton(
                        onClick = {
                            val trimmed = newTagText.trim()
                            if (trimmed.isNotBlank()) {
                                selectedCategories = selectedCategories + trimmed
                                newTagText = ""
                            }
                        },
                        enabled = newTagText.isNotBlank()
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onCreate(name.trim(), selectedGroup, selectedCategory, notes.trim(), primaryMuscle, emptyList(), selectedMechanics, selectedForce, selectedLevel, selectedCategories.toList())
                    }
                },
                enabled = name.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Text(
                    text = stringResource(R.string.action_save),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    )
}

@Composable
fun ManageCategoriesDialog(
    categories: List<CustomExerciseCategory>,
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit,
    onRename: (CustomExerciseCategory, String) -> Unit,
    onDelete: (CustomExerciseCategory) -> Unit
) {
    var newCategoryText by remember { mutableStateOf("") }
    var editingCategory by remember { mutableStateOf<CustomExerciseCategory?>(null) }
    var renameText by remember { mutableStateOf("") }
    var confirmingDeleteCategory by remember { mutableStateOf<CustomExerciseCategory?>(null) }

    if (confirmingDeleteCategory != null) {
        val targetCat = confirmingDeleteCategory!!
        AlertDialog(
            onDismissRequest = { confirmingDeleteCategory = null },
            title = {
                Text(stringResource(R.string.dialog_delete_category_confirm, targetCat.name), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete(targetCat)
                        confirmingDeleteCategory = null
                    }
                ) {
                    Text(stringResource(R.string.action_delete), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { confirmingDeleteCategory = null }) {
                    Text(stringResource(R.string.action_cancel))
                }
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.dialog_manage_categories_title), fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newCategoryText,
                        onValueChange = { newCategoryText = it.replace(",", "") },
                        placeholder = { Text(stringResource(R.string.dialog_manage_categories_add_placeholder), fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            val trimmed = newCategoryText.trim()
                            if (trimmed.isNotBlank()) {
                                onCreate(trimmed)
                                newCategoryText = ""
                            }
                        },
                        enabled = newCategoryText.isNotBlank()
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                if (categories.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_custom_categories),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 240.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(categories, key = { it.id }) { cat ->
                            if (editingCategory?.id == cat.id) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    OutlinedTextField(
                                        value = renameText,
                                        onValueChange = { renameText = it.replace(",", "") },
                                        singleLine = true,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = {
                                            val trimmed = renameText.trim()
                                            if (trimmed.isNotBlank()) {
                                                onRename(cat, trimmed)
                                                editingCategory = null
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    }
                                    IconButton(onClick = { editingCategory = null }) {
                                        Icon(Icons.Default.Close, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "#${cat.name}",
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = {
                                            editingCategory = cat
                                            renameText = cat.name
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                                    }
                                    IconButton(
                                        onClick = { confirmingDeleteCategory = cat },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}

@Composable
fun EditExerciseTagsDialog(
    exercise: Exercise,
    allCategories: List<CustomExerciseCategory>,
    onDismiss: () -> Unit,
    onSave: (List<String>) -> Unit
) {
    var selectedTags by remember { mutableStateOf(exercise.customCategories.toSet()) }
    var newTagText by remember { mutableStateOf("") }

    val candidateTags = remember(allCategories, selectedTags) {
        (allCategories.map { it.name } + selectedTags).distinct()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.dialog_edit_tags_title, exercise.displayName()), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newTagText,
                        onValueChange = { newTagText = it.replace(",", "") },
                        placeholder = { Text(stringResource(R.string.dialog_manage_categories_add_placeholder), fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            val trimmed = newTagText.trim()
                            if (trimmed.isNotBlank()) {
                                selectedTags = selectedTags + trimmed
                                newTagText = ""
                            }
                        },
                        enabled = newTagText.isNotBlank()
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                if (candidateTags.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_custom_categories),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        candidateTags.forEach { tag ->
                            val isChecked = selectedTags.contains(tag)
                            FilterChip(
                                selected = isChecked,
                                onClick = {
                                    selectedTags = if (isChecked) {
                                        selectedTags - tag
                                    } else {
                                        selectedTags + tag
                                    }
                                },
                                label = { Text("#$tag", fontSize = 12.sp) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(selectedTags.toList()) }
            ) {
                Text(stringResource(R.string.action_save_tags), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
