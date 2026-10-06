package com.kveld9.trackgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeSettings by settingsViewModel.themeSettings.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val darkTheme = when (themeSettings.themeMode) {
                "SYSTEM" -> systemDark
                "LIGHT" -> false
                "DARK" -> true
                else -> systemDark
            }

            TrackGymTheme(
                darkTheme = darkTheme,
                dynamicColor = themeSettings.dynamicColor,
                amoledBlack = themeSettings.amoledBlack
            ) {
                MainScreen(
                    viewModel = viewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}
