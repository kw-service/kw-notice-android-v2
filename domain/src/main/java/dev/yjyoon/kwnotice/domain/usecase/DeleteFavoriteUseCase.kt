package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(favorite: Favorite): Result<Unit> =
        favoriteRepository.deleteFavorite(favorite)
}
