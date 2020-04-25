package com.mydigipay.challenge.network.services

import retrofit2.http.Query

interface SearchService {

    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int
    )
}