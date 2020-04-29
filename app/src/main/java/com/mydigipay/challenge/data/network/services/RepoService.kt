package com.mydigipay.challenge.data.network.services

import com.mydigipay.challenge.data.models.Branch
import com.mydigipay.challenge.data.models.CommitDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoService {

    @GET("repos/{OWNER}/{REPO}/branches")
    suspend fun getBranches(
        @Path("OWNER") owner: String,
        @Path("REPO") repo: String
    ): List<Branch>

    @GET("repos/{OWNER}/{REPO}/commits")
    suspend fun getCommits(
        @Path("OWNER") owner: String,
        @Path("REPO") repo: String,
        @Query("sha") branch: String,
        @Query("per_page") perPage: Int = 10
    ): List<CommitDetail>
}