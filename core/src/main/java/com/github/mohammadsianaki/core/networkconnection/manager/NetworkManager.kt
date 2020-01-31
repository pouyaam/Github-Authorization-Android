package com.github.mohammadsianaki.core.networkconnection.manager

import android.app.Activity
import android.content.Context
import com.github.mohammadsianaki.core.extenstion.getConnectivityManager
import com.github.mohammadsianaki.core.networkconnection.NetworkListener

class NetworkManager : NetworkStateHelper {

    override fun registerNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    ) {
        ReceiverManager.registerNetworkChangeReceiver(activity, networkListener)
    }

    override fun unregisterNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkListener
    ) {
        ReceiverManager.unregisterNetworkChangeReceiver(activity, networkListener)
    }

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            return context.getConnectivityManager().activeNetworkInfo?.isConnected ?: false
        }
    }
}


interface NetworkStateHelper {
    fun registerNetworkChangeReceiver(activity: Activity, networkListener: NetworkListener)
    fun unregisterNetworkChangeReceiver(activity: Activity, networkListener: NetworkListener)
}