package dev.yjyoon.kwnotice.data.repository

import dev.yjyoon.kwnotice.data.local.dao.FavoriteDao
import dev.yjyoon.kwnotice.data.local.entity.toData
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.repository.FavoriteRepository
import javax.inject.Inject

internal class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override suspend fun addFavorite(favorite: Favorite): Result<Unit> = runCatching {
        favoriteDao.add(favorite = favorite.toData())
    }

    override suspend fun deleteFavorite(favorite: Favorite): Result<Unit> = runCatching {
        favoriteDao.delete(favorite = favorite.toData())
    }

    override suspend fun getFavoriteKwHomeIds(): Result<List<Long>> = runCatching {
        favoriteDao.getKwHomeIds()
    }

    override suspend fun getFavoriteSwCentralIds(): Result<List<Long>> = runCatching {
        favoriteDao.getSwCentralIds()
    }
}
