package com.mydigipay.challenge.utils.ktx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

fun logE(t: Throwable, message: String? = null) {
    Timber.e(t, message)
}

fun logD(message: String) {
    Timber.d(message)
}

fun ViewModel.logRepeat(
    field: MutableLiveData<*>,
    prefix: String = "",
    interval: Long = 1_000
) {
    viewModelScope.launch {
        logD("$prefix -> ${field.value}")
        delay(interval)
        logRepeat(field, prefix, interval)
    }
}

fun ViewModel.logRepeat(
    field: Any,
    prefix: String = "",
    interval: Long = 1_000
) {
    viewModelScope.launch {
        logD("$prefix -> $field")
        delay(interval)
        logRepeat(field, prefix, interval)
    }
}