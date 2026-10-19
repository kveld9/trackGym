package com.kveld9.trackgym.ui.screens.active

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.RoutineExercise
import com.kveld9.trackgym.domain.model.RoutineSetPlan
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.ui.theme.subtleBorder
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName

private enum class RoutineSetBadgeType {
    NORMAL,
    TOP_SET,
    BACK_OFF,
    WARMUP,
    DROP,
    OTHER
}

private object RoutineSetBadgeStyleResolver {
    @Composable
    fun resolve(badgeType: RoutineSetBadgeType, plan: RoutineSetPlan): Pair<String, Pair<Color, Color>> {
        val label = when (badgeType) {
            RoutineSetBadgeType.TOP_SET -> stringResource(R.string.routine_builder_top_set)
            RoutineSetBadgeType.BACK_OFF -> stringResource(R.string.routine_builder_back_off)
            RoutineSetBadgeType.WARMUP -> stringResource(R.string.set_type_warmup)
            RoutineSetBadgeType.DROP -> stringResource(R.string.set_type_drop)
            RoutineSetBadgeType.OTHER -> plan.setType.displayName
            RoutineSetBadgeType.NORMAL -> stringResource(R.string.routine_builder_normal_set)
        }
        val colors = when (badgeType) {
            RoutineSetBadgeType.TOP_SET -> {
                MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.onPrimaryContainer
            }
            RoutineSetBadgeType.BACK_OFF -> {
                MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
            }
            RoutineSetBadgeType.WARMUP -> {
                MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer
            }
            RoutineSetBadgeType.DROP -> {
                MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
            }
            else -> {
                MaterialTheme.colorScheme.surfaceContainerHighest to MaterialTheme.colorScheme.onSurfaceVariant
            }
        }
        return label to colors
    }
}

@Composable
fun RoutineExerciseBuilderCard(
    routineExercise: RoutineExercise,
    canMoveUp: Boolean,
    canMoveDown: Boolean,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onRemoveExercise: () -> Unit,
    onUpdateSetPlan: (setIndex: Int, plan: RoutineSetPlan) -> Unit,
    onAddSet: () -> Unit,
    onRemoveSet: (setIndex: Int) -> Unit,
    onDuplicateSet: (setIndex: Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = SolidColor(
                MaterialTheme.colorScheme.subtleBorder
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
                onRemove = onRemoveExercise
            )

            routineExercise.sets.forEachIndexed { setIndex, plan ->
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHighest
                )
                RoutineExerciseSetRow(
                    setIndex = setIndex,
                    plan = plan,
                    allPlans = routineExercise.sets,
                    canDelete = routineExercise.sets.size > 1,
                    onUpdatePlan = { updated -> onUpdateSetPlan(setIndex, updated) },
                    onRemoveSet = { onRemoveSet(setIndex) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            RoutineExerciseCardActions(
                onAddSet = onAddSet,
                onDuplicateLast = {
                    if (routineExercise.sets.isNotEmpty()) {
                        onDuplicateSet(routineExercise.sets.lastIndex)
                    }
                }
            )
        }
    }
}

@Composable
private fun RoutineExerciseSetRow(
    setIndex: Int,
    plan: RoutineSetPlan,
    allPlans: List<RoutineSetPlan>,
    canDelete: Boolean,
    onUpdatePlan: (RoutineSetPlan) -> Unit,
    onRemoveSet: () -> Unit
) {
    val badgeType = resolveRoutineSetBadgeType(setIndex, allPlans)

    Column(modifier = Modifier.fillMaxWidth()) {
        RoutineExerciseSetHeader(
            setIndex = setIndex,
            badgeType = badgeType,
            plan = plan,
            canDelete = canDelete,
            onToggleType = {
                val nextType = when (plan.setType) {
                    SetType.NORMAL -> SetType.WARMUP
                    SetType.WARMUP -> SetType.DROP
                    else -> SetType.NORMAL
                }
                onUpdatePlan(plan.copy(setType = nextType))
            },
            onRemoveSet = onRemoveSet
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val weightText = if (plan.weightKg % 1.0 == 0.0) {
                "${plan.weightKg.toInt()} kg"
            } else {
                "${plan.weightKg} kg"
            }
            RoutineSetStepper(
                label = stringResource(R.string.routine_builder_default_weight),
                valueText = weightText,
                onDecrement = {
                    val next = (plan.weightKg - 2.5).coerceAtLeast(0.0)
                    onUpdatePlan(plan.copy(weightKg = next))
                },
                onIncrement = {
                    val next = plan.weightKg + 2.5
                    onUpdatePlan(plan.copy(weightKg = next))
                }
            )

            RoutineSetStepper(
                label = stringResource(R.string.routine_builder_target_reps),
                valueText = plan.reps.toString(),
                onDecrement = {
                    if (plan.reps > 1) {
                        onUpdatePlan(plan.copy(reps = plan.reps - 1))
                    }
                },
                onIncrement = {
                    onUpdatePlan(plan.copy(reps = plan.reps + 1))
                }
            )
        }
    }
}

@Composable
private fun RoutineExerciseSetHeader(
    setIndex: Int,
    badgeType: RoutineSetBadgeType,
    plan: RoutineSetPlan,
    canDelete: Boolean,
    onToggleType: () -> Unit,
    onRemoveSet: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.routine_builder_set_n, setIndex + 1),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(8.dp))
            RoutineSetBadge(
                badgeType = badgeType,
                plan = plan,
                onToggleType = onToggleType
            )
        }

        IconButton(
            onClick = onRemoveSet,
            enabled = canDelete,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                tint = if (canDelete) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                },
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

