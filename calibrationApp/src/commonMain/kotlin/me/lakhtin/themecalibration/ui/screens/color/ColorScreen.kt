package me.lakhtin.themecalibration.ui.screens.color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
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
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.components.TopBar
import me.lakhtin.themecalibration.ui.screens.color.ColorInfo
import me.lakhtin.themecalibration.ui.screens.color.components.ColorDropdownMenu
import me.lakhtin.themecalibration.ui.screens.color.components.ColorPairDropdownMenu
import me.lakhtin.themecalibration.ui.screens.color.components.ResultColorCard
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import me.lakhtin.themecalibration.ui.theme.*

@Composable
fun ColorScreen(
    navigateTo: (Route) -> Unit
) {
    ColorScreenView(navigateTo = navigateTo)
}

@Composable
fun ColorScreenView ( viewModel: ColorViewModel = ColorViewModel(), navigateTo: (Route) -> Unit) {
    val savedHex by viewModel.color.collectAsState()

    val colorScheme = MaterialTheme.colorScheme
    val allThemeColors = getThemeColors(colorScheme)
    val themeColorPairs = getThemeColorPairs(colorScheme)

    val selectedColorPair= remember { mutableStateOf<ColorPair>(themeColorPairs[0]) }
    val availableColors = remember(selectedColorPair.value) {
        selectedColorPair.value.let { pair ->
            allThemeColors.filter {
                it.name != pair.first.name && it.name != pair.second.name
            }
        }
    }
    val selectedColor = remember { mutableStateOf<ColorInfo>(availableColors[0]) }

    Scaffold(
        topBar = {
            TopBar("Color", { navigateTo(Route.NavigationUp) })
        }

    ) { innerPadding ->
        LazyColumn (
            modifier= Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                ColorPairDropdownMenu(
                    selectedColor = selectedColorPair.value,
                    colorPairs = themeColorPairs,
                    onColorSelected = { selectedColorPair.value = it }
                )
            }
            item {
                ColorDropdownMenu(
                    selectedColor = selectedColor.value,
                    colors = availableColors,
                    onColorSelected = { selectedColor.value = it }
                )
            }
            item {
                ResultColorCard(
                    colorPair = selectedColorPair.value,
                    selectedColor = selectedColor.value,
                )
            }
            item {
                Text("Сохранненый цвет $savedHex")
            }
        }
    }

}

data class ColorInfo (
    val name: String,
    val color: Color
)
data class ColorPair(val name: String, val first: ColorInfo, val second: ColorInfo)

private fun getThemeColors(colorScheme: ColorScheme): List<ColorInfo> {
    return listOf(
        ColorInfo("primary", colorScheme.primary),
        ColorInfo("onPrimary", colorScheme.onPrimary),
        ColorInfo("primaryContainer", colorScheme.primaryContainer),
        ColorInfo("onPrimaryContainer", colorScheme.onPrimaryContainer),
        ColorInfo("secondary", colorScheme.secondary),
        ColorInfo("onSecondary", colorScheme.onSecondary),
        ColorInfo("secondaryContainer", colorScheme.secondaryContainer),
        ColorInfo("onSecondaryContainer", colorScheme.onSecondaryContainer),
        ColorInfo("tertiary", colorScheme.tertiary),
        ColorInfo("onTertiary", colorScheme.onTertiary),
        ColorInfo("tertiaryContainer", colorScheme.tertiaryContainer),
        ColorInfo("onTertiaryContainer", colorScheme.onTertiaryContainer),
        ColorInfo("error", colorScheme.error),
        ColorInfo("onError", colorScheme.onError),
        ColorInfo("errorContainer", colorScheme.errorContainer),
        ColorInfo("onErrorContainer", colorScheme.onErrorContainer),
        ColorInfo("background", colorScheme.background),
        ColorInfo("onBackground", colorScheme.onBackground),
        ColorInfo("surface", colorScheme.surface),
        ColorInfo("onSurface", colorScheme.onSurface),
        ColorInfo("surfaceVariant", colorScheme.surfaceVariant),
        ColorInfo("onSurfaceVariant", colorScheme.onSurfaceVariant),
        ColorInfo("outline", colorScheme.outline),
        ColorInfo("outlineVariant", colorScheme.outlineVariant),
        ColorInfo("scrim", colorScheme.scrim),
        ColorInfo("inverseSurface", colorScheme.inverseSurface),
        ColorInfo("inverseOnSurface", colorScheme.inverseOnSurface),
        ColorInfo("inversePrimary", colorScheme.inversePrimary),
        ColorInfo("surfaceDim", colorScheme.surfaceDim),
        ColorInfo("surfaceBright", colorScheme.surfaceBright),
        ColorInfo("surfaceContainerLowest", colorScheme.surfaceContainerLowest),
        ColorInfo("surfaceContainerLow", colorScheme.surfaceContainerLow),
        ColorInfo("surfaceContainer", colorScheme.surfaceContainer),
        ColorInfo("surfaceContainerHigh", colorScheme.surfaceContainerHigh),
        ColorInfo("surfaceContainerHighest", colorScheme.surfaceContainerHighest)

    )
}

