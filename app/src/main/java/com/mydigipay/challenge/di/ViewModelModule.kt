package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.login.LoginViewModel
import com.mydigipay.challenge.ui.repository.list.RepositoryListViewModel
import com.mydigipay.challenge.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { SplashViewModel(get()) }
    viewModel { RepositoryListViewModel() }

}