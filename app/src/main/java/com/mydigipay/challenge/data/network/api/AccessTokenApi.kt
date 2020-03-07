package com.mydigipay.challenge.data.network.api

import com.mydigipay.challenge.data.network.req.AccessTokenReq
import com.mydigipay.challenge.data.network.res.AccessTokenRes
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenApi {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun accessToken(@Body requestAccessToken: AccessTokenReq): AccessTokenRes
}