package com.mydigipay.challenge.ui.profile.viewmodel

import androidx.databinding.Bindable
import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.dataaccess.model.UserResponse
import com.mydigipay.challenge.ui.profile.model.ProfileModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private val model: ProfileModel,
    private val compositeDisposable: CompositeDisposable
) : RxNavBaseViewModel(compositeDisposable) {

    @Bindable
    var loading = true
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var user: UserResponse = UserResponse()
        set(value) {
            field = value
            notifyChange()
        }

    init {
        getUser()
    }

    private fun getUser() {
        compositeDisposable.add(
            model.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        loading = false
                        user = it
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

    fun back() = navigator.popBackStack()
}