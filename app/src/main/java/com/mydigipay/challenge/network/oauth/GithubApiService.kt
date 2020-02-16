package com.mydigipay.challenge.network.oauth

import com.mydigipay.challenge.network.repository.CommitWrapper
import com.mydigipay.challenge.network.repository.Repository
import com.mydigipay.challenge.network.repository.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface GithubApiService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun accessToken(@Body requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>


    @GET("/user/repos")
    fun getRepositories(): Call<List<Repository>>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("sha") branch: String = "master"
    ): Call<List<CommitWrapper>>
}