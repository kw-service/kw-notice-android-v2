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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
            favoriteNotices = favoriteNotices
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, NoticeUiState.Loading)

    private val _filterState = MutableStateFlow(NoticeFilterState.Unspecified)
    val filterState: StateFlow<NoticeFilterState> = _filterState.asStateFlow()

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
}
