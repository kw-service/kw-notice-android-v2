package dev.yjyoon.kwnotice.presentation.ui.favorite

import dev.yjyoon.kwnotice.domain.model.Favorite

data class FavoriteFilterState(
    val title: String?,
    val type: Favorite.Type?,
    val month: String?
) {

    fun filtering(favorite: Favorite): Boolean =
        (title == null || title in favorite.title)
            .and(type == null || type == favorite.type)
            .and(month == null || month.dropLast(1).toInt() == favorite.date.monthValue)

    companion object {
        val Unspecified = FavoriteFilterState(
            title = null,
            type = null,
            month = null
        )
    }
}
