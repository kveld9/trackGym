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

private val TrackGymDarkColorScheme = darkColorScheme(
    primary = TrackGymNeonGreen,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF00522B),
    onPrimaryContainer = Color(0xFF69FF9D),
    inversePrimary = Color(0xFF006D3B),
    secondary = GymBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF004394),
    onSecondaryContainer = Color(0xFFD6E3FF),
    tertiary = GymGold,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF534600),
    onTertiaryContainer = Color(0xFFFFE082),
    error = GymRed,
    onError = Color.Black,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = TrackGymDarkBackground,
    onBackground = Color.White,
    surface = TrackGymDarkSurface,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFC7C7CC),
    outline = Color(0xFF8E8E93),
    outlineVariant = Color(0xFF3E3E42),
    scrim = Color.Black,
    inverseSurface = Color(0xFFE2E2E2),
    inverseOnSurface = Color(0xFF1A1A1A),
    surfaceDim = Color(0xFF121212),
    surfaceBright = Color(0xFF383838),
    surfaceContainerLowest = Color(0xFF0C0C0C),
    surfaceContainerLow = Color(0xFF171717),
    surfaceContainer = Color(0xFF222222),
    surfaceContainerHigh = Color(0xFF2C2C2C),
    surfaceContainerHighest = Color(0xFF373737)
)

private val TrackGymLightColorScheme = lightColorScheme(
    primary = Color(0xFF00893E),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF8CF8AC),
    onPrimaryContainer = Color(0xFF00210A),
    inversePrimary = TrackGymNeonGreen,
    secondary = GymBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD8E2FF),
    onSecondaryContainer = Color(0xFF001A41),
    tertiary = Color(0xFF755B00),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE082),
    onTertiaryContainer = Color(0xFF241A00),
    error = GymRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFF9F9F9),
    onBackground = Color(0xFF1A1A1A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFE0E0E0),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color.Black,
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    surfaceDim = Color(0xFFDED8E1),
    surfaceBright = Color(0xFFFEF7FF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF7F2FA),
    surfaceContainer = Color(0xFFF1ECF4),
    surfaceContainerHigh = Color(0xFFEBE6EE),
    surfaceContainerHighest = Color(0xFFE6E0E9)
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
            background = TrackGymOledBlack,
            surface = TrackGymOledBlack,
            surfaceDim = TrackGymOledBlack,
            surfaceContainerLowest = TrackGymOledBlack,
            surfaceContainerLow = Color(0xFF0A0A0A),
            surfaceContainer = Color(0xFF141414),
            surfaceContainerHigh = Color(0xFF1E1E1E),
            surfaceContainerHighest = Color(0xFF282828)
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
        shapes = GymShapes,
        typography = GymTypography,
        content = content
    )
}
