package com.kveld9.trackgym.ui.screens.active

import java.util.Locale
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.material.icons.filled.Tune
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.domain.model.WorkoutExercise
import com.kveld9.trackgym.domain.model.WorkoutSet
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.domain.calculator.CardioCalculator
import com.kveld9.trackgym.domain.calculator.ProgressiveOverloadEngine
import com.kveld9.trackgym.domain.calculator.ProgressiveOverloadRecommendation
import com.kveld9.trackgym.domain.model.DistanceUnit
import com.kveld9.trackgym.domain.model.RpeScale
import com.kveld9.trackgym.ui.components.PinnedExerciseNotesCard
import com.kveld9.trackgym.domain.calculator.AutoProgressionEngine
import com.kveld9.trackgym.domain.calculator.AutoProgressionResult
import com.kveld9.trackgym.domain.calculator.ProgressionDecisionType
import com.kveld9.trackgym.ui.components.RpeSelectionDialog
import com.kveld9.trackgym.ui.theme.GymBlue
import com.kveld9.trackgym.ui.theme.GymWarmupAmber
import com.kveld9.trackgym.ui.theme.GymAmrapCrimson
import com.kveld9.trackgym.ui.theme.GymCardioTeal
import com.kveld9.trackgym.ui.util.LocalActiveGymProfile
import com.kveld9.trackgym.ui.util.displayName

