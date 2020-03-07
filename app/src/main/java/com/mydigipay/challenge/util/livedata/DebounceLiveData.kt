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
fun <T> LiveData<T>.debounce(duration: Long = 1000L) = MediatorLiveData<T>().apply {
    val source = this@debounce
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        if (value != source.value) value = source.value
    }

    addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }
}

/**
 * an ext func like rx debounce operator for live data
 * this generates event wrapper to prevent from calling when onBackPressed
 */
fun <T> LiveData<T>.debounceEvent(duration: Long = 1000L) = MediatorLiveData<Event<T?>>().apply {
    val source = this@debounceEvent
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        if (value?.peek() != source.value) value = Event(source.value)
    }

    addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }
}