package com.kotlinsampleapp.common

/**
 * @author federico.giardini
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class LoadMore<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class InProgress(val status: LoadingStatus = LoadingStatus.NEW_SEARCH) : Result<Nothing>()
}
enum class LoadingStatus {
    NEW_SEARCH, MORE_ITEMS
}