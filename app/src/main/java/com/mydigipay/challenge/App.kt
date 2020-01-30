package com.mydigipay.challenge

import android.app.Application
import com.mydigipay.challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule, networkModule, repositoriesModule, usecaseModule, viewModelModule
                )
            )
        }
    }
}