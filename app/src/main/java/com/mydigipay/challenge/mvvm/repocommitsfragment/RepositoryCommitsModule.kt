package com.mydigipay.challenge.mvvm.repocommitsfragment

import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.repocommitsfragment.adapter.CommitAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */

@Module
class RepositoryCommitsModule {

    @Provides
    fun provideViewModel(
        dataManager: DataManager,
        schedulersProvider: SchedulersProvider
    ): RepositoryCommitsViewModel =
        RepositoryCommitsViewModel(dataManager, schedulersProvider)

    @Provides
    fun provideAdapter(): CommitAdapter = CommitAdapter(mutableListOf())
}