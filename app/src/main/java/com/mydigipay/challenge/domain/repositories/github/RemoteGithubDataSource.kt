package com.mydigipay.challenge.domain.repositories.github

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.CommitEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity

interface RemoteGithubDataSource {
    suspend fun performSearch(query: String): Result<List<SearchItemEntity>?>

    suspend fun getDetails(repo: String, owner: String): Result<List<CommitEntity>>
}