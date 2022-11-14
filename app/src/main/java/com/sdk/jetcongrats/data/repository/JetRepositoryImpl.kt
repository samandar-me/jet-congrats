package com.sdk.jetcongrats.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sdk.jetcongrats.data.database.FavoriteDao
import com.sdk.jetcongrats.data.manager.DataStoreManager
import com.sdk.jetcongrats.data.manager.MyClipBoardManager
import com.sdk.jetcongrats.domain.model.Data
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.domain.repository.JetRepository
import com.sdk.jetcongrats.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class JetRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val dataStoreManager: DataStoreManager,
    private val clipBoardManager: MyClipBoardManager,
    private val dao: FavoriteDao
) : JetRepository {
    override suspend fun getAllData(collectionName: String): Flow<Response<List<Data>>> =
        callbackFlow {
            Response.Loading
            val snap = fireStore.collection(collectionName)
                .addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val dataList = snapShot.toObjects(Data::class.java)
                        Response.Success(dataList)
                    } else {
                        Response.Error(error?.stackTraceToString().toString())
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snap.remove()
            }
        }

    override suspend fun saveFavorite(favoriteData: FavoriteData) {
        dao.saveFavorite(favoriteData)
    }

    override suspend fun deleteFavorite(favoriteData: FavoriteData) {
        dao.deleteFavorite(favoriteData)
    }

    override fun getAllFavorites(): Flow<List<FavoriteData>> {
        return dao.getAllFavorites()
    }

    override suspend fun saveColor(int: Int) {
        dataStoreManager.saveColor(int)
    }

    override suspend fun getColor(): Flow<Int> {
        return dataStoreManager.getColor()
    }

    override fun copyText(text: String) {
        clipBoardManager.copyText(text)
    }

    override suspend fun saveBackColor(isDark: Boolean) {
        dataStoreManager.saveBackColor(isDark)
    }

    override suspend fun getBackColor(): Flow<Boolean> {
        return dataStoreManager.getBackColor()
    }
}