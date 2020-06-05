package com.mydigipay.challenge.mvvm.repositorysearchfragment

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ItemRepository
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.base.BaseViewModel
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositorySearchViewModel(
    private val dataManager: DataManager,
    private val schedulersProvider: SchedulersProvider
) :
    BaseViewModel<RepositorySearchNavigator>(dataManager, schedulersProvider) {

    private val subject: PublishSubject<String> = PublishSubject.create()

    val repoLiveData = MutableLiveData<MutableList<ItemRepository>>()
    val repositories = ObservableArrayList<ItemRepository>()

    init {
        compositeDisposable.add(
            subject.debounce(2, TimeUnit.SECONDS, schedulersProvider.io())
                .subscribe {
                    apiSearchRepo(it)
                }
        )
    }

    fun searchInRepositories(query: String) {
        subject.onNext(query)
    }

    private fun apiSearchRepo(query: String) {
        compositeDisposable.add(
            dataManager.searchRepositories(query)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(
                    {
                        repoLiveData.value = it.items
                    },
                    {
                        mNavigator.get()?.showError(it.message)
                    }
                )
        )
    }
}