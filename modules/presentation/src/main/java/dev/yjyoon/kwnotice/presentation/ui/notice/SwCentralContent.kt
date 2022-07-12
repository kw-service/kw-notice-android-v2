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
    uiState: SwCentralNoticeUiState
) {
    when (uiState) {
        is SwCentralNoticeUiState.Success -> {
            SwCentralNoticeColumn(uiState.notices)
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
fun SwCentralNoticeColumn(notices: List<Notice.SwCentral>) {
    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(notices) {
            NoticeCard(notice = it, bookmarked = false, onToggleBookmark = { _, _ -> })
        }
    }
}