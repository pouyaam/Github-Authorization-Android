package com.mydigipay.challenge.ui.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.gitrepo.GitRepoRepository
import com.mydigipay.challenge.util.ktx.launch
import com.mydigipay.challenge.util.livedata.debounce

class RepositoryListViewModel(
    private val gitRepoRepository: GitRepoRepository
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitRepo>>()
    val repositories: LiveData<List<GitRepo>>
        get() = _repositories


    val query = MutableLiveData<String>()

    val onQueryChanged = query.debounce(500L)

    init {
        searchRepositories(1)
    }

    fun searchRepositories(page: Int) = launch {
        query.value?.trim().takeIf { !it.isNullOrBlank() }?.let { query ->
            val repos =
                if (page == 1)
                    mutableListOf()
                else
                    (_repositories.value ?: emptyList()).toMutableList()

            when (val result = gitRepoRepository.searchRepositories(query, page)) {
                is ApiResult.Success -> {
                    repos.addAll(result.data)
                    _repositories.postValue(repos)
                }
                is ApiResult.Error ->
                    showSnackBar(result.message)
            }
        } ?: _repositories.postValue(emptyList())
    }

}