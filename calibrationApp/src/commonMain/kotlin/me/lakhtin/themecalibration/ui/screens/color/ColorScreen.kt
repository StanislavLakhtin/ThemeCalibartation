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
    navigateTo: (Route) -> Unit
) {
    ColorScreenView(navigateTo = navigateTo)
}

@Composable
fun ColorScreenView(viewModel: ColorViewModel = viewModel(), navigateTo: (Route) -> Unit) {
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
            TopBar("Color", { navigateTo(Route.NavigationUp) })
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
                    onColorSelected = { selectedColor = it }
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

data class ColorInfo(
    val name: String,
    val color: Color,
    val colorKey: ColorKey? = null
)

data class ColorPair(
    val name: String,
    val first: ColorInfo,
    val second: ColorInfo
)

@Composable
private fun getCurrentThemeColors(
    viewModel: ColorViewModel,
    colorScheme: ColorScheme
): List<ColorInfo> {
    return ColorKey.entries.map { key ->
        val hexColor = viewModel.getColor(key, colorScheme)
        ColorInfo(
            name = key.name,
            color = hexToColor(hexColor),
            colorKey = key
        )
    }
}

@Composable
private fun getCurrentThemeColorPairs(
    viewModel: ColorViewModel,
    colorScheme: ColorScheme
): List<ColorPair> {
    return listOf(
        ColorPair(
            "Primary",
            getColorInfo(ColorKey.PRIMARY, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_PRIMARY, viewModel, colorScheme)
        ),
        ColorPair(
            "Secondary",
            getColorInfo(ColorKey.SECONDARY, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_SECONDARY, viewModel, colorScheme)
        ),
        ColorPair(
            "Tertiary",
            getColorInfo(ColorKey.TERTIARY, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_TERTIARY, viewModel, colorScheme)
        ),
        ColorPair(
            "Primary Container",
            getColorInfo(ColorKey.PRIMARY_CONTAINER, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_PRIMARY_CONTAINER, viewModel, colorScheme)
        ),
        ColorPair(
            "Secondary Container",
            getColorInfo(ColorKey.SECONDARY_CONTAINER, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_SECONDARY_CONTAINER, viewModel, colorScheme)
        ),
        ColorPair(
            "Tertiary Container",
            getColorInfo(ColorKey.TERTIARY_CONTAINER, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_TERTIARY_CONTAINER, viewModel, colorScheme)
        ),
        ColorPair(
            "Error",
            getColorInfo(ColorKey.ERROR, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_ERROR, viewModel, colorScheme)
        ),
        ColorPair(
            "Error Container",
            getColorInfo(ColorKey.ERROR_CONTAINER, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_ERROR_CONTAINER, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_SURFACE, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Variant",
            getColorInfo(ColorKey.SURFACE_VARIANT, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_SURFACE_VARIANT, viewModel, colorScheme)
        ),
        ColorPair(
            "Background",
            getColorInfo(ColorKey.BACKGROUND, viewModel, colorScheme),
            getColorInfo(ColorKey.ON_BACKGROUND, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Dim",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_DIM, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Bright",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_BRIGHT, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Container Lowest",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_CONTAINER_LOWEST, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Container Low",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_CONTAINER_LOW, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Container",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_CONTAINER, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Container High",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_CONTAINER_HIGH, viewModel, colorScheme)
        ),
        ColorPair(
            "Surface Container Highest",
            getColorInfo(ColorKey.SURFACE, viewModel, colorScheme),
            getColorInfo(ColorKey.SURFACE_CONTAINER_HIGHEST, viewModel, colorScheme)
        ),
    )
}

@Composable
private fun getColorInfo(
    key: ColorKey,
    viewModel: ColorViewModel,
    colorScheme: ColorScheme
): ColorInfo {
    val hexColor = viewModel.getColor(key, colorScheme)
    return ColorInfo(
        name = key.name,
        color = hexToColor(hexColor),
        colorKey = key
    )
}
