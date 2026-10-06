package com.kveld9.trackgym.ui.screens.history

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import java.text.SimpleDateFormat
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
    val completedWorkouts by viewModel.completedWorkouts.collectAsState()
    val consistencyStats by viewModel.consistencyStats.collectAsState()
    val weeklyHeatmap by viewModel.weeklyHeatmapState.collectAsState()

    var workoutToSaveAsRoutine by remember { mutableStateOf<Workout?>(null) }
    var routineNameInput by remember { mutableStateOf("") }

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        if (completedWorkouts.isEmpty()) {
            EmptyHistoryView(modifier = Modifier.padding(paddingValues))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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

                items(completedWorkouts, key = { it.id }) { workout ->
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
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
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
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (stats.currentStreakWeeks > 0) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = streakText,
                        color = if (stats.currentStreakWeeks > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
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
    onSaveAsRoutine: () -> Unit
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
