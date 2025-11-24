package me.lakhtin.themecalibration.data.repository

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import me.lakhtin.themecalibration.createSettings
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHex

enum class ColorKey {
    // Primary colors
    PRIMARY,
    ON_PRIMARY,
    PRIMARY_CONTAINER,
    ON_PRIMARY_CONTAINER,

    // Secondary colors
    SECONDARY,
    ON_SECONDARY,
    SECONDARY_CONTAINER,
    ON_SECONDARY_CONTAINER,

    // Tertiary colors
    TERTIARY,
    ON_TERTIARY,
    TERTIARY_CONTAINER,
    ON_TERTIARY_CONTAINER,

    // Error colors
    ERROR,
    ON_ERROR,
    ERROR_CONTAINER,
    ON_ERROR_CONTAINER,

    // Background colors
    BACKGROUND,
    ON_BACKGROUND,

    // Surface colors
    SURFACE,
    ON_SURFACE,
    SURFACE_VARIANT,
    ON_SURFACE_VARIANT,

    // Outline colors
    OUTLINE,
    OUTLINE_VARIANT,

    // Scrim
    SCRIM,

    // Inverse colors
    INVERSE_SURFACE,
    INVERSE_ON_SURFACE,
    INVERSE_PRIMARY,

    // Surface container colors
    SURFACE_DIM,
    SURFACE_BRIGHT,
    SURFACE_CONTAINER_LOWEST,
    SURFACE_CONTAINER_LOW,
    SURFACE_CONTAINER,
    SURFACE_CONTAINER_HIGH,
    SURFACE_CONTAINER_HIGHEST
}

class ColorRepository(
    private val settings: Settings = createSettings()
) {
    fun getSavedColor(key: ColorKey, colorScheme: ColorScheme): String {
        return settings[key.toString()] ?: getDefaultColor(key, colorScheme)
    }

    fun saveColor(key: ColorKey, hex: String) {
        settings[key.toString()] = hex
    }

    private fun getDefaultColor(key: ColorKey, colorScheme: ColorScheme): String {
        return colorToHex(
            when (key) {
                // Primary colors
                ColorKey.PRIMARY -> colorScheme.primary
                ColorKey.ON_PRIMARY -> colorScheme.onPrimary
                ColorKey.PRIMARY_CONTAINER -> colorScheme.primaryContainer
                ColorKey.ON_PRIMARY_CONTAINER -> colorScheme.onPrimaryContainer

                // Secondary colors
                ColorKey.SECONDARY -> colorScheme.secondary
                ColorKey.ON_SECONDARY -> colorScheme.onSecondary
                ColorKey.SECONDARY_CONTAINER -> colorScheme.secondaryContainer
                ColorKey.ON_SECONDARY_CONTAINER -> colorScheme.onSecondaryContainer

                // Tertiary colors
                ColorKey.TERTIARY -> colorScheme.tertiary
                ColorKey.ON_TERTIARY -> colorScheme.onTertiary
                ColorKey.TERTIARY_CONTAINER -> colorScheme.tertiaryContainer
                ColorKey.ON_TERTIARY_CONTAINER -> colorScheme.onTertiaryContainer

                // Error colors
                ColorKey.ERROR -> colorScheme.error
                ColorKey.ON_ERROR -> colorScheme.onError
                ColorKey.ERROR_CONTAINER -> colorScheme.errorContainer
                ColorKey.ON_ERROR_CONTAINER -> colorScheme.onErrorContainer

                // Background colors
                ColorKey.BACKGROUND -> colorScheme.background
                ColorKey.ON_BACKGROUND -> colorScheme.onBackground

                // Surface colors
                ColorKey.SURFACE -> colorScheme.surface
                ColorKey.ON_SURFACE -> colorScheme.onSurface
                ColorKey.SURFACE_VARIANT -> colorScheme.surfaceVariant
                ColorKey.ON_SURFACE_VARIANT -> colorScheme.onSurfaceVariant

                // Outline colors
                ColorKey.OUTLINE -> colorScheme.outline
                ColorKey.OUTLINE_VARIANT -> colorScheme.outlineVariant

                // Scrim
                ColorKey.SCRIM -> colorScheme.scrim

                // Inverse colors
                ColorKey.INVERSE_SURFACE -> colorScheme.inverseSurface
                ColorKey.INVERSE_ON_SURFACE -> colorScheme.inverseOnSurface
                ColorKey.INVERSE_PRIMARY -> colorScheme.inversePrimary

                // Surface container colors
                ColorKey.SURFACE_DIM -> colorScheme.surfaceDim
                ColorKey.SURFACE_BRIGHT -> colorScheme.surfaceBright
                ColorKey.SURFACE_CONTAINER_LOWEST -> colorScheme.surfaceContainerLowest
                ColorKey.SURFACE_CONTAINER_LOW -> colorScheme.surfaceContainerLow
                ColorKey.SURFACE_CONTAINER -> colorScheme.surfaceContainer
                ColorKey.SURFACE_CONTAINER_HIGH -> colorScheme.surfaceContainerHigh
                ColorKey.SURFACE_CONTAINER_HIGHEST -> colorScheme.surfaceContainerHighest
            }
        )
    }
}