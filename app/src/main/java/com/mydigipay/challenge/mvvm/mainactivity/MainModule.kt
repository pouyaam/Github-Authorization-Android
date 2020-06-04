package com.mydigipay.challenge.mvvm.mainactivity

import androidx.lifecycle.ViewModelProvider
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.di.ViewModelProviderFactory
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */

@Module
class MainModule {

    @Provides
    fun provideViewModel(mainViewModel: MainViewModel): ViewModelProvider.Factory =
        ViewModelProviderFactory(mainViewModel)

    @Provides
    fun provideMainViewModel(
        dataManager: DataManager,
        schedulersProvider: SchedulersProvider
    ): MainViewModel = MainViewModel(dataManager, schedulersProvider)
}