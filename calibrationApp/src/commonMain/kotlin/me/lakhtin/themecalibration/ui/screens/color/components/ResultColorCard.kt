package me.lakhtin.themecalibration.ui.screens.color.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.ui.screens.color.ColorInfo
import me.lakhtin.themecalibration.ui.screens.color.ColorPair

@Composable
fun ResultColorCard(
    colorPair: ColorPair,
    selectedColor: ColorInfo,
    modifier: Modifier = Modifier
) {
    val backgroundColor = colorPair.first.color
    val textColor = colorPair.second.color
    val iconColor = selectedColor.color

    Card(
        modifier = modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Icon(
                modifier = Modifier
                .size(44.dp)
                .align(Alignment.TopEnd),
                tint = iconColor,
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings icon color check"

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Заголовок",
                    style = MaterialTheme.typography.displayMedium,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Подзаголовок",
                    style = MaterialTheme.typography.headlineMedium,
                    color = textColor,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Этот пример демонстрирует, как выбранная цветовая пара создает визуальное восприятие." +
                            "Фон карточки использует основной цвет, в то время как текст контрастно выделяется вторым цветом пары. " +
                            "Круг в углу регулируется второй палитрой.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor,
                )
            }
        }
    }
}