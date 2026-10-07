package com.kveld9.trackgym.ui.screens.active

import android.content.Intent
import android.widget.Toast
import com.kveld9.trackgym.domain.util.RoutineShareCodec
import java.util.Locale
import kotlinx.coroutines.launch
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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Timer
import com.kveld9.trackgym.domain.calculator.ProgramRecommendation
import com.kveld9.trackgym.domain.model.PeriodizedCycle
import com.kveld9.trackgym.ui.components.DeloadRoutineDialog
import com.kveld9.trackgym.ui.components.PeriodizedCycleDialog
import com.kveld9.trackgym.ui.components.ProgramFinderDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineFolder
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import com.kveld9.trackgym.domain.calculator.CardioCalculator
import com.kveld9.trackgym.domain.calculator.OvertrainingDetector
import com.kveld9.trackgym.domain.calculator.ProgressiveOverloadEngine
import com.kveld9.trackgym.domain.calculator.ProgressiveOverloadRecommendation
import com.kveld9.trackgym.domain.model.DistanceUnit
import com.kveld9.trackgym.domain.model.RpeScale
import com.kveld9.trackgym.domain.calculator.OneRepMaxCalculator
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.ui.components.OvertrainingWarningBanner
import com.kveld9.trackgym.ui.components.PinnedExerciseNotesCard
import com.kveld9.trackgym.ui.components.PrCelebrationBanner
import com.kveld9.trackgym.domain.calculator.WarmupGenerator
import com.kveld9.trackgym.domain.calculator.WarmupSetConfig
import com.kveld9.trackgym.domain.calculator.AutoProgressionEngine
import com.kveld9.trackgym.domain.calculator.AutoProgressionResult
import com.kveld9.trackgym.domain.calculator.ProgressionDecisionType
import com.kveld9.trackgym.ui.components.AutoProgressionDialog
import com.kveld9.trackgym.ui.components.RpeSelectionDialog
import com.kveld9.trackgym.ui.components.RpeTargetWeightDialog
import com.kveld9.trackgym.ui.components.WarmupRampDialog
import com.kveld9.trackgym.ui.components.WorkoutSessionNotesCard
import com.kveld9.trackgym.ui.theme.GymBlue
import com.kveld9.trackgym.ui.theme.GymWarmupAmber
import com.kveld9.trackgym.ui.theme.GymAmrapCrimson
import com.kveld9.trackgym.ui.theme.GymCardioTeal
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
    val activeWorkout by viewModel.activeWorkout.collectAsStateWithLifecycle()
    val previousSetsMap by viewModel.previousSetsMap.collectAsStateWithLifecycle()
    val timerSeconds by viewModel.timerSeconds.collectAsStateWithLifecycle()
    val recentPr by viewModel.recentlyUnlockedPr.collectAsStateWithLifecycle()
    val allExercises by viewModel.filteredExercises.collectAsStateWithLifecycle()
    val weightUnit by viewModel.weightUnit.collectAsStateWithLifecycle()
    val distanceUnit by viewModel.distanceUnit.collectAsStateWithLifecycle()
    val userBodyWeight by viewModel.userBodyWeight.collectAsStateWithLifecycle()
    val showInlinePlates by viewModel.showInlinePlates.collectAsStateWithLifecycle()
    val allRecords by viewModel.allRecords.collectAsStateWithLifecycle()

    // Rest Timer state
    val restRemaining by viewModel.restTimerRemainingSeconds.collectAsStateWithLifecycle()
    val restTotal by viewModel.restTimerTotalSeconds.collectAsStateWithLifecycle()
    val restIsRunning by viewModel.restTimerIsRunning.collectAsStateWithLifecycle()

    // Routines state
    val routines by viewModel.routines.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val routineUpdateMode by viewModel.routineUpdateMode.collectAsStateWithLifecycle()

    val haptic = LocalHapticFeedback.current
    val context = LocalContext.current
    val audioCuePlayer = remember { com.kveld9.trackgym.ui.audio.WorkoutAudioCuePlayer(context) }
    val timerSoundName by viewModel.timerSound.collectAsStateWithLifecycle()
    val timerSoundCountdown by viewModel.timerSoundCountdown.collectAsStateWithLifecycle()
    val soundFeedbackOnComplete by viewModel.soundFeedbackOnComplete.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        onDispose {
            audioCuePlayer.release()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.restTimerWarningEvent.collect {
            audioCuePlayer.playWarningBeep()
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.restTimerFinishedEvent.collect {
            val sound = runCatching {
                com.kveld9.trackgym.ui.audio.TimerSound.valueOf(timerSoundName)
            }.getOrDefault(com.kveld9.trackgym.ui.audio.TimerSound.DIGITAL_BEEP)
            audioCuePlayer.playFinishedSound(sound)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            com.kveld9.trackgym.service.RestTimerNotificationManager.showTimerNotification(
                context = context,
                remainingSeconds = 0,
                totalSeconds = restTotal
            )
        }
    }

    LaunchedEffect(restRemaining, restTotal) {
        val remaining = restRemaining
        if (remaining != null && remaining > 0) {
            com.kveld9.trackgym.service.RestTimerNotificationManager.showTimerNotification(
                context = context,
                remainingSeconds = remaining,
                totalSeconds = restTotal
            )
        } else if (remaining == null) {
            com.kveld9.trackgym.service.RestTimerNotificationManager.dismissNotification(context)
        }
    }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.supersetFocusEvent.collect { targetExerciseId ->
            val targetIndex = activeWorkout?.exercises?.indexOfFirst { it.exercise.id == targetExerciseId } ?: -1
            if (targetIndex >= 0) {
                listState.animateScrollToItem(targetIndex + 1)
            }
        }
    }

    var showExercisePicker by remember { mutableStateOf(false) }
    var showFinishDialog by remember { mutableStateOf(false) }
    var showRoutineSyncPrompt by remember { mutableStateOf(false) }
    var showDiscardDialog by remember { mutableStateOf(false) }
    var showSaveRoutineDialog by remember { mutableStateOf(false) }
    var finishNotes by remember { mutableStateOf("") }
    var detachRoutineFromWorkout by remember { mutableStateOf(false) }
    var backdateCompletionTimestamp by remember { mutableStateOf<Long?>(null) }
    var routineNameInput by remember { mutableStateOf("") }
    var plateCalcExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var plateCalcInitialWeightKg by remember { mutableStateOf<Double?>(null) }
    var rpeCalcExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var rpeCalcSet by remember { mutableStateOf<WorkoutSet?>(null) }
    var restConfigExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var warmupRampExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var autoProgressionExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var supersetConfigExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var exerciseToSwap by remember { mutableStateOf<WorkoutExercise?>(null) }
    var pendingSwapTarget by remember { mutableStateOf<Exercise?>(null) }
    var showSwapExercisePicker by remember { mutableStateOf(false) }
    var showSwapConfirmDialog by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val setDeletedMsg = stringResource(R.string.snackbar_set_deleted)
    val undoMsg = stringResource(R.string.action_undo)

    var dismissedOvertrainingWarning by rememberSaveable { mutableStateOf(false) }
    val currentWorkout = activeWorkout
    val overtrainingWarnings = remember(currentWorkout, dismissedOvertrainingWarning) {
        if (currentWorkout != null && !dismissedOvertrainingWarning) {
            OvertrainingDetector.detectExcessiveVolume(currentWorkout)
        } else {
            emptyList()
        }
    }

    if (activeWorkout == null) {
        val copySuffix = stringResource(R.string.routine_copy_suffix)
        EmptyWorkoutDashboard(
            routines = routines,
            folders = folders,
            onStartWorkout = { viewModel.startWorkout() },
            onStartRoutine = { routineId -> viewModel.startWorkoutFromRoutine(routineId) },
            onDeleteRoutine = { routineId -> viewModel.deleteRoutine(routineId) },
            onDuplicateRoutine = { routineId -> viewModel.duplicateRoutine(routineId, copySuffix) },
            onToggleArchive = { routineId, isArchived -> viewModel.toggleRoutineArchived(routineId, isArchived) },
            onImportRoutine = { rawText, onResult -> viewModel.importRoutineFromText(rawText, onResult) },
            onMoveRoutineUp = { routineId -> viewModel.moveRoutineUp(routineId, routines) },
            onMoveRoutineDown = { routineId -> viewModel.moveRoutineDown(routineId, routines) },
            onMoveFolderUp = { folderId -> viewModel.moveFolderUp(folderId, folders) },
            onMoveFolderDown = { folderId -> viewModel.moveFolderDown(folderId, folders) },
            onUpdatePeriodization = { routineId, isPeriodized, cycle -> viewModel.updateRoutinePeriodization(routineId, isPeriodized, cycle) },
            onAdvanceCycleWeek = { routineId -> viewModel.advanceRoutineCycleWeek(routineId) },
            onPreviousCycleWeek = { routineId -> viewModel.previousRoutineCycleWeek(routineId) },
            onInstantiateProgram = { program -> viewModel.instantiateProgram(program) },
            onGenerateDeloadRoutine = { routineId, loadPct, volPct -> viewModel.generateDeloadRoutine(routineId, loadPct, volPct) },
            modifier = modifier
        )
    } else {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
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

                // Real-time Excessive Volume / Overtraining warning banner
                OvertrainingWarningBanner(
                    warnings = overtrainingWarnings,
                    onDismiss = { dismissedOvertrainingWarning = true }
                )

                LazyColumn(
                    state = listState,
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
                            distanceUnit = distanceUnit,
                            userBodyWeightKg = userBodyWeight,
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
                                if (!set.isCompleted) {
                                    if (soundFeedbackOnComplete) {
                                        audioCuePlayer.playSetCompleteClick()
                                    }
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                }
                                activeWorkout?.let { wo ->
                                    viewModel.toggleCompleteSet(set, wo.id, we.exercise.id, weight, reps)
                                }
                            },
                            onDeleteSet = { set ->
                                viewModel.deleteSet(set)
                                coroutineScope.launch {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    val result = snackbarHostState.showSnackbar(
                                        message = setDeletedMsg,
                                        actionLabel = undoMsg,
                                        duration = SnackbarDuration.Short
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.restoreRecentlyDeletedSet()
                                    }
                                }
                            },
                            onRemoveExercise = { viewModel.removeExerciseFromActiveWorkout(we.id) },
                            showInlinePlates = showInlinePlates,
                            onOpenPlateCalculator = {
                                plateCalcExercise = we
                                plateCalcInitialWeightKg = null
                            },
                            onOpenPlateCalculatorForWeight = { targetKg ->
                                plateCalcExercise = we
                                plateCalcInitialWeightKg = targetKg
                            },
                            onOpenRpeCalculator = { targetSet ->
                                rpeCalcExercise = we
                                rpeCalcSet = targetSet
                            },
                            onSetRestDuration = { restConfigExercise = we },
                            onSetSupersetGroup = { supersetConfigExercise = we },
                            onAddWarmupSets = {
                                val workingWeight = we.sets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                                    ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
                                    ?: DEFAULT_FALLBACK_WEIGHT_KG
                                viewModel.addWarmupSets(we.id, workingWeight)
                            },
                            onConfigureWarmupRamp = { warmupRampExercise = we },
                            onConfigureAutoProgression = { autoProgressionExercise = we }
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

                    val activeDateMillis = backdateCompletionTimestamp ?: System.currentTimeMillis()
                    val formattedDate = remember(activeDateMillis) {
                        java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
                            .format(java.util.Date(activeDateMillis))
                    }
                    val isBackdated = backdateCompletionTimestamp != null
                    val context = LocalContext.current

                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                            .clickable {
                                val cal = java.util.Calendar.getInstance().apply { timeInMillis = activeDateMillis }
                                android.app.DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        cal.set(java.util.Calendar.YEAR, year)
                                        cal.set(java.util.Calendar.MONTH, month)
                                        cal.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
                                        android.app.TimePickerDialog(
                                            context,
                                            { _, hourOfDay, minute ->
                                                cal.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
                                                cal.set(java.util.Calendar.MINUTE, minute)
                                                backdateCompletionTimestamp = cal.timeInMillis
                                            },
                                            cal.get(java.util.Calendar.HOUR_OF_DAY),
                                            cal.get(java.util.Calendar.MINUTE),
                                            true
                                        ).show()
                                    },
                                    cal.get(java.util.Calendar.YEAR),
                                    cal.get(java.util.Calendar.MONTH),
                                    cal.get(java.util.Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(R.string.label_completion_date),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isBackdated) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.action_backdate_workout),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    if (activeWorkout?.routineId != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { detachRoutineFromWorkout = !detachRoutineFromWorkout }
                                .padding(vertical = 4.dp)
                        ) {
                            Checkbox(
                                checked = detachRoutineFromWorkout,
                                onCheckedChange = { detachRoutineFromWorkout = it }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = stringResource(R.string.checkbox_detach_routine, activeWorkout?.name.orEmpty()),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = stringResource(R.string.checkbox_detach_routine_desc),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showFinishDialog = false
                        val linkedRoutineId = activeWorkout?.routineId
                        if (linkedRoutineId != null && !detachRoutineFromWorkout) {
                            when (routineUpdateMode) {
                                com.kveld9.trackgym.domain.model.RoutineUpdateMode.ALWAYS -> {
                                    viewModel.finishWorkout(
                                        notes = finishNotes,
                                        syncRoutine = true,
                                        detachRoutine = false,
                                        completedAtTimestamp = backdateCompletionTimestamp
                                    ) {
                                        onWorkoutFinished()
                                    }
                                }
                                com.kveld9.trackgym.domain.model.RoutineUpdateMode.NEVER -> {
                                    viewModel.finishWorkout(
                                        notes = finishNotes,
                                        syncRoutine = false,
                                        detachRoutine = false,
                                        completedAtTimestamp = backdateCompletionTimestamp
                                    ) {
                                        onWorkoutFinished()
                                    }
                                }
                                com.kveld9.trackgym.domain.model.RoutineUpdateMode.ASK -> {
                                    showRoutineSyncPrompt = true
                                }
                            }
                        } else {
                            viewModel.finishWorkout(
                                notes = finishNotes,
                                syncRoutine = false,
                                detachRoutine = detachRoutineFromWorkout,
                                completedAtTimestamp = backdateCompletionTimestamp
                            ) {
                                onWorkoutFinished()
                            }
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

    if (showRoutineSyncPrompt) {
        val routineName = activeWorkout?.name.orEmpty()
        AlertDialog(
            onDismissRequest = {
                showRoutineSyncPrompt = false
                viewModel.finishWorkout(
                    notes = finishNotes,
                    syncRoutine = false,
                    detachRoutine = detachRoutineFromWorkout,
                    completedAtTimestamp = backdateCompletionTimestamp
                ) {
                    onWorkoutFinished()
                }
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = {
                Text(
                    text = stringResource(R.string.dialog_sync_routine_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.dialog_sync_routine_msg, routineName),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showRoutineSyncPrompt = false
                        viewModel.finishWorkout(
                            notes = finishNotes,
                            syncRoutine = true,
                            detachRoutine = detachRoutineFromWorkout,
                            completedAtTimestamp = backdateCompletionTimestamp
                        ) {
                            onWorkoutFinished()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(stringResource(R.string.action_update_routine), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showRoutineSyncPrompt = false
                        viewModel.finishWorkout(
                            notes = finishNotes,
                            syncRoutine = false,
                            detachRoutine = detachRoutineFromWorkout,
                            completedAtTimestamp = backdateCompletionTimestamp
                        ) {
                            onWorkoutFinished()
                        }
                    }
                ) {
                    Text(stringResource(R.string.action_keep_original), color = MaterialTheme.colorScheme.onSurfaceVariant)
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
        val initialWeightKg = plateCalcInitialWeightKg
            ?: we.sets.lastOrNull { it.weightKg > 0.0 }?.weightKg
            ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
            ?: DEFAULT_FALLBACK_WEIGHT_KG
        PlateCalculatorDialog(
            exerciseName = we.exercise.displayName(),
            weightUnit = weightUnit,
            initialWeightKg = initialWeightKg,
            onDismiss = {
                plateCalcExercise = null
                plateCalcInitialWeightKg = null
            }
        )
    }

    rpeCalcExercise?.let { we ->
        val best1RmRecord = allRecords
            .filter { it.exerciseId == we.exercise.id && it.recordType == RecordType.ESTIMATED_1RM }
            .maxOfOrNull { it.recordValue }

        val previousSets = previousSetsMap[we.exercise.id] ?: emptyList()
        val bestFromSets = (we.sets + previousSets)
            .filter { it.weightKg > 0.0 && it.reps > 0 }
            .maxOfOrNull { OneRepMaxCalculator.calculate1RM(it.weightKg, it.reps) }

        val resolved1Rm = best1RmRecord ?: bestFromSets ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg ?: 0.0

        val targetSet = rpeCalcSet ?: we.sets.firstOrNull { !it.isCompleted } ?: we.sets.lastOrNull()
        val initialReps = targetSet?.reps?.takeIf { it > 0 } ?: 5
        val initialRpe = targetSet?.rpe ?: 8.0

        RpeTargetWeightDialog(
            exerciseName = we.exercise.displayName(),
            initial1RmKg = resolved1Rm,
            initialReps = initialReps,
            initialRpe = initialRpe,
            weightUnit = weightUnit,
            onApplyTargetWeight = { suggestedKg, reps, rpe ->
                if (targetSet != null) {
                    viewModel.updateSet(targetSet.copy(weightKg = suggestedKg, reps = reps, rpe = rpe))
                } else {
                    viewModel.addSet(we.id, suggestedKg, reps)
                }
                rpeCalcExercise = null
                rpeCalcSet = null
            },
            onDismiss = {
                rpeCalcExercise = null
                rpeCalcSet = null
            }
        )
    }

    restConfigExercise?.let { we ->
        ExerciseRestDurationDialog(
            exerciseName = we.exercise.displayName(),
            currentRestSeconds = we.exercise.restDurationSeconds,
            defaultGlobalSeconds = restTotal,
            onSaveRest = { seconds ->
                viewModel.updateExerciseRestDuration(we.exercise.id, seconds)
                restConfigExercise = null
            },
            onDismiss = { restConfigExercise = null }
        )
    }

    supersetConfigExercise?.let { we ->
        SupersetGroupDialog(
            exerciseName = we.exercise.displayName(),
            currentGroup = we.supersetGroupId,
            onSelectGroup = { group ->
                viewModel.setExerciseSupersetGroup(we.id, group)
                supersetConfigExercise = null
            },
            onDismiss = { supersetConfigExercise = null }
        )
    }

    warmupRampExercise?.let { we ->
        val workingWeight = we.sets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
            ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
            ?: DEFAULT_FALLBACK_WEIGHT_KG
        val currentProtocol = WarmupGenerator.decodeProtocol(we.exercise.warmupRampProtocol)

        WarmupRampDialog(
            exerciseName = we.exercise.displayName(),
            workingWeightKg = workingWeight,
            currentProtocol = currentProtocol,
            weightUnit = weightUnit,
            onSaveProtocol = { newProtocol ->
                val encoded = newProtocol?.let { WarmupGenerator.encodeProtocol(it) }
                viewModel.updateExerciseWarmupProtocol(we.exercise.id, encoded)
                warmupRampExercise = null
            },
            onApplyAndAddSets = { protocolToApply ->
                val encoded = WarmupGenerator.encodeProtocol(protocolToApply)
                viewModel.updateExerciseWarmupProtocol(we.exercise.id, encoded)
                viewModel.addWarmupSets(we.id, workingWeight, protocolToApply)
                warmupRampExercise = null
            },
            onDismiss = { warmupRampExercise = null }
        )
    }

    autoProgressionExercise?.let { we ->
        val currentRule = AutoProgressionEngine.decode(we.exercise.autoProgressionRule)
        AutoProgressionDialog(
            exerciseName = we.exercise.displayName(),
            currentRule = currentRule,
            weightUnit = weightUnit,
            onSaveRule = { rule ->
                val encoded = rule?.let { AutoProgressionEngine.encode(it) }
                viewModel.updateExerciseAutoProgressionRule(we.exercise.id, encoded)
                autoProgressionExercise = null
            },
            onDismiss = { autoProgressionExercise = null }
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
    onDuplicateRoutine: ((Long) -> Unit)? = null,
    onToggleArchive: ((Long, Boolean) -> Unit)? = null,
    onImportRoutine: ((String, (Boolean) -> Unit) -> Unit)? = null,
    onMoveRoutineUp: ((Long) -> Unit)? = null,
    onMoveRoutineDown: ((Long) -> Unit)? = null,
    onMoveFolderUp: ((Long) -> Unit)? = null,
    onMoveFolderDown: ((Long) -> Unit)? = null,
    onUpdatePeriodization: ((Long, Boolean, PeriodizedCycle?) -> Unit)? = null,
    onAdvanceCycleWeek: ((Long) -> Unit)? = null,
    onPreviousCycleWeek: ((Long) -> Unit)? = null,
    onInstantiateProgram: ((ProgramRecommendation) -> Unit)? = null,
    onGenerateDeloadRoutine: ((Long, Double, Double) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var selectedFolderId by remember { mutableStateOf<Long?>(null) }
    var showArchived by remember { mutableStateOf(false) }
    var showReorderFoldersDialog by remember { mutableStateOf(false) }
    var showImportRoutineDialog by remember { mutableStateOf(false) }
    var showProgramFinderDialog by remember { mutableStateOf(false) }
    var routineToConfigurePeriodization by remember { mutableStateOf<Routine?>(null) }
    var routineToGenerateDeload by remember { mutableStateOf<Routine?>(null) }
    val baseRoutines = if (showArchived) {
        routines.filter { it.isArchived }
    } else {
        routines.filter { !it.isArchived }
    }
    val filteredRoutines = if (selectedFolderId == null) {
        baseRoutines
    } else {
        baseRoutines.filter { it.folderId == selectedFolderId }
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
            Column(modifier = Modifier.weight(1f)) {
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onInstantiateProgram != null) {
                    OutlinedButton(
                        onClick = { showProgramFinderDialog = true },
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.btn_program_wizard),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                if (onImportRoutine != null) {
                    OutlinedButton(
                        onClick = { showImportRoutineDialog = true },
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FileUpload,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.btn_import_routine),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Folder & Filter Chips
        val hasArchived = routines.any { it.isArchived }
        if (folders.isNotEmpty() || hasArchived) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = !showArchived && selectedFolderId == null,
                        onClick = {
                            showArchived = false
                            selectedFolderId = null
                        },
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
                            selected = !showArchived && selectedFolderId == folder.id,
                            onClick = {
                                showArchived = false
                                selectedFolderId = folder.id
                            },
                            label = { Text(folder.name) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                    if (hasArchived) {
                        FilterChip(
                            selected = showArchived,
                            onClick = {
                                showArchived = !showArchived
                            },
                            label = { Text(stringResource(R.string.routine_filter_archived)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
                if (folders.size > 1 && (onMoveFolderUp != null || onMoveFolderDown != null)) {
                    IconButton(
                        onClick = { showReorderFoldersDialog = true },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.action_reorder_folders),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
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
            val context = LocalContext.current
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                filteredRoutines.forEachIndexed { index, routine ->
                    RoutineCardItem(
                        routine = routine,
                        onStart = { onStartRoutine(routine.id) },
                        onDelete = { onDeleteRoutine(routine.id) },
                        onDuplicate = { onDuplicateRoutine?.invoke(routine.id) },
                        onGenerateDeload = if (onGenerateDeloadRoutine != null) {
                            { routineToGenerateDeload = routine }
                        } else null,
                        onToggleArchive = { onToggleArchive?.invoke(routine.id, !routine.isArchived) },
                        onConfigurePeriodization = { routineToConfigurePeriodization = routine },
                        onAdvanceWeek = { onAdvanceCycleWeek?.invoke(routine.id) },
                        onPreviousWeek = { onPreviousCycleWeek?.invoke(routine.id) },
                        onShare = {
                            val shareText = RoutineShareCodec.encodeToShareText(routine)
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, routine.name)
                            context.startActivity(shareIntent)
                        },
                        canMoveUp = index > 0,
                        canMoveDown = index < filteredRoutines.size - 1,
                        onMoveUp = { onMoveRoutineUp?.invoke(routine.id) },
                        onMoveDown = { onMoveRoutineDown?.invoke(routine.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(88.dp))
    }

    if (routineToConfigurePeriodization != null && onUpdatePeriodization != null) {
        val targetRoutine = routineToConfigurePeriodization!!
        PeriodizedCycleDialog(
            routineName = targetRoutine.name,
            initialIsPeriodized = targetRoutine.isPeriodized,
            initialCycle = targetRoutine.periodizedCycle,
            onSave = { isPeriodized, cycle ->
                onUpdatePeriodization(targetRoutine.id, isPeriodized, cycle)
                routineToConfigurePeriodization = null
            },
            onDismiss = { routineToConfigurePeriodization = null }
        )
    }

    if (showReorderFoldersDialog && (onMoveFolderUp != null || onMoveFolderDown != null)) {
        ReorderFoldersDialog(
            folders = folders,
            onMoveFolderUp = { onMoveFolderUp?.invoke(it) },
            onMoveFolderDown = { onMoveFolderDown?.invoke(it) },
            onDismiss = { showReorderFoldersDialog = false }
        )
    }

    if (showImportRoutineDialog && onImportRoutine != null) {
        ImportRoutineDialog(
            onDismiss = { showImportRoutineDialog = false },
            onImport = onImportRoutine
        )
    }

    if (showProgramFinderDialog && onInstantiateProgram != null) {
        ProgramFinderDialog(
            onImportProgram = { program ->
                onInstantiateProgram(program)
                showProgramFinderDialog = false
            },
            onDismiss = { showProgramFinderDialog = false }
        )
    }

    if (routineToGenerateDeload != null && onGenerateDeloadRoutine != null) {
        val targetRoutine = routineToGenerateDeload!!
        DeloadRoutineDialog(
            routineName = targetRoutine.name,
            onGenerate = { loadPct, volPct ->
                onGenerateDeloadRoutine(targetRoutine.id, loadPct, volPct)
                routineToGenerateDeload = null
            },
            onDismiss = { routineToGenerateDeload = null }
        )
    }
}

@Composable
fun RoutineCardItem(
    routine: Routine,
    onStart: () -> Unit,
    onDelete: () -> Unit,
    onDuplicate: (() -> Unit)? = null,
    onGenerateDeload: (() -> Unit)? = null,
    onToggleArchive: (() -> Unit)? = null,
    onConfigurePeriodization: (() -> Unit)? = null,
    onAdvanceWeek: (() -> Unit)? = null,
    onPreviousWeek: (() -> Unit)? = null,
    onShare: (() -> Unit)? = null,
    canMoveUp: Boolean = false,
    canMoveDown: Boolean = false,
    onMoveUp: (() -> Unit)? = null,
    onMoveDown: (() -> Unit)? = null
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = routine.name,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (routine.isPeriodized && routine.periodizedCycle != null) {
                        val currentConfig = routine.periodizedCycle.getCurrentWeekConfig()
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            color = if (currentConfig.isDeload) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.badge_periodized_block, currentConfig.weekNumber, routine.periodizedCycle.totalWeeks),
                                color = if (currentConfig.isDeload) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    if (routine.isArchived) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.routine_archived_badge),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
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
                if (routine.isPeriodized && routine.periodizedCycle != null) {
                    val currentConfig = routine.periodizedCycle.getCurrentWeekConfig()
                    val volPct = (currentConfig.volumeMultiplier * 100).toInt()
                    val intPct = (currentConfig.intensityMultiplier * 100).toInt()
                    val deltaInt = intPct - 100
                    val intSign = if (deltaInt > 0) "+$deltaInt%" else "$deltaInt%"
                    val rpeStr = currentConfig.targetRpe?.let { " • RPE $it" } ?: ""
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${currentConfig.phase.name} • $volPct% Vol • $intSign Load$rpeStr",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                        if (canMoveUp && onMoveUp != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_move_up)) },
                                leadingIcon = { Icon(Icons.Default.KeyboardArrowUp, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onMoveUp()
                                }
                            )
                        }
                        if (canMoveDown && onMoveDown != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_move_down)) },
                                leadingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onMoveDown()
                                }
                            )
                        }
                        if (onConfigurePeriodization != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_configure_periodization)) },
                                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onConfigurePeriodization()
                                }
                            )
                        }
                        if (routine.isPeriodized && onAdvanceWeek != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_advance_cycle_week)) },
                                leadingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onAdvanceWeek()
                                }
                            )
                        }
                        if (routine.isPeriodized && onPreviousWeek != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_previous_cycle_week)) },
                                leadingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onPreviousWeek()
                                }
                            )
                        }
                        if (onDuplicate != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_duplicate_routine)) },
                                leadingIcon = { Icon(Icons.Default.ContentCopy, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onDuplicate()
                                }
                            )
                        }
                        if (onGenerateDeload != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_generate_deload)) },
                                leadingIcon = { Icon(Icons.Default.Refresh, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onGenerateDeload()
                                }
                            )
                        }
                        if (onShare != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_share_routine)) },
                                leadingIcon = { Icon(Icons.Default.Share, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onShare()
                                }
                            )
                        }
                        if (onToggleArchive != null) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        if (routine.isArchived) stringResource(R.string.menu_unarchive_routine)
                                        else stringResource(R.string.menu_archive_routine)
                                    )
                                },
                                leadingIcon = { Icon(Icons.Default.Bookmark, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onToggleArchive()
                                }
                            )
                        }
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
private fun ReorderFoldersDialog(
    folders: List<RoutineFolder>,
    onMoveFolderUp: (Long) -> Unit,
    onMoveFolderDown: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.dialog_reorder_folders_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                folders.forEachIndexed { index, folder ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = folder.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Row {
                            IconButton(
                                onClick = { onMoveFolderUp(folder.id) },
                                enabled = index > 0,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                            }
                            IconButton(
                                onClick = { onMoveFolderDown(folder.id) },
                                enabled = index < folders.size - 1,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(android.R.string.ok))
            }
        }
    )
}

