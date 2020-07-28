package com.mydigipay.challenge.framework.network

import com.mydigipay.challenge.framework.network.response.GithubRepoResponse
import retrofit2.Response
import retrofit2.http.*

interface RemoteGithubRepoService {

    @Headers("Accept:application/json")
    @GET("repositories")
    suspend fun fetchGithubRepos(
        @Query("since") since: Int,
        @Header("Authorization") token: String
    ): Response<List<GithubRepoResponse>>
}
