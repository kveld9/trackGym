package com.kveld9.trackgym

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Process
import android.util.Log
import com.kveld9.trackgym.data.local.GymDatabase
import com.kveld9.trackgym.data.repository.GymRepository
import com.kveld9.trackgym.ui.error.ErrorActivity
import java.io.File

class TrackGymApp : Application() {

    val database: GymDatabase by lazy {
        GymDatabase.getInstance(this)
    }

    val repository: GymRepository by lazy {
        GymRepository(database)
    }

    val themePreferences: com.kveld9.trackgym.data.ThemePreferences by lazy {
        com.kveld9.trackgym.data.ThemePreferences(this)
    }

    val autoBackupEngine: com.kveld9.trackgym.data.backup.AutoBackupEngine by lazy {
        com.kveld9.trackgym.data.backup.AutoBackupEngine(this, repository)
    }

    val healthConnectSyncManager: com.kveld9.trackgym.data.health.HealthConnectSyncManager? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            com.kveld9.trackgym.data.health.HealthConnectSyncManager(this)
        } else {
            null
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (!isErrorProcess()) {
            setupGlobalExceptionHandler()
        }
    }

    private fun isErrorProcess(): Boolean {
        val processName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getProcessName()
        } else {
            runCatching {
                File("/proc/self/cmdline").readText().trim('\u0000', ' ', '\n')
            }.getOrNull()
        }
        return processName?.endsWith(":error_process") == true
    }

    private fun setupGlobalExceptionHandler() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val stackTrace = Log.getStackTraceString(throwable)
                val intent = Intent(this, ErrorActivity::class.java).apply {
                    putExtra(ErrorActivity.EXTRA_ERROR_MESSAGE, throwable.message ?: throwable.javaClass.simpleName)
                    putExtra(ErrorActivity.EXTRA_STACKTRACE, stackTrace)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(intent)
                Process.killProcess(Process.myPid())
                System.exit(10)
            } catch (t: Throwable) {
                defaultHandler?.uncaughtException(thread, throwable)
            }
        }
    }
}
