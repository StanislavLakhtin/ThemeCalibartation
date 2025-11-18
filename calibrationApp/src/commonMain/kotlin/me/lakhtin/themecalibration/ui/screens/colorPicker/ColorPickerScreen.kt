package me.lakhtin.themecalibration.ui.screens.colorPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.components.TopBar
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorInput
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorPreviewCard
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.ColorWheelPicker
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.colorToHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.isValidHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.components.parseHexColor


@Composable
fun ColorPickerScreen(
    navigateTo: (Route) -> Unit
) {
    ColorPickerScreenView(navigateTo = navigateTo)
}

@Composable
fun ColorPickerScreenView (
    navigateTo: (Route) -> Unit
) {
    val selectedColor = remember { mutableStateOf<Color>(Color.Red) }
    val hexInput = remember { mutableStateOf<String>(colorToHex(selectedColor.value)) }
    val isInputValid = remember { mutableStateOf<Boolean>(true) }

    fun onValueChangeHex (newHex: String) {
        hexInput.value = newHex
        // TODO: add validation
    }

    LaunchedEffect(selectedColor.value) {
        hexInput.value = colorToHex(selectedColor.value)
        isInputValid.value = true
    }

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
            onColorSelected = { selectedColor.value = it },
            modifier = Modifier
        ) }
        item {
            ColorInput(
                hexValue = hexInput.value,
                isError = !isInputValid.value,
                onValueChange = { newHex ->
                    onValueChangeHex(newHex)
                }
            )
        }
        item { ColorPreviewCard(
            color = selectedColor.value,
        ) }
    } }
}
