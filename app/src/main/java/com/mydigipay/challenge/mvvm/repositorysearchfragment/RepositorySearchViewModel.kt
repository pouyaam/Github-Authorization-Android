package com.mydigipay.challenge.mvvm.repositorysearchfragment

import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.base.BaseViewModel

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositorySearchViewModel(dataManager: DataManager, schedulersProvider: SchedulersProvider) :
    BaseViewModel<RepositorySearchNavigator>(dataManager, schedulersProvider) {
}