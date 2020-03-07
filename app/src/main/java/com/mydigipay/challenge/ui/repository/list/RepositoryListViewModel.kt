package com.mydigipay.challenge.ui.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.error.ErrorType
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

    // First parameter : a request failed due to "No Internet" and we need to request again
    // Second parameter: page that failed if any
    var networkPark: Pair<Boolean, Int?> = false to null

    fun searchRepositories(page: Int) = launch {
        query.value?.trim().takeIf { !it.isNullOrBlank() }?.let { query ->
            isLoading.postValue(true)
            networkPark = false to null
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
                    if (result.error.type == ErrorType.NETWORK) networkPark = true to page
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