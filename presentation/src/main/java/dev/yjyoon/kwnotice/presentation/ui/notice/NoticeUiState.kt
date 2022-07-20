package dev.yjyoon.kwnotice.presentation.ui.notice

import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.model.Notice

data class NoticeUiState(
    val kwHomeNoticeUiState: KwHomeNoticeUiState,
    val swCentralNoticeUiState: SwCentralNoticeUiState,
    val favoriteNotices: List<Favorite>
) {

    companion object {
        val Loading = NoticeUiState(
            kwHomeNoticeUiState = KwHomeNoticeUiState.Loading,
            swCentralNoticeUiState = SwCentralNoticeUiState.Loading,
            favoriteNotices = emptyList()
        )
    }
}

sealed interface KwHomeNoticeUiState {
    data class Success(
        val notices: List<Notice.KwHome>,
        val tags: List<String>,
        val departments: List<String>,
        val months: List<Int>
    ) : KwHomeNoticeUiState

    object Loading : KwHomeNoticeUiState
    object Failure : KwHomeNoticeUiState
}

sealed interface SwCentralNoticeUiState {
    data class Success(
        val notices: List<Notice.SwCentral>,
        val months: List<Int>
    ) : SwCentralNoticeUiState

    object Loading : SwCentralNoticeUiState
    object Failure : SwCentralNoticeUiState
}
