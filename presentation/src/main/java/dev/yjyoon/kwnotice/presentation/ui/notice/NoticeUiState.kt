package dev.yjyoon.kwnotice.presentation.ui.notice

import dev.yjyoon.kwnotice.domain.model.Notice

data class NoticeUiState(
    val kwHomeNoticeUiState: KwHomeNoticeUiState,
    val swCentralNoticeUiState: SwCentralNoticeUiState,
) {

    companion object {
        val Loading = NoticeUiState(
            kwHomeNoticeUiState = KwHomeNoticeUiState.Loading,
            swCentralNoticeUiState = SwCentralNoticeUiState.Loading
        )
    }
}

sealed interface KwHomeNoticeUiState {
    data class Success(
        val notices: List<Notice.KwHome>,
        val favoriteIds: List<Long>
    ) : KwHomeNoticeUiState

    object Loading : KwHomeNoticeUiState
    object Failure : KwHomeNoticeUiState
}

sealed interface SwCentralNoticeUiState {
    data class Success(
        val notices: List<Notice.SwCentral>,
        val favoriteIds: List<Long>
    ) : SwCentralNoticeUiState

    object Loading : SwCentralNoticeUiState
    object Failure : SwCentralNoticeUiState
}
