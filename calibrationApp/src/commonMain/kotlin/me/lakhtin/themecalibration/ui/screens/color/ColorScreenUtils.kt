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
    themeColors: Map<ColorKey, String>
): List<ColorInfo> {
    return themeColors.entries.map { (key, hex) ->
        ColorInfo(
            name = key.name,
            color = hexToColor(hex),
            colorKey = key
        )
    }
}

@Composable
fun getCurrentThemeColorPairs(
    themeColors: Map<ColorKey, String>
): List<ColorPair> {
    return listOf(
        ColorPair(
            "Primary",
            getColorInfo(ColorKey.PRIMARY, themeColors),
            getColorInfo(ColorKey.ON_PRIMARY, themeColors)
        ),
        ColorPair(
            "Secondary",
            getColorInfo(ColorKey.SECONDARY, themeColors),
            getColorInfo(ColorKey.ON_SECONDARY, themeColors)
        ),
        ColorPair(
            "Tertiary",
            getColorInfo(ColorKey.TERTIARY, themeColors),
            getColorInfo(ColorKey.ON_TERTIARY, themeColors)
        ),
        ColorPair(
            "Primary Container",
            getColorInfo(ColorKey.PRIMARY_CONTAINER, themeColors),
            getColorInfo(ColorKey.ON_PRIMARY_CONTAINER, themeColors)
        ),
        ColorPair(
            "Secondary Container",
            getColorInfo(ColorKey.SECONDARY_CONTAINER, themeColors),
            getColorInfo(ColorKey.ON_SECONDARY_CONTAINER, themeColors)
        ),
        ColorPair(
            "Tertiary Container",
            getColorInfo(ColorKey.TERTIARY_CONTAINER, themeColors),
            getColorInfo(ColorKey.ON_TERTIARY_CONTAINER, themeColors)
        ),
        ColorPair(
            "Error",
            getColorInfo(ColorKey.ERROR, themeColors),
            getColorInfo(ColorKey.ON_ERROR, themeColors)
        ),
        ColorPair(
            "Error Container",
            getColorInfo(ColorKey.ERROR_CONTAINER, themeColors),
            getColorInfo(ColorKey.ON_ERROR_CONTAINER, themeColors)
        ),
        ColorPair(
            "Surface",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.ON_SURFACE, themeColors)
        ),
        ColorPair(
            "Surface Variant",
            getColorInfo(ColorKey.SURFACE_VARIANT, themeColors),
            getColorInfo(ColorKey.ON_SURFACE_VARIANT, themeColors)
        ),
        ColorPair(
            "Background",
            getColorInfo(ColorKey.BACKGROUND, themeColors),
            getColorInfo(ColorKey.ON_BACKGROUND, themeColors)
        ),
        ColorPair(
            "Surface Dim",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_DIM, themeColors)
        ),
        ColorPair(
            "Surface Bright",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_BRIGHT, themeColors)
        ),
        ColorPair(
            "Surface Container Lowest",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_CONTAINER_LOWEST, themeColors)
        ),
        ColorPair(
            "Surface Container Low",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_CONTAINER_LOW, themeColors)
        ),
        ColorPair(
            "Surface Container",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_CONTAINER, themeColors)
        ),
        ColorPair(
            "Surface Container High",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_CONTAINER_HIGH, themeColors)
        ),
        ColorPair(
            "Surface Container Highest",
            getColorInfo(ColorKey.SURFACE, themeColors),
            getColorInfo(ColorKey.SURFACE_CONTAINER_HIGHEST, themeColors)
        ),
    )
}

@Composable
fun getColorInfo(
    key: ColorKey,
    themeColors: Map<ColorKey, String>
): ColorInfo {
    return ColorInfo(
        name = key.name,
        color = hexToColor(themeColors[key] ?: ""),
        colorKey = key
    )
}