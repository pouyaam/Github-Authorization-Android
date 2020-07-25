package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource

interface AccessTokenDataSource {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String = "0"
    ): Resource<AccessToken>
}
