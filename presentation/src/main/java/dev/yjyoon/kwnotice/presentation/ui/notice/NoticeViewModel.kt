package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.model.toFavorite
import dev.yjyoon.kwnotice.domain.usecase.favorite.AddFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.DeleteFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetAllFavoriteListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetKwDormNoticeListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetKwHomeNoticeListUseCase
import dev.yjyoon.kwnotice.domain.usecase.notice.GetSwCentralNoticeListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    getAllFavoriteListUseCase: GetAllFavoriteListUseCase,
    private val getKwHomeNoticeListUseCase: GetKwHomeNoticeListUseCase,
    private val getSwCentralNoticeListUseCase: GetSwCentralNoticeListUseCase,
    private val getKwDormNoticeListUseCase: GetKwDormNoticeListUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    var uiState: StateFlow<NoticeUiState> = combine(
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
        flow {
            getKwDormNoticeListUseCase()
                .onSuccess { emit(it) }
                .onFailure { emit(null) }
        },
        getAllFavoriteListUseCase()
    ) { kwHomeNotices, swCentralNotices, kwDormNotices, favoriteNotices ->
        NoticeUiState(
            kwHomeNoticeUiState = if (kwHomeNotices != null) {
                KwHomeNoticeUiState.Success(
                    notices = kwHomeNotices,
                    tags = kwHomeNotices.map { it.tag }.distinct(),
                    departments = kwHomeNotices.map { it.department }.distinct(),
                    months = kwHomeNotices.map { it.modifiedDate.monthValue }.distinct()
                )
            } else {
                KwHomeNoticeUiState.Failure
            },
            swCentralNoticeUiState = if (swCentralNotices != null) {
                SwCentralNoticeUiState.Success(
                    notices = swCentralNotices,
                    months = swCentralNotices.map { it.postedDate.monthValue }.distinct()
                )
            } else {
                SwCentralNoticeUiState.Failure
            },
            kwDormNoticeUiState = if (kwDormNotices != null) {
                KwDormNoticeUiState.Success(
                    notices = kwDormNotices,
                    months = kwDormNotices.map { it.postedDate.monthValue }.distinct()
                )
            } else {
                KwDormNoticeUiState.Failure
            },
            favoriteNotices = favoriteNotices
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, NoticeUiState.Loading)

    private val _filterState = MutableStateFlow(NoticeFilterState.Unspecified)
    val filterState: StateFlow<NoticeFilterState> = _filterState.asStateFlow()

    var refreshing by mutableStateOf(false)

    fun addFavorite(notice: Notice) {
        launch {
            addFavoriteUseCase(notice.toFavorite()).getOrThrow()
        }
    }

    fun deleteFavorite(notice: Notice) {
        launch {
            deleteFavoriteUseCase(notice.toFavorite()).getOrThrow()
        }
    }

    fun setTitleFilter(title: String) {
        _filterState.update {
            it.copy(title = title.trim())
        }
    }

    fun setTagFilter(tag: String?) {
        _filterState.update {
            it.copy(tag = tag)
        }
    }

    fun setDepartmentFilter(department: String?) {
        _filterState.update {
            it.copy(department = department)
        }
    }

    fun setMonthFilter(month: String?) {
        _filterState.update {
            it.copy(month = month)
        }
    }

    fun initFilter() {
        _filterState.value = NoticeFilterState.Unspecified
    }

    fun refresh(tab: NoticeTab) {
        when (tab) {
            NoticeTab.KwHome -> {
                viewModelScope.launch {
                    refreshing = true
                    getKwHomeNoticeListUseCase()
                        .onSuccess { kwHomeNotices ->
                            uiState.value.kwHomeNoticeUiState = KwHomeNoticeUiState.Success(
                                notices = kwHomeNotices,
                                tags = kwHomeNotices.map { it.tag }.distinct(),
                                departments = kwHomeNotices.map { it.department }.distinct(),
                                months = kwHomeNotices.map { it.modifiedDate.monthValue }.distinct()
                            )
                        }
                        .onFailure {
                            uiState.value.kwHomeNoticeUiState = KwHomeNoticeUiState.Failure
                        }
                    delay(250L)
                    refreshing = false
                }
            }
            NoticeTab.SwCentral -> {
                viewModelScope.launch {
                    refreshing = true
                    getSwCentralNoticeListUseCase()
                        .onSuccess { swCentralNotices ->
                            uiState.value.swCentralNoticeUiState = SwCentralNoticeUiState.Success(
                                notices = swCentralNotices,
                                months = swCentralNotices
                                    .map { it.postedDate.monthValue }
                                    .distinct()
                            )
                        }
                        .onFailure {
                            uiState.value.swCentralNoticeUiState = SwCentralNoticeUiState.Failure
                        }
                    delay(250L)
                    refreshing = false
                }
            }
            NoticeTab.KwDorm -> {
                viewModelScope.launch {
                    refreshing = true
                    getKwDormNoticeListUseCase()
                        .onSuccess { kwDormNotices ->
                            uiState.value.kwDormNoticeUiState = KwDormNoticeUiState.Success(
                                notices = kwDormNotices,
                                months = kwDormNotices
                                    .map { it.postedDate.monthValue }
                                    .distinct()
                            )
                        }
                        .onFailure {
                            uiState.value.kwDormNoticeUiState = KwDormNoticeUiState.Failure
                        }
                    delay(250L)
                    refreshing = false
                }
            }
        }
    }
}
