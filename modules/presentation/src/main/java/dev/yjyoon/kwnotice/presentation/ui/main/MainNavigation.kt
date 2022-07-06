package dev.yjyoon.kwnotice.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class MainNavigation(
    val navController: NavController
) {

    fun navigateTo(destination: MainDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberMainNavigation(
    navController: NavController
) = remember(navController) {
    MainNavigation(
        navController = navController
    )
}
