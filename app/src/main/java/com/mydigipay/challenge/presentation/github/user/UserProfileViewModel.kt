package com.mydigipay.challenge.presentation.github.user

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.UserUseCase
import com.mydigipay.challenge.presentation.model.UserItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val state: BehaviorRelay<UserProfileFragmentState> = BehaviorRelay.create()

    fun getState() = state.hide()

    fun fetchUserInfo() {
        userUseCase.getUser().subscribeOn(Schedulers.io())
            .subscribe({
                state.accept(UserProfileFragmentState.GotUser(it.mapToPresentationModel()))
            }, {
                state.accept(UserProfileFragmentState.Error)
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class UserProfileFragmentState() {
    data class GotUser(val user: UserItem) : UserProfileFragmentState()
    object Loading : UserProfileFragmentState()
    object Error : UserProfileFragmentState()
}