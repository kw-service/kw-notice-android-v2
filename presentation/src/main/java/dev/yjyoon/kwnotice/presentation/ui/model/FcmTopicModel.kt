package dev.yjyoon.kwnotice.presentation.ui.model

import androidx.annotation.StringRes
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.presentation.R

data class FcmTopicModel(val topic: FcmTopic) {
    @StringRes
    val settingsLabelResId: Int = when (topic) {
        FcmTopic.KwHomeNew -> R.string.settings_notification_new
        FcmTopic.KwHomeEdit -> R.string.settings_notification_edit
        FcmTopic.SwCentralNew -> R.string.settings_notification_new
    }
}
