package com.mydigipay.challenge.commit.repository

import com.mydigipay.challenge.commit.repository.remote.CommitService

class CommitRepositoryImp(private val commitService: CommitService) : CommitRepository {
    override suspend fun getCommits(owner: String, repo: String) =
        commitService.getCommits(owner, repo)
}