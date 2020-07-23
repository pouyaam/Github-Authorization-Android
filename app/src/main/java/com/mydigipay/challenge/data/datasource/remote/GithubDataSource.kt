package com.mydigipay.challenge.data.datasource.remote

import com.mydigipay.challenge.domain.model.RemoteRepository
import io.reactivex.Single

interface GithubDataSource {
    fun search(query: String): Single<List<RemoteRepository>>
}