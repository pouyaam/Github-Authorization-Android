package com.github.mohammadsianaki.core.networkconnection

interface NetworkListener {
    fun onNetworkChanged(connected: Boolean)
}