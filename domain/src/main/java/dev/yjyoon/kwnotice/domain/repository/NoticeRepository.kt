package dev.yjyoon.kwnotice.domain.repository

import dev.yjyoon.kwnotice.domain.model.Notice

interface NoticeRepository {

    suspend fun getKwHomeNotices(): Result<List<Notice.KwHome>>

    suspend fun getSwCentralNotices(): Result<List<Notice.SwCentral>>
    suspend fun getKwDormNotices(): Result<List<Notice.KwDorm>>
}
