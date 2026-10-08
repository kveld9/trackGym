package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.HypertrophyVolumeEngine
import com.kveld9.trackgym.domain.model.HypertrophyVolumeStatus
import com.kveld9.trackgym.domain.model.MuscleHypertrophyVolume
import com.kveld9.trackgym.domain.model.Workout

@Composable
fun HypertrophyThresholdCard(
    workouts: List<Workout>,
    modifier: Modifier = Modifier
) {
    val volumes: List<MuscleHypertrophyVolume> = remember(workouts) {
        HypertrophyVolumeEngine.calculateWeeklyVolume(workouts)
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
            Text(
                text = stringResource(R.string.hypertrophy_title),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = stringResource(R.string.hypertrophy_subtitle),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                volumes.forEach { volume ->
                    HypertrophyVolumeRow(volume = volume)
                }
            }
        }
    }
}

@Composable
private fun HypertrophyVolumeRow(volume: MuscleHypertrophyVolume) {
    val statusColor = when (volume.status) {
        HypertrophyVolumeStatus.OPTIMAL -> MaterialTheme.colorScheme.primary
        HypertrophyVolumeStatus.BELOW_OPTIMAL -> MaterialTheme.colorScheme.outline
        HypertrophyVolumeStatus.EXCESSIVE -> MaterialTheme.colorScheme.error
    }

    val statusContainerColor = when (volume.status) {
        HypertrophyVolumeStatus.OPTIMAL -> MaterialTheme.colorScheme.primaryContainer
        HypertrophyVolumeStatus.BELOW_OPTIMAL -> MaterialTheme.colorScheme.surfaceContainerHigh
        HypertrophyVolumeStatus.EXCESSIVE -> MaterialTheme.colorScheme.errorContainer
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(volume.muscleGroup.nameRes),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = stringResource(R.string.hypertrophy_weekly_sets, volume.weeklySets, volume.maxThreshold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = statusContainerColor
                ) {
                    Text(
                        text = stringResource(volume.status.labelRes),
                        color = statusColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Multi-tier progress bar showing:
        // - Grey background track
        // - Colored progress up to 20 sets (maxThreshold)
        LinearProgressIndicator(
            progress = { volume.progressWithinRange },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = statusColor,
            trackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            strokeCap = StrokeCap.Round
        )
    }
}
