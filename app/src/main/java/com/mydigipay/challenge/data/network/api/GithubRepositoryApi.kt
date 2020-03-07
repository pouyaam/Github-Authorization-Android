package com.mydigipay.challenge.data.network.api

import com.mydigipay.challenge.data.network.res.RepositorySearchRes
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepositoryApi {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): RepositorySearchRes

}