private fun resolveSpecialSetType(setType: SetType): RoutineSetBadgeType? {
    return when (setType) {
        SetType.WARMUP -> RoutineSetBadgeType.WARMUP
        SetType.DROP -> RoutineSetBadgeType.DROP
        SetType.NORMAL -> null
        else -> RoutineSetBadgeType.OTHER
    }
}

private fun resolveWeightHierarchyBadge(
    setIndex: Int,
    weightKg: Double,
    plans: List<RoutineSetPlan>
): RoutineSetBadgeType {
    val maxWeight = plans.maxOfOrNull { it.weightKg } ?: 0.0
    val minWeight = plans.minOfOrNull { it.weightKg } ?: 0.0
    val isDiverse = maxWeight > minWeight && maxWeight > 0.0
    val topIndex = if (isDiverse) plans.indexOfFirst { it.weightKg == maxWeight } else -1

    return when {
        topIndex == -1 -> RoutineSetBadgeType.NORMAL
        weightKg == maxWeight -> RoutineSetBadgeType.TOP_SET
        setIndex > topIndex && weightKg < maxWeight -> RoutineSetBadgeType.BACK_OFF
        else -> RoutineSetBadgeType.NORMAL
    }
}

private fun resolveRoutineSetBadgeType(
    setIndex: Int,
    plans: List<RoutineSetPlan>
): RoutineSetBadgeType {
    val plan = plans.getOrNull(setIndex) ?: return RoutineSetBadgeType.NORMAL
    val special = resolveSpecialSetType(plan.setType)
    return special ?: resolveWeightHierarchyBadge(setIndex, plan.weightKg, plans)
}

@Composable
private fun RoutineSetBadge(
    badgeType: RoutineSetBadgeType,
    plan: RoutineSetPlan,
    onToggleType: () -> Unit
) {
    val (label, colors) = RoutineSetBadgeStyleResolver.resolve(badgeType, plan)
    val (containerColor, contentColor) = colors

    Surface(
        onClick = onToggleType,
        shape = RoundedCornerShape(6.dp),
        color = containerColor,
        modifier = Modifier.defaultMinSize(minHeight = 24.dp)
    ) {
        Text(
            text = label,
            color = contentColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun RoutineExerciseCardActions(
    onAddSet: () -> Unit,
    onDuplicateLast: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onAddSet,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.routine_builder_add_set),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = onDuplicateLast,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                Icons.Default.ContentCopy,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.routine_builder_duplicate_last),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
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
private fun RoutineSetStepper(
    label: String,
    valueText: String,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onDecrement,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = valueText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = onIncrement,
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
