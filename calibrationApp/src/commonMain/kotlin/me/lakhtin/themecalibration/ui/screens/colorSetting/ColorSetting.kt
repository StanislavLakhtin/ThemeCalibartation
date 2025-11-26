package me.lakhtin.themecalibration.ui.screens.colorSetting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor


@Composable
fun ColorSettingScreen(
    viewModel: ColorViewModel = ColorViewModel(), navigateTo: (Route) -> Unit
) {
    ColorSettingScreenView(viewModel = viewModel, navigateTo = navigateTo)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSettingScreenView(
    viewModel: ColorViewModel = ColorViewModel(),
    navigateTo: (Route) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Настройки цвета") },
                actions = {
                    IconButton(onClick = {
                        ColorKey.entries.forEach { viewModel.resetColor(it) }
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Сбросить все")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ColorKey.entries.toTypedArray()) { colorKey ->
                val currentColor = hexToColor(viewModel.getColor(colorKey, colorScheme))
                val defaultColor = getDefaultColor(colorKey, colorScheme)

                ColorListItem(
                    colorKey = colorKey,
                    currentColor = currentColor,
                    defaultColor = defaultColor,
                    // onColorClick = { onColorClick(colorKey) },
                    onResetClick = { viewModel.resetColor(colorKey) },
                    isModified = currentColor != defaultColor
                )
            }
        }
    }
}

@Composable
fun ColorListItem(
    colorKey: ColorKey,
    currentColor: Color,
    defaultColor: Color,
    // onColorClick: () -> Unit,
    onResetClick: () -> Unit,
    isModified: Boolean
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = colorKey.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = colorToHex(currentColor),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                ColorBox(
                    color = currentColor,
                    // onClick = onColorClick
                )

                Spacer(modifier = Modifier.width(8.dp))

                if (isModified) {
                    TextButton(onClick = onResetClick) {
                        Text("Сбросить")
                    }
                }
            }
        }
    }
}

@Composable
fun ColorBox(
    color: Color,
   //  onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(40.dp)
            .clip(MaterialTheme.shapes.medium),
        color = color,
        // onClick = onClick,
        border = ButtonDefaults.outlinedButtonBorder
    ) {}
}

private fun getDefaultColor(
    key: ColorKey,
    colorScheme: androidx.compose.material3.ColorScheme
): Color {
    return when (key) {
        ColorKey.PRIMARY -> colorScheme.primary
        ColorKey.ON_PRIMARY -> colorScheme.onPrimary
        ColorKey.PRIMARY_CONTAINER -> colorScheme.primaryContainer
        ColorKey.ON_PRIMARY_CONTAINER -> colorScheme.onPrimaryContainer
        ColorKey.SECONDARY -> colorScheme.secondary
        ColorKey.ON_SECONDARY -> colorScheme.onSecondary
        ColorKey.SECONDARY_CONTAINER -> colorScheme.secondaryContainer
        ColorKey.ON_SECONDARY_CONTAINER -> colorScheme.onSecondaryContainer
        ColorKey.TERTIARY -> colorScheme.tertiary
        ColorKey.ON_TERTIARY -> colorScheme.onTertiary
        ColorKey.TERTIARY_CONTAINER -> colorScheme.tertiaryContainer
        ColorKey.ON_TERTIARY_CONTAINER -> colorScheme.onTertiaryContainer
        ColorKey.ERROR -> colorScheme.error
        ColorKey.ON_ERROR -> colorScheme.onError
        ColorKey.ERROR_CONTAINER -> colorScheme.errorContainer
        ColorKey.ON_ERROR_CONTAINER -> colorScheme.onErrorContainer
        ColorKey.BACKGROUND -> colorScheme.background
        ColorKey.ON_BACKGROUND -> colorScheme.onBackground
        ColorKey.SURFACE -> colorScheme.surface
        ColorKey.ON_SURFACE -> colorScheme.onSurface
        ColorKey.SURFACE_VARIANT -> colorScheme.surfaceVariant
        ColorKey.ON_SURFACE_VARIANT -> colorScheme.onSurfaceVariant
        ColorKey.OUTLINE -> colorScheme.outline
        ColorKey.OUTLINE_VARIANT -> colorScheme.outlineVariant
        ColorKey.SCRIM -> colorScheme.scrim
        ColorKey.INVERSE_SURFACE -> colorScheme.inverseSurface
        ColorKey.INVERSE_ON_SURFACE -> colorScheme.inverseOnSurface
        ColorKey.INVERSE_PRIMARY -> colorScheme.inversePrimary
        ColorKey.SURFACE_DIM -> colorScheme.surfaceDim
        ColorKey.SURFACE_BRIGHT -> colorScheme.surfaceBright
        ColorKey.SURFACE_CONTAINER_LOWEST -> colorScheme.surfaceContainerLowest
        ColorKey.SURFACE_CONTAINER_LOW -> colorScheme.surfaceContainerLow
        ColorKey.SURFACE_CONTAINER -> colorScheme.surfaceContainer
        ColorKey.SURFACE_CONTAINER_HIGH -> colorScheme.surfaceContainerHigh
        ColorKey.SURFACE_CONTAINER_HIGHEST -> colorScheme.surfaceContainerHighest
    }
}