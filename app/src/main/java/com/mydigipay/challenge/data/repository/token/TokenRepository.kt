package com.mydigipay.challenge.data.repository.token

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.oauth.RequestAccessToken

interface TokenRepository {
    suspend fun readToken(): String
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ApiResult<Unit>
    fun getLoginUrl(): String
    suspend fun isUserLoggedIn(): Boolean
}