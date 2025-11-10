package me.lakhtin.themecalibration.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.lakhtin.themecalibration.Greeting
import me.lakhtin.themecalibration.Route
import org.jetbrains.compose.resources.painterResource
import themecalibration.calibrationapp.generated.resources.Res
import themecalibration.calibrationapp.generated.resources.compose_multiplatform

@Composable
fun MainScreen (
    navigateTo: (Route) -> Unit
) {
    MainScreenView(
        navigateTo = navigateTo
    )
}

@Composable
fun MainScreenView(navigateTo: (Route) -> Unit) {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
        Button(onClick = { navigateTo(Route.TypographyScreenRoute) }) {
            Text("Typography screen")
        }
    }
}

