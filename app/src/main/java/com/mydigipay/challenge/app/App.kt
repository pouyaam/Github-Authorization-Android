package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.di.*
import com.mydigipay.challenge.network.di.accessTokenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    accessTokenModule,
                    authModule,
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}