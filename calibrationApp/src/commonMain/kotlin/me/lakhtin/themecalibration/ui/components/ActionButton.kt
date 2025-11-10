package me.lakhtin.themecalibration.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Базовый переиспользуемый компонент кнопки
 *
 * @param onClick обработчик нажатия
 * @param modifier модификатор для кастомизации
 * @param shape форма кнопки (по умолчанию скруглённый прямоугольник)
 * @param enabled активна ли кнопка
 * @param interactionSource источник взаимодействий для ripple-эффекта
 * @param content содержимое кнопки (текст, иконки, градиент и т.д.)
 */
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: ActionButtonColor = SimpleActionButtonColors(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable BoxScope.() -> Unit
) {
    val (containerModifier, contentColor, borderModifier) = when (colors) {
        is SimpleActionButtonColors -> Triple(
            Modifier.background(colors.containerColor),
            colors.contentColor,
            if (colors.borderWidth > 0.dp && colors.borderColor != Color.Unspecified) {
                Modifier.border(colors.borderWidth, colors.borderColor, shape)
            } else Modifier
        )
        is GradientActionButtonColors -> Triple(
            Modifier.background(colors.containerBrush),
            colors.contentColor,
            if (colors.borderWidth > 0.dp && colors.borderColor != Color.Unspecified) {
                Modifier.border(colors.borderWidth, colors.borderColor, shape)
            } else Modifier
        )
        else -> Triple(Modifier, Color.Unspecified, Modifier)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = modifier
                .clip(shape)
                .then(containerModifier)
                .then(borderModifier)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = ripple()
                ),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}