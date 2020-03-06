package com.mydigipay.challenge.search.repository.remote

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.search.repository.model.RepositoryResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/search/repositories")
    suspend fun getRepositories(@Query("q") searchKey: String): RetrofitResult<RepositoryResponseModel>
}