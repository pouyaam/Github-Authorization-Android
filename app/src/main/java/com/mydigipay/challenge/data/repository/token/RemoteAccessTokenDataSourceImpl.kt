package com.mydigipay.challenge.data.repository.token

import com.github.mohammadsianaki.core.extenstion.awaitResult
import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.data.api.token.AccessTokenService
import com.mydigipay.challenge.data.api.token.RequestAccessToken
import com.mydigipay.challenge.data.toAccessTokenEntity
import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.domain.repositories.token.RemoteAccessTokenDataSource

class RemoteAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    RemoteAccessTokenDataSource {
    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): Result<AccessTokenEntity> {
        return accessTokenService.accessToken(
            RequestAccessToken(
                clientId, clientSecret, code, redirectUrl, state
            )
        ).awaitResult {
            it.toAccessTokenEntity()
        }
    }
}