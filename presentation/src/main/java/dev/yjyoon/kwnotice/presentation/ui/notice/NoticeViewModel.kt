package dev.yjyoon.kwnotice.presentation.ui.notice

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.usecase.GetKwHomeNoticeListUseCase
import dev.yjyoon.kwnotice.domain.usecase.GetSwCentralNoticeListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getKwHomeNoticeListUseCase: GetKwHomeNoticeListUseCase,
    private val getSwCentralNoticeListUseCase: GetSwCentralNoticeListUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(NoticeUiState.Loading)
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() {
        launch {
            getKwHomeNoticeListUseCase()
                .onSuccess { notices ->
                    _uiState.update {
                        it.copy(kwHomeNoticeUiState = KwHomeNoticeUiState.Success(notices))
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(kwHomeNoticeUiState = KwHomeNoticeUiState.Failure)
                    }
                }

            getSwCentralNoticeListUseCase()
                .onSuccess { notices ->
                    _uiState.update {
                        it.copy(swCentralNoticeUiState = SwCentralNoticeUiState.Success(notices))
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(swCentralNoticeUiState = SwCentralNoticeUiState.Failure)
                    }
                }
        }
    }

    fun refresh() {
        _uiState.value = NoticeUiState.Loading
        fetch()
    }
}
