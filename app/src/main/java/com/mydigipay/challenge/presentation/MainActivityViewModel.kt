package com.mydigipay.challenge.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.usecase.AuthCheckingUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val useCase: AuthCheckingUseCase) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun isUserAuthorized(): Boolean {
        return useCase.isUserAuthorized()
    }

    fun fetchAccessToken(code: String) {
        useCase.fetchAccessToken(code).subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("", "")
            }, {
                Log.d("", "")
            }).let {
                compositeDisposable.add(it)
            }
    }
}