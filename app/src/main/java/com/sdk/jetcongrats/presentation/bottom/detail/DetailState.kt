package com.sdk.jetcongrats.presentation.bottom.detail

import com.sdk.jetcongrats.domain.model.Data

data class DetailState(
    val success: List<Data>? = null,
    val loading: Boolean = false,
    val error: String = ""
)
