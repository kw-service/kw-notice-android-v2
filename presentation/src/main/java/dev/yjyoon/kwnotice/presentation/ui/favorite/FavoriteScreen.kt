package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSearchTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

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
        onMonthFilterChange = viewModel::setMonthFilter,
        onInitFilter = viewModel::initFilter
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
    onMonthFilterChange: (String?) -> Unit,
    onInitFilter: () -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeSearchTopAppBar(
            titleText = stringResource(id = R.string.navigation_favorite),
            onSearch = onSearch,
            onCloseSearh = onInitFilter
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
                FavoriteUiState.Empty -> {
                    FavoriteEmpty(
                        Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                    )
                }
                FavoriteUiState.Loading -> {
                    KwNoticeLoading()
                }
                FavoriteUiState.Failure -> {
                    // no-op
                }
            }
        }
    }
}

@Composable
fun FavoriteEmpty(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.favorite_empty),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    KwNoticeTheme {
        FavoriteScreen(
            uiState = FavoriteUiState.Empty,
            filterState = FavoriteFilterState.Unspecified,
            onSearch = {},
            onInitFilter = {},
            onMonthFilterChange = {},
            onTypeFilterChange = {},
            onUnbookmark = {},
            onClickFavorite = {}
        )
    }
}
