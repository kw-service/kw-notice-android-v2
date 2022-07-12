package dev.yjyoon.kwnotice.presentation.fcm

import dev.yjyoon.kwnotice.domain.model.FcmTopic

interface FcmSubscription {

    suspend fun subscribeTo(topic: FcmTopic): Result<Unit>

    suspend fun unsubscribeFrom(topic: FcmTopic): Result<Unit>
}
