package com.mydigipay.challenge.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Branch
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel(val repoRepository: RepoRepository) : BaseViewModel() {

    val repo = MutableLiveData<Repo?>()
    private val _branches = MutableLiveData(listOf<Branch>())
    val branch: MutableLiveData<List<Branch>>
        get() = _branches

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean>
        get() = _inProgress

    fun loadCommits(branch: Branch?) {
        branch?.let {

        }
    }

    fun loadBranches(repo: Repo) {
        viewModelScope.launch(Dispatchers.IO) {
            _inProgress.postValue(true)

            val result = repoRepository.getBranches(repo)

            when (result) {
                is ApiResult.Success -> _branches.postValue(result.data)
                is ApiResult.Error -> {
                    // TODO: 4/28/20 Display error
                }
            }
            _inProgress.postValue(false)
        }
    }

}
