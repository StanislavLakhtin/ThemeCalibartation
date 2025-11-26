package me.lakhtin.themecalibration.ui.screens.colorPicker.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHsv
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hsvToColor
import kotlin.math.pow


@Composable
fun ColorWheelPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    selectedHue: Float,
    onHueSelected: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val saturationState = remember { mutableStateOf(1f) }
    val brightnessState = remember { mutableStateOf(1f) }

    LaunchedEffect(selectedColor) {
        val newHsv = colorToHsv(selectedColor, selectedHue)
        saturationState.value = newHsv[1]
        brightnessState.value = newHsv[2]
    }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        HuePalette(
            selectedHue = selectedHue,
            onHueSelected = { newHue ->
                onHueSelected(newHue)
                onColorSelected(hsvToColor(newHue, saturationState.value, brightnessState.value))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        SaturationBrightnessPicker(
            hue = selectedHue,
            saturation = saturationState.value,
            brightness = brightnessState.value,
            onSaturationBrightnessSelected = { hue, s, v ->
                saturationState.value = s
                brightnessState.value = v

                println(hue)

                val newColor = hsvToColor(hue, s, v)
                onColorSelected(newColor)
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        ColorValuesDisplay(selectedHue, saturationState.value, brightnessState.value)

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
            .pointerInput(Unit) {
                detectTapGestures { pos ->
                    val hue = (pos.x / size.width) * 360f
                    onHueSelected(hue.coerceIn(0f, 360f))
                }
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { pos ->
                        val hue = (pos.x / size.width) * 360f
                        onHueSelected(hue.coerceIn(0f, 360f))
                    },
                    onDrag = { change, _ ->
                        val hue = (change.position.x / size.width) * 360f
                        onHueSelected(hue.coerceIn(0f, 360f))
                        change.consumeAllChanges()
                    }
                )
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
                style = Stroke(width = 3.dp.toPx())
            )
        }
    }

}

@Composable
fun SaturationBrightnessPicker(
    hue: Float,
    saturation: Float,
    brightness: Float,
    onSaturationBrightnessSelected: (hue: Float, saturation: Float, brightness: Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .pointerInput(hue) {
                detectTapGestures { pos ->
                    val s = (pos.x / size.width).coerceIn(0f, 1f)
                    val v = 1f - (pos.y / size.height).coerceIn(0f, 1f)
                    onSaturationBrightnessSelected(hue,s, v)
                }
            }
            .pointerInput(hue) {
                detectDragGestures(
                    onDragStart = { pos ->
                        val s = (pos.x / size.width).coerceIn(0f, 1f)
                        val v = 1f - (pos.y / size.height).coerceIn(0f, 1f)
                        onSaturationBrightnessSelected(hue,s, v)
                    },
                    onDrag = { change, _ ->
                        val s = (change.position.x / size.width).coerceIn(0f, 1f)
                        val v = 1f - (change.position.y / size.height).coerceIn(0f, 1f)
                        onSaturationBrightnessSelected(hue,s, v)
                        change.consumeAllChanges()
                    }
                )
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