package com.example.testproject.coroutine

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Error(var errorMessage: String? = null): Resource<Nothing>()
    data class Success<T>(val data: T): Resource<T>()
    object Started: Resource<Nothing>()
}
