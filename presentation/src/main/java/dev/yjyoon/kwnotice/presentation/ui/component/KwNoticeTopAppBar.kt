package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography

@Composable
fun KwNoticeTopAppBar(
    titleText: String,
    actionIcon: ImageVector,
    onActionClick: () -> Unit
) {
    SmallTopAppBar(
        title = { Text(text = titleText, style = KwNoticeTypography.titleLarge) },
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
    onSearch: (String) -> Unit
) {
    var showSearchBar by remember { mutableStateOf(false) }

    SmallTopAppBar(
        title = {
            Text(
                text = titleText,
                style = KwNoticeTypography.titleLarge,
                maxLines = 1
            )
        },
        actions = {
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
                }
            ) { targetState ->
                if (targetState) {
                    KwNoticeSearchBar(
                        Modifier.fillMaxWidth(),
                        onSearch = onSearch,
                        onClose = {
                            showSearchBar = false
                        }
                    )
                } else {
                    IconButton(onClick = {
                        showSearchBar = true
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun KwNoticeTopAppBarPreview() {
    KwNoticeTheme {
        KwNoticeTopAppBar(
            titleText = stringResource(id = R.string.navigation_notice),
            actionIcon = Icons.Outlined.Search,
            onActionClick = {}
        )
    }
}