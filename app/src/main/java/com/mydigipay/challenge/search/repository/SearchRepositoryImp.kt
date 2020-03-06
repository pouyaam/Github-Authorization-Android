package com.mydigipay.challenge.search.repository

import com.mydigipay.challenge.search.repository.remote.SearchService

class SearchRepositoryImp(private val searchService: SearchService) : SearchRepository {
    override suspend fun getRepositories(searchKey: String) =
        searchService.getRepositories(searchKey)
}