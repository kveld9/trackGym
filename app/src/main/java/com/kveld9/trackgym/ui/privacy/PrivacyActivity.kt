package com.kveld9.trackgym.ui.privacy

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import androidx.health.connect.client.PermissionController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.compose.ui.graphics.Color
import com.kveld9.trackgym.MainActivity
import com.kveld9.trackgym.R
import com.kveld9.trackgym.TrackGymApp
import com.kveld9.trackgym.data.ThemeSettings
import com.kveld9.trackgym.ui.theme.TrackGymNeonGreen
import com.kveld9.trackgym.ui.theme.TrackGymTheme
import com.kveld9.trackgym.ui.theme.subtleBorder
import kotlinx.coroutines.launch

class PrivacyActivity : ComponentActivity() {

    private val areNotificationsEnabledState = mutableStateOf(false)
    private val hasHealthConnectPermissionsState = mutableStateOf(false)
    private val showRationaleDialogState = mutableStateOf(false)

    private val privacyPrefs by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private var hasRequestedNotificationPermission: Boolean
        get() = privacyPrefs.getBoolean(KEY_POST_NOTIFICATIONS_REQUESTED, false)
        set(value) = privacyPrefs.edit().putBoolean(KEY_POST_NOTIFICATIONS_REQUESTED, value).apply()

