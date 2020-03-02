package com.mydigipay.challenge.repository.oauth

import com.mydigipay.challenge.network.oauth.AccessTokenService
import com.mydigipay.challenge.network.oauth.RequestAccessToken

class AccessTokenDataSourceImpl(
    private val accessTokenService: AccessTokenService
) : AccessTokenDataSource {

    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken)
}