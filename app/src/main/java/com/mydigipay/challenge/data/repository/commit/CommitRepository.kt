package com.mydigipay.challenge.data.repository.commit

import com.mydigipay.challenge.data.model.CommitDetail
import com.mydigipay.challenge.data.network.ApiResult

interface CommitRepository {

    suspend fun getCommits(
        owner: String,
        repo: String,
        page: Int,
        perPage: Int = 30
    ): ApiResult<List<CommitDetail>>
}