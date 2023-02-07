package dev.yjyoon.kwnotice.data.remote.model

import com.google.gson.annotations.SerializedName
import dev.yjyoon.kwnotice.domain.model.Notice
import java.time.LocalDate

data class KwDormNoticeResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("createdAt")
    val postedDate: String,
)

fun KwDormNoticeResponse.toDomain() = Notice.KwDorm(
    id = id,
    title = title,
    url = url,
    postedDate = LocalDate.parse(postedDate)
)
