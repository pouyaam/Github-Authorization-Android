package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.pojo.fillRest
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.GithubRepositoryApi
import com.mydigipay.challenge.data.pojo.Order
import com.mydigipay.challenge.data.pojo.Sort
import com.mydigipay.challenge.data.network.safeApiCall

class SearchRepositoryImpl(
    private val gitRepoService: GithubRepositoryApi
) : SearchRepository {

    override suspend fun searchRepositories(
        query: String,
        page: Int,
        perPage: Int,
        sort: Sort,
        order: Order
    ) = safeApiCall {
        val result = gitRepoService.searchRepositories(
            query,
            page,
            perPage,
            sort.toString(),
            order.toString()
        )
        return@safeApiCall ApiResult.Success(result.gitRepos.map { it.fillRest() })
    }
}