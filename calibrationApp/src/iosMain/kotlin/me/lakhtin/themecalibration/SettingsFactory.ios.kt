package me.lakhtin.themecalibration

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun createSettings(): Settings {
    return NSUserDefaultsSettings(
        delegate = NSUserDefaults.standardUserDefaults
    )
}