package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.model.User
import com.mydigipay.challenge.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val githubDataSource: GithubDataSource) :
    GithubRepository {
    override fun search(query: String): Single<List<RemoteRepository>> {
        return githubDataSource.search(query)
    }

    override fun getUser(): Single<User> {
        return githubDataSource.getUser()
    }
}