package com.mydigipay.challenge.domain.interactors

import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.presentation.ui.github.search.SearchViewModel.Companion.KEY_QUERY

class SearchRepositoriesUsecase(private val githubRepository: GithubRepository) :
    Usecase<List<Nothing>>() {

    override suspend fun execute(data: Map<String, Any>?): List<Nothing> {
        return data?.let {
            githubRepository.performSearch(data[KEY_QUERY] as String)
            listOf<Nothing>()
        } ?: run { listOf<Nothing>() }
    }
}