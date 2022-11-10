package com.sdk.jetcongrats.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sdk.jetcongrats.data.manager.DataStoreManager
import com.sdk.jetcongrats.data.manager.MyClipBoardManager
import com.sdk.jetcongrats.domain.model.Data
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.domain.repository.JetRepository
import com.sdk.jetcongrats.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class JetRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val dataStoreManager: DataStoreManager,
    private val clipBoardManager: MyClipBoardManager
) : JetRepository {
    private var operationSuccessful = false
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

    override suspend fun saveFavorite(favoriteData: FavoriteData): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            fireStore.collection("favorites").document().set(favoriteData)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if (operationSuccessful) {
                Response.Success(operationSuccessful)
            } else {
                Response.Success("Error")
            }
        } catch (e: Exception) {
            emit(Response.Error(e.stackTraceToString().toString()))
        }
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