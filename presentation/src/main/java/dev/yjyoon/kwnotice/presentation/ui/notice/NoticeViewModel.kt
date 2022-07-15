package dev.yjyoon.kwnotice.presentation.ui.notice

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.model.toFavorite
import dev.yjyoon.kwnotice.domain.usecase.favorite.AddFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.DeleteFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetFavoriteKwIdListUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetFavoriteSwIdListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetKwHomeNoticeListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetSwCentralNoticeListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getKwHomeNoticeListUseCase: GetKwHomeNoticeListUseCase,
    private val getSwCentralNoticeListUseCase: GetSwCentralNoticeListUseCase,
    private val getFavoriteKwIdListUseCase: GetFavoriteKwIdListUseCase,
    private val getFavoriteSwIdListUseCase: GetFavoriteSwIdListUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(NoticeUiState.Loading)
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() {
        fetchKwHome()
        fetchSwCentral()
    }

    private fun fetchKwHome() {
        launch {
            getKwHomeNoticeListUseCase()
                .onSuccess { notices ->
                    val favoriteIds = getFavoriteKwIdListUseCase().getOrThrow()
                    _uiState.update {
                        it.copy(
                            kwHomeNoticeUiState = KwHomeNoticeUiState.Success(
                                notices = notices,
                                favoriteIds = favoriteIds
                            )
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(kwHomeNoticeUiState = KwHomeNoticeUiState.Failure)
                    }
                    Log.e(null, throwable.stackTraceToString())
                }
        }
    }

    private fun fetchSwCentral() {
        launch {
            getSwCentralNoticeListUseCase()
                .onSuccess { notices ->
                    val favoriteIds = getFavoriteSwIdListUseCase().getOrThrow()
                    _uiState.update {
                        it.copy(
                            swCentralNoticeUiState = SwCentralNoticeUiState.Success(
                                notices = notices,
                                favoriteIds = favoriteIds
                            )
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(swCentralNoticeUiState = SwCentralNoticeUiState.Failure)
                    }
                    Log.e(null, throwable.stackTraceToString())
                }
        }
    }

    fun addFavorite(notice: Notice) {
        launch {
            addFavoriteUseCase(notice.toFavorite())
                .onSuccess { refreshFavorite(notice) }
        }
    }

    fun deleteFavorite(notice: Notice) {
        launch {
            deleteFavoriteUseCase(notice.toFavorite())
                .onSuccess { refreshFavorite(notice) }
        }

    }

    private fun refreshFavorite(notice: Notice) {
        when (notice) {
            is Notice.KwHome -> launch {
                getFavoriteKwIdListUseCase()
                    .onSuccess { ids ->
                        _uiState.update {
                            it.copy(
                                kwHomeNoticeUiState = KwHomeNoticeUiState.Success(
                                    notices = (_uiState.value.kwHomeNoticeUiState as KwHomeNoticeUiState.Success).notices,
                                    favoriteIds = ids
                                )
                            )
                        }
                    }
            }
            is Notice.SwCentral -> launch {
                getFavoriteSwIdListUseCase()
                    .onSuccess { ids ->
                        _uiState.update {
                            it.copy(
                                swCentralNoticeUiState = SwCentralNoticeUiState.Success(
                                    notices = (_uiState.value.swCentralNoticeUiState as SwCentralNoticeUiState.Success).notices,
                                    favoriteIds = ids
                                )
                            )
                        }
                    }
            }
        }
    }

    fun refresh() {
        if (isLoading().not()) {
            _uiState.value = NoticeUiState.Loading
            fetch()
        }
    }

    fun isLoading() =
        _uiState.value.kwHomeNoticeUiState == KwHomeNoticeUiState.Loading
                || _uiState.value.swCentralNoticeUiState == SwCentralNoticeUiState.Loading
}
