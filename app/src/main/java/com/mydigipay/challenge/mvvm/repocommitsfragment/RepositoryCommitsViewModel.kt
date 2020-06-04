package com.mydigipay.challenge.mvvm.repocommitsfragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.repositorycommit.ResponseRepositoryCommits
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.base.BaseViewModel

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositoryCommitsViewModel(
    private val dataManager: DataManager,
    private val schedulersProvider: SchedulersProvider
) :
    BaseViewModel<RepositoryCommitsNavigator>(dataManager, schedulersProvider) {


    val repoCommitsLiveData = MutableLiveData<MutableList<ResponseRepositoryCommits>>()
    val repositoryCommits = ObservableArrayList<ResponseRepositoryCommits>()


    fun getRepoCommits(login: String,name:String) {
        compositeDisposable.add(
            dataManager.getRepositoryCommits(login,name)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(
                    {
                        repoCommitsLiveData.value = it
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

}