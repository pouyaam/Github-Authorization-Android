package com.mydigipay.challenge.authorization.repository.oauth

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.authorization.repository.oauth.model.RequestAccessToken
import com.mydigipay.challenge.authorization.repository.oauth.model.ResponseAccessToken

interface AccessTokenDataSource {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): RetrofitResult<ResponseAccessToken>
}