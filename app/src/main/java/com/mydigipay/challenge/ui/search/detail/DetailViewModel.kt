package com.mydigipay.challenge.ui.search.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.pojo.Commit
import com.mydigipay.challenge.data.pojo.Repository
import com.mydigipay.challenge.data.pojo.fillRest
import kotlinx.coroutines.launch

class DetailViewModel(private val model: DetailModel) : BaseViewModel(model) {

    private val _repository = MutableLiveData<Repository>()
    val repository: LiveData<Repository>
        get() = _repository

    private val _commits = MutableLiveData<List<Commit>>()
    val commits: LiveData<List<Commit>>
        get() = _commits

    fun setGitRepo(repository: Repository) {
        _repository.value = repository.fillRest()
        getCommits(1)
    }

    fun getCommits(page: Int) = viewModelScope.launch {
        val repository = _repository.value ?: return@launch
        val commits = (_commits.value ?: emptyList()).toMutableList()
        when (val result = model.getCommits(repository.owner.login, repository.name, page)) {
            is ApiResult.Success -> {
                commits.addAll(result.data)
                _commits.postValue(commits)
            }
            is ApiResult.Error -> {
            }

        }
    }

}