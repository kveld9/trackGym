package com.kveld9.trackgym.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kveld9.trackgym.data.ThemePreferences
import com.kveld9.trackgym.data.ThemeSettings
import com.kveld9.trackgym.data.backup.DuplicatePolicy
import com.kveld9.trackgym.data.backup.GymBackupDto
import com.kveld9.trackgym.data.backup.JsonBackupManager
import com.kveld9.trackgym.data.backup.CsvWorkoutExporter
import com.kveld9.trackgym.data.repository.GymRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

sealed class SettingsUiEvent {
    data class Success(@get:StringRes val messageRes: Int, val formatArgs: List<Any> = emptyList()) : SettingsUiEvent()
    data class Error(@get:StringRes val messageRes: Int, val formatArgs: List<Any> = emptyList()) : SettingsUiEvent()
    data class ImportPromptDuplicate(val backup: GymBackupDto) : SettingsUiEvent()
}

data class SettingsUiState(
    val isLoading: Boolean = false,
    val pendingImportBackup: GymBackupDto? = null
)

class SettingsViewModel(
    private val themePreferences: ThemePreferences,
    private val repository: GymRepository,
    private val backupManager: JsonBackupManager = JsonBackupManager(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val themeSettings: StateFlow<ThemeSettings> = themePreferences.themeSettings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ThemeSettings()
    )

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SettingsUiEvent>()
    val uiEvent: SharedFlow<SettingsUiEvent> = _uiEvent.asSharedFlow()

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            themePreferences.setThemeMode(mode)
        }
    }

    fun setWeightUnit(unit: String) {
        viewModelScope.launch {
            themePreferences.setWeightUnit(unit)
        }
    }

    fun setDistanceUnit(unit: String) {
        viewModelScope.launch {
            themePreferences.setDistanceUnit(unit)
        }
    }

    fun setAutoRestTimer(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setAutoRestTimer(enabled)
        }
    }

    fun setDefaultRestSeconds(seconds: Int) {
        viewModelScope.launch {
            themePreferences.setDefaultRestSeconds(seconds)
        }
    }

    fun setExerciseLanguage(language: String) {
        viewModelScope.launch {
            themePreferences.setExerciseLanguage(language)
        }
    }

    fun setOrmFormula(formula: String) {
        viewModelScope.launch {
            themePreferences.setOrmFormula(formula)
        }
    }

    fun setDoubleDumbbellVolume(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setDoubleDumbbellVolume(enabled)
        }
    }

    fun setExcludeWarmupFromVolume(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setExcludeWarmupFromVolume(enabled)
        }
    }

    fun setTimerSound(sound: String) {
        viewModelScope.launch {
            themePreferences.setTimerSound(sound)
        }
    }

    fun setTimerSoundCountdown(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setTimerSoundCountdown(enabled)
        }
    }

    fun setSoundFeedbackOnComplete(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setSoundFeedbackOnComplete(enabled)
        }
    }

    fun setUserBodyWeight(weightKg: Double) {
        viewModelScope.launch {
            themePreferences.setUserBodyWeight(weightKg)
        }
    }

    fun exportBackup(streamProvider: () -> OutputStream?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                withContext(ioDispatcher) {
                    val backupData = repository.getBackupData()
                    if (backupData.workouts.isEmpty() && backupData.exercises.none { it.isCustom }) {
                        throw IllegalStateException("No hay entrenamientos ni ejercicios personalizados para exportar.")
                    }
                    val json = backupManager.exportToJson(
                        exercises = backupData.exercises,
                        workouts = backupData.workouts,
                        personalRecords = backupData.personalRecords
                    )
                    val outputStream = streamProvider()
                        ?: throw IllegalStateException("No se pudo abrir el archivo para exportar.")
                    outputStream.use { stream ->
                        OutputStreamWriter(stream, Charsets.UTF_8).use { writer ->
                            writer.write(json)
                            writer.flush()
                        }
                    }
                }
                _uiEvent.emit(SettingsUiEvent.Success(R.string.toast_backup_exported_success))
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_backup_export_error, listOf(e.localizedMessage.orEmpty())))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun exportBackup(outputStream: OutputStream) = exportBackup { outputStream }

    fun exportCsv(streamProvider: () -> OutputStream?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                withContext(ioDispatcher) {
                    val backupData = repository.getBackupData()
                    if (backupData.workouts.isEmpty()) {
                        throw IllegalStateException("No hay entrenamientos para exportar.")
                    }
                    val outputStream = streamProvider()
                        ?: throw IllegalStateException("No se pudo abrir el archivo para exportar.")
                    outputStream.use { stream ->
                        CsvWorkoutExporter.exportToCsv(backupData.workouts, stream)
                    }
                }
                _uiEvent.emit(SettingsUiEvent.Success(R.string.toast_csv_exported_success))
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_csv_export_error, listOf(e.localizedMessage.orEmpty())))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun exportCsv(outputStream: OutputStream) = exportCsv { outputStream }

    fun startImport(streamProvider: () -> InputStream?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val parsedBackup = withContext(ioDispatcher) {
                    val inputStream = streamProvider()
                        ?: throw IllegalStateException("No se pudo abrir el archivo de respaldo.")
                    inputStream.use { stream ->
                        val content = backupManager.readBoundedStream(stream)
                        val result = backupManager.parseAndValidateJson(content)
                        result.getOrThrow()
                    }
                }

                if (parsedBackup.workouts.isEmpty() && parsedBackup.exercises.isEmpty()) {
                    _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_backup_invalid_data))
                    return@launch
                }

                val hasExisting = withContext(ioDispatcher) { repository.hasExistingData() }
                if (hasExisting) {
                    _uiState.update { it.copy(pendingImportBackup = parsedBackup) }
                    _uiEvent.emit(SettingsUiEvent.ImportPromptDuplicate(parsedBackup))
                } else {
                    executeImport(parsedBackup, DuplicatePolicy.OVERWRITE_ALL)
                }
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_backup_read_error, listOf(e.localizedMessage.orEmpty())))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun startImport(inputStream: InputStream) = startImport { inputStream }

    fun startImportCsv(streamProvider: () -> InputStream?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val parsedBackup = withContext(ioDispatcher) {
                    val inputStream = streamProvider()
                        ?: throw IllegalStateException("No se pudo abrir el archivo CSV.")
                    inputStream.use { stream ->
                        com.kveld9.trackgym.data.backup.CsvWorkoutImporter.parseCsvToBackupDto(stream)
                    }
                }

                if (parsedBackup.workouts.isEmpty()) {
                    _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_csv_import_empty))
                    return@launch
                }

                val hasExisting = withContext(ioDispatcher) { repository.hasExistingData() }
                if (hasExisting) {
                    _uiState.update { it.copy(pendingImportBackup = parsedBackup) }
                    _uiEvent.emit(SettingsUiEvent.ImportPromptDuplicate(parsedBackup))
                } else {
                    executeImport(parsedBackup, DuplicatePolicy.OVERWRITE_ALL)
                }
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_csv_import_error, listOf(e.localizedMessage.orEmpty())))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun startImportCsv(inputStream: InputStream) = startImportCsv { inputStream }

    fun applyImportPolicy(policy: DuplicatePolicy) {
        val pending = _uiState.value.pendingImportBackup ?: return
        _uiState.update { it.copy(pendingImportBackup = null) }
        executeImport(pending, policy)
    }

    fun dismissImportPrompt() {
        _uiState.update { it.copy(pendingImportBackup = null) }
    }

    private fun executeImport(backup: GymBackupDto, policy: DuplicatePolicy) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val (inserted, skipped) = withContext(ioDispatcher) {
                    repository.importBackup(backup, policy)
                }
                _uiEvent.emit(SettingsUiEvent.Success(R.string.toast_import_success, listOf(inserted, skipped)))
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(R.string.toast_import_error, listOf(e.localizedMessage.orEmpty())))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    class Factory(
        private val themePreferences: ThemePreferences,
        private val repository: GymRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(themePreferences, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
