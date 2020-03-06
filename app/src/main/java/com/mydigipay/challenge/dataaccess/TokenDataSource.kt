package com.mydigipay.challenge.dataaccess

import androidx.lifecycle.LifecycleObserver
import com.mydigipay.challenge.dataaccess.model.ResponseAccessToken
import io.reactivex.Single

internal const val isUserLoginKey = "IS_USER_LOGIN"
internal const val userTokenKey = "USER_TOKEN"
internal const val clientId = "31d8fee088f5e2caed71"
internal const val clientSecret = "ba1e3bc0669edb06c928d9233d7ce775d87a787c"
internal const val redirectUrl = "challenge://mydigipay.com/login/callback"
internal const val clientState = "7053078815"

interface TokenDataSource : LifecycleObserver {
    fun isUserLogin(): Boolean
    fun saveToken(token: String)
    fun getToken(): String
    fun getLoginURL(): String
    fun generateToken(code: String): Single<ResponseAccessToken>
}