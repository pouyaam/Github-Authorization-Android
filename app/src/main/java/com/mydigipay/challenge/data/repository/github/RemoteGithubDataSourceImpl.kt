package com.mydigipay.challenge.data.repository.github

import com.github.mohammadsianaki.core.extenstion.awaitResult
import com.mydigipay.challenge.data.api.github.GithubService
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class RemoteGithubDataSourceImpl(private val githubService: GithubService) :
    RemoteGithubDataSource {

    override suspend fun perormSearch(query: String) {
        return githubService.performSearch(query)
            .awaitResult { responseDto -> responseDto.remoteItemEntities.map { it. } }
    }
}