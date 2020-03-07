package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.pojo.Commit

interface CommitRepository {

    suspend fun getCommitsRepository(
        owner: String,
        repo: String,
        page: Int,
        perPage: Int = 30
    ): ApiResult<List<Commit>>
}