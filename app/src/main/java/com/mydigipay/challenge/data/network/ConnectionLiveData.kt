package com.mydigipay.challenge.data.network

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import com.mydigipay.challenge.util.ktx.logD

/**
 * A singleton to contain the Connectivity status of application.
 * It provides the status as a live data to be observed inside the view by ConnectionLiveData.observe
 *
 * Since this live data is observed in [BaseActivity][com.mydigipay.challenge.base.BaseActivity]
 * you can use ConnectionLiveData.value to access the updated ConnectivityModel
 *
 */
object ConnectionLiveData : LiveData<Boolean>() {

    private lateinit var appContext: Context

    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    fun init(context: Context) {
        appContext = context.applicationContext
        connectivityManager =
            appContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun isConnected() = value ?: false

    override fun onActive() {
        super.onActive()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
                connectivityManager.registerDefaultNetworkCallback(getConnectivityManagerCallback())
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
            else -> if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                appContext.registerReceiver(
                    networkReceiver,
                    IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
                ) // android.net.ConnectivityManager.CONNECTIVITY_ACTION
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
            } else {
                appContext.unregisterReceiver(networkReceiver)
            }
        } catch (e: Exception) {
            logD(e.localizedMessage ?: "")
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkAvailableRequest() {
        val builder = NetworkRequest.Builder()
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(
            builder.build(),
            getConnectivityManagerCallback()
        )
    }

    /**
     * [connectivityManagerCallback] is initialized for SDK_INT >= LOLLIPOP
     * lollipop needs a NetworkRequest.Builder but nougat and later does not need it
     */
    private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    postValue(true)
                }

                override fun onLost(network: Network?) {
                    postValue(false)
                }
            }
            return connectivityManagerCallback
        } else {
            throw IllegalAccessError("Should not happened")
        }
    }

    /**
     * This BroadcastReceiver will be registered only on SDK_INT < LOLLIPOP
     */
    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                postValue(activeNetwork?.isConnected == true)
            } else throw Exception("cannot happen")
        }
    }
}
