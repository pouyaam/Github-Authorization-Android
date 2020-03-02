package com.mydigipay.challenge.data.repository.token

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.oauth.AccessTokenService
import com.mydigipay.challenge.data.network.api.oauth.RequestAccessToken
import com.mydigipay.challenge.data.network.safeApiCall
import com.mydigipay.challenge.data.persist.SharedPrefWrapper


class TokenRepositoryImpl(
    private val sharedPrefWrapper: SharedPrefWrapper,
    private val accessTokenService: AccessTokenService
) : TokenRepository {

    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        safeApiCall {
            val result = accessTokenService.accessToken(requestAccessToken)
            saveToken(result.accessToken)
            return@safeApiCall ApiResult.Success(Unit)
        }

    private suspend fun saveToken(token: String) = sharedPrefWrapper.saveToken(token)

    override suspend fun readToken() = sharedPrefWrapper.readToken()
}