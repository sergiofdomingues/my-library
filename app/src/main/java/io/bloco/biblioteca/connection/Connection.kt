package io.bloco.biblioteca.connection

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

class Connection(private val connectionManager: ConnectivityManager) {

    fun internetEnabled(): Boolean {

        if (Build.VERSION.SDK_INT < 23) run {
            val ni: NetworkInfo = connectionManager.activeNetworkInfo
            return (ni.isConnected && ni.type == ConnectivityManager.TYPE_WIFI
                    || ni.type == ConnectivityManager.TYPE_MOBILE)
        } else {
            val activeNetwork = connectionManager.activeNetwork
            if (activeNetwork != null) {
                val nc = connectionManager.getNetworkCapabilities(activeNetwork)
                return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ))
            }
        }
        return false
    }
}