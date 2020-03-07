package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.*
import com.mydigipay.challenge.dataaccess.model.RequestAccessToken

class TokenRemoteDataSource(
    private val api: GitApiService
) : TokenDataSource {
    override fun isUserLogin() = throw IllegalStateException("bad request")

    override fun saveToken(token: String) = throw IllegalStateException("bad request")

    override fun getToken() = throw IllegalStateException("bad request")

    override fun getLoginUrl() = throw IllegalStateException("bad request")

    override fun generateToken(code: String) =
        api.getAccessToken(
            RequestAccessToken(
                clientId,
                clientSecret,
                code,
                redirectUrl,
                clientState
            )
        )
}