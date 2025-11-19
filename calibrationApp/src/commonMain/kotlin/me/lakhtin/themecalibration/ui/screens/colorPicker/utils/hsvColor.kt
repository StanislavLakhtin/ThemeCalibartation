package me.lakhtin.themecalibration.ui.screens.colorPicker.utils

import androidx.compose.ui.graphics.Color

fun hsvToColor(h: Float, s: Float, v: Float): Color {
    val h = h.toDouble()
    val s = s.toDouble()
    val v = v.toDouble()

    val hh = ((h % 360.0) + 360.0) % 360.0
    val c = v * s
    val x = c * (1 - kotlin.math.abs((hh / 60) % 2 - 1))
    val m = v - c

    val (r1, g1, b1) = when ((hh / 60).toInt()) {
        0 -> Triple(c, x, 0.0)
        1 -> Triple(x, c, 0.0)
        2 -> Triple(0.0, c, x)
        3 -> Triple(0.0, x, c)
        4 -> Triple(x, 0.0, c)
        else -> Triple(c, 0.0, x)
    }

    return Color(
        (r1 + m).toFloat(),
        (g1 + m).toFloat(),
        (b1 + m).toFloat()
    )
}



fun colorToHsv(color: Color, previousHue: Float?): FloatArray {
    val r = color.red
    val g = color.green
    val b = color.blue

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val delta = max - min

    // Hue calculation
    val rawHue = when {
        // delta < 1e-10f -> previousHue
        max == r -> 60f * (((g - b) / delta) % 6f)
        max == g -> 60f * (((b - r) / delta) + 2f)
        else -> 60f * (((r - g) / delta) + 4f)
    }

    val hue = previousHue ?: rawHue

    val h = ((hue % 360f) + 360f) % 360f
    val s = if (max == 0f) 0f else delta / max
    val v = max

    return floatArrayOf(h, s, v)
}


