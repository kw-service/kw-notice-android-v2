package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography


@Composable
fun KwNoticeTopAppBar(
    title: @Composable() (RowScope.() -> Unit),
    actions: @Composable() (RowScope.() -> Unit)
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 0.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.End,
            content = actions
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            content = title
        )
    }
}

@Composable
fun KwNoticeSimpleTopAppBar(
    titleText: String,
    actionIcon: ImageVector,
    onActionClick: () -> Unit
) {
    KwNoticeTopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = titleText,
                style = KwNoticeTypography.titleLarge,
                maxLines = 1
            )
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(imageVector = actionIcon, contentDescription = null)
            }
        }
    )
}

@Composable
fun KwNoticeSearchTopAppBar(
    titleText: String,
    onSearch: (String) -> Unit,
    onCloseSearch: () -> Unit
) {
    var showSearchBar by remember { mutableStateOf(false) }

    KwNoticeTopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = titleText,
                style = KwNoticeTypography.titleLarge,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(16.dp))
            AnimatedContent(
                targetState = showSearchBar,
                transitionSpec = {
                    if (targetState) {
                        slideInHorizontally { it } + fadeIn() with
                                slideOutHorizontally { -it } + fadeOut()
                    } else {
                        slideInHorizontally { -it } + fadeIn() with
                                slideOutHorizontally { it } + fadeOut()
                    }.using(SizeTransform(clip = false))
                },
                label = "AnimatedSearchBar"
            ) { targetState ->
                if (targetState) {
                    KwNoticeSearchBar(
                        Modifier.fillMaxWidth(),
                        onSearch = onSearch
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    showSearchBar = !showSearchBar
                    if (showSearchBar.not()) onCloseSearch()
                }
            ) {
                Icon(
                    imageVector = if (showSearchBar) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    )
}
