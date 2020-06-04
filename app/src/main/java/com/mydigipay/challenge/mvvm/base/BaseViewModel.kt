package com.mydigipay.challenge.mvvm.base

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */

abstract class BaseViewModel<N>(dataManager: DataManager, schedulersProvider: SchedulersProvider) : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var mNavigator: WeakReference<N>

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}