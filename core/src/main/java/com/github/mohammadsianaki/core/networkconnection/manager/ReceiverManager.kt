package com.github.mohammadsianaki.core.networkconnection.manager

import android.app.Activity
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import com.github.mohammadsianaki.core.extenstion.getConnectivityManager
import com.github.mohammadsianaki.core.networkconnection.NetworkListener
import com.github.mohammadsianaki.core.networkconnection.receiver.NetworkCallback
import com.github.mohammadsianaki.core.networkconnection.receiver.NetworkReceiver

object ReceiverManager :
    NetworkChangeReceiver {

    private val connectivityReceiver = NetworkReceiver()

    override fun registerNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager = activity.applicationContext.getConnectivityManager()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(NetworkCallback)
            } else {
                connectivityManager.registerNetworkCallback(
                    NetworkRequest.Builder().build(),
                    NetworkCallback
                )
            }
            NetworkCallback.addNeworkListener(networkListener)
        } else {
            activity.registerReceiver(
                connectivityReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
            connectivityReceiver.addNeworkListener(networkListener)
        }
    }

    override fun unregisterNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager = activity.applicationContext.getConnectivityManager()
            connectivityManager.unregisterNetworkCallback(NetworkCallback)
            NetworkCallback.removeNetworkListener(networkListener)

        } else {
            activity.unregisterReceiver(connectivityReceiver)
            connectivityReceiver.removeNetworkListener(networkListener)
        }
    }
}

interface NetworkChangeReceiver {
    fun registerNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    )

    fun unregisterNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    )
}