package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.model.toFavorite
import dev.yjyoon.kwnotice.domain.usecase.favorite.AddFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.DeleteFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetAllFavoriteListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetKwHomeNoticeListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetSwCentralNoticeListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    getAllFavoriteListUseCase: GetAllFavoriteListUseCase,
    private val getKwHomeNoticeListUseCase: GetKwHomeNoticeListUseCase,
    private val getSwCentralNoticeListUseCase: GetSwCentralNoticeListUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    val uiState: StateFlow<NoticeUiState> = combine(
        flow {
            getKwHomeNoticeListUseCase()
                .onSuccess { emit(it) }
                .onFailure { emit(null) }
        },
        flow {
            getSwCentralNoticeListUseCase()
                .onSuccess { emit(it) }
                .onFailure { emit(null) }
        },
        getAllFavoriteListUseCase()
    ) { kwHomeNotices, swCentralNotices, favoriteNotices ->
        NoticeUiState(
            kwHomeNoticeUiState = if (kwHomeNotices != null) {
                KwHomeNoticeUiState.Success(kwHomeNotices)
            } else {
                KwHomeNoticeUiState.Failure
            },
            swCentralNoticeUiState = if (swCentralNotices != null) {
                SwCentralNoticeUiState.Success(swCentralNotices)
            } else {
                SwCentralNoticeUiState.Failure
            },
            favoriteNotices = favoriteNotices
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, NoticeUiState.Loading)

    fun addFavorite(notice: Notice) {
        launch {
            addFavoriteUseCase(notice.toFavorite())
        }
    }

    fun deleteFavorite(notice: Notice) {
        launch {
            deleteFavoriteUseCase(notice.toFavorite())
        }

    }
}
