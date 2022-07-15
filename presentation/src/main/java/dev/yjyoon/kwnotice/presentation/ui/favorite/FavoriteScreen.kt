package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onClickNotice: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    FavoriteScreen(
        uiState = uiState,
        onClickFavorite = onClickNotice,
        onUnbookmark = viewModel::deleteFromFavorite
    )
}

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeTopAppBar(
            titleText = stringResource(id = R.string.navigation_favorite),
            actionIcon = Icons.Outlined.Search,
            onActionClick = {}
        )
        Box(
            Modifier.weight(1f)
        ) {
            when (uiState) {
                is FavoriteUiState.Success -> {
                    FavoriteContent(
                        favorites = uiState.favorites,
                        onClickFavorite = onClickFavorite,
                        onUnbookmark = onUnbookmark
                    )
                }
                FavoriteUiState.Loading -> {
                    KwNoticeLoading()
                }
                FavoriteUiState.Failure -> {

                }
            }
        }
    }
}
