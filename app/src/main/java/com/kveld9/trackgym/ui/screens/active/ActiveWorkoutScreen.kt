package com.kveld9.trackgym.ui.screens.active

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import com.kveld9.trackgym.ui.theme.GymMotionTokens
import com.kveld9.trackgym.ui.theme.subtleBorder
import com.kveld9.trackgym.domain.model.StandardContextTag
import com.kveld9.trackgym.domain.model.WorkoutContextTagParser
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import com.kveld9.trackgym.ui.theme.screenEnterTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import com.kveld9.trackgym.domain.calculator.OvertrainingDetector
import com.kveld9.trackgym.domain.calculator.OneRepMaxCalculator
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.ui.components.OvertrainingWarningBanner
import com.kveld9.trackgym.ui.components.PrCelebrationBanner
import com.kveld9.trackgym.domain.calculator.WarmupGenerator
import com.kveld9.trackgym.domain.calculator.AutoProgressionEngine
import com.kveld9.trackgym.ui.components.AutoProgressionDialog
import com.kveld9.trackgym.ui.components.ExerciseHistoryDialog
import com.kveld9.trackgym.ui.components.ExerciseTechniqueDialog
import com.kveld9.trackgym.ui.components.RpeTargetWeightDialog
import com.kveld9.trackgym.ui.components.WarmupRampDialog
import com.kveld9.trackgym.ui.components.WorkoutSessionNotesCard
import com.kveld9.trackgym.ui.components.HapticWheelSetPickerDialog
import com.kveld9.trackgym.ui.util.LocalActiveGymProfile
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
    val timerSecondsState = viewModel.timerSeconds.collectAsStateWithLifecycle()
    val recentPr by viewModel.recentlyUnlockedPr.collectAsStateWithLifecycle()
    val allExercises by viewModel.allExercises.collectAsStateWithLifecycle()
    val weightUnit by viewModel.weightUnit.collectAsStateWithLifecycle()
    val distanceUnit by viewModel.distanceUnit.collectAsStateWithLifecycle()
    val userBodyWeight by viewModel.userBodyWeight.collectAsStateWithLifecycle()
    val showInlinePlates by viewModel.showInlinePlates.collectAsStateWithLifecycle()
    val allRecords by viewModel.allRecords.collectAsStateWithLifecycle()
    val gymProfiles by viewModel.gymProfiles.collectAsStateWithLifecycle()
    val activeGymProfile by viewModel.activeGymProfile.collectAsStateWithLifecycle()

    // Rest Timer state
    val restRemainingState = viewModel.restTimerRemainingSeconds.collectAsStateWithLifecycle()
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
    val keepScreenOn by viewModel.keepScreenOn.collectAsStateWithLifecycle()
    val getReadySeconds by viewModel.getReadySeconds.collectAsStateWithLifecycle()

    DisposableEffect(activeWorkout != null, keepScreenOn) {
        val activity = context.findActivity()
        if (activeWorkout != null && keepScreenOn) {
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        onDispose {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

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

    LaunchedEffect(restTotal) {
        snapshotFlow { restRemainingState.value }.collect { remaining ->
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
    var selectedContextTags by remember { mutableStateOf<List<String>>(emptyList()) }
    var detachRoutineFromWorkout by remember { mutableStateOf(false) }
    var backdateCompletionTimestamp by remember { mutableStateOf<Long?>(null) }
    var routineNameInput by remember { mutableStateOf("") }
    var plateCalcExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var plateCalcInitialWeightKg by remember { mutableStateOf<Double?>(null) }
    var wheelPickerTarget by remember { mutableStateOf<Pair<WorkoutExercise, WorkoutSet>?>(null) }
    var rpeCalcExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var rpeCalcSet by remember { mutableStateOf<WorkoutSet?>(null) }
    var restConfigExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var warmupRampExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var autoProgressionExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var supersetConfigExercise by remember { mutableStateOf<WorkoutExercise?>(null) }
    var exerciseToSwap by remember { mutableStateOf<WorkoutExercise?>(null) }
    var exerciseForTechniqueGuide by remember { mutableStateOf<Exercise?>(null) }
    var exerciseForHistory by remember { mutableStateOf<Exercise?>(null) }
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

    var isHydrating by rememberSaveable {
        mutableStateOf(activeWorkout == null && routines.isEmpty() && allExercises.isEmpty())
    }

    LaunchedEffect(Unit) {
        launch {
            viewModel.routines.collect { list ->
                if (list.isNotEmpty()) {
                    isHydrating = false
                }
            }
        }
        launch {
            viewModel.allExercises.collect { list ->
                if (list.isNotEmpty()) {
                    isHydrating = false
                }
            }
        }
        launch {
            viewModel.activeWorkout.collect { workout ->
                if (workout != null) {
                    isHydrating = false
                }
            }
        }
        launch {
            delay(GymMotionTokens.HydrationTimeoutMs)
            isHydrating = false
        }
    }

    LaunchedEffect(activeWorkout) {
        if (activeWorkout != null) {
            isHydrating = false
        }
    }

    CompositionLocalProvider(LocalActiveGymProfile provides activeGymProfile) {
        Crossfade(
            targetState = isHydrating && activeWorkout == null,
            animationSpec = tween(durationMillis = GymMotionTokens.HydrationTransitionMs),
            label = "ActiveWorkoutHydrationCrossfade"
        ) { hydrating ->
            if (hydrating) {
                ActiveWorkoutSkeleton(modifier = modifier)
            } else if (activeWorkout == null) {
            val copySuffix = stringResource(R.string.routine_copy_suffix)
        val defaultWorkoutTitle = stringResource(R.string.workout_default_title)
        val deloadSuffix = stringResource(R.string.deload_routine_suffix)
        EmptyWorkoutDashboard(
            routines = routines,
            folders = folders,
            allExercises = allExercises,
            onStartWorkout = { viewModel.startWorkout(defaultWorkoutTitle) },
            onStartRoutine = { routineId -> viewModel.startWorkoutFromRoutine(routineId) },
            onDeleteRoutine = { routineId -> viewModel.deleteRoutine(routineId) },
            onCreateRoutine = { name, onCreated ->
                viewModel.createEmptyRoutine(name, onCreated = onCreated)
            },
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
            onGenerateDeloadRoutine = { routineId, loadPct, volPct -> viewModel.generateDeloadRoutine(routineId, loadPct, volPct, deloadSuffix) },
            onAddExerciseToRoutine = { routineId, exerciseId ->
                viewModel.addExerciseToRoutine(routineId, exerciseId)
            },
            onRemoveExerciseFromRoutine = { routineExerciseId ->
                viewModel.removeExerciseFromRoutine(routineExerciseId)
            },
            onUpdateRoutineExercise = { routineExerciseId, sets, weight, reps ->
                viewModel.updateRoutineExercise(routineExerciseId, sets, weight, reps)
            },
            onMoveRoutineExercise = { routineId, fromIndex, toIndex ->
                viewModel.moveRoutineExercise(routineId, fromIndex, toIndex)
            },
            modifier = modifier.screenEnterTransition()
        )
    } else {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                ActiveWorkoutTopBar(
                    timerSeconds = { timerSecondsState.value },
                    scrollBehavior = scrollBehavior,
                    onFinishClick = { showFinishDialog = true },
                    onCancelClick = { showDiscardDialog = true },
                    onSaveAsRoutineClick = {
                        routineNameInput = activeWorkout?.name.orEmpty()
                        showSaveRoutineDialog = true
                    }
                )
            },
            bottomBar = {
                val restRemaining = restRemainingState.value
                if (restRemaining != null) {
                    FloatingRestTimer(
                        remainingSeconds = restRemaining ?: 0,
                        totalSeconds = restTotal,
                        isRunning = restIsRunning,
                        onAddSeconds = { viewModel.addRestSeconds(it) },
                        onPauseResume = {
                            if (restIsRunning) viewModel.pauseRestTimer() else viewModel.resumeRestTimer()
                        },
                        onSkip = { viewModel.stopRestTimer() },
                        modifier = Modifier.padding(bottom = 80.dp)
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            modifier = modifier
                .screenEnterTransition()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
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
                    itemsIndexed(
                        items = exercisesList,
                        key = { _, it -> it.id },
                        contentType = { _, _ -> "workout_exercise_card" }
                    ) { index, we ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItem()
                        ) {
                            WorkoutExerciseCard(
                                workoutExercise = we,
                                weightUnit = weightUnit,
                                distanceUnit = distanceUnit,
                                userBodyWeightKg = userBodyWeight,
                                previousSets = previousSetsMap[we.exercise.id].orEmpty(),
                                getReadySeconds = getReadySeconds,
                                audioCuePlayer = audioCuePlayer,
                                timerSoundName = timerSoundName,
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
                                onOpenWheelPicker = { targetSet -> wheelPickerTarget = Pair(we, targetSet) },
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
                                    val workingWeight = we.sets
                                        .firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                                        ?: we.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
                                        ?: DEFAULT_FALLBACK_WEIGHT_KG
                                    viewModel.addWarmupSets(we.id, workingWeight)
                                },
                                onConfigureWarmupRamp = { warmupRampExercise = we },
                                onConfigureAutoProgression = { autoProgressionExercise = we },
                                onOpenTechniqueGuide = { exerciseForTechniqueGuide = we.exercise },
                                onOpenExerciseHistory = { exerciseForHistory = we.exercise }
                            )
                        }
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
                        Spacer(modifier = Modifier.height(120.dp))
                    }
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
        val currentEx = exerciseToSwap?.exercise
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
                exerciseToSubstitute = currentEx,
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
                            unfocusedBorderColor = MaterialTheme.colorScheme.subtleBorder,
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
                            unfocusedBorderColor = MaterialTheme.colorScheme.subtleBorder,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.label_context_tags),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        StandardContextTag.entries.forEach { tag ->
                            val isSelected = selectedContextTags.contains(tag.tagKey)
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    selectedContextTags = WorkoutContextTagParser.toggleTag(selectedContextTags, tag.tagKey)
                                },
                                label = { Text(stringResource(tag.labelRes), fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }

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
                        val finalNotes = WorkoutContextTagParser.embedTagsIntoNotes(selectedContextTags, finishNotes)
                        val linkedRoutineId = activeWorkout?.routineId
                        if (linkedRoutineId != null && !detachRoutineFromWorkout) {
                            when (routineUpdateMode) {
                                com.kveld9.trackgym.domain.model.RoutineUpdateMode.ALWAYS -> {
                                    viewModel.finishWorkout(
                                        notes = finalNotes,
                                        syncRoutine = true,
                                        detachRoutine = false,
                                        completedAtTimestamp = backdateCompletionTimestamp
                                    ) {
                                        onWorkoutFinished()
                                    }
                                }
                                com.kveld9.trackgym.domain.model.RoutineUpdateMode.NEVER -> {
                                    viewModel.finishWorkout(
                                        notes = finalNotes,
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
                                notes = finalNotes,
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
        val finalNotes = WorkoutContextTagParser.embedTagsIntoNotes(selectedContextTags, finishNotes)
        AlertDialog(
            onDismissRequest = {
                showRoutineSyncPrompt = false
                viewModel.finishWorkout(
                    notes = finalNotes,
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
                            notes = finalNotes,
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
                            notes = finalNotes,
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
            activeProfile = activeGymProfile,
            allProfiles = gymProfiles,
            onSelectProfile = { viewModel.selectGymProfile(it) },
            onSaveProfile = { viewModel.saveGymProfile(it) },
            onDeleteProfile = { viewModel.deleteGymProfile(it) },
            onDismiss = {
                plateCalcExercise = null
                plateCalcInitialWeightKg = null
            }
        )
    }

    wheelPickerTarget?.let { (we, targetSet) ->
        HapticWheelSetPickerDialog(
            exerciseName = we.exercise.displayName(),
            setNumber = targetSet.setNumber,
            initialWeightKg = targetSet.weightKg,
            initialReps = targetSet.reps,
            weightUnit = weightUnit,
            onApply = { weightKg, reps, completeNow ->
                val updated = targetSet.copy(
                    weightKg = weightKg,
                    reps = reps,
                    isCompleted = if (completeNow) true else targetSet.isCompleted
                )
                viewModel.updateSet(updated)
                if (completeNow && !targetSet.isCompleted) {
                    if (soundFeedbackOnComplete) {
                        audioCuePlayer.playSetCompleteClick()
                    }
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    activeWorkout?.let { wo ->
                        viewModel.toggleCompleteSet(updated, wo.id, we.exercise.id, weightKg, reps)
                    }
                }
                wheelPickerTarget = null
            },
            onDismiss = { wheelPickerTarget = null }
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

    exerciseForTechniqueGuide?.let { exercise ->
        ExerciseTechniqueDialog(
            exercise = exercise,
            onDismiss = { exerciseForTechniqueGuide = null }
        )
    }

    exerciseForHistory?.let { exercise ->
        val completedWorkouts by viewModel.completedWorkouts.collectAsStateWithLifecycle()
        ExerciseHistoryDialog(
            exercise = exercise,
            completedWorkouts = completedWorkouts,
            weightUnit = weightUnit,
            userBodyWeightKg = userBodyWeight,
            onDismiss = { exerciseForHistory = null }
        )
    }
    }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
private fun ActiveWorkoutSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .width(180.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(240.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        )
    }
}

