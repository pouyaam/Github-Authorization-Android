package com.mydigipay.challenge.domain.repository.oauth

import com.mydigipay.challenge.network.oauth.AccessTokenService
import com.mydigipay.challenge.network.oauth.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) : AccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}