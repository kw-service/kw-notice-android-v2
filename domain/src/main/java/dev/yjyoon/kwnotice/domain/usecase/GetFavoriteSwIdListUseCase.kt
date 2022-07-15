package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteSwIdListUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(): Result<List<Long>> =
        favoriteRepository.getFavoriteSwCentralIds()
}
