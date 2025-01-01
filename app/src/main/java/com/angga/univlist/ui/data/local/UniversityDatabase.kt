package com.angga.univlist.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.angga.univlist.ui.data.local.converter.UniversityConverter
import com.angga.univlist.ui.data.local.dao.UniversityDao
import com.angga.univlist.ui.data.local.dao.UniversityRemoteKeysDao
import com.angga.univlist.ui.data.local.entity.UniversityEntity
import com.angga.univlist.ui.data.local.entity.UniversityRemoteKeysEntity

@Database(entities = [UniversityEntity::class, UniversityRemoteKeysEntity::class], version = 1, exportSchema = false)
@TypeConverters(UniversityConverter::class)
abstract class UniversityDatabase : RoomDatabase() {
    abstract val universityDao : UniversityDao
    abstract val universityRemoteKeysDao : UniversityRemoteKeysDao
}