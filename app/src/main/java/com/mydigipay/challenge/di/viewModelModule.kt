package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.repos.RepositoriesListViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { RepositoriesListViewModel() }
}