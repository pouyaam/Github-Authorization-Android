package com.mydigipay.challenge.ui.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.CommitDetail
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.model.fillRest
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.commit.CommitRepository
import com.mydigipay.challenge.util.ktx.launch

class RepositoryDetailViewModel(
    private val commitRepository: CommitRepository
) : BaseViewModel() {

    private val _gitRepo = MutableLiveData<GitRepo>()
    val gitRepo: LiveData<GitRepo>
        get() = _gitRepo

    private val _commits = MutableLiveData<List<CommitDetail>>()
    val commits: LiveData<List<CommitDetail>>
        get() = _commits

    fun setGitRepo(gitRepo: GitRepo) {
        _gitRepo.value = gitRepo.fillRest()
        getCommits(1)
    }

    fun getCommits(page: Int) = launch {
        val gitRepo = _gitRepo.value ?: return@launch
        val commits = (_commits.value ?: emptyList()).toMutableList()
        when (val result = commitRepository.getCommits(gitRepo.owner.login, gitRepo.name, page)) {
            is ApiResult.Success -> {
                commits.addAll(result.data)
                _commits.postValue(commits)
            }
            is ApiResult.Error ->
                showSnackBar(result.error.message)
        }
    }
}