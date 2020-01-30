package com.mydigipay.challenge.presentation.ui.github.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.domain.interactors.SearchRepositoriesUsecase
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepositoriesUsecase: SearchRepositoriesUsecase) :
    ViewModel() {

    fun performSearch(query: String) {
        viewModelScope.launch {
            searchRepositoriesUsecase.execute(mapOf(KEY_QUERY to query))
        }
    }

    companion object {
        const val KEY_QUERY = "key-query"
    }
}
