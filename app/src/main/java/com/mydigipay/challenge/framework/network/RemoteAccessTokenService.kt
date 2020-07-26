package com.mydigipay.challenge.framework.network

import com.mydigipay.challenge.framework.network.request.AccessTokenRequest
import com.mydigipay.challenge.framework.network.response.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteAccessTokenService {

    @Headers("Accept:application/json")
    @POST(".")
    suspend fun fetchAccessToken(@Body accessTokenRequest: AccessTokenRequest): Response<AccessTokenResponse>
}
