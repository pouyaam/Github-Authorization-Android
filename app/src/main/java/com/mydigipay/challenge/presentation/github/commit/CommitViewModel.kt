package com.mydigipay.challenge.presentation.github.commit

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.CommitUseCase
import com.mydigipay.challenge.presentation.model.CommitItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommitViewModel @Inject constructor(private val commitUseCase: CommitUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val state: BehaviorRelay<CommitFragmentState> = BehaviorRelay.create()

    fun getState() = state.hide()

    fun getCommits(owner: String, repo: String) {
        state.accept(CommitFragmentState.Loading)
        commitUseCase.getCommits(owner, repo)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if(it.isEmpty()){
                    state.accept(CommitFragmentState.NoCommits)
                }else{
                    state.accept(CommitFragmentState.GotCommits(it.map { it.mapToPresentationModel() }))
                }
            }, {
                state.accept(CommitFragmentState.Error)
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class CommitFragmentState {
    object Error : CommitFragmentState()
    object Loading : CommitFragmentState()
    object NoCommits : CommitFragmentState()
    data class GotCommits(val commits: List<CommitItem>) : CommitFragmentState()
}