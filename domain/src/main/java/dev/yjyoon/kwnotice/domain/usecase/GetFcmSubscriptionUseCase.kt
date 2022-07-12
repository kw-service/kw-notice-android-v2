package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFcmSubscriptionUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke(): Flow<Map<FcmTopic, Boolean>> = settingsRepository.getFcmSettingsStream()
}
