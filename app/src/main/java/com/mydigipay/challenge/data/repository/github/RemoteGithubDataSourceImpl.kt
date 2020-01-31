package com.mydigipay.challenge.data.repository.github

import com.github.mohammadsianaki.core.extenstion.awaitResult
import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.data.api.github.GithubService
import com.mydigipay.challenge.data.toCommitEntity
import com.mydigipay.challenge.data.toSearchItemEntity
import com.mydigipay.challenge.domain.entities.CommitEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class RemoteGithubDataSourceImpl(private val githubService: GithubService) :
    RemoteGithubDataSource {

    override suspend fun performSearch(query: String): Result<List<SearchItemEntity>?> {
        return githubService.performSearch(query)
            .awaitResult { responseDto -> responseDto.remoteSearchItemEntities?.map { it.toSearchItemEntity() } }
    }

    override suspend fun getDetails(repo: String, owner: String): Result<CommitEntity> {
        return githubService.getCommits(repo, owner).awaitResult { it.commit.toCommitEntity() }
    }
}