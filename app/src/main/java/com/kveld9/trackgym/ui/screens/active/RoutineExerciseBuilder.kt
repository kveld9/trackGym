package com.kveld9.trackgym.ui.screens.active

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.RoutineExercise
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName

@Composable
fun RoutineExerciseBuilderCard(
    routineExercise: RoutineExercise,
    canMoveUp: Boolean,
    canMoveDown: Boolean,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onRemove: () -> Unit,
    onUpdate: (Int, Double, Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = SolidColor(
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            RoutineExerciseHeaderRow(
                routineExercise = routineExercise,
                canMoveUp = canMoveUp,
                canMoveDown = canMoveDown,
                onMoveUp = onMoveUp,
                onMoveDown = onMoveDown,
                onRemove = onRemove
            )

            RoutineExerciseSteppersRow(
                routineExercise = routineExercise,
                onUpdate = onUpdate
            )
        }
    }
}

@Composable
private fun RoutineExerciseSteppersRow(
    routineExercise: RoutineExercise,
    onUpdate: (Int, Double, Int) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SetsStepper(
            targetSets = routineExercise.targetSets,
            onUpdateSets = { nextSets ->
                onUpdate(
                    nextSets,
                    routineExercise.defaultWeightKg,
                    routineExercise.defaultReps
                )
            }
        )

        WeightStepper(
            defaultWeightKg = routineExercise.defaultWeightKg,
            onUpdateWeight = { nextWeight ->
                onUpdate(
                    routineExercise.targetSets,
                    nextWeight,
                    routineExercise.defaultReps
                )
            }
        )

        RepsStepper(
            defaultReps = routineExercise.defaultReps,
            onUpdateReps = { nextReps ->
                onUpdate(
                    routineExercise.targetSets,
                    routineExercise.defaultWeightKg,
                    nextReps
                )
            }
        )
    }
}

@Composable
private fun RoutineExerciseHeaderRow(
    routineExercise: RoutineExercise,
    canMoveUp: Boolean,
    canMoveDown: Boolean,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onRemove: () -> Unit
) {
    val context = LocalContext.current
    val keepEnglish = LocalKeepEnglishExerciseNames.current
    val muscleName = stringResource(routineExercise.exercise.muscleGroup.nameRes)
    val categoryName = stringResource(routineExercise.exercise.category.nameRes)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = routineExercise.exercise.displayName(context, keepEnglish),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "$muscleName • $categoryName",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row {
            IconButton(
                onClick = onMoveUp,
                enabled = canMoveUp,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
            }
            IconButton(
                onClick = onMoveDown,
                enabled = canMoveDown,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
            }
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun SetsStepper(
    targetSets: Int,
    onUpdateSets: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.routine_builder_target_sets),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    if (targetSets > 1) {
                        onUpdateSets(targetSets - 1)
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = "$targetSets",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = {
                    onUpdateSets(targetSets + 1)
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun WeightStepper(
    defaultWeightKg: Double,
    onUpdateWeight: (Double) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.routine_builder_default_weight),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    val next = (defaultWeightKg - 2.5).coerceAtLeast(0.0)
                    onUpdateWeight(next)
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            val weightText = if (defaultWeightKg % 1.0 == 0.0) {
                "${defaultWeightKg.toInt()} kg"
            } else {
                "$defaultWeightKg kg"
            }
            Text(
                text = weightText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = {
                    val next = defaultWeightKg + 2.5
                    onUpdateWeight(next)
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun RepsStepper(
    defaultReps: Int,
    onUpdateReps: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.routine_builder_target_reps),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    if (defaultReps > 1) {
                        onUpdateReps(defaultReps - 1)
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = "$defaultReps",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = {
                    onUpdateReps(defaultReps + 1)
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
