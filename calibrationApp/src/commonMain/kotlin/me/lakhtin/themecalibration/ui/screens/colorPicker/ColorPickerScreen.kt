package me.lakhtin.themecalibration.ui.screens.colorPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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


@Composable
fun ColorPickerScreen(
    viewModel: ColorViewModel = ColorViewModel(),
    navigateTo: (Route) -> Unit
) {
    ColorPickerScreenView(viewModel = viewModel, navigateTo = navigateTo)
}

@Composable
fun ColorPickerScreenView (
    viewModel: ColorViewModel = ColorViewModel(),
    navigateTo: (Route) -> Unit
) {
    val selectedColor = remember { mutableStateOf<Color>(Color.Red) }
    val selectedHue = remember { mutableStateOf(0f) }
    val hexInput = remember { mutableStateOf<String>(colorToHex(selectedColor.value)) }

    fun onValueChangeHex (newHex: String) {
        hexInput.value = newHex
        if (isValidHex(newHex)) {
            val newColor = hexToColor(newHex)
            val newHsv = colorToHsv(
                newColor,
                previousHue = null
            )
            selectedHue.value = newHsv[0]
            selectedColor.value = hexToColor(newHex)
        }
    }

    hexInput.value = colorToHex(selectedColor.value)


    Scaffold (
        topBar = {
            TopBar("Color picker") { navigateTo(Route.NavigationUp) }
        }
    ) { innerPadding ->
        LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item { ColorWheelPicker(
            selectedColor = selectedColor.value,
            onColorSelected = { newColor ->
                if (!colorsAreClose(selectedColor.value, newColor)) {
                    selectedColor.value = newColor
                }
            },
            selectedHue = selectedHue.value,
            onHueSelected = { selectedHue.value = it },
            modifier = Modifier
        ) }
        item {
            ColorInput(
                hexValue = hexInput.value,
                onValueChange = { newHex ->
                    onValueChangeHex(newHex)
                }
            )
        }
        item { ColorPreviewCard(
            color = selectedColor.value,
        ) }
        item {
                Button(onClick = {
                    viewModel.saveColor(hexInput.value)
                }) {
                    Text("Сохранить")
                }
        }
    } }
}


private fun colorsAreClose(c1: Color, c2: Color): Boolean {
    return (c1.red - c2.red).absoluteValue < 0.0005f &&
            (c1.green - c2.green).absoluteValue < 0.0005f &&
            (c1.blue - c2.blue).absoluteValue < 0.0005f
}
