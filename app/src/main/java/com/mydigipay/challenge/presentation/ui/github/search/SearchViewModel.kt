package com.mydigipay.challenge.presentation.ui.github.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.github.mohammadsianaki.core.model.fold
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.interactors.SearchRepositoriesUsecase
import com.mydigipay.challenge.presentation.model.SearchItemModel
import com.mydigipay.challenge.presentation.model.toSearchItemModel
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepositoriesUsecase: SearchRepositoriesUsecase) :
    ViewModel() {

    private val liveData: MutableLiveData<Resource<List<SearchItemModel>>> = MutableLiveData()

    fun performSearch(query: String) {
        liveData.value = Resource(ResourcesState.Loading)
        viewModelScope.launch {
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

    fun getLiveData(): LiveData<Resource<List<SearchItemModel>>> = liveData

    companion object {
        const val KEY_QUERY = "key-query"
    }
}
