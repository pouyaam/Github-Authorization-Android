package com.mydigipay.challenge.data.repositories.repo

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.Search
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.fetchFromNetwork
import com.mydigipay.challenge.network.services.SearchService

class RepoRepositoryImp(val searchService: SearchService) : RepoRepository {
    override suspend fun search(
        query: String,
        sort: String,
        order: String,
        page: Int
    ): ApiResult<Search<Repo>> = fetchFromNetwork({
        searchService.searchRepo(
            query,
            sort,
            order,
            page
        )
    })
}
