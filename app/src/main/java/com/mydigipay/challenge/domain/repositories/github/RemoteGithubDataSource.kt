package com.mydigipay.challenge.domain.repositories.github

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.ItemEntity

interface RemoteGithubDataSource {
    suspend fun perormSearch(query: String): Result<List<ItemEntity>?>
}