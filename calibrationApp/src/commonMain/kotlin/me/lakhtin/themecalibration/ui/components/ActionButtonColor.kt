package me.lakhtin.themecalibration.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*

open class ActionButtonColor () {
}

@Immutable
data class SimpleActionButtonColors(
    val containerColor: Color = Color.Unspecified,
    val contentColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
) : ActionButtonColor()