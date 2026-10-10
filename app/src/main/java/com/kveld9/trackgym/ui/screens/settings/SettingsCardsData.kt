package com.kveld9.trackgym.ui.screens.settings

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.OneRepMaxFormula

@Composable
fun OneRepMaxSelectionDialog(
    currentFormula: String,
    onSelectFormula: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.setting_orm_formula_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                OneRepMaxFormula.entries.forEach { formula ->
                    val isSelected = currentFormula.equals(formula.name, ignoreCase = true)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .clickable { onSelectFormula(formula.name); onDismiss() }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = isSelected, onClick = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(formula.displayNameRes),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                            Text(
                                text = formula.formulaExpression,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
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
fun BackupCard(
    autoBackupUri: String?,
    maxAutoBackups: Int,
    onExportClick: () -> Unit,
    onImportClick: () -> Unit,
    onSelectFolder: () -> Unit,
    onDisableAutoBackup: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SettingsActionRow(
                icon = Icons.Default.FileUpload,
                title = stringResource(R.string.setting_export_data_title),
                description = stringResource(R.string.setting_export_data_desc),
                onClick = onExportClick
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            SettingsActionRow(
                icon = Icons.Default.FileDownload,
                title = stringResource(R.string.setting_import_data_title),
                description = stringResource(R.string.setting_import_data_desc),
                onClick = onImportClick
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            AutoBackupRow(
                autoBackupUri = autoBackupUri,
                maxAutoBackups = maxAutoBackups,
                onSelectFolder = onSelectFolder,
                onDisableAutoBackup = onDisableAutoBackup
            )
        }
    }
}

@Composable
private fun AutoBackupRow(
    autoBackupUri: String?,
    maxAutoBackups: Int,
    onSelectFolder: () -> Unit,
    onDisableAutoBackup: () -> Unit
) {
    val hasFolder = !autoBackupUri.isNullOrBlank()
    val folderName = if (hasFolder) {
        Uri.parse(autoBackupUri).lastPathSegment ?: autoBackupUri.orEmpty()
    } else {
        stringResource(R.string.setting_auto_backup_disabled)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp)
                .clickable(onClick = onSelectFolder),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.setting_auto_backup_title),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.setting_auto_backup_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AutoBackupActionRow(
            folderName = folderName,
            hasFolder = hasFolder,
            maxAutoBackups = maxAutoBackups,
            onSelectFolder = onSelectFolder,
            onDisableAutoBackup = onDisableAutoBackup
        )
    }
}

@Composable
private fun AutoBackupActionRow(
    folderName: String,
    hasFolder: Boolean,
    maxAutoBackups: Int,
    onSelectFolder: () -> Unit,
    onDisableAutoBackup: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = folderName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = if (hasFolder) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (hasFolder) {
                Text(
                    text = stringResource(R.string.setting_auto_backup_rotation_count, maxAutoBackups),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        val (btnText, btnColor, btnAction) = if (hasFolder) {
            Triple(
                stringResource(R.string.setting_auto_backup_disable),
                MaterialTheme.colorScheme.error,
                onDisableAutoBackup
            )
        } else {
            Triple(
                stringResource(R.string.setting_auto_backup_select_folder),
                MaterialTheme.colorScheme.primary,
                onSelectFolder
            )
        }
        TextButton(
            onClick = btnAction,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
            modifier = Modifier.defaultMinSize(minHeight = 48.dp)
        ) {
            Text(text = btnText, color = btnColor, fontSize = 12.sp)
        }
    }
}

@Composable
fun ExportFormatDialog(
    onDismiss: () -> Unit,
    onSelectJson: () -> Unit,
    onSelectCsv: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.export_format_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormatOptionRow(
                    title = stringResource(R.string.format_json_title),
                    description = stringResource(R.string.format_json_desc),
                    onClick = {
                        onSelectJson()
                        onDismiss()
                    }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                FormatOptionRow(
                    title = stringResource(R.string.format_csv_title),
                    description = stringResource(R.string.format_csv_desc),
                    onClick = {
                        onSelectCsv()
                        onDismiss()
                    }
                )
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
fun ImportFormatDialog(
    onDismiss: () -> Unit,
    onSelectJson: () -> Unit,
    onSelectCsv: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.import_format_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormatOptionRow(
                    title = stringResource(R.string.format_json_title),
                    description = stringResource(R.string.format_json_desc),
                    onClick = {
                        onSelectJson()
                        onDismiss()
                    }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                FormatOptionRow(
                    title = stringResource(R.string.format_csv_title),
                    description = stringResource(R.string.format_csv_desc),
                    onClick = {
                        onSelectCsv()
                        onDismiss()
                    }
                )
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
fun IntegrationsCard(
    healthConnectSync: Boolean,
    isAvailable: Boolean,
    onSyncChange: (Boolean) -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_health_connect),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.setting_health_connect_title),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(R.string.setting_health_connect_desc),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = healthConnectSync,
                    onCheckedChange = onSyncChange
                )
            }

            if (!isAvailable) {
                Text(
                    text = stringResource(R.string.setting_health_connect_unavailable),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AboutCard(
    versionName: String,
    onGitHubClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_version_info, versionName),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.app_author_info, "kveld9"),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.app_tagline),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            AboutActions(
                onGitHubClick = onGitHubClick,
                onPrivacyClick = onPrivacyClick
            )
        }
    }
}

@Composable
private fun AboutActions(
    onGitHubClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    FilledTonalButton(
        onClick = onGitHubClick,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.defaultMinSize(minHeight = 48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Code,
            contentDescription = "GitHub",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(stringResource(R.string.view_on_github, "kveld9/trackGym"))
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
            contentDescription = null,
            modifier = Modifier.size(14.dp)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedButton(
        onClick = onPrivacyClick,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.defaultMinSize(minHeight = 48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Security,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(stringResource(R.string.privacy_title))
    }
}
