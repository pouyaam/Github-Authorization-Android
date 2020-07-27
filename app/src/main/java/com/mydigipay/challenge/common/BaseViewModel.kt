package com.mydigipay.challenge.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Reference:
 * [https://github.com/googlesamples/android-architecture-components]
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        super.onCleared()
    }
}
