package com.example.recipeexplore.urils

import retrofit2.Response

open class NetworkResponseCode<T>(private val response: Response<T>) {

    fun generalNetworkResponse(): NetworkState<T> {
        return when {
            response.message().contains("timeout") -> NetworkState.Error("Time out server")
            response.code() == 401 -> NetworkState.Error("You are not authorized")
            response.code() == 402 -> NetworkState.Error("Your free plan finished")
            response.code() == 404 -> NetworkState.Error("Not found")
            response.code() == 422 -> NetworkState.Error("Api key not found")
            response.code() == 500 -> NetworkState.Error("Try again, server not ready")
            response.isSuccessful -> response.body()?.let { body ->
                NetworkState.Success(body)
            } ?: NetworkState.Error("Response body is null")
            else -> NetworkState.Error(response.message())
        }
    }
}