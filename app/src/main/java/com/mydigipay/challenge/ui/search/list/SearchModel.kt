package com.mydigipay.challenge.ui.search.list

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.SearchRepository

class SearchModel(private val searchRepository: SearchRepository) : BaseModel() {

    suspend fun searchRepository(
        query: String,
        page: Int
    ) = searchRepository.searchRepositories(query, page)

}