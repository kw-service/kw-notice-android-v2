package dev.yjyoon.kwnotice.data.remote.model

import com.google.gson.annotations.SerializedName
import dev.yjyoon.kwnotice.domain.model.Notice
import java.time.LocalDate

data class SwCentralNoticeResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("posted_date")
    val postedDate: String,
)

fun SwCentralNoticeResponse.toDomain() = Notice.SwCentral(
    id = id,
    title = title,
    url = url,
    postedDate = LocalDate.parse(postedDate)
)
