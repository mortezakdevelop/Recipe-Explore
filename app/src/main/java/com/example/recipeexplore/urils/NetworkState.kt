package com.example.recipeexplore.urils

import android.net.Network
import android.net.NetworkRequest

sealed class NetworkState<T>(val data:T? = null,val message: String? = null) {
    class Loading<T> : NetworkState<T>()
    class Success<T>(data:T): NetworkState<T>(data)
    class Error<T>(message: String, data: T? = null):NetworkState<T>(data,message)
}