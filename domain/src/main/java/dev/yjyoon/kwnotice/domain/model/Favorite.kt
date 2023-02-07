package dev.yjyoon.kwnotice.domain.model

import java.time.LocalDate

data class Favorite(
    val id: Long,
    val title: String,
    val type: Type,
    val date: LocalDate,
    val url: String
) {
    enum class Type(
        val text: String
    ) {
        KwHome(text = KW_HOME),
        SwCentral(text = SW_CENTRAL),
        KwDorm(text = KW_DORM),
        Unknown(text = UNKNOWN)
    }
}

fun String.toFavoriteType(): Favorite.Type = when (this) {
    KW_HOME -> Favorite.Type.KwHome
    SW_CENTRAL -> Favorite.Type.SwCentral
    KW_DORM -> Favorite.Type.KwDorm
    else -> Favorite.Type.Unknown
}

const val KW_HOME = "광운대학교"
const val SW_CENTRAL = "SW중심대학사업단"
const val KW_DORM = "빛솔재(기숙사)"
const val UNKNOWN = "UNKNOWN"
