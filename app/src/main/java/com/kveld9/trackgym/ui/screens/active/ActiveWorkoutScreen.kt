package com.kveld9.trackgym.ui.screens.active

import java.util.Locale
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.material.icons.filled.Tune
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineFolder
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.kveld9.trackgym.domain.model.RpeScale
import com.kveld9.trackgym.ui.components.PinnedExerciseNotesCard
import com.kveld9.trackgym.ui.components.PrCelebrationBanner
import com.kveld9.trackgym.ui.components.RpeSelectionDialog
import com.kveld9.trackgym.ui.components.WorkoutSessionNotesCard
import com.kveld9.trackgym.ui.theme.GymBlue
import com.kveld9.trackgym.ui.theme.GymWarmupAmber
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName
import com.kveld9.trackgym.ui.viewmodel.GymViewModel

private const val DEFAULT_FALLBACK_WEIGHT_KG = 60.0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutScreen(
    viewModel: GymViewModel,
    onWorkoutFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeWorkout by viewModel.activeWorkout.collectAsState()
    val previousSetsMap by viewModel.previousSetsMap.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val recentPr by viewModel.recentlyUnlockedPr.collectAsState()
    val allExercises by viewModel.filteredExercises.collectAsState()
    val weightUnit by viewModel.weightUnit.collectAsState()

    // Rest Timer state
    val restRemaining by viewModel.restTimerRemainingSeconds.collectAsState()
    val restTotal by viewModel.restTimerTotalSeconds.collectAsState()
    val restIsRunning by viewModel.restTimerIsRunning.collectAsState()

    // Routines state
    val routines by viewModel.routines.collectAsState()
    val folders by viewModel.folders.collectAsState()

    val haptic = LocalHapticFeedback.current
    LaunchedEffect(Unit) {
        viewModel.restTimerFinishedEvent.collect {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    var showExercisePicker by remember { mutableStateOf(false) }
    var showFinishDialog by remember { mutableStateOf(false) }
    var showDiscardDialog by remember { mutableStateOf(false) }
    var showSaveRoutineDialog by remember { mutableStateOf(false) }
    var finishNotes by remember { mutableStateOf("") }
    var routineNameInput by remember { mutableStateOf("") }
    var plateCalcExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var exerciseToSwap by remember { mutableStateOf<WorkoutExercise?>(null) }
    var pendingSwapTarget by remember { mutableStateOf<Exercise?>(null) }
    var showSwapExercisePicker by remember { mutableStateOf(false) }
    var showSwapConfirmDialog by remember { mutableStateOf(false) }

    if (activeWorkout == null) {
        EmptyWorkoutDashboard(
            routines = routines,
            folders = folders,
            onStartWorkout = { viewModel.startWorkout() },
            onStartRoutine = { routineId -> viewModel.startWorkoutFromRoutine(routineId) },
            onDeleteRoutine = { routineId -> viewModel.deleteRoutine(routineId) },
            modifier = modifier
        )
    } else {
        Scaffold(
            topBar = {
                ActiveWorkoutTopBar(
                    timerSeconds = timerSeconds,
                    onFinishClick = { showFinishDialog = true },
                    onCancelClick = { showDiscardDialog = true },
                    onSaveAsRoutineClick = {
                        routineNameInput = activeWorkout?.name.orEmpty()
                        showSaveRoutineDialog = true
                    }
                )
            },
            bottomBar = {
                if (restRemaining != null) {
                    FloatingRestTimer(
                        remainingSeconds = restRemaining ?: 0,
                        totalSeconds = restTotal,
                        isRunning = restIsRunning,
                        onAddSeconds = { viewModel.addRestSeconds(it) },
                        onPauseResume = {
                            if (restIsRunning) viewModel.pauseRestTimer() else viewModel.resumeRestTimer()
                        },
                        onSkip = { viewModel.stopRestTimer() }
                    )
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
                        Column {
                            Text(
                                text = activeWorkout?.name ?: stringResource(R.string.workout_default_title),
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            WorkoutSessionNotesCard(
                                notes = activeWorkout?.notes.orEmpty(),
                                onNotesChange = { newNotes ->
                                    viewModel.updateWorkoutNotes(newNotes)
                                }
                            )
                        }
                    }

                    val exercisesList = activeWorkout?.exercises.orEmpty()
                    itemsIndexed(exercisesList, key = { _, it -> it.id }) { index, we ->
                        WorkoutExerciseCard(
                            workoutExercise = we,
                            weightUnit = weightUnit,
                            previousSets = previousSetsMap[we.exercise.id].orEmpty(),
                            canMoveUp = index > 0,
                            canMoveDown = index < exercisesList.size - 1,
                            onMoveUp = { viewModel.moveExercise(index, index - 1) },
                            onMoveDown = { viewModel.moveExercise(index, index + 1) },
                            onSwapExercise = {
                                exerciseToSwap = we
                                showSwapExercisePicker = true
                            },
                            onAddSet = {
                                viewModel.addSet(we.id, 0.0, 0)
                            },
                            onDuplicateSet = {
                                viewModel.duplicateLastSet(we.id)
                            },
                            onUpdateSet = { set -> viewModel.updateSet(set) },
                            onUpdateExerciseNotes = { notes ->
                                viewModel.updateExerciseNotes(we.exercise.id, notes)
                            },
                            onToggleComplete = { set, weight, reps ->
                                activeWorkout?.let { wo ->
                                    viewModel.toggleCompleteSet(set, wo.id, we.exercise.id, weight, reps)
                                }
                            },
                            onDeleteSet = { setId -> viewModel.deleteSet(setId) },
                            onRemoveExercise = { viewModel.removeExerciseFromActiveWorkout(we.id) },
                            onOpenPlateCalculator = { plateCalcExercise = we },
                            onAddWarmupSets = {
                                val workingWeight = we.sets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                                    ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
                                    ?: DEFAULT_FALLBACK_WEIGHT_KG
                                viewModel.addWarmupSets(we.id, workingWeight)
                            }
                        )
                    }

                    item {
                        Button(
                            onClick = { showExercisePicker = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.btn_add_exercise),
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(88.dp))
                    }
                }
            }
        }
    }

    if (showExercisePicker) {
        ModalBottomSheet(
            onDismissRequest = { showExercisePicker = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
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

    if (showSwapExercisePicker) {
        ModalBottomSheet(
            onDismissRequest = {
                showSwapExercisePicker = false
                exerciseToSwap = null
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            ExercisePickerContent(
                exercises = allExercises,
                onSelectExercise = { exercise ->
                    pendingSwapTarget = exercise
                    showSwapExercisePicker = false
                    showSwapConfirmDialog = true
                }
            )
        }
    }

    if (showSwapConfirmDialog && exerciseToSwap != null && pendingSwapTarget != null) {
        AlertDialog(
            onDismissRequest = {
                showSwapConfirmDialog = false
                exerciseToSwap = null
                pendingSwapTarget = null
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = {
                Text(
                    text = stringResource(R.string.dialog_swap_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.dialog_swap_msg),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val currentEx = exerciseToSwap
                        val targetEx = pendingSwapTarget
                        if (currentEx != null && targetEx != null) {
                            viewModel.swapExercise(currentEx.id, targetEx.id, resetSets = false)
                        }
                        showSwapConfirmDialog = false
                        exerciseToSwap = null
                        pendingSwapTarget = null
                    }
                ) {
                    Text(stringResource(R.string.action_swap_keep_sets))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        val currentEx = exerciseToSwap
                        val targetEx = pendingSwapTarget
                        if (currentEx != null && targetEx != null) {
                            viewModel.swapExercise(currentEx.id, targetEx.id, resetSets = true)
                        }
                        showSwapConfirmDialog = false
                        exerciseToSwap = null
                        pendingSwapTarget = null
                    }
                ) {
                    Text(stringResource(R.string.action_swap_reset_sets))
                }
            }
        )
    }

    if (showSaveRoutineDialog) {
        AlertDialog(
            onDismissRequest = { showSaveRoutineDialog = false },
            properties = DialogProperties(dismissOnClickOutside = false),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = {
                Text(
                    text = stringResource(R.string.dialog_save_routine_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = routineNameInput,
                        onValueChange = { routineNameInput = it },
                        label = { Text(stringResource(R.string.dialog_routine_name)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSaveRoutineDialog = false
                        viewModel.saveActiveWorkoutAsRoutine(routineNameInput)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(stringResource(R.string.action_save), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveRoutineDialog = false }) {
                    Text(stringResource(R.string.action_cancel), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }

    if (showFinishDialog) {
        AlertDialog(
            onDismissRequest = { showFinishDialog = false },
            properties = DialogProperties(dismissOnClickOutside = false),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = { Text(stringResource(R.string.dialog_finish_workout_title), color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(stringResource(R.string.dialog_finish_workout_msg), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = finishNotes,
                        onValueChange = { finishNotes = it },
                        placeholder = { Text(stringResource(R.string.notes_placeholder)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
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
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(stringResource(R.string.btn_save_and_summary), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showFinishDialog = false }) {
                    Text(stringResource(R.string.action_cancel), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }

    if (showDiscardDialog) {
        AlertDialog(
            onDismissRequest = { showDiscardDialog = false },
            properties = DialogProperties(dismissOnClickOutside = false),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = {
                Text(
                    text = stringResource(R.string.dialog_discard_workout_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.dialog_discard_workout_msg),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDiscardDialog = false
                        viewModel.cancelActiveWorkout()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(
                        text = stringResource(R.string.action_discard),
                        color = MaterialTheme.colorScheme.onError,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDiscardDialog = false }) {
                    Text(
                        text = stringResource(R.string.action_continue_workout),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        )
    }

    plateCalcExercise?.let { we ->
        val initialWeightKg = we.sets.lastOrNull { it.weightKg > 0.0 }?.weightKg
            ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
            ?: DEFAULT_FALLBACK_WEIGHT_KG
        PlateCalculatorDialog(
            exerciseName = we.exercise.displayName(),
            weightUnit = weightUnit,
            initialWeightKg = initialWeightKg,
            onDismiss = { plateCalcExercise = null }
        )
    }
}

@Composable
fun EmptyWorkoutDashboard(
    routines: List<Routine>,
    folders: List<RoutineFolder>,
    onStartWorkout: () -> Unit,
    onStartRoutine: (Long) -> Unit,
    onDeleteRoutine: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFolderId by remember { mutableStateOf<Long?>(null) }
    val filteredRoutines = if (selectedFolderId == null) {
        routines
    } else {
        routines.filter { it.folderId == selectedFolderId }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHigh
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.dashboard_title),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.dashboard_subtitle),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Primary CTA: Start Empty Workout
        Button(
            onClick = onStartWorkout,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.btn_start_empty_workout),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Routines & Templates Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.routines_title),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.routines_subtitle),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Folder Chips
        if (folders.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedFolderId == null,
                    onClick = { selectedFolderId = null },
                    label = { Text(stringResource(R.string.routine_folder_all)) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                folders.forEach { folder ->
                    FilterChip(
                        selected = selectedFolderId == folder.id,
                        onClick = { selectedFolderId = folder.id },
                        label = { Text(folder.name) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        if (filteredRoutines.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.routine_empty_title),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.routine_empty_desc),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                filteredRoutines.forEach { routine ->
                    RoutineCardItem(
                        routine = routine,
                        onStart = { onStartRoutine(routine.id) },
                        onDelete = { onDeleteRoutine(routine.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(88.dp))
    }
}

@Composable
fun RoutineCardItem(
    routine: Routine,
    onStart: () -> Unit,
    onDelete: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = routine.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                val context = LocalContext.current
                val keepEnglish = LocalKeepEnglishExerciseNames.current
                val exerciseNames = routine.exercises.joinToString(", ") { it.exercise.displayName(context, keepEnglish) }
                Text(
                    text = if (exerciseNames.isNotBlank()) exerciseNames else stringResource(R.string.routine_exercises_count, routine.exercises.size),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = onStart,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.height(38.dp)
                ) {
                    Text(
                        text = stringResource(R.string.btn_start_routine),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box {
                    IconButton(
                        onClick = { menuExpanded = true },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(Icons.Default.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_delete_routine), color = MaterialTheme.colorScheme.error) },
                            onClick = {
                                menuExpanded = false
                                onDelete()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FloatingRestTimer(
    remainingSeconds: Int,
    totalSeconds: Int,
    isRunning: Boolean,
    onAddSeconds: (Int) -> Unit,
    onPauseResume: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)
    val progress = if (totalSeconds > 0) {
        (remainingSeconds.toFloat() / totalSeconds.toFloat()).coerceIn(0f, 1f)
    } else 0f

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))),
        shadowElevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.rest_timer_title),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = timeFormatted,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    TextButton(
                        onClick = { onAddSeconds(-15) },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.rest_timer_minus_15),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    TextButton(
                        onClick = { onAddSeconds(15) },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.rest_timer_add_15),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = onPauseResume,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isRunning) "Pause" else "Resume",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = onSkip,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.rest_timer_skip),
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutTopBar(
    timerSeconds: Long,
    onFinishClick: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveAsRoutineClick: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val minutes = timerSeconds / 60
    val seconds = timerSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = timeFormatted,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        actions = {
            Box {
                IconButton(
                    onClick = { menuExpanded = true },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.action_save_as_routine), color = MaterialTheme.colorScheme.onSurface) },
                        onClick = {
                            menuExpanded = false
                            onSaveAsRoutineClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.action_discard), color = MaterialTheme.colorScheme.error) },
                        onClick = {
                            menuExpanded = false
                            onCancelClick()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = onFinishClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(stringResource(R.string.action_finish), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}

@Composable
fun WorkoutExerciseCard(
    workoutExercise: WorkoutExercise,
    weightUnit: WeightUnit = WeightUnit.KG,
    previousSets: List<WorkoutSet> = emptyList(),
    canMoveUp: Boolean = false,
    canMoveDown: Boolean = false,
    onMoveUp: () -> Unit = {},
    onMoveDown: () -> Unit = {},
    onSwapExercise: () -> Unit = {},
    onAddSet: () -> Unit,
    onDuplicateSet: () -> Unit = {},
    onUpdateSet: (WorkoutSet) -> Unit,
    onUpdateExerciseNotes: (String) -> Unit = {},
    onToggleComplete: (WorkoutSet, Double, Int) -> Unit,
    onDeleteSet: (Long) -> Unit,
    onRemoveExercise: () -> Unit,
    onOpenPlateCalculator: () -> Unit = {},
    onAddWarmupSets: () -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
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
                        text = workoutExercise.exercise.displayName(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${stringResource(workoutExercise.exercise.muscleGroup.nameRes)} • ${stringResource(workoutExercise.exercise.category.nameRes)}",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onOpenPlateCalculator,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = stringResource(R.string.action_plate_calculator),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Box {
                        IconButton(
                            onClick = { menuExpanded = true },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(Icons.Default.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false },
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                        ) {
                            if (canMoveUp) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.menu_move_up), color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        menuExpanded = false
                                        onMoveUp()
                                    }
                                )
                            }
                            if (canMoveDown) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.menu_move_down), color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        menuExpanded = false
                                        onMoveDown()
                                    }
                                )
                            }
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_swap_exercise), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onSwapExercise()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_add_warmup_sets), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onAddWarmupSets()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_plate_calculator), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenPlateCalculator()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_remove_exercise), color = MaterialTheme.colorScheme.error) },
                                onClick = {
                                    menuExpanded = false
                                    onRemoveExercise()
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Pinned Machine Setup & Notes
            PinnedExerciseNotesCard(
                notes = workoutExercise.exercise.notes,
                onNotesChange = onUpdateExerciseNotes
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Sets Table Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.table_header_set), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
                Text(weightUnit.symbol.uppercase(), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(stringResource(R.string.table_header_reps), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(stringResource(R.string.table_header_complete), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sets rows
            workoutExercise.sets.forEach { set ->
                val prevSet = previousSets.firstOrNull { it.setNumber == set.setNumber }
                    ?: previousSets.getOrNull(set.setNumber - 1)
                SetRowItem(
                    set = set,
                    weightUnit = weightUnit,
                    previousSet = prevSet,
                    onUpdateSet = onUpdateSet,
                    onToggleComplete = { w, r -> onToggleComplete(set, w, r) },
                    onDeleteSet = { onDeleteSet(set.id) }
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            Spacer(modifier = Modifier.height(6.dp))

            SetActionButtonsRow(
                hasSets = workoutExercise.sets.isNotEmpty(),
                onAddSet = onAddSet,
                onDuplicateSet = onDuplicateSet
            )
        }
    }
}

@Composable
fun SetActionButtonsRow(
    hasSets: Boolean,
    onAddSet: () -> Unit,
    onDuplicateSet: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (hasSets) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onAddSet,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.btn_add_set), fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = onDuplicateSet,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.btn_duplicate_set), fontSize = 12.sp)
            }
        }
    } else {
        OutlinedButton(
            onClick = onAddSet,
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
        ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(stringResource(R.string.btn_add_set), fontSize = 13.sp)
        }
    }
}

@Composable
fun SetRowItem(
    set: WorkoutSet,
    weightUnit: WeightUnit = WeightUnit.KG,
    previousSet: WorkoutSet? = null,
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (Double, Int) -> Unit,
    onDeleteSet: () -> Unit
) {
    var weightText by remember(set.id) {
        mutableStateOf(
            if (set.weightKg > 0.0 || (set.isCompleted && set.weightKg == 0.0)) {
                weightUnit.formatValue(set.weightKg)
            } else ""
        )
    }
    LaunchedEffect(set.weightKg, weightUnit) {
        val currentParsed = weightText.replace(',', '.').toDoubleOrNull()
        if (set.weightKg == 0.0 && weightText.isNotBlank() && currentParsed == 0.0) {
            return@LaunchedEffect
        }
        val expected = if (set.weightKg > 0.0 || (set.isCompleted && set.weightKg == 0.0)) {
            weightUnit.formatValue(set.weightKg)
        } else ""
        val expectedParsed = expected.toDoubleOrNull()
        if (currentParsed == null || expectedParsed == null || kotlin.math.abs(currentParsed - expectedParsed) > 0.001) {
            weightText = expected
        }
    }
    var repsText by remember(set.id, set.reps) {
        mutableStateOf(if (set.reps > 0) set.reps.toString() else "")
    }

    val ghostWeightDisplay = if (previousSet != null && previousSet.weightKg > 0.0) {
        weightUnit.formatValue(previousSet.weightKg)
    } else null
    val ghostRepsDisplay = if (previousSet != null && previousSet.reps > 0) {
        previousSet.reps.toString()
    } else null

    var showSetTypePicker by remember { mutableStateOf(false) }

    val checkBgColor by animateColorAsState(
        targetValue = if (set.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
        label = "checkColor"
    )

    val badgeColor = when (set.setType) {
        SetType.NORMAL -> if (set.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        SetType.WARMUP -> GymWarmupAmber
        SetType.DROP -> GymBlue
        SetType.FAILURE -> MaterialTheme.colorScheme.error
        SetType.MYO_REPS -> MaterialTheme.colorScheme.tertiary
    }

    val badgeLabel = when (set.setType) {
        SetType.NORMAL -> "${set.setNumber}"
        SetType.WARMUP -> "W"
        SetType.DROP -> "D"
        SetType.FAILURE -> "F"
        SetType.MYO_REPS -> "M"
    }

    var showQuickAdjust by remember { mutableStateOf(false) }
    var showRpePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (set.isCompleted) MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.4f) else Color.Transparent)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Set number / SetType badge (Interactive with 48x48 dp touch target)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (set.setType != SetType.NORMAL) badgeColor.copy(alpha = 0.15f) else Color.Transparent)
                    .clickable { showSetTypePicker = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeLabel,
                    color = badgeColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                DropdownMenu(
                    expanded = showSetTypePicker,
                    onDismissRequest = { showSetTypePicker = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    SetType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = type.shortLabel,
                                        color = when (type) {
                                            SetType.NORMAL -> MaterialTheme.colorScheme.primary
                                            SetType.WARMUP -> GymWarmupAmber
                                            SetType.DROP -> GymBlue
                                            SetType.FAILURE -> MaterialTheme.colorScheme.error
                                            SetType.MYO_REPS -> MaterialTheme.colorScheme.tertiary
                                        },
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(28.dp)
                                    )
                                    Text(
                                        text = stringResource(type.nameRes),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 14.sp
                                    )
                                }
                            },
                            onClick = {
                                showSetTypePicker = false
                                onUpdateSet(set.copy(setType = type))
                            }
                        )
                    }
                }
            }

            // Weight Input with Ghost Placeholder & Micro-load Toggle
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                OutlinedTextField(
                    value = weightText,
                    onValueChange = { input ->
                        weightText = input
                        val parsedDisplay = input.replace(',', '.').toDoubleOrNull() ?: 0.0
                        val inKg = weightUnit.toKg(parsedDisplay)
                        onUpdateSet(set.copy(weightKg = inKg))
                    },
                    placeholder = {
                        Text(
                            text = ghostWeightDisplay ?: "0",
                            color = if (ghostWeightDisplay != null) {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                            }
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { showQuickAdjust = !showQuickAdjust },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = "Quick adjust",
                                tint = if (showQuickAdjust) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                showQuickAdjust = true
                            }
                        },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            // Reps Input with Ghost Placeholder
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                OutlinedTextField(
                    value = repsText,
                    onValueChange = { input ->
                        repsText = input
                        val parsed = input.toIntOrNull() ?: 0
                        onUpdateSet(set.copy(reps = parsed))
                    },
                    placeholder = {
                        Text(
                            text = ghostRepsDisplay ?: "0",
                            color = if (ghostRepsDisplay != null) {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                            }
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp),
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

            // Complete Checkbox Button (48x48 dp minimum touch target)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(checkBgColor)
                    .clickable {
                        val typedDisplay = weightText.replace(',', '.').toDoubleOrNull()
                        val finalWeightKg = if (typedDisplay != null) {
                            weightUnit.toKg(typedDisplay)
                        } else if (set.weightKg > 0.0) {
                            set.weightKg
                        } else if (previousSet != null && previousSet.weightKg > 0.0) {
                            previousSet.weightKg
                        } else {
                            0.0
                        }

                        val typedReps = repsText.toIntOrNull()
                        val finalReps = if (typedReps != null) {
                            typedReps
                        } else if (set.reps > 0) {
                            set.reps
                        } else if (previousSet != null && previousSet.reps > 0) {
                            previousSet.reps
                        } else {
                            0
                        }

                        if (weightText.isBlank()) {
                            weightText = weightUnit.formatValue(finalWeightKg)
                        }
                        if (repsText.isBlank() && finalReps > 0) {
                            repsText = finalReps.toString()
                        }

                        showQuickAdjust = false
                        onToggleComplete(finalWeightKg, finalReps)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.desc_complete_set),
                    tint = if (set.isCompleted) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SetRpeChip(
                rpe = set.rpe,
                onClick = { showRpePicker = true }
            )
        }

        // Quick-adjust Micro-load Chips
        AnimatedVisibility(visible = showQuickAdjust && !set.isCompleted) {
            val currentDisplay = weightText.replace(',', '.').toDoubleOrNull()
                ?: ghostWeightDisplay?.replace(',', '.')?.toDoubleOrNull()
                ?: 0.0
            MicroLoadChipsRow(
                weightUnit = weightUnit,
                currentWeight = currentDisplay,
                onAdjust = { updatedDisplay ->
                    weightText = if (updatedDisplay % 1.0 == 0.0) {
                        "${updatedDisplay.toInt()}"
                    } else {
                        String.format(Locale.US, "%.2f", updatedDisplay).trimEnd('0').trimEnd('.')
                    }
                    val inKg = weightUnit.toKg(updatedDisplay)
                    onUpdateSet(set.copy(weightKg = inKg))
                },
                onClose = { showQuickAdjust = false }
            )
        }
    }

    if (showRpePicker) {
        RpeSelectionDialog(
            currentRpe = set.rpe,
            onSelectRpe = { selectedRpe ->
                onUpdateSet(set.copy(rpe = selectedRpe))
            },
            onDismiss = { showRpePicker = false }
        )
    }
}

@Composable
fun SetRpeChip(
    rpe: Double?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rpeStr = RpeScale.formatRpe(rpe)
    val rirLabel = if (rpe != null) RpeScale.options.firstOrNull { it.rpe == rpe }?.rirLabel else null
    val rpeLabel = when {
        rpeStr != null && rirLabel != null -> "@$rpeStr (RIR $rirLabel)"
        rpeStr != null -> "@$rpeStr"
        else -> stringResource(R.string.rpe_add)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (rpe != null) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = rpeLabel,
            color = if (rpe != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            fontSize = 11.sp,
            fontWeight = if (rpe != null) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun MicroLoadChipsRow(
    weightUnit: WeightUnit,
    currentWeight: Double,
    onAdjust: (Double) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val deltas = when (weightUnit) {
        WeightUnit.KG -> listOf(-5.0, -2.5, -1.25, 1.25, 2.5, 5.0)
        WeightUnit.LB -> listOf(-10.0, -5.0, -2.5, 2.5, 5.0, 10.0)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        deltas.forEach { delta ->
            val sign = if (delta > 0) "+" else ""
            val deltaLabel = "$sign${if (delta % 1.0 == 0.0) delta.toInt().toString() else delta.toString()}"
            val isPositive = delta > 0

            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        val updated = (currentWeight + delta).coerceAtLeast(0.0)
                        val rounded = kotlin.math.round(updated * 100.0) / 100.0
                        onAdjust(rounded)
                    },
                shape = RoundedCornerShape(8.dp),
                color = if (isPositive) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        else MaterialTheme.colorScheme.surfaceContainerHigh,
                border = BorderStroke(
                    1.dp,
                    if (isPositive) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                )
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = deltaLabel,
                        color = if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }

        IconButton(
            onClick = onClose,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
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
            color = MaterialTheme.colorScheme.onSurface,
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
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .clickable { onSelectExercise(exercise) }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = exercise.displayName(),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${stringResource(exercise.muscleGroup.nameRes)} • ${stringResource(exercise.category.nameRes)}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp
                        )
                    }
                    if (exercise.isCustom) {
                        Text(
                            text = stringResource(R.string.badge_manual),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.badge_preloaded),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlateCalculatorDialog(
    exerciseName: String,
    weightUnit: WeightUnit,
    initialWeightKg: Double,
    onDismiss: () -> Unit
) {
    val initialDisplay = weightUnit.formatValue(initialWeightKg)
    var targetWeightInput by remember { mutableStateOf(initialDisplay) }
    val defaultBar = com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultBarWeight(weightUnit)
    var barWeightInput by remember { mutableStateOf(if (defaultBar % 1.0 == 0.0) defaultBar.toInt().toString() else defaultBar.toString()) }

    val currentTarget = targetWeightInput.replace(',', '.').toDoubleOrNull() ?: 0.0
    val currentBar = barWeightInput.replace(',', '.').toDoubleOrNull() ?: defaultBar
    val availablePlates = remember(weightUnit) {
        com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultPlates(weightUnit)
    }

    val calcResult = remember(currentTarget, currentBar, weightUnit) {
        com.kveld9.trackgym.domain.calculator.PlateCalculator.calculatePlates(
            targetWeight = currentTarget,
            barWeight = currentBar,
            availablePlates = availablePlates
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
