package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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