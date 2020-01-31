package com.github.mohammadsianaki.core.networkconnection.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import com.github.mohammadsianaki.core.networkconnection.NetworkListener
import com.github.mohammadsianaki.core.networkconnection.manager.NetworkManager


class NetworkReceiver : BroadcastReceiver() {
    private var latestNetworkState: Boolean? = null
    private val observers = mutableListOf<NetworkListener>()

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val networkInfo = intent?.extras?.get("networkInfo") as? NetworkInfo
            val networkConnected = when (networkInfo?.detailedState) {
                NetworkInfo.DetailedState.CONNECTED -> true
                NetworkInfo.DetailedState.DISCONNECTED -> NetworkManager.isNetworkAvailable(context)
                else -> latestNetworkState
            }
            if (networkConnected == latestNetworkState || networkConnected == null) {
                return
            }
            latestNetworkState = networkConnected
            notifyObservers(networkConnected)
        }
    }

    private fun notifyObservers(isConnected: Boolean) {
        observers.forEach { it.onNetworkChanged(isConnected) }
    }

    fun addNeworkListener(listener: NetworkListener) = observers.add(listener)

    fun removeNetworkListener(listener: NetworkListener) = observers.remove(listener)
}