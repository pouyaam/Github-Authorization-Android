package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.authorization.authorizationModule
import com.mydigipay.challenge.authorization.repository.token.TokenRepositoryImpl
import com.mydigipay.challenge.commit.commitModule
import com.mydigipay.challenge.core.extension.PreferenceHelper
import com.mydigipay.challenge.core.network.di.networkModule
import com.mydigipay.challenge.profile.profileModule
import com.mydigipay.challenge.search.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

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
                    authorizationModule,
                    searchModule,
                    commitModule,
                    profileModule
                )
            )
        }
    }

    val appModule = module {
        factory { TokenRepositoryImpl(get()) }
        single(named(APPLICATION_CONTEXT)) { applicationContext }
        single { PreferenceHelper.defaultPrefs(get()) }
    }

}