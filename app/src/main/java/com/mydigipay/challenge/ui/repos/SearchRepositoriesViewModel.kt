package com.mydigipay.challenge.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import com.mydigipay.challenge.network.Order.ASC
import com.mydigipay.challenge.network.Order.DESC
import com.mydigipay.challenge.network.Sort
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchRepositoriesViewModel(val repoRepository: RepoRepository) : BaseViewModel() {

    private val _order = MutableLiveData(ASC)

    val q = MutableLiveData("")

    val query = Transformations.switchMap(q) {
        search(it)
        q
    }
    val order: LiveData<String>
        get() = _order

    init {

    }

    private val _list = MutableLiveData<MutableList<Repo>>(mutableListOf())
    val list: LiveData<MutableList<Repo>>
        get() = _list

    fun changeOrder() {
        when (order.value) {
            ASC -> _order.value = DESC
            DESC -> _order.value = ASC
        }
        reSearch()
    }

    private fun reSearch() {
        _list.value?.clear()
        q.value?.let { search(it) }
    }

    fun search(q: String) = viewModelScope.launch {

        Timber.e("Search : $q")
        val apiResult = repoRepository.search(q, Sort.STARS, ASC, 1)
        when (apiResult) {
            is ApiResult.Success -> {
                _list.value = apiResult.data.items.toMutableList()
            }
        }
    }

}
