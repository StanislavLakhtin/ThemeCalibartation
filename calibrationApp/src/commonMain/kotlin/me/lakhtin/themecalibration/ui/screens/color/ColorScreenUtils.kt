package me.lakhtin.themecalibration.ui.screens.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel


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
fun getCurrentThemeColors(
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
fun getCurrentThemeColorPairs(
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
fun getColorInfo(
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