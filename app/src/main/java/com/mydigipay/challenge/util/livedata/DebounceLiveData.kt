/**
 * Created by Saman Sattari on 1/19/2020.
 *
 * Copyright (c) 2020 MobtakerTeam All rights reserved.
 *
 */

package com.mydigipay.challenge.util.livedata

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * an ext func like rx debounce operator for live data
 */
fun <T> LiveData<T>.debounce(duration: Long = 1000L) = MediatorLiveData<T>().let { mld ->
    val source = this
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        if (mld.value != source.value) mld.value = source.value
    }

    mld.addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }

    mld
}