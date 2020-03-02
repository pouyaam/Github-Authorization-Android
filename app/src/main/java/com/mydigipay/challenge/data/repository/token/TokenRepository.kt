package com.mydigipay.challenge.data.repository.token

import com.mydigipay.challenge.data.network.api.oauth.RequestAccessToken
import com.mydigipay.challenge.data.network.api.oauth.ResponseAccessToken

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun readToken(): String
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
}