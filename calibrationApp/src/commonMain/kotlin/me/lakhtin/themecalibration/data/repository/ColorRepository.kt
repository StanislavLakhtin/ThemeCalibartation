package me.lakhtin.themecalibration.data.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import me.lakhtin.themecalibration.createSettings

class ColorRepository(
    private val settings: Settings = createSettings()
) {
    private val key = "saved_color_hex"
    private val defaultColor = "#6200EE"

    fun getSavedColor(): String {
        return settings[key] ?: defaultColor
    }

    fun saveColor(hex: String) {
        settings[key] = hex
    }
}
