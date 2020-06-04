package com.mydigipay.challenge.mvvm.profilefragment

import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
@Module
class ProfileModule {

    @Provides
    fun provideViewModel(
        dataManager: DataManager,
        schedulersProvider: SchedulersProvider
    ): ProfileViewModel =
        ProfileViewModel(dataManager,schedulersProvider)
}