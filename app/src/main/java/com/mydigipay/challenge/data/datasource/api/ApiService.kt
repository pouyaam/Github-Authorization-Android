package com.mydigipay.challenge.data.datasource.api

import com.mydigipay.challenge.data.model.commit.CommitResponseEntity
import com.mydigipay.challenge.data.model.search.SearchResponse
import com.mydigipay.challenge.data.model.user.UserEntity
import com.mydigipay.challenge.data.model.token.RequestAccessToken
import com.mydigipay.challenge.data.model.token.ResponseAccessToken
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>

    @GET("/search/repositories")
    fun performSearch(@Query("q") query: String): Single<SearchResponse>

    @GET("/user")
    fun getUser(): Single<UserEntity>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("sha") branch: String = "master"
    ): Single<List<CommitResponseEntity>>
}