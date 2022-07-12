package dev.yjyoon.kwnotice.data.remote.model

import com.google.gson.annotations.SerializedName
import dev.yjyoon.kwnotice.domain.model.Notice
import java.time.LocalDate

data class KwHomeNoticeResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("posted_date")
    val postedDate: String,
    @SerializedName("modified_date")
    val modifiedDate: String
)

fun KwHomeNoticeResponse.toDomain() = Notice.KwHome(
    id = id,
    title = title,
    tag = tag,
    department = department,
    url = url,
    postedDate = LocalDate.parse(postedDate),
    modifiedDate = LocalDate.parse(modifiedDate)
)
