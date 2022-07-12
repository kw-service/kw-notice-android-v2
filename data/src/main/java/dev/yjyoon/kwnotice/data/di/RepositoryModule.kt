package dev.yjyoon.kwnotice.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.kwnotice.data.repository.NoticeRepositoryImpl
import dev.yjyoon.kwnotice.data.repository.SettingsRepositoryImpl
import dev.yjyoon.kwnotice.domain.repository.NoticeRepository
import dev.yjyoon.kwnotice.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(repo: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindNoticeRepository(repo: NoticeRepositoryImpl): NoticeRepository
}
