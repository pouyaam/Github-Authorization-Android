package com.mydigipay.challenge.authorization.repository.oauth

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.authorization.repository.oauth.model.RequestAccessToken
import com.mydigipay.challenge.authorization.repository.oauth.model.ResponseAccessToken
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun accessToken(@Body requestAccessToken: RequestAccessToken): RetrofitResult<ResponseAccessToken>
}