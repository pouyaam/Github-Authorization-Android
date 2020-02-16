package com.mydigipay.challenge.app

import android.app.Application
import androidx.preference.PreferenceManager
import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.network.di.accessTokenModule
import com.mydigipay.challenge.network.di.networkModule
import com.mydigipay.challenge.utils.initPreferenceUtils
import com.mydigipay.challenge.ui.home.HomeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initPreferenceUtils(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }

    val appModule = module {
        factory {
            HomeRepositoryImpl(
                get(),
                get()
            )
        }
        single(named(APPLICATION_CONTEXT)) { applicationContext }
        single { PreferenceManager.getDefaultSharedPreferences(get()) }
        viewModel { HomeViewModel(get()) }
    }

}