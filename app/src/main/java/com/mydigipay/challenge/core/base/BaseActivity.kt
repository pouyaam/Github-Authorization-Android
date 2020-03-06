package com.mydigipay.challenge.core.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.core.NetworkStateReceiver
import com.mydigipay.challenge.core.extension.toast

abstract class BaseActivity : AppCompatActivity(),
    NetworkStateReceiver.NetworkStateReceiverListener {
    private var networkStateReceiver: NetworkStateReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNetworkStateChangeReceiver()
    }

    public override fun onDestroy() {
        super.onDestroy()
        networkStateReceiver?.removeListener(this)
        this.unregisterReceiver(networkStateReceiver)
    }

    private fun addNetworkStateChangeReceiver() {
        networkStateReceiver = NetworkStateReceiver()
        networkStateReceiver?.addListener(this)
        this.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    abstract fun initView()
    abstract fun subscribeViews()

    abstract override fun networkAvailable()
    override fun networkUnavailable() {
        toast("Disconnected")
    }
}
