package com.mydigipay.challenge.data.network.user

import com.mydigipay.challenge.data.model.User
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE_FORCE
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserService {

    @GET("user")
    @Headers("$AUTH_STATE:$AUTH_STATE_FORCE")
    suspend fun getAuthenticatedUser(): User

}