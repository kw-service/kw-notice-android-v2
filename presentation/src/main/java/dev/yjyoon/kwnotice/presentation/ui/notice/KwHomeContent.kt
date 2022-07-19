package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.model.toFavorite

@Composable
fun KwHomeContent(
    uiState: KwHomeNoticeUiState,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    when (uiState) {
        is KwHomeNoticeUiState.Success -> {
            KwHomeNoticeColumn(
                uiState = uiState,
                filterState = filterState,
                onClickNotice = onClickNotice,
                onAddToFavorite = onAddToFavorite,
                onDeleteFromFavorite = onDeleteFromFavorite,
                favoriteNotices = favoriteNotices
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
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
    ) {
        items(uiState.notices.filter { filterState.filtering(it) }) {
            NoticeCard(
                notice = it,
                onClickNotice = onClickNotice,
                bookmarked = favoriteNotices.contains(it.toFavorite()),
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
