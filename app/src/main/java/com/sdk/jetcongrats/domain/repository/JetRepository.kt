package com.sdk.jetcongrats.domain.repository

import com.sdk.jetcongrats.domain.model.Data
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.util.Response
import kotlinx.coroutines.flow.Flow

interface JetRepository {
    suspend fun getAllData(collectionName: String): Flow<Response<List<Data>>>
    suspend fun saveColor(int: Int)
    suspend fun getColor(): Flow<Int>
    fun copyText(text: String)
    suspend fun saveBackColor(isDark: Boolean)
    suspend fun getBackColor(): Flow<Boolean>

    suspend fun saveFavorite(favoriteData: FavoriteData)
    suspend fun deleteFavorite(favoriteData: FavoriteData)
    fun getAllFavorites(): Flow<List<FavoriteData>>
}