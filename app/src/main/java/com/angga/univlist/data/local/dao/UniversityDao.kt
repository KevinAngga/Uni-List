package com.angga.univlist.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.angga.univlist.data.local.entity.UniversityEntity

@Dao
interface UniversityDao {

    @Upsert
    suspend fun addAllUni(universities: List<UniversityEntity>)

    @Query("DELETE FROM `university_table`")
    suspend fun deleteAllUni()

    @Query("SELECT * FROM `university_table`")
    fun getAll(): PagingSource<Int, UniversityEntity>
}