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
    uiState: KwHomeNoticeUiState
) {
    when (uiState) {
        is KwHomeNoticeUiState.Success -> {
            KwHomeNoticeColumn(uiState.notices)
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
fun KwHomeNoticeColumn(notices: List<Notice.KwHome>) {
    LazyColumn(
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        items(notices) {
            NoticeCard(notice = it, bookmarked = false, onToggleBookmark = { _, _ -> })
        }
    }
}
