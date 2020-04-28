package com.mydigipay.challenge.repository.oauth

import com.mydigipay.challenge.data.network.oauth.AccessTokenService
import com.mydigipay.challenge.data.network.oauth.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) : AccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}