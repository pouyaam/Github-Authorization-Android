package com.mydigipay.challenge.ui.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.gitrepo.GitRepoRepository
import com.mydigipay.challenge.util.ktx.launch

class RepositoryListViewModel(
    private val gitRepoRepository: GitRepoRepository
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitRepo>>()
    val repositories: LiveData<List<GitRepo>>
        get() = _repositories


    val query = MutableLiveData<String>("toast")

    init {
        searchRepositories(1)
    }

    fun searchRepositories(page: Int) = launch {
        val repos =
            if (page == 1)
                mutableListOf()
            else
                (_repositories.value ?: emptyList()).toMutableList()
        val query = query.value ?: ""
        when (val result = gitRepoRepository.searchRepositories(query, page)) {
            is ApiResult.Success -> {
                repos.addAll(result.data)
                _repositories.postValue(repos)
            }
            is ApiResult.Error ->
                showSnackBar(result.message)
        }
    }

}