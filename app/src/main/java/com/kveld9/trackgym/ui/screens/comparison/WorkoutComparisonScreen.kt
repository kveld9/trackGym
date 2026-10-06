package com.kveld9.trackgym.ui.screens.comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import android.content.Intent
import android.widget.Toast
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import com.kveld9.trackgym.domain.calculator.LlmWorkoutPromptGenerator
import com.kveld9.trackgym.domain.calculator.MuscleHeatmapEngine
import com.kveld9.trackgym.ui.components.MuscleHeatmapCard
import com.kveld9.trackgym.ui.util.displayName
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutComparison
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutComparisonScreen(
    comparison: WorkoutComparison,
    onBackClick: () -> Unit,
    weightUnit: WeightUnit = WeightUnit.KG,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
    val dateString = dateFormat.format(Date(comparison.currentWorkout.completedAt ?: comparison.currentWorkout.startedAt)).replaceFirstChar { it.uppercase() }
    val workoutHeatmap = remember(comparison.currentWorkout) {
        MuscleHeatmapEngine.calculate(comparison.currentWorkout)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.comparison_title),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.action_back),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    val context = LocalContext.current
                    IconButton(
                        onClick = {
                            val promptText = LlmWorkoutPromptGenerator.generateMarkdownPrompt(comparison, weightUnit)
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, promptText)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, null))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = stringResource(R.string.action_share_prompt),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                WorkoutSummaryHeader(
                    title = comparison.currentWorkout.name,
                    dateString = dateString,
                    durationSeconds = comparison.currentWorkout.durationSeconds,
                    totalVolumeKg = comparison.currentWorkout.totalVolume,
                    recordsCount = comparison.totalRecordsUnlocked.size,
                    weightUnit = weightUnit
                )
            }

            item {
                MuscleHeatmapCard(
                    state = workoutHeatmap,
                    title = stringResource(R.string.heatmap_title_session),
                    subtitle = stringResource(R.string.heatmap_subtitle_session),
                    emptyMessage = stringResource(R.string.heatmap_no_muscles_session)
                )
            }

            item {
                LlmPromptExportCard(
                    comparison = comparison,
                    weightUnit = weightUnit
                )
            }

            // Unlocked PRs section
            if (comparison.totalRecordsUnlocked.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.comparison_unlocked_prs_section, comparison.totalRecordsUnlocked.size),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                items(comparison.totalRecordsUnlocked) { pr ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.tertiary))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = stringResource(pr.recordType.nameRes),
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = pr.description,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = stringResource(R.string.comparison_by_exercise_section),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            items(comparison.exerciseComparisons) { exComp ->
                ExerciseComparisonCard(exerciseComparison = exComp, weightUnit = weightUnit)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun WorkoutSummaryHeader(
    title: String,
    dateString: String,
    durationSeconds: Long,
    totalVolumeKg: Double,
    recordsCount: Int,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, color = MaterialTheme.colorScheme.onSurface, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = dateString, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBadge(label = stringResource(R.string.stat_duration), value = formatDuration(durationSeconds))
                StatBadge(
                    label = stringResource(R.string.stat_volume),
                    value = "${weightUnit.formatValue(totalVolumeKg)} ${weightUnit.symbol}",
                    valueColor = MaterialTheme.colorScheme.primary
                )
                if (recordsCount > 0) {
                    StatBadge(label = stringResource(R.string.stat_records), value = recordsCount.toString(), valueColor = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Composable
fun ExerciseComparisonCard(
    exerciseComparison: ExerciseComparison,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                        text = exerciseComparison.exercise.displayName(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(exerciseComparison.exercise.muscleGroup.nameRes),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp
                    )
                }

                if (exerciseComparison.hasRecords) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(stringResource(R.string.badge_record), color = MaterialTheme.colorScheme.tertiary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Before vs After summary banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.badge_previous), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = exerciseComparison.previousSummary,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp).padding(horizontal = 4.dp)
                )

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(stringResource(R.string.badge_current), color = MaterialTheme.colorScheme.primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = exerciseComparison.currentSummary,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Set-by-Set comparisons
            exerciseComparison.setComparisons.forEach { setComp ->
                SetComparisonRow(setComp = setComp, weightUnit = weightUnit)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
fun SetComparisonRow(
    setComp: SetComparison,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    val prev = setComp.previousSet
    val curr = setComp.currentSet

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (setComp.isImprovement) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f) else Color.Transparent)
            .border(
                1.dp,
                if (setComp.recordsUnlocked.isNotEmpty()) MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Set number
        Text(
            text = stringResource(R.string.set_format, setComp.setNumber),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        // Previous performance
        Text(
            text = if (prev != null) "${weightUnit.formatValue(prev.weightKg)} ${weightUnit.symbol} × ${prev.reps}" else "—",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(14.dp)
        )

        // Current performance
        Text(
            text = "${weightUnit.formatValue(curr.weightKg)} ${weightUnit.symbol} × ${curr.reps}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        // Delta indicator
        if (setComp.isImprovement) {
            val deltaText = buildString {
                if (setComp.weightDeltaKg > 0) append("+${weightUnit.formatValue(setComp.weightDeltaKg)}${weightUnit.symbol} ")
                if (setComp.repsDelta > 0) append("+${setComp.repsDelta}r")
                if (isEmpty()) append("✓")
            }
            Text(
                text = deltaText.trim(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "=",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun StatBadge(label: String, value: String, valueColor: Color = Color.Unspecified) {
    val finalValueColor = if (valueColor != Color.Unspecified) valueColor else MaterialTheme.colorScheme.onSurface
    Column {
        Text(text = label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = finalValueColor, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

private fun formatDuration(seconds: Long): String {
    val m = seconds / 60
    val s = seconds % 60
    return if (m > 60) {
        val h = m / 60
        val remM = m % 60
        "${h}h ${remM}m"
    } else {
        "${m}m ${s}s"
    }
}

@Composable
fun LlmPromptExportCard(
    comparison: WorkoutComparison,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val promptText = remember(comparison, weightUnit) {
        LlmWorkoutPromptGenerator.generateMarkdownPrompt(comparison, weightUnit)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.llm_export_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.llm_export_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(promptText))
                        Toast.makeText(context, R.string.toast_prompt_copied, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = stringResource(R.string.action_copy_prompt), maxLines = 1)
                }

                Button(
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, promptText)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, null))
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(R.string.action_share_prompt), maxLines = 1)
                }
            }
        }
    }
}
