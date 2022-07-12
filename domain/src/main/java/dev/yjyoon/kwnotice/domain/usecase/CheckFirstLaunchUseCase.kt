package dev.yjyoon.kwnotice.domain.usecase

import dev.yjyoon.kwnotice.domain.repository.SettingsRepository
import javax.inject.Inject

class CheckFirstLaunchUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(): Result<Boolean> = settingsRepository.checkFirstLaunch()
}
