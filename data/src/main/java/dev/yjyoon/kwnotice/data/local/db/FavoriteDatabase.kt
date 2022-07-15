package dev.yjyoon.kwnotice.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.yjyoon.kwnotice.data.local.converter.FavoriteConverter
import dev.yjyoon.kwnotice.data.local.dao.FavoriteDao
import dev.yjyoon.kwnotice.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FavoriteConverter::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
