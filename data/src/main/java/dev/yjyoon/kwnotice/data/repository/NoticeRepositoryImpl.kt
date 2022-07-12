package dev.yjyoon.kwnotice.data.repository

import dev.yjyoon.kwnotice.data.remote.api.NoticeService
import dev.yjyoon.kwnotice.data.remote.model.toDomain
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.repository.NoticeRepository
import javax.inject.Inject

internal class NoticeRepositoryImpl @Inject constructor(
    private val noticeService: NoticeService
) : NoticeRepository {

    override suspend fun getKwHomeNotices(): Result<List<Notice.KwHome>> = runCatching {
        noticeService.getKwHomeNotices().map { it.toDomain() }
    }

    override suspend fun getSwCentralNotices(): Result<List<Notice.SwCentral>> = runCatching {
        noticeService.getSwCentralNotices().map { it.toDomain() }
    }
}