@Composable
private fun ImportRoutineDialog(
    onDismiss: () -> Unit,
    onImport: (String, (Boolean) -> Unit) -> Unit
) {
    var shareText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isImporting by remember { mutableStateOf(false) }
    val invalidTokenMessage = stringResource(R.string.toast_routine_import_invalid)

    AlertDialog(
        onDismissRequest = { if (!isImporting) onDismiss() },
        title = {
            Text(
                text = stringResource(R.string.dialog_import_routine_title),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.dialog_import_routine_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = shareText,
                    onValueChange = {
                        shareText = it
                        errorMessage = null
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.dialog_import_routine_placeholder),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    isError = errorMessage != null,
                    supportingText = {
                        if (errorMessage != null) {
                            Text(errorMessage ?: "", color = MaterialTheme.colorScheme.error)
                        }
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (shareText.isBlank()) return@Button
                    isImporting = true
                    onImport(shareText) { success ->
                        isImporting = false
                        if (success) {
                            onDismiss()
                        } else {
                            errorMessage = invalidTokenMessage
                        }
                    }
                },
                enabled = shareText.isNotBlank() && !isImporting
            ) {
                Text(stringResource(R.string.btn_import_routine))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isImporting
            ) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
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
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
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
    onDeleteSet: (WorkoutSet) -> Unit,
    onRemoveExercise: () -> Unit,
    showInlinePlates: Boolean = true,
    onOpenPlateCalculator: () -> Unit = {},
    onOpenPlateCalculatorForWeight: (Double) -> Unit = {},
    onOpenRpeCalculator: (WorkoutSet?) -> Unit = {},
    onAddWarmupSets: () -> Unit = {},
    onConfigureWarmupRamp: () -> Unit = {},
    onConfigureAutoProgression: () -> Unit = {},
    onSetRestDuration: () -> Unit = {},
    onSetSupersetGroup: () -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }

    val supersetColor = when (workoutExercise.supersetGroupId) {
        "A" -> MaterialTheme.colorScheme.primary
        "B" -> GymBlue
        "C" -> GymWarmupAmber
        "D" -> MaterialTheme.colorScheme.tertiary
        else -> null
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = if (supersetColor != null) {
            BorderStroke(2.dp, supersetColor)
        } else {
            CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
        }
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${stringResource(workoutExercise.exercise.muscleGroup.nameRes)} • ${stringResource(workoutExercise.exercise.category.nameRes)}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        workoutExercise.exercise.restDurationSeconds?.let { restSec ->
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier.clickable { onSetRestDuration() }
                            ) {
                                Text(
                                    text = stringResource(R.string.exercise_rest_badge, restSec),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.exercise.warmupRampProtocol?.let {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GymWarmupAmber.copy(alpha = 0.2f),
                                border = BorderStroke(1.dp, GymWarmupAmber),
                                modifier = Modifier.clickable { onConfigureWarmupRamp() }
                            ) {
                                Text(
                                    text = stringResource(R.string.badge_custom_warmup),
                                    color = GymWarmupAmber,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.exercise.autoProgressionRule?.let {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                modifier = Modifier.clickable { onConfigureAutoProgression() }
                            ) {
                                Text(
                                    text = "Auto",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.supersetGroupId?.let { groupId ->
                            val badgeColor = supersetColor ?: MaterialTheme.colorScheme.primary
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = badgeColor.copy(alpha = 0.15f),
                                border = BorderStroke(1.dp, badgeColor),
                                modifier = Modifier.clickable { onSetSupersetGroup() }
                            ) {
                                Text(
                                    text = stringResource(R.string.superset_label, groupId),
                                    color = badgeColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
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
                                text = { Text(stringResource(R.string.menu_configure_warmup_ramp), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onConfigureWarmupRamp()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_auto_progression_rules), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onConfigureAutoProgression()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_set_rest_duration), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onSetRestDuration()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        if (workoutExercise.supersetGroupId != null)
                                            stringResource(R.string.menu_ungroup_superset)
                                        else
                                            stringResource(R.string.menu_group_superset),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    menuExpanded = false
                                    onSetSupersetGroup()
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
                                text = { Text(stringResource(R.string.action_rpe_target_calculator), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenRpeCalculator(null)
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

            val autoProgressionRule = remember(workoutExercise.exercise.autoProgressionRule) {
                AutoProgressionEngine.decode(workoutExercise.exercise.autoProgressionRule)
            }
            val autoProgressionResult = remember(autoProgressionRule, previousSets, workoutExercise.sets) {
                if (autoProgressionRule != null && autoProgressionRule.enabled) {
                    val workingWeight = workoutExercise.sets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                        ?: workoutExercise.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
                        ?: previousSets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                        ?: 0.0
                    val historySessions = if (previousSets.isNotEmpty()) listOf(previousSets) else emptyList()
                    AutoProgressionEngine.evaluate(autoProgressionRule, historySessions, workingWeight)
                } else null
            }

            if (autoProgressionResult != null && autoProgressionResult.type != ProgressionDecisionType.MAINTAIN) {
                Spacer(modifier = Modifier.height(8.dp))
                AutoProgressionBadge(
                    result = autoProgressionResult,
                    weightUnit = weightUnit,
                    onApplyLoad = { targetKg ->
                        workoutExercise.sets.forEach { set ->
                            if (set.setType != SetType.WARMUP && !set.isCompleted) {
                                onUpdateSet(set.copy(weightKg = targetKg))
                            }
                        }
                    }
                )
            } else {
                val overloadRec = remember(previousSets, weightUnit) {
                    ProgressiveOverloadEngine.computeRecommendation(previousSets, weightUnit)
                }
                if (overloadRec != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressiveOverloadHintBadge(recommendation = overloadRec)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sets Table Header
            val isCardioExercise = workoutExercise.exercise.category == ExerciseCategory.CARDIO ||
                workoutExercise.sets.any { it.setType == SetType.CARDIO }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.table_header_set), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
                val col1Header = if (isCardioExercise) distanceUnit.symbol.uppercase() else weightUnit.symbol.uppercase()
                Text(col1Header, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                val hasDurationSets = workoutExercise.sets.any { it.setType == SetType.DURATION }
                val col2Header = when {
                    isCardioExercise -> stringResource(R.string.cardio_time_label).uppercase()
                    hasDurationSets -> stringResource(R.string.table_header_reps_or_time)
                    else -> stringResource(R.string.table_header_reps)
                }
                Text(
                    text = col2Header,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(stringResource(R.string.table_header_complete), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sets rows
            workoutExercise.sets.forEach { set ->
                val prevSet = previousSets.firstOrNull { it.setNumber == set.setNumber }
                    ?: previousSets.getOrNull(set.setNumber - 1)
                key(set.id) {
                    SwipeableSetRow(
                        set = set,
                        weightUnit = weightUnit,
                        distanceUnit = distanceUnit,
                        userBodyWeightKg = userBodyWeightKg,
                        previousSet = prevSet,
                        showInlinePlates = showInlinePlates,
                        onOpenPlateCalculator = { targetKg -> onOpenPlateCalculatorForWeight(targetKg) },
                        onOpenRpeCalculator = { onOpenRpeCalculator(set) },
                        onUpdateSet = onUpdateSet,
                        onToggleComplete = { w, r -> onToggleComplete(set, w, r) },
                        onDeleteSet = { onDeleteSet(set) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
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
fun ProgressiveOverloadHintBadge(
    recommendation: ProgressiveOverloadRecommendation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.FitnessCenter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(
                R.string.overload_hint_body,
                recommendation.previousSummary,
                recommendation.suggestedTarget
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AutoProgressionBadge(
    result: AutoProgressionResult,
    weightUnit: WeightUnit,
    onApplyLoad: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val isProgression = result.type == ProgressionDecisionType.PROGRESSION
    val badgeColor = if (isProgression) MaterialTheme.colorScheme.primary else GymWarmupAmber
    val icon = if (isProgression) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    val title = if (isProgression) {
        stringResource(R.string.auto_progression_badge_progression, weightUnit.format(result.recommendedWeightKg))
    } else {
        stringResource(R.string.auto_progression_badge_deload, weightUnit.format(result.recommendedWeightKg))
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = badgeColor.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, badgeColor.copy(alpha = 0.5f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = badgeColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor
                    )
                    Text(
                        text = stringResource(result.explanationRes),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        lineHeight = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = badgeColor,
                modifier = Modifier.clickable { onApplyLoad(result.recommendedWeightKg) }
            ) {
                Text(
                    text = stringResource(R.string.auto_progression_action_apply_load),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isProgression) MaterialTheme.colorScheme.onPrimary else Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableSetRow(
    set: WorkoutSet,
    weightUnit: WeightUnit,
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
    previousSet: WorkoutSet?,
    showInlinePlates: Boolean = true,
    onOpenPlateCalculator: (Double) -> Unit = {},
    onOpenRpeCalculator: () -> Unit = {},
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (Double, Int) -> Unit,
    onDeleteSet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                onDeleteSet()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            val color = if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                MaterialTheme.colorScheme.errorContainer
            } else {
                Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.action_delete_set),
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        },
        modifier = modifier
    ) {
        SetRowItem(
            set = set,
            weightUnit = weightUnit,
            distanceUnit = distanceUnit,
            userBodyWeightKg = userBodyWeightKg,
            previousSet = previousSet,
            showInlinePlates = showInlinePlates,
            onOpenPlateCalculator = onOpenPlateCalculator,
            onOpenRpeCalculator = onOpenRpeCalculator,
            onUpdateSet = onUpdateSet,
            onToggleComplete = onToggleComplete,
            onDeleteSet = onDeleteSet
        )
    }
}

@Composable
fun SetRowItem(
    set: WorkoutSet,
    weightUnit: WeightUnit = WeightUnit.KG,
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
    previousSet: WorkoutSet? = null,
    showInlinePlates: Boolean = true,
    onOpenPlateCalculator: (Double) -> Unit = {},
    onOpenRpeCalculator: () -> Unit = {},
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
    val isDuration = set.setType == SetType.DURATION
    val isCardio = set.setType == SetType.CARDIO
    var isStopwatchRunning by remember { mutableStateOf(false) }

    var distanceText by remember(set.id, set.distanceKm) {
        mutableStateOf(
            if (set.distanceKm != null && set.distanceKm > 0.0) {
                val distDisplay = distanceUnit.fromKm(set.distanceKm)
                if (distDisplay % 1.0 == 0.0) distDisplay.toInt().toString()
                else String.format(Locale.US, "%.2f", distDisplay)
            } else ""
        )
    }

    LaunchedEffect(isStopwatchRunning) {
        if (isStopwatchRunning) {
            while (isStopwatchRunning) {
                kotlinx.coroutines.delay(1000)
                val current = (set.durationSeconds ?: set.reps) + 1
                val distKm = set.distanceKm ?: 0.0
                val cal = CardioCalculator.calculateCaloriesBurned(current, userBodyWeightKg)
                onUpdateSet(set.copy(durationSeconds = current, reps = current, caloriesBurned = if (isCardio) cal else set.caloriesBurned))
            }
        }
    }

    val ghostWeightDisplay = if (previousSet != null && previousSet.weightKg > 0.0) {
        weightUnit.formatValue(previousSet.weightKg)
    } else null

    val effectiveWeightDisplay = weightText.replace(',', '.').toDoubleOrNull()
        ?: if (set.weightKg > 0.0) weightUnit.fromKg(set.weightKg)
        else (ghostWeightDisplay?.replace(',', '.')?.toDoubleOrNull() ?: 0.0)

    val compactPlates = remember(effectiveWeightDisplay, weightUnit, isCardio, set.setType, showInlinePlates) {
        if (showInlinePlates && !isCardio && set.setType != SetType.CARDIO && set.setType != SetType.DURATION &&
            set.setType != SetType.BODYWEIGHT_LOAD && set.setType != SetType.BODYWEIGHT_ASSISTED &&
            effectiveWeightDisplay > 0.0
        ) {
            com.kveld9.trackgym.domain.calculator.PlateCalculator.formatCompactPlatesPerSide(
                targetWeight = effectiveWeightDisplay,
                barWeight = com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultBarWeight(weightUnit),
                availablePlates = com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultPlates(weightUnit),
                unit = weightUnit
            )
        } else null
    }
    val ghostDistanceDisplay = if (previousSet != null && previousSet.distanceKm != null && previousSet.distanceKm > 0.0) {
        val distDisplay = distanceUnit.fromKm(previousSet.distanceKm)
        if (distDisplay % 1.0 == 0.0) distDisplay.toInt().toString()
        else String.format(Locale.US, "%.2f", distDisplay)
    } else null
    val ghostRepsDisplay = if (previousSet != null && previousSet.reps > 0) {
        if (isDuration || isCardio) com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(previousSet.durationSeconds ?: previousSet.reps)
        else previousSet.reps.toString()
    } else null

    var repsText by remember(set.id, set.reps, set.durationSeconds, isDuration, isCardio) {
        mutableStateOf(
            if (isDuration || isCardio) {
                val sec = set.durationSeconds ?: set.reps
                if (sec > 0) com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(sec) else ""
            } else {
                if (set.reps > 0) set.reps.toString() else ""
            }
        )
    }

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
        SetType.DURATION -> GymBlue
        SetType.BODYWEIGHT_LOAD -> GymWarmupAmber
        SetType.BODYWEIGHT_ASSISTED -> MaterialTheme.colorScheme.tertiary
        SetType.AMRAP -> GymAmrapCrimson
        SetType.CARDIO -> GymCardioTeal
    }

    val badgeLabel = when (set.setType) {
        SetType.NORMAL -> "${set.setNumber}"
        SetType.WARMUP -> "W"
        SetType.DROP -> "D"
        SetType.FAILURE -> "F"
        SetType.MYO_REPS -> "M"
        SetType.DURATION -> "T"
        SetType.BODYWEIGHT_LOAD -> "B+"
        SetType.BODYWEIGHT_ASSISTED -> "B-"
        SetType.AMRAP -> "${set.setNumber}+"
        SetType.CARDIO -> "C"
    }

    var showQuickAdjust by remember { mutableStateOf(false) }
    var showRpePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (set.isCompleted) MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surfaceContainer)
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
                                            SetType.DURATION -> GymBlue
                                            SetType.BODYWEIGHT_LOAD -> GymWarmupAmber
                                            SetType.BODYWEIGHT_ASSISTED -> MaterialTheme.colorScheme.tertiary
                                            SetType.AMRAP -> GymAmrapCrimson
                                            SetType.CARDIO -> GymCardioTeal
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
                                if (type != SetType.DURATION) {
                                    isStopwatchRunning = false
                                }
                                onUpdateSet(set.copy(setType = type))
                            }
                        )
                    }
                }
            }

            // Weight / Distance Input with Ghost Placeholder & Micro-load Toggle
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                if (isCardio) {
                    OutlinedTextField(
                        value = distanceText,
                        onValueChange = { input ->
                            distanceText = input
                            val parsedDist = input.replace(',', '.').toDoubleOrNull()
                            val distKm = parsedDist?.let { distanceUnit.toKm(it) }
                            val durSec = set.durationSeconds ?: set.reps
                            val cal = CardioCalculator.calculateCaloriesBurned(durSec, userBodyWeightKg)
                            onUpdateSet(set.copy(distanceKm = distKm, caloriesBurned = cal))
                        },
                        placeholder = {
                            Text(
                                text = ghostDistanceDisplay ?: "0.0",
                                color = if (ghostDistanceDisplay != null) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                }
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
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
                } else {
                    OutlinedTextField(
                        value = weightText,
                        onValueChange = { input ->
                            weightText = input
                            val parsedDisplay = input.replace(',', '.').toDoubleOrNull() ?: 0.0
                            val inKg = weightUnit.toKg(parsedDisplay)
                            onUpdateSet(set.copy(weightKg = inKg))
                        },
                        placeholder = {
                            val weightPlaceholder = when (set.setType) {
                                SetType.BODYWEIGHT_LOAD -> ghostWeightDisplay ?: "+0"
                                SetType.BODYWEIGHT_ASSISTED -> ghostWeightDisplay ?: "-0"
                                else -> ghostWeightDisplay ?: "0"
                            }
                            Text(
                                text = weightPlaceholder,
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
            }

            // Reps / Time Input with Ghost Placeholder & In-set Stopwatch
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                OutlinedTextField(
                    value = repsText,
                    onValueChange = { input ->
                        repsText = input
                        if (isDuration || isCardio) {
                            val parsedSec = com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(input)
                            val distKm = set.distanceKm ?: 0.0
                            val cal = if (isCardio) CardioCalculator.calculateCaloriesBurned(parsedSec, userBodyWeightKg) else set.caloriesBurned
                            onUpdateSet(set.copy(durationSeconds = parsedSec, reps = parsedSec, caloriesBurned = cal))
                        } else {
                            val parsed = input.toIntOrNull() ?: 0
                            onUpdateSet(set.copy(reps = parsed))
                        }
                    },
                    placeholder = {
                        val repsPlaceholder = when {
                            isDuration || isCardio -> "00:00"
                            set.setType == SetType.AMRAP -> (ghostRepsDisplay?.let { "$it+" } ?: "0+")
                            else -> ghostRepsDisplay ?: "0"
                        }
                        Text(
                            text = repsPlaceholder,
                            color = if (ghostRepsDisplay != null || isDuration || isCardio) {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                            }
                        )
                    },
                    trailingIcon = if (isDuration || isCardio) {
                        {
                            IconButton(
                                onClick = { isStopwatchRunning = !isStopwatchRunning },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = if (isStopwatchRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isStopwatchRunning) stringResource(R.string.action_stop_stopwatch) else stringResource(R.string.action_start_stopwatch),
                                    tint = if (isStopwatchRunning) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    } else null,
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
                        isStopwatchRunning = false
                        val finalWeightKg = if (isCardio) {
                            0.0
                        } else {
                            val typedDisplay = weightText.replace(',', '.').toDoubleOrNull()
                            if (typedDisplay != null) {
                                weightUnit.toKg(typedDisplay)
                            } else if (set.weightKg > 0.0) {
                                set.weightKg
                            } else if (previousSet != null && previousSet.weightKg > 0.0) {
                                previousSet.weightKg
                            } else {
                                0.0
                            }
                        }

                        val finalReps = if (isDuration || isCardio) {
                            val parsedSec = com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(repsText)
                            if (parsedSec > 0) parsedSec
                            else (set.durationSeconds ?: set.reps)
                        } else {
                            val typedReps = repsText.toIntOrNull()
                            if (typedReps != null) {
                                typedReps
                            } else if (set.reps > 0) {
                                set.reps
                            } else if (previousSet != null && previousSet.reps > 0) {
                                previousSet.reps
                            } else {
                                0
                            }
                        }

                        if (isCardio) {
                            val parsedDist = distanceText.replace(',', '.').toDoubleOrNull()
                                ?: ghostDistanceDisplay?.replace(',', '.')?.toDoubleOrNull()
                            val distKm = parsedDist?.let { distanceUnit.toKm(it) }
                            val cal = CardioCalculator.calculateCaloriesBurned(finalReps, userBodyWeightKg)
                            onUpdateSet(set.copy(distanceKm = distKm, caloriesBurned = cal))
                        } else {
                            if (weightText.isBlank()) {
                                weightText = weightUnit.formatValue(finalWeightKg)
                            }
                        }

                        if (repsText.isBlank() && finalReps > 0) {
                            repsText = if (isDuration || isCardio) {
                                com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(finalReps)
                            } else {
                                finalReps.toString()
                            }
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SetRpeChip(
                rpe = set.rpe,
                onClick = { showRpePicker = true }
            )

            if (compactPlates != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier
                        .clickable {
                            val inKg = weightUnit.toKg(effectiveWeightDisplay)
                            onOpenPlateCalculator(inKg)
                        }
                        .padding(vertical = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = stringResource(R.string.action_plate_calculator),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = compactPlates,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            if (isCardio) {
                val distanceVal = set.distanceKm ?: distanceText.replace(',', '.').toDoubleOrNull()?.let { distanceUnit.toKm(it) } ?: 0.0
                val durationSec = if (set.durationSeconds != null && set.durationSeconds > 0) set.durationSeconds else com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(repsText)
                if (distanceVal > 0.0 && durationSec > 0) {
                    val paceFormatted = CardioCalculator.formatPace(distanceVal, durationSec, distanceUnit)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cardio_pace_format, paceFormatted),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                val calories = set.caloriesBurned ?: if (durationSec > 0) CardioCalculator.calculateCaloriesBurned(durationSec, userBodyWeightKg) else 0
                if (calories > 0) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cardio_calories_format, calories),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
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
            onOpenTargetCalculator = onOpenRpeCalculator,
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

    var includeCollars by remember { mutableStateOf(false) }
    val defaultCollars = remember(weightUnit) { com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultCollarsWeight(weightUnit) }

    val allDefaultPlates = remember(weightUnit) {
        com.kveld9.trackgym.domain.calculator.PlateCalculator.defaultPlates(weightUnit)
    }
    var activePlates by remember(weightUnit) { mutableStateOf(allDefaultPlates.toSet()) }

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
                            modifier = Modifier.clickable { textInput = seconds.toString() }
                        ) {
                            Text(
                                text = "${seconds}s",
                                color = if (textInput == seconds.toString()) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
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

