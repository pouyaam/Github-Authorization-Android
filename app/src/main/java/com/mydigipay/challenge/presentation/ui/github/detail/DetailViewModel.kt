package com.mydigipay.challenge.presentation.ui.github.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.github.mohammadsianaki.core.model.fold
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.interactors.GetCommitsUsecase
import com.mydigipay.challenge.presentation.core.ListViewModel
import com.mydigipay.challenge.presentation.model.CommitItemModel
import com.mydigipay.challenge.presentation.model.RepoFullNameModel
import com.mydigipay.challenge.presentation.model.toCommitItemModel
import kotlinx.coroutines.launch

class DetailViewModel(private val getCommitsUsecase: GetCommitsUsecase) :
    ListViewModel<CommitItemModel, DetailFragmentArgs>() {


    private fun getCommits(repoFullNameModel: RepoFullNameModel) {
        viewModelScope.launch {
            awaitIO {
                getCommitsUsecase.execute(mapOf(KEY_REPO_FULL_NAME to repoFullNameModel))
            }.fold(
                ifSuccess = { items ->
                    liveData.value =
                        Resource(ResourcesState.Success, items.map { it.toCommitItemModel() })
                },
                ifFailure = {
                    it?.let {
                        liveData.value = Resource(ResourcesState.Failure, null, it)
                        Log.d("<<<SearchViewModel>>>", "status = failure with message=$it")
                    }
                }
            )
        }
    }

    companion object {
        const val KEY_REPO_FULL_NAME = "key-arg"
    }

    override fun makeData(params: DetailFragmentArgs) {
        getCommits(RepoFullNameModel(params.owner, params.repo))
    }
}
