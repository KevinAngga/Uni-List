package com.angga.univlist.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UniversityConverter {
    //Ignore all the keys that not need
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return json.encodeToString(list)
    }
}