package com.mydigipay.challenge.domain.repository

import com.mydigipay.challenge.domain.model.RemoteRepository
import io.reactivex.Single

interface SearchRepository {
    fun search(query: String): Single<List<RemoteRepository>>
}