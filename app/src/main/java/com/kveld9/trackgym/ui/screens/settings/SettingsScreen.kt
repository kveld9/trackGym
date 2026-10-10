package com.kveld9.trackgym.ui.screens.settings

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kveld9.trackgym.BuildConfig
import com.kveld9.trackgym.R
import com.kveld9.trackgym.TrackGymApp
import com.kveld9.trackgym.data.backup.DuplicatePolicy
import com.kveld9.trackgym.domain.calculator.OneRepMaxFormula
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.ui.components.GymEquipmentProfilesManageDialog
import com.kveld9.trackgym.ui.privacy.PrivacyActivity
import com.kveld9.trackgym.ui.theme.screenEnterTransition
import com.kveld9.trackgym.ui.viewmodel.SettingsUiEvent
import com.kveld9.trackgym.ui.viewmodel.SettingsViewModel

/**
 * UI Selector Pattern Rule:
 * - Options with <= 3 short labels must use [androidx.compose.material3.SingleChoiceSegmentedButtonRow].
 * - Options with > 3 items or long labels must use a clickable dialog row displaying the active
 *   value in the subtitle, which triggers an [AlertDialog] single-choice list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val themeSettings by viewModel.themeSettings.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val gymProfiles by viewModel.gymProfiles.collectAsStateWithLifecycle()
    val activeGymProfile by viewModel.activeGymProfile.collectAsStateWithLifecycle()
    val weightUnit = remember(themeSettings.weightUnit) { WeightUnit.fromString(themeSettings.weightUnit) }

    var showGymProfilesDialog by remember { mutableStateOf(false) }
    var showExportFormatDialog by remember { mutableStateOf(false) }
    var showImportFormatDialog by remember { mutableStateOf(false) }
    var showOrmDialog by remember { mutableStateOf(false) }
    var showTimerSoundDialog by remember { mutableStateOf(false) }
    var showGetReadyDialog by remember { mutableStateOf(false) }
    var showBodyWeightDialog by remember { mutableStateOf(false) }
    var showAgeDialog by remember { mutableStateOf(false) }

    val activeOrmFormula = remember(themeSettings.ormFormula) {
        OneRepMaxFormula.entries.firstOrNull {
            it.name.equals(themeSettings.ormFormula, ignoreCase = true)
        } ?: OneRepMaxFormula.EPLEY
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SettingsUiEvent.Success -> {
                    val message = if (event.formatArgs.isEmpty()) {
                        context.getString(event.messageRes)
                    } else {
                        context.getString(event.messageRes, *event.formatArgs.toTypedArray())
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                is SettingsUiEvent.Error -> {
                    val message = if (event.formatArgs.isEmpty()) {
                        context.getString(event.messageRes)
                    } else {
                        context.getString(event.messageRes, *event.formatArgs.toTypedArray())
                    }
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
                is SettingsUiEvent.ImportPromptDuplicate -> {
                    // Handled declaratively by uiState.pendingImportBackup
                }
            }
        }
    }

    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.exportBackup { context.contentResolver.openOutputStream(uri) }
        }
    }

    val exportCsvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/csv")
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.exportCsv { context.contentResolver.openOutputStream(uri) }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.startImport { context.contentResolver.openInputStream(uri) }
        }
    }

    val importCsvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.startImportCsv { context.contentResolver.openInputStream(uri) }
        }
    }

    val autoBackupFolderLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri: Uri? ->
        if (uri != null) {
            val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            runCatching {
                context.contentResolver.takePersistableUriPermission(uri, flags)
            }
            viewModel.setAutoBackupUri(uri.toString())
        }
    }

    val healthConnectSyncManager = remember {
        (context.applicationContext as? TrackGymApp)?.healthConnectSyncManager
    }
    val healthConnectLauncher = rememberLauncherForActivityResult(
        contract = androidx.health.connect.client.PermissionController.createRequestPermissionResultContract()
    ) { grantedPermissions ->
        if (healthConnectSyncManager != null && grantedPermissions.containsAll(healthConnectSyncManager.permissions)) {
            viewModel.setHealthConnectSync(true)
        }
    }

    if (showGymProfilesDialog) {
        GymEquipmentProfilesManageDialog(
            profiles = gymProfiles,
            activeProfileId = activeGymProfile.id,
            weightUnit = weightUnit,
            onSelectProfile = { viewModel.selectGymProfile(it) },
            onSaveProfile = { viewModel.saveGymProfile(it) },
            onDeleteProfile = { viewModel.deleteGymProfile(it) },
            onDismiss = { showGymProfilesDialog = false }
        )
    }

    if (showExportFormatDialog) {
        ExportFormatDialog(
            onDismiss = { showExportFormatDialog = false },
            onSelectJson = {
                val fileName = "trackgym_backup_${System.currentTimeMillis()}.json"
                exportLauncher.launch(fileName)
            },
            onSelectCsv = {
                val fileName = "trackgym_workouts_${System.currentTimeMillis()}.csv"
                exportCsvLauncher.launch(fileName)
            }
        )
    }

    if (showImportFormatDialog) {
        ImportFormatDialog(
            onDismiss = { showImportFormatDialog = false },
            onSelectJson = {
                importLauncher.launch(arrayOf("application/json", "*/*"))
            },
            onSelectCsv = {
                importCsvLauncher.launch(arrayOf("text/comma-separated-values", "text/csv", "application/csv", "*/*"))
            }
        )
    }

    if (showOrmDialog) {
        OneRepMaxSelectionDialog(
            currentFormula = themeSettings.ormFormula,
            onSelectFormula = { viewModel.setOrmFormula(it) },
            onDismiss = { showOrmDialog = false }
        )
    }

    if (showTimerSoundDialog) {
        TimerSoundSelectionDialog(
            currentSound = themeSettings.timerSound,
            onSelectSound = { viewModel.setTimerSound(it) },
            onDismiss = { showTimerSoundDialog = false }
        )
    }

    if (showGetReadyDialog) {
        GetReadySelectionDialog(
            currentSeconds = themeSettings.getReadySeconds,
            onSelectSeconds = { viewModel.setGetReadySeconds(it) },
            onDismiss = { showGetReadyDialog = false }
        )
    }

    if (showBodyWeightDialog) {
        BodyWeightInputDialog(
            initialWeight = themeSettings.userBodyWeightKg,
            weightUnit = weightUnit,
            onDismiss = { showBodyWeightDialog = false },
            onConfirm = {
                viewModel.setUserBodyWeight(it)
                showBodyWeightDialog = false
            }
        )
    }

    if (showAgeDialog) {
        AgeInputDialog(
            initialAge = themeSettings.userAge,
            onDismiss = { showAgeDialog = false },
            onConfirm = {
                viewModel.setUserAge(it)
                showAgeDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.action_settings), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    if (onNavigateBack != null) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.action_back)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.screenEnterTransition()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Appearance Section
            SectionHeader(title = stringResource(R.string.section_appearance))
            AppearanceCard(
                themeMode = themeSettings.themeMode,
                dynamicColor = themeSettings.dynamicColor,
                exerciseLanguage = themeSettings.exerciseLanguage,
                onSetThemeMode = { viewModel.setThemeMode(it) },
                onSetDynamicColor = { viewModel.setDynamicColor(it) },
                onSetExerciseLanguage = { viewModel.setExerciseLanguage(it) }
            )

            // User Profile Section
            SectionHeader(title = stringResource(R.string.section_profile))
            UserProfileCard(
                bodyWeightKg = themeSettings.userBodyWeightKg,
                sex = themeSettings.userBiologicalSex,
                age = themeSettings.userAge,
                weightUnit = weightUnit,
                onWeightClick = { showBodyWeightDialog = true },
                onSexChanged = { viewModel.setUserBiologicalSex(it) },
                onAgeClick = { showAgeDialog = true }
            )

            // Units Section
            SectionHeader(title = stringResource(R.string.section_units))
            UnitsCard(
                weightUnit = themeSettings.weightUnit,
                distanceUnit = themeSettings.distanceUnit,
                onSetWeightUnit = { viewModel.setWeightUnit(it) },
                onSetDistanceUnit = { viewModel.setDistanceUnit(it) }
            )

            // Timer Section
            SectionHeader(title = stringResource(R.string.section_timer))
            TimerCard(
                autoRestTimer = themeSettings.autoRestTimer,
                defaultRestSeconds = themeSettings.defaultRestSeconds,
                timerSoundCountdown = themeSettings.timerSoundCountdown,
                getReadySeconds = themeSettings.getReadySeconds,
                onAutoRestChange = { viewModel.setAutoRestTimer(it) },
                onDefaultRestChange = { viewModel.setDefaultRestSeconds(it) },
                onCountdownBeepsChange = { viewModel.setTimerSoundCountdown(it) },
                onGetReadyClick = { showGetReadyDialog = true }
            )

            // Sound Section
            SectionHeader(title = stringResource(R.string.section_sound))
            SoundCard(
                timerSound = themeSettings.timerSound,
                soundFeedbackOnComplete = themeSettings.soundFeedbackOnComplete,
                onTimerSoundClick = { showTimerSoundDialog = true },
                onSoundFeedbackChange = { viewModel.setSoundFeedbackOnComplete(it) }
            )

            // Volume, Equipment & Calculation Section
            SectionHeader(title = stringResource(R.string.section_volume_equipment))
            VolumeEquipmentCard(
                doubleDumbbell = themeSettings.doubleDumbbellVolume,
                excludeWarmup = themeSettings.excludeWarmupFromVolume,
                showInlinePlates = themeSettings.showInlinePlates,
                activeProfile = activeGymProfile,
                weightUnit = weightUnit,
                activeOrmFormula = activeOrmFormula,
                onDoubleDumbbellChange = { viewModel.setDoubleDumbbellVolume(it) },
                onExcludeWarmupChange = { viewModel.setExcludeWarmupFromVolume(it) },
                onShowInlinePlatesChange = { viewModel.setShowInlinePlates(it) },
                onManageProfilesClick = { showGymProfilesDialog = true },
                onOrmFormulaClick = { showOrmDialog = true }
            )

            // Session Section
            SectionHeader(title = stringResource(R.string.section_session))
            SessionCard(
                keepScreenOn = themeSettings.keepScreenOn,
                routineUpdateMode = themeSettings.routineUpdateMode,
                onKeepScreenOnChange = { viewModel.setKeepScreenOn(it) },
                onRoutineUpdateModeChange = { viewModel.setRoutineUpdateMode(it) }
            )

            // Backup & Portability Section
            SectionHeader(title = stringResource(R.string.section_backup))
            BackupCard(
                autoBackupUri = themeSettings.autoBackupUri,
                maxAutoBackups = themeSettings.maxAutoBackups,
                onExportClick = { showExportFormatDialog = true },
                onImportClick = { showImportFormatDialog = true },
                onSelectFolder = { autoBackupFolderLauncher.launch(null) },
                onDisableAutoBackup = { viewModel.setAutoBackupUri(null) }
            )

            // Integrations Section
            SectionHeader(title = stringResource(R.string.section_integrations))
            IntegrationsCard(
                healthConnectSync = themeSettings.healthConnectSync,
                isAvailable = healthConnectSyncManager?.isAvailable() == true,
                onSyncChange = { enabled ->
                    if (enabled) {
                        if (healthConnectSyncManager?.isAvailable() == true) {
                            healthConnectLauncher.launch(healthConnectSyncManager.permissions)
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.setting_health_connect_unavailable),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        viewModel.setHealthConnectSync(false)
                    }
                }
            )

            // About Section
            AboutCard(
                versionName = BuildConfig.VERSION_NAME,
                onGitHubClick = {
                    runCatching {
                        val intent = Intent(Intent.ACTION_VIEW, "https://github.com/kveld9/trackGym".toUri())
                        context.startActivity(intent)
                    }
                },
                onPrivacyClick = {
                    context.startActivity(Intent(context, PrivacyActivity::class.java))
                }
            )

            Spacer(modifier = Modifier.height(120.dp))
        }
    }

    // Duplicate Policy Dialog
    if (uiState.pendingImportBackup != null) {
        DuplicatePolicyDialog(
            onDismiss = { viewModel.dismissImportPrompt() },
            onApplyPolicy = { policy -> viewModel.applyImportPolicy(policy) }
        )
    }
}

@Composable
fun DuplicatePolicyDialog(
    onDismiss: () -> Unit,
    onApplyPolicy: (DuplicatePolicy) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = false),
        title = { Text(stringResource(R.string.duplicate_policy_title)) },
        text = { Text(stringResource(R.string.duplicate_policy_prompt)) },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onApplyPolicy(DuplicatePolicy.SKIP_EXISTING) },
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp)
                ) {
                    Text(stringResource(R.string.duplicate_policy_skip))
                }

                OutlinedButton(
                    onClick = { onApplyPolicy(DuplicatePolicy.OVERWRITE_ALL) },
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp)
                ) {
                    Text(stringResource(R.string.duplicate_policy_overwrite))
                }

                OutlinedButton(
                    onClick = { onApplyPolicy(DuplicatePolicy.DUPLICATE_ALL) },
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp)
                ) {
                    Text(stringResource(R.string.duplicate_policy_allow_duplicates))
                }

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End).defaultMinSize(minHeight = 48.dp)
                ) {
                    Text(stringResource(R.string.action_cancel))
                }
            }
        },
        dismissButton = null,
        modifier = modifier
    )
}
