package com.mydigipay.challenge.data.network.api.commit

import com.mydigipay.challenge.data.model.CommitDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommitService {

    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CommitDetail>

}