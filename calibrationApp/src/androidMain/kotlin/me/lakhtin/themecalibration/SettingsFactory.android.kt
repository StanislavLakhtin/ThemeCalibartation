package me.lakhtin.themecalibration

import android.content.Context.MODE_PRIVATE
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettings(): Settings {
    val prefs = appContext.getSharedPreferences("app_settings", MODE_PRIVATE)
    return SharedPreferencesSettings(prefs)
}