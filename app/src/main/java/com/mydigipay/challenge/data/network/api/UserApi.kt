package com.mydigipay.challenge.data.network.api

import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE_FORCE
import com.mydigipay.challenge.data.pojo.User
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {

    @GET("user")
    @Headers("$AUTH_STATE:$AUTH_STATE_FORCE")
    suspend fun getAuthenticatedUser(): User
}