package com.mydigipay.challenge.core

import android.app.Application
import com.mydigipay.challenge.di.appModule
import com.mydigipay.challenge.ui.authorize.di.authorizeModule
import com.mydigipay.challenge.ui.detail.di.detailModule
import com.mydigipay.challenge.ui.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

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
                    authorizeModule,
                    detailModule,
                    profileModule
                )
            )
        }
    }
}