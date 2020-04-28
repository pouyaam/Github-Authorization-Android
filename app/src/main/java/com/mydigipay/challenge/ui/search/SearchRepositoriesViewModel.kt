package com.mydigipay.challenge.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.network.Order.ASC
import com.mydigipay.challenge.data.network.Order.DESC
import com.mydigipay.challenge.data.network.Sort
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(private val repoRepository: RepoRepository) : BaseViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _order = MutableLiveData(ASC)
    val order: LiveData<String>
        get() = _order

    val query = MutableLiveData("")

    private val _list = MutableLiveData<MutableList<Repo>>(mutableListOf())
    val list: LiveData<MutableList<Repo>>
        get() = _list

    private val _sort = MutableLiveData(Sort.STARS)
    val sort: LiveData<String>
        get() = _sort

    fun changeOrder() {
        when (order.value) {
            ASC -> _order.value = DESC
            DESC -> _order.value = ASC
        }
        reSearch()
    }

    fun reSearch() {
        _list.postValue(mutableListOf())
        query.value?.let { search(1) }
    }

    fun search(page: Int) = viewModelScope.launch {
        query.value
            .takeIf { !it.isNullOrEmpty() }?.let { keyword ->

                _loading.postValue(true)
                val result = repoRepository.search(
                    keyword,
                    _sort.value!!,
                    _order.value!!,
                    page
                )

                when (result) {
                    is ApiResult.Success -> {
                        val currentList: MutableList<Repo> =
                            (if (list.value != null) list.value else mutableListOf()) as MutableList<Repo>
                        currentList.addAll(result.data.items)
                        _list.value = currentList
                    }
                    is ApiResult.Error -> {
                        // TODO: 4/27/20 Display Error
                    }
                }
                _loading.postValue(false)
            }
    }

}
