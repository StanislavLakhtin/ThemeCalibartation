@file:OptIn(ExperimentalComposeUiApi::class)

package me.lakhtin.themecalibration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import me.lakhtin.themecalibration.ui.theme.CalibrationAppTheme
val LocalAppLocalization = compositionLocalOf {
    AppLocale.English
}

@Composable
fun App() {
    CalibrationAppTheme {
        val currentLanguage = AppLocale.English
        val vm: ColorViewModel = viewModel()

        CompositionLocalProvider(LocalAppLocalization provides currentLanguage) {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                NavigationActivity(
                    vm = vm,
                    navController = rememberNavController()
                )
            }
        }
    }
}