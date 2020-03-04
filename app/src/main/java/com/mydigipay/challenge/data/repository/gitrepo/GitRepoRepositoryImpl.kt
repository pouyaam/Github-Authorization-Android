package com.mydigipay.challenge.data.repository.gitrepo

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.gitrepo.GitRepoService
import com.mydigipay.challenge.data.network.api.gitrepo.Order
import com.mydigipay.challenge.data.network.api.gitrepo.Sort
import com.mydigipay.challenge.data.network.safeApiCall

class GitRepoRepositoryImpl(
    private val gitRepoService: GitRepoService
) : GitRepoRepository {

    override suspend fun searchRepositories(
        query: String,
        page: Int,
        sort: Sort,
        order: Order
    ) = safeApiCall {
        val result = gitRepoService.searchRepositories(
            query,
            page,
            sort.toString(),
            order.toString()
        )
        return@safeApiCall ApiResult.Success(result.gitRepos)
    }

}