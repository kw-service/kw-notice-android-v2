package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeDropdownMenu

@Composable
fun FavoriteContent(
    uiState: FavoriteUiState.Success,
    filterState: FavoriteFilterState,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit,
    onTypeFilterChange: (String?) -> Unit,
    onMonthFilterChange: (String?) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            KwNoticeDropdownMenu(
                leadingIconRes = R.drawable.ic_tag,
                initialItem = stringResource(id = R.string.filter_tag_all),
                items = uiState.types.map { it.text },
                onSelectItem = onTypeFilterChange
            )
            KwNoticeDropdownMenu(
                leadingIconRes = R.drawable.ic_calendar,
                initialItem = stringResource(id = R.string.filter_month_all),
                items = uiState.months.map { "$it${stringResource(id = R.string.month)}" },
                onSelectItem = onMonthFilterChange
            )
        }
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top)
        ) {
            items(uiState.favorites.filter { filterState.filtering(it) }) {
                FavoriteCard(
                    favorite = it,
                    onClickFavorite = onClickFavorite,
                    onUnbookmark = onUnbookmark
                )
            }
        }
    }

}