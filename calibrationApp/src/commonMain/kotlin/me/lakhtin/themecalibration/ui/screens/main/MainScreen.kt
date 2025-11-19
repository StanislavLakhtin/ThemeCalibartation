package me.lakhtin.themecalibration.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.screens.main.components.GoToCard
import org.jetbrains.compose.resources.painterResource
import themecalibration.calibrationapp.generated.resources.Res
import themecalibration.calibrationapp.generated.resources.app_header


@Composable
fun MainScreen(
    navigateTo: (Route) -> Unit
) {
    MainScreenView(
        navigateTo = navigateTo
    )
}

@Composable
fun MainScreenView(navigateTo: (Route) -> Unit) {
    // var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Image(
            painter = painterResource(Res.drawable.app_header),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            GoToCard("Шрифты") {navigateTo(Route.TypographyScreenRoute)}
        }
        item {
            GoToCard("Цвета") {navigateTo(Route.ColorScreenRoute)}
        }
        item {
            GoToCard("Палитра") {navigateTo(Route.ColorPickerScreenRoute)}
        }
//        ActionButton(
//            modifier = Modifier.fillMaxWidth()
//                .padding(16.dp)
//                .height(48.dp),
//            onClick = { showContent = !showContent },
//            colors = gradientButtonColors()
//        ) {
//            Text("Click me!", style = MaterialTheme.typography.titleLarge)
//        }
//        AnimatedVisibility(showContent) {
//            val greeting = remember { Greeting().greet() }
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Image(painterResource(Res.drawable.compose_multiplatform), null)
//                Text("Compose: $greeting")
//            }
//        }
    }
        }
}

