package com.mydigipay.challenge.data.repository.gitrepo

import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.gitrepo.Order
import com.mydigipay.challenge.data.network.api.gitrepo.Sort

interface GitRepoRepository {

    suspend fun searchRepositories(
        query: String,
        page: Int,
        sort: Sort = Sort.STARS,
        order: Order = Order.DESC
    ): ApiResult<List<GitRepo>>
}