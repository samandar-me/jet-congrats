package com.sdk.jetcongrats.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdk.jetcongrats.domain.model.FavoriteData
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteData: FavoriteData)

    @Query("SELECT * FROM Favorites ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<FavoriteData>>

    @Delete
    suspend fun deleteFavorite(favoriteData: FavoriteData)
}