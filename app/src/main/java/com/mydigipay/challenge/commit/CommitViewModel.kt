package com.mydigipay.challenge.commit

import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.commit.repository.CommitRepository
import com.mydigipay.challenge.commit.repository.model.CommitResponseModel
import com.mydigipay.challenge.core.SingleLiveEvent
import com.mydigipay.challenge.core.base.BaseViewModel
import com.mydigipay.challenge.core.network.RetrofitResult
import kotlinx.coroutines.launch

class CommitViewModel(private val commitRepository: CommitRepository) : BaseViewModel() {
    val commits = SingleLiveEvent<List<CommitResponseModel>>()
    private var isLoading = false

    fun getCommits(owner: String, repo: String) {
        if (commits.value == null && !isLoading) {
            showProgressBar.call()
            isLoading = true

            viewModelScope.launch {
                val result = commitRepository.getCommits(owner, repo)
                hideProgressBar.call()

                if (result is RetrofitResult.Success) {
                    commits.value = result.data
                } else if (result is RetrofitResult.Error) {
                    mError.value = result.e.message
                }

                isLoading = false
            }
        }
    }
}