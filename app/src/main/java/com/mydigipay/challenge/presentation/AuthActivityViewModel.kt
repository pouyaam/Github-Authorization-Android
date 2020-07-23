package com.mydigipay.challenge.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.usecase.AuthUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthActivityViewModel @Inject constructor(private val useCase: AuthUseCase) :
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