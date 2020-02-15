package com.mydigipay.challenge.ui.home

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mydigipay.challenge.base.RequiredCodeException
import com.mydigipay.challenge.repository.token.LoginRepository

class HomeViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    var code: String? = null
        set(value) {
            if (value != null) {
                field = value
                Log.i("HomeViewModel", "Code:$value")
            }
        }

    init {
        code = loginRepository.code
    }

    private val viewState = LoginViewState()

    val data by lazy {
        Transformations.map(
            liveData {
                code ?: run {
                    emit(LoginResult.Failure(RequiredCodeException()))
                    return@liveData
                }
                emit(LoginResult.Loading)
                emit(loginRepository.getRepositories(code!!))
            }
        ) {
            handleResult(it)
        }
    }


    private fun handleResult(it: LoginResult): LoginViewState {
        return when (it) {
            is LoginResult.Loading -> viewState.copy(isLoading = true)
            is LoginResult.Success -> viewState.copy(
                isLoading = false,
                repositories = it.data,
                error = null
            )
            is LoginResult.Failure -> viewState.copy(
                isLoading = false,
                requiredCode = loginRepository.code == null,
                error = it.error
            )
        }
    }


}