package com.angga.univlist.ui.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angga.univlist.ui.data.UNIVERSITY_TABLE

@Entity(tableName = UNIVERSITY_TABLE)
data class UniversityEntity(
    @PrimaryKey
    val uniName: String,
    val countryCode: String,
    val webPages: List<String>,
)
