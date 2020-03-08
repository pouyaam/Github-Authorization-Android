package com.mydigipay.challenge.ui.splash.viewmodel

import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.ui.splash.model.SplashModel
import com.mydigipay.challenge.ui.splash.view.SplashFragmentDirections
import com.mydigipay.challenge.util.go
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val model: SplashModel,
    private val compositeDisposable: CompositeDisposable
) : RxNavBaseViewModel(compositeDisposable) {

    fun navigation() {
        compositeDisposable.add(
            Observable.timer(2, TimeUnit.SECONDS)
                .map { model.isUserLogin() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isUserLogin ->
                    if (isUserLogin)
                        navigator go SplashFragmentDirections.actionSplashFragmentToSearchFragment()
                    else
                        navigator go SplashFragmentDirections.actionSplashFragmentToAuthorizeFragment()
                }
        )
    }
}