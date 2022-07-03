package dev.yjyoon.kwnotice.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideBaseUrl(): String = "https://n6f11u33jf.execute-api.ap-northeast-2.amazonaws.com/v1"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
}
