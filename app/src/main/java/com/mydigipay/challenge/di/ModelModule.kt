package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.login.LoginModel
import com.mydigipay.challenge.ui.main.MainModel
import com.mydigipay.challenge.ui.profile.ProfileModel
import com.mydigipay.challenge.ui.search.SearchModel
import com.mydigipay.challenge.ui.splash.SplashModel
import org.koin.dsl.module

val modelModule = module {
    factory { SplashModel(get()) }
    factory { MainModel() }
    factory { LoginModel(get()) }
    factory { ProfileModel(get()) }
    factory { SearchModel(get()) }
}