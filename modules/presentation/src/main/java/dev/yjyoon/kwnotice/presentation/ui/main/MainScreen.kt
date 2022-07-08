package dev.yjyoon.kwnotice.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.yjyoon.kwnotice.presentation.ui.favorite.FavoriteScreen
import dev.yjyoon.kwnotice.presentation.ui.notice.NoticeScreen
import dev.yjyoon.kwnotice.presentation.ui.settings.SettingsScreen
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val navigation = rememberMainNavigation(navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            MainNavigationBar(
                currentDestination = currentDestination,
                onNavigate = { navigation.navigateTo(it) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainDestination.Notice.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MainDestination.Notice.route) { NoticeScreen() }
            composable(MainDestination.Favorite.route) { FavoriteScreen() }
            composable(MainDestination.Settings.route) { SettingsScreen(viewModel = hiltViewModel()) }
        }
    }
}

@Composable
fun MainNavigationBar(
    currentDestination: NavDestination?,
    onNavigate: (MainDestination) -> Unit
) {
    NavigationBar(
        tonalElevation = 1.dp
    ) {
        MainDestination.values().forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected) destination.iconFilledResId else destination.iconOutlinedResId
                        ),
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.labelResId),
                        style = TextStyle(fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
                    )
                },
                selected = selected,
                onClick = { onNavigate(destination) }
            )
        }
    }
}

@Preview
@Composable
private fun MainNavigationBarPreview() {
    KwNoticeTheme {
        MainNavigationBar(currentDestination = null, onNavigate = {})
    }
}

