package com.mydigipay.challenge.datasource.remote

import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.data.model.commit.mapToDomainModel
import com.mydigipay.challenge.data.model.search.mapToDomainModel
import com.mydigipay.challenge.domain.model.Commit
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

    override fun getCommits(owner: String, repo: String): Single<List<Commit>> {
        return apiService.getCommits(owner, repo).map {
            it.map {
                it.mapToDomainModel()
            }
        }
    }

}