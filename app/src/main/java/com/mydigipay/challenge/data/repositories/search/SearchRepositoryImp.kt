package com.mydigipay.challenge.data.repositories.search

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.Search
import com.mydigipay.challenge.data.network.services.SearchService
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.fetchFromNetwork

class SearchRepositoryImp(private val searchService: SearchService) : SearchRepository {
    override suspend fun searchRepo(
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
