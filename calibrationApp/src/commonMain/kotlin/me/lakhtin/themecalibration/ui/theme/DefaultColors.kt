package me.lakhtin.themecalibration.ui.theme

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun actionButtonColors(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 0.dp
) = SimpleActionButtonColors(
    containerColor = containerColor,
    contentColor = contentColor,
    borderColor = borderColor,
    borderWidth = borderWidth
)

@Composable
fun primaryButtonColors(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 0.dp
) = actionButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        borderColor = borderColor,
        borderWidth = borderWidth
    )

@Composable
fun secondaryButtonColors() = actionButtonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        borderColor = Color.Unspecified,
        borderWidth = 0.dp
    )

@Composable
fun errorButtonColors() = actionButtonColors(
    containerColor = MaterialTheme.colorScheme.error,
    contentColor = MaterialTheme.colorScheme.onError,
    borderColor = Color.Unspecified,
    borderWidth = 0.dp
)

@Composable
fun gradientButtonColors(
    containerBrush: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 0.dp
) = GradientActionButtonColors(
    containerBrush = createGradientBrush(
        gradientType = GradientType.LINEAR,
        gradientDirection = GradientDirection.LEFT_TO_RIGHT,
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    ),
    contentColor = contentColor,
    borderColor = borderColor,
    borderWidth = borderWidth,
)