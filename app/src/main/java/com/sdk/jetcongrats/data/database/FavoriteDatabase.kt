package com.sdk.jetcongrats.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.jetcongrats.domain.model.FavoriteData

@Database(entities = [FavoriteData::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val dao: FavoriteDao
}