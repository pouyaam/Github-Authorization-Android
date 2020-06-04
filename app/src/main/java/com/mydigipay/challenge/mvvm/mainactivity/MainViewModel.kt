package com.mydigipay.challenge.mvvm.mainactivity

import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.utils.Constants
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.base.BaseViewModel

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class MainViewModel(
    private val dataManager: DataManager,
    private val schedulersProvider: SchedulersProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulersProvider) {


    fun getAccessToken(code: String) {
        compositeDisposable.add(
            dataManager.accessToken(
                RequestAccessToken(
                    Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    code,
                    Constants.CLIENT_REDIRECT_URI,
                    "0"
                )
            ).subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(
                    {
                        dataManager.setToken(it.accessToken)
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }
}