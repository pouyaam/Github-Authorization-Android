package com.mydigipay.challenge.ui.authorize.viewmodel

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.databinding.Bindable
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.ui.authorize.model.AuthorizeModel
import com.mydigipay.challenge.ui.authorize.view.AuthorizeFragmentDirections
import com.mydigipay.challenge.util.go
import com.mydigipay.challenge.util.openIntent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthorizeViewModel(
    private val fragment: BaseFragment,
    private val model: AuthorizeModel,
    private val compositeDisposable: CompositeDisposable
) : RxNavBaseViewModel(compositeDisposable) {

    @Bindable
    var showLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }

    fun authorize() {
        fragment.openIntent(
            action = Intent.ACTION_VIEW,
            uri = Uri.parse(model.getLoginUrl())
        )
    }

    fun setCode(code: String) {
        showLoading = true
        navigator?.let { navigator ->
            compositeDisposable.add(
                model.getToken(code)
                    .doAfterSuccess {
                        model.saveToken(it.accessToken)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            showLoading = false
                            navigator go AuthorizeFragmentDirections.actionAuthorizeFragmentToSearchFragment()
                        },
                        {
                            it.printStackTrace()
                            Toast.makeText(fragment.context, "error", Toast.LENGTH_SHORT).show()
                        }
                    )
            )
        }
    }

}