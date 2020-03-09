package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.model.*
import io.reactivex.Single
import retrofit2.http.*

interface GitApiService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(@Body requestAccessToken: RequestAccessToken): Single<ResponseAccessToken>

    @GET("/search/repositories")
    fun getProjects(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Single<ResponseProject>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("sha") branch: String = "master"
    ): Single<List<ResponseCommitItem>>


    @GET("user")
    fun getAuthenticatedUser(@Header("Authorization") token: String): Single<UserResponse>
}