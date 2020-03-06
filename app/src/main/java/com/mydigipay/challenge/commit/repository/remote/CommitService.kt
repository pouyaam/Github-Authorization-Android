package com.mydigipay.challenge.commit.repository.remote

import com.mydigipay.challenge.commit.repository.model.CommitResponseModel
import com.mydigipay.challenge.core.network.RetrofitResult
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitService {
    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RetrofitResult<List<CommitResponseModel>>
}