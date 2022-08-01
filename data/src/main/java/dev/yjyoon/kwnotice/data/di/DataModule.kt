package dev.yjyoon.kwnotice.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.kwnotice.data.BuildConfig
import dev.yjyoon.kwnotice.data.local.dao.FavoriteDao
import dev.yjyoon.kwnotice.data.local.db.FavoriteDatabase
import dev.yjyoon.kwnotice.data.remote.api.NoticeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    @Named("BaseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Named("Preferences")
    fun providePreference(): String = "settings"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNoticeService(retrofit: Retrofit): NoticeService =
        retrofit.create(NoticeService::class.java)

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        @Named("Preferences") preferences: String
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create { context.preferencesDataStoreFile(preferences) }

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context: Context): FavoriteDao = Room
        .databaseBuilder(context, FavoriteDatabase::class.java, "favorite")
        .build().favoriteDao()
}
