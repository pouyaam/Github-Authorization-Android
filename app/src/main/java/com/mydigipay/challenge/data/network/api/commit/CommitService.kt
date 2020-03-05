package com.mydigipay.challenge.data.network.api.commit

import com.mydigipay.challenge.data.model.CommitDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitService {

    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<CommitDetail>

}