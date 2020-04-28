package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.BuildConfig
import com.mydigipay.challenge.data.network.di.accessTokenModule
import com.mydigipay.challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

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
                    viewModelModule,
                    serviceModule,
                    repositoryModule
                )
            )

            if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        }
    }
}