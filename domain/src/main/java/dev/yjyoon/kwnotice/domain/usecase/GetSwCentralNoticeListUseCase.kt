package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.repository.NoticeRepository
import javax.inject.Inject

class GetSwCentralNoticeListUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) {

    suspend operator fun invoke(): Result<List<Notice.SwCentral>> =
        noticeRepository.getSwCentralNotices()
}
