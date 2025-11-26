package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import androidx.compose.material3.ColorScheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.data.repository.ColorRepository

class ColorViewModel(
    private val repository: ColorRepository = ColorRepository()
) : ViewModel() {

    private val _selectedColorKey = MutableStateFlow<ColorKey?>(null)
    val selectedColorKey: StateFlow<ColorKey?> = _selectedColorKey

    private val _themeColors = MutableStateFlow<Map<ColorKey, String>>(emptyMap())
    val themeColors: StateFlow<Map<ColorKey, String>> = _themeColors

    fun loadThemeColors(colorScheme: ColorScheme) {
        viewModelScope.launch {
            val colors =
                ColorKey.entries.associateWith { key -> repository.getSavedColor(key, colorScheme) }
            _themeColors.value = colors
        }
    }

    fun saveColor(key: ColorKey, hex: String) {
        viewModelScope.launch {
            repository.saveColor(key, hex)
            _selectedColorKey.value = key

            _themeColors.value = _themeColors.value.toMutableMap().apply {
                this[key] = hex
            }
        }
    }

    fun resetColor(key: ColorKey, colorScheme: ColorScheme) {
        viewModelScope.launch {
            repository.removeColor(key)

            val currentColors = _themeColors.value.toMutableMap()
            currentColors[key] = repository.getSavedColor(key, colorScheme)
            _themeColors.value = currentColors
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

    fun setSelectedColor(key: ColorKey) {
        _selectedColorKey.value = key
    }

    fun getColor(key: ColorKey, colorScheme: ColorScheme): String {
        return repository.getSavedColor(key, colorScheme)
    }

    fun isColorModified(key: ColorKey, colorScheme: ColorScheme, hex: String): Boolean {
        return repository.isColorModified(key, colorScheme, hex)
    }
}