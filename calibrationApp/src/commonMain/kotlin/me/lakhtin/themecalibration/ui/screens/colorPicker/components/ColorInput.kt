package me.lakhtin.themecalibration.ui.screens.colorPicker.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ColorInput(
    hexValue: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = hexValue,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            placeholder = {
                Text("#hex", color = MaterialTheme.colorScheme.onSurfaceVariant)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Tag,
                    contentDescription = "Hex color",
                    tint = if (isError) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                if (isError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Invalid color",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Characters
            )
        )
    }
}

fun parseHexColor(hex: String): Color? {
    var cleanHex = hex.trim().uppercase()

    if (!cleanHex.startsWith("#")) {
        cleanHex = "#$cleanHex"
    }

    return when {
        cleanHex.matches(Regex("#[0-9A-F]{3}")) -> {
            val r = cleanHex.substring(1, 2).repeat(2).toInt(16) / 255f
            val g = cleanHex.substring(2, 3).repeat(2).toInt(16) / 255f
            val b = cleanHex.substring(3, 4).repeat(2).toInt(16) / 255f
            Color(r, g, b)
        }

        cleanHex.matches(Regex("#[0-9A-F]{6}")) -> {
            val r = cleanHex.substring(1, 3).toInt(16) / 255f
            val g = cleanHex.substring(3, 5).toInt(16) / 255f
            val b = cleanHex.substring(5, 7).toInt(16) / 255f
            Color(r, g, b)
        }
        else -> null
    }
}

fun isValidHex(hex: String): Boolean {
    val cleanHex = hex.trim().uppercase()
    return cleanHex.matches(Regex("#?[0-9A-F]{3}")) ||
            cleanHex.matches(Regex("#?[0-9A-F]{6}"))
}