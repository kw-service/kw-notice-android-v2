package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSearchTopAppBar

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onClickNotice: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()

    FavoriteScreen(
        uiState = uiState,
        filterState = filterState,
        onSearch = viewModel::setTitleFilter,
        onClickFavorite = onClickNotice,
        onUnbookmark = viewModel::deleteFromFavorite,
        onTypeFilterChange = viewModel::setTypeFilter,
        onMonthFilterChange = viewModel::setMonthFilter
    )
}

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    filterState: FavoriteFilterState,
    onSearch: (String) -> Unit,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit,
    onTypeFilterChange: (String?) -> Unit,
    onMonthFilterChange: (String?) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeSearchTopAppBar(
            titleText = stringResource(id = R.string.navigation_favorite),
            onSearch = onSearch
        )
        Box(
            Modifier.weight(1f)
        ) {
            when (uiState) {
                is FavoriteUiState.Success -> {
                    FavoriteContent(
                        uiState = uiState,
                        filterState = filterState,
                        onClickFavorite = onClickFavorite,
                        onUnbookmark = onUnbookmark,
                        onTypeFilterChange = onTypeFilterChange,
                        onMonthFilterChange = onMonthFilterChange
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
