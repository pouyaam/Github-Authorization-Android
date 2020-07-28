package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource

interface AccessTokenDataSource {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String
    ): Resource<AccessToken> = Resource.error(IllegalAccessException())

    suspend fun saveAccessToken(token: String): Resource<Unit> = Resource.error(IllegalAccessException())

    suspend fun readAccessToken(): Resource<String> = Resource.error(IllegalAccessException())
}
