package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.di.appModule
import com.mydigipay.challenge.network.di.accessTokenModule
import com.mydigipay.challenge.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }
}