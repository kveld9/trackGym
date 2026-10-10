package com.kveld9.trackgym.ui.screens.settings

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.data.ThemePreferences

@Composable
fun AppearanceCard(
    themeMode: String,
    dynamicColor: Boolean,
    exerciseLanguage: String,
    onSetThemeMode: (String) -> Unit,
    onSetDynamicColor: (Boolean) -> Unit,
    onSetExerciseLanguage: (String) -> Unit,
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
            ThemeModeRow(
                themeMode = themeMode,
                onSetThemeMode = onSetThemeMode
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                SettingsSwitchRow(
                    icon = Icons.Default.AutoFixHigh,
                    title = stringResource(R.string.setting_dynamic_color_title),
                    description = stringResource(R.string.setting_dynamic_color_desc),
                    checked = dynamicColor,
                    onCheckedChange = onSetDynamicColor
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            ExerciseLanguageRow(
                exerciseLanguage = exerciseLanguage,
                onSetExerciseLanguage = onSetExerciseLanguage
            )
        }
    }
}

@Composable
private fun ThemeModeRow(
    themeMode: String,
    onSetThemeMode: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Palette,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(R.string.setting_theme_title),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }

    val themeOptions = listOf(
        Triple(ThemePreferences.MODE_LIGHT, stringResource(R.string.theme_mode_light), Icons.Default.LightMode),
        Triple(ThemePreferences.MODE_DARK, stringResource(R.string.theme_mode_dark), Icons.Default.DarkMode),
        Triple(ThemePreferences.MODE_AMOLED, stringResource(R.string.theme_mode_amoled), Icons.Default.Contrast)
    )

    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        themeOptions.forEachIndexed { index, (mode, label, icon) ->
            val isSelected = themeMode == mode
            SegmentedButton(
                selected = isSelected,
                onClick = { onSetThemeMode(mode) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = themeOptions.size),
                icon = {
                    SegmentedButtonDefaults.Icon(active = isSelected) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                        )
                    }
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
private fun ExerciseLanguageRow(
    exerciseLanguage: String,
    onSetExerciseLanguage: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.setting_exercise_language_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.setting_exercise_language_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    val languageOptions = listOf(
        ThemePreferences.EXERCISE_LANG_SYSTEM to stringResource(R.string.exercise_lang_system),
        ThemePreferences.EXERCISE_LANG_ENGLISH to stringResource(R.string.exercise_lang_english)
    )

    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        languageOptions.forEachIndexed { index, (lang, label) ->
            val isSelected = exerciseLanguage.equals(lang, ignoreCase = true)
            SegmentedButton(
                selected = isSelected,
                onClick = { onSetExerciseLanguage(lang) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = languageOptions.size),
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
