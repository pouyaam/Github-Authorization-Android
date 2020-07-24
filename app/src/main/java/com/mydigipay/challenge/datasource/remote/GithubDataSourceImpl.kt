package com.mydigipay.challenge.datasource.remote

import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.data.model.UserEntity
import com.mydigipay.challenge.data.model.mapToDomainModel
import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.model.User
import io.reactivex.Single
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    GithubDataSource {
    override fun search(query: String): Single<List<RemoteRepository>> {
        return apiService.performSearch(query).flatMap {
            return@flatMap Single.just(
                it.remoteSearchItemEntities.map {
                    it.mapToDomainModel()
                }
            )
        }
    }

    override fun getUser(): Single<User> {
        return apiService.getUser().map {
            it.mapToDomainModel()
        }
    }
}