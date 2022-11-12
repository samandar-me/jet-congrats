package com.sdk.jetcongrats.presentation.bottom.favorite

import com.sdk.jetcongrats.domain.model.FavoriteData

data class FavoriteState(
    val success: List<FavoriteData>? = null,
    val loading: Boolean = false
)