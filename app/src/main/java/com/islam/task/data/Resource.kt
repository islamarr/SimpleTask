package com.islam.task.data

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}

val Result<*>.succeeded
    get() = this is Resource.Success<*> && data != null