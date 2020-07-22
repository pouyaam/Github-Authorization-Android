package com.mydigipay.challenge.data.datasource.remote

import com.mydigipay.challenge.data.model.RequestAccessToken
import com.mydigipay.challenge.data.model.ResponseAccessToken
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>
}