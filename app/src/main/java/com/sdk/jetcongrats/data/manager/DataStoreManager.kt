package com.sdk.jetcongrats.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Prefs")

    companion object {
        val colorKey = intPreferencesKey(name = "color")
        val backKey = booleanPreferencesKey(name = "backColor")
    }

    suspend fun saveColor(color: Int) {
        context.dataStore.edit {
            it[colorKey] = color
        }
    }

    fun getColor(): Flow<Int> = context.dataStore.data.map {
        it[colorKey] ?: 0
    }

    suspend fun saveBackColor(isDark: Boolean) {
        context.dataStore.edit {
            it[backKey] = isDark
        }
    }

    fun getBackColor(): Flow<Boolean> = context.dataStore.data.map {
        it[backKey] ?: false
    }
}