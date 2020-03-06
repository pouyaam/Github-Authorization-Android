package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.di.*
import com.mydigipay.challenge.github.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Plant Timber logger in
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        // Init Koin DI framework
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule, networkModule, modelModule,
                    viewModelModule, adapterModule, repositoryModule, restModule
                )
            )
        }

    }

}