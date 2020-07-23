package com.mydigipay.challenge.data.datasource.remote

import com.mydigipay.challenge.domain.model.RemoteRepository
import io.reactivex.Single

interface SearchDataSource {
    fun search(query: String): Single<List<RemoteRepository>>
}