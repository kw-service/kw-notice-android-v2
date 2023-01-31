package dev.yjyoon.kwnotice.data.repository

import dev.yjyoon.kwnotice.data.remote.api.NoticeService
import dev.yjyoon.kwnotice.data.remote.model.toDomain
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.repository.NoticeRepository
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named

internal class NoticeRepositoryImpl @Inject constructor(
    private val noticeService: NoticeService,
    @Named("KwDormNoticeUrl") private val kwDormNoticeUrl: String
) : NoticeRepository {

    override suspend fun getKwHomeNotices(): Result<List<Notice.KwHome>> = runCatching {
        noticeService.getKwHomeNotices().map { it.toDomain() }
    }

    override suspend fun getSwCentralNotices(): Result<List<Notice.SwCentral>> = runCatching {
        noticeService.getSwCentralNotices().map { it.toDomain() }
    }

    override suspend fun getKwDormNotices(): Result<List<Notice.KwDorm>> = runCatching {
        noticeService.getKwDormNotices(kwDormNoticeUrl)
            .filter {
                LocalDate.parse(it.postedDate)
                    .plusMonths(4)
                    .isAfter(LocalDate.now())
            }
            .reversed()
            .map { it.toDomain() }
    }
}
