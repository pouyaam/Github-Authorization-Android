package com.mydigipay.challenge.domain.repositories.oauth

import com.mydigipay.challenge.data.AccessTokenService
import com.mydigipay.challenge.data.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    AccessTokenDataSource {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken)
}