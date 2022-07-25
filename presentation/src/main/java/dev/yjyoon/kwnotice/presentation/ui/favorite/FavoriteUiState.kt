package dev.yjyoon.kwnotice.presentation.ui.favorite

import dev.yjyoon.kwnotice.domain.model.Favorite

sealed interface FavoriteUiState {
    data class Success(
        val favorites: List<Favorite>,
        val types: List<Favorite.Type>,
        val months: List<Int>
    ) : FavoriteUiState

    object Empty : FavoriteUiState
    object Loading : FavoriteUiState
    object Failure : FavoriteUiState
}
