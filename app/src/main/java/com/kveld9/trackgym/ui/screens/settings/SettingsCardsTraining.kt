package com.kveld9.trackgym.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.StayCurrentPortrait
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.OneRepMaxFormula
import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import com.kveld9.trackgym.domain.model.WeightUnit

@Composable
fun TimerCard(
    autoRestTimer: Boolean,
    defaultRestSeconds: Int,
    timerSoundCountdown: Boolean,
    getReadySeconds: Int,
    onAutoRestChange: (Boolean) -> Unit,
    onDefaultRestChange: (Int) -> Unit,
    onCountdownBeepsChange: (Boolean) -> Unit,
    onGetReadyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsSwitchRow(
                icon = Icons.Default.Timer,
                title = stringResource(R.string.setting_auto_rest_timer_title),
                description = stringResource(R.string.setting_auto_rest_timer_desc),
                checked = autoRestTimer,
                onCheckedChange = onAutoRestChange
            )

            if (autoRestTimer) {
                AutoRestExpandedSection(
                    defaultRestSeconds = defaultRestSeconds,
                    timerSoundCountdown = timerSoundCountdown,
                    onDefaultRestChange = onDefaultRestChange,
                    onCountdownBeepsChange = onCountdownBeepsChange
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            val getReadyText = if (getReadySeconds == 0) {
                stringResource(R.string.get_ready_off)
            } else {
                "${getReadySeconds}s"
            }
            SettingsClickableRow(
                icon = Icons.Default.HourglassTop,
                title = stringResource(R.string.setting_get_ready_title),
                description = stringResource(R.string.setting_get_ready_desc),
                trailingText = getReadyText,
                onClick = onGetReadyClick
            )
        }
    }
}

@Composable
private fun AutoRestExpandedSection(
    defaultRestSeconds: Int,
    timerSoundCountdown: Boolean,
    onDefaultRestChange: (Int) -> Unit,
    onCountdownBeepsChange: (Boolean) -> Unit
) {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
    DefaultRestDurationSection(
        defaultRestSeconds = defaultRestSeconds,
        onDefaultRestChange = onDefaultRestChange
    )
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
    SettingsSwitchRow(
        icon = Icons.Default.Notifications,
        title = stringResource(R.string.setting_timer_countdown_title),
        description = stringResource(R.string.setting_timer_countdown_desc),
        checked = timerSoundCountdown,
        onCheckedChange = onCountdownBeepsChange
    )
}

