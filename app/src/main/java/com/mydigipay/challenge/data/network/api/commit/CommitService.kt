package com.mydigipay.challenge.data.network.api.commit

import com.mydigipay.challenge.data.model.CommitDetail
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE
import com.mydigipay.challenge.data.network.interceptor.AUTH_STATE_OPTIONAL
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CommitService {

    @GET("repos/{owner}/{repo}/commits")
    @Headers("$AUTH_STATE:$AUTH_STATE_OPTIONAL")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CommitDetail>

}