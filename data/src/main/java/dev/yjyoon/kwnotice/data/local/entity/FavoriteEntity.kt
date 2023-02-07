package dev.yjyoon.kwnotice.data.local.entity

import androidx.room.Entity
import dev.yjyoon.kwnotice.domain.model.Favorite
import java.time.LocalDate

@Entity(
    tableName = "favorite",
    primaryKeys = ["id", "type"]
)
data class FavoriteEntity(
    val id: Long,
    val title: String,
    val type: Type,
    val date: LocalDate,
    val url: String
) {
    enum class Type { KwHome, SwCentral, KwDorm, Unknown }
}

fun Favorite.toData() = FavoriteEntity(
    id = id,
    title = title,
    type = when (type) {
        Favorite.Type.KwHome -> FavoriteEntity.Type.KwHome
        Favorite.Type.SwCentral -> FavoriteEntity.Type.SwCentral
        Favorite.Type.KwDorm -> FavoriteEntity.Type.KwDorm
        else -> FavoriteEntity.Type.Unknown
    },
    date = date,
    url = url
)

fun FavoriteEntity.toDomain() = Favorite(
    id = id,
    title = title,
    type = when (type) {
        FavoriteEntity.Type.KwHome -> Favorite.Type.KwHome
        FavoriteEntity.Type.SwCentral -> Favorite.Type.SwCentral
        FavoriteEntity.Type.KwDorm -> Favorite.Type.KwDorm
        else -> Favorite.Type.Unknown
    },
    date = date,
    url = url
)
