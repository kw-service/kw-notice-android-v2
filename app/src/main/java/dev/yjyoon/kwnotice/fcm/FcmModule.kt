package dev.yjyoon.kwnotice.fcm

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.kwnotice.presentation.fcm.FcmSubscription

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FcmModule {

    @Binds
    abstract fun bindFcmSettings(fcmSettings: FcmSubscriptionImpl): FcmSubscription
}