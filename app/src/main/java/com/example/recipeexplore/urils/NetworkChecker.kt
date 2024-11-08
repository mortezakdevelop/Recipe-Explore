package com.example.recipeexplore.urils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class NetworkChecker @Inject constructor(
    private val manager: ConnectivityManager,
    private val request: NetworkRequest
) : ConnectivityManager.NetworkCallback() {

    private val isNetworkAvailable = MutableStateFlow(false)
    private val capabilities: NetworkCapabilities? = null

    fun checkNetworkAvailability(): MutableStateFlow<Boolean> {
        manager.registerNetworkCallback(request, this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val activityNetwork = manager.activeNetwork
            if (activityNetwork == null) {
                isNetworkAvailable.update { false }
                return isNetworkAvailable
            }
            if (capabilities == null) {
                isNetworkAvailable.update { false }
                return isNetworkAvailable
            }
            manager.getNetworkCapabilities(activityNetwork)?.let { capabilities ->
                isNetworkAvailable.update {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                }
            } ?: isNetworkAvailable.update { false }

        }else{
            // this is deprecated way activeNetwork like line 23(we use deprecate and non deprecate check network)
            manager.run {
                manager.activeNetworkInfo?.run {
                    isNetworkAvailable.value = when(type){
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
       isNetworkAvailable.update { true }
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        isNetworkAvailable.update { false }
    }
}