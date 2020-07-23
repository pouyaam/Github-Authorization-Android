package com.mydigipay.challenge.datasource.remote

import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.SearchDataSource
import com.mydigipay.challenge.data.model.RemoteRepositorySearchResponse
import com.mydigipay.challenge.data.model.mapToDomainModel
import com.mydigipay.challenge.domain.model.RemoteRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    SearchDataSource {
    override fun search(query: String): Single<List<RemoteRepository>> {
        return apiService.performSearch(query).flatMap {
            return@flatMap Single.just(
                it.remoteSearchItemEntities.map {
                    it.mapToDomainModel()
                }
            )
        }
    }
}