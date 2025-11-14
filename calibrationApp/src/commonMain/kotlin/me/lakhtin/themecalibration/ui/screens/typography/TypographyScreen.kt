package me.lakhtin.themecalibration.ui.screens.typography

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.components.TopBar


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
    Scaffold(
        topBar = {
            TopBar("Typography", { navigateTo(Route.NavigationUp) })
        }

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                CalibrateTypography()
            }
        }
    }
}