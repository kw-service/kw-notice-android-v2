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
fun SwCentralContent(
    uiState: SwCentralNoticeUiState,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    when (uiState) {
        is SwCentralNoticeUiState.Success -> {
            SwCentralNoticeColumn(
                uiState = uiState,
                onClickNotice = onClickNotice,
                onAddToFavorite = onAddToFavorite,
                onDeleteFromFavorite = onDeleteFromFavorite
            )
        }
        SwCentralNoticeUiState.Loading -> {
            CircularProgressIndicator()
        }
        SwCentralNoticeUiState.Failure -> {
            FailureScreen()
        }
    }
}

@Composable
fun SwCentralNoticeColumn(
    uiState: SwCentralNoticeUiState.Success,
    onClickNotice: (String) -> Unit,
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
                onClickNotice = onClickNotice,
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