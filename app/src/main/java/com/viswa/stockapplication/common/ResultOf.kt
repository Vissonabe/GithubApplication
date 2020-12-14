package com.viswa.stockapplication.common

sealed class ResultOf<out T : Any> {
    data class Success<out T: Any>(val data : T) : ResultOf<T>()
    data class Failure(val message : String) : ResultOf<Nothing>()
    object Loading : ResultOf<Nothing>()
}