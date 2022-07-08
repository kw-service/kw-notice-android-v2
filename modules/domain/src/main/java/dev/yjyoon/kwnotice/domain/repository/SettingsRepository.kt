package dev.yjyoon.kwnotice.domain.repository

import dev.yjyoon.kwnotice.domain.model.FcmTopic
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getFcmSettingsStream(): Flow<Map<FcmTopic, Boolean>>

    suspend fun subscribeFcmTopic(topic: FcmTopic): Result<Unit>

    suspend fun unsubscribeFcmTopic(topic: FcmTopic): Result<Unit>

    suspend fun checkFirstLaunch(): Result<Boolean>
}
