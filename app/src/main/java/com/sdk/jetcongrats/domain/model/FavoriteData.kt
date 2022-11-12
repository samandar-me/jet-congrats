package com.sdk.jetcongrats.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val from: String,
    val text: String,
    val itemId: String
)