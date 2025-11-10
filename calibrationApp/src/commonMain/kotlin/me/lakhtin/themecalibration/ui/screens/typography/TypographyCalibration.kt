package me.lakhtin.themecalibration.ui.screens.typography

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalibrateTypography(
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
    ) {
        val fontStyles = listOf(
            "displayLarge" to MaterialTheme.typography.displayLarge,
            "displayMedium" to MaterialTheme.typography.displayMedium,
            "displaySmall" to MaterialTheme.typography.displaySmall,
            "headlineLarge" to MaterialTheme.typography.headlineLarge,
            "headlineMedium" to MaterialTheme.typography.headlineMedium,
            "headlineSmall" to MaterialTheme.typography.headlineSmall,
            "titleLarge" to MaterialTheme.typography.titleLarge,
            "titleMedium" to MaterialTheme.typography.titleMedium,
            "titleSmall" to MaterialTheme.typography.titleSmall,
            "bodyLarge" to MaterialTheme.typography.bodyLarge,
            "bodyMedium" to MaterialTheme.typography.bodyMedium,
            "bodySmall" to MaterialTheme.typography.bodySmall,
            "labelLarge" to MaterialTheme.typography.labelLarge,
            "labelMedium" to MaterialTheme.typography.labelMedium,
            "labelSmall" to MaterialTheme.typography.labelSmall,
        )

        fontStyles.forEach { (name, style) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = style,
                    modifier = Modifier.weight(1f),
                    color = contentColor
                )
                Text(
                    text = "${style.fontSize.value.toInt()}sp / ${style.lineHeight.value.toInt()}sp",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}
