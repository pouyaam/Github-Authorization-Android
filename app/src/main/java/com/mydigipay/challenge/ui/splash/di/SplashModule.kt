package com.mydigipay.challenge.ui.splash.di

import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_REPOSITORY
import com.mydigipay.challenge.ui.splash.model.SplashModel
import com.mydigipay.challenge.ui.splash.viewmodel.SplashViewModel
import org.koin.dsl.module

var splashModule = module {

    factory { SplashModel(get(NAMED_DEPENDENCY_REPOSITORY)) }

    factory { SplashViewModel(get(), get()) }

}