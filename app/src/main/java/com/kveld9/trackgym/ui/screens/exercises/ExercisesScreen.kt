package com.kveld9.trackgym.ui.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup
import com.kveld9.trackgym.ui.theme.GymBlack
import com.kveld9.trackgym.ui.theme.GymBorder
import com.kveld9.trackgym.ui.theme.GymNeonGreen
import com.kveld9.trackgym.ui.theme.GymSurface
import com.kveld9.trackgym.ui.theme.GymSurfaceVariant
import com.kveld9.trackgym.ui.theme.TextMuted
import com.kveld9.trackgym.ui.theme.TextWhite
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(
    viewModel: GymViewModel,
    modifier: Modifier = Modifier
) {
    val exercises by viewModel.filteredExercises.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedFilter by viewModel.selectedMuscleFilter.collectAsState()

    var showCreateDialog by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(12.dp))

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

            Spacer(modifier = Modifier.height(12.dp))

            // Exercise List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(exercises, key = { it.id }) { exercise ->
                    ExerciseRowCard(exercise = exercise)
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

    if (showCreateDialog) {
        CreateExerciseDialog(
            onDismiss = { showCreateDialog = false },
            onCreate = { name, group, category, notes ->
                viewModel.createCustomExercise(name, group, category, notes)
                showCreateDialog = false
            }
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
            .background(if (isSelected) GymNeonGreen else GymSurfaceVariant)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = if (isSelected) GymBlack else TextWhite,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ExerciseRowCard(exercise: Exercise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(GymSurface)
            .border(1.dp, GymBorder, RoundedCornerShape(12.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "${stringResource(exercise.muscleGroup.nameRes)} • ${stringResource(exercise.category.nameRes)}",
                color = GymNeonGreen,
                fontSize = 12.sp
            )
        }

        if (exercise.isCustom) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(GymNeonGreen.copy(alpha = 0.15f))
                    .border(1.dp, GymNeonGreen, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.badge_manual),
                    color = GymNeonGreen,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExerciseDialog(
    onDismiss: () -> Unit,
    onCreate: (String, MuscleGroup, ExerciseCategory, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedGroup by remember { mutableStateOf(MuscleGroup.CHEST) }
    var selectedCategory by remember { mutableStateOf(ExerciseCategory.BARBELL) }
    var notes by remember { mutableStateOf("") }

    var groupExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = GymSurface,
        title = {
            Text(stringResource(R.string.dialog_create_exercise_title), color = TextWhite, fontWeight = FontWeight.Bold)
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
                        focusedBorderColor = GymNeonGreen,
                        unfocusedBorderColor = GymBorder,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
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
                            focusedBorderColor = GymNeonGreen,
                            unfocusedBorderColor = GymBorder,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
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
                            focusedBorderColor = GymNeonGreen,
                            unfocusedBorderColor = GymBorder,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
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

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(stringResource(R.string.label_notes_optional)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GymNeonGreen,
                        unfocusedBorderColor = GymBorder,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onCreate(name.trim(), selectedGroup, selectedCategory, notes.trim())
                    }
                },
                enabled = name.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = GymNeonGreen)
            ) {
                Text(stringResource(R.string.action_save), color = GymBlack, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel), color = TextMuted)
            }
        }
    )
}
