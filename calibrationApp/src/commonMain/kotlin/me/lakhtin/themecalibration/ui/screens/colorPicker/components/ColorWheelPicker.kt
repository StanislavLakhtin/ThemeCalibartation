package me.lakhtin.themecalibration.ui.screens.colorPicker.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun ColorWheelPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val hue = remember { mutableStateOf(0f) }
    val saturation = remember { mutableStateOf(1f) }
    val brightness = remember { mutableStateOf(1f) }

    LaunchedEffect(hue.value, saturation.value, brightness.value) {
        onColorSelected(hsvToColor(hue.value, saturation.value, brightness.value))
    }

    LaunchedEffect(selectedColor.value) {
        val hsv = colorToHsv(selectedColor)
        hue.value = hsv[0]
        saturation.value = hsv[1]
        brightness.value = hsv[2]
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        HuePalette(
            selectedHue = hue.value,
            onHueSelected = { hue.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        SaturationBrightnessPicker(
            hue = hue.value,
            saturation = saturation.value,
            brightness = brightness.value,
            onSaturationBrightnessSelected = { s, v ->
                saturation.value = s
                brightness.value = v
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        ColorValuesDisplay(hue.value, saturation.value, brightness.value)
    }
}

@Composable
fun HuePalette(
    selectedHue: Float,
    onHueSelected: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val hue = (offset.x / size.width) * 360f
                    onHueSelected(hue.coerceIn(0f, 360f))
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val hue = (change.position.x / size.width) * 360f
                    onHueSelected(hue.coerceIn(0f, 360f))
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (x in 0 until size.width.toInt() step 5) {
                val hue = (x.toFloat() / size.width) * 360f
                val color = hsvToColor(hue, 1f, 1f)

                drawRect(
                    color = color,
                    topLeft = Offset(x.toFloat(), 0f),
                    size = Size(5f, size.height)
                )
            }

            val markerX = (selectedHue / 360f) * size.width
            drawCircle(
                color = hsvToColor(selectedHue, 1f, 1f),
                radius = 16.dp.toPx(),
                center = Offset(markerX, size.height / 2),
            )
            drawCircle(
                color = Color.White,
                radius = 16.dp.toPx(),
                center = Offset(markerX, size.height / 2),
                style = Stroke(width = 3.dp.toPx() )
            )
        }
    }
}

@Composable
fun SaturationBrightnessPicker(
    hue: Float,
    saturation: Float,
    brightness: Float,
    onSaturationBrightnessSelected: (saturation: Float, brightness: Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val s = (offset.x / size.width).coerceIn(0f, 1f)
                    val v = 1f - (offset.y / size.height).coerceIn(0f, 1f)
                    onSaturationBrightnessSelected(s, v)
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val s = (change.position.x / size.width).coerceIn(0f, 1f)
                    val v = 1f - (change.position.y / size.height).coerceIn(0f, 1f)
                    onSaturationBrightnessSelected(s, v)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (x in 0 until size.width.toInt() step 5) {
                for (y in 0 until size.height.toInt() step 5) {
                    val s = x.toFloat() / size.width
                    val v = 1f - (y.toFloat() / size.height)
                    val color = hsvToColor(hue, s, v)

                    drawRect(
                        color = color,
                        topLeft = Offset(x.toFloat(), y.toFloat()),
                        size = Size(5f, 5f)
                    )
                }
            }

            val markerX = saturation * size.width
            val markerY = (1f - brightness) * size.height

            drawCircle(
                color = Color.White,
                radius = 12.dp.toPx(),
                center = Offset(markerX, markerY),
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                color = Color.Transparent,
                radius = 6.dp.toPx(),
                center = Offset(markerX, markerY)
            )
        }
    }
}

@Composable
fun ColorValuesDisplay(hue: Float, saturation: Float, brightness: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("H", style = MaterialTheme.typography.labelSmall)
            Text(
                hue.toInt().toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("S", style = MaterialTheme.typography.labelSmall)
            Text(
                "${(saturation * 100).toInt()}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("V", style = MaterialTheme.typography.labelSmall)
            Text(
                "${(brightness * 100).toInt()}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Hex", style = MaterialTheme.typography.labelSmall)
            val color = hsvToColor(hue, saturation, brightness)
            val hex = colorToHex(color)
            Text(
                hex,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun hsvToColor(h: Float, s: Float, v: Float): Color {
    val h = h % 360f
    val c = v * s
    val x = c * (1 - abs((h / 60f) % 2f - 1))
    val m = v - c

    val (r1, g1, b1) = when {
        h < 60f -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }

    return Color(r1 + m, g1 + m, b1 + m)
}

fun colorToHsv(color: Color): FloatArray {
    val r = color.red
    val g = color.green
    val b = color.blue

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val delta = max - min

    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> (b - r) / delta + 2f
        else -> (r - g) / delta + 4f
    } * 60f

    val s = if (max == 0f) 0f else delta / max
    val v = max

    return floatArrayOf(if (h < 0) h + 360 else h, s, v)
}

fun colorToHex(color: Color): String {
    return "#${(color.red * 255).toInt().toString(16).padStart(2, '0')}" +
            (color.green * 255).toInt().toString(16).padStart(2, '0') +
            (color.blue * 255).toInt().toString(16).padStart(2, '0').uppercase()
}