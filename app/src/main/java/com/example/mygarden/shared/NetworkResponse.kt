package com.example.mygarden.shared

import okhttp3.ResponseBody

sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T) : NetworkResponse<T>()
    data class Failure(
        val isNetWorkError: Boolean,
        val errorCode: Int?,
        val errorResponseBody: ResponseBody?
    ) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}