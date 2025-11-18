package me.lakhtin.themecalibration.ui.screens.color.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.ui.screens.color.ColorPair

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPairDropdownMenu(
    selectedColor: ColorPair,
    colorPairs: List<ColorPair>,
    onColorSelected: (ColorPair) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
            Row (
                modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 16.dp ,vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)){
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = selectedColor.first.color,
                            shape = MaterialTheme.shapes.small
                        )
                        .border(width = 2.dp, color = MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.small)
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = selectedColor.second.color,
                            shape = MaterialTheme.shapes.small
                        )
                        .border(width = 2.dp, color = MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.small)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    text = selectedColor.name,
                    fontWeight = FontWeight.Medium
                )
            }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            colorPairs.forEach { colorPair ->
                Text(
                    text = colorPair.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = colorPair.first.color,
                                            shape = MaterialTheme.shapes.small
                                        )
                                )
                                Text(
                                    text = colorPair.first.name,
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = colorPair.second.color,
                                            shape = MaterialTheme.shapes.small
                                        )
                                )
                                Text(
                                    text = colorPair.second.name,
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    },
                    onClick = {
                        onColorSelected(colorPair)
                        expanded = false
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 2.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}