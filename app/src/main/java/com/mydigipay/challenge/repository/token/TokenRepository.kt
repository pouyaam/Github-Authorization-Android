package com.mydigipay.challenge.repository.token

import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.network.oauth.ResponseAccessToken

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun readToken(): String
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
}