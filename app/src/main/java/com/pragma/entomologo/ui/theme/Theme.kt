package com.pragma.entomologo.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecundaryDark,
    tertiary = TertiaryDark,
    error = ErrorDark,
    background = BackgroundDark,
    surfaceVariant = SurfaceVariantDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecundaryDark,
    onTertiary = OnTertiaryDark,
    onError = OnErrorDark,
    onBackground = OnBackgroundDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    primaryContainer = PrimaryContainerDark,
    secondaryContainer = SecundaryContainerDark,
    tertiaryContainer = TertiaryContainerDark,
    errorContainer = ErrorContainerDark,
    surface = SurfaceDark,
    outline = OutlineDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    onSecondaryContainer = OnSecundaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    onErrorContainer = OnErrorContainerDark,
    onSurface = OnSurfaceDark,
    outlineVariant = OutlineVariantDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLigth,
    secondary = SecundaryLigth,
    tertiary = TertiaryLigth,
    error = ErrorLigth,
    background = BackgroundLigth,
    surfaceVariant = SurfaceVariantLigth,
    onPrimary = OnPrimaryLigth,
    onSecondary = OnSecundaryLigth,
    onTertiary = OnTertiaryLigth,
    onError = OnErrorLigth,
    onBackground = OnBackgroundLigth,
    onSurfaceVariant = OnSurfaceVariantLigth,
    primaryContainer = PrimaryContainerLigth,
    secondaryContainer = SecundaryContainerLigth,
    tertiaryContainer = TertiaryContainerLigth,
    errorContainer = ErrorContainerLigth,
    surface = SurfaceLigth,
    outline = OutlineLigth,
    onPrimaryContainer = OnPrimaryContainerLigth,
    onSecondaryContainer = OnSecundaryContainerLigth,
    onTertiaryContainer = OnTertiaryContainerLigth,
    onErrorContainer = OnErrorContainerLigth,
    onSurface = OnSurfaceLigth,
    outlineVariant = OutlineVariantLigth
)

@Composable
fun EntomologoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
        content = content
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}