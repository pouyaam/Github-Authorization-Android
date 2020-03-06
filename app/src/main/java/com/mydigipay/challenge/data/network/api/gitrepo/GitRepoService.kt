package com.mydigipay.challenge.data.network.api.gitrepo

import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE_OPTIONAL
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitRepoService {

    @GET("search/repositories")
    @Headers("$AUTH_STATE:$AUTH_STATE_OPTIONAL")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): ResponseSearchRepository

}