package com.mydigipay.challenge.ui.login

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.token.TokenRepository
import com.mydigipay.challenge.util.ktx.launch

class LoginViewModel(
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    private val code = MutableLiveData<String>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun onLoginClicked() {
        openIntent(action = Intent.ACTION_VIEW, uri = Uri.parse(tokenRepository.getLoginUrl()))
    }


    fun setCode(code: String?) {
        this.code.postValue(code)
        getToken(code)
    }

    private fun getToken(code: String?) = launch {
        _isLoading.postValue(true)
        code.takeIf { !it.isNullOrBlank() }?.let { code ->
            when (val result = tokenRepository.accessToken(code)) {
                is ApiResult.Success -> showToast(R.string.login_successful)
                is ApiResult.Error -> showToast(result.message)
            }
        }
        _isLoading.postValue(false)

    }

}