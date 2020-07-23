package com.mydigipay.challenge.presentation.github

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.usecase.SearchUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(private val useCase: SearchUseCase) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun searchRepository(query: String) {
        useCase.saerchRepository(query).observeOn(Schedulers.io())
            .subscribe({
                Log.i("", "")
            }, {
                Log.i("", "")

            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}