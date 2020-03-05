package com.mydigipay.challenge.data.repository.commit

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.commit.CommitService
import com.mydigipay.challenge.data.network.safeApiCall

class CommitRepositoryImpl(
    private val commitService: CommitService
) : CommitRepository {

    override suspend fun getCommits(
       owner: String,
       repo: String,
       page: Int,
       perPage: Int
    ) = safeApiCall {
        return@safeApiCall ApiResult.Success(
            commitService.getCommits(
                owner,
                repo,
                page,
                perPage
            )
        )
    }

}