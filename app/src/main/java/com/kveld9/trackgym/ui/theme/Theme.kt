package com.kveld9.trackgym.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
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

private val DarkColorScheme = darkColorScheme(
    primary = GymNeonGreen,
    onPrimary = GymBlack,
    primaryContainer = Color(0xFF004D26),
    onPrimaryContainer = Color(0xFF76FCA2),
    secondary = GymBlue,
    onSecondary = TextWhite,
    secondaryContainer = Color(0xFF0D3268),
    onSecondaryContainer = Color(0xFFD0E1FD),
    tertiary = GymGold,
    onTertiary = GymBlack,
    tertiaryContainer = Color(0xFF4C3D00),
    onTertiaryContainer = Color(0xFFFFE088),
    background = GymBlack,
    onBackground = TextWhite,
    surface = GymSurface,
    onSurface = TextWhite,
    surfaceVariant = GymSurfaceVariant,
    onSurfaceVariant = TextMuted,
    surfaceDim = Color(0xFF0C0E12),
    surfaceBright = Color(0xFF232731),
    surfaceContainerLowest = GymBlack,
    surfaceContainerLow = Color(0xFF14171E),
    surfaceContainer = GymSurface,
    surfaceContainerHigh = GymSurfaceVariant,
    surfaceContainerHighest = Color(0xFF2D323E),
    error = GymRed,
    onError = TextWhite,
    errorContainer = Color(0xFF5A1414),
    onErrorContainer = Color(0xFFFFDAD6),
    outline = GymBorder,
    outlineVariant = Color(0xFF383E4B)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF008844),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFA1F7C0),
    onPrimaryContainer = Color(0xFF00210E),
    secondary = Color(0xFF1565C0),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD8E6FF),
    onSecondaryContainer = Color(0xFF051C3B),
    tertiary = Color(0xFFB28900),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFEFA7),
    onTertiaryContainer = Color(0xFF382A00),
    background = Color(0xFFF7F9FC),
    onBackground = Color(0xFF181C22),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF181C22),
    surfaceVariant = Color(0xFFE2E7EE),
    onSurfaceVariant = Color(0xFF4B5360),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF1F3F8),
    surfaceContainer = Color(0xFFE8ECF2),
    surfaceContainerHigh = Color(0xFFE1E5ED),
    surfaceContainerHighest = Color(0xFFDADEE7),
    error = Color(0xFFD32F2F),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    outline = Color(0xFFB0B7C3),
    outlineVariant = Color(0xFFCCD1DC)
)

@Composable
fun TrackGymTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    amoledBlack: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val baseColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val colorScheme = if (darkTheme && amoledBlack) {
        baseColorScheme.copy(
            background = Color.Black,
            surface = Color.Black,
            surfaceContainer = Color(0xFF121212),
            surfaceContainerLow = Color(0xFF0A0A0A),
            surfaceContainerHigh = Color(0xFF1A1A1A),
            surfaceContainerHighest = Color(0xFF242424)
        )
    } else {
        baseColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode && view.context is Activity) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
