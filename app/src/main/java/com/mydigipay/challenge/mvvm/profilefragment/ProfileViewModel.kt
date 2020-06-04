package com.mydigipay.challenge.mvvm.profilefragment

import androidx.databinding.ObservableField
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import com.mydigipay.challenge.mvvm.base.BaseViewModel

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class ProfileViewModel(
    private val dataManager: DataManager,
    private val schedulersProvider: SchedulersProvider
) :
    BaseViewModel<ProfileNavigator>(dataManager, schedulersProvider) {

    val name = ObservableField<String>()
    val avatar = ObservableField<String>()
    val bio = ObservableField<String>()
    val company = ObservableField<String>()
    val url = ObservableField<String>()

    fun getProfile(username:String) {
        compositeDisposable.add(
            dataManager.getUserProfile(username)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(
                    {
                        name.set(it.name)
                        avatar.set(it.avatarUrl)
                        bio.set(it.bio)
                        company.set(it.company)
                        url.set(it.url)
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

}