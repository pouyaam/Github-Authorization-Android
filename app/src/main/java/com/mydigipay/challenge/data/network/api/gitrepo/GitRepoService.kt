package com.mydigipay.challenge.data.network.api.gitrepo

import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): ResponseSearchRepository

}