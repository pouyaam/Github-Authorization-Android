package com.mydigipay.challenge.dataaccess

import androidx.lifecycle.LifecycleObserver
import com.mydigipay.challenge.dataaccess.model.ResponseAccessToken
import io.reactivex.Single

internal const val isUserLoginKey = "IS_USER_LOGIN"
internal const val userTokenKey = "USER_TOKEN"
internal const val clientId = "5bbf76100ba0a25e29f5"
internal const val clientSecret = "f5b4a76900ae27a416d09dfa0abde2d876d78847"
internal const val redirectUrl = "challenge://mydigipay.com/login/callback"
internal const val clientState = "34235465352"

interface TokenDataSource : LifecycleObserver {
    fun isUserLogin(): Boolean
    fun saveToken(token: String)
    fun getToken(): String
    fun getLoginUrl(): String
    fun generateToken(code: String): Single<ResponseAccessToken>
}