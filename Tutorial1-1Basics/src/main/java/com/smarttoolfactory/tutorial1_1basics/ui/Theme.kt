package com.smarttoolfactory.tutorial1_1basics.ui

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = purple200,
    secondary = teal200,
    primaryVariant = purple700,
    error = Color(0xFFB3261E),
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF201F24),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5),
    onError = Color.White
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun ComposeTutorialsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)

        SideEffect {
            // Set system bar colors using Compose's window insets
            WindowCompat.setDecorFitsSystemWindows(window, false)

            // Use the new Material system bar color APIs
            windowInsetsController.isAppearanceLightStatusBars = !darkTheme
            windowInsetsController.isAppearanceLightNavigationBars = !darkTheme

            // Apply translucent effect
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            // Apply the color through the theme instead
            window.setBackgroundDrawable(
                ColorDrawable(colors.surface.copy(alpha = 0.5f).toArgb())
            )
        }
    }

    MaterialTheme(
        colors = colors.copy(
            surface = colors.surface.copy(alpha = 0.5f)
        ),
        typography = typography,
        shapes = shapes,
        content = content
    )
}