package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.model.Notice
import javax.inject.Inject

class GetSwCentralNoticeListUseCase @Inject constructor() {

    suspend operator fun invoke(): Result<List<Notice.SwCentral>> = runCatching {
        emptyList()
    }
}