@Composable
fun WorkoutExerciseCard(
    workoutExercise: WorkoutExercise,
    weightUnit: WeightUnit = WeightUnit.KG,
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
    previousSets: List<WorkoutSet> = emptyList(),
    getReadySeconds: Int = 0,
    audioCuePlayer: com.kveld9.trackgym.ui.audio.WorkoutAudioCuePlayer? = null,
    timerSoundName: String = "DIGITAL_BEEP",
    canMoveUp: Boolean = false,
    canMoveDown: Boolean = false,
    onMoveUp: () -> Unit = {},
    onMoveDown: () -> Unit = {},
    onSwapExercise: () -> Unit = {},
    onAddSet: () -> Unit,
    onDuplicateSet: () -> Unit = {},
    onUpdateSet: (WorkoutSet) -> Unit,
    onUpdateExerciseNotes: (String) -> Unit = {},
    onToggleComplete: (WorkoutSet, Double, Int) -> Unit,
    onDeleteSet: (WorkoutSet) -> Unit,
    onRemoveExercise: () -> Unit,
    showInlinePlates: Boolean = true,
    onOpenPlateCalculator: () -> Unit = {},
    onOpenPlateCalculatorForWeight: (Double) -> Unit = {},
    onOpenRpeCalculator: (WorkoutSet?) -> Unit = {},
    onAddWarmupSets: () -> Unit = {},
    onConfigureWarmupRamp: () -> Unit = {},
    onConfigureAutoProgression: () -> Unit = {},
    onSetRestDuration: () -> Unit = {},
    onSetSupersetGroup: () -> Unit = {},
    onOpenTechniqueGuide: () -> Unit = {},
    onOpenExerciseHistory: () -> Unit = {},
    onOpenWheelPicker: (WorkoutSet) -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }

    val supersetColor = when (workoutExercise.supersetGroupId) {
        "A" -> MaterialTheme.colorScheme.primary
        "B" -> GymBlue
        "C" -> GymWarmupAmber
        "D" -> MaterialTheme.colorScheme.tertiary
        else -> null
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = if (supersetColor != null) {
            BorderStroke(2.dp, supersetColor)
        } else {
            CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Exercise Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = workoutExercise.exercise.displayName(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${stringResource(workoutExercise.exercise.muscleGroup.nameRes)} • ${stringResource(workoutExercise.exercise.category.nameRes)}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        workoutExercise.exercise.restDurationSeconds?.let { restSec ->
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 48.dp)
                                    .clickable { onSetRestDuration() }
                            ) {
                                Text(
                                    text = stringResource(R.string.exercise_rest_badge, restSec),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.exercise.warmupRampProtocol?.let {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GymWarmupAmber.copy(alpha = 0.2f),
                                border = BorderStroke(1.dp, GymWarmupAmber),
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 48.dp)
                                    .clickable { onConfigureWarmupRamp() }
                            ) {
                                Text(
                                    text = stringResource(R.string.badge_custom_warmup),
                                    color = GymWarmupAmber,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.exercise.autoProgressionRule?.let {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 48.dp)
                                    .clickable { onConfigureAutoProgression() }
                            ) {
                                Text(
                                    text = stringResource(R.string.badge_auto),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        workoutExercise.supersetGroupId?.let { groupId ->
                            val badgeColor = supersetColor ?: MaterialTheme.colorScheme.primary
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = badgeColor.copy(alpha = 0.15f),
                                border = BorderStroke(1.dp, badgeColor),
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 48.dp)
                                    .clickable { onSetSupersetGroup() }
                            ) {
                                Text(
                                    text = stringResource(R.string.superset_label, groupId),
                                    color = badgeColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onOpenPlateCalculator,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = stringResource(R.string.action_plate_calculator),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

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
                            if (canMoveUp) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.menu_move_up), color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        menuExpanded = false
                                        onMoveUp()
                                    }
                                )
                            }
                            if (canMoveDown) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.menu_move_down), color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        menuExpanded = false
                                        onMoveDown()
                                    }
                                )
                            }
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_swap_exercise), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onSwapExercise()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_add_warmup_sets), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onAddWarmupSets()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_configure_warmup_ramp), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onConfigureWarmupRamp()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_auto_progression_rules), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onConfigureAutoProgression()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_set_rest_duration), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onSetRestDuration()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        if (workoutExercise.supersetGroupId != null)
                                            stringResource(R.string.menu_ungroup_superset)
                                        else
                                            stringResource(R.string.menu_group_superset),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    menuExpanded = false
                                    onSetSupersetGroup()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_plate_calculator), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenPlateCalculator()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_rpe_target_calculator), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenRpeCalculator(null)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_technique_guide), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenTechniqueGuide()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.action_exercise_history), color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    menuExpanded = false
                                    onOpenExerciseHistory()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_remove_exercise), color = MaterialTheme.colorScheme.error) },
                                onClick = {
                                    menuExpanded = false
                                    onRemoveExercise()
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Pinned Machine Setup & Notes
            PinnedExerciseNotesCard(
                notes = workoutExercise.exercise.notes,
                onNotesChange = onUpdateExerciseNotes
            )

            val autoProgressionRule = remember(workoutExercise.exercise.autoProgressionRule) {
                AutoProgressionEngine.decode(workoutExercise.exercise.autoProgressionRule)
            }
            val autoProgressionResult = remember(autoProgressionRule, previousSets, workoutExercise.sets) {
                if (autoProgressionRule != null && autoProgressionRule.enabled) {
                    val workingWeight = workoutExercise.sets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                        ?: workoutExercise.sets.firstOrNull { it.weightKg > 0.0 }?.weightKg
                        ?: previousSets.firstOrNull { it.weightKg > 0.0 && it.setType != SetType.WARMUP }?.weightKg
                        ?: 0.0
                    val historySessions = if (previousSets.isNotEmpty()) listOf(previousSets) else emptyList()
                    AutoProgressionEngine.evaluate(autoProgressionRule, historySessions, workingWeight)
                } else null
            }

            if (autoProgressionResult != null && autoProgressionResult.type != ProgressionDecisionType.MAINTAIN) {
                Spacer(modifier = Modifier.height(8.dp))
                AutoProgressionBadge(
                    result = autoProgressionResult,
                    weightUnit = weightUnit,
                    onApplyLoad = { targetKg ->
                        workoutExercise.sets.forEach { set ->
                            if (set.setType != SetType.WARMUP && !set.isCompleted) {
                                onUpdateSet(set.copy(weightKg = targetKg))
                            }
                        }
                    }
                )
            } else {
                val overloadRec = remember(previousSets, weightUnit) {
                    ProgressiveOverloadEngine.computeRecommendation(previousSets, weightUnit)
                }
                if (overloadRec != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressiveOverloadHintBadge(recommendation = overloadRec)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sets Table Header
            val isCardioExercise = workoutExercise.exercise.category == ExerciseCategory.CARDIO ||
                workoutExercise.sets.any { it.setType == SetType.CARDIO }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.table_header_set), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
                val col1Header = if (isCardioExercise) distanceUnit.symbol.uppercase() else weightUnit.symbol.uppercase()
                Text(col1Header, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                val hasDurationSets = workoutExercise.sets.any { it.setType == SetType.DURATION }
                val col2Header = when {
                    isCardioExercise -> stringResource(R.string.cardio_time_label).uppercase()
                    hasDurationSets -> stringResource(R.string.table_header_reps_or_time)
                    else -> stringResource(R.string.table_header_reps)
                }
                Text(
                    text = col2Header,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(stringResource(R.string.table_header_complete), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(48.dp), textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sets rows
            workoutExercise.sets.forEach { set ->
                val prevSet = previousSets.firstOrNull { it.setNumber == set.setNumber }
                    ?: previousSets.getOrNull(set.setNumber - 1)
                key(set.id) {
                    SwipeableSetRow(
                        set = set,
                        weightUnit = weightUnit,
                        distanceUnit = distanceUnit,
                        userBodyWeightKg = userBodyWeightKg,
                        previousSet = prevSet,
                        showInlinePlates = showInlinePlates,
                        getReadySeconds = getReadySeconds,
                        audioCuePlayer = audioCuePlayer,
                        timerSoundName = timerSoundName,
                        onOpenPlateCalculator = { targetKg -> onOpenPlateCalculatorForWeight(targetKg) },
                        onOpenRpeCalculator = { onOpenRpeCalculator(set) },
                        onOpenWheelPicker = { onOpenWheelPicker(set) },
                        onUpdateSet = onUpdateSet,
                        onToggleComplete = { w, r -> onToggleComplete(set, w, r) },
                        onDeleteSet = { onDeleteSet(set) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            SetActionButtonsRow(
                hasSets = workoutExercise.sets.isNotEmpty(),
                onAddSet = onAddSet,
                onDuplicateSet = onDuplicateSet
            )
        }
    }
}

@Composable
fun SetActionButtonsRow(
    hasSets: Boolean,
    onAddSet: () -> Unit,
    onDuplicateSet: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (hasSets) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onAddSet,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.btn_add_set), fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = onDuplicateSet,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.btn_duplicate_set), fontSize = 12.sp)
            }
        }
    } else {
        OutlinedButton(
            onClick = onAddSet,
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)))
        ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(stringResource(R.string.btn_add_set), fontSize = 13.sp)
        }
    }
}

