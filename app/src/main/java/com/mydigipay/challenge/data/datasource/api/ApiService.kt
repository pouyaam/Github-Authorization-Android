package com.mydigipay.challenge.data.datasource.api

import com.mydigipay.challenge.data.model.RemoteRepositorySearchResponse
import com.mydigipay.challenge.data.model.RequestAccessToken
import com.mydigipay.challenge.data.model.ResponseAccessToken
import com.mydigipay.challenge.data.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>

    @GET("/search/repositories")
    fun performSearch(@Query("q") query: String): Single<SearchResponse>
}