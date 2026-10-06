package com.kveld9.trackgym.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kveld9.trackgym.data.ThemePreferences
import com.kveld9.trackgym.data.ThemeSettings
import com.kveld9.trackgym.data.backup.DuplicatePolicy
import com.kveld9.trackgym.data.backup.GymBackupDto
import com.kveld9.trackgym.data.backup.JsonBackupManager
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

sealed class SettingsUiEvent {
    data class Success(val message: String) : SettingsUiEvent()
    data class Error(val message: String) : SettingsUiEvent()
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

    fun setDynamicColor(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setDynamicColor(enabled)
        }
    }

    fun setAmoledBlack(enabled: Boolean) {
        viewModelScope.launch {
            themePreferences.setAmoledBlack(enabled)
        }
    }

    fun exportBackup(outputStream: OutputStream) {
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
                    OutputStreamWriter(outputStream, Charsets.UTF_8).use { writer ->
                        writer.write(json)
                        writer.flush()
                    }
                }
                _uiEvent.emit(SettingsUiEvent.Success("Respaldo exportado correctamente."))
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(e.localizedMessage ?: "Error al exportar respaldo."))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun startImport(inputStream: InputStream) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val parsedBackup = withContext(ioDispatcher) {
                    val content = backupManager.readBoundedStream(inputStream)
                    val result = backupManager.parseAndValidateJson(content)
                    result.getOrThrow()
                }

                if (parsedBackup.workouts.isEmpty() && parsedBackup.exercises.isEmpty()) {
                    _uiEvent.emit(SettingsUiEvent.Error("El archivo no contiene registros válidos."))
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
                _uiEvent.emit(SettingsUiEvent.Error(e.localizedMessage ?: "Error al leer el archivo de respaldo."))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

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
                val msg = if (skipped > 0) {
                    "Se importaron $inserted registros ($skipped omitidos)."
                } else {
                    "Se importaron $inserted registros exitosamente."
                }
                _uiEvent.emit(SettingsUiEvent.Success(msg))
            } catch (e: Exception) {
                _uiEvent.emit(SettingsUiEvent.Error(e.localizedMessage ?: "Error al importar datos."))
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
