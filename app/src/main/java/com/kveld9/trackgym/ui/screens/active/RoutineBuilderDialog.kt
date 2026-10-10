package com.kveld9.trackgym.ui.screens.active

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.RoutineBiomechanicalBalanceEngine
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.Routine

@Composable
fun RoutineBuilderDialog(
    routine: Routine,
    allExercises: List<Exercise>,
    onAddExercise: (Long) -> Unit,
    onRemoveExercise: (Long) -> Unit,
    onUpdateExercise: (Long, Int, Double, Int) -> Unit,
    onMoveExercise: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var showExercisePicker by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (showExercisePicker) {
                RoutineBuilderExercisePicker(
                    allExercises = allExercises,
                    onSelectExercise = { exerciseId ->
                        onAddExercise(exerciseId)
                        showExercisePicker = false
                    },
                    onBack = { showExercisePicker = false }
                )
            } else {
                RoutineBuilderContent(
                    routine = routine,
                    onOpenPicker = { showExercisePicker = true },
                    onDismiss = onDismiss,
                    onRemoveExercise = onRemoveExercise,
                    onUpdateExercise = onUpdateExercise,
                    onMoveExercise = onMoveExercise
                )
            }
        }
    }
}

@Composable
private fun RoutineBuilderExercisePicker(
    allExercises: List<Exercise>,
    onSelectExercise: (Long) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.action_cancel)
                )
            }
            Text(
                text = stringResource(R.string.title_select_exercise),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        ExercisePickerContent(
            exercises = allExercises,
            onSelectExercise = { exercise ->
                onSelectExercise(exercise.id)
            }
        )
    }
}

@Composable
private fun RoutineBuilderContent(
    routine: Routine,
    onOpenPicker: () -> Unit,
    onDismiss: () -> Unit,
    onRemoveExercise: (Long) -> Unit,
    onUpdateExercise: (Long, Int, Double, Int) -> Unit,
    onMoveExercise: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        RoutineBuilderHeader(
            routineName = routine.name,
            onDone = onDismiss
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (routine.exercises.isEmpty()) {
                item {
                    RoutineBuilderEmptyState()
                }
            } else {
                item {
                    RoutineBuilderBalanceBanner(exercises = routine.exercises.map { it.exercise })
                }

                itemsIndexed(
                    items = routine.exercises,
                    key = { _, exercise -> exercise.id }
                ) { index, re ->
                    RoutineExerciseBuilderCard(
                        routineExercise = re,
                        canMoveUp = index > 0,
                        canMoveDown = index < routine.exercises.size - 1,
                        onMoveUp = { onMoveExercise(index, index - 1) },
                        onMoveDown = { onMoveExercise(index, index + 1) },
                        onRemove = { onRemoveExercise(re.id) },
                        onUpdate = { sets, weight, reps ->
                            onUpdateExercise(re.id, sets, weight, reps)
                        }
                    )
                }
            }

            item {
                RoutineBuilderAddExerciseButton(onClick = onOpenPicker)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun RoutineBuilderHeader(
    routineName: String,
    onDone: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = routineName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.routine_builder_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Button(
            onClick = onDone,
            modifier = Modifier.defaultMinSize(minHeight = 48.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.action_done),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun RoutineBuilderEmptyState() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = SolidColor(
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.routine_builder_empty_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.routine_builder_empty_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun RoutineBuilderBalanceBanner(exercises: List<Exercise>) {
    val balance = remember(exercises) {
        RoutineBiomechanicalBalanceEngine.calculate(exercises)
    }
    val pushLabel = stringResource(R.string.force_push)
    val pullLabel = stringResource(R.string.force_pull)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = if (balance.isBalanced) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceContainerHighest
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(balance.balanceStatusRes),
                    color = if (balance.isBalanced) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Text(
                text = "${balance.pushCount} $pushLabel • ${balance.pullCount} $pullLabel",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun RoutineBuilderAddExerciseButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.routine_builder_add_exercise),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
