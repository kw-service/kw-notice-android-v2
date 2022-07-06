package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar

@Composable
fun NoticeScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeTopAppBar(
            titleText = stringResource(id = R.string.navigation_notice),
            actionIcon = Icons.Outlined.Search,
            onActionClick = {}
        )
    }
}