    private val requestNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _ ->
        hasRequestedNotificationPermission = true
        refreshPermissionStatuses()
    }

    private val healthConnectPermissionLauncher = registerForActivityResult(
        PermissionController.createRequestPermissionResultContract()
    ) { grantedPermissions ->
        val app = application as TrackGymApp
        val syncManager = app.healthConnectSyncManager
        if (syncManager != null && grantedPermissions.containsAll(syncManager.permissions)) {
            lifecycleScope.launch {
                app.themePreferences.setHealthConnectSync(true)
                refreshPermissionStatuses()
            }
        } else {
            refreshPermissionStatuses()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val app = application as TrackGymApp
        val themePreferences = app.themePreferences
        val isHealthConnectAvailable = app.healthConnectSyncManager?.isAvailable() == true

        setContent {
            val themeSettings by themePreferences.themeSettings.collectAsState(initial = ThemeSettings())
            val areNotificationsEnabled by areNotificationsEnabledState
            val hasHealthConnectPermissions by hasHealthConnectPermissionsState
            val showRationaleDialog by showRationaleDialogState

            TrackGymTheme(
                themeMode = themeSettings.themeMode,
                dynamicColor = themeSettings.dynamicColor
            ) {
                PrivacyScreen(
                    isHealthConnectAvailable = isHealthConnectAvailable,
                    isHealthConnectEnabled = themeSettings.healthConnectSync,
                    onManageNotifications = { handleManageNotifications() },
                    onConnectHealth = { handleConnectHealth() },
                    onNavigateBackups = { handleNavigateBackups() },
                    onBackClick = { finish() },
                    areNotificationsEnabled = areNotificationsEnabled,
                    hasHealthConnectPermissions = hasHealthConnectPermissions,
                    showRationaleDialog = showRationaleDialog,
                    onConfirmRationale = {
                        showRationaleDialogState.value = false
                        hasRequestedNotificationPermission = true
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    },
                    onDismissRationale = { showRationaleDialogState.value = false }
                )
            }
        }
    }

    private fun handleNavigateBackups() {
        val intent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_APPLICATION_PREFERENCES
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
        finish()
    }

    private fun handleConnectHealth() {
        val app = application as TrackGymApp
        val syncManager = app.healthConnectSyncManager
        if (syncManager != null && syncManager.isAvailable()) {
            healthConnectPermissionLauncher.launch(syncManager.permissions)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPermissionStatuses()
    }

    private fun refreshPermissionStatuses() {
        areNotificationsEnabledState.value = NotificationManagerCompat.from(this).areNotificationsEnabled()
        val app = application as TrackGymApp
        lifecycleScope.launch {
            hasHealthConnectPermissionsState.value = runCatching {
                app.healthConnectSyncManager?.hasAllPermissions() == true
            }.getOrDefault(false)
        }
    }

    private fun handleManageNotifications() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            openNotificationSettings()
            return
        }

        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            openNotificationSettings()
            return
        }

        val permission = Manifest.permission.POST_NOTIFICATIONS
        val isPermanentlyDenied = hasRequestedNotificationPermission &&
            !shouldShowRequestPermissionRationale(permission)

        when {
            isPermanentlyDenied -> openNotificationSettings()
            shouldShowRequestPermissionRationale(permission) -> showRationaleDialogState.value = true
            else -> {
                hasRequestedNotificationPermission = true
                requestNotificationPermissionLauncher.launch(permission)
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

    companion object {
        private const val PREFS_NAME = "privacy_prefs"
        private const val KEY_POST_NOTIFICATIONS_REQUESTED = "post_notifications_requested"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(
    isHealthConnectAvailable: Boolean,
    isHealthConnectEnabled: Boolean,
    onManageNotifications: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onConnectHealth: () -> Unit = {},
    onNavigateBackups: () -> Unit = {},
    areNotificationsEnabled: Boolean = NotificationManagerCompat.from(LocalContext.current).areNotificationsEnabled(),
    hasHealthConnectPermissions: Boolean = false,
    showRationaleDialog: Boolean = false,
    onConfirmRationale: () -> Unit = {},
    onDismissRationale: () -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var currentNotificationsEnabled by remember {
        mutableStateOf(areNotificationsEnabled)
    }

    DisposableEffect(areNotificationsEnabled) {
        currentNotificationsEnabled = areNotificationsEnabled
        onDispose {}
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                currentNotificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
                areNotificationsEnabled = currentNotificationsEnabled,
                isHealthConnectAvailable = isHealthConnectAvailable,
                isHealthConnectEnabled = isHealthConnectEnabled,
                hasHealthConnectPermissions = hasHealthConnectPermissions,
                onManageNotifications = onManageNotifications,
                onConnectHealth = onConnectHealth,
                onNavigateBackups = onNavigateBackups
            )

            PrivacySectionHeader(title = stringResource(R.string.privacy_section_statement))

            PrivacyStatementCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = onDismissRationale,
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            title = {
                Text(
                    text = stringResource(R.string.permission_notifications_rationale_title),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.permission_notifications_rationale_desc),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = onConfirmRationale,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.defaultMinSize(minHeight = 48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.permission_notifications_rationale_confirm),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissRationale,
                    modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.action_cancel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
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
    hasHealthConnectPermissions: Boolean,
    onManageNotifications: () -> Unit,
    modifier: Modifier = Modifier,
    onConnectHealth: () -> Unit = {},
    onNavigateBackups: () -> Unit = {}
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

            StoragePermissionRow(onNavigateBackups = onNavigateBackups)

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

            HealthConnectPermissionRow(
                isAvailable = isHealthConnectAvailable,
                isEnabled = isHealthConnectEnabled,
                hasAllPermissions = hasHealthConnectPermissions,
                onConnectHealth = onConnectHealth
            )
        }
    }
}

@Composable
private fun NotificationPermissionRow(
    areNotificationsEnabled: Boolean,
    onManageNotifications: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            PermissionBadge(
                text = stringResource(
                    if (areNotificationsEnabled) R.string.permission_status_granted
                    else R.string.permission_status_denied
                ),
                style = if (areNotificationsEnabled) PermissionBadgeStyle.SUCCESS else PermissionBadgeStyle.SOFT_ERROR
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
private fun StoragePermissionRow(
    onNavigateBackups: () -> Unit = {}
) {
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
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            PermissionBadge(
                text = stringResource(R.string.permission_status_not_required),
                style = PermissionBadgeStyle.NEUTRAL_OUTLINE
            )
            OutlinedButton(
                onClick = onNavigateBackups,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(
                    text = stringResource(R.string.permission_action_backups),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun HealthConnectPermissionRow(
    isAvailable: Boolean,
    isEnabled: Boolean,
    hasAllPermissions: Boolean,
    onConnectHealth: () -> Unit = {}
) {
    val isGranted = isEnabled && hasAllPermissions
    val statusText = when {
        !isAvailable -> stringResource(R.string.permission_status_unavailable)
        isGranted -> stringResource(R.string.permission_status_granted)
        else -> stringResource(R.string.permission_status_available)
    }
    val badgeStyle = when {
        !isAvailable -> PermissionBadgeStyle.NEUTRAL
        isGranted -> PermissionBadgeStyle.SUCCESS
        else -> PermissionBadgeStyle.NEUTRAL_OUTLINE
    }
    val showConnectAction = isAvailable && !isEnabled

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
        HealthConnectActionBlock(
            statusText = statusText,
            badgeStyle = badgeStyle,
            showConnectAction = showConnectAction,
            onConnectHealth = onConnectHealth
        )
    }
}

@Composable
private fun HealthConnectActionBlock(
    statusText: String,
    badgeStyle: PermissionBadgeStyle,
    showConnectAction: Boolean,
    onConnectHealth: () -> Unit
) {
    if (showConnectAction) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            PermissionBadge(
                text = statusText,
                style = badgeStyle
            )
            OutlinedButton(
                onClick = onConnectHealth,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(
                    text = stringResource(R.string.permission_action_connect),
                    fontSize = 12.sp
                )
            }
        }
    } else {
        PermissionBadge(
            text = statusText,
            style = badgeStyle
        )
    }
}

private enum class PermissionBadgeStyle {
    SUCCESS,
    NEUTRAL_OUTLINE,
    SOFT_ERROR,
    NEUTRAL
}

@Composable
private fun PermissionBadge(
    text: String,
    style: PermissionBadgeStyle
) {
    val containerColor: Color = when (style) {
        PermissionBadgeStyle.SUCCESS -> TrackGymNeonGreen.copy(alpha = 0.15f)
        PermissionBadgeStyle.NEUTRAL_OUTLINE -> Color.Transparent
        PermissionBadgeStyle.SOFT_ERROR -> MaterialTheme.colorScheme.errorContainer
        PermissionBadgeStyle.NEUTRAL -> MaterialTheme.colorScheme.surfaceContainerHighest
    }
    val contentColor: Color = when (style) {
        PermissionBadgeStyle.SUCCESS -> TrackGymNeonGreen
        PermissionBadgeStyle.NEUTRAL_OUTLINE -> MaterialTheme.colorScheme.onSurfaceVariant
        PermissionBadgeStyle.SOFT_ERROR -> MaterialTheme.colorScheme.onErrorContainer
        PermissionBadgeStyle.NEUTRAL -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    val border: BorderStroke? = when (style) {
        PermissionBadgeStyle.SUCCESS -> BorderStroke(1.dp, TrackGymNeonGreen.copy(alpha = 0.35f))
        PermissionBadgeStyle.NEUTRAL_OUTLINE -> BorderStroke(1.dp, MaterialTheme.colorScheme.subtleBorder)
        PermissionBadgeStyle.SOFT_ERROR,
        PermissionBadgeStyle.NEUTRAL -> null
    }

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
