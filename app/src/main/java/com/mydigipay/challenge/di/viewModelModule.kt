package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.authorize.AuthorizeViewModel
import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.repo.RepoViewModel
import com.mydigipay.challenge.ui.search.SearchRepositoriesViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { SearchRepositoriesViewModel(get()) }
    viewModel { RepoViewModel(get()) }
    viewModel { AuthorizeViewModel(get(), get()) }
}