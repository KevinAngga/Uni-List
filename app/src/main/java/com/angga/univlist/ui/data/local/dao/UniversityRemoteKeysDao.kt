package com.angga.univlist.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.angga.univlist.ui.data.local.entity.UniversityRemoteKeysEntity

@Dao
interface UniversityRemoteKeysDao {
    @Upsert
    suspend fun addRemoteKeys(remoteKeys: List<UniversityRemoteKeysEntity>)

    @Query("DELETE FROM `university_remote_keys`")
    suspend fun deleteAllRemoteKeys()

    @Query("SELECT * FROM `university_remote_keys` WHERE name=:uniName")
    suspend fun getRemoteKeys(uniName : String): UniversityRemoteKeysEntity
}