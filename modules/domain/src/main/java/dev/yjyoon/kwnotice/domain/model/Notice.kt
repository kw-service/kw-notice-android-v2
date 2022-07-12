package dev.yjyoon.kwnotice.domain.model

import java.time.LocalDate

sealed class Notice {
    abstract val id: Long
    abstract val title: String
    abstract val url: String
    abstract val postedAt: LocalDate
    abstract val crawledAt: LocalDate

    data class KwHome(
        override val id: Long,
        override val title: String,
        override val url: String,
        override val postedAt: LocalDate,
        override val crawledAt: LocalDate,
        val tag: String,
        val department: String,
        val modifiedAt: LocalDate

    ) : Notice()

    data class SwCentral(
        override val id: Long,
        override val title: String,
        override val url: String,
        override val postedAt: LocalDate,
        override val crawledAt: LocalDate,
    ) : Notice()
}
