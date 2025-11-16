package me.lakhtin.themecalibration.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.lakhtin.themecalibration.Greeting
import me.lakhtin.themecalibration.Route
import me.lakhtin.themecalibration.ui.theme.ActionButton
import me.lakhtin.themecalibration.ui.theme.gradientButtonColors
import me.lakhtin.themecalibration.ui.theme.primaryButtonColors
import org.jetbrains.compose.resources.painterResource
import themecalibration.calibrationapp.generated.resources.Res
import themecalibration.calibrationapp.generated.resources.compose_multiplatform

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
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ActionButton(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            onClick = { showContent = !showContent },
            colors = gradientButtonColors()
        ) {
            Text("Click me!", style = MaterialTheme.typography.titleLarge)
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
        ActionButton(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            onClick = { navigateTo(Route.TypographyScreenRoute) },
            colors = primaryButtonColors()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Typography screen",
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
                style = MaterialTheme.typography.displaySmall
            )
        }
        ActionButton(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            onClick = { navigateTo(Route.ColorScreenRoute) },
            colors = primaryButtonColors()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Color screen",
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
                style = MaterialTheme.typography.displaySmall
            )
        }
        ActionButton(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            onClick = { navigateTo(Route.ColorPickerScreenRoute) },
            colors = primaryButtonColors()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Color picker screen",
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

