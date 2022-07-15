package dev.yjyoon.kwnotice.domain.repository

import dev.yjyoon.kwnotice.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun addFavorite(favorite: Favorite): Result<Unit>

    suspend fun deleteFavorite(favorite: Favorite): Result<Unit>

    suspend fun getFavoriteKwHomeIds(): Result<List<Long>>

    suspend fun getFavoriteSwCentralIds(): Result<List<Long>>

    fun getAllFavoritesStream(): Flow<List<Favorite>>
}
