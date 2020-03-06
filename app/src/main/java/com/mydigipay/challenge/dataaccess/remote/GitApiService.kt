package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.model.RequestAccessToken
import com.mydigipay.challenge.dataaccess.model.ResponseAccessToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitApiService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>
}