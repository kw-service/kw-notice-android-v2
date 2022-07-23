package dev.yjyoon.kwnotice.domain.usecase.notice

import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.repository.NoticeRepository
import javax.inject.Inject

class GetKwHomeNoticeListUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) {

    suspend operator fun invoke(): Result<List<Notice.KwHome>> =
        noticeRepository.getKwHomeNotices()
}
