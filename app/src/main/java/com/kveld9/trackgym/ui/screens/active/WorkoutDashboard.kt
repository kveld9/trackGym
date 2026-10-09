package com.kveld9.trackgym.ui.screens.active

import android.content.Intent
import com.kveld9.trackgym.domain.calculator.RoutineBiomechanicalBalanceEngine
import com.kveld9.trackgym.domain.util.RoutineShareCodec
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import com.kveld9.trackgym.domain.calculator.ProgramRecommendation
import com.kveld9.trackgym.domain.model.PeriodizedCycle
import com.kveld9.trackgym.ui.components.DeloadRoutineDialog
import com.kveld9.trackgym.ui.components.PeriodizedCycleDialog
import com.kveld9.trackgym.ui.components.ProgramFinderDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Routine
import com.kveld9.trackgym.domain.model.RoutineFolder
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName

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
                            runCatching { context.startActivity(shareIntent) }
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
                if (routine.exercises.isNotEmpty()) {
                    val balance = remember(routine.exercises) {
                        RoutineBiomechanicalBalanceEngine.calculate(routine.exercises.map { it.exercise })
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            color = if (balance.isBalanced) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainerHighest,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = stringResource(balance.balanceStatusRes),
                                color = if (balance.isBalanced) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = "${balance.pushCount} ${stringResource(R.string.force_push)} • ${balance.pullCount} ${stringResource(R.string.force_pull)}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }
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
