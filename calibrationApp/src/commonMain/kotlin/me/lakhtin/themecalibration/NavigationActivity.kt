package me.lakhtin.themecalibration

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.lakhtin.themecalibration.ui.screens.color.ColorScreen
import me.lakhtin.themecalibration.ui.screens.colorPicker.ColorPickerScreen
import me.lakhtin.themecalibration.ui.screens.colorPicker.viewmodel.ColorViewModel
import me.lakhtin.themecalibration.ui.screens.main.MainScreen
import me.lakhtin.themecalibration.ui.screens.typography.TypographyScreen

@ExperimentalComposeUiApi
@Composable
fun NavigationActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    vm: ColorViewModel
) {
    val navigateFunction: (Route) -> Unit = { navigateTo ->
        when (navigateTo) {
            is Route.NavigationUp -> navController.navigateUp()

            else -> navController.navigate(navigateTo)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.MainScreenRoute,
        modifier = modifier
    ) {

        composable<Route.MainScreenRoute> {
            MainScreen (navigateTo = navigateFunction)
        }

        composable<Route.TypographyScreenRoute> {
            TypographyScreen (navigateTo = navigateFunction)
        }

        composable<Route.ColorScreenRoute> {
            ColorScreen (navigateTo = navigateFunction)
        }

        composable<Route.ColorPickerScreenRoute> {
            ColorPickerScreen (vm, navigateTo = navigateFunction)
        }
    }
}
