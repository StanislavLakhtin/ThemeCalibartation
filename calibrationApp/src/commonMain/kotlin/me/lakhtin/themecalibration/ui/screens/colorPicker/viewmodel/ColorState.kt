package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import androidx.compose.ui.graphics.Color

data class ColorUiState (
    val selectedColor: Color = Color.Red
)

sealed interface ColorEvent {
    data class SelectColor(val color: Color): ColorEvent
}