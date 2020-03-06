package com.mydigipay.challenge.search

import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.core.SingleLiveEvent
import com.mydigipay.challenge.core.base.BaseViewModel
import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.search.repository.SearchRepository
import com.mydigipay.challenge.search.repository.model.RepositoryModel
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {
    val repositories = SingleLiveEvent<List<RepositoryModel>>()

    fun getRepositories(searchKey: String) {
        showProgressBar.call()

        viewModelScope.launch {
            val result = searchRepository.getRepositories(searchKey)
            hideProgressBar.call()

            if (result is RetrofitResult.Success) {
                repositories.value = result.data?.items
            } else if (result is RetrofitResult.Error) {
                mError.value = result.e.message
            }
        }
    }
}