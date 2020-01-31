package com.mydigipay.challenge.data.api.github

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/search/repositories")
    fun performSearch(@Query("q") query: String): Call<SearchResponseDto>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(@Path("owner") owner: String, @Path("repo") repo: String): Call<CommitsReoponseDto>
}