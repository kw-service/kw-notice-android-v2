package dev.yjyoon.kwnotice.data.remote.api

import dev.yjyoon.kwnotice.data.remote.model.KwDormNoticeResponse
import dev.yjyoon.kwnotice.data.remote.model.KwHomeNoticeResponse
import dev.yjyoon.kwnotice.data.remote.model.SwCentralNoticeResponse
import retrofit2.http.GET
import retrofit2.http.Url

internal interface NoticeService {

    @GET("kw-home")
    suspend fun getKwHomeNotices(): List<KwHomeNoticeResponse>

    @GET("sw-central")
    suspend fun getSwCentralNotices(): List<SwCentralNoticeResponse>

    @GET
    suspend fun getKwDormNotices(@Url url: String): List<KwDormNoticeResponse>
}
