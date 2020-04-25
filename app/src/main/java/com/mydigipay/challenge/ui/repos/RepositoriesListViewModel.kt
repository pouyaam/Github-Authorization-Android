package com.mydigipay.challenge.ui.repos

import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.repositories.repo.RepoRepository

class RepositoriesListViewModel(val repoRepository: RepoRepository) : BaseViewModel() {

    val list = MutableLiveData<List<Repo>>(mutableListOf())
    val message = MutableLiveData<String>()

//    fun search(q: String) = viewModelScope.launch {
//        val apiResult = repoRepository.search(q, Sort.STARS, Order.ASC, 1)
//        when (apiResult) {
//            is ApiResult.Success -> {
//                list.value = apiResult.data.items
//            }
//        }
//    }

}
