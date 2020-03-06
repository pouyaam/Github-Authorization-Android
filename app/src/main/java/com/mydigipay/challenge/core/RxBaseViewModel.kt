package com.mydigipay.challenge.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

open class RxBaseViewModel(
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        compositeDisposable.dispose()
    }
}