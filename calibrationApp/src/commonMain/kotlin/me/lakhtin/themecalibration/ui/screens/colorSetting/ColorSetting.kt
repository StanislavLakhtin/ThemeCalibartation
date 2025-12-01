package me.lakhtin.themecalibration.ui.screens.colorSetting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.data.repository.ColorKey
import me.lakhtin.themecalibration.ui.components.TopBar
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.colorToHex
import me.lakhtin.themecalibration.ui.screens.colorPicker.utils.hexToColor

@Composable
fun ColorSettingScreen(
    viewModel: ColorViewModel,
    navigateTo: (Route) -> Unit
) {
    ColorSettingScreenView(viewModel = viewModel, navigateTo = navigateTo)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSettingScreenView(
    viewModel: ColorViewModel,
    navigateTo: (Route) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val themeColors by viewModel.themeColors.collectAsState()

    Scaffold(
        topBar = {
            TopBar("Настройки") { navigateTo(Route.NavigationUp) }
        }
    ) { paddingValues ->
        if (themeColors.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(ColorKey.entries.toTypedArray()) { colorKey ->
                    val colorHex = themeColors[colorKey] ?: return@items
                    val currentColor = hexToColor(colorHex)

                    val isModified = viewModel.isColorModified(colorKey, colorScheme, colorHex)

                    ColorListItem(
                        colorKey = colorKey,
                        currentColor = currentColor,
                        onColorClick = {
                            viewModel.setSelectedColorKey(colorKey, colorScheme)
                            navigateTo(Route.ColorPickerScreenRoute)
                        },
                        onResetClick = { viewModel.resetColor(colorKey, colorScheme) },
                        isModified = isModified
                    )
                }
            }
        }
    }
}


@Composable
fun ColorListItem(
    colorKey: ColorKey,
    currentColor: Color,
    onColorClick: () -> Unit,
    onResetClick: () -> Unit,
    isModified: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
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
                if (isModified) {
                    TextButton(onClick = onResetClick) {
                        Text("Сбросить")
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                ColorBox(
                    color = currentColor,
                    onClick = onColorClick
                )
            }
        }
    }
}

@Composable
fun ColorBox(
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(40.dp)
            .clip(MaterialTheme.shapes.medium),
        color = color,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        border = ButtonDefaults.outlinedButtonBorder
    ) {}
}