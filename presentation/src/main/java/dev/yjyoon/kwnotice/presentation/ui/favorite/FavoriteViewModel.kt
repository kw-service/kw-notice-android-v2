package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.usecase.favorite.DeleteFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetAllFavoriteListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getAllFavoriteListUseCase: GetAllFavoriteListUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    val uiState = getAllFavoriteListUseCase().map {
        FavoriteUiState.Success(
            favorites = it,
            types = it.map { favorite -> favorite.type }.distinct(),
            months = it.map { favorite -> favorite.date.monthValue }.distinct()
        )
    }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            FavoriteUiState.Loading
        )

    private val _filterState = MutableStateFlow(FavoriteFilterState.Unspecified)
    val filterState: StateFlow<FavoriteFilterState> = _filterState.asStateFlow()


    fun deleteFromFavorite(favorite: Favorite) {
        launch {
            deleteFavoriteUseCase(favorite).getOrThrow()
        }
    }

    fun setTitleFilter(title: String) {
        _filterState.update {
            it.copy(title = title.trim())
        }
    }

    fun setTypeFilter(type: String?) {
        _filterState.update {
            it.copy(type = type?.let { type -> Favorite.Companion.stringToType(type) })
        }
    }

    fun setMonthFilter(month: String?) {
        _filterState.update {
            it.copy(month = month)
        }
    }
}
