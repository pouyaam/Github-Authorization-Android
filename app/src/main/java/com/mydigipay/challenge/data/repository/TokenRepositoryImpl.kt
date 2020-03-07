package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.AccessTokenApi
import com.mydigipay.challenge.data.network.req.AccessTokenReq
import com.mydigipay.challenge.data.network.safeApiCall
import com.mydigipay.challenge.data.persist.SharedPrefWrapper

private const val CLIENT_ID = "Iv1.3bce69c99a7f223f"
private const val CLIENT_SECRET = "2f2eeb74e0eff9433b5214eed5826f691c80d129"
private const val REDIRECT_URI = "challenge://mydigipay.com/login/callback"
private const val STATE = "0"

class TokenRepositoryImpl(
    private val sharedPrefWrapper: SharedPrefWrapper,
    private val accessTokenService: AccessTokenApi
) : TokenRepository {

    override suspend fun accessToken(code: String) =
        safeApiCall {
            val result = accessTokenService.accessToken(
                AccessTokenReq(
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    REDIRECT_URI,
                    STATE
                )
            )
            saveToken(result.accessToken)
            return@safeApiCall ApiResult.Success(Unit)
        }

    private suspend fun saveToken(token: String) = sharedPrefWrapper.saveToken(token)

    override suspend fun readToken() = sharedPrefWrapper.readToken()

    override suspend fun isUserLoggedIn() = sharedPrefWrapper.isUserLoggedIn()

    override fun getLoginUrl() =
        "https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&scope=repo user&state=$STATE"
}