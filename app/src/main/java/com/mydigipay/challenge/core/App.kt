package com.mydigipay.challenge.core

import androidx.multidex.MultiDexApplication
import com.mydigipay.challenge.di.appModule
import com.mydigipay.challenge.di.dataAccessModule
import com.mydigipay.challenge.di.networkModule
import com.mydigipay.challenge.ui.authorize.di.authorizeModule
import com.mydigipay.challenge.ui.detail.di.detailModule
import com.mydigipay.challenge.ui.profile.di.profileModule
import com.mydigipay.challenge.ui.search.di.searchModule
import com.mydigipay.challenge.ui.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    dataAccessModule,
                    authorizeModule,
                    detailModule,
                    profileModule,
                    searchModule,
                    splashModule
                )
            )
        }
    }
}