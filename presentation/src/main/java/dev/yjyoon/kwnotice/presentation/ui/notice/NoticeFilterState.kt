package dev.yjyoon.kwnotice.presentation.ui.notice

import dev.yjyoon.kwnotice.domain.model.Notice

data class NoticeFilterState(
    val title: String?,
    val department: String?,
    val tag: String?
) {

    fun filtering(notice: Notice): Boolean = when (notice) {
        is Notice.KwHome -> {
            (title == null || title in notice.title)
                .and(tag == null || tag == notice.tag)
                .and(department == null || department == notice.department)
        }
        is Notice.SwCentral -> {
            (title == null || title in notice.title)
        }
    }

    companion object {
        val Unspecified = NoticeFilterState(
            title = null,
            department = null,
            tag = null
        )
    }
}
