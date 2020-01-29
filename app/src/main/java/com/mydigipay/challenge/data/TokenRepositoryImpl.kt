package com.mydigipay.challenge.data

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.domain.repositories.token.LocalAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.RemoteAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.TokenRepository

class TokenRepositoryImpl(
    private val remoteAccessTokenDataSource: RemoteAccessTokenDataSource,
    private val localAccessTokenDataSource: LocalAccessTokenDataSource
) : TokenRepository {

    override suspend fun fetchAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String
    ): Result<AccessTokenEntity> {
        return remoteAccessTokenDataSource.getAccessToken(
            clientId,
            clientSecret,
            code,
            redirectUri,
            state
        )
    }

    override suspend fun saveAccessToken(token: String) {
        localAccessTokenDataSource.saveToken(token)
    }

    override suspend fun readAccessToken(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}