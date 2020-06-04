package com.mydigipay.challenge.infrastructure.network

import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseAccessToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitInterface {

    @Headers("Accept:application/json")
    @POST("/login/oauth/access_token")
    fun accessToken(@Body requestAccessToken: RequestAccessToken) : Single<ResponseAccessToken>

}
