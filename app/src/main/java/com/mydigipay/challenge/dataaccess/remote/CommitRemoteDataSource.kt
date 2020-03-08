package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.CommitDataSource

class CommitRemoteDataSource(
    private val api: GitApiService
) : CommitDataSource {
    override fun getAll(owner: String, repo: String) = api.getCommits(owner, repo)
}