package com.mydigipay.challenge.data.repositories.search

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.Search
import com.mydigipay.challenge.data.repositories.ApiResult

interface SearchRepository {

    suspend fun searchRepo(
        query: String,
        sort: String,
        order: String,
        page: Int
    ): ApiResult<Search<Repo>>
}

