package com.mydigipay.challenge.data.network.services

import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @GET("repos/{OWNER}/{REPO}/branches")
    suspend fun getBranches(
        @Path("OWNER") owner: String,
        @Path("REPO") repo: String
    )

    @GET("repos/{OWNER}/{REPO}/commits/{BRANCH}")
    suspend fun getCommits(
        @Path("OWNER") owner: String,
        @Path("REPO") repo: String,
        @Path("BRANCH") branch: String?
    )
}