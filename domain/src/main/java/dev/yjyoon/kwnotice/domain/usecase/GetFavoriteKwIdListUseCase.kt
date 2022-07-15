package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteKwIdListUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(): Result<List<Long>> =
        favoriteRepository.getFavoriteKwHomeIds()
}
