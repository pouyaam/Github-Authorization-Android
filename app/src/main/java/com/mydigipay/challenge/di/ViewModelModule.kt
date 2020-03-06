package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.login.LoginViewModel
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.ui.repository.detail.RepositoryDetailViewModel
import com.mydigipay.challenge.ui.repository.list.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { RepositoryListViewModel(get()) }
    viewModel { RepositoryDetailViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

}