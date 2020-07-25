package com.mydigipay.challenge.presentation.github.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.SearchUseCase
import com.mydigipay.challenge.domain.usecase.UserUseCase
import com.mydigipay.challenge.presentation.model.RepositoryItem
import com.mydigipay.challenge.presentation.model.UserItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val state: BehaviorRelay<SearchFragmentState> = BehaviorRelay.create()
    private val unprocessableEntity = "422"

    fun getState() = state.hide()


    fun searchRepository(query: String) {
        state.accept(SearchFragmentState.Loading)
        searchUseCase.searchRepository(query)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.isEmpty()) {
                    state.accept(SearchFragmentState.NoRepoFound)
                } else {
                    state.accept(
                        SearchFragmentState.SearchedRepository(
                            it.map {
                                it.mapToPresentationModel()
                            })
                    )
                }
            }, {
                if (it.message != null && it.message.toString().contains(unprocessableEntity)) {
                    state.accept(SearchFragmentState.EmptyQuery)
                } else {
                    state.accept(SearchFragmentState.Error)
                }
            }).let {
                compositeDisposable.add(it)
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
    object Error : SearchFragmentState()
    object NoRepoFound : SearchFragmentState()
    object EmptyQuery : SearchFragmentState()
}