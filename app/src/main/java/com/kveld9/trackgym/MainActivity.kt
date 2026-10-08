package com.kveld9.trackgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.kveld9.trackgym.ui.navigation.MainScreen
import com.kveld9.trackgym.ui.theme.TrackGymTheme
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import com.kveld9.trackgym.ui.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GymViewModel by viewModels {
        val app = application as TrackGymApp
        GymViewModel.Factory(app.repository, app.themePreferences)
    }

    private val settingsViewModel: SettingsViewModel by viewModels {
        val app = application as TrackGymApp
        SettingsViewModel.Factory(app.themePreferences, app.repository)
    }

    private val timerActionReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
            when (intent?.action) {
                com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_ADD_30 -> {
                    viewModel.addRestSeconds(30)
                }
                com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_SKIP -> {
                    viewModel.stopRestTimer()
                }
                com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_COMPLETE_CURRENT_SET -> {
                    viewModel.completeFirstPendingSet()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        com.kveld9.trackgym.service.RestTimerNotificationManager.createNotificationChannel(this)

        val intentFilter = android.content.IntentFilter().apply {
            addAction(com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_ADD_30)
            addAction(com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_SKIP)
            addAction(com.kveld9.trackgym.service.RestTimerNotificationManager.ACTION_COMPLETE_CURRENT_SET)
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(timerActionReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(timerActionReceiver, intentFilter)
        }

        setContent {
            val themeSettings by settingsViewModel.themeSettings.collectAsStateWithLifecycle()

            TrackGymTheme(
                themeMode = themeSettings.themeMode
            ) {
                MainScreen(
                    viewModel = viewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        runCatching {
            unregisterReceiver(timerActionReceiver)
        }
    }
}
