package com.mydigipay.challenge.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import io.reactivex.disposables.CompositeDisposable

open class RxNavBaseViewModel(
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    var navigator: NavController? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        compositeDisposable.dispose()
    }
}