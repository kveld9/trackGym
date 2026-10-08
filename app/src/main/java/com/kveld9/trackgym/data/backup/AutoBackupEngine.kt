package com.kveld9.trackgym.data.backup

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.kveld9.trackgym.data.repository.GymRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Result data holder for automatic silent backup executions.
 */
data class AutoBackupResult(
    val isSuccess: Boolean,
    val fileName: String? = null,
    val totalWorkouts: Int = 0,
    val totalExercises: Int = 0,
    val errorMessage: String? = null
)

/**
 * Performs unattended, silent JSON backups via Storage Access Framework (SAF) folder URIs.
 * Manages backup rotation, keeping only the most recent N backups.
 */
class AutoBackupEngine(
    private val context: Context,
    private val repository: GymRepository,
    private val backupManager: JsonBackupManager = JsonBackupManager()
) {

    companion object {
        const val BACKUP_FILE_PREFIX = "trackgym_autobackup_"
        const val BACKUP_FILE_EXTENSION = ".json"
        const val MIME_TYPE_JSON = "application/json"

        fun generateBackupFileName(timestamp: Long = System.currentTimeMillis()): String {
            val formatter = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
            return "$BACKUP_FILE_PREFIX${formatter.format(Date(timestamp))}$BACKUP_FILE_EXTENSION"
        }

        fun isAutoBackupFile(name: String?): Boolean {
            if (name == null) return false
            return name.startsWith(BACKUP_FILE_PREFIX) && name.endsWith(BACKUP_FILE_EXTENSION)
        }
    }

    /**
     * Executes silent automatic backup into the user-selected tree Uri folder.
     */
    suspend fun performAutoBackup(
        treeUriString: String?,
        maxBackups: Int = 10
    ): AutoBackupResult = withContext(Dispatchers.IO) {
        if (treeUriString.isNullOrBlank()) {
            return@withContext AutoBackupResult(
                isSuccess = false,
                errorMessage = "No backup folder configured."
            )
        }

        try {
            val treeUri = Uri.parse(treeUriString)
            val dir = DocumentFile.fromTreeUri(context, treeUri)
            if (dir == null || !dir.exists() || !dir.canWrite()) {
                return@withContext AutoBackupResult(
                    isSuccess = false,
                    errorMessage = "Target backup directory is inaccessible or read-only."
                )
            }

            val backupData = repository.getBackupData()
            if (backupData.workouts.isEmpty() && backupData.exercises.none { it.isCustom }) {
                return@withContext AutoBackupResult(
                    isSuccess = true,
                    totalWorkouts = 0,
                    totalExercises = 0,
                    errorMessage = "Skipped: no workouts or custom exercises to backup."
                )
            }

            val jsonContent = backupManager.exportToJson(
                exercises = backupData.exercises,
                workouts = backupData.workouts,
                personalRecords = backupData.personalRecords
            )

            val fileName = generateBackupFileName()
            val newFile = dir.createFile(MIME_TYPE_JSON, fileName)
                ?: return@withContext AutoBackupResult(
                    isSuccess = false,
                    errorMessage = "Failed to create backup document file."
                )

            val outputStream = context.contentResolver.openOutputStream(newFile.uri)
                ?: return@withContext AutoBackupResult(
                    isSuccess = false,
                    errorMessage = "Failed to open output stream for backup file."
                )

            outputStream.use { stream ->
                OutputStreamWriter(stream, Charsets.UTF_8).use { writer ->
                    writer.write(jsonContent)
                    writer.flush()
                }
            }

            // Prune / rotate older backups exceeding maxBackups threshold
            pruneOldBackups(dir, maxBackups)

            AutoBackupResult(
                isSuccess = true,
                fileName = fileName,
                totalWorkouts = backupData.workouts.size,
                totalExercises = backupData.exercises.size
            )
        } catch (e: Exception) {
            AutoBackupResult(
                isSuccess = false,
                errorMessage = e.localizedMessage ?: e.javaClass.simpleName
            )
        }
    }

    /**
     * Deletes older automated backups if total count exceeds the max allowed.
     */
    fun pruneOldBackups(dir: DocumentFile, maxBackups: Int) {
        if (maxBackups <= 0) return
        val files = dir.listFiles()
            .filter { it.isFile && isAutoBackupFile(it.name) }
            .sortedBy { it.lastModified() } // oldest first

        if (files.size > maxBackups) {
            val toDeleteCount = files.size - maxBackups
            for (i in 0 until toDeleteCount) {
                files[i].delete()
            }
        }
    }
}
