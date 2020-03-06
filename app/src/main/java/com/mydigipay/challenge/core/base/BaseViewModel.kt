package com.mydigipay.challenge.core.base

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.core.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    val showProgressBar = SingleLiveEvent<Unit>()
    val hideProgressBar = SingleLiveEvent<Unit>()
    var mError = SingleLiveEvent<String>()

    override fun onCleared() {
        super.onCleared()
    }
}