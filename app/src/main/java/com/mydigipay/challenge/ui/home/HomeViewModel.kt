package com.mydigipay.challenge.ui.home

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mydigipay.challenge.base.RequiredCodeException

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    var code: String? = null
        set(value) {
            if (value != null) {
                field = value
                Log.i("HomeViewModel", "Code:$value")
            }
        }

    init {
        code = homeRepository.code
    }

    private val viewState = HomeViewState()

    val data by lazy {
        Transformations.map(
            liveData {
                code ?: run {
                    emit(RepositoryResult.Failure(RequiredCodeException()))
                    return@liveData
                }
                emit(RepositoryResult.Loading)
                emit(homeRepository.getRepositories(code!!))
            }
        ) {
            handleResult(it)
        }
    }


    private fun handleResult(it: RepositoryResult): HomeViewState {
        return when (it) {
            is RepositoryResult.Loading -> viewState.copy(isLoading = true)
            is RepositoryResult.Success -> viewState.copy(
                isLoading = false,
                repositories = it.data,
                error = null
            )
            is RepositoryResult.Failure -> viewState.copy(
                isLoading = false,
                requiredCode = homeRepository.code == null,
                error = it.error
            )
        }
    }


}