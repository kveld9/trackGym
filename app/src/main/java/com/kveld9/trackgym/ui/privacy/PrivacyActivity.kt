package com.kveld9.trackgym.ui.privacy

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FolderShared
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import com.kveld9.trackgym.R
import com.kveld9.trackgym.TrackGymApp
import com.kveld9.trackgym.data.ThemeSettings
import com.kveld9.trackgym.ui.theme.TrackGymTheme

class PrivacyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val app = application as TrackGymApp
        val themePreferences = app.themePreferences
        val isHealthConnectAvailable = app.healthConnectSyncManager?.isAvailable() == true

        setContent {
            val themeSettings by themePreferences.themeSettings.collectAsState(initial = ThemeSettings())

            TrackGymTheme(
                themeMode = themeSettings.themeMode,
                dynamicColor = themeSettings.dynamicColor
            ) {
                PrivacyScreen(
                    isHealthConnectAvailable = isHealthConnectAvailable,
                    isHealthConnectEnabled = themeSettings.healthConnectSync,
                    onManageNotifications = { openNotificationSettings() },
                    onBackClick = { finish() }
                )
            }
        }
    }

    private fun openNotificationSettings() {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
        } else {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
        }
        runCatching { startActivity(intent) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(
    isHealthConnectAvailable: Boolean,
    isHealthConnectEnabled: Boolean,
    onManageNotifications: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val areNotificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.privacy_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.action_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PrivacySectionHeader(title = stringResource(R.string.privacy_section_permissions))

            PermissionStatusCard(
                areNotificationsEnabled = areNotificationsEnabled,
                isHealthConnectAvailable = isHealthConnectAvailable,
                isHealthConnectEnabled = isHealthConnectEnabled,
                onManageNotifications = onManageNotifications
            )

            PrivacySectionHeader(title = stringResource(R.string.privacy_section_statement))

            PrivacyStatementCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PrivacySectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        letterSpacing = 0.8.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.padding(start = 4.dp)
    )
}

@Composable
private fun PermissionStatusCard(
    areNotificationsEnabled: Boolean,
    isHealthConnectAvailable: Boolean,
    isHealthConnectEnabled: Boolean,
    onManageNotifications: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            NotificationPermissionRow(
                areNotificationsEnabled = areNotificationsEnabled,
                onManageNotifications = onManageNotifications
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            StoragePermissionRow()

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            HealthConnectPermissionRow(
                isAvailable = isHealthConnectAvailable,
                isEnabled = isHealthConnectEnabled
            )
        }
    }
}

@Composable
private fun NotificationPermissionRow(
    areNotificationsEnabled: Boolean,
    onManageNotifications: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.privacy_notifications_title),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.privacy_notifications_compact_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PermissionBadge(
                text = stringResource(
                    if (areNotificationsEnabled) R.string.permission_status_granted
                    else R.string.permission_status_denied
                ),
                isPositive = areNotificationsEnabled
            )
            OutlinedButton(
                onClick = onManageNotifications,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(
                    text = stringResource(R.string.permission_action_manage),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun StoragePermissionRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.FolderShared,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.privacy_storage_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.privacy_storage_compact_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        PermissionBadge(
            text = stringResource(R.string.permission_status_not_required),
            isNeutral = true
        )
    }
}

@Composable
private fun HealthConnectPermissionRow(
    isAvailable: Boolean,
    isEnabled: Boolean
) {
    val statusText = when {
        !isAvailable -> stringResource(R.string.permission_status_unavailable)
        isEnabled -> stringResource(R.string.permission_status_granted)
        else -> stringResource(R.string.permission_status_available)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
                text = stringResource(R.string.privacy_health_connect_compact_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        PermissionBadge(
            text = statusText,
            isPositive = isAvailable
        )
    }
}

@Composable
private fun PermissionBadge(
    text: String,
    isPositive: Boolean = true,
    isNeutral: Boolean = false
) {
    val containerColor = when {
        isNeutral -> MaterialTheme.colorScheme.surfaceContainerHighest
        isPositive -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.errorContainer
    }
    val contentColor = when {
        isNeutral -> MaterialTheme.colorScheme.onSurfaceVariant
        isPositive -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.onErrorContainer
    }
    val border = if (isNeutral) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
    } else null

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        border = border,
        modifier = Modifier.defaultMinSize(minHeight = 28.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun PrivacyStatementCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatementRow(text = stringResource(R.string.privacy_offline_verified))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
            StatementRow(text = stringResource(R.string.privacy_no_internet_verified))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
            StatementRow(text = stringResource(R.string.privacy_no_tracking_verified))
        }
    }
}

@Composable
private fun StatementRow(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
