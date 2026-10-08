package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.TimeBucketedDistributionEngine
import com.kveld9.trackgym.domain.model.DistributionMetricType
import com.kveld9.trackgym.domain.model.DistributionTimeBucket
import com.kveld9.trackgym.domain.model.ExerciseDistributionItem
import com.kveld9.trackgym.domain.model.MuscleDistributionItem
import com.kveld9.trackgym.domain.model.TimeBucketedDistribution
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.Workout

@Composable
fun TimeBucketedDistributionCard(
    workouts: List<Workout>,
    weightUnit: WeightUnit,
    modifier: Modifier = Modifier
) {
    var selectedBucket by remember { mutableStateOf(DistributionTimeBucket.LAST_MONTH) }
    var selectedMetric by remember { mutableStateOf(DistributionMetricType.SETS) }

    val distribution: TimeBucketedDistribution = remember(workouts, selectedBucket, selectedMetric) {
        TimeBucketedDistributionEngine.calculateDistribution(
            workouts = workouts,
            bucket = selectedBucket,
            metricType = selectedMetric
        )
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.distribution_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                if (distribution.totalWorkouts > 0) {
                    val summaryValueText = when (selectedMetric) {
                        DistributionMetricType.SETS -> stringResource(R.string.history_stat_sets, distribution.totalValue.toInt())
                        DistributionMetricType.VOLUME -> weightUnit.format(distribution.totalValue)
                        DistributionMetricType.REPS -> stringResource(R.string.history_stat_reps, distribution.totalValue.toInt())
                    }
                    Text(
                        text = summaryValueText,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Time Bucket selector chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DistributionTimeBucket.entries.forEach { bucket ->
                    val isSelected = bucket == selectedBucket
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedBucket = bucket },
                        label = { Text(stringResource(bucket.labelRes), fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            // Metric selector chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DistributionMetricType.entries.forEach { metric ->
                    val isSelected = metric == selectedMetric
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedMetric = metric },
                        label = { Text(stringResource(metric.labelRes), fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (distribution.totalWorkouts == 0 || distribution.muscleDistributions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.distribution_no_data),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            } else {
                Text(
                    text = stringResource(R.string.distribution_muscle_groups),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    distribution.muscleDistributions.take(6).forEach { item ->
                        MuscleDistributionRow(item = item, metric = selectedMetric, weightUnit = weightUnit)
                    }
                }

                if (distribution.topExercises.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = stringResource(R.string.distribution_top_exercises),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        distribution.topExercises.take(4).forEach { exItem ->
                            ExerciseDistributionRow(item = exItem, metric = selectedMetric, weightUnit = weightUnit)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MuscleDistributionRow(
    item: MuscleDistributionItem,
    metric: DistributionMetricType,
    weightUnit: WeightUnit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(item.muscleGroup.nameRes),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(90.dp)
        )

        LinearProgressIndicator(
            progress = { item.percentage.coerceIn(0f, 1f) },
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            strokeCap = StrokeCap.Round
        )

        val valText = when (metric) {
            DistributionMetricType.SETS -> "${item.value.toInt()} s"
            DistributionMetricType.VOLUME -> weightUnit.format(item.value)
            DistributionMetricType.REPS -> "${item.value.toInt()} r"
        }

        Text(
            text = valText,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.width(64.dp)
        )
    }
}

@Composable
private fun ExerciseDistributionRow(
    item: ExerciseDistributionItem,
    metric: DistributionMetricType,
    weightUnit: WeightUnit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.exerciseName,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        val valText = when (metric) {
            DistributionMetricType.SETS -> "${stringResource(R.string.history_stat_sets, item.value.toInt())} (${(item.percentage * 100).toInt()}%)"
            DistributionMetricType.VOLUME -> "${weightUnit.format(item.value)} (${(item.percentage * 100).toInt()}%)"
            DistributionMetricType.REPS -> "${stringResource(R.string.history_stat_reps, item.value.toInt())} (${(item.percentage * 100).toInt()}%)"
        }

        Text(
            text = valText,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
