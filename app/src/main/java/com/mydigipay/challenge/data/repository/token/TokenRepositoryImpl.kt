package com.mydigipay.challenge.data.repository.token

import com.mydigipay.challenge.data.network.api.oauth.AccessTokenService
import com.mydigipay.challenge.data.network.api.oauth.RequestAccessToken
import com.mydigipay.challenge.data.persist.SharedPrefWrapper


class TokenRepositoryImpl(
    private val sharedPrefWrapper: SharedPrefWrapper,
    private val accessTokenService: AccessTokenService
) : TokenRepository {

    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken)

    override suspend fun saveToken(token: String) = sharedPrefWrapper.saveToken(token)

    override suspend fun readToken() = sharedPrefWrapper.readToken()
}