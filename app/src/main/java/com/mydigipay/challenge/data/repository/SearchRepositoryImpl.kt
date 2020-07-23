package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchDataSource: GithubDataSource) :
    SearchRepository {
    override fun search(query: String): Single<List<RemoteRepository>> {
        return searchDataSource.search(query)
    }
}