package me.lakhtin.themecalibration.ui.theme

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun primaryActionButtonColors() = SimpleActionButtonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    borderColor = MaterialTheme.colorScheme.outline,
    borderWidth = 0.dp
)

@Composable
fun primaryGradientButtonColors() = GradientActionButtonColors(
    containerBrush = createGradientBrush(
        gradientType = GradientType.LINEAR,
        gradientDirection = GradientDirection.LEFT_TO_RIGHT,
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    ),
    contentColor = MaterialTheme.colorScheme.onPrimary,
    borderColor = Color.Unspecified,
    borderWidth = 0.dp,
)

@Composable
fun grayCardColors() = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.secondaryContainer,
    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
    disabledContentColor = MaterialTheme.colorScheme.outline
)