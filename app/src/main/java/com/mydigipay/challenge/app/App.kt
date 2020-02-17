package com.mydigipay.challenge.app

import android.app.Application
import androidx.preference.PreferenceManager
import com.mydigipay.challenge.network.di.accessTokenModule
import com.mydigipay.challenge.network.di.networkModule
import com.mydigipay.challenge.ui.commits.CommitsViewModel
import com.mydigipay.challenge.ui.home.HomeRepositoryImpl
import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.utils.initPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

class App : Application() {
    companion object {
//        const val CLIENT_ID = "68375a0e72182e5016d1"
//        const val CLIENT_SECRET = "83bf9101594e346b8888e27ba37627301aba7c48"
        const val REDIRECT_URI = "omidgitapp://authorization/"
    }

    override fun onCreate() {
        super.onCreate()
        initPreferenceUtils(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }

    val appModule = module {
        factory { HomeRepositoryImpl(get()) }
        single(named(APPLICATION_CONTEXT)) { applicationContext }
        single { PreferenceManager.getDefaultSharedPreferences(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { CommitsViewModel(get()) }
        viewModel { ProfileViewModel(get()) }
    }

}