@Composable
private fun DefaultRestDurationSection(
    defaultRestSeconds: Int,
    onDefaultRestChange: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.setting_default_rest_duration_title),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.setting_default_rest_duration_desc),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val restOptions = listOf(30, 60, 90, 120, 180)
        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            restOptions.forEachIndexed { index, durationSec ->
                val isSelected = defaultRestSeconds == durationSec
                SegmentedButton(
                    selected = isSelected,
                    onClick = { onDefaultRestChange(durationSec) },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = restOptions.size),
                    label = {
                        Text(
                            text = stringResource(R.string.rest_duration_seconds, durationSec),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SoundCard(
    timerSound: String,
    soundFeedbackOnComplete: Boolean,
    onTimerSoundClick: () -> Unit,
    onSoundFeedbackChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val soundLabelRes = when (timerSound) {
        "BOXING_BELL" -> R.string.sound_boxing_bell
        "TING_TING" -> R.string.sound_ting_ting
        "ALARM" -> R.string.sound_alarm
        "SILENT" -> R.string.sound_silent
        else -> R.string.sound_digital_beep
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsClickableRow(
                icon = Icons.AutoMirrored.Filled.VolumeUp,
                title = stringResource(R.string.setting_timer_sound_title),
                description = stringResource(R.string.setting_timer_sound_desc),
                trailingText = stringResource(soundLabelRes),
                onClick = onTimerSoundClick
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            SettingsSwitchRow(
                icon = Icons.Default.GraphicEq,
                title = stringResource(R.string.setting_sound_complete_title),
                description = stringResource(R.string.setting_sound_complete_desc),
                checked = soundFeedbackOnComplete,
                onCheckedChange = onSoundFeedbackChange
            )
        }
    }
}

@Composable
fun VolumeEquipmentCard(
    doubleDumbbell: Boolean,
    excludeWarmup: Boolean,
    showInlinePlates: Boolean,
    activeProfile: GymEquipmentProfile,
    weightUnit: WeightUnit,
    activeOrmFormula: OneRepMaxFormula,
    onDoubleDumbbellChange: (Boolean) -> Unit,
    onExcludeWarmupChange: (Boolean) -> Unit,
    onShowInlinePlatesChange: (Boolean) -> Unit,
    onManageProfilesClick: () -> Unit,
    onOrmFormulaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsSwitchRow(
                icon = Icons.Default.ContentCopy,
                title = stringResource(R.string.setting_double_dumbbell_title),
                description = stringResource(R.string.setting_double_dumbbell_desc),
                checked = doubleDumbbell,
                onCheckedChange = onDoubleDumbbellChange
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            SettingsSwitchRow(
                icon = Icons.Default.FilterAlt,
                title = stringResource(R.string.setting_exclude_warmup_title),
                description = stringResource(R.string.setting_exclude_warmup_desc),
                checked = excludeWarmup,
                onCheckedChange = onExcludeWarmupChange
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            SettingsSwitchRow(
                icon = Icons.Default.Layers,
                title = stringResource(R.string.setting_inline_plates_title),
                description = stringResource(R.string.setting_inline_plates_desc),
                checked = showInlinePlates,
                onCheckedChange = onShowInlinePlatesChange
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            EquipmentProfilesRow(
                activeProfile = activeProfile,
                weightUnit = weightUnit,
                onManageProfilesClick = onManageProfilesClick
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            val formulaName = stringResource(activeOrmFormula.displayNameRes)
            SettingsClickableRow(
                icon = Icons.Default.Functions,
                title = stringResource(R.string.setting_orm_formula_title),
                description = "$formulaName • ${activeOrmFormula.formulaExpression}",
                trailingText = formulaName,
                onClick = onOrmFormulaClick
            )
        }
    }
}

@Composable
private fun EquipmentProfilesRow(
    activeProfile: GymEquipmentProfile,
    weightUnit: WeightUnit,
    onManageProfilesClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.HomeRepairService,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.gym_profiles_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            val barDisplay = "${activeProfile.barWeight(weightUnit)} ${weightUnit.symbol}"
            Text(
                text = "${activeProfile.name} • $barDisplay",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
            onClick = onManageProfilesClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.defaultMinSize(minHeight = 48.dp)
        ) {
            Text(
                text = stringResource(R.string.gym_profile_select),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SessionCard(
    keepScreenOn: Boolean,
    routineUpdateMode: String,
    onKeepScreenOnChange: (Boolean) -> Unit,
    onRoutineUpdateModeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsSwitchRow(
                icon = Icons.Default.StayCurrentPortrait,
                title = stringResource(R.string.setting_keep_screen_on_title),
                description = stringResource(R.string.setting_keep_screen_on_desc),
                checked = keepScreenOn,
                onCheckedChange = onKeepScreenOnChange
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            RoutineSyncRow(
                routineUpdateMode = routineUpdateMode,
                onRoutineUpdateModeChange = onRoutineUpdateModeChange
            )
        }
    }
}

@Composable
private fun RoutineSyncRow(
    routineUpdateMode: String,
    onRoutineUpdateModeChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.setting_routine_update_mode_title),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.setting_routine_update_mode_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        val routineModes = listOf(
            "ALWAYS" to stringResource(R.string.routine_update_mode_always_title),
            "ASK" to stringResource(R.string.routine_update_mode_ask_title),
            "NEVER" to stringResource(R.string.routine_update_mode_never_title)
        )

        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            routineModes.forEachIndexed { index, (mode, label) ->
                val isSelected = routineUpdateMode.equals(mode, ignoreCase = true)
                SegmentedButton(
                    selected = isSelected,
                    onClick = { onRoutineUpdateModeChange(mode) },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = routineModes.size),
                    label = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun TimerSoundSelectionDialog(
    currentSound: String,
    onSelectSound: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val soundOptions = listOf(
        "DIGITAL_BEEP" to R.string.sound_digital_beep,
        "BOXING_BELL" to R.string.sound_boxing_bell,
        "TING_TING" to R.string.sound_ting_ting,
        "ALARM" to R.string.sound_alarm,
        "SILENT" to R.string.sound_silent
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.setting_timer_sound_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                soundOptions.forEach { (soundId, labelRes) ->
                    val isSelected = currentSound == soundId
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .clickable { onSelectSound(soundId); onDismiss() }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = isSelected, onClick = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(labelRes),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            ) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}

@Composable
fun GetReadySelectionDialog(
    currentSeconds: Int,
    onSelectSeconds: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val getReadyOptions = listOf(
        0 to stringResource(R.string.get_ready_off),
        3 to "3s",
        5 to "5s",
        10 to "10s",
        15 to "15s"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.setting_get_ready_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                getReadyOptions.forEach { (seconds, label) ->
                    val isSelected = currentSeconds == seconds
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .clickable { onSelectSeconds(seconds); onDismiss() }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = isSelected, onClick = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            ) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