@Composable
fun ProgressiveOverloadHintBadge(
    recommendation: ProgressiveOverloadRecommendation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.FitnessCenter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(
                R.string.overload_hint_body,
                recommendation.previousSummary,
                recommendation.suggestedTarget
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AutoProgressionBadge(
    result: AutoProgressionResult,
    weightUnit: WeightUnit,
    onApplyLoad: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val isProgression = result.type == ProgressionDecisionType.PROGRESSION
    val badgeColor = if (isProgression) MaterialTheme.colorScheme.primary else GymWarmupAmber
    val icon = if (isProgression) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    val title = if (isProgression) {
        stringResource(R.string.auto_progression_badge_progression, weightUnit.format(result.recommendedWeightKg))
    } else {
        stringResource(R.string.auto_progression_badge_deload, weightUnit.format(result.recommendedWeightKg))
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = badgeColor.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, badgeColor.copy(alpha = 0.5f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = badgeColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor
                    )
                    Text(
                        text = stringResource(result.explanationRes),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        lineHeight = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = badgeColor,
                modifier = Modifier
                    .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                    .clickable { onApplyLoad(result.recommendedWeightKg) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.auto_progression_action_apply_load),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isProgression) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableSetRow(
    set: WorkoutSet,
    weightUnit: WeightUnit,
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
    previousSet: WorkoutSet?,
    showInlinePlates: Boolean = true,
    getReadySeconds: Int = 0,
    audioCuePlayer: com.kveld9.trackgym.ui.audio.WorkoutAudioCuePlayer? = null,
    timerSoundName: String = "DIGITAL_BEEP",
    onOpenPlateCalculator: (Double) -> Unit = {},
    onOpenRpeCalculator: () -> Unit = {},
    onOpenWheelPicker: () -> Unit = {},
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (Double, Int) -> Unit,
    onDeleteSet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                onDeleteSet()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            val color = if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                MaterialTheme.colorScheme.errorContainer
            } else {
                Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.action_delete_set),
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        },
        modifier = modifier
    ) {
        SetRowItem(
            set = set,
            weightUnit = weightUnit,
            distanceUnit = distanceUnit,
            userBodyWeightKg = userBodyWeightKg,
            previousSet = previousSet,
            showInlinePlates = showInlinePlates,
            getReadySeconds = getReadySeconds,
            audioCuePlayer = audioCuePlayer,
            timerSoundName = timerSoundName,
            onOpenPlateCalculator = onOpenPlateCalculator,
            onOpenRpeCalculator = onOpenRpeCalculator,
            onOpenWheelPicker = onOpenWheelPicker,
            onUpdateSet = onUpdateSet,
            onToggleComplete = onToggleComplete,
            onDeleteSet = onDeleteSet
        )
    }
}

@Composable
fun SetRowItem(
    set: WorkoutSet,
    weightUnit: WeightUnit = WeightUnit.KG,
    distanceUnit: DistanceUnit = DistanceUnit.KM,
    userBodyWeightKg: Double = 70.0,
    previousSet: WorkoutSet? = null,
    showInlinePlates: Boolean = true,
    getReadySeconds: Int = 0,
    audioCuePlayer: com.kveld9.trackgym.ui.audio.WorkoutAudioCuePlayer? = null,
    timerSoundName: String = "DIGITAL_BEEP",
    onOpenPlateCalculator: (Double) -> Unit = {},
    onOpenRpeCalculator: () -> Unit = {},
    onOpenWheelPicker: () -> Unit = {},
    onUpdateSet: (WorkoutSet) -> Unit,
    onToggleComplete: (Double, Int) -> Unit,
    onDeleteSet: () -> Unit
) {
    var weightText by remember(set.id) {
        mutableStateOf(
            if (set.weightKg > 0.0 || (set.isCompleted && set.weightKg == 0.0)) {
                weightUnit.formatValue(set.weightKg)
            } else ""
        )
    }
    LaunchedEffect(set.weightKg, weightUnit) {
        val currentParsed = weightText.replace(',', '.').toDoubleOrNull()
        if (set.weightKg == 0.0 && weightText.isNotBlank() && currentParsed == 0.0) {
            return@LaunchedEffect
        }
        val expected = if (set.weightKg > 0.0 || (set.isCompleted && set.weightKg == 0.0)) {
            weightUnit.formatValue(set.weightKg)
        } else ""
        val expectedParsed = expected.toDoubleOrNull()
        if (currentParsed == null || expectedParsed == null || kotlin.math.abs(currentParsed - expectedParsed) > 0.001) {
            weightText = expected
        }
    }
    val isDuration = set.setType == SetType.DURATION
    val isCardio = set.setType == SetType.CARDIO
    var isStopwatchRunning by remember { mutableStateOf(false) }
    var getReadyCountdown by remember { mutableStateOf<Int?>(null) }
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(getReadyCountdown) {
        val count = getReadyCountdown ?: return@LaunchedEffect
        if (count > 0) {
            if (count <= 3) {
                val sound = runCatching {
                    com.kveld9.trackgym.ui.audio.TimerSound.valueOf(timerSoundName)
                }.getOrDefault(com.kveld9.trackgym.ui.audio.TimerSound.DIGITAL_BEEP)
                audioCuePlayer?.playWarningBeep(sound)
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            }
            kotlinx.coroutines.delay(1000)
            getReadyCountdown = count - 1
        } else {
            val sound = runCatching {
                com.kveld9.trackgym.ui.audio.TimerSound.valueOf(timerSoundName)
            }.getOrDefault(com.kveld9.trackgym.ui.audio.TimerSound.DIGITAL_BEEP)
            audioCuePlayer?.playFinishedSound(sound)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            getReadyCountdown = null
            if (isDuration || isCardio) {
                isStopwatchRunning = true
            }
        }
    }

    var distanceText by remember(set.id, set.distanceKm) {
        mutableStateOf(
            if (set.distanceKm != null && set.distanceKm > 0.0) {
                val distDisplay = distanceUnit.fromKm(set.distanceKm)
                if (distDisplay % 1.0 == 0.0) distDisplay.toInt().toString()
                else String.format(Locale.US, "%.2f", distDisplay)
            } else ""
        )
    }

    val currentSetState by androidx.compose.runtime.rememberUpdatedState(set)
    val currentOnUpdateSet by androidx.compose.runtime.rememberUpdatedState(onUpdateSet)
    LaunchedEffect(isStopwatchRunning) {
        if (isStopwatchRunning) {
            var elapsed = currentSetState.durationSeconds ?: currentSetState.reps
            while (isStopwatchRunning) {
                kotlinx.coroutines.delay(1000)
                elapsed++
                val cal = CardioCalculator.calculateCaloriesBurned(elapsed, userBodyWeightKg)
                val latest = currentSetState
                currentOnUpdateSet(
                    latest.copy(
                        durationSeconds = elapsed,
                        reps = elapsed,
                        caloriesBurned = if (isCardio) cal else latest.caloriesBurned
                    )
                )
            }
        }
    }

    val ghostWeightDisplay = if (previousSet != null && previousSet.weightKg > 0.0) {
        weightUnit.formatValue(previousSet.weightKg)
    } else null

    val effectiveWeightDisplay = weightText.replace(',', '.').toDoubleOrNull()
        ?: if (set.weightKg > 0.0) weightUnit.fromKg(set.weightKg)
        else (ghostWeightDisplay?.replace(',', '.')?.toDoubleOrNull() ?: 0.0)

    val currentActiveGymProfile = LocalActiveGymProfile.current
    val compactPlates = remember(effectiveWeightDisplay, weightUnit, isCardio, set.setType, showInlinePlates, currentActiveGymProfile) {
        if (showInlinePlates && !isCardio && set.setType != SetType.CARDIO && set.setType != SetType.DURATION &&
            set.setType != SetType.BODYWEIGHT_LOAD && set.setType != SetType.BODYWEIGHT_ASSISTED &&
            effectiveWeightDisplay > 0.0
        ) {
            com.kveld9.trackgym.domain.calculator.PlateCalculator.formatCompactPlatesPerSide(
                targetWeight = effectiveWeightDisplay,
                barWeight = currentActiveGymProfile.barWeight(weightUnit),
                availablePlates = currentActiveGymProfile.availablePlates(weightUnit),
                unit = weightUnit
            )
        } else null
    }
    val ghostDistanceDisplay = if (previousSet != null && previousSet.distanceKm != null && previousSet.distanceKm > 0.0) {
        val distDisplay = distanceUnit.fromKm(previousSet.distanceKm)
        if (distDisplay % 1.0 == 0.0) distDisplay.toInt().toString()
        else String.format(Locale.US, "%.2f", distDisplay)
    } else null
    val ghostRepsDisplay = if (previousSet != null && previousSet.reps > 0) {
        if (isDuration || isCardio) com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(previousSet.durationSeconds ?: previousSet.reps)
        else previousSet.reps.toString()
    } else null

    var repsText by remember(set.id, set.reps, set.durationSeconds, isDuration, isCardio) {
        mutableStateOf(
            if (isDuration || isCardio) {
                val sec = set.durationSeconds ?: set.reps
                if (sec > 0) com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(sec) else ""
            } else {
                if (set.reps > 0) set.reps.toString() else ""
            }
        )
    }

    var showSetTypePicker by remember { mutableStateOf(false) }

    val checkBgColor by animateColorAsState(
        targetValue = if (set.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
        label = "checkColor"
    )

    val badgeColor = when (set.setType) {
        SetType.NORMAL -> if (set.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        SetType.WARMUP -> GymWarmupAmber
        SetType.DROP -> GymBlue
        SetType.FAILURE -> MaterialTheme.colorScheme.error
        SetType.MYO_REPS -> MaterialTheme.colorScheme.tertiary
        SetType.DURATION -> GymBlue
        SetType.BODYWEIGHT_LOAD -> GymWarmupAmber
        SetType.BODYWEIGHT_ASSISTED -> MaterialTheme.colorScheme.tertiary
        SetType.AMRAP -> GymAmrapCrimson
        SetType.CARDIO -> GymCardioTeal
    }

    val badgeLabel = when (set.setType) {
        SetType.NORMAL -> "${set.setNumber}"
        SetType.WARMUP -> "W"
        SetType.DROP -> "D"
        SetType.FAILURE -> "F"
        SetType.MYO_REPS -> "M"
        SetType.DURATION -> "T"
        SetType.BODYWEIGHT_LOAD -> "B+"
        SetType.BODYWEIGHT_ASSISTED -> "B-"
        SetType.AMRAP -> "${set.setNumber}+"
        SetType.CARDIO -> "C"
    }

    var showQuickAdjust by remember { mutableStateOf(false) }
    var showRpePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (set.isCompleted) MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surfaceContainer)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Set number / SetType badge (Interactive with 48x48 dp touch target)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (set.setType != SetType.NORMAL) badgeColor.copy(alpha = 0.15f) else Color.Transparent)
                    .clickable { showSetTypePicker = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeLabel,
                    color = badgeColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                DropdownMenu(
                    expanded = showSetTypePicker,
                    onDismissRequest = { showSetTypePicker = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    SetType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = type.shortLabel,
                                        color = when (type) {
                                            SetType.NORMAL -> MaterialTheme.colorScheme.primary
                                            SetType.WARMUP -> GymWarmupAmber
                                            SetType.DROP -> GymBlue
                                            SetType.FAILURE -> MaterialTheme.colorScheme.error
                                            SetType.MYO_REPS -> MaterialTheme.colorScheme.tertiary
                                            SetType.DURATION -> GymBlue
                                            SetType.BODYWEIGHT_LOAD -> GymWarmupAmber
                                            SetType.BODYWEIGHT_ASSISTED -> MaterialTheme.colorScheme.tertiary
                                            SetType.AMRAP -> GymAmrapCrimson
                                            SetType.CARDIO -> GymCardioTeal
                                        },
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(28.dp)
                                    )
                                    Text(
                                        text = stringResource(type.nameRes),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 14.sp
                                    )
                                }
                            },
                            onClick = {
                                showSetTypePicker = false
                                if (type != SetType.DURATION) {
                                    isStopwatchRunning = false
                                }
                                onUpdateSet(set.copy(setType = type))
                            }
                        )
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null,
                                    tint = GymWarmupAmber,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.action_get_ready_timer),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 14.sp
                                )
                            }
                        },
                        onClick = {
                            showSetTypePicker = false
                            getReadyCountdown = if (getReadySeconds > 0) getReadySeconds else 5
                        }
                    )
                }
            }

            // Weight / Distance Input with Ghost Placeholder & Micro-load Toggle
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                if (isCardio) {
                    OutlinedTextField(
                        value = distanceText,
                        onValueChange = { input ->
                            distanceText = input
                            val parsedDist = input.replace(',', '.').toDoubleOrNull()
                            val distKm = parsedDist?.let { distanceUnit.toKm(it) }
                            val durSec = set.durationSeconds ?: set.reps
                            val cal = CardioCalculator.calculateCaloriesBurned(durSec, userBodyWeightKg)
                            onUpdateSet(set.copy(distanceKm = distKm, caloriesBurned = cal))
                        },
                        placeholder = {
                            Text(
                                text = ghostDistanceDisplay ?: "0.0",
                                color = if (ghostDistanceDisplay != null) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                }
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp),
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    OutlinedTextField(
                        value = weightText,
                        onValueChange = { input ->
                            weightText = input
                            val parsedDisplay = input.replace(',', '.').toDoubleOrNull() ?: 0.0
                            val inKg = weightUnit.toKg(parsedDisplay)
                            onUpdateSet(set.copy(weightKg = inKg))
                        },
                        placeholder = {
                            val weightPlaceholder = when (set.setType) {
                                SetType.BODYWEIGHT_LOAD -> ghostWeightDisplay ?: "+0"
                                SetType.BODYWEIGHT_ASSISTED -> ghostWeightDisplay ?: "-0"
                                else -> ghostWeightDisplay ?: "0"
                            }
                            Text(
                                text = weightPlaceholder,
                                color = if (ghostWeightDisplay != null) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                }
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { showQuickAdjust = !showQuickAdjust },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = stringResource(R.string.desc_quick_adjust),
                                    tint = if (showQuickAdjust) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    showQuickAdjust = true
                                }
                            },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }

            // Reps / Time Input with Ghost Placeholder & In-set Stopwatch
            Box(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                OutlinedTextField(
                    value = repsText,
                    onValueChange = { input ->
                        repsText = input
                        if (isDuration || isCardio) {
                            val parsedSec = com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(input)
                            val distKm = set.distanceKm ?: 0.0
                            val cal = if (isCardio) CardioCalculator.calculateCaloriesBurned(parsedSec, userBodyWeightKg) else set.caloriesBurned
                            onUpdateSet(set.copy(durationSeconds = parsedSec, reps = parsedSec, caloriesBurned = cal))
                        } else {
                            val parsed = input.toIntOrNull() ?: 0
                            onUpdateSet(set.copy(reps = parsed))
                        }
                    },
                    placeholder = {
                        val repsPlaceholder = when {
                            isDuration || isCardio -> "00:00"
                            set.setType == SetType.AMRAP -> (ghostRepsDisplay?.let { "$it+" } ?: "0+")
                            else -> ghostRepsDisplay ?: "0"
                        }
                        Text(
                            text = repsPlaceholder,
                            color = if (ghostRepsDisplay != null || isDuration || isCardio) {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                            }
                        )
                    },
                    trailingIcon = if (isDuration || isCardio) {
                        {
                            IconButton(
                                onClick = {
                                    if (isStopwatchRunning) {
                                        isStopwatchRunning = false
                                        getReadyCountdown = null
                                    } else if (getReadyCountdown != null) {
                                        getReadyCountdown = null
                                    } else if (getReadySeconds > 0) {
                                        getReadyCountdown = getReadySeconds
                                    } else {
                                        isStopwatchRunning = true
                                    }
                                },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = if (isStopwatchRunning) Icons.Default.Pause
                                        else if (getReadyCountdown != null) Icons.Default.Timer
                                        else Icons.Default.PlayArrow,
                                    contentDescription = if (isStopwatchRunning) stringResource(R.string.action_stop_stopwatch)
                                        else stringResource(R.string.action_start_stopwatch),
                                    tint = if (isStopwatchRunning) MaterialTheme.colorScheme.primary
                                        else if (getReadyCountdown != null) GymWarmupAmber
                                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    } else null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp),
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            // Complete Checkbox Button (48x48 dp minimum touch target)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(checkBgColor)
                    .clickable {
                        isStopwatchRunning = false
                        val finalWeightKg = if (isCardio) {
                            0.0
                        } else {
                            val typedDisplay = weightText.replace(',', '.').toDoubleOrNull()
                            if (typedDisplay != null) {
                                weightUnit.toKg(typedDisplay)
                            } else if (set.weightKg > 0.0) {
                                set.weightKg
                            } else if (previousSet != null && previousSet.weightKg > 0.0) {
                                previousSet.weightKg
                            } else {
                                0.0
                            }
                        }

                        val finalReps = if (isDuration || isCardio) {
                            val parsedSec = com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(repsText)
                            if (parsedSec > 0) parsedSec
                            else (set.durationSeconds ?: set.reps)
                        } else {
                            val typedReps = repsText.toIntOrNull()
                            if (typedReps != null) {
                                typedReps
                            } else if (set.reps > 0) {
                                set.reps
                            } else if (previousSet != null && previousSet.reps > 0) {
                                previousSet.reps
                            } else {
                                0
                            }
                        }

                        if (isCardio) {
                            val parsedDist = distanceText.replace(',', '.').toDoubleOrNull()
                                ?: ghostDistanceDisplay?.replace(',', '.')?.toDoubleOrNull()
                            val distKm = parsedDist?.let { distanceUnit.toKm(it) }
                            val cal = CardioCalculator.calculateCaloriesBurned(finalReps, userBodyWeightKg)
                            onUpdateSet(set.copy(distanceKm = distKm, caloriesBurned = cal))
                        } else {
                            if (weightText.isBlank()) {
                                weightText = weightUnit.formatValue(finalWeightKg)
                            }
                        }

                        if (repsText.isBlank() && finalReps > 0) {
                            repsText = if (isDuration || isCardio) {
                                com.kveld9.trackgym.domain.util.DurationFormatter.formatSecondsToMmSs(finalReps)
                            } else {
                                finalReps.toString()
                            }
                        }

                        showQuickAdjust = false
                        getReadyCountdown = null
                        onToggleComplete(finalWeightKg, finalReps)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.desc_complete_set),
                    tint = if (set.isCompleted) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (getReadyCountdown != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(GymWarmupAmber.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = GymWarmupAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.get_ready_countdown_active, getReadyCountdown ?: 0),
                        color = GymWarmupAmber,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                            .clickable {
                                getReadyCountdown = null
                                if (isDuration || isCardio) isStopwatchRunning = true
                            }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.rest_timer_skip),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                            .clickable { getReadyCountdown = null }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.action_cancel),
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SetRpeChip(
                rpe = set.rpe,
                onClick = { showRpePicker = true }
            )

            if (!isCardio && set.setType != SetType.CARDIO && set.setType != SetType.DURATION) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier
                        .clickable { onOpenWheelPicker() }
                        .padding(vertical = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = stringResource(R.string.wheel_picker_open),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.wheel_picker_open),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            if (compactPlates != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier
                        .clickable {
                            val inKg = weightUnit.toKg(effectiveWeightDisplay)
                            onOpenPlateCalculator(inKg)
                        }
                        .padding(vertical = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = stringResource(R.string.action_plate_calculator),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = compactPlates,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            if (isCardio) {
                val distanceVal = set.distanceKm ?: distanceText.replace(',', '.').toDoubleOrNull()?.let { distanceUnit.toKm(it) } ?: 0.0
                val durationSec = if (set.durationSeconds != null && set.durationSeconds > 0) set.durationSeconds else com.kveld9.trackgym.domain.util.DurationFormatter.parseInputToSeconds(repsText)
                if (distanceVal > 0.0 && durationSec > 0) {
                    val paceFormatted = CardioCalculator.formatPace(distanceVal, durationSec, distanceUnit)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cardio_pace_format, paceFormatted),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                val calories = set.caloriesBurned ?: if (durationSec > 0) CardioCalculator.calculateCaloriesBurned(durationSec, userBodyWeightKg) else 0
                if (calories > 0) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cardio_calories_format, calories),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Quick-adjust Micro-load Chips
        AnimatedVisibility(visible = showQuickAdjust && !set.isCompleted) {
            val currentDisplay = weightText.replace(',', '.').toDoubleOrNull()
                ?: ghostWeightDisplay?.replace(',', '.')?.toDoubleOrNull()
                ?: 0.0
            MicroLoadChipsRow(
                weightUnit = weightUnit,
                currentWeight = currentDisplay,
                onAdjust = { updatedDisplay ->
                    weightText = if (updatedDisplay % 1.0 == 0.0) {
                        "${updatedDisplay.toInt()}"
                    } else {
                        String.format(Locale.US, "%.2f", updatedDisplay).trimEnd('0').trimEnd('.')
                    }
                    val inKg = weightUnit.toKg(updatedDisplay)
                    onUpdateSet(set.copy(weightKg = inKg))
                },
                onClose = { showQuickAdjust = false }
            )
        }
    }

    if (showRpePicker) {
        RpeSelectionDialog(
            currentRpe = set.rpe,
            onSelectRpe = { selectedRpe ->
                onUpdateSet(set.copy(rpe = selectedRpe))
            },
            onOpenTargetCalculator = onOpenRpeCalculator,
            onDismiss = { showRpePicker = false }
        )
    }
}

@Composable
fun SetRpeChip(
    rpe: Double?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rpeStr = RpeScale.formatRpe(rpe)
    val rirLabel = if (rpe != null) RpeScale.options.firstOrNull { it.rpe == rpe }?.rirLabel else null
    val rpeLabel = when {
        rpeStr != null && rirLabel != null -> "@$rpeStr (RIR $rirLabel)"
        rpeStr != null -> "@$rpeStr"
        else -> stringResource(R.string.rpe_add)
    }

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (rpe != null) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = rpeLabel,
            color = if (rpe != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            fontSize = 11.sp,
            fontWeight = if (rpe != null) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun MicroLoadChipsRow(
    weightUnit: WeightUnit,
    currentWeight: Double,
    onAdjust: (Double) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val deltas = when (weightUnit) {
        WeightUnit.KG -> listOf(-5.0, -2.5, -1.25, 1.25, 2.5, 5.0)
        WeightUnit.LB -> listOf(-10.0, -5.0, -2.5, 2.5, 5.0, 10.0)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        deltas.forEach { delta ->
            MicroLoadDeltaChip(
                delta = delta,
                currentWeight = currentWeight,
                onAdjust = onAdjust
            )
        }

        IconButton(
            onClick = onClose,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun MicroLoadDeltaChip(
    delta: Double,
    currentWeight: Double,
    onAdjust: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val sign = if (delta > 0) "+" else ""
    val deltaLabel = "$sign${if (delta % 1.0 == 0.0) delta.toInt().toString() else delta.toString()}"
    val isPositive = delta > 0

    Surface(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                val updated = (currentWeight + delta).coerceAtLeast(0.0)
                val rounded = kotlin.math.round(updated * 100.0) / 100.0
                onAdjust(rounded)
            },
        shape = RoundedCornerShape(8.dp),
        color = if (isPositive) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        } else {
            MaterialTheme.colorScheme.surfaceContainerHigh
        },
        border = BorderStroke(
            1.dp,
            if (isPositive) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            } else {
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
            }
        )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = deltaLabel,
                color = if (isPositive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}
