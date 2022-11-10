package com.sdk.jetcongrats.util

sealed class Response<out T> {
    object Loading: Response<Nothing>()
    data class Success<out T>(val data: T): Response<T>()
    data class Error(val error: String): Response<Nothing>()
}