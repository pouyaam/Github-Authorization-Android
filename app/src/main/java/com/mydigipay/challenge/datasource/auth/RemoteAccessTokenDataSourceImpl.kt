package com.mydigipay.challenge.datasource.auth

import com.mydigipay.challenge.data.datasource.auth.RemoteAccessTokenDataSource
import com.mydigipay.challenge.network.oauth.AccessTokenService
import com.mydigipay.challenge.network.oauth.RequestAccessToken

class RemoteAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    RemoteAccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}