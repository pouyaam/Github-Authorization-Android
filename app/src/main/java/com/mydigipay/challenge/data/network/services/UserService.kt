package com.mydigipay.challenge.data.network.services

import com.mydigipay.challenge.data.models.RequestAccessToken
import com.mydigipay.challenge.data.models.ResponseAccessToken
import com.mydigipay.challenge.data.models.User
import retrofit2.http.*

interface UserService {

    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept:application/json")
    suspend fun getAccessToken(@Body requestAccessToken: RequestAccessToken): ResponseAccessToken

    @GET("user")
    suspend fun profile(@Header("Authorization") token: String? = null): User
}
