package com.mydigipay.challenge.data.repository.github

import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class GithubRepositoryImpl(private val remoteGithubDataSource: RemoteGithubDataSource) :
    GithubRepository {

    override suspend fun performSearch(query: String) {
        return remoteGithubDataSource.perormSearch(query)
    }
}