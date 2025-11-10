package me.lakhtin.themecalibration.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.*

@Immutable
data class GradientActionButtonColors(
    val containerBrush: Brush,
    val contentColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
) : ActionButtonColor()

enum class GradientType {
    LINEAR,
    RADIAL,
    SWEEP
}

enum class GradientDirection {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT,
    TOP_TO_BOTTOM,
    BOTTOM_TO_TOP,
    TOP_LEFT_TO_BOTTOM_RIGHT,
    TOP_RIGHT_TO_BOTTOM_LEFT
}

@Composable
fun createGradientBrush(
    gradientType: GradientType,
    gradientDirection: GradientDirection,
    colors: List<Color>,
    tileMode: TileMode = TileMode.Clamp
): Brush {
    return when (gradientType) {
        GradientType.LINEAR -> {
            val (start, end) = getLinearGradientOffsets(gradientDirection)
            Brush.linearGradient(
                colors = colors,
                start = start,
                end = end,
                tileMode = tileMode
            )
        }
        GradientType.RADIAL -> {
            Brush.radialGradient(
                colors = colors,
                tileMode = tileMode
            )
        }
        GradientType.SWEEP -> {
            Brush.sweepGradient(
                colors = colors
            )
        }
    }
}

fun getLinearGradientOffsets(direction: GradientDirection): Pair<Offset, Offset> {
    return when (direction) {
        GradientDirection.LEFT_TO_RIGHT -> Offset(0f, 0f) to Offset(Float.POSITIVE_INFINITY, 0f)
        GradientDirection.RIGHT_TO_LEFT -> Offset(Float.POSITIVE_INFINITY, 0f) to Offset(0f, 0f)
        GradientDirection.TOP_TO_BOTTOM -> Offset(0f, 0f) to Offset(0f, Float.POSITIVE_INFINITY)
        GradientDirection.BOTTOM_TO_TOP -> Offset(0f, Float.POSITIVE_INFINITY) to Offset(0f, 0f)
        GradientDirection.TOP_LEFT_TO_BOTTOM_RIGHT -> Offset(0f, 0f) to Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        GradientDirection.TOP_RIGHT_TO_BOTTOM_LEFT -> Offset(Float.POSITIVE_INFINITY, 0f) to Offset(0f, Float.POSITIVE_INFINITY)
    }
}
