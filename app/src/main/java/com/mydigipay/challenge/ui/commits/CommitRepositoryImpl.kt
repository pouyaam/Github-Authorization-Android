package com.mydigipay.challenge.ui.commits

import com.mydigipay.challenge.network.oauth.GithubApiService

class CommitRepositoryImpl(private val githubApiService: GithubApiService) : CommitRepository {

    override suspend fun getCommits(owner: String, repo: String) =
        githubApiService.getCommits(owner, repo).await()

}