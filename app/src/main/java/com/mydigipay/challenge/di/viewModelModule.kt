package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.authorize.AuthorizeViewModel
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.ui.repo.RepoViewModel
import com.mydigipay.challenge.ui.search.SearchRepositoriesViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProfileViewModel() }
    viewModel { SearchRepositoriesViewModel(get()) }
    viewModel { RepoViewModel(get()) }
    viewModel { AuthorizeViewModel(get(), get()) }
}