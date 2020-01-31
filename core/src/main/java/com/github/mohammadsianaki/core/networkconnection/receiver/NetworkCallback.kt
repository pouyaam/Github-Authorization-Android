package com.github.mohammadsianaki.core.networkconnection.receiver

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mohammadsianaki.core.networkconnection.NetworkListener

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object NetworkCallback : ConnectivityManager.NetworkCallback() {
    private val observers = mutableListOf<NetworkListener>()


    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        notifyObservers(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        notifyObservers(false)
    }

    private fun notifyObservers(isConnected: Boolean) {
        observers.forEach { it.onNetworkChanged(isConnected) }
    }

    fun addNetworkListener(listener: NetworkListener) = observers.add(listener)

    fun removeNetworkListener(listener: NetworkListener) = observers.remove(listener)
}