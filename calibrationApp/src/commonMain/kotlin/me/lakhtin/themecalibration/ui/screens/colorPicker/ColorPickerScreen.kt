package me.lakhtin.themecalibration.ui.screens.colorPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.components.TopBar
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorInput
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorPreviewCard
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorWheelPicker
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHsv
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.isValidHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import kotlin.math.absoluteValue
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorKeyDropdownMenu


@Composable
fun ColorPickerScreen(
    viewModel: ColorViewModel = ColorViewModel(), navigateTo: (Route) -> Unit
) {
    ColorPickerScreenView(viewModel = viewModel, navigateTo = navigateTo)
}

@Composable
fun ColorPickerScreenView(
    viewModel: ColorViewModel = ColorViewModel(), navigateTo: (Route) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val colorState by viewModel.colorState.collectAsState()
    val selectedColorKey by viewModel.selectedColorKey.collectAsState()
    println(colorState.hex)

    var hexInput by remember { mutableStateOf("") }

    LaunchedEffect(colorState) {
        hexInput = colorToHex(colorState.color)
    }

    Scaffold(
        topBar = {
            TopBar("Палитра") { navigateTo(Route.NavigationUp) }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                ColorWheelPicker(
                    selectedColor = colorState.color,
                    onColorSelected = { newColor ->
                        if (!colorsAreClose(colorState.color, newColor)) {
                            viewModel.updateColorState(
                                newColor,
                            )
                        }
                    },
                    selectedHue = colorState.hue,
                    onHueSelected = { newHue ->
                        viewModel.updateColorState(
                            colorState.color,
                            newHue,
                        )
                    },
                    modifier = Modifier
                )
            }
            item {
                ColorInput(
                    hexValue = hexInput,
                    onValueChange = { newHex ->
                        hexInput = newHex
                        if (isValidHex(newHex)) {
                            val color = hexToColor(newHex)
                            val hue = colorToHsv(color, null)[0]
                            viewModel.updateColorState(
                                color,
                                hue = hue
                            )
                        }
                    }
                )
            }
            item {
                ColorPreviewCard(color = colorState.color)
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            selectedColorKey?.let { key ->
                                viewModel.saveColor(key, colorToHex(colorState.color))
                            }
                        },
                        enabled = selectedColorKey != null
                    ) {
                        Text("Сохранить в")
                    }
                    ColorKeyDropdownMenu(
                        viewModel = viewModel,
                        selectedColorKey = selectedColorKey,
                        onColorKeySelected = { newKey ->
                            viewModel.setSelectedColorKey(
                                newKey,
                                colorScheme
                            )
                        },
                    )
                }
            }
        }
    }
}


private fun colorsAreClose(c1: Color, c2: Color): Boolean {
    return (c1.red - c2.red).absoluteValue < 0.0005f && (c1.green - c2.green).absoluteValue < 0.0005f && (c1.blue - c2.blue).absoluteValue < 0.0005f
}
