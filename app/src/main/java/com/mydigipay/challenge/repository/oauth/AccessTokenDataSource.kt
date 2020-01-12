package com.mydigipay.challenge.repository.oauth

import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.network.oauth.ResponseAccessToken
import kotlinx.coroutines.Deferred

interface AccessTokenDataSource {
    fun accessToken(requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>
}