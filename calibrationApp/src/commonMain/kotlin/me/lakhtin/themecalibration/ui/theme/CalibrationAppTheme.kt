package me.lakhtin.themecalibration.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CalibrationAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,  // Dynamic color is available on Android 12+
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    val appTypography = createAppTypography(useCondensed = true)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        content = content
    )
}