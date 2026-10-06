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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.ExerciseComparison
import com.kveld9.trackgym.domain.model.SetComparison
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.ui.theme.GymBlack
import com.kveld9.trackgym.ui.theme.GymBorder
import com.kveld9.trackgym.ui.theme.GymGold
import com.kveld9.trackgym.ui.theme.GymNeonGreen
import com.kveld9.trackgym.ui.theme.GymSurface
import com.kveld9.trackgym.ui.theme.GymSurfaceVariant
import com.kveld9.trackgym.ui.theme.TextMuted
import com.kveld9.trackgym.ui.theme.TextWhite
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutComparisonScreen(
    comparison: WorkoutComparison,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
    val dateString = dateFormat.format(Date(comparison.currentWorkout.completedAt ?: comparison.currentWorkout.startedAt)).replaceFirstChar { it.uppercase() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.comparison_title),
                        color = TextWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.action_back),
                            tint = TextWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GymBlack)
            )
        },
        containerColor = GymBlack,
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
                    recordsCount = comparison.totalRecordsUnlocked.size
                )
            }

            // Unlocked PRs section
            if (comparison.totalRecordsUnlocked.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.comparison_unlocked_prs_section, comparison.totalRecordsUnlocked.size),
                        color = GymGold,
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
                        colors = CardDefaults.cardColors(containerColor = GymSurface),
                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(GymGold))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = GymGold,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = stringResource(pr.recordType.nameRes),
                                    color = GymGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = pr.description,
                                    color = TextWhite,
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
                    color = TextMuted,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            items(comparison.exerciseComparisons) { exComp ->
                ExerciseComparisonCard(exerciseComparison = exComp)
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
    recordsCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymSurface),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(GymBorder))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, color = TextWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = dateString, color = TextMuted, fontSize = 13.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBadge(label = stringResource(R.string.stat_duration), value = formatDuration(durationSeconds))
                StatBadge(label = stringResource(R.string.stat_volume), value = "${formatKg(totalVolumeKg)} kg")
                if (recordsCount > 0) {
                    StatBadge(label = stringResource(R.string.stat_records), value = "$recordsCount 🏆", valueColor = GymGold)
                }
            }
        }
    }
}

@Composable
fun ExerciseComparisonCard(
    exerciseComparison: ExerciseComparison
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymSurface),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(GymBorder))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = exerciseComparison.exercise.name,
                        color = TextWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(exerciseComparison.exercise.muscleGroup.nameRes),
                        color = GymNeonGreen,
                        fontSize = 12.sp
                    )
                }

                if (exerciseComparison.hasRecords) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(GymGold.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = GymGold, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(stringResource(R.string.badge_record), color = GymGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Before vs After summary banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(GymSurfaceVariant)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.badge_previous), color = TextMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = exerciseComparison.previousSummary,
                        color = Color.LightGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = GymNeonGreen,
                    modifier = Modifier.size(18.dp).padding(horizontal = 4.dp)
                )

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(stringResource(R.string.badge_current), color = GymNeonGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = exerciseComparison.currentSummary,
                        color = TextWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Set-by-Set comparisons
            exerciseComparison.setComparisons.forEach { setComp ->
                SetComparisonRow(setComp = setComp)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
fun SetComparisonRow(setComp: SetComparison) {
    val prev = setComp.previousSet
    val curr = setComp.currentSet

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (setComp.isImprovement) GymNeonGreen.copy(alpha = 0.08f) else Color.Transparent)
            .border(
                1.dp,
                if (setComp.recordsUnlocked.isNotEmpty()) GymGold.copy(alpha = 0.6f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Set number
        Text(
            text = stringResource(R.string.set_format, setComp.setNumber),
            color = TextMuted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        // Previous performance
        Text(
            text = if (prev != null) "${formatKg(prev.weightKg)} kg × ${prev.reps}" else "—",
            color = TextMuted,
            fontSize = 13.sp
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(14.dp)
        )

        // Current performance
        Text(
            text = "${formatKg(curr.weightKg)} kg × ${curr.reps}",
            color = TextWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        // Delta indicator
        if (setComp.isImprovement) {
            val deltaText = buildString {
                if (setComp.weightDeltaKg > 0) append("+${formatKg(setComp.weightDeltaKg)}kg ")
                if (setComp.repsDelta > 0) append("+${setComp.repsDelta}r")
                if (isEmpty()) append("✓")
            }
            Text(
                text = deltaText.trim(),
                color = GymNeonGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "=",
                color = TextMuted,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun StatBadge(label: String, value: String, valueColor: Color = TextWhite) {
    Column {
        Text(text = label, color = TextMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = valueColor, fontSize = 15.sp, fontWeight = FontWeight.Bold)
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

private fun formatKg(value: Double): String {
    return if (value % 1.0 == 0.0) {
        value.toInt().toString()
    } else {
        String.format(Locale.US, "%.1f", value)
    }
}
