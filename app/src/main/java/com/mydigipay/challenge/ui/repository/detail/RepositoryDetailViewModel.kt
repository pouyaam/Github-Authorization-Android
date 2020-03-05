package com.mydigipay.challenge.ui.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.GitRepo
import com.mydigipay.challenge.data.model.fillRest

class RepositoryDetailViewModel : BaseViewModel() {

    private val _gitRepo = MutableLiveData<GitRepo>()
    val gitRepo: LiveData<GitRepo>
        get() = _gitRepo

    fun setGitRepo(gitRepo: GitRepo) {
        _gitRepo.postValue(gitRepo.fillRest())
    }
}