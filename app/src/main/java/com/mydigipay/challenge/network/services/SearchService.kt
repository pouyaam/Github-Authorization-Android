package com.mydigipay.challenge.network.services

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.Search
import retrofit2.http.Query

interface SearchService {

    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Search<Repo>
}