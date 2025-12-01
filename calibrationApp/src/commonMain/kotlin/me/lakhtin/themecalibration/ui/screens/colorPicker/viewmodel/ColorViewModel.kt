package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.data.repository.ColorRepository
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHsv
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor

data class ColorState(
    val color: Color = Color.Red,
    val hue: Float = 0f,
    val hex: String = ""
)

class ColorViewModel(
    private val repository: ColorRepository = ColorRepository()
) : ViewModel() {

    // Состояние для цвета
    private val _colorState = MutableStateFlow(ColorState())
    val colorState: StateFlow<ColorState> = _colorState

    // Состояние для ключа выбранного цвета
    private val _selectedColorKey = MutableStateFlow<ColorKey?>(null)
    val selectedColorKey: StateFlow<ColorKey?> = _selectedColorKey

    // Состояние для всех цветов
    private val _themeColors = MutableStateFlow<Map<ColorKey, String>>(emptyMap())
    val themeColors: StateFlow<Map<ColorKey, String>> = _themeColors

    fun loadThemeColors(colorScheme: ColorScheme) {
        viewModelScope.launch {
            val colors =
                ColorKey.entries.associateWith { key -> repository.getSavedColor(key, colorScheme) }
            _themeColors.value = colors
        }
    }

    private fun updateColorInTheme(key: ColorKey, hex: String) {
        _themeColors.value = _themeColors.value.toMutableMap().apply {
            this[key] = hex
        }
    }

    fun updateColorState(color: Color? = null, hue: Float? = null, hex: String? = null) {
        _colorState.value = _colorState.value.copy(
            color = color ?: _colorState.value.color,
            hue = hue ?: _colorState.value.hue,
            hex = hex ?: _colorState.value.hex
        )
    }

    fun loadSelectedColor(key: ColorKey, colorScheme: ColorScheme) {
        viewModelScope.launch {
            val colorHex = repository.getSavedColor(key, colorScheme)
            val color = hexToColor(colorHex)
            val hue = colorToHsv(color, null)[0]
            updateColorState(color, hue, colorHex)
            _selectedColorKey.value = key
        }
    }

    fun saveColor(key: ColorKey, hex: String) {
        viewModelScope.launch {
            repository.saveColor(key, hex)

            val updatedColors = _themeColors.value.toMutableMap()
            updatedColors[key] = hex
            _themeColors.value = updatedColors

            // loadSelectedColor(key, colorScheme)
        }
    }

    fun setSelectedColorKey(key: ColorKey, colorScheme: ColorScheme) {
        _selectedColorKey.value = key
        loadSelectedColor(key, colorScheme)
    }

    fun getColor(key: ColorKey, colorScheme: ColorScheme): String {
        return _themeColors.value[key] ?: repository.getSavedColor(key, colorScheme)
    }

    fun resetColor(key: ColorKey, colorScheme: ColorScheme) {
        viewModelScope.launch {
            repository.removeColor(key)

            val colorHex = repository.getSavedColor(key, colorScheme)
            updateColorInTheme(key, colorHex)
        }
    }

    fun resetAllColors(colorScheme: ColorScheme) {
        viewModelScope.launch {
            ColorKey.entries.forEach { key ->
                repository.removeColor(key)
            }

            loadThemeColors(colorScheme)
        }
    }

    fun isColorModified(key: ColorKey, colorScheme: ColorScheme, hex: String): Boolean {
        return repository.isColorModified(key, colorScheme, hex)
    }
}