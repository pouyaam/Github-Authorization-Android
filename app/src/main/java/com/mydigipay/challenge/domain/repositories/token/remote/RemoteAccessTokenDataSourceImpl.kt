package com.mydigipay.challenge.domain.repositories.token.remote

import com.mydigipay.challenge.data.AccessTokenService
import com.mydigipay.challenge.data.RequestAccessToken
import com.mydigipay.challenge.data.ResponseAccessToken

class RemoteAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    RemoteAccessTokenDataSource {
    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): ResponseAccessToken {
        return accessTokenService.accessToken(
            RequestAccessToken(
                clientId, clientSecret, code, redirectUrl, state
            )
        )
    }
}