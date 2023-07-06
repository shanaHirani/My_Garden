package com.example.mygarden.data.repository

import android.util.Log
import com.example.mygarden.shared.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository() {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val a = apiCall.invoke()
                Log.i("safeApiCall",a.toString())
                NetworkResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.i("safeApiCall",throwable.toString())
                when (throwable) {
                    is HttpException -> {
                        NetworkResponse.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        NetworkResponse.Failure(true, null, null)
                    }
                }

            }
        }
    }

    suspend fun <T,S> safeApiCall(
        apiCall: suspend () -> T,
        transform: (T)->S
    ): NetworkResponse<S> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(transform(apiCall.invoke()))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResponse.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        NetworkResponse.Failure(true, null, null)
                    }
                }

            }
        }
    }
}