package dev.yjyoon.kwnotice.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import dev.yjyoon.kwnotice.domain.model.VersionName
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeNavigationBar
import dev.yjyoon.kwnotice.presentation.ui.favorite.FavoriteScreen
import dev.yjyoon.kwnotice.presentation.ui.notice.NoticeScreen
import dev.yjyoon.kwnotice.presentation.ui.settings.SettingsScreen
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun MainScreen(
    onClickNotice: (String) -> Unit,
    onClickOsl: () -> Unit,
    versionName: VersionName
) {
    val navController = rememberAnimatedNavController()
    val navigation = rememberMainNavigation(navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            KwNoticeNavigationBar(
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
            composable(MainDestination.Notice.route) { NoticeScreen(onClickNotice = onClickNotice) }
            composable(MainDestination.Favorite.route) { FavoriteScreen(onClickNotice = onClickNotice) }
            composable(MainDestination.Settings.route) {
                SettingsScreen(
                    viewModel = hiltViewModel(),
                    onClickOsl = onClickOsl,
                    versionName = versionName
                )
            }
        }
    }
}
