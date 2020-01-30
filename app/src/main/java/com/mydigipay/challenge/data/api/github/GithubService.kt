package com.mydigipay.challenge.data.api.github

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("/search/repositories")
    fun performSearch(query: String): Call<SearchResponseDto>
}