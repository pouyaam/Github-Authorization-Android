package com.mydigipay.challenge.commit.repository

import com.mydigipay.challenge.commit.repository.model.CommitResponseModel
import com.mydigipay.challenge.core.network.RetrofitResult

interface CommitRepository {
    suspend fun getCommits(owner: String, repo: String): RetrofitResult<List<CommitResponseModel>>
}