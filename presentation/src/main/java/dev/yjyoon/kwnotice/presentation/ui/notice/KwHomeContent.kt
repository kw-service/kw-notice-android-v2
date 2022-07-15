package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Notice

@Composable
fun KwHomeContent(
    uiState: KwHomeNoticeUiState,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    when (uiState) {
        is KwHomeNoticeUiState.Success -> {
            KwHomeNoticeColumn(
                uiState = uiState,
                onAddToFavorite = onAddToFavorite,
                onDeleteFromFavorite = onDeleteFromFavorite
            )
        }
        KwHomeNoticeUiState.Loading -> {
            CircularProgressIndicator()
        }
        KwHomeNoticeUiState.Failure -> {
            FailureScreen()
        }
    }
}

@Composable
fun KwHomeNoticeColumn(
    uiState: KwHomeNoticeUiState.Success,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        items(uiState.notices) {
            NoticeCard(
                notice = it,
                bookmarked = uiState.favoriteIds.contains(it.id),
                onToggleBookmark = { notice, bookmarked ->
                    if (bookmarked) {
                        onAddToFavorite(notice)
                    } else {
                        onDeleteFromFavorite(notice)
                    }
                })
        }
    }
}
