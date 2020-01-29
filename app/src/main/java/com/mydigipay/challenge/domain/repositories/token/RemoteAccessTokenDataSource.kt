package com.mydigipay.challenge.domain.repositories.token

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.AccessTokenEntity

interface RemoteAccessTokenDataSource {
    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): Result<AccessTokenEntity>
}