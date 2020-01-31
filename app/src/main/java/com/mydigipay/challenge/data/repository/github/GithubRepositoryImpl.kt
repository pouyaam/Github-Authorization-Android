package com.mydigipay.challenge.data.repository.github

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.CommitEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class GithubRepositoryImpl(private val remoteGithubDataSource: RemoteGithubDataSource) :
    GithubRepository {

    override suspend fun performSearch(query: String): Result<List<SearchItemEntity>?> {
        return remoteGithubDataSource.performSearch(query)
    }

    override suspend fun getCommitsForRepo(owner: String, repo: String): Result<CommitEntity> {
        return remoteGithubDataSource.getDetails(repo, owner)
    }
}