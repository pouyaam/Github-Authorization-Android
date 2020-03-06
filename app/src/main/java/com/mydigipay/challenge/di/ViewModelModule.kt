package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.main.MainViewModel
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}