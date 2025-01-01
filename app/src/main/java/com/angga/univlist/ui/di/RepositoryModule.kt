package com.angga.univlist.ui.di

import com.angga.univlist.ui.data.local.UniversityDatabase
import com.angga.univlist.ui.data.repository.UniversityRepositoryImpl
import com.angga.univlist.ui.domain.repository.UniversityRepository
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