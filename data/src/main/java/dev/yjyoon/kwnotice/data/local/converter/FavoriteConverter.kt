package dev.yjyoon.kwnotice.data.local.converter

import androidx.room.TypeConverter
import dev.yjyoon.kwnotice.data.local.entity.FavoriteEntity
import java.time.LocalDate

object FavoriteConverter {
    @TypeConverter
    fun typeToString(type: FavoriteEntity.Type): String = type.name

    @TypeConverter
    fun stringToType(string: String) = FavoriteEntity.Type.valueOf(string)

    @TypeConverter
    fun dateToString(date: LocalDate): String = date.toString()

    @TypeConverter
    fun stringToDate(string: String) = LocalDate.parse(string)
}
