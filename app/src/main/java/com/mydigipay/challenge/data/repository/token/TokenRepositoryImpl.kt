package com.mydigipay.challenge.data.repository.token

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.oauth.AccessTokenService
import com.mydigipay.challenge.data.network.api.oauth.RequestAccessToken
import com.mydigipay.challenge.data.network.safeApiCall
import com.mydigipay.challenge.data.persist.SharedPrefWrapper

private const val CLIENT_ID = "31d8fee088f5e2caed71"
private const val CLIENT_SECRET = "ba1e3bc0669edb06c928d9233d7ce775d87a787c"
private const val REDIRECT_URI = "challenge://mydigipay.com/login/callback"

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

    override suspend fun isUserLoggedIn() = sharedPrefWrapper.isUserLoggedIn()

    override fun getLoginUrl() =
        "https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&scope=repo user&state=0"
}