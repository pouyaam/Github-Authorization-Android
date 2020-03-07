package com.mydigipay.challenge.core

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent, LifecycleObserver {

    val sharedPref: SharedPreferences by inject()

}