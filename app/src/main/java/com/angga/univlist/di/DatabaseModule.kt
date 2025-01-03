package com.angga.univlist.di

import android.content.Context
import androidx.room.Room
import com.angga.univlist.data.UNIVERSITY_DATABASE
import com.angga.univlist.data.local.UniversityDatabase
import com.angga.univlist.data.local.dao.UniversityDao
import com.angga.univlist.data.local.dao.UniversityRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): UniversityDatabase {
        return Room.databaseBuilder(
            context = context,
            UniversityDatabase::class.java,
            UNIVERSITY_DATABASE
        ).build()
    }

    @Provides
    fun provideUniversityDao(universityDatabase: UniversityDatabase)
            : UniversityDao = universityDatabase.universityDao

    @Provides
    fun provideUniversityRemoteKeysDao(universityDatabase: UniversityDatabase)
            : UniversityRemoteKeysDao = universityDatabase.universityRemoteKeysDao
}