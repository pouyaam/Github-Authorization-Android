package com.mydigipay.challenge.ui.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.gitrepo.GitRepoRepository
import com.mydigipay.challenge.util.ktx.launch
import com.mydigipay.challenge.util.livedata.StateLessEvent
import com.mydigipay.challenge.util.livedata.combine
import com.mydigipay.challenge.util.livedata.debounceEvent

class RepositoryListViewModel(
    private val gitRepoRepository: GitRepoRepository
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitRepo>>()
    val repositories: LiveData<List<GitRepo>>
        get() = _repositories

    private val isLoading = MutableLiveData<Boolean>(false)

    val isFirstDataLoading = combine(_repositories, isLoading) { repositories, isLoading ->
        repositories.isNullOrEmpty() && isLoading == true
    }

    val isDataEmpty = combine(_repositories, isLoading) { repositories, isLoading ->
        repositories.isNullOrEmpty() && isLoading == false
    }

    val query = MutableLiveData<String>()

    val onQueryChanged = query.debounceEvent(500L)

    private val _onResetList = MutableLiveData<StateLessEvent>()
    val onResetList: LiveData<StateLessEvent>
        get() = _onResetList

    private val _onFetchDataFailed = MutableLiveData<StateLessEvent>()
    val onFetchDataFailed: LiveData<StateLessEvent>
        get() = _onFetchDataFailed

    init {
        searchRepositories(1)
    }

    fun searchRepositories(page: Int) = launch {
        query.value?.trim().takeIf { !it.isNullOrBlank() }?.let { query ->
            isLoading.postValue(true)
            val repos =
                if (page == 1)
                    mutableListOf()
                else
                    (_repositories.value ?: emptyList()).toMutableList()

            when (val result = gitRepoRepository.searchRepositories(query, page)) {
                is ApiResult.Success -> {
                    repos.addAll(result.data)
                    _repositories.postValue(repos)
                    if (page == 1) _onResetList.postValue(StateLessEvent())
                }
                is ApiResult.Error -> {
                    showSnackBar(result.error.message)
                    _onFetchDataFailed.postValue(StateLessEvent())
                }
            }
            isLoading.postValue(false)
        } ?: {
            _repositories.postValue(emptyList())
            _onResetList.postValue(StateLessEvent())
        }()
    }

    fun openRepoDetail(gitRepo: GitRepo) {
        navigateTo(RepositoryListFragmentDirections.actionRepositoriesToRepository(gitRepo))
    }

}