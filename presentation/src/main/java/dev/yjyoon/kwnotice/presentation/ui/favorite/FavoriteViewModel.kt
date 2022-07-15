package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.usecase.favorite.DeleteFavoriteUseCase
import dev.yjyoon.kwnotice.domain.usecase.favorite.GetAllFavoriteListUseCase
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getAllFavoriteListUseCase: GetAllFavoriteListUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    val uiState = getAllFavoriteListUseCase().map { FavoriteUiState.Success(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            FavoriteUiState.Loading
        )


    fun deleteFromFavorite(favorite: Favorite) {
        launch {
            deleteFavoriteUseCase(favorite).getOrThrow()
        }
    }
}
