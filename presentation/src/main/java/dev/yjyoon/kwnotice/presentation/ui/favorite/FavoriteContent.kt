package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite

@Composable
fun FavoriteContent(
    favorites: List<Favorite>,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        items(favorites) {
            FavoriteCard(
                favorite = it,
                onClickFavorite = onClickFavorite,
                onUnbookmark = onUnbookmark
            )
        }
    }
}