package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.login.LoginViewModel
import com.mydigipay.challenge.ui.main.MainViewModel
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.ui.search.SearchViewModel
import com.mydigipay.challenge.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}