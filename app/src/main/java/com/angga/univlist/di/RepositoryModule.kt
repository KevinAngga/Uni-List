package com.angga.univlist.di

import com.angga.univlist.data.local.UniversityDatabase
import com.angga.univlist.data.repository.UniversityRepositoryImpl
import com.angga.univlist.domain.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUniversityRepository(
        database: UniversityDatabase,
        httpClient: HttpClient,
    ) : UniversityRepository {
        return UniversityRepositoryImpl(
            universityDatabase = database,
            httpClient = httpClient,
        )
    }
}