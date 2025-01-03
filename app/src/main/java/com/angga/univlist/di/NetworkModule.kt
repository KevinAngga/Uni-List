package com.angga.univlist.di

import com.angga.univlist.data.remote.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClientFactory().build(Android.create())
    }
}