package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.base.Result
import com.mydigipay.challenge.network.repository.Repository

sealed class LoginResult : Result {
    object Loading : LoginResult()
    data class Success(val data: List<Repository>) : LoginResult()
    data class Failure(val error: Throwable) : LoginResult()
}