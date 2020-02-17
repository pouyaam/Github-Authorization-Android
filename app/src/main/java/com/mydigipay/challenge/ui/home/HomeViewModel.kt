package com.mydigipay.challenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.utils.Coroutines
import com.mydigipay.challenge.utils.githubClinetId
import com.mydigipay.challenge.utils.githubClinetSecret
import com.mydigipay.challenge.utils.githubCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val homeState = MutableLiveData<HomeViewState>(HomeViewState())

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        if (githubClinetId == null || githubClinetSecret == null || githubCode == null) {
            homeState.value = homeState.value!!.copy(
                isLoading = false,
                requiredCode = true
            )
            return
        }
        Coroutines.ioThenMain({ homeRepository.getRepositories() }) {
            onExecute { homeState.value = homeState.value!!.copy(isLoading = true) }
            finally { homeState.value = homeState.value!!.copy(isLoading = false) }
            onComplete {
                homeState.value = homeState.value!!.copy(
                    isLoading = false,
                    repositories = it,
                    error = null
                )
            }
            onError {
                homeState.value = homeState.value!!.copy(
                    isLoading = false,
                    requiredCode = githubCode == null,
                    error = it
                )
            }
        }
    }


}