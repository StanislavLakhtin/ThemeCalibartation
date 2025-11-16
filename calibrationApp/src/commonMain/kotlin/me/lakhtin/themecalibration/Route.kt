package me.lakhtin.themecalibration

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object NavigationUp : Route()

    @Serializable
    data object MainScreenRoute : Route()

    @Serializable
    data object TypographyScreenRoute : Route()

    @Serializable
    data object ColorScreenRoute : Route()

    @Serializable
    data object ColorPickerScreenRoute : Route()
}