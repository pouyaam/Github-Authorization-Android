package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.CommitsApi
import com.mydigipay.challenge.data.network.safeApiCall

class CommitRepositoryImpl(private val commitApi: CommitsApi) :
    CommitRepository {

    override suspend fun getCommitsRepository(
        owner: String,
        repo: String,
        page: Int,
        perPage: Int
    ) = safeApiCall {
        return@safeApiCall ApiResult.Success(
            commitApi.getCommits(
                owner,
                repo,
                page,
                perPage
            )
        )
    }
}