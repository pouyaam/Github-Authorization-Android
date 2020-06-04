package com.mydigipay.challenge.infrastructure.network

import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ResponseRepositorySearch
import io.reactivex.Single
import retrofit2.http.*


interface RetrofitInterface {

    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun accessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>

    @GET("/search/repositories?order=desc")
    fun searchRepositories(@Query("q") repoSearch: String): Single<ResponseRepositorySearch>

}
