package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite

@Composable
fun FavoriteContent(
    favorites: List<Favorite>,
    filterState: FavoriteFilterState,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top)
    ) {
        items(favorites.filter { filterState.filtering(it) }) {
            FavoriteCard(
                favorite = it,
                onClickFavorite = onClickFavorite,
                onUnbookmark = onUnbookmark
            )
        }
    }
}