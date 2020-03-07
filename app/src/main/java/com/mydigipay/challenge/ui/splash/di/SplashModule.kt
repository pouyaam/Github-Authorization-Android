package com.mydigipay.challenge.ui.splash.di

import com.mydigipay.challenge.di.Qualifiers
import com.mydigipay.challenge.ui.splash.model.SplashModel
import com.mydigipay.challenge.ui.splash.viewmodel.SplashViewModel
import org.koin.dsl.module

var splashModule = module {

    factory { SplashModel(get(Qualifiers.getNamedDependencyRepository(Qualifiers.tokenDataSource))) }

    factory { SplashViewModel(get(), get()) }

}