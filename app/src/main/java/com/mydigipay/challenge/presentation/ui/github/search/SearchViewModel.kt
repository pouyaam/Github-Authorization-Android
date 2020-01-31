package com.mydigipay.challenge.presentation.ui.github.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.github.mohammadsianaki.core.model.fold
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.interactors.SearchRepositoriesUsecase
import com.mydigipay.challenge.presentation.core.ListViewModel
import com.mydigipay.challenge.presentation.model.SearchItemModel
import com.mydigipay.challenge.presentation.model.toSearchItemModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepositoriesUsecase: SearchRepositoriesUsecase
) : ListViewModel<SearchItemModel, String>() {

    private fun performSearch(query: String) {
        viewModelScope.launch {
            liveData.value = Resource(ResourcesState.Loading)
            awaitIO {
                searchRepositoriesUsecase.execute(mapOf(KEY_QUERY to query))
            }.fold(
                ifSuccess = { items ->
                    items?.let {
                        liveData.value =
                            Resource(ResourcesState.Success, items.map { it.toSearchItemModel() })
                    }
                },
                ifFailure = {
                    it?.let {
                        liveData.value = Resource(ResourcesState.Failure, null, it)
                        Log.d("<<<SearchViewModel>>>", "status = failure with message=$it")
                    }
                }
            )
        }
    }
    companion object {
        const val KEY_QUERY = "key-query"
    }

    override fun makeData(params: String) {
        performSearch(params)
    }
}
