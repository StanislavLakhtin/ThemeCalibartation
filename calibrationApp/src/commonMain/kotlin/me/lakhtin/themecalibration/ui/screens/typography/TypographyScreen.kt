package me.lakhtin.themecalibration.ui.screens.typography

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import me.lakhtin.themecalibration.Route

@Composable
fun TypographyScreen(
    navigateTo: (Route) -> Unit
) {
    TypographyScreenView(navigateTo = navigateTo)
}

@Composable
fun TypographyScreenView(
    navigateTo: (Route) -> Unit
) {
    LazyColumn {
        item {
            CalibrateTypography()
        }
    }
}