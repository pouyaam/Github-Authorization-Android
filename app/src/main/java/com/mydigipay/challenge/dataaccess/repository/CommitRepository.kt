package com.mydigipay.challenge.dataaccess.repository

import com.mydigipay.challenge.dataaccess.CommitDataSource

class CommitRepository(
    private val remote: CommitDataSource
) : CommitDataSource {
    override fun getAll(owner: String, repo: String) = remote.getAll(owner, repo)
}