private fun getThemeColorPairs(colorScheme: ColorScheme): List<ColorPair> {
    return listOf(
        ColorPair("Primary",
            ColorInfo("primary", colorScheme.primary),
            ColorInfo("onPrimary", colorScheme.onPrimary)
        ),
        ColorPair("Secondary",
            ColorInfo("secondary", colorScheme.secondary),
            ColorInfo("onSecondary", colorScheme.onSecondary)
        ),
        ColorPair("Tertiary",
            ColorInfo("tertiary", colorScheme.tertiary),
            ColorInfo("onTertiary", colorScheme.onTertiary)
        ),
        ColorPair("Primary Container",
            ColorInfo("primaryContainer", colorScheme.primaryContainer),
            ColorInfo("onPrimaryContainer", colorScheme.onPrimaryContainer)
        ),
        ColorPair("Secondary Container",
            ColorInfo("secondaryContainer", colorScheme.secondaryContainer),
            ColorInfo("onSecondaryContainer", colorScheme.onSecondaryContainer)
        ),
        ColorPair("Tertiary Container",
            ColorInfo("tertiaryContainer", colorScheme.tertiaryContainer),
            ColorInfo("onTertiaryContainer", colorScheme.onTertiaryContainer)
        ),
        ColorPair("Error",
            ColorInfo("error", colorScheme.error),
            ColorInfo("onError", colorScheme.onError)
        ),
        ColorPair("Error Container",
            ColorInfo("errorContainer", colorScheme.errorContainer),
            ColorInfo("onErrorContainer", colorScheme.onErrorContainer)
        ),
        ColorPair("Surface",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("onSurface", colorScheme.onSurface)
        ),
        ColorPair("Surface Variant",
            ColorInfo("surfaceVariant", colorScheme.surfaceVariant),
            ColorInfo("onSurfaceVariant", colorScheme.onSurfaceVariant)
        ),
        ColorPair("Background",
            ColorInfo("background", colorScheme.background),
            ColorInfo("onBackground", colorScheme.onBackground)
        ),
        ColorPair("Surface Dim",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceDim", colorScheme.surfaceDim)
        ),
        ColorPair("Surface Bright",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceBright", colorScheme.surfaceBright)
        ),
        ColorPair("Surface Container Lowest",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceContainerLowest", colorScheme.surfaceContainerLowest)
        ),
        ColorPair("Surface Container Low",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceContainerLow", colorScheme.surfaceContainerLow)
        ),
        ColorPair("Surface Container",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceContainer", colorScheme.surfaceContainer)
        ),
        ColorPair("Surface Container High",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceContainerHigh", colorScheme.surfaceContainerHigh)
        ),
        ColorPair("Surface Container Highest",
            ColorInfo("surface", colorScheme.surface),
            ColorInfo("surfaceContainerHighest", colorScheme.surfaceContainerHighest)
        ),
    )
}
