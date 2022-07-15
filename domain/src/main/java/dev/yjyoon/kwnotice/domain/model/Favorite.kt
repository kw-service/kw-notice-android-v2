package dev.yjyoon.kwnotice.domain.model

import java.time.LocalDate

data class Favorite(
    val id: Long,
    val title: String,
    val type: Type,
    val date: LocalDate,
    val url: String
) {
    enum class Type { KwHome, SwCentral }
}
