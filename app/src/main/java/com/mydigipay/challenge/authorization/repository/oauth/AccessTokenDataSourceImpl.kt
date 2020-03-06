package com.mydigipay.challenge.authorization.repository.oauth

import com.mydigipay.challenge.authorization.repository.oauth.model.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    AccessTokenDataSource {

    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken)
}