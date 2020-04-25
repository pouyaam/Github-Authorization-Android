package com.mydigipay.challenge.data.repositories.repo

import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.Search
import com.mydigipay.challenge.data.repositories.ApiResult

interface RepoRepository {

    suspend fun search(
        query: String,
        sort: String,
        order: String,
        page: Int
    ): ApiResult<Search<Repo>>
}

