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
        SwCentral(text = SW_CENTRAL)
    }

    companion object {

        fun stringToType(string: String) = when (string) {
            KW_HOME -> Type.KwHome
            else -> Type.SwCentral
        }

        const val KW_HOME = "광운대학교"
        const val SW_CENTRAL = "SW중심대학사업단"
    }
}
