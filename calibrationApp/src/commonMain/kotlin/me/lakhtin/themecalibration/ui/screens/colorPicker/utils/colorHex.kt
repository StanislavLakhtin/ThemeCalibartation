package me.lakhtin.themecalibration.ui.screens.colorPicker.utils

import androidx.compose.ui.graphics.Color

fun colorToHex(color: Color): String {
    return "#${(color.red * 255).toInt().toString(16).padStart(2, '0')}" +
            (color.green * 255).toInt().toString(16).padStart(2, '0') +
            (color.blue * 255).toInt().toString(16).padStart(2, '0').uppercase()
}

fun hexToColor(hexString: String): Color {
    val cleanHex = hexString.removePrefix("#")
    return when (cleanHex.length) {
        6 -> Color(
            red = cleanHex.substring(0, 2).toInt(16),
            green = cleanHex.substring(2, 4).toInt(16),
            blue = cleanHex.substring(4, 6).toInt(16)
        )
        8 -> Color(
            alpha = cleanHex.substring(0, 2).toInt(16),
            red = cleanHex.substring(2, 4).toInt(16),
            green = cleanHex.substring(4, 6).toInt(16),
            blue = cleanHex.substring(6, 8).toInt(16)
        )
        else -> Color.Black // fallback цвет
    }
}

fun isValidHex(hex: String): Boolean {
    val cleanHex = hex.trim().uppercase()
    return cleanHex.matches(Regex("#?[0-9A-F]{3}")) ||
            cleanHex.matches(Regex("#?[0-9A-F]{6}"))
}
