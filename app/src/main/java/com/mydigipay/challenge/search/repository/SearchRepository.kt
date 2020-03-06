package com.mydigipay.challenge.search.repository

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.search.repository.model.RepositoryResponseModel

interface SearchRepository {
    suspend fun getRepositories(searchKey: String): RetrofitResult<RepositoryResponseModel>
}