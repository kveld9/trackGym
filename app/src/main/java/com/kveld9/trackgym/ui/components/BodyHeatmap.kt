package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.MuscleGroupVolume
import com.kveld9.trackgym.domain.model.MuscleHeatmapState

private const val BODY_ASPECT_RATIO = 364f / 858f

@Composable
fun MuscleHeatmapCard(
    state: MuscleHeatmapState,
    title: String,
    subtitle: String? = null,
    emptyMessage: String? = null,
    modifier: Modifier = Modifier
) {
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
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            if (!subtitle.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            BodyHeatmap(state = state)

            if (state.isEmpty && !emptyMessage.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = emptyMessage,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (state.muscleGroupVolumes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                MuscleVolumesBreakdown(volumes = state.muscleGroupVolumes)
            }
        }
    }
}

@Composable
fun BodyHeatmap(
    state: MuscleHeatmapState,
    modifier: Modifier = Modifier,
    highlightColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            BodyFigure(
                isFront = true,
                state = state,
                highlightColor = highlightColor,
                modifier = Modifier
                    .height(180.dp)
                    .aspectRatio(BODY_ASPECT_RATIO)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.heatmap_front_view),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            BodyFigure(
                isFront = false,
                state = state,
                highlightColor = highlightColor,
                modifier = Modifier
                    .height(180.dp)
                    .aspectRatio(BODY_ASPECT_RATIO)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.heatmap_back_view),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun BodyFigure(
    isFront: Boolean,
    state: MuscleHeatmapState,
    highlightColor: Color,
    modifier: Modifier = Modifier
) {
    val baseDrawable = if (isFront) R.drawable.body_silhouette_front else R.drawable.body_silhouette_back

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = baseDrawable),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        BodyMuscle.entries.forEach { muscle ->
            val overlayRes = if (isFront) muscle.frontRes else muscle.backRes
            val intensity = state.getIntensity(muscle)

            if (overlayRes != null && intensity > 0f) {
                val tintedColor = highlightColor.copy(alpha = intensity)
                Image(
                    painter = painterResource(id = overlayRes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(tintedColor, BlendMode.SrcAtop),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MuscleVolumesBreakdown(
    volumes: List<MuscleGroupVolume>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        volumes.forEach { volume ->
            MuscleVolumeRow(volume = volume)
        }
    }
}

@Composable
private fun MuscleVolumeRow(volume: MuscleGroupVolume) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(volume.muscleGroup.nameRes),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(90.dp)
        )

        LinearProgressIndicator(
            progress = { volume.percentage.coerceIn(0f, 1f) },
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            strokeCap = StrokeCap.Round
        )

        val setsText = if (volume.setsCount == 1) {
            stringResource(R.string.heatmap_sets_single)
        } else {
            stringResource(R.string.heatmap_sets_count, volume.setsCount)
        }

        Text(
            text = setsText,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.width(60.dp)
        )
    }
}
