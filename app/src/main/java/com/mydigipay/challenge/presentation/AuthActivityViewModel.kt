package com.mydigipay.challenge.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.mydigipay.challenge.domain.usecase.AuthUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthActivityViewModel @Inject constructor(private val useCase: AuthUseCase) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val state: BehaviorRelay<AuthActivityState> = BehaviorRelay.create()
    fun getState() = state.hide()

    fun isUserAuthorized(): Boolean {
        return useCase.isUserAuthorized()
    }

    fun fetchAccessToken(code: String) {
        state.accept(AuthActivityState.Loading)
        useCase.fetchAccessToken(code).subscribeOn(Schedulers.io())
            .subscribe({
                state.accept(AuthActivityState.SuccessfullyGotToken)
            }, {
                state.accept(AuthActivityState.Error)
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class AuthActivityState {
    object Loading : AuthActivityState()
    object Error : AuthActivityState()
    object SuccessfullyGotToken : AuthActivityState()
}