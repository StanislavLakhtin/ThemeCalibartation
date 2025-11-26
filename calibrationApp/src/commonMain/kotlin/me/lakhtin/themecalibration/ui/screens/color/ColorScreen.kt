package me.lakhtin.themecalibration.ui.screens.color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ColorScheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.ui.components.TopBar
import me.lakhtin.themecalibration.ui.screens.color.components.ColorDropdownMenu
import me.lakhtin.themecalibration.ui.screens.color.components.ColorPairDropdownMenu
import me.lakhtin.themecalibration.ui.screens.color.components.ResultColorCard
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel

@Composable
fun ColorScreen(
    viewModel: ColorViewModel = ColorViewModel(),
    navigateTo: (Route) -> Unit
) {
    ColorScreenView(viewModel, navigateTo = navigateTo)
}

@Composable
fun ColorScreenView(
    viewModel: ColorViewModel = ColorViewModel(),
    navigateTo: (Route) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val allThemeColors = getCurrentThemeColors(viewModel, colorScheme)
    val themeColorPairs = getCurrentThemeColorPairs(viewModel, colorScheme)

    var selectedColorPair by remember { mutableStateOf(themeColorPairs[0]) }
    var selectedColor by remember { mutableStateOf(allThemeColors[0]) }

    val availableColors = remember(selectedColorPair) {
        allThemeColors.filter {
            it.name != selectedColorPair.first.name && it.name != selectedColorPair.second.name
        }
    }

    if (availableColors.none { it.name == selectedColor.name }) {
        selectedColor = availableColors.firstOrNull() ?: allThemeColors[0]
    }

    Scaffold(
        topBar = {
            TopBar("Примерка", { navigateTo(Route.NavigationUp) })
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
                ColorPairDropdownMenu(
                    selectedColor = selectedColorPair,
                    colorPairs = themeColorPairs,
                    onColorSelected = { selectedColorPair = it }
                )
            }
            item {
                ColorDropdownMenu(
                    selectedColor = selectedColor,
                    colors = availableColors,
                    onColorSelected = { selectedColor = it },
                    onColorEditClick = { colorKey ->
                        viewModel.setSelectedColor(colorKey)
                        navigateTo(Route.ColorPickerScreenRoute)
                    },
                )
            }
            item {
                ResultColorCard(
                    colorPair = selectedColorPair,
                    selectedColor = selectedColor,
                )
            }
        }
    }
}
