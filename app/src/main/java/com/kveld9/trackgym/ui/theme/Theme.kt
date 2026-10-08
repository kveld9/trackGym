package com.kveld9.trackgym.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.kveld9.trackgym.data.ThemePreferences

private val TrackGymNeonGreen = Color(0xFF00E676)
private val TrackGymDarkBackground = Color(0xFF121212)
private val TrackGymDarkSurface = Color(0xFF1E1E1E)

private val TrackGymDarkColorScheme = darkColorScheme(
    primary = TrackGymNeonGreen,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF004D25),
    onPrimaryContainer = Color(0xFF69FF9D),
    secondary = GymBlue,
    onSecondary = Color.White,
    background = TrackGymDarkBackground,
    surface = TrackGymDarkSurface,
    surfaceContainer = Color(0xFF252525),
    surfaceContainerHigh = Color(0xFF2E2E2E),
    onBackground = Color.White,
    onSurface = Color.White
)

private val TrackGymLightColorScheme = lightColorScheme(
    primary = Color(0xFF00893E),
    onPrimary = Color.White,
    secondary = GymBlue,
    onSecondary = Color.White
)

@Composable
fun TrackGymTheme(
    themeMode: String = ThemePreferences.MODE_AMOLED,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isLight = themeMode == ThemePreferences.MODE_LIGHT
    val isAmoled = themeMode == ThemePreferences.MODE_AMOLED

    val baseColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isLight) dynamicLightColorScheme(context) else dynamicDarkColorScheme(context)
        }
        isLight -> TrackGymLightColorScheme
        else -> TrackGymDarkColorScheme
    }

    val colorScheme = if (isAmoled) {
        baseColorScheme.copy(
            background = Color.Black,
            surface = Color.Black,
            surfaceDim = Color.Black,
            surfaceContainerLowest = Color.Black
        )
    } else {
        baseColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode && view.context is Activity) {
        SideEffect {
            val window = (view.context as Activity).window
            val barColor = if (isAmoled) Color.Black else colorScheme.background
            window.statusBarColor = barColor.toArgb()
            window.navigationBarColor = barColor.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = isLight
                isAppearanceLightNavigationBars = isLight
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
