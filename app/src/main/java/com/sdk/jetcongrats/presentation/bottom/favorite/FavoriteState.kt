package com.sdk.jetcongrats.presentation.bottom.favorite

import com.sdk.jetcongrats.domain.model.FavoriteData

data class FavoriteState(
    val success: List<FavoriteData>? = null,
    val error: String = "",
    val loading: Boolean = false
)