package com.mydigipay.challenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.utils.Coroutines
import com.mydigipay.challenge.utils.githubCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {


    private val viewState = HomeViewState()
    val homeState = MutableLiveData<HomeViewState>(viewState)

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        Coroutines.ioThenMain({ homeRepository.getRepositories() }) {
            onExecute { homeState.value = viewState.copy(isLoading = true) }
            finally { homeState.value = viewState.copy(isLoading = false) }
            onComplete {
                homeState.value = viewState.copy(
                    isLoading = false,
                    repositories = it,
                    error = null
                )
            }
            onError {
                homeState.value = viewState.copy(
                    isLoading = false,
                    requiredCode = githubCode == null,
                    error = it
                )
            }
        }
    }


}