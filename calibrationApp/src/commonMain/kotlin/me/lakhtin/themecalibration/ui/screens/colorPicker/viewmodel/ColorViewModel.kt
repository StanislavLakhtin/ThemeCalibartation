package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import androidx.compose.material3.ColorScheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.data.repository.ColorRepository

class ColorViewModel(
    private val repo: ColorRepository = ColorRepository()
) : ViewModel() {
    private val _selectedColorKey = MutableStateFlow<ColorKey?>(null)

    private val _currentColorHex = MutableStateFlow("#6200EE")

    fun saveColor(key: ColorKey, hex: String) {
        viewModelScope.launch {
            repo.saveColor(key, hex)
            _selectedColorKey.value = key
            _currentColorHex.value = hex
        }
    }

    fun getColor(key: ColorKey, colorScheme: ColorScheme): String {
        return repo.getSavedColor(key, colorScheme)
    }

    fun resetColor(key: ColorKey) {
        viewModelScope.launch {
            repo.removeColor(key)
        }
    }
}