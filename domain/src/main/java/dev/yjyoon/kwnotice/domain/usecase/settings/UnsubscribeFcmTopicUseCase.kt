package dev.yjyoon.kwnotice.domain.usecase.settings

import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.domain.repository.SettingsRepository
import javax.inject.Inject

class UnsubscribeFcmTopicUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(topic: FcmTopic): Result<Unit> =
        settingsRepository.unsubscribeFcmTopic(topic)
}