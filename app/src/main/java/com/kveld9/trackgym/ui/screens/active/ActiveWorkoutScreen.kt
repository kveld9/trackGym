package com.kveld9.trackgym.ui.screens.active

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import com.kveld9.trackgym.ui.components.PrCelebrationBanner
import com.kveld9.trackgym.ui.theme.GymBlack
import com.kveld9.trackgym.ui.theme.GymBorder
import com.kveld9.trackgym.ui.theme.GymNeonGreen
import com.kveld9.trackgym.ui.theme.GymRed
import com.kveld9.trackgym.ui.theme.GymSurface
import com.kveld9.trackgym.ui.theme.GymSurfaceVariant
import com.kveld9.trackgym.ui.theme.TextMuted
import com.kveld9.trackgym.ui.theme.TextWhite
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutScreen(
    viewModel: GymViewModel,
    onWorkoutFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeWorkout by viewModel.activeWorkout.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val recentPr by viewModel.recentlyUnlockedPr.collectAsState()
    val allExercises by viewModel.filteredExercises.collectAsState()
    val weightUnit by viewModel.weightUnit.collectAsState()

    var showExercisePicker by remember { mutableStateOf(false) }
    var showFinishDialog by remember { mutableStateOf(false) }
    var finishNotes by remember { mutableStateOf("") }

    if (activeWorkout == null) {
        EmptyWorkoutDashboard(
            onStartWorkout = { viewModel.startWorkout() },
            modifier = modifier
        )
    } else {
        Scaffold(
            topBar = {
                ActiveWorkoutTopBar(
                    timerSeconds = timerSeconds,
                    onFinishClick = { showFinishDialog = true },
                    onCancelClick = { viewModel.cancelActiveWorkout() }
                )
            },
            containerColor = GymBlack,
            modifier = modifier
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Real-time PR Celebration banner
                PrCelebrationBanner(
                    record = recentPr,
                    onDismiss = { viewModel.clearRecentPrAlert() }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = activeWorkout?.name ?: stringResource(R.string.workout_default_title),
                            color = TextWhite,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    items(activeWorkout?.exercises.orEmpty(), key = { it.id }) { we ->
                        WorkoutExerciseCard(
                            workoutExercise = we,
                            weightUnit = weightUnit,
                            onAddSet = {
                                val lastSet = we.sets.lastOrNull()
                                val weight = lastSet?.weightKg ?: 0.0
                                val reps = lastSet?.reps ?: 0
                                viewModel.addSet(we.id, weight, reps)
                            },
                            onUpdateSet = { set -> viewModel.updateSet(set) },
                            onToggleComplete = { set, weight, reps ->
                                activeWorkout?.let { wo ->
                                    viewModel.toggleCompleteSet(set, wo.id, we.exercise.id, weight, reps)
                                }
                            },
                            onDeleteSet = { setId -> viewModel.deleteSet(setId) },
                            onRemoveExercise = { viewModel.removeExerciseFromActiveWorkout(we.id) }
                        )
                    }

                    item {
                        Button(
                            onClick = { showExercisePicker = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GymSurfaceVariant)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = GymNeonGreen)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.btn_add_exercise),
                                color = GymNeonGreen,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }

    if (showExercisePicker) {
        ModalBottomSheet(
            onDismissRequest = { showExercisePicker = false },
            containerColor = GymSurface,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            ExercisePickerContent(
                exercises = allExercises,
                onSelectExercise = { exercise ->
                    viewModel.addExerciseToActiveWorkout(exercise.id)
                    showExercisePicker = false
                }
            )
        }
    }

    if (showFinishDialog) {
        AlertDialog(
            onDismissRequest = { showFinishDialog = false },
            containerColor = GymSurface,
            title = { Text(stringResource(R.string.dialog_finish_workout_title), color = TextWhite, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(stringResource(R.string.dialog_finish_workout_msg), color = TextMuted)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = finishNotes,
                        onValueChange = { finishNotes = it },
                        placeholder = { Text(stringResource(R.string.notes_placeholder)) },
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
                        showFinishDialog = false
                        viewModel.finishWorkout(finishNotes) {
                            onWorkoutFinished()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GymNeonGreen)
                ) {
                    Text(stringResource(R.string.btn_save_and_summary), color = GymBlack, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showFinishDialog = false }) {
                    Text(stringResource(R.string.action_cancel), color = TextMuted)
                }
            }
        )
    }
}

@Composable
fun EmptyWorkoutDashboard(
    onStartWorkout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GymBlack)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(96.dp),
            shape = CircleShape,
            color = GymSurfaceVariant
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = GymNeonGreen,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.dashboard_title),
            color = TextWhite,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.dashboard_subtitle),
            color = TextMuted,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = onStartWorkout,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GymNeonGreen)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = GymBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.btn_start_empty_workout),
                color = GymBlack,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutTopBar(
    timerSeconds: Long,
    onFinishClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    val minutes = timerSeconds / 60
    val seconds = timerSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(GymSurfaceVariant)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null,
                    tint = GymNeonGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = timeFormatted,
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        actions = {
            TextButton(onClick = onCancelClick) {
                Text(stringResource(R.string.action_discard), color = GymRed, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = onFinishClick,
                colors = ButtonDefaults.buttonColors(containerColor = GymNeonGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(stringResource(R.string.action_finish), color = GymBlack, fontWeight = FontWeight.Bold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = GymBlack)
    )
}

@Composable
fun WorkoutExerciseCard(
    workoutExercise: WorkoutExercise,
    weightUnit: WeightUnit = WeightUnit.KG,
    onAddSet: () -> Unit,
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (WorkoutSet, Double, Int) -> Unit,
    onDeleteSet: (Long) -> Unit,
    onRemoveExercise: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymSurface),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(GymBorder))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Exercise Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = workoutExercise.exercise.name,
                        color = TextWhite,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${stringResource(workoutExercise.exercise.muscleGroup.nameRes)} • ${stringResource(workoutExercise.exercise.category.nameRes)}",
                        color = GymNeonGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null, tint = TextMuted)
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_remove_exercise), color = GymRed) },
                            onClick = {
                                menuExpanded = false
                                onRemoveExercise()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sets Table Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.table_header_set), color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(42.dp), textAlign = TextAlign.Center)
                Text(weightUnit.symbol.uppercase(), color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(stringResource(R.string.table_header_reps), color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(stringResource(R.string.table_header_complete), color = TextMuted, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sets rows
            workoutExercise.sets.forEach { set ->
                SetRowItem(
                    set = set,
                    weightUnit = weightUnit,
                    onUpdateSet = onUpdateSet,
                    onToggleComplete = { w, r -> onToggleComplete(set, w, r) },
                    onDeleteSet = { onDeleteSet(set.id) }
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Add Set button
            OutlinedButton(
                onClick = onAddSet,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(GymBorder))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(stringResource(R.string.btn_add_set), fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun SetRowItem(
    set: WorkoutSet,
    weightUnit: WeightUnit = WeightUnit.KG,
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (Double, Int) -> Unit,
    onDeleteSet: () -> Unit
) {
    val initialDisplay = if (set.weightKg > 0.0) weightUnit.formatValue(set.weightKg) else ""
    var weightText by remember(set.id, weightUnit) {
        mutableStateOf(initialDisplay)
    }
    var repsText by remember(set.reps) {
        mutableStateOf(if (set.reps > 0) set.reps.toString() else "")
    }

    val checkBgColor by animateColorAsState(
        targetValue = if (set.isCompleted) GymNeonGreen else GymSurfaceVariant,
        label = "checkColor"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (set.isCompleted) GymSurfaceVariant.copy(alpha = 0.4f) else Color.Transparent)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Set number badge
        Box(
            modifier = Modifier
                .width(42.dp)
                .height(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${set.setNumber}",
                color = if (set.isCompleted) GymNeonGreen else TextMuted,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        // Weight Input
        Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
            OutlinedTextField(
                value = weightText,
                onValueChange = { input ->
                    weightText = input
                    val parsedDisplay = input.toDoubleOrNull() ?: 0.0
                    val inKg = weightUnit.toKg(parsedDisplay)
                    onUpdateSet(set.copy(weightKg = inKg))
                },
                placeholder = { Text("0", color = TextMuted) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = TextWhite, fontSize = 15.sp),
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GymNeonGreen,
                    unfocusedBorderColor = GymBorder,
                    focusedContainerColor = GymSurfaceVariant,
                    unfocusedContainerColor = GymSurfaceVariant
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Reps Input
        Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
            OutlinedTextField(
                value = repsText,
                onValueChange = { input ->
                    repsText = input
                    val parsed = input.toIntOrNull() ?: 0
                    onUpdateSet(set.copy(reps = parsed))
                },
                placeholder = { Text("0", color = TextMuted) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = TextWhite, fontSize = 15.sp),
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GymNeonGreen,
                    unfocusedBorderColor = GymBorder,
                    focusedContainerColor = GymSurfaceVariant,
                    unfocusedContainerColor = GymSurfaceVariant
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Complete Checkbox Button
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(checkBgColor)
                .clickable {
                    val typedDisplay = weightText.toDoubleOrNull()
                    val finalWeightKg = if (typedDisplay != null) {
                        weightUnit.toKg(typedDisplay)
                    } else {
                        set.weightKg
                    }
                    val finalReps = repsText.toIntOrNull() ?: set.reps
                    onToggleComplete(finalWeightKg, finalReps)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.desc_complete_set),
                tint = if (set.isCompleted) GymBlack else TextMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun ExercisePickerContent(
    exercises: List<Exercise>,
    onSelectExercise: (Exercise) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_select_exercise),
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(exercises, key = { it.id }) { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(GymSurfaceVariant)
                        .clickable { onSelectExercise(exercise) }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = exercise.name,
                            color = TextWhite,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${stringResource(exercise.muscleGroup.nameRes)} • ${stringResource(exercise.category.nameRes)}",
                            color = GymNeonGreen,
                            fontSize = 12.sp
                        )
                    }
                    if (exercise.isCustom) {
                        Text(
                            text = stringResource(R.string.badge_manual),
                            color = GymNeonGreen,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .border(1.dp, GymNeonGreen, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
