package dev.yjyoon.kwnotice.presentation.ui.notice

import dev.yjyoon.kwnotice.domain.model.Notice

data class NoticeUiState(
    val kwHomeNoticeUiState: KwHomeNoticeUiState,
    val swCentralNoticeUiState: SwCentralNoticeUiState
) {

    companion object {
        val Loading = NoticeUiState(
            kwHomeNoticeUiState = KwHomeNoticeUiState.Loading,
            swCentralNoticeUiState = SwCentralNoticeUiState.Loading
        )
    }
}

sealed interface KwHomeNoticeUiState {
    data class Success(val notices: List<Notice.KwHome>) : KwHomeNoticeUiState
    object Loading : KwHomeNoticeUiState
    object Failure : KwHomeNoticeUiState
}

sealed interface SwCentralNoticeUiState {
    data class Success(val notices: List<Notice.SwCentral>) : SwCentralNoticeUiState
    object Loading : SwCentralNoticeUiState
    object Failure : SwCentralNoticeUiState
}


