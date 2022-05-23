package com.sample.soccerfan

sealed class Result<out T : Any> {
    data class Loading<out T : Any>(val isLoading: Boolean) : Result<T>()
    data class Success<out T : Any>(val value: T, val from: String = "") : Result<T>()
    // in case of network connection errors
    data class NetworkError<out T : Any>(val message: String?, val cause: Exception? = null) : Result<T>()
}