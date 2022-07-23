package dev.yjyoon.kwnotice.domain.model

import java.time.LocalDate

sealed class Notice {
    abstract val id: Long
    abstract val title: String
    abstract val url: String
    abstract val postedDate: LocalDate

    data class KwHome(
        override val id: Long,
        override val title: String,
        override val url: String,
        override val postedDate: LocalDate,
        val tag: String,
        val department: String,
        val modifiedDate: LocalDate

    ) : Notice()

    data class SwCentral(
        override val id: Long,
        override val title: String,
        override val url: String,
        override val postedDate: LocalDate,
    ) : Notice()
}

fun Notice.toFavorite() = when (this) {
    is Notice.KwHome -> Favorite(
        id = id,
        title = title,
        url = url,
        date = modifiedDate,
        type = Favorite.Type.KwHome
    )
    is Notice.SwCentral -> Favorite(
        id = id,
        title = title,
        url = url,
        date = postedDate,
        type = Favorite.Type.SwCentral
    )
}
