package com.mydigipay.challenge.data.repository.github

import com.github.mohammadsianaki.core.extenstion.awaitResult
import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.data.api.github.GithubService
import com.mydigipay.challenge.data.toSearchItemEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource

class RemoteGithubDataSourceImpl(private val githubService: GithubService) :
    RemoteGithubDataSource {

    override suspend fun perormSearch(query: String): Result<List<SearchItemEntity>?> {
        return githubService.performSearch(query)
            .awaitResult { responseDto -> responseDto.remoteSearchItemEntities?.map { it.toSearchItemEntity() } }
    }
}