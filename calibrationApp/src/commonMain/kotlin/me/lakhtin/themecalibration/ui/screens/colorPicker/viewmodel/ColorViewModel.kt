package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColorViewModel : ViewModel() {
    private val _state = MutableStateFlow(ColorUiState())
    val state: StateFlow<ColorUiState> = _state

    fun onEvent(event: ColorEvent) {
        when (event) {
            is ColorEvent.SelectColor -> updateColor(event.color)
        }
    }

    private fun updateColor(color: Color) {
        viewModelScope.launch {
            _state.value = _state.value.copy(selectedColor = color)
        }
    }
}