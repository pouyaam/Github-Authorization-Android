package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.data.network.ConnectionLiveData
import com.mydigipay.challenge.di.appModule
import com.mydigipay.challenge.di.networkModule
import com.mydigipay.challenge.di.repositoryModule
import com.mydigipay.challenge.di.restModule
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
                    appModule, networkModule,
                    restModule, repositoryModule
                )
            )
        }

        // Init Connection Detector
        ConnectionLiveData.init(this)
    }

}