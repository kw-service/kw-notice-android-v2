package dev.yjyoon.kwnotice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.kwnotice.BuildConfig
import dev.yjyoon.kwnotice.domain.model.VersionName

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideVersionName() = VersionName(BuildConfig.VERSION_NAME)
}
