package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.repository.GithubRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    fun searchRepository(query: String): Single<List<RemoteRepository>> {
        return githubRepository.search(query)
    }
}