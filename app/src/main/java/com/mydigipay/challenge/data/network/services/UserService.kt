package com.mydigipay.challenge.data.network.services

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.RequestAccessToken
import com.mydigipay.challenge.data.models.ResponseAccessToken
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.data.network.AUTHENTICATE_TOKEN
import retrofit2.http.*

interface UserService {

    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept:application/json")
    suspend fun getAccessToken(@Body requestAccessToken: RequestAccessToken): ResponseAccessToken

    @GET("user")
    suspend fun profile(@Header(AUTHENTICATE_TOKEN) token: String? = null): User

    @GET("user/repos")
    suspend fun repos(
        @Query("page") page: Int,
        @Query("sort") sort: String = "pushed",
        @Query("per_page") per_page: Int = 10
    ): List<Repo>
}
