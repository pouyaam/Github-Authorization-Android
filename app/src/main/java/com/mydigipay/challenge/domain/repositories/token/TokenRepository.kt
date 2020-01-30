package com.mydigipay.challenge.domain.repositories.token

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.AccessTokenEntity

interface TokenRepository {
    suspend fun fetchAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String
    ): Result<AccessTokenEntity>

    suspend fun saveAccessToken(token: String)

    fun readAccessToken(): String
}