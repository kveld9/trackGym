package com.kveld9.trackgym.ui.screens.active

import com.kveld9.trackgym.ui.theme.subtleBorder
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
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.icons.filled.Add
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
import com.kveld9.trackgym.domain.model.Exercise
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
    onCreateRoutine: ((String, (Long) -> Unit) -> Unit)? = null,
    onDuplicateRoutine: ((Long) -> Unit)? = null,
    onToggleArchive: ((Long, Boolean) -> Unit)? = null,
    onImportRoutine: ((String, (Boolean) -> Unit) -> Unit)? = null,
    onMoveRoutineUp: ((Long) -> Unit)? = null,
    onMoveRoutineDown: ((Long) -> Unit)? = null,
    onMoveFolderUp: ((Long) -> Unit)? = null,
    onMoveFolderDown: ((Long) -> Unit)? = null,
    allExercises: List<Exercise> = emptyList(),
    onAddExerciseToRoutine: ((Long, Long) -> Unit)? = null,
    onRemoveExerciseFromRoutine: ((Long) -> Unit)? = null,
    onUpdateRoutineExercise: ((Long, Int, Double, Int) -> Unit)? = null,
    onMoveRoutineExercise: ((Long, Int, Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var selectedFolderId by remember { mutableStateOf<Long?>(null) }
    var showArchived by remember { mutableStateOf(false) }
    var showReorderFoldersDialog by remember { mutableStateOf(false) }
    var showImportRoutineDialog by remember { mutableStateOf(false) }
    var showNewRoutineDialog by remember { mutableStateOf(false) }
    var routineIdForBuilder by remember { mutableStateOf<Long?>(null) }
    var pendingBuilderRoutine by remember { mutableStateOf<Routine?>(null) }
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

        if (onCreateRoutine != null) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = { showNewRoutineDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.btn_new_routine),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
                        modifier = Modifier.size(48.dp)
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
                border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.subtleBorder)
        )
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
                    if (onCreateRoutine != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = { showNewRoutineDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.btn_new_routine),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
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
                        onToggleArchive = { onToggleArchive?.invoke(routine.id, !routine.isArchived) },
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
                        onMoveDown = { onMoveRoutineDown?.invoke(routine.id) },
                        onEdit = { routineIdForBuilder = routine.id }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(88.dp))
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



    if (showNewRoutineDialog && onCreateRoutine != null) {
        NewRoutineDialog(
            onDismiss = { showNewRoutineDialog = false },
            onConfirm = { name ->
                showNewRoutineDialog = false
                onCreateRoutine(name) { newRoutineId ->
                    routineIdForBuilder = newRoutineId
                    pendingBuilderRoutine = Routine(id = newRoutineId, name = name)
                }
            }
        )
    }

    val routineToBuild = routines.find { it.id == routineIdForBuilder } ?: pendingBuilderRoutine
    if (routineToBuild != null && onAddExerciseToRoutine != null) {
        RoutineBuilderDialog(
            routine = routineToBuild,
            allExercises = allExercises,
            onAddExercise = { exerciseId ->
                onAddExerciseToRoutine(routineToBuild.id, exerciseId)
            },
            onRemoveExercise = { routineExerciseId ->
                onRemoveExerciseFromRoutine?.invoke(routineExerciseId)
            },
            onUpdateExercise = { routineExerciseId, targetSets, defaultWeightKg, defaultReps ->
                onUpdateRoutineExercise?.invoke(routineExerciseId, targetSets, defaultWeightKg, defaultReps)
            },
            onMoveExercise = { fromIndex, toIndex ->
                onMoveRoutineExercise?.invoke(routineToBuild.id, fromIndex, toIndex)
            },
            onDismiss = {
                routineIdForBuilder = null
                pendingBuilderRoutine = null
            }
        )
    }
}

@Composable
fun RoutineCardItem(
    routine: Routine,
    onStart: () -> Unit,
    onDelete: () -> Unit,
    onDuplicate: (() -> Unit)? = null,
    onToggleArchive: (() -> Unit)? = null,
    onShare: (() -> Unit)? = null,
    canMoveUp: Boolean = false,
    canMoveDown: Boolean = false,
    onMoveUp: (() -> Unit)? = null,
    onMoveDown: (() -> Unit)? = null,
    onEdit: (() -> Unit)? = null
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.subtleBorder)
        )
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
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = onStart,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.defaultMinSize(minHeight = 48.dp)
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
                        if (onEdit != null) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_edit_routine)) },
                                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onEdit()
                                }
                            )
                        }
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
