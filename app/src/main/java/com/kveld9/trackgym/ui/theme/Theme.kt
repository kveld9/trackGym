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

@Composable
fun TrackGymTheme(
    themeMode: String = "AMOLED", // "LIGHT", "DARK", "AMOLED"
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isLight = themeMode == "LIGHT"
    val isAmoled = themeMode == "AMOLED"

    val baseColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isLight) dynamicLightColorScheme(context) else dynamicDarkColorScheme(context)
    } else {
        if (isLight) lightColorScheme() else darkColorScheme()
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
