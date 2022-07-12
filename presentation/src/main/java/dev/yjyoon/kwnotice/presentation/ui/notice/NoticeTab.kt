package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.annotation.StringRes
import dev.yjyoon.kwnotice.presentation.R

enum class NoticeTab(
    @StringRes val textRes: Int
) {
    KwHome(textRes = R.string.kw_home),
    SwCentral(textRes = R.string.sw_central)
}
