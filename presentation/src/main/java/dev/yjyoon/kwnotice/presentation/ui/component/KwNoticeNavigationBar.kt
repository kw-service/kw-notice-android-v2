package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.yjyoon.kwnotice.presentation.ui.main.MainDestination
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun KwNoticeNavigationBar(
    currentDestination: NavDestination?,
    onNavigate: (MainDestination) -> Unit
) {
    NavigationBar(
        tonalElevation = 0.dp
    ) {
        MainDestination.entries.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected) destination.iconFilledResId else destination.iconOutlinedResId
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.labelResId),
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    )
                },
                selected = selected,
                onClick = { onNavigate(destination) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun KwNoticeNavigationBarPreview() {
    KwNoticeTheme {
        KwNoticeNavigationBar(currentDestination = null, onNavigate = {})
    }
}
