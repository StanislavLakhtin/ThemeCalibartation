package me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel

import me.lakhtin.themecalibration.data.repository.ColorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ColorViewModel(
    private val repo: ColorRepository = ColorRepository()
) {
    private val _color = MutableStateFlow(repo.getSavedColor())
    val color = _color.asStateFlow()

    fun saveColor(hex: String) {
        repo.saveColor(hex)
        _color.value = hex
    }
}