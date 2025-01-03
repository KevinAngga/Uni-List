package com.angga.univlist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angga.univlist.data.UNIVERSITY_REMOTE_KEYS

@Entity(tableName = UNIVERSITY_REMOTE_KEYS)
data class UniversityRemoteKeysEntity(
    @PrimaryKey
    val name : String,
    val nextKey : Int?,
    val prevKey : Int?
)