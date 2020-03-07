package com.mydigipay.challenge.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.utils.ktx.logD
import kotlinx.coroutines.launch

class LoginViewModel(private val model: LoginModel) : BaseViewModel(model) {

    private val code = MutableLiveData<String>()

    private val _openLink = MutableLiveData<String>()
    val openLink: LiveData<String>
        get() = _openLink

    fun onLoginClicked() {
        _openLink.postValue(model.getLoginUrl)
    }


    fun setCode(code: String?) {
        this.code.postValue(code)
        getToken(code)
    }

    private fun getToken(code: String?) = viewModelScope.launch {
        code.takeIf { !it.isNullOrBlank() }?.let { code ->
            when (val result = model.accessToken(code)) {
                is ApiResult.Success -> {
                    logD("result Success = $result")
                    navigateTo(LoginFragmentDirections.actionHomeToProfile())
                }
                is ApiResult.Error -> {
                    logD("result Error = $result")
                }
            }
        }
    }
}