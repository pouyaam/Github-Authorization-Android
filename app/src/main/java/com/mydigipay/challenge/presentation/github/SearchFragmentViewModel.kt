package com.mydigipay.challenge.presentation.github

import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.SearchUseCase
import com.mydigipay.challenge.presentation.model.RepositoryItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(private val useCase: SearchUseCase) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val state: BehaviorRelay<SearchFragmentState> = BehaviorRelay.create()
    fun getState() = state.hide()

    fun searchRepository(query: String) {
        if (query.isNotBlank()) {
            state.accept(SearchFragmentState.Loading)
            useCase.saerchRepository(query).subscribeOn(Schedulers.io())
                .subscribe({
                    state.accept(SearchFragmentState.SearchedRepository(it.map {
                        it.mapToPresentationModel()
                    }))
                }, {
                    state.accept(SearchFragmentState.Error(it.message))
                }).let {
                    compositeDisposable.add(it)
                }
        } else {
            state.accept(SearchFragmentState.SearchedRepository(listOf()))
        }


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class SearchFragmentState() {
    data class SearchedRepository(val repositories: List<RepositoryItem>) : SearchFragmentState()
    object Loading : SearchFragmentState()
    data class Error(val message: String?) : SearchFragmentState()
}