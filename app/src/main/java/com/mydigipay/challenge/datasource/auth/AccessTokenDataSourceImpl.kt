package com.mydigipay.challenge.datasource.auth

import com.mydigipay.challenge.data.datasource.auth.AccessTokenDataSource
import com.mydigipay.challenge.network.oauth.AccessTokenService
import com.mydigipay.challenge.network.oauth.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    AccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}