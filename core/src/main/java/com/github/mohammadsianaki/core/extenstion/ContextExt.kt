package com.github.mohammadsianaki.core.extenstion

import android.content.Context
import android.net.ConnectivityManager

fun Context.getConnectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager