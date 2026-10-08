package com.kveld9.trackgym.ui.screens.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import com.kveld9.trackgym.domain.model.StandardContextTag
import com.kveld9.trackgym.domain.model.WorkoutContextTagParser
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.kveld9.trackgym.domain.calculator.DayActivity
import com.kveld9.trackgym.domain.calculator.TrainingConsistencyStats
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.Workout
import com.kveld9.trackgym.ui.components.MuscleHeatmapCard
import com.kveld9.trackgym.ui.components.TimeBucketedDistributionCard
import com.kveld9.trackgym.ui.components.HypertrophyThresholdCard
import com.kveld9.trackgym.ui.components.WorkoutShareCard
import com.kveld9.trackgym.ui.components.WorkoutSharePreviewDialog
import com.kveld9.trackgym.ui.util.ShareProvider
import com.kveld9.trackgym.domain.model.WorkoutComparison
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import com.kveld9.trackgym.domain.calculator.WorkoutCalendarMonthEngine
import com.kveld9.trackgym.domain.calculator.CalendarDayInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: GymViewModel,
    onWorkoutClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    val completedWorkouts by viewModel.completedWorkouts.collectAsStateWithLifecycle()
    val consistencyStats by viewModel.consistencyStats.collectAsStateWithLifecycle()
    val weeklyHeatmap by viewModel.weeklyHeatmapState.collectAsStateWithLifecycle()

    var isCalendarView by remember { mutableStateOf(false) }
    var calendarMonth by remember {
        mutableStateOf(Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        })
    }
    var selectedDateKey by remember { mutableStateOf<String?>(null) }
    var selectedContextFilter by remember { mutableStateOf<String?>(null) }

    val filteredCompletedWorkouts = remember(completedWorkouts, selectedContextFilter) {
        if (selectedContextFilter == null) {
            completedWorkouts
        } else {
            completedWorkouts.filter { workout ->
                val tags = WorkoutContextTagParser.extractTagsFromNotes(workout.notes)
                tags.any { it.equals(selectedContextFilter, ignoreCase = true) }
            }
        }
    }

    var workoutToSaveAsRoutine by remember { mutableStateOf<Workout?>(null) }
    var routineNameInput by remember { mutableStateOf("") }
    var workoutToShare by remember { mutableStateOf<Workout?>(null) }
    var previewBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val shareChooserTitle = stringResource(R.string.share_chooser_title)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.history_title),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(
                        onClick = { isCalendarView = !isCalendarView },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = if (isCalendarView) Icons.AutoMirrored.Filled.List else Icons.Default.DateRange,
                            contentDescription = stringResource(if (isCalendarView) R.string.action_switch_to_list else R.string.action_switch_to_calendar),
                            tint = if (isCalendarView) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        if (completedWorkouts.isEmpty()) {
            EmptyHistoryView(modifier = Modifier.padding(paddingValues))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // View Mode Segmented Controls (List vs Calendar)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (!isCalendarView) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isCalendarView = false }
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = null,
                                tint = if (!isCalendarView) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = stringResource(R.string.history_view_list),
                                color = if (!isCalendarView) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isCalendarView) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isCalendarView = true }
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                tint = if (isCalendarView) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = stringResource(R.string.history_view_calendar),
                                color = if (isCalendarView) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                // Context Tags Filter Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = selectedContextFilter == null,
                        onClick = { selectedContextFilter = null },
                        label = { Text(stringResource(R.string.chip_all_context_tags), fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    StandardContextTag.entries.forEach { tag ->
                        val isSelected = selectedContextFilter.equals(tag.tagKey, ignoreCase = true)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedContextFilter = if (isSelected) null else tag.tagKey
                            },
                            label = { Text(stringResource(tag.labelRes), fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                if (!isCalendarView) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Training Consistency & Heatmap
                        item {
                            TrainingConsistencyHeader(
                                stats = consistencyStats,
                                weightUnit = weightUnit
                            )
                        }

                        // Weekly Muscle Split Heatmap
                        item {
                            val weeklySubtitle = if (weeklyHeatmap.totalSets > 0) {
                                stringResource(R.string.heatmap_subtitle_weekly, weeklyHeatmap.totalSets)
                            } else null

                            MuscleHeatmapCard(
                                state = weeklyHeatmap,
                                title = stringResource(R.string.heatmap_title_weekly),
                                subtitle = weeklySubtitle,
                                emptyMessage = stringResource(R.string.heatmap_no_muscles_weekly)
                            )
                        }

                        // Time-Bucketed Stimulus Distribution Card
                        item {
                            TimeBucketedDistributionCard(
                                workouts = completedWorkouts,
                                weightUnit = weightUnit
                            )
                        }

                        // Hypertrophy Thresholds Card
                        item {
                            HypertrophyThresholdCard(
                                workouts = completedWorkouts
                            )
                        }

                        items(filteredCompletedWorkouts, key = { it.id }) { workout ->
                            WorkoutHistoryCard(
                                workout = workout,
                                weightUnit = weightUnit,
                                onClick = {
                                    viewModel.viewWorkoutDetail(workout.id)
                                    onWorkoutClick(workout.id)
                                },
                                onSaveAsRoutine = {
                                    routineNameInput = workout.name
                                    workoutToSaveAsRoutine = workout
                                },
                                onShareCard = {
                                    workoutToShare = workout
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(120.dp))
                        }
                    }
                } else {
                    HistoryCalendarView(
                        calendarMonth = calendarMonth,
                        completedWorkouts = completedWorkouts,
                        selectedDateKey = selectedDateKey,
                        weightUnit = weightUnit,
                        onPreviousMonth = {
                            val nextCal = (calendarMonth.clone() as Calendar).apply { add(Calendar.MONTH, -1) }
                            calendarMonth = nextCal
                            selectedDateKey = null
                        },
                        onNextMonth = {
                            val nextCal = (calendarMonth.clone() as Calendar).apply { add(Calendar.MONTH, 1) }
                            calendarMonth = nextCal
                            selectedDateKey = null
                        },
                        onSelectDate = { dateKey ->
                            selectedDateKey = if (selectedDateKey == dateKey) null else dateKey
                        },
                        onWorkoutClick = { workoutId ->
                            viewModel.viewWorkoutDetail(workoutId)
                            onWorkoutClick(workoutId)
                        },
                        onSaveAsRoutine = { workout ->
                            routineNameInput = workout.name
                            workoutToSaveAsRoutine = workout
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    if (workoutToSaveAsRoutine != null) {
        AlertDialog(
            onDismissRequest = { workoutToSaveAsRoutine = null },
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
                        val target = workoutToSaveAsRoutine
                        if (target != null) {
                            viewModel.saveCompletedWorkoutAsRoutine(target.id, routineNameInput)
                        }
                        workoutToSaveAsRoutine = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(stringResource(R.string.action_save), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { workoutToSaveAsRoutine = null }) {
                    Text(stringResource(R.string.action_cancel), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }

    workoutToShare?.let { workout ->
        val dummyComparison = remember(workout) {
            WorkoutComparison(
                currentWorkout = workout,
                previousWorkout = null,
                exerciseComparisons = emptyList(),
                totalRecordsUnlocked = emptyList(),
                totalVolumeDeltaKg = 0.0
            )
        }

        // Invisible off-screen composable that triggers capture to bitmap
        Box(modifier = Modifier.size(0.dp)) {
            WorkoutShareCard(
                comparison = dummyComparison,
                weightUnit = weightUnit,
                onCapture = { bitmap ->
                    previewBitmap = bitmap
                    workoutToShare = null
                }
            )
        }
    }

    previewBitmap?.let { bitmap ->
        WorkoutSharePreviewDialog(
            bitmap = bitmap,
            onDownload = {
                coroutineScope.launch {
                    val success = ShareProvider.saveBitmapToGallery(context, bitmap)
                    Toast.makeText(
                        context,
                        if (success) R.string.toast_image_saved else R.string.toast_image_save_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            onShare = {
                coroutineScope.launch {
                    ShareProvider.shareBitmap(context, bitmap, shareChooserTitle)
                }
            },
            onDismiss = {
                previewBitmap = null
            }
        )
    }
}

@Composable
fun TrainingConsistencyHeader(
    stats: TrainingConsistencyStats,
    weightUnit: WeightUnit = WeightUnit.KG,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Title and Streak Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.consistency_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                val streakText = if (stats.currentStreakWeeks > 0) {
                    if (stats.currentStreakWeeks == 1) {
                        stringResource(R.string.consistency_streak_week_singular)
                    } else {
                        stringResource(R.string.consistency_streak_weeks, stats.currentStreakWeeks)
                    }
                } else {
                    stringResource(R.string.consistency_no_streak)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (stats.bestStreakWeeks > 0) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceContainerHigh
                        ) {
                            Text(
                                text = stringResource(R.string.consistency_best_streak, stats.bestStreakWeeks),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (stats.currentStreakWeeks > 0) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHigh
                    ) {
                        Text(
                            text = streakText,
                            color = if (stats.currentStreakWeeks > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle: 30-day stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.consistency_workouts_month, stats.totalWorkoutsLast30Days),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
                Text(
                    text = stringResource(R.string.consistency_volume_month, weightUnit.format(stats.totalVolumeLast30Days)),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Activity Heatmap Grid
            ActivityHeatmapGrid(days = stats.recentDays)
        }
    }
}

@Composable
fun ActivityHeatmapGrid(days: List<DayActivity>) {
    val weeks = days.chunked(7)
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            weeks.forEach { week ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    week.forEach { day ->
                        val cellColor = when (day.intensityLevel) {
                            1 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                            2 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.70f)
                            3 -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.surfaceContainerHigh
                        }
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(cellColor)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Heatmap Legend
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.consistency_legend_less),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            listOf(
                MaterialTheme.colorScheme.surfaceContainerHigh,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                MaterialTheme.colorScheme.primary.copy(alpha = 0.70f),
                MaterialTheme.colorScheme.primary
            ).forEach { color ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(color)
                )
                Spacer(modifier = Modifier.width(3.dp))
            }
            Text(
                text = stringResource(R.string.consistency_legend_more),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun WorkoutHistoryCard(
    workout: Workout,
    weightUnit: WeightUnit = WeightUnit.KG,
    onClick: () -> Unit,
    onSaveAsRoutine: () -> Unit,
    onShareCard: () -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
    val dateString = dateFormat.format(Date(workout.completedAt ?: workout.startedAt)).replaceFirstChar { it.uppercase() }
    val durationMin = workout.durationSeconds / 60

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = workout.name,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = dateString,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
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
                                    onSaveAsRoutine()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_share_image), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onShareCard()
                                }
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Stats row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.history_stat_min, durationMin),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.history_stat_sets, workout.totalCompletedSets),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = weightUnit.format(workout.totalVolume),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (workout.exercises.isNotEmpty()) {
                val context = LocalContext.current
                val keepEnglish = LocalKeepEnglishExerciseNames.current
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = workout.exercises.joinToString(" • ") { "${it.exercise.displayName(context, keepEnglish)} (${it.sets.count { s -> s.isCompleted }}s)" },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun EmptyHistoryView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHigh
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.history_empty_title),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.history_empty_desc),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HistoryCalendarView(
    calendarMonth: Calendar,
    completedWorkouts: List<Workout>,
    selectedDateKey: String?,
    weightUnit: WeightUnit,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onSelectDate: (String) -> Unit,
    onWorkoutClick: (Long) -> Unit,
    onSaveAsRoutine: (Workout) -> Unit,
    modifier: Modifier = Modifier
) {
    val monthData = remember(calendarMonth, completedWorkouts) {
        WorkoutCalendarMonthEngine.computeMonthData(
            year = calendarMonth.get(Calendar.YEAR),
            month = calendarMonth.get(Calendar.MONTH),
            completedWorkouts = completedWorkouts
        )
    }

    val monthFormatter = remember { SimpleDateFormat("MMMM yyyy", Locale.getDefault()) }
    val monthTitle = remember(calendarMonth) {
        monthFormatter.format(calendarMonth.time).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

    val selectedDay = remember(selectedDateKey, monthData) {
        monthData.days.firstOrNull { it.dateKey == selectedDateKey }
    }

    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")

    // Flatten grid with leading empty slots
    val gridItems = remember(monthData) {
        val list = mutableListOf<CalendarDayInfo?>()
        repeat(monthData.firstDayOfWeekOffset) {
            list.add(null)
        }
        list.addAll(monthData.days)
        list
    }

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Month Navigation Header (48x48 dp touch targets)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onPreviousMonth,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.calendar_prev_month),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Text(
                            text = monthTitle,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )

                        IconButton(
                            onClick = onNextMonth,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = stringResource(R.string.calendar_next_month),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Weekday Labels
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        weekDays.forEach { dayName ->
                            Text(
                                text = dayName,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Days Grid chunked by 7
                    val rows = gridItems.chunked(7)
                    rows.forEach { weekRow ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            for (col in 0 until 7) {
                                val item = weekRow.getOrNull(col)
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (item != null) {
                                        val isSelected = item.dateKey == selectedDateKey
                                        val hasWorkouts = item.hasWorkouts

                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(8.dp))
                                                .then(
                                                    if (hasWorkouts) {
                                                        Modifier
                                                            .background(
                                                                if (isSelected) MaterialTheme.colorScheme.primary
                                                                else MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
                                                            )
                                                            .then(
                                                                if (!isSelected) Modifier.border(
                                                                    BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                                                    RoundedCornerShape(8.dp)
                                                                ) else Modifier
                                                            )
                                                            .clickable { onSelectDate(item.dateKey) }
                                                    } else {
                                                        Modifier
                                                    }
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = item.dayOfMonth.toString(),
                                                    color = when {
                                                        isSelected -> MaterialTheme.colorScheme.onPrimary
                                                        hasWorkouts -> MaterialTheme.colorScheme.primary
                                                        else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.35f)
                                                    },
                                                    fontWeight = if (hasWorkouts) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 13.sp
                                                )
                                                if (hasWorkouts && item.workouts.size > 1) {
                                                    Text(
                                                        text = "x${item.workouts.size}",
                                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }

        // Selected Date Workouts Section or Guidance Banner
        if (selectedDay != null && selectedDay.hasWorkouts) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.calendar_workouts_on_date, selectedDay.dateKey),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            items(selectedDay.workouts, key = { it.id }) { workout ->
                WorkoutHistoryCard(
                    workout = workout,
                    weightUnit = weightUnit,
                    onClick = { onWorkoutClick(workout.id) },
                    onSaveAsRoutine = { onSaveAsRoutine(workout) }
                )
            }
        } else {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (monthData.days.any { it.hasWorkouts }) {
                                stringResource(R.string.calendar_select_date_hint)
                            } else {
                                stringResource(R.string.calendar_no_workouts_in_month)
                            },
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

