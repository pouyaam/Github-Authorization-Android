package com.mydigipay.challenge.domain.repositories.token.remote

import com.mydigipay.challenge.data.ResponseAccessToken

interface RemoteAccessTokenDataSource {
    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): ResponseAccessToken
}