package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    fun searchRepository(query: String): Single<List<RemoteRepository>> {
        return searchRepository.search(query)
    }
}