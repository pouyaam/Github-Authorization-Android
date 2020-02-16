package com.mydigipay.challenge.network.oauth

import com.mydigipay.challenge.network.model.commit.CommitWrapper
import com.mydigipay.challenge.network.model.repository.Repository
import com.mydigipay.challenge.network.model.user.User
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface GithubApiService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun accessToken(@Body requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>


    @GET("/user/repos")
    fun getRepositories(): Deferred<List<Repository>>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("sha") branch: String = "master"
    ): Deferred<List<CommitWrapper>>

    @GET("/user")
    fun getUserProfile(): Deferred<User>

    @PATCH("/user")
    fun updateUserProfile(@Body userInfo: Map<String, Any>): Deferred<User>
}