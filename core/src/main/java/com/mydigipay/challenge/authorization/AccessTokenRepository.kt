package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class AccessTokenRepository @Inject constructor(private val dataSource: AccessTokenDataSource) {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String = "0"
    ): Resource<AccessToken> =
        dataSource.getAccessToken(clientId, clientSecret, code, redirectUri, state)
}
