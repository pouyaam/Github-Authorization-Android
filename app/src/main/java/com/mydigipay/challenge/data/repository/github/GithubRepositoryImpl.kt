package com.mydigipay.challenge.data.repository.github

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.ItemEntity
import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class GithubRepositoryImpl(private val remoteGithubDataSource: RemoteGithubDataSource) :
    GithubRepository {

    override suspend fun performSearch(query: String): Result<List<ItemEntity>?> {
        return remoteGithubDataSource.perormSearch(query)
    }
}