package com.mydigipay.challenge.domain.interactors

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.presentation.ui.github.search.SearchViewModel.Companion.KEY_QUERY

class SearchRepositoriesUsecase(private val githubRepository: GithubRepository) :
    Usecase<Result<List<SearchItemEntity>?>>() {

    override suspend fun execute(data: Map<String, Any>?): Result<List<SearchItemEntity>?> {
        val query = data?.let { data[KEY_QUERY] as String } ?: ""
        return githubRepository.performSearch(query)
    }
}