package dev.yjyoon.kwnotice.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.presentation.fcm.FcmSubscription
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class FcmSubscriptionImpl @Inject constructor() : FcmSubscription {

    override suspend fun subscribeTo(topic: FcmTopic): Result<Unit> = runCatching {
        suspendCoroutine { continuation ->
            FirebaseMessaging.getInstance().subscribeToTopic(topic.value)
                .addOnCompleteListener {
                    Log.d("fcm", "Subscribed to ${topic.value} successfully")
                    continuation.resume(Unit)
                }
                .addOnFailureListener {
                    Log.d("fcm", "Failed to subscribe to ${topic.value}")
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun unsubscribeFrom(topic: FcmTopic): Result<Unit> = runCatching {
        suspendCoroutine { continuation ->
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic.value)
                .addOnCompleteListener {
                    Log.d("fcm", "Unsubscribed from ${topic.value} successfully")
                    continuation.resume(Unit)
                }
                .addOnFailureListener {
                    Log.d("fcm", "Failed to unsubscribe from ${topic.value}")
                    continuation.resumeWithException(it)
                }
        }
    }
}
