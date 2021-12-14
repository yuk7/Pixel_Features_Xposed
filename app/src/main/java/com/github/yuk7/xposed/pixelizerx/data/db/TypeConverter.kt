package com.github.yuk7.xposed.pixelizerx.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeConverter {
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStringStringMap(map: Map<String, String>): String {
        return Json.encodeToString(map)
    }

    @TypeConverter
    fun toStringStringMap(value: String): Map<String, String> {
        return Json.decodeFromString(value)
    